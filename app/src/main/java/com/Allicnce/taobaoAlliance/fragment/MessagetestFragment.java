package com.Allicnce.taobaoAlliance.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseFragment;
import com.Allicnce.taobaoAlliance.okgohttp.ModelCallBack;
import com.lzy.okgo.OkGo;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:54
 * @Email 1016224774@qq.com
 * @Description socket连接测试
 */
public class MessagetestFragment extends BaseFragment {
    @Bind(R.id.et_sent_id)
    EditText etSentId;
    @Bind(R.id.et_sentto_id)
    EditText etSenttoId;
    @Bind(R.id.et_sent_type)
    EditText etSentType;
    @Bind(R.id.et_sent_message)
    EditText etSentMessage;
    @Bind(R.id.et_sentto_type)
    EditText etSenttoType;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.bt_connect)
    Button btConnect;
    @Bind(R.id.bt_sent)
    Button btSent;
    public String loginUrl = "http://10.0.0.28:8080/websocketTest/login.do";
    public String sendmessage = "http://10.0.0.28:8080/websocketTest/sendMessage.do";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message_test;
    }

    @Override
    public void initView() {
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.bt_connect:
//                getActivity().startService(new Intent(getActivity(), SocketService.class));

                break;
            case R.id.bt_login:
                OkGo.get(loginUrl)//
                        .tag(this)//
                        .params("id", etSentId.getText() + "")//
                        .params("type", etSentType.getText() + "")//
                        .execute(new ModelCallBack<Object>() {
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<Object> response) {
                                super.onSuccess(response);
                            }

                        });
                break;
            case R.id.bt_sent:
                OkGo.get(sendmessage)//
                        .tag(this)//
                        .params("id1", etSentId.getText() + "")//
                        .params("id2", etSenttoId.getText() + "")//
                        .params("type1", etSentType.getText() + "")//
                        .params("type2", etSenttoType.getText() + "")//
                        .params("message", etSentMessage.getText() + "")//
                        .execute(new ModelCallBack<Object>() {

                        });
              /*  SocketMessageBean messageBean = newdialog SocketMessageBean();
                messageBean.id1 = etSentId.getText() + "";
                messageBean.id2 = etSenttoId.getText() + "";
                messageBean.type1 = etSentType.getText() + "";
                messageBean.type2 = etSenttoType.getText() + "";
                messageBean.message = etSentMessage.getText() + "";
                RxBus.getInstance().post("sentMessage",messageBean);*/
                break;
        }

    }

    @Override
    public void onMessage(Bundle bundle) {

    }

    @Override
    public void setListener() {
        btConnect.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        btSent.setOnClickListener(this);
    }

}
