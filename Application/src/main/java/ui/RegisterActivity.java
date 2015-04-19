package ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.actionbarcompat.styled.R;

import exception.RegisterInputNullExceptionHandler;
import model.Account;
import ws.remote.RemoteServerProxy;


public class RegisterActivity extends ActionBarActivity {
    private EditText userText;
    private EditText passwordText;
    private EditText nameText;
    private EditText ageText;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button registerBtn;
    private String inputUser;
    private String inputPW;
    private String inputName;
    private int inputAge;
    private String inputGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_registeractivity);
        userText = (EditText) findViewById(R.id.registerUserid);
        passwordText = (EditText) findViewById(R.id.registerPassword);
        nameText = (EditText) findViewById(R.id.registerName);
        radioSexGroup = (RadioGroup) findViewById(R.id.registerGenderGroup);
        ageText = (EditText) findViewById(R.id.registerAge);
        registerBtn = (Button) findViewById(R.id.btn_register);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
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

    public void onSubmitClicked(View view){
        //get the register information
        try {
            inputUser = userText.getText().toString();
            inputPW = passwordText.getText().toString();
            nameText = (EditText) findViewById(R.id.registerName);
            radioSexGroup = (RadioGroup) findViewById(R.id.registerGenderGroup);
            ageText = (EditText) findViewById(R.id.registerAge);
            registerBtn = (Button) findViewById(R.id.btn_register);
            inputName = nameText.getText().toString();
            inputAge = Integer.parseInt(ageText.getText().toString());
            int selectedId = radioSexGroup.getCheckedRadioButtonId();
            radioSexButton = (RadioButton) findViewById(selectedId);
            inputGender = radioSexButton.getText().toString();

            String strAge = "" + inputAge;
            Account newAccount = new Account(inputName, strAge, inputGender, inputUser, inputPW);

            RemoteServerProxy remoteServerProxy = new RemoteServerProxy();
            remoteServerProxy.register(newAccount);
            //TODO if it has been registered
            //Toast.makeText(getApplicationContext(), "The userid has been registered",
            //Toast.LENGTH_LONG).show();
            LogIn.loginAccount = newAccount;

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }catch(Exception e){
            //TODO do we need an exception class
            RegisterInputNullExceptionHandler registerInputNullExceptionHandler = new RegisterInputNullExceptionHandler();
            registerInputNullExceptionHandler.fix();
        }
    }
}
