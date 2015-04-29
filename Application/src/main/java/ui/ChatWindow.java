package ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.android.actionbarcompat.styled.R;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dblayout.ChatRecordDAO;
import model.Account;
import model.ChatRecord;
import ws.remote.RemoteServerProxy;

/**
 * Created by Jackyliz on 4/10/15.
 */

public class ChatWindow extends ActionBarActivity {
    private Button sendMsgBtn;
    private Button callCameraButton;
    private EditText inputText;
    private ChatWindowAdapter mAdapter;
    private ListView chatWindowList;
    private List<ChatRecord> mData;
    private String myName= "";
    private String friendName = "";
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_chat_window);

        Intent intent = getIntent();
        myName = LogIn.loginAccount.getBasicAccount().getName();
        friendName = getIntent().getStringExtra("name");
        System.out.println(myName + " " + friendName);

        chatWindowList = (ListView) findViewById(R.id.chat_window_listview);
        mData = getData();
        mAdapter = new ChatWindowAdapter(this, mData);

        chatWindowList.setAdapter(mAdapter);
        chatWindowList.smoothScrollToPositionFromTop(mData.size(), 0);

        inputText = (EditText) findViewById(R.id.input_text);

        callCameraButton = (Button) findViewById(R.id.button_callcamera);
        callCameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });

        sendMsgBtn = (Button) findViewById(R.id.send_btn);
        sendMsgBtn.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        InputMethodManager imm=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        if(inputText.getText().toString()!="")
                        {
                            //获取时间
                            Calendar c=Calendar.getInstance();
                            StringBuilder mBuilder=new StringBuilder();
                            mBuilder.append(Integer.toString(c.get(Calendar.YEAR))+"-");
                            mBuilder.append(Integer.toString(c.get(Calendar.MONTH))+"-");
                            mBuilder.append(Integer.toString(c.get(Calendar.DATE))+" ");
                            mBuilder.append(Integer.toString(c.get(Calendar.HOUR_OF_DAY))+":");
                            mBuilder.append(Integer.toString(c.get(Calendar.MINUTE)));
                            //构造输入消息
                            ChatRecord Message=new ChatRecord(myName, friendName, mBuilder.toString(), inputText.getText().toString());
                            mData.add(Message);
                            //insert to local db, send to server
                            ChatRecordDAO chatRecordDAO = new ChatRecordDAO(getApplicationContext());
                            chatRecordDAO.insertRecord(Message);
                            RemoteServerProxy rsp = new RemoteServerProxy();
                            rsp.uploadChatRec(Message);
                            //构造返回消息
                            Message=new ChatRecord(friendName, myName, mBuilder.toString(), "收到！");
                            mData.add(Message);
                            //更新数据
                            mAdapter.Refresh();
                        }
                        //清空输入框
                        inputText.setText("");
                        //关闭输入法
                        imm.hideSoftInputFromWindow(null, InputMethodManager.HIDE_IMPLICIT_ONLY);
                        //滚动列表到当前消息
                        chatWindowList.smoothScrollToPositionFromTop(mData.size(), 0);
                    }
                }
        );


    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.removeCallbacks(mUpdateClockTask);
        mHandler.postDelayed(mUpdateClockTask, 100);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mUpdateClockTask);
    }

    private Runnable mUpdateClockTask = new Runnable() {
        public void run() {
            updateClock();
            mHandler.postDelayed(mUpdateClockTask, 1000);
        }
    };

    public void updateClock(){
        ChatRecordDAO chatRecordDAO = new ChatRecordDAO(this);
        RemoteServerProxy remoteServerProxy = new RemoteServerProxy();

        List<ChatRecord> list = remoteServerProxy.getChatRecord(LogIn.loginAccount.getBasicAccount().getName());
        List<ChatRecord> db_list = new ArrayList<ChatRecord>();

        if (list.size() != 0) {
            for (ChatRecord chatRecord : list) {
                chatRecordDAO.insertRecord(chatRecord);

            }
            db_list = chatRecordDAO.getAllRecord(friendName, myName);
//            for (ChatRecord chatRecord : db_list) {
//                System.out.println(chatRecord.getMyName() + " " + chat);
//            }
            Log.d("zheng",String.valueOf(db_list.size()));
            Collections.sort(db_list, new Comparator<ChatRecord>() {
                public int compare(ChatRecord o1, ChatRecord o2){
                    if(getDate(o1.getTimeStamp()) == getDate(o2.getTimeStamp()))
                        return 0;
                    return (getDate(o1.getTimeStamp()).compareTo(getDate(o2.getTimeStamp())) == 1  ? 1 : -1);
                }
            });

            chatWindowList = (ListView) findViewById(R.id.chat_window_listview);

            mAdapter = new ChatWindowAdapter(this, db_list);

            chatWindowList.setAdapter(mAdapter);
        }

//        layout = (RelativeLayout) findViewById(R.id.contact_relativelayout3);
//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Contact.this, ProfileAndRemove.class);
//                startActivity(intent);
//            }
//        });

    }

    public List<ChatRecord> getData() {

        List<ChatRecord> messages = new ArrayList<ChatRecord>();

        return messages;
    }

    public BigInteger getDate(String date) {
        String[] trimDate1 = date.split(" ")[0].split("-");
        String[] trimDate2 = date.split(" ")[1].split(":");

        return (new BigInteger(trimDate1[0]+trimDate1[1]+trimDate1[2]+trimDate2[0]+trimDate2[1]));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_chat_window, menu);
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
