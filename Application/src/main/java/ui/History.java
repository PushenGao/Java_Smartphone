package ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.android.actionbarcompat.styled.R;



public class History extends ActionBarActivity {
    private String totalDistance;
    private String totalTime;
    private TextView showDistance;
    private TextView showTime;
    private Intent intent;
    private BroadcastReceiver broadcastReceiver;
    private Handler mHandler = new Handler();
    private int count = 0;
    private int count1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_history);

        showDistance = (TextView) findViewById(R.id.show_total_run);
        showTime = (TextView) findViewById(R.id.show_total_time);

        totalDistance = LogIn.loginAccount.getBasicAccount().getHistoryRecord().getTotalDistance();
        totalTime = LogIn.loginAccount.getBasicAccount().getHistoryRecord().getTotalTime();
//
        showDistance.setText(totalDistance);
        showTime.setText(totalTime);

//        intent = new Intent(this, BroadcastService.class);
//        broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                updateUI(intent);
//            }
//        };
    }


    @Override
    public void onResume() {
        super.onResume();
        mHandler.removeCallbacks(mUpdateClockTask);
        mHandler.postDelayed(mUpdateClockTask, 100);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mUpdateClockTask);
    }

    private Runnable mUpdateClockTask = new Runnable() {
        public void run() {
            updateClock();
            mHandler.postDelayed(mUpdateClockTask, 1000);
        }
    };

    public void updateClock(){
        showDistance = (TextView) findViewById(R.id.show_total_run);
        showTime = (TextView) findViewById(R.id.show_total_time);

        totalDistance = LogIn.loginAccount.getBasicAccount().getHistoryRecord().getTotalDistance();
        totalTime = LogIn.loginAccount.getBasicAccount().getHistoryRecord().getTotalTime();
//
        showDistance.setText(totalDistance);
        showTime.setText(totalTime);
//        showDistance.setText(String.valueOf(count++));
//        showTime.setText(String.valueOf(count1++));
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        startService(intent);
//        registerReceiver(broadcastReceiver, new IntentFilter(BroadcastService.BROADCAST_ACTION));
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        unregisterReceiver(broadcastReceiver);
//        stopService(intent);
//    }
//
//    private void updateUI(Intent intent) {
//        String counter = intent.getStringExtra("counter");
//        String time = intent.getStringExtra("time");
//        showDistance = (TextView) findViewById(R.id.show_total_run);
//        showTime = (TextView) findViewById(R.id.show_total_time);
//        showDistance.setText(time);
//        showTime.setText(counter);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
