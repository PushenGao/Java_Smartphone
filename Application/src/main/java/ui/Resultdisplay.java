package ui;
;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.actionbarcompat.styled.R;

import ws.remote.RemoteServerProxy;
import ws.remote.UpdateRunningRecord;

public class Resultdisplay extends ActionBarActivity {
    private Button rstBtn;
    private TextView rstText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_resultdisplay);
        Intent intent = getIntent();
        String message = intent.getStringExtra(Mainpage.EXTRA_MESSAGE);
        rstBtn = (Button)findViewById(R.id.result_btn);
        rstText = (TextView) findViewById(R.id.rstTexxt);

        String[] result = message.split("werun");
        long time = (long)Double.parseDouble(result[1]);
        int intTime = (int) (time/1000);
        int min = intTime / 60;
        int second = intTime % 60;

        LogIn.loginAccount.getBasicAccount().getHistoryRecord().setUserid(LogIn.loginAccount.getBasicAccount().getName());
        LogIn.loginAccount.getBasicAccount().getHistoryRecord().addDistance(result[0]);
        LogIn.loginAccount.getBasicAccount().getHistoryRecord().addTime(intTime);

        RemoteServerProxy remoteServerProxy = new RemoteServerProxy();
        remoteServerProxy.updateHistoryRecord(LogIn.loginAccount.getBasicAccount().getHistoryRecord());

        rstText.setText(result[0] + " meters " + min + " mins " + second + " s");
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

    public void returntomain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}