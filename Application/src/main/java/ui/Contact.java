package ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.android.actionbarcompat.styled.R;

import java.util.ArrayList;
import java.util.List;

import model.Account;


public class Contact extends ActionBarActivity {
    private RelativeLayout layout;
    private Button searchButton;
    private Button agreeButton;
    private EditText search_bar;
    private ListView contact_listView;
    private FriendAdapter mAdaper;
    private ListView pending_listView;
    private PendingRequestAdapter pendingAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_contact);

        contact_listView=(ListView) findViewById(R.id.contact_listview);
        mAdaper=new FriendAdapter(this, getData());
        //use in real time
        //mAdaper = new FriendAdapter(this, LogIn.loginAccount.getActiveFriends());

        pending_listView=(ListView) findViewById(R.id.contact_pendinglistview);
        pendingAdaper=new PendingRequestAdapter(this, getData());
        //use in real time
        //pendingAdaper=new PendingRequestAdapter(this, LogIn.loginAccount.getPendingFriends());

        contact_listView.setAdapter(mAdaper);
        
        layout = (RelativeLayout) findViewById(R.id.contact_relativelayout3);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contact.this, ProfileAndRemove.class);
                startActivity(intent);
            }
        });

        searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(Contact.this, Profile.class);
                startActivity(searchIntent);
            }
        });

        agreeButton = (Button) findViewById(R.id.agree_button);

        search_bar = (EditText) findViewById(R.id.search_bar);

        contact_listView.setAdapter(mAdaper);

        pending_listView.setAdapter(pendingAdaper);
    }

    private List<Account> getData()
    {
        List<Account> list=new ArrayList<Account>();
        Account friend1=new Account();
        friend1.getBasicAccount().setName("Nancy");

        Account friend2=new Account();
        friend2.getBasicAccount().setName("Joe");

        Account friend3=new Account();
        friend3.getBasicAccount().setName("Annie");

        list.add(friend1);
        list.add(friend2);
        list.add(friend3);

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_contact, menu);
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
