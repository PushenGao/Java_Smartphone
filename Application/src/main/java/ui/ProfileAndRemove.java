package ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.actionbarcompat.styled.R;

import ws.remote.RemoteServerProxy;


public class ProfileAndRemove extends ActionBarActivity {

    private Button addButton;
    private Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_profileandremove);

        addButton = (Button) findViewById(R.id.profileandremove_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileAndRemove.this, ChatWindow.class);
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

//TODO after add the button, can delete the friend request to server
    public void deleteFriend(View view){
        RemoteServerProxy remoteServerProxy = new RemoteServerProxy();

    }
}
