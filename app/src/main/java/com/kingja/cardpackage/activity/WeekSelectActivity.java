package com.kingja.cardpackage.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.kingja.cardpackage.adapter.WeekdayAdapter;
import com.kingja.cardpackage.entiy.Weekday;
import com.kingja.cardpackage.ui.PullToBottomRecyclerView;
import com.tdr.wisdome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/4/18 14:53
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class WeekSelectActivity extends BackTitleActivity {

    private ListView mLvSelector;
    private static final String[] weekdays = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private List<Weekday> weekdayList = new ArrayList<>();
    private WeekdayAdapter weekdayAdapter;

    @Override
    protected void initVariables() {
        for (int i = 0; i < weekdays.length; i++) {
            weekdayList.add(new Weekday(false, weekdays[i]));
        }
    }

    @Override
    protected void initContentView() {
        mLvSelector = (ListView) findViewById(R.id.lv_selector);

        weekdayAdapter = new WeekdayAdapter(this, weekdayList);
        mLvSelector.setAdapter(weekdayAdapter);

    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_week_select;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mLvSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                weekdayAdapter.selectWeekday(position);
            }
        });

        setOnRightClickListener(new OnRightClickListener() {
            @Override
            public void onRightClick() {
                String weekdayInt = weekdayAdapter.getWeekdayInt();
                String weekdayStr = weekdayAdapter.getWeekdayStr();
                Intent intent = new Intent();
                intent.putExtra("weekdayInt", weekdayInt);
                intent.putExtra("weekdayStr", weekdayStr);
                setResult(RESULT_OK, intent);
                finish();
            }
        }, "完成");
    }

    @Override
    protected void setData() {
        setTitle("频率");
    }

}
