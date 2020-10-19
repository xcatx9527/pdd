package com.Allicnce.taobaoAlliance.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.base.BaseApplication;
import com.Allicnce.taobaoAlliance.common.immersionbar.ImmersionBar;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseQuickAdapter;
import com.Allicnce.taobaoAlliance.common.rcAdapter.BaseViewHolder;
import com.Allicnce.taobaoAlliance.common.view.SwitchButton;
import com.Allicnce.taobaoAlliance.model.SalersModel;
import com.Allicnce.taobaoAlliance.model.TerminalListEntity;
import com.Allicnce.taobaoAlliance.utils.AlertDialogUtils;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:46
 * @Email 1016224774@qq.com
 * @Description 设置
 */
public class SettingActivity extends BaseActivity {

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.btn_send)
    Button btnSend;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.ll_bindContainer)
    LinearLayout llBindContainer;
    @Bind(R.id.sb_upticket)
    SwitchButton sbUpticket;
    @Bind(R.id.sb_turnon)
    SwitchButton sbTurnon;
    @Bind(R.id.sb_setturnon)
    SwitchButton sbSetturnon;
    @Bind(R.id.cb_terminal1)
    CheckBox cbTerminal1;
    @Bind(R.id.cb_terminal2)
    CheckBox cbTerminal2;
    @Bind(R.id.bt_commit)
    Button btCommit;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.btn_relasesaler)
    Button btnrelasesaler;
    @Bind(R.id.tv_phonenum)
    TextView tvPhonenum;
    @Bind(R.id.ll_hasbind)
    LinearLayout llHasbind;

    @Bind(R.id.rv_terminal_list)
    RecyclerView rvterminallist;
    @Bind(R.id.sb_order)
    SwitchButton sbOrder;
    private SalersModel.TerminalControlListEntity controlListEntity;
    private BaseQuickAdapter adapter;
    private boolean canadd;
    private Dialog deletedialog;


    @Override
    public int getLayoutId() {

        showToolBar("绑定", "", true);
        return R.layout.activity_setting;
    }

    @Override
    public void onMessage(Bundle bundle) {

    }


    public void relaseSalers() {
       /* AddSalersRequest orderListRequest = new AddSalersRequest();
        orderListRequest.terminalControl.proprietor.id = getIntent().getStringExtra("id");
        CustomRequest.get(orderListRequest, AppConstant.RELASE_SALERS_URL, new ModelCallBack<OrderModel>() {
            @Override
            public void onSuccess(OrderModel orderModel, Call call, Response response) {
                if (!orderModel.returnCode.equals("0000")) {
                    showShortToast(orderModel.returnMessage);
                } else {
                    showShortToast("解除绑定成功");
                    canadd = true;
                    getSellTicket();
                    llBindContainer.setVisibility(View.VISIBLE);
                    llHasbind.setVisibility(View.GONE);
                    setResult(10011);
                    finish();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
            }
        });*/
    }


    public void getSellTicket() {
     /*   CanSellRequest model = new CanSellRequest();
        CustomRequest.get(model, AppConstant.TERMINAL_LIST_URL, new ModelCallBack<TerminalListModel>() {
            @Override
            public void onSuccess(TerminalListModel terminalListModel, Call call, Response response) {
                adapter.setNewData(terminalListModel.terminalList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                showShortToast("网络异常!");
            }
        });*/
    }

    @Override
    public void initView() {
        controlListEntity = (SalersModel.TerminalControlListEntity) getIntent().getSerializableExtra("bean");
        ImmersionBar.with(this).barColor(R.color.colorPrimary).init();
        if (!getIntent().hasExtra("tobind")) {
            llHasbind.setVisibility(View.VISIBLE);
            llBindContainer.setVisibility(View.GONE);
            tvName.setText(controlListEntity.proprietor.name);
            tvPhonenum.setText(controlListEntity.proprietor.account);
            sbUpticket.setChecked("1".equals(controlListEntity.ticketControlCode));
            sbSetturnon.setChecked("1".equals(controlListEntity.onOffTimeControlCode));
            sbTurnon.setChecked("1".equals(controlListEntity.onOffControlCode));
            sbOrder.setChecked("1".equals(controlListEntity.orderControlCode));
        } else {
            llHasbind.setVisibility(View.GONE);
            llBindContainer.setVisibility(View.VISIBLE);
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvterminallist.setLayoutManager(layoutManager);
        adapter = new BaseQuickAdapter<TerminalListEntity, BaseViewHolder>(R.layout.item_terminal) {
            @Override
            protected void convert(BaseViewHolder helper, TerminalListEntity item) {
                helper.setText(R.id.tv_terminal_name, item.id);
                if (controlListEntity != null) {
                    for (int i = 0; i < controlListEntity.terminalList.size(); i++) {
                        item.cancommit = item.id.equals(controlListEntity.terminalList.get(i).id) ? true : false;
                        if (item.cancommit) {
                            break;
                        }
                    }
                }
                if (item.cancommit) {
                    helper.setChecked(R.id.cb_terminal, true);
                } else {
                    helper.setChecked(R.id.cb_terminal, false);
                }
                helper.setImageResource(R.id.iv_terminal_bg, R.drawable.terminal_a);
                helper.setOnCheckedChangeListener(R.id.cb_terminal, (buttonView, isChecked) -> {
                    if (isChecked) {
                        item.cancommit = true;
                    } else {
                        item.cancommit = false;
                    }
                });
            }
        };
        rvterminallist.setAdapter(adapter);
        getSellTicket();

      /*  switchButton.setChecked(true);
        switchButton.isChecked();
        switchButton.toggle();     //switch state
        switchButton.toggle(true);//switch without animation
        switchButton.setShadowEffect(true);//disable shadow effect
        switchButton.setEnabled(true);//disable button
        switchButton.setEnableEffect(true);//disable the switch animation*/

    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                if (!TextUtils.isEmpty(etPhone.getText())) {
                    BaseApplication.instance.setRegisterCountDown(60 * 1000, 1000, btnSend, 1);
                }
                break;
            case R.id.bt_commit:

                break;
            case R.id.btn_relasesaler:
                deletedialog = AlertDialogUtils.showDeleteTicketDialog(this
                        , "是否确定解除绑定?", v1 -> {
                            deletedialog.dismiss();
                            relaseSalers();
                        });
                break;

        }

    }

    @Override
    public void setListener() {
        btCommit.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        btnrelasesaler.setOnClickListener(this);
    }


}
