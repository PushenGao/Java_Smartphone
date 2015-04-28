package ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.android.actionbarcompat.styled.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dblayout.ChatRecordDAO;
import model.ChatRecord;
import ws.remote.RemoteServerProxy;


public class ChatWindow extends ActionBarActivity {
    private Button sendMsgBtn;
    private Button callCameraButton;
    private EditText inputText;
    private ChatWindowAdapter mAdapter;
    private ListView chatWindowList;
    private List<ChatRecord> mData;
    private String myName= "";
    private String friendName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_chat_window);

        myName = LogIn.loginAccount.getBasicAccount().getName();

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
                            mBuilder.append(Integer.toString(c.get(Calendar.YEAR))+"year");
                            mBuilder.append(Integer.toString(c.get(Calendar.MONTH))+"month");
                            mBuilder.append(Integer.toString(c.get(Calendar.DATE))+"day");
                            mBuilder.append(Integer.toString(c.get(Calendar.HOUR_OF_DAY))+":");
                            mBuilder.append(Integer.toString(c.get(Calendar.MINUTE)));
                            //构造输入消息
                            ChatRecord Message=new ChatRecord(myName, friendName, mBuilder.toString(), inputText.getText().toString());
                            mData.add(Message);
                            //insert to local db, send to server
                            ChatRecordDAO chatRecordDAO = new ChatRecordDAO();
                            chatRecordDAO.insertRecord(Message);
                            RemoteServerProxy rsp = new RemoteServerProxy();
                            rsp.uploadChatRec(Message);
                            //构造返回消息，如果这里加入网络的功能，那么这里将变成一个网络机器人
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

    public List<ChatRecord> getData() {

        List<ChatRecord> messages = new ArrayList<ChatRecord>();

        return messages;
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
