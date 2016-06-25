package olszewski.filip.pl.ctalk.controller;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import olszewski.filip.pl.ctalk.connector.EventServiceConnector;
import olszewski.filip.pl.ctalk.listener.EventsListener;
import olszewski.filip.pl.ctalk.utility.SharedPreferencesUtil;

/**
 * Created by Filip on 2016-06-20.
 */
public class EventController {

    private static EventController controller;
    private EventServiceConnector connector = new EventServiceConnector();
    private Context context;

    public static EventController getInstance() {
        if (controller == null) {
            controller = new EventController();
        }
        return controller;
    }

    public void setListener(EventsListener listener) {
        connector.setListener(listener);
    }

    public void invokeForEventList() {
        try {
            ByteArrayEntity entity = new ByteArrayEntity(getEventCriteria().getBytes("UTF-8"));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            connector.invokeForEventList(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private String getEventCriteria() {
        JSONObject criteria = new JSONObject();
        List<String> tags = SharedPreferencesUtil.getCheckedTagsIDs(context);
        if (tags.size() == 0) {
            tags.add("" + 1);
        }
        JSONArray array = new JSONArray(tags);
        String prefProv = SharedPreferencesUtil.getPrefferedProvince(context);
        try {
            criteria.put("tagList", array);
            criteria.put("idProvince", prefProv.substring(0, prefProv.indexOf(".")));
            criteria.put("maxResults", "" + 20);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return criteria.toString();
    }

    public void setContext(Context context) {
        this.context = context;
        connector.setContext(context);
    }
}
