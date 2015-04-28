package ui;

/**
 * Created by Jackyliz on 4/28/15.
 */
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.actionbarcompat.styled.R;

import model.ChatRecord;

public class ChatWindowAdapter extends BaseAdapter {

    private Context mContext;
    private List<ChatRecord> mData;

    public ChatWindowAdapter(Context context,List<ChatRecord> data)
    {
        this.mContext=context;
        this.mData=data;
    }

    public void Refresh()
    {
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return mData.size();
    }

    @Override
    public Object getItem(int Index)
    {
        return mData.get(Index);
    }

    @Override
    public long getItemId(int Index)
    {
        return Index;
    }

    @Override
    public View getView(int Index, View mView, ViewGroup mParent) {

        TextView Content;
        if (mData.get(Index).getMyName().equals(LogIn.loginAccount.getBasicAccount().getName())) {
            mView=LayoutInflater.from(mContext).inflate(R.layout.ui_chat_window_me, null);
            Content=(TextView)mView.findViewById(R.id.From_Content);
            Content.setText(mData.get(Index).getChatContent());
        }
        else {
            mView=LayoutInflater.from(mContext).inflate(R.layout.ui_chat_window_friend, null);
            Content=(TextView)mView.findViewById(R.id.To_Content);
            Content.setText(mData.get(Index).getChatContent());
        }
        return mView;
    }

}
