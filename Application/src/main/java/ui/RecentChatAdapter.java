package ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.actionbarcompat.styled.R;

import java.util.List;

import model.Account;

/**
 * Created by JiateLi on 15/4/17.
 */
public class RecentChatAdapter extends BaseAdapter {
    private List<Account> mData;
    private Context context;
    public RecentChatAdapter(Context context, List data){
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
        View view=View.inflate(context, R.layout.ui_chat_history_view, null);
        Account friend= mData.get(position);

        TextView chatview=(TextView) view.findViewById(R.id.chatview_textview);
        chatview.setText(friend.getName());

        return view;
    }

}