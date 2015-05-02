package ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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
import model.BasicAccount;
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

            if(inputUser.length() == 0 || inputPW.length() == 0 || inputName.length() == 0 || strAge.length() == 0
                    || inputGender.length() == 0){
                Toast.makeText(getApplicationContext(), "Please input valid user information",
                        Toast.LENGTH_LONG).show();
                return;
            }

            //try to see if the account is registered
            BasicAccount newBasicAccount = new BasicAccount(inputName, strAge, inputGender);
            Account newAccount = new Account(inputPW);
            newAccount.setBasicAccount(newBasicAccount);
            RemoteServerProxy remoteServerProxy = new RemoteServerProxy();
            String isOK = remoteServerProxy.register(newAccount);

            //if the account is registered
            if(isOK.equals("fail")){
                Toast.makeText(getApplicationContext(), "The userid has been registered",
                Toast.LENGTH_LONG).show();
                return;
            }

            //if register succeed
            Account tryAccount = remoteServerProxy.verifyAccount(inputUser,inputPW);
            LogIn.loginAccount = tryAccount;

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }catch(Exception e){
            //catch the null exception
            Log.d("register exception:", e.toString());
            RegisterInputNullExceptionHandler registerInputNullExceptionHandler = new RegisterInputNullExceptionHandler();
            registerInputNullExceptionHandler.fix(inputUser);
        }
    }
}
