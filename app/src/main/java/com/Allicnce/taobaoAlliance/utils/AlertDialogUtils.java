package com.Allicnce.taobaoAlliance.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.commonutils.DisplayUtil;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:06
 * @Email 1016224774@qq.com
 * @Description
 */
public class AlertDialogUtils {


    public static Dialog showDeleteTicketDialog(Activity activity, String message, View.OnClickListener confirmlistener) {

        View view = activity.getLayoutInflater().inflate(R.layout.dialog_delete_ticket, null);
       /* AlertDialog alertDialog = builder.setView(view).create();

        alertDialog.show();*/

        Dialog mDialog = new Dialog(activity, R.style.MyDialog);//自定义的样式，没有贴出代码来
        mDialog.setContentView(view);
        Button cancel_btn = (Button) view.findViewById(R.id.mBtn_Cancel);
        Button confirm_btn = (Button) view.findViewById(R.id.mBtn_commit);
        TextView tvmessage = (TextView) view.findViewById(R.id.tv_message);
        tvmessage.setText(message);
        cancel_btn.setOnClickListener(v -> mDialog.dismiss());
        confirm_btn.setOnClickListener(confirmlistener);
        mDialog.show();
        mDialog.getWindow().setLayout(DisplayUtil.dp2px(280), DisplayUtil.dp2px(180));

        return mDialog;
    }

    public static Dialog showshortTicketDialog(Activity activity, String message, View.OnClickListener confirmlistener) {

        View view = activity.getLayoutInflater().inflate(R.layout.dialog_shortmessage, null);
       /* AlertDialog alertDialog = builder.setView(view).create();

        alertDialog.show();*/
        Dialog mDialog = new Dialog(activity, R.style.MyDialog);//自定义的样式，没有贴出代码来
        mDialog.setContentView(view);
        Button cancel_btn = (Button) view.findViewById(R.id.mBtn_Cancel);
        Button confirm_btn = (Button) view.findViewById(R.id.mBtn_commit);
        TextView tvmessage = (TextView) view.findViewById(R.id.tv_message);
        tvmessage.setText(message);
        cancel_btn.setOnClickListener(v -> mDialog.dismiss());
        confirm_btn.setOnClickListener(confirmlistener);
        mDialog.show();
        mDialog.getWindow().setLayout(DisplayUtil.dp2px(250), DisplayUtil.dp2px(150));

        return mDialog;
    }

    public static Dialog showTicketDialog(Activity activity, String message, View.OnClickListener confirmlistener) {

        View view = activity.getLayoutInflater().inflate(R.layout.dialog_light_ticket, null);
       /* AlertDialog alertDialog = builder.setView(view).create();

        alertDialog.show();*/

        Dialog mDialog = new Dialog(activity, R.style.MyDialog);//自定义的样式，没有贴出代码来
        mDialog.setContentView(view);
        Button cancel_btn = (Button) view.findViewById(R.id.mBtn_Cancel);
        Button confirm_btn = (Button) view.findViewById(R.id.mBtn_commit);
        TextView tvmessage = (TextView) view.findViewById(R.id.tv_message);
        tvmessage.setText(message);
        cancel_btn.setOnClickListener(v -> mDialog.dismiss());
        confirm_btn.setOnClickListener(confirmlistener);
        mDialog.show();
        mDialog.getWindow().setLayout(DisplayUtil.dp2px(280), DisplayUtil.dp2px(230));

        return mDialog;
    }
}
