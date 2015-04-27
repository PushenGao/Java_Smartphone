package ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.actionbarcompat.styled.R;

import java.util.List;

import model.Account;
import model.BasicAccount;
import model.FriendReq;
import ws.remote.RemoteServerProxy;

/**
 * Created by JiateLi on 15/4/17.
 */
public class PendingRequestAdapter extends BaseAdapter {
    private List<BasicAccount> mData;
    private Context context;
    private Button agreeBtn;
    private Button rejectBtn;

    public PendingRequestAdapter(Context context, List data){
        this.mData=data;
        this.context=context;
    }
    @Override
    public int getCount()
    {

        return mData.size();
    }
    @Override
    public Object getItem(int position)
    {

        return mData.get(position);
    }
    @Override
    public long getItemId(int position)
    {

        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view=View.inflate(context, R.layout.ui_pengdingfriendsview, null);
        final BasicAccount friend= mData.get(position);
        TextView contactview=(TextView) view.findViewById(R.id.pengdingview_textview);
        contactview.setText(friend.getName());

        agreeBtn = (Button) view.findViewById(R.id.pending_btn);
        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoteServerProxy rsp = new RemoteServerProxy();
                FriendReq friendReq = new FriendReq();
                friendReq.setSender(LogIn.loginAccount.getBasicAccount().getName());
                friendReq.setReceiver(friend.getName());
                friendReq.setAction("add");
                rsp.reqFriend(friendReq);
                //Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
            }
        });

        rejectBtn = (Button) view.findViewById(R.id.reject_btn);
        rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoteServerProxy rsp = new RemoteServerProxy();
                FriendReq friendReq = new FriendReq();
                friendReq.setSender(LogIn.loginAccount.getBasicAccount().getName());
                friendReq.setReceiver(friend.getName());
                friendReq.setAction("reject");
                rsp.reqFriend(friendReq);
               // Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}