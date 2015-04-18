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
    RelativeLayout layout;
    Button searchButton;
    Button agreeButton;
    EditText search_bar;
    ListView contact_listView;
    private FriendAdapter mAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_contact);

        contact_listView=(ListView) findViewById(R.id.contact_listview);
        mAdaper=new FriendAdapter(this, getData());

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
    }

    private List<Account> getData()
    {
        List<Account> list=new ArrayList<Account>();
//		好友数据共7个好友数据
        Account friend1=new Account();
        //friend1.setIv_photo(R.drawable.show04);
        friend1.setName("西西");
        //friend1.setTv_02("你是我的贝贝，我是你的舒舒");

        Account friend2=new Account();
        //friend2.setIv_photo(R.drawable.show01);
        friend2.setName("小南");
        //friend2.setTv_02("我还有什么理由可以回到以前？");

        Account friend3=new Account();
        //friend3.setIv_photo(R.drawable.show02);
        friend3.setName("城池");
       // friend3.setTv_02("做你坚固的壁垒");
//
//        FriendModel friend4=new FriendModel();
//        friend4.setIv_photo(R.drawable.show03);
//        friend4.setTv_01("阿德哈");
//        friend4.setTv_02("嘻嘻哈哈，快快乐乐");
//
//        FriendModel friend5=new FriendModel();
//        friend5.setIv_photo(R.drawable.show05);
//        friend5.setTv_01("阿斯顿");
//        friend5.setTv_02("给我一个不伤心的理由");
//
//        FriendModel friend6=new FriendModel();
//        friend6.setIv_photo(R.drawable.show01);
//        friend6.setTv_01("小南");
//        friend6.setTv_02("我还有什么理由可以回到以前？");
//
//        FriendModel friend7=new FriendModel();
//        friend7.setIv_photo(R.drawable.show02);
//        friend7.setTv_01("城池");
//        friend7.setTv_02("做你坚固的壁垒");
        list.add(friend1);
        list.add(friend2);
        list.add(friend3);
//        list.add(friend4);
//        list.add(friend5);
//        list.add(friend6);
//        list.add(friend7);

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
