package com.xunce.electrombile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogUtil;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.UpdatePasswordCallback;
import com.xunce.electrombile.R;

public class ForgetActivity extends Activity implements View.OnClickListener {
    private EditText validation_edt;
    private EditText newPwd;
    private Button validationOk;
    private Button sendValidation;
    private EditText phoneNum;
    private String telNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("找回密码");
        setContentView(R.layout.activity_forget);
        initView();
    }

    private void initView(){
        validation_edt = (EditText)findViewById(R.id.validation_forget);
        newPwd = (EditText) findViewById(R.id.newPwd);
        phoneNum = (EditText)findViewById(R.id.phoneNum);
        validationOk = (Button) findViewById(R.id.validation_ok);
        sendValidation =(Button) findViewById(R.id.validation_send);
        validationOk.setOnClickListener(this);
        sendValidation.setOnClickListener(this);
     }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.validation_ok:
                telNum = phoneNum.getText().toString();
                String validationCode = validation_edt.getText().toString();
                final String newPassword = newPwd.getText().toString();
                if("".equals(telNum) ||
                        "".equals(validationCode)
                        ||telNum.length() != 11
                        ||"".equals(newPassword)){
                    Toast.makeText(getApplicationContext(),
                            "请仔细检查",
                            Toast.LENGTH_SHORT)
                            .show();
                }else{
                    AVUser.resetPasswordBySmsCodeInBackground(validationCode,newPassword,new UpdatePasswordCallback() {
                        @Override
                        public void done(AVException e) {
                            if( e == null){
                                Toast.makeText(getApplicationContext(),
                                        "密码更改成功",
                                        Toast.LENGTH_SHORT)
                                        .show();
                                Intent intent = new Intent(ForgetActivity.this,LoginActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("tel",telNum);
                                bundle.putString("pwd",newPassword);
                                intent.putExtras(bundle);
                                //startActivity(intent);
                                ForgetActivity.this.finish();
                            }else{
                                Toast.makeText(getApplicationContext(),
                                        "验证码错误",
                                        Toast.LENGTH_SHORT)
                                        .show();
                                LogUtil.log.i(e.toString());
                            }
                        }
                    });
                }
                break;
            case R.id.validation_send:
                telNum = phoneNum.getText().toString();
                if("".equals(telNum) || telNum.length() != 11){
                    Toast.makeText(getApplicationContext(),
                            "电话填写错误"
                            ,Toast.LENGTH_SHORT)
                            .show();
                }else{
                    AVUser.requestPasswordResetBySmsCodeInBackground(telNum,new RequestMobileCodeCallback() {
                        @Override
                        public void done(AVException e) {
                            if(e == null) {
                                LogUtil.log.i("发送成功");
                                TimeCount time = new TimeCount(120000, 1000);
                                time.start();
                            }else{
                                LogUtil.log.i(e.toString());
                            }
                        }
                    });
                    }
                break;
            default: break;
                }
    }

    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {  //计时过程显示
            sendValidation.setClickable(false);
            sendValidation.setText(l/1000+"秒");
        }

        @Override
        public void onFinish() {
            sendValidation.setText("重新验证");
            sendValidation.setClickable(true);
        }
    }


}
