package olszewski.filip.pl.ctalk.connector;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import olszewski.filip.pl.ctalk.fragment.listItems.EventListViewItem;
import olszewski.filip.pl.ctalk.listener.EventsListener;
import olszewski.filip.pl.ctalk.utility.WebServiceRestClient;

/**
 * Created by Filip on 2016-06-20.
 */
public class EventServiceConnector {

    private EventsListener listener;
    private Context context;

    public void invokeForEventList(HttpEntity entity) {

        WebServiceRestClient.post(context, "/findEvents", entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray events) {
                List<EventListViewItem> eventList = new ArrayList<>();
                try {
                    for (int i = 0; i < events.length(); i++) {
                        JSONObject obj = (JSONObject) events.get(i);

                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String formatedDate = df.format(new Date(Long.parseLong(obj.getString("date"))));

                        EventListViewItem eventItem = new EventListViewItem(obj.getInt("id"), obj.getString("name"),
                                obj.getString("address"), formatedDate, obj.getString("institution"));
                        eventList.add(eventItem);
                    }
                    listener.onListOfEvents(eventList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject object) {
                listener.onFailure(statusCode);
            }
        });
    }

    public void setListener(EventsListener listener) {
        this.listener = listener;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
