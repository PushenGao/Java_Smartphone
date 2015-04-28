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
import model.ChatRecord;

/**
 * Created by JiateLi on 15/4/17.
 */
public class RecentChatAdapter extends BaseAdapter {
    private List<ChatRecord> mData;
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
        final ChatRecord friend = mData.get(position);

        TextView chatview = (TextView) view.findViewById(R.id.chatview_textview1);
        chatview.setText(friend.getFriendName());

        TextView chatview2 = (TextView) view.findViewById(R.id.chatview_textview2);
        chatview2.setText(friend.getChatContent());

        TextView chatview3 = (TextView) view.findViewById(R.id.chatview_textview3);
        chatview3.setText(friend.getTimeStamp());

        layout = (RelativeLayout) view.findViewById(R.id.chat_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatWindow.class);
                intent.putExtra("myname", friend.getMyName())
                        .putExtra("friendname", friend.getFriendName())
                        .putExtra("time", friend.getTimeStamp())
                        .putExtra("content", friend.getChatContent());

                context.startActivity(intent);
            }
        });

        return view;
    }

}