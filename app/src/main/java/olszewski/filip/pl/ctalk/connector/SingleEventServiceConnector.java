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
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import olszewski.filip.pl.ctalk.EventInfo;
import olszewski.filip.pl.ctalk.listener.SingleEventListener;
import olszewski.filip.pl.ctalk.utility.WebServiceRestClient;

/**
 * Created by Filip on 2016-06-20.
 */
public class SingleEventServiceConnector {

    private SingleEventListener listener;
    private Context context;

    public void setListener(SingleEventListener listener) {
        this.listener = listener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void invokeForEvent(ByteArrayEntity entity) {
        WebServiceRestClient.post(context, "/findOneEvent", entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject o) {
                try {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String formatedDate = df.format(new Date(Long.parseLong(o.getString("date"))));

                    JSONArray array = o.getJSONArray("tags");
                    List<String> tags = new ArrayList<String>();

                    for (int i = 0; i < array.length(); i++) {
                        tags.add((String) array.get(i));
                    }
                    EventInfo eventInfo = new EventInfo(o.getInt("id"), o.getString("name"), formatedDate,
                            o.getString("institution"), o.getString("province"), o.getString("address"),
                            o.getString("ticketLink"), o.getString("description"), tags);

                    listener.onSingleEvent(eventInfo);
                } catch (JSONException e) {
                    listener.onFailure(37);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject object) {
                listener.onFailure(statusCode);
            }
        });
    }
}
