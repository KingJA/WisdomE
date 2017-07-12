package com.tdr.wisdome.actvitiy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.cardpackage.db.ECardXutils3;
import com.kingja.cardpackage.entiy.KJBikeCode;
import com.kingja.cardpackage.ui.SideBar;
import com.tdr.wisdome.R;
import com.tdr.wisdome.adapter.BrandListAdapter;
import com.tdr.wisdome.adapter.ResultBrandListAdapter;
import com.tdr.wisdome.model.BikeCode;
import com.tdr.wisdome.model.SortModel;
import com.tdr.wisdome.util.BrandSortUtils;
import com.tdr.wisdome.util.CharacterParser;
import com.tdr.wisdome.util.PinyinComparator;
import com.tdr.wisdome.util.Utils;
import com.tdr.wisdome.view.ZProgressHUD;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 品牌列表
 * Created by Linus_Xie on 2016/9/22.
 */
public class BrandActivity extends Activity implements Handler.Callback {
    private static final String TAG = "BrandActivity";


    private ViewGroup emptyView;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> sortModelList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    private List<KJBikeCode> brandsList = new ArrayList<>();
    private List<SortModel> hotSortList = new ArrayList<SortModel>();

    private ZProgressHUD mProgressHUD;

    private Context mContext;
    private Handler mHandler;

    private BrandListAdapter mBrandListAdapter;
    private ResultBrandListAdapter mResultBrandListAdapter;
    private List<SortModel> models;

    private ListView mListBrand;
    private TextView mTextDialog;
    private SideBar mSidrbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        mContext = this;
        mHandler = new Handler(this);
        initView();
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<SortModel> mSortList = new ArrayList<SortModel>();
                brandsList= ECardXutils3.getInstance().selectAllWhere(KJBikeCode.class,"type","1");
                try {
                    for (int i = 0; i < brandsList.size(); i++) {
                        SortModel sortModel = new SortModel();
                        sortModel.setGuid(brandsList.get(i).getCODE());
                        sortModel.setName(brandsList.get(i).getNAME());
                        sortModel.setSort(brandsList.get(i).getSORT()+"");
                        String pinyin = characterParser
                                .getSelling(brandsList.get(i).getNAME());
                        String sortString = pinyin.substring(0, 1)
                                .toUpperCase();
                        // 正则表达式，判断首字母是否是英文字母
                        if (sortString.matches("[A-Z]")) {
                            sortModel.setSortLetters(sortString
                                    .toUpperCase());
                        } else {
                            sortModel.setSortLetters("#");
                        }

                        mSortList.add(sortModel);
                        hotSortList.add(sortModel);
                    }
                    // 根据a-z进行排序源数据
                    Collections.sort(mSortList, pinyinComparator);
                    //热门品牌排序
                    Collections.sort(hotSortList, new BrandSortUtils());
                } catch (Exception e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(-1);
                }
                Message message = new Message();
                message.what = 1;
                message.obj = mSortList;
                mHandler.sendMessage(message);
            }
        }).start();

        mEditSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterData(s.toString());
            }
        });

    }

    private void filterData(String s) {
        List<SortModel> filterDataList = new ArrayList<>();
        if (TextUtils.isEmpty(s)) {
            filterDataList = sortModelList;
        } else {
            filterDataList.clear();
            for (SortModel sortModel : sortModelList) {
                String name = sortModel.getName();
                if (name.indexOf(s.toString()) != -1 || characterParser.getSelling(name).startsWith(s.toString())) {
                    filterDataList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(filterDataList, pinyinComparator);
        mBrandListAdapter.updateListView(filterDataList);
    }

    private void selectedBrand(SortModel sortModel) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("brandName", sortModel.getName());
        bundle.putString("brandCode", sortModel.getGuid());
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
    private RelativeLayout mRelativeTitle;
    private ImageView mImageBack;
    private TextView mTextTitle;
    private TextView mTextDeal;
    private ImageView mImageScan;
    private EditText mEditSearch;
    private ImageView mImageSearchClear;



    private void initView() {
        mListBrand = (ListView) findViewById(R.id.list_brand);
        mTextDialog = (TextView) findViewById(R.id.text_dialog);
        mSidrbar = (SideBar) findViewById(R.id.sidrbar);
        mRelativeTitle = (RelativeLayout) findViewById(R.id.relative_title);
        mImageBack = (ImageView) findViewById(R.id.image_back);
        mTextTitle = (TextView) findViewById(R.id.text_title);
        mTextDeal = (TextView) findViewById(R.id.text_deal);
        mImageScan = (ImageView) findViewById(R.id.image_scan);
        mEditSearch = (EditText) findViewById(R.id.edit_search);
        mImageSearchClear = (ImageView) findViewById(R.id.image_searchClear);

        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mTextTitle.setText("车辆品牌");
        mProgressHUD = new ZProgressHUD(BrandActivity.this);
        mProgressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
        mProgressHUD.setMessage("");
        mProgressHUD.show();

        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        mSidrbar.setTextView(mTextDialog);
        mSidrbar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = mBrandListAdapter.getLetterPosition(s);
                if (position == -1) {
                    position += 1;
                }
                mListBrand.setSelection(position);
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case -1:
                mProgressHUD.dismiss();
                Utils.myToast(mContext, "车辆品牌加载失败，请重新读取！");
                break;

            case 0:

                break;

            case 1:
                mProgressHUD.dismiss();
                sortModelList = (List<SortModel>) msg.obj;
                if (brandsList.size() > 0) {
                    mBrandListAdapter = new BrandListAdapter(this, sortModelList, hotSortList);
                    mListBrand.setAdapter(mBrandListAdapter);
                    mBrandListAdapter.setOnBrandClickListener(new BrandListAdapter.OnBrandClickListener() {
                        @Override
                        public void onBrandClick(SortModel sortModel) {
                            selectedBrand(sortModel);
                        }
                    });
                } else {
                    Utils.myToast(mContext, "车辆品牌加载失败，请重新读取！");
                }

                break;
        }
        return false;
    }
}
