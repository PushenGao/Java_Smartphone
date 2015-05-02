package ui;

/**
 * Created by Jackyliz on 4/28/15.
 */
import java.io.File;
import java.util.List;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.actionbarcompat.styled.R;

import model.ChatRecord;
import ws.remote.RemoteServerProxy;

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
        ImageView Image;

        Log.d("sender", mData.get(Index).getMyName());
        Log.d("receiver", mData.get(Index).getFriendName());

        if (mData.get(Index).getMyName().equals(LogIn.loginAccount.getBasicAccount().getName())) {

                mView=LayoutInflater.from(mContext).inflate(R.layout.ui_chat_window_me, null);
                Content=(TextView)mView.findViewById(R.id.From_Content);
                Content.setText(mData.get(Index).getChatContent());
        }
        else {
            RemoteServerProxy remoteServerProxy = new RemoteServerProxy();
            List<String> imagelist = remoteServerProxy.getImageFromServer(LogIn.loginAccount.getBasicAccount().getName());

            mView=LayoutInflater.from(mContext).inflate(R.layout.ui_chat_window_friend, null);
            Content=(TextView)mView.findViewById(R.id.To_Content);
            Content.setText(mData.get(Index).getChatContent());

            if (imagelist != null) {
                ContextWrapper cw = new ContextWrapper(ChatWindow.appContext);
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                File image = new File(directory, imagelist.get(0));

                Log.d("sender", mData.get(Index).getMyName());

                Log.d("receiver", mData.get(Index).getFriendName());

                remoteServerProxy.uploadOrDeleteImage(mData.get(Index).getMyName(), LogIn.loginAccount.getBasicAccount().getName(), image.getAbsolutePath(), "delete");
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);

                //mView = LayoutInflater.from(mContext).inflate(R.layout.ui_chat_window_friend, null);
                Image = (ImageView) mView.findViewById(R.id.Friend_Header);

                Image.setImageBitmap(bitmap);
            }



         }
//        }
        return mView;
    }

}
