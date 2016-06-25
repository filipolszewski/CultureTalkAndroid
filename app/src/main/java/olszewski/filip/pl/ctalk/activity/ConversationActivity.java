package olszewski.filip.pl.ctalk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.fragment.ConversationFragment;

/**
 * Created by Filip on 2016-06-24.
 */
public class ConversationActivity extends AppCompatActivity {

    ConversationFragment fragment;
    TextView userNameTv;
    EditText messageValue;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Intent myIntent = getIntent();
        final Integer idUser = myIntent.getIntExtra("userToID", 1);
        String userName = myIntent.getStringExtra("userName");

        FragmentManager manager = getSupportFragmentManager();
        fragment = (ConversationFragment) manager.findFragmentById(R.id.fragment_conversation);
        messageValue = (EditText) findViewById(R.id.messageValueET);
        userNameTv = (TextView) findViewById(R.id.conversationUsername);
        userNameTv.setText(userName);

        sendBtn = (Button) findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageValue.getText().toString();
                if (message.length() > 0) {
                    fragment.sendMessage(idUser, message);
                }
            }
        });
    }
}
