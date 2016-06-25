package olszewski.filip.pl.ctalk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.utility.LoginValidator;
import olszewski.filip.pl.ctalk.utility.WebServiceRestClient;

public class LogInActivity extends AppCompatActivity {

    EditText emailET;
    EditText passwordET;
    String loggedEmail;
    String loggedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_log_in);
        emailET = (EditText) findViewById(R.id.emailEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);
        emailET.setText("test@test.com");
        passwordET.setText("test");
    }

    // register button clicked
    public void startRegistration(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
//        Intent intent = new Intent(this, AndroidDatabaseManager.class);
//        startActivity(intent);
    }

    public void onLoginButtonClick(View view) {
        loggedEmail = emailET.getText().toString();
        loggedPassword = passwordET.getText().toString();
        if (LoginValidator.isNotNull(loggedEmail) && LoginValidator.isNotNull(loggedPassword)) {
            if (LoginValidator.validate(loggedEmail)) {
                invokeWebService(true);
            } else {
                Toast.makeText(getApplicationContext(), "Please enter valid email.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please fill the login form.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loggedEmail != null) {
            invokeWebService(false);
        }
    }

    private void invokeWebService(final Boolean status) {
        WebServiceRestClient.setCredentials(loggedEmail, loggedPassword);
        RequestParams params;
        if (status) {
            params = new RequestParams("true", "true");
        } else {
            params = new RequestParams("false", "false");
        }
        WebServiceRestClient.post(LogInActivity.this, "/status", null, params, "application/json", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (status) {
                    navigateToMenuActivity();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 401) {
                    Toast.makeText(getApplicationContext(), "User not found / Wrong Password", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), "Login Error: " + statusCode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}