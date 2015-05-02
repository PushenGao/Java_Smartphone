package ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.actionbarcompat.styled.R;

import model.FriendReq;
import ws.remote.RemoteServerProxy;

/**
 * Created by Jackyliz on 4/10/15.
 */

public class ProfileAndRemove extends ActionBarActivity {
    private Button addButton;
    private Button removeButton;
    private TextView showName;
    private TextView showAge;
    private TextView showGender;
    private String name;
    private String age;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_profileandremove);

        showName = (TextView) findViewById(R.id.profileandremove_textview2);
        showAge = (TextView) findViewById(R.id.profileandremove_textview3);
        showGender = (TextView) findViewById(R.id.profileandremove_textview4);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");
        gender = intent.getStringExtra("gender");

        showName.setText(name);
        showAge.setText("Age: " + age);
        showGender.setText("Gender: " + gender);

        //when chat button is clicked, turn to chat window
        addButton = (Button) findViewById(R.id.profileandremove_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileAndRemove.this, ChatWindow.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        removeButton = (Button) findViewById(R.id.profileandremove_delete);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_profile_and_remove, menu);
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


    public void deleteFriend(View view){
//when the delete button is clicked, detele the friend and update in the server
        RemoteServerProxy remoteServerProxy = new RemoteServerProxy();
        FriendReq friendReq = new FriendReq();
        friendReq.setSender(LogIn.loginAccount.getBasicAccount().getName());
        friendReq.setReceiver(name);
        friendReq.setAction("delete");
        remoteServerProxy.reqFriend(friendReq);
    }
}
