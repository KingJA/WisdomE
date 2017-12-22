package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.kingja.recyclerviewhelper.RecyclerViewHelper;
import com.tdr.wisdome.R;

import java.util.Collections;
import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MainCardAdapter extends BaseRvAdaper<String> implements RecyclerViewHelper.OnItemCallback {


    public MainCardAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new GiftViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_main_card;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, String bean, final int position) {
        final GiftViewHolder holder = (GiftViewHolder) baseHolder;
        holder.tv_msg.setText(getCardName(bean));
        holder.iv_icon.setBackgroundResource(getCardImage(bean));
    }

    public void onMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onSwipe(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }


    class GiftViewHolder extends ViewHolder {
        public TextView tv_msg;
        public ImageView iv_icon;

        public GiftViewHolder(View itemView) {
            super(itemView);
            tv_msg = (TextView) itemView.findViewById(R.id.tv_msg);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }

    protected String getCardName(String cardType) {
        String cardName = "";
        switch (cardType) {
            case "1001":
                cardName = "我的住房";
                break;
            case "1002":
                cardName = "我家出租房";
                break;
            case "1003":
                cardName = "我的车";
                break;
            case "1004":
                cardName = "我的店";
                break;
            case "1005":
                cardName = "亲情关爱";
                break;
            case "1006":
                cardName = "服务商城";
                break;
            case "1007":
                cardName = "出租房代管";
                break;
            case "1008":
                cardName = "出租房中介";
                break;
            case "1009":
                cardName = "NFC门禁卡";
                break;
            case "1010":
                cardName = "智能充电器";
                break;
        }
        return cardName;
    }

    protected int getCardImage(String cardType) {
        int cardImage = 0;
        switch (cardType) {
            case "1001":
                cardImage = R.mipmap.image_myhome;
                break;
            case "1002":
                cardImage = R.mipmap.image_myrental;
                break;
            case "1003":
                cardImage = R.mipmap.image_mycar;
                break;
            case "1004":
                cardImage = R.mipmap.image_mystore;
                break;
            case "1005":
                cardImage = R.mipmap.image_mycare;
                break;
            case "1006":
                cardImage = R.mipmap.image_severstore;
                break;
            case "1007":
                cardImage = R.mipmap.image_rentalescrow;
                break;
            case "1008":
                cardImage = R.mipmap.image_myintermediary;
                break;
            case "1009":
                cardImage = R.mipmap.image_nfcdevice;
                break;
            case "1010":
                cardImage = R.mipmap.image_charger;
                break;
        }
        return cardImage;
    }
}
