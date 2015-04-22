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
public class RecommendationAdapter extends BaseAdapter {
    private List<Account> mData;
    private Context context;
    public RecommendationAdapter(Context context, List data){
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
        View view=View.inflate(context, R.layout.ui_recommendview, null);
        Account friend= mData.get(position);

        TextView recommendview=(TextView) view.findViewById(R.id.recommendview_textview);
        recommendview.setText(friend.getBasicAccount().getName());
        return view;
    }

}