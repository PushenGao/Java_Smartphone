package ui;

import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.actionbarcompat.styled.R;


public class ShakeActivityToBeAddedIntoRecommend extends ActionBarActivity {
    ShakeListener mShakeListener = null;
    Vibrator mVibrator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_activity_to_be_added_into_recommend);
        // drawerSet ();//设置 drawer监听 切换 按钮的方向

        // 获得振动器服务
        mVibrator = (Vibrator) getApplication().getSystemService(
                VIBRATOR_SERVICE);
        // 实例化加速度传感器检测类
        mShakeListener = new ShakeListener(ShakeActivityToBeAddedIntoRecommend.this);

        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {

            public void onShake() {
                mShakeListener.stop();
                startVibrato(); // 开始 震动
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast mtoast;
                        int time = 10;
                        mtoast = Toast.makeText(ShakeActivityToBeAddedIntoRecommend.this,
                                "摇啊摇啊摇啊摇",Toast.LENGTH_LONG);
                        mtoast.show();
                        mVibrator.cancel();
                        mShakeListener.start();
                    }
                }, 2000);
            }
        });
    }

    // 定义震动
    public void startVibrato() {
        mVibrator.vibrate(new long[] { 500, 200, 500, 200 }, -1);
        // 第一个｛｝里面是节奏数组，
        // 第二个参数是重复次数，-1为不重复，非-1则从pattern的指定下标开始重复
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mShakeListener != null) {
            mShakeListener.stop();
        }
    }
}
