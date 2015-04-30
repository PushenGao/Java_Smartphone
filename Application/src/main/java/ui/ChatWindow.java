package ui;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        //照片的命名，目标文件夹下，以当前时间数字串为名称，即可确保每张照片名称不相同。
        String str=null;
        Date date=null;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//获取当前时间，进一步转化为字符串
        date =new Date();
        str=format.format(date);
        String fileName = str+".png";
        //File myInternalFile = new File(filepath,fileName);

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,fileName);

        try {
            b = new FileOutputStream(mypath);
            cameraBitmap.compress(Bitmap.CompressFormat.PNG, 100, b);// 把数据写入文件
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



        //TODO get image from server
//        List<String> imagelist = remoteServerProxy.getImageFromServer(myName);
//        ContextWrapper cw = new ContextWrapper(ChatWindow.appContext);
//        // path to /data/data/yourapp/app_data/imageDir
//        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
//        File image = new File(directory, imagelist.get(0));
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
        //TODO not sure if the filepath show be empty
//        remoteServerProxy.uploadOrDeleteImage(myName,friendName,"","delete");
        //img.setImageBitmap(bitmap);
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
