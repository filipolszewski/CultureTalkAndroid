package olszewski.filip.pl.ctalk.controller;

import android.content.Context;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import olszewski.filip.pl.ctalk.connector.SingleEventServiceConnector;
import olszewski.filip.pl.ctalk.listener.SingleEventListener;

/**
 * Created by Filip on 2016-06-20.
 */
public class SingleEventController {

    private SingleEventServiceConnector conn = new SingleEventServiceConnector();

    public void setListener(SingleEventListener listener) {
        conn.setListener(listener);
    }

    public void invokeForEvent(Integer idEvent) {
        try {
            ByteArrayEntity entity = new ByteArrayEntity(("" + idEvent).getBytes("UTF-8"));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            conn.invokeForEvent(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setContext(Context context) {
        conn.setContext(context);
    }
}
