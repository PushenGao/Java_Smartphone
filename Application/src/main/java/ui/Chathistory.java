package ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.android.actionbarcompat.styled.R;

import java.util.ArrayList;
import java.util.List;

import model.Account;


public class Chathistory extends ActionBarActivity {
    private RelativeLayout layout;
    private ListView chat_listview;
    private RecentChatAdapter mAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_chat_history);

        chat_listview = (ListView) findViewById(R.id.chat_listview);
        mAdpter = new RecentChatAdapter(this, getData());

        chat_listview.setAdapter(mAdpter);

        layout = (RelativeLayout) findViewById(R.id.chat_relative);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chathistory.this, ChatWindow.class);
                startActivity(intent);
            }
        });
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
//        getMenuInflater().inflate(R.menu.menu_chathistory, menu);
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
