package ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.actionbarcompat.styled.R;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.BasicAccount;
import ws.remote.RemoteServerProxy;


public class Recommend extends ActionBarActivity {
    private Button bt;
    private ListView recommend_listview;
    private RecommendationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_recommend);

        recommend_listview = (ListView) findViewById(R.id.recommend_listview);
        mAdapter = new RecommendationAdapter(this, getData());
        //will be used in real time
        //RemoteServerProxy remoteServerProxy = new RemoteServerProxy();
        //List<BasicAccount> basicAccountList = remoteServerProxy.getRecommend(LogIn.loginAccount.getBasicAccount().getName());
        //mAdapter = new RecommendationAdapter(this, basicAccountList);

        recommend_listview.setAdapter(mAdapter);

        bt = (Button)findViewById(R.id.recommend_button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recommend.this, Profile.class);
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
        getMenuInflater().inflate(R.menu.menu_recommend, menu);
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

    public void viewProfile(View view){
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }
}
