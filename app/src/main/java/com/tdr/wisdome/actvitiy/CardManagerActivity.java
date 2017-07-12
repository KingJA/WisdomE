package com.tdr.wisdome.actvitiy;

import android.text.TextUtils;

import com.kingja.cardpackage.activity.BackTitleActivity;
import com.kingja.cardpackage.db.ECardXutils3;
import com.kingja.cardpackage.entiy.AddUserCards;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetCardEvent;
import com.kingja.cardpackage.entiy.GetCityCardList;
import com.kingja.cardpackage.entiy.GetUserMessagePager;
import com.kingja.cardpackage.entiy.UserCard;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.DataManager;
import com.tdr.wisdome.R;
import com.tdr.wisdome.adapter.LeftCardAdapter;
import com.tdr.wisdome.adapter.OwnedCardAdapter;
import com.tdr.wisdome.view.FixedListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2016/8/15 13:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CardManagerActivity extends BackTitleActivity implements LeftCardAdapter.OnCardAddListener,
        OwnedCardAdapter.OnCardDeleteListener {
    public  List<String> mAllCards = new ArrayList<>();
    private FixedListView mLvOwnedCard;
    private FixedListView mLvLeftCard;
    private List<String> mOwnedCardList = new ArrayList<>();
    private List<String> mLeftCardList = new ArrayList<>();
    private OwnedCardAdapter mOwnedCardAdapter;
    private LeftCardAdapter mLeftCardAdapter;
    private List<GetCityCardList.ContentBean> supportedCards = new ArrayList<>();


    @Override
    protected void initVariables() {
    }


    @Override
    protected void initContentView() {
        mLvOwnedCard = (FixedListView) findViewById(R.id.lv_ownedCard);
        mLvLeftCard = (FixedListView) findViewById(R.id.lv_leftCard);
        mOwnedCardAdapter = new OwnedCardAdapter(this, mOwnedCardList);
        mLeftCardAdapter = new LeftCardAdapter(this, mLeftCardList);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_card_manager;
    }

    @Override
    protected void initNet() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("citycode", DataManager.getCityCode());
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants
                        .GetCityCardList, param)
                .setBeanType(GetCityCardList.class)
                .setCallBack(new WebServiceCallBack<GetCityCardList>() {
                    @Override
                    public void onSuccess(GetCityCardList bean) {
                        supportedCards = bean.getContent();
                        initCardData(supportedCards);
                        setProgressDialog(false);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
        mOwnedCardAdapter.setOnCardDeleteListener(this);
        mLeftCardAdapter.setOnCardAddListener(this);

    }

    @Override
    protected void setData() {
        mLvOwnedCard.setAdapter(mOwnedCardAdapter);
        mLvLeftCard.setAdapter(mLeftCardAdapter);
        setTitle("我的卡包");
    }

    private void initCardData(List<GetCityCardList.ContentBean> supportedCards) {
        for (GetCityCardList.ContentBean supportedCard : supportedCards) {
            mAllCards.add(supportedCard.getCardCode());
        }
        if (!TextUtils.isEmpty(DataManager.getCardCode())) {
            String[] owner = DataManager.getCardCode().split("#");
            for (int i = 0; i < owner.length; i++) {
                mOwnedCardList.add(owner[i]);
            }
        }
        mLeftCardList = getLeftList(mAllCards, mOwnedCardList);
        mOwnedCardAdapter.setData(mOwnedCardList);
        mLeftCardAdapter.setData(mLeftCardList);
    }

    public static List getLeftList(List totelList, List ownList) {
        List leftList = new ArrayList(Arrays.asList(new Object[totelList.size()]));
        Collections.copy(leftList, totelList);
        leftList.removeAll(ownList);
        return leftList;
    }


    @Override
    public void onCardAdd(final String cardCode) {
        mLeftCardAdapter.deleteCard(cardCode);
        mOwnedCardAdapter.addCard(cardCode);

        Map<String, Object> param = new HashMap<>();
        param.put("cardcode", cardCode);
        param.put("citycode", DataManager.getCityCode());
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants
                        .AddUserCards, param)
                .setBeanType(AddUserCards.class)
                .setCallBack(new WebServiceCallBack<AddUserCards>() {
                    @Override
                    public void onSuccess(AddUserCards bean) {
                        UserCard card = bean.getContent();
                        ECardXutils3.getInstance().saveOrUpdate(card);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                    }
                }).build().execute();
//    }
//
//
//    JSONObject jsonObject = new JSONObject();
//        try
//
//    {
//        jsonObject.put("cardcode", cardType);
//        jsonObject.put("citycode", KConstants.getCityCode());
//    } catch(
//    JSONException e)
//
//    {
//        e.printStackTrace();
//    }
//
//    final HashMap<String, String> map = new HashMap<>();
//        map.put("token",KConstants.getToken());
//        map.put("cardType","");
//        map.put("taskId","");
//        map.put("DataTypeCode","AddUserCards");
//        map.put("content",jsonObject.toString());
//        WebServiceUtils.callWebService(KConstants.WEBSERVER_URL,KConstants.WEBSERVER_CARDHOLDER,map,new
//            WebServiceUtils.WebServiceCallBack()
//
//    {
//        @Override
//        public void callBack (String result){
//        if (result != null) {
//            Log.e("添加卡片", result);
//            try {
//                JSONObject json = new JSONObject(result);
//                int resultCode = json.getInt("ResultCode");
//                String resultText = Utils.initNullStr(json.getString("ResultText"));
//                if (resultCode == 0) {
//                    String content = json.getString("Content");
//                    JSONObject obj = new JSONObject(content);
//                    CardInfo mInfo = new CardInfo();
//                    mInfo.setListID(Utils.initNullStr(obj.getString("ListID")));
//                    mInfo.setUserID(Utils.initNullStr(obj.getString("UserID")));
//                    mInfo.setUserCityID(Utils.initNullStr(obj.getString("UserCityID")));
//                    mInfo.setCityCode(Utils.initNullStr(obj.getString("CityCode")));
//                    mInfo.setCardCode(Utils.initNullStr(obj.getString("CardCode")));
//                    mInfo.setCardName(Utils.initNullStr(obj.getString("CardName")));
//                    mInfo.setCardLogo(Utils.initNullStr(obj.getString("CardLogo")));
//                    mInfo.setIsDelete(Utils.initNullStr(obj.getString("IsDelete")));
////                                    db.save(mInfo);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//    });
    }

    @Override
    public void onCardDelete(final String cardCode) {
        mOwnedCardAdapter.deleteCard(cardCode);
        mLeftCardAdapter.addCard(cardCode);
        String listID = ECardXutils3.getInstance().selectAllWhere(UserCard.class, "CardCode", cardCode).get(0)
                .getListID();


        Map<String, Object> param = new HashMap<>();
        param.put("ListID", listID);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants
                        .DeleteUserCards, param)
                .setBeanType(GetUserMessagePager.class)
                .setCallBack(new WebServiceCallBack<GetUserMessagePager>() {
                    @Override
                    public void onSuccess(GetUserMessagePager bean) {
                        ECardXutils3.getInstance().deleteById(UserCard.class, cardCode);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                    }
                }).build().execute();


    }

    @Override
    protected void onClickBack() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new GetCardEvent());
        StringBuilder sb = new StringBuilder();
        for (String card : mOwnedCardList) {
            sb.append(card);
            sb.append("#");
        }
        DataManager.putCardCodes(sb.toString());
        finish();
    }

}