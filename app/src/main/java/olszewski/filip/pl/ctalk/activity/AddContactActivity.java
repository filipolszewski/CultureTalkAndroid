package olszewski.filip.pl.ctalk.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.fragment.AddContactFragment;

/**
 * Created by Filip on 2016-06-21.
 */
public class AddContactActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchBtn;
    AddContactFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        FragmentManager manager = getSupportFragmentManager();
        fragment = (AddContactFragment) manager.findFragmentById(R.id.fragment_add_contact);

        searchEditText = (EditText) findViewById(R.id.searchContactsToAddET);
        searchBtn = (Button) findViewById(R.id.searchContactsToAddBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString();
                if (query.length() > 2) {
                    fragment.refreshList(query);
                } else {
                    Toast.makeText(getApplicationContext(), "Podaj conajmniej 3 znaki..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
