package com.Allicnce.taobaoAlliance.activity.login;

import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:31
 * @Email 1016224774@qq.com
 * @Description 注册第二步
 */
public class RegisterSecondStepActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    @Bind(R.id.iv_headpic)
    ImageView ivHeadpic;
    @Bind(R.id.gradeicon)
    ImageView gradeicon;
    @Bind(R.id.et_grade)
    EditText etGrade;
    @Bind(R.id.gradelayout)
    RelativeLayout gradelayout;
    @Bind(R.id.passicon)
    ImageView passicon;
    @Bind(R.id.et_pass)
    EditText etPass;
    @Bind(R.id.passlayout)
    RelativeLayout passlayout;
    @Bind(R.id.makesurepassicon)
    ImageView makesurepassicon;
    @Bind(R.id.et_makesurepass)
    EditText etMakesurepass;
    @Bind(R.id.makesuerpasslayout)
    RelativeLayout makesuerpasslayout;
    @Bind(R.id.bt_finish)
    Button btFinish;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.register_second_step;
    }

    @Override
    public void initView() {


    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
