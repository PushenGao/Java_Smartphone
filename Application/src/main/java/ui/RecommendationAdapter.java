package ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.actionbarcompat.styled.R;

import java.util.List;

import model.Account;
import model.BasicAccount;

/**
 * Created by JiateLi on 15/4/17.
 */
public class RecommendationAdapter extends BaseAdapter {
    private List<BasicAccount> mData;
    private Context context;
    private Button btn;
    public RecommendationAdapter(Context context, List data){
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
    public View getView(int position, View convertView, ViewGroup parent){
        //set up the recommend list view
        View view = View.inflate(context, R.layout.ui_recommendview, null);
        final BasicAccount friend = mData.get(position);

        TextView recommendview = (TextView) view.findViewById(R.id.recommendview_textview);
        recommendview.setText(friend.getName());

        //when button clicked, turn to the profile page then can add friend
        btn = (Button) view.findViewById(R.id.recommend_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Profile.class);
                intent.putExtra("name", friend.getName())
                        .putExtra("age", friend.getAge())
                        .putExtra("gender", friend.getGender());

                context.startActivity(intent);
            }
        });
        return view;
    }

}