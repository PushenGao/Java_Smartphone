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
public class FriendAdapter extends BaseAdapter {
    //来获取position数据
    private List<Account> mData;
    //没有上下文所以创建一个context
    private Context context;
    //	创建一个构造方法来接收 mData和context
    public FriendAdapter(Context context, List data){
        this.mData=data;
        this.context=context;
    }
    //决定列表item显示的个数
    @Override
    public int getCount()
    {

        return mData.size();
    }
    //根据position获取对应item数据
    @Override
    public Object getItem(int position)
    {

        return mData.get(position);
    }
    //获取对应position的item的id
    @Override
    public long getItemId(int position)
    {

        return position;
    }
    //创建列表item的视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
//创建的view返回值，获取listView的布局
        View view=View.inflate(context, R.layout.ui_contactview, null);
//		获取item相应的数据对象
        Account friend= mData.get(position);
//		初始化view
        //ImageView iv_photo=(ImageView) view.findViewById(R.id.iv_photo);
        TextView tv_01=(TextView) view.findViewById(R.id.contactview_textview);
       // TextView tv_02=(TextView) view.findViewById(R.id.tv_02);
//	数据绑定到view
        //iv_photo.setImageResource(friend.getIv_photo());
        tv_01.setText(friend.getName());
        //tv_02.setText(friend.getTv_02());
//返回绑定完数据的view
        return view;
    }

}