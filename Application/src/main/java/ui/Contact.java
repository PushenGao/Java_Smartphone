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
import android.widget.Toast;

import com.example.android.actionbarcompat.styled.R;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.BasicAccount;
import ws.remote.RemoteServerProxy;
import ws.remote.SearchAccount;


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
//                RemoteServerProxy rsp = new RemoteServerProxy();
//                BasicAccount searchRst = rsp.searchAccount(userid);
//                if(searchRst != null){
//                    String name = searchRst.getName();
//                    String age = searchRst.getAge();
//                    String gender = searchRst.getGender();
//                    Intent searchIntent = new Intent(Contact.this, Profile.class);
//                    searchIntent.putExtra("name", name)
//                            .putExtra("age", age)
//                            .putExtra("gender", gender);
//                    startActivity(searchIntent);
//                }else{
//                    //TODO cannot find
//                }
                search_bar = (EditText) findViewById(R.id.search_bar);
                String userid = search_bar.getText().toString();
                Toast.makeText(getApplicationContext(), userid, Toast.LENGTH_SHORT).show();

            }
        });

        agreeButton = (Button) findViewById(R.id.agree_button);



        contact_listView.setAdapter(mAdaper);

        pending_listView.setAdapter(pendingAdaper);
    }

    private List<Account> getData()
    {
        List<Account> list=new ArrayList<Account>();

        BasicAccount basicAccount1 = new BasicAccount();
        Account friend1=new Account();
        friend1.setBasicAccount(basicAccount1);
        friend1.getBasicAccount().setName("Nancy");
        friend1.getBasicAccount().setAge("18");
        friend1.getBasicAccount().setGender("F");

        BasicAccount basicAccount2 = new BasicAccount();
        Account friend2=new Account();
        friend2.setBasicAccount(basicAccount2);
        friend2.getBasicAccount().setName("Joe");

        BasicAccount basicAccount3 = new BasicAccount();
        Account friend3=new Account();
        friend3.setBasicAccount(basicAccount3);
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
