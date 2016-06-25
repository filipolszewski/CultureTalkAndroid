package olszewski.filip.pl.ctalk.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.utility.LoginValidator;
import olszewski.filip.pl.ctalk.utility.WebServiceRestClient;

public class RegisterActivity extends AppCompatActivity {

    EditText emailET;
    EditText nameET;
    EditText passwordET;
    EditText confirmPasswordET;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_register);

        emailET = (EditText) findViewById(R.id.emailEditText);
        nameET = (EditText) findViewById(R.id.nameEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);
        confirmPasswordET = (EditText) findViewById(R.id.confirmPasswordEditText);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String name = nameET.getText().toString();
                String password = passwordET.getText().toString();
                String confirmPassword = confirmPasswordET.getText().toString();

                Boolean emptyFields = LoginValidator.isNotNull(email) && LoginValidator.isNotNull(name)
                        && LoginValidator.isNotNull(password) && LoginValidator.isNotNull(confirmPassword);

                if (emptyFields) {
                    if (LoginValidator.validate(email) && password.equals(confirmPassword)) {
                        invokeRegistration(email, name, password);
                    } else {
                        Toast.makeText(getApplicationContext(), "Uncorrect email or different passwords.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Fill All the fields, please.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void invokeRegistration(String email, String name, String password) {
        WebServiceRestClient.post(this, "/registerUser", prepareEntity(email, name, password),
                "application/json", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(getApplicationContext(), "Rejestracja zakończona powodzeniem!", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), "Rejestracja nie powiodła się.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private HttpEntity prepareEntity(String email, String name, String password) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("name", name);
            obj.put("email", email);
            obj.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ByteArrayEntity entity = new ByteArrayEntity(obj.toString().getBytes());
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        return entity;
    }
}
