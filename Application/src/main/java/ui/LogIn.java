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
import android.widget.Toast;

import com.example.android.actionbarcompat.styled.R;

import exception.LoginInputNullExceptionHandler;
import model.Account;
import ws.remote.RemoteServerProxy;
import ws.remote.VerifyLoginAccount;


public class LogIn extends ActionBarActivity {
    private EditText userText;
    private EditText passwordText;
    private Button loginBtn;
    public static Account loginAccount;
    public String inputUser;
    public String inputPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_login);
        userText = (EditText) findViewById(R.id.login_user_edit);
        passwordText = (EditText) findViewById(R.id.login_passwd_edit);
        loginBtn = (Button) findViewById(R.id.login_btn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
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

    //when login button is cliked, would cause this method
    public void login_werun(View view){
        //try to get the input string of the user
        try {
            inputUser = userText.getText().toString();
            inputPW = passwordText.getText().toString();

        }catch(Exception e){
            LoginInputNullExceptionHandler loginInputNullExceptionHandler = new LoginInputNullExceptionHandler();
            inputUser = loginInputNullExceptionHandler.fix(inputUser);
            return;
        }


        if(inputUser.length() == 0 || inputPW.length() == 0){
            Toast.makeText(getApplicationContext(), "Please input valid user information",
                    Toast.LENGTH_LONG).show();
            return;
        }
//        //verify the log in userid and password
        RemoteServerProxy remoteServerProxy = new RemoteServerProxy();
        Account tryAccount = remoteServerProxy.verifyAccount(inputUser,inputPW);
//        //if the server has the account information, directlt login the application
        if(tryAccount.getPassword() != null){
            loginAccount = tryAccount;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            //if the login information is wrong
            Toast.makeText(getApplicationContext(), "The Account verification fails!",
                    Toast.LENGTH_LONG).show();
        }
    }

    //when the register button is clicked
    public void register_werun(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
