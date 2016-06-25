package olszewski.filip.pl.ctalk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import olszewski.filip.pl.ctalk.EventInfo;
import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.controller.SingleEventController;
import olszewski.filip.pl.ctalk.listener.SingleEventListener;

/**
 * Created by Filip on 2016-06-20.
 */
public class SingleEventActivity extends AppCompatActivity {

    private SingleEventController controller;
    private TextView eventNameTv;
    private TextView eventInstTv;
    private TextView eventAddressTv;
    private TextView eventDateTv;
    private TextView eventDescriptionTv;
    private TextView eventTicketLinkTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event);

        eventNameTv = (TextView) findViewById(R.id.eventNameTv);
        eventInstTv = (TextView) findViewById(R.id.eventInstTv);
        eventAddressTv = (TextView) findViewById(R.id.eventAddressTv);
        eventDateTv = (TextView) findViewById(R.id.eventDateTv);
        eventDescriptionTv = (TextView) findViewById(R.id.eventDescriptionTv);
        eventTicketLinkTv = (TextView) findViewById(R.id.eventTicketLinkTv);

        Bundle extras = getIntent().getExtras();
        Integer idEvent = (Integer) extras.get("id");

        controller = new SingleEventController();
        controller.setContext(getApplicationContext());
        controller.setListener(new SingleEventListener() {
            @Override
            public void onSingleEvent(EventInfo event) {
                populateTextViews(event);
            }

            @Override
            public void onFailure(int statusCode) {
                Toast.makeText(getApplicationContext(), "Error occured while loading this event. " + statusCode, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
        controller.invokeForEvent(idEvent);
    }

    private void populateTextViews(EventInfo event) {
        eventNameTv.setText(event.getName());
        eventAddressTv.setText(event.getAddress());
        eventDateTv.setText(event.getDate());
        eventInstTv.setText(event.getInstitution());
        eventDescriptionTv.setText(event.getDescription());
        eventTicketLinkTv.setText(event.getTicketLink());
    }
}
