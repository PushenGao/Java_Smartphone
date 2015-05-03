package ui;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.actionbarcompat.styled.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
    public static Context appContext;
    private Bitmap cameraBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_chat_window);

        Intent intent = getIntent();
        myName = LogIn.loginAccount.getBasicAccount().getName();
        friendName = getIntent().getStringExtra("name");
        System.out.println(myName + " " + friendName);

        appContext = getApplicationContext();

        chatWindowList = (ListView) findViewById(R.id.chat_window_listview);
        mData = getData();
        mAdapter = new ChatWindowAdapter(this, mData);

        chatWindowList.setAdapter(mAdapter);
        chatWindowList.smoothScrollToPositionFromTop(mData.size(), 0);

        // load chatting history
        ChatRecordDAO chatRecordDAO = new ChatRecordDAO(getApplicationContext());
        List<ChatRecord> db_list1 = new ArrayList<ChatRecord>();
        List<ChatRecord> db_list2 = new ArrayList<ChatRecord>();
        List<ChatRecord> db_list = new ArrayList<ChatRecord>();
        db_list1 = chatRecordDAO.getAllRecord(friendName, myName);
        db_list2 = chatRecordDAO.getAllRecord(myName, friendName);
        db_list.addAll(db_list1);
        db_list.addAll(db_list2);

        Collections.sort(db_list, new Comparator<ChatRecord>() {
            public int compare(ChatRecord o1, ChatRecord o2){
                if(getDate(o1.getTimeStamp()) == getDate(o2.getTimeStamp()))
                    return 0;
                return (getDate(o1.getTimeStamp()).compareTo(getDate(o2.getTimeStamp())) == 1  ? 1 : -1);
            }
        });



        chatWindowList = (ListView) findViewById(R.id.chat_window_listview);

        mAdapter = new ChatWindowAdapter(getApplicationContext(), db_list);

        chatWindowList.setAdapter(mAdapter);

        inputText = (EditText) findViewById(R.id.input_text);

        callCameraButton = (Button) findViewById(R.id.button_callcamera);
        callCameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, Activity.DEFAULT_KEYS_DIALER);
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

                            String str=null;
                            Date date=null;
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取当前时间，进一步转化为字符串
                            date =new Date();
                            str=format.format(date);
                            // input value
                            ChatRecord message=new ChatRecord(myName, friendName, str, inputText.getText().toString());

                            mData.add(message);
                            //insert to local db, send to server
                            ChatRecordDAO chatRecordDAO = new ChatRecordDAO(getApplicationContext());
                            chatRecordDAO.insertRecord(message);
                            RemoteServerProxy rsp = new RemoteServerProxy();
                            rsp.uploadChatRec(message);

                            // reload list view
                            List<ChatRecord> db_list1 = new ArrayList<ChatRecord>();
                            List<ChatRecord> db_list2 = new ArrayList<ChatRecord>();
                            List<ChatRecord> db_list = new ArrayList<ChatRecord>();
                            db_list1 = chatRecordDAO.getAllRecord(friendName, myName);
                            db_list2 = chatRecordDAO.getAllRecord(myName, friendName);
                            db_list.addAll(db_list1);
                            db_list.addAll(db_list2);

                            Collections.sort(db_list, new Comparator<ChatRecord>() {
                                public int compare(ChatRecord o1, ChatRecord o2){
                                    if(getDate(o1.getTimeStamp()) == getDate(o2.getTimeStamp()))
                                        return 0;
                                    return (getDate(o1.getTimeStamp()).compareTo(getDate(o2.getTimeStamp())) == 1  ? 1 : -1);
                                }
                            });

                            chatWindowList = (ListView) findViewById(R.id.chat_window_listview);

                            mAdapter = new ChatWindowAdapter(getApplicationContext(), db_list);

                            chatWindowList.setAdapter(mAdapter);
                        }
                        //empty input box
                        inputText.setText("");
                        //close input box
                        imm.hideSoftInputFromWindow(null, InputMethodManager.HIDE_IMPLICIT_ONLY);
                        //scroll to current message
                        chatWindowList.smoothScrollToPositionFromTop(mData.size(), 0);
                    }
                }
        );
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data!= null) {
            cameraBitmap = (Bitmap) data.getExtras().get("data");
            //img.setImageBitmap(cameraBitmap);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            cameraBitmap.compress(Bitmap.CompressFormat.PNG,40,baos);
            byte[] byteImage_photo = baos.toByteArray();
        }

        FileOutputStream b = null;
        //name pictures as date.png
        String str=null;
        Date date=null;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        date =new Date();
        str=format.format(date);
        String fileName = str+".png";

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,fileName);

        try {
            b = new FileOutputStream(mypath);
            cameraBitmap.compress(Bitmap.CompressFormat.PNG, 100, b);//write data in file
            String absolutePath = mypath.getAbsolutePath();
            RemoteServerProxy remoteServerProxy = new RemoteServerProxy();
            remoteServerProxy.uploadOrDeleteImage(myName,friendName,absolutePath,"add");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            mHandler.postDelayed(mUpdateClockTask, 5000);
        }
    };

    public void updateClock(){

        ChatRecordDAO chatRecordDAO = new ChatRecordDAO(this);
        RemoteServerProxy remoteServerProxy = new RemoteServerProxy();

        List<ChatRecord> list = remoteServerProxy.getChatRecord(LogIn.loginAccount.getBasicAccount().getName());
        List<ChatRecord> db_list1 = new ArrayList<ChatRecord>();
        List<ChatRecord> db_list2 = new ArrayList<ChatRecord>();
        List<ChatRecord> db_list = new ArrayList<ChatRecord>();

        for (ChatRecord chatRecord : list) {
            chatRecordDAO.insertRecord(chatRecord);

        }
        db_list1 = chatRecordDAO.getAllRecord(friendName, myName);
        db_list2 = chatRecordDAO.getAllRecord(myName, friendName);
        db_list.addAll(db_list1);
        db_list.addAll(db_list2);

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

    public List<ChatRecord> getData() {

        List<ChatRecord> messages = new ArrayList<ChatRecord>();

        return messages;
    }

    public BigInteger getDate(String date) {
        String[] trimDate1 = date.split(" ")[0].split("-");
        String[] trimDate2 = date.split(" ")[1].split(":");

        return (new BigInteger(trimDate1[0]+trimDate1[1]+trimDate1[2]+trimDate2[0]+trimDate2[1]+trimDate2[2]));
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
