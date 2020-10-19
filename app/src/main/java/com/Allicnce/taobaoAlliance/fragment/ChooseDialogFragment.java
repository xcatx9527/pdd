package com.Allicnce.taobaoAlliance.fragment;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.commonutils.DisplayUtil;
import com.Allicnce.taobaoAlliance.model.MainModel;
import com.Allicnce.taobaoAlliance.request.SalesRequest;
import com.Allicnce.taobaoAlliance.widget.CheckAbleLinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:53
 * @Email 1016224774@qq.com
 * @Description
 */
@SuppressLint("ValidFragment")
public class ChooseDialogFragment extends BaseDialogFragment {


    @Bind(R.id.start_ime)
    TextView startIme;
    @Bind(R.id.end_time)
    TextView endTime;
    @Bind(R.id.rb_yesday)
    RadioButton rbYesday;
    @Bind(R.id.rb_week)
    RadioButton rbWeek;
    @Bind(R.id.rb_month)
    RadioButton rbMonth;
    @Bind(R.id.rb_all)
    RadioButton rbAll;
    @Bind(R.id.rg_timecontainer)
    RadioGroup rgTimecontainer;
    @Bind(R.id.tv_terminal)
    TextView tvTerminal;
    @Bind(R.id.ll_terminal)
    LinearLayout llTerminal;
    @Bind(R.id.tv_cash)
    TextView tvCash;
    @Bind(R.id.cl_rss_l)
    CheckAbleLinearLayout clRssL;
    @Bind(R.id.cl_rss_r)
    CheckAbleLinearLayout clRssR;
    @Bind(R.id.ll_cash)
    LinearLayout llCash;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.cl_five)
    CheckAbleLinearLayout clFive;
    @Bind(R.id.cl_ten)
    CheckAbleLinearLayout clTen;
    @Bind(R.id.cl_twenty)
    CheckAbleLinearLayout clTwenty;
    @Bind(R.id.cl_thidty)
    CheckAbleLinearLayout clThidty;
    @Bind(R.id.ll_money)
    LinearLayout llMoney;
    @Bind(R.id.btn_commit)
    Button btnCommit;
    @Bind(R.id.activity_filter)
    LinearLayout activityFilter;
    private List<MainModel.TerminalListEntity> terminallist;
    private int CurrentTab = 0;

    @SuppressLint("ValidFragment")
    public ChooseDialogFragment(int currentTab) {
        this.CurrentTab = currentTab;
    }

    @Override
    protected int getDialogResource() {
        return R.layout.dialog_content;
    }

    @Override
    protected void initView() {
        initDialog();
    }

    @Override
    public void onMessage(Bundle bundle) {
        super.onMessage(bundle);
        if (bundle != null) {
            terminallist = (List<MainModel.TerminalListEntity>) bundle.getSerializable("terminallist");
        }

    }

    public String getDateBeforeToday(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, n);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return date;
    }

    private void initDialog() {
        setCancelable(true);
        if (CurrentTab == 0) {
            llCash.setVisibility(View.GONE);
            llMoney.setVisibility(View.GONE);
            tvCash.setVisibility(View.GONE);
            tvMoney.setVisibility(View.GONE);
        }
        rgTimecontainer.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_yesday:
                    startIme.setText(getDateBeforeToday(-1));
                    endTime.setText(getDateBeforeToday(-1));
                    break;
                case R.id.rb_week:
                    startIme.setText(getDateBeforeToday(-7));
                    endTime.setText(getDateBeforeToday(0));
                    break;
                case R.id.rb_month:
                    startIme.setText(getDateBeforeToday(-30));
                    endTime.setText(getDateBeforeToday(0));
                    break;
                case R.id.rb_all:
                    startIme.setText("");
                    endTime.setText("");
                    break;
            }

        });

        if (terminallist != null || CurrentTab == 3) {
            for (int i = 0; i < terminallist.size(); i++) {
                CheckAbleLinearLayout view1 = (CheckAbleLinearLayout) getActivity().getLayoutInflater().inflate(R.layout.item_dialog_terminal, null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DisplayUtil.dp2px(80), DisplayUtil.dp2px(40));
                params.weight = 1;
                params.leftMargin = DisplayUtil.dp2px(10);
                params.rightMargin = DisplayUtil.dp2px(10);
                params.topMargin = DisplayUtil.dp2px(3);
                params.bottomMargin = DisplayUtil.dp2px(3);
                view1.setLayoutParams(params);
                TextView textView = (TextView) view1.findViewById(R.id.tv_name);
                textView.setText(terminallist.get(i).id + "");
                view1.setTag(terminallist.get(i).id + "");
                llTerminal.addView(view1);
            }
        } else {
            tvTerminal.setVisibility(View.GONE);
            llTerminal.setVisibility(View.GONE);
        }
    }

    public Dialog createDialog(final Context context, final TextView edit, final String location) {
        DatePickerDialog dialog = null;
        Calendar c = Calendar.getInstance();
        // 点击"设置"按钮时调用onDateSet方法
        dialog = new DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;

            // 为时间time赋值
            String time = "";
            time = mYear + "-" + (mMonth + 1) + "-" + mDay;
            String time_a;
            if (location.equals("head")) {
                time_a = mYear + "-" + (mMonth + 1) + "-" + (mDay + 1);
            } else {
                time_a = mYear + "-" + (mMonth + 1) + "-" + (mDay);
            }
            String time_head = startIme.getText() + "";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date headDate = null;
            try {
                startDate = formatter.parse(time_a);
                if (!TextUtils.isEmpty(time_head)) {
                    headDate = formatter.parse(time_head);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (!location.equals("head") && headDate != null) {
                if (headDate.getTime() > startDate.getTime()) {
                    showShortToast("结束日期不能小于开始日期");
                    return;
                } else {
                    endTime.setText(time_a);
                }
            } else {
                edit.setText(time);
                if (location.equals("head")) {
                    Date endDate = new Date(startDate.getTime() + 432000000);
                    endTime.setText(formatter.format(endDate));
                }
            }
        },
                c.get(Calendar.YEAR), // 传入年份
                c.get(Calendar.MONTH), // 传入月份
                c.get(Calendar.DAY_OF_MONTH) // 传入天数
        );
        return dialog;

    }


    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.start_ime://比赛时间:
                createDialog(getActivity(), startIme, "head").show();
                break;
            case R.id.end_time:
                if (!"开始日期".equals(startIme.getText().toString())) {
                    createDialog(getActivity(), endTime, "").show();
                } else {
                    showShortToast("请先选择开始日期");
                }
                break;
            case R.id.btn_commit:
                List<String> cashlist = new ArrayList<>();
                List<String> moneylist = new ArrayList<>();
                for (int i = 0; i < llCash.getChildCount(); i++) {
                    CheckAbleLinearLayout checkAbleLinearLayout = (CheckAbleLinearLayout) llCash.getChildAt(i);
                    String tag = (String) checkAbleLinearLayout.getTag();
                    if (checkAbleLinearLayout.isChecked()) {
                        cashlist.add(tag);
                    }
                }
                for (int i = 0; i < llMoney.getChildCount(); i++) {
                    CheckAbleLinearLayout checkAbleLinearLayout = (CheckAbleLinearLayout) llMoney.getChildAt(i);
                    String tag = (String) checkAbleLinearLayout.getTag();
                    if (checkAbleLinearLayout.isChecked()) {
                        moneylist.add(tag);
                    }
                }
                List<String> terminalIdlist = new ArrayList<>();
                for (int i = 0; i < llTerminal.getChildCount(); i++) {
                    CheckAbleLinearLayout checkAbleLinearLayout = (CheckAbleLinearLayout) llTerminal.getChildAt(i);
                    if (checkAbleLinearLayout.isChecked()) {
                        terminalIdlist.add((String) checkAbleLinearLayout.getTag());
                    }
                }
                SalesRequest orderListRequest = new SalesRequest();
                orderListRequest.pager.currentPage = 1;
                orderListRequest.pager.pageSize = 10;
                if (!TextUtils.isEmpty(startIme.getText() + "")) {
                    orderListRequest.lotteryOrderQuery.createDatetimeBegin = startIme.getText() + "";
                }
                if (!TextUtils.isEmpty(endTime.getText() + "")) {
                    orderListRequest.lotteryOrderQuery.createDatetimeEnd = endTime.getText() + "";
                }
                if (cashlist.size() > 0) {
                    orderListRequest.lotteryOrderQuery.payTypeCodeArr = cashlist;
                }
                if (moneylist.size() > 0) {
                    orderListRequest.lotteryOrderQuery.priceArr = moneylist;
                }
                if (terminalIdlist.size() > 0) {
                    orderListRequest.lotteryOrderQuery.terminalIdArr = terminalIdlist;
                }
                Bundle bundle = new Bundle();
                bundle.putInt("currenttab", CurrentTab);
                bundle.putSerializable("SalesRequest", orderListRequest);
                sendMessage(bundle);
                dismiss();
                break;
        }
    }


    @Override
    public void setListener() {
        startIme.setOnClickListener(this);
        endTime.setOnClickListener(this);
        clRssR.setOnClickListener(this);
        clRssL.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
        clRssL.setOnClickListener(this);
        llCash.setOnClickListener(this);
        llMoney.setOnClickListener(this);
        llTerminal.setOnClickListener(this);
    }
}
