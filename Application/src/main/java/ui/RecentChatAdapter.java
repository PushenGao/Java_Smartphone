package ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.actionbarcompat.styled.R;

import java.util.List;

import model.Account;
import model.BasicAccount;
import model.ChatRecord;

/**
 * Created by JiateLi on 15/4/17.
 */
public class RecentChatAdapter extends BaseAdapter {
    private List<Account> mData;
    private Context context;
    private RelativeLayout layout;

    public RecentChatAdapter(Context context, List data){
        this.mData = data;
        this.context = context;
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
        View view = View.inflate(context, R.layout.ui_chat_history_view, null);
        final Account friend = mData.get(position);
        final BasicAccount basicAccount = friend.getBasicAccount();

        TextView chatview = (TextView) view.findViewById(R.id.chatview_textview1);
        chatview.setText(basicAccount.getName());

        layout = (RelativeLayout) view.findViewById(R.id.chat_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatWindow.class);
                  intent.putExtra("myname", LogIn.loginAccount.getBasicAccount().getName())
                          .putExtra("name", basicAccount.getName());

                context.startActivity(intent);
            }
        });

        return view;
    }

}