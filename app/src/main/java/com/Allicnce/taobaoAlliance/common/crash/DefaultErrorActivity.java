/*
 * Copyright 2014-2017 Eduard Ereza Martínez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.Allicnce.taobaoAlliance.common.crash;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;


public final class DefaultErrorActivity extends BaseActivity {


    Button btnRestart;
    Button btnInfo;

    private void copyErrorToClipboard() {
        String errorInformation = CustomActivityOnCrash.getAllErrorDetailsFromIntent(DefaultErrorActivity.this, getIntent());
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("异常信息", errorInformation);
        clipboard.setPrimaryClip(clip);
    }

    @Override
    public int getLayoutId() {
        return R.layout.crash_default_activity;
    }

    @Override
    public void initView() {

        btnRestart = (Button) findViewById(R.id.btn_restart);
        btnInfo = (Button) findViewById(R.id.btn_info);
        final CaocConfig config = CustomActivityOnCrash.getConfigFromIntent(getIntent());

        if (config.isShowRestartButton() && config.getRestartActivityClass() != null) {
            btnRestart.setText("重启app");
            btnRestart.setOnClickListener(v -> CustomActivityOnCrash.restartApplication(DefaultErrorActivity.this, config));
        } else {
            btnRestart.setOnClickListener(v -> CustomActivityOnCrash.closeApplication(DefaultErrorActivity.this, config));
        }


        if (config.isShowErrorDetails()) {
            btnInfo.setOnClickListener(v -> {
                AlertDialog dialog = new AlertDialog.Builder(DefaultErrorActivity.this)
                        .setTitle("异常详情")
                        .setMessage(CustomActivityOnCrash.getAllErrorDetailsFromIntent(DefaultErrorActivity.this, getIntent()))
                        .setPositiveButton("关闭", null)
                        .setNeutralButton("复制到剪贴板",
                                (dialog1, which) -> {
                                    copyErrorToClipboard();
                                    Toast.makeText(DefaultErrorActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
                                })
                        .show();
            });
        } else {
            btnInfo.setVisibility(View.GONE);
        }

    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {

    }


}
