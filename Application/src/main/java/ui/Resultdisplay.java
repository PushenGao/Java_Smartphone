package ui;
;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.actionbarcompat.styled.MainActivity;
import com.example.android.actionbarcompat.styled.R;

public class Resultdisplay extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultdisplay);
        TextView text = (TextView) findViewById(R.id.rstTexxt);
        StringBuilder sb = new StringBuilder();
        sb.append("Running time: 39 min\n");
        sb.append("Running distance: 2.5 mile\n");
        sb.append("Energy consumption: 400 cal");
        text.setText(sb.toString());
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

    public void returntomain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}