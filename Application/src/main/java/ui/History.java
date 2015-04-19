package ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.actionbarcompat.styled.R;


public class History extends ActionBarActivity {
    String totalDistance;
    String totalTime;
    TextView showDistance;
    TextView showTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_history);

        showDistance = (TextView) findViewById(R.id.show_total_run);
        showTime = (TextView) findViewById(R.id.show_total_time);

        totalDistance = LogIn.loginAccount.getHistoryRecord().getTotalDistance();
        totalTime = LogIn.loginAccount.getHistoryRecord().getTotalTime();

        showDistance.setText(totalDistance);
        showTime.setText(totalTime);
    }

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
