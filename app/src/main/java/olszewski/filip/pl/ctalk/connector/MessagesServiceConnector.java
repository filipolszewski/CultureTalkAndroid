package olszewski.filip.pl.ctalk.connector;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import olszewski.filip.pl.ctalk.StateInfo;
import olszewski.filip.pl.ctalk.listener.MessagesListener;
import olszewski.filip.pl.ctalk.utility.WebServiceRestClient;

/**
 * Created by Filip on 2016-06-22.
 */
public class MessagesServiceConnector {

    private Context context;
    private MessagesListener listener;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setListener(MessagesListener listener) {
        this.listener = listener;
    }

    public void invokeForMessages(HttpEntity entity) {
        WebServiceRestClient.post(context, "/newState", entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new Gson();
                StateInfo stateInfo = gson.fromJson(response.toString(), StateInfo.class);
                listener.onNewStateInfo(stateInfo);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject object) {
                listener.onFailure(statusCode);
            }
        });
    }
}
