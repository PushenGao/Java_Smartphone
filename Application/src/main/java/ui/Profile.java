package ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.actionbarcompat.styled.R;

import model.FriendReq;
import ws.remote.RemoteServerProxy;


public class Profile extends ActionBarActivity {
    private Button addFriendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_profile);

        addFriendBtn = (Button) findViewById(R.id.profile_button1);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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

    //TODO after add the button, can set the friend request to server
    public void addFriend(View view){
        RemoteServerProxy remoteServerProxy = new RemoteServerProxy();
        FriendReq friendReq = new FriendReq();
        friendReq.setSender(LogIn.loginAccount.getBasicAccount().getName());
        // friendReq.setReceiver();
        friendReq.setAction("request");
    }
}
