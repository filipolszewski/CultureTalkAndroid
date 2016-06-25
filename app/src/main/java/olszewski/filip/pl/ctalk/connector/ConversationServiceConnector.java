package olszewski.filip.pl.ctalk.connector;

import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import olszewski.filip.pl.ctalk.MessageData;
import olszewski.filip.pl.ctalk.listener.ConversationListener;
import olszewski.filip.pl.ctalk.utility.WebServiceRestClient;

/**
 * Created by Filip on 2016-06-24.
 */
public class ConversationServiceConnector {

    private Context context;
    private ConversationListener listener;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setListener(ConversationListener listener) {
        this.listener = listener;
    }

    public void sendMessage(ByteArrayEntity entity, final MessageData message) {
        WebServiceRestClient.post(context, "/message", entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                listener.onMessageSent(message);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                listener.onFailure(statusCode);
            }
        });
    }
}
