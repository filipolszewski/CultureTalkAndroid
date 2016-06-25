package olszewski.filip.pl.ctalk.controller;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import olszewski.filip.pl.ctalk.connector.MessagesServiceConnector;
import olszewski.filip.pl.ctalk.listener.MessagesListener;
import olszewski.filip.pl.ctalk.utility.SharedPreferencesUtil;

/**
 * Created by Filip on 2016-06-22.
 */
public class MessagesController {

    private MessagesServiceConnector connector = new MessagesServiceConnector();
    private Context context;

    public void setContext(Context context) {
        this.context = context;
        connector.setContext(context);
    }

    public void setListener(MessagesListener listener) {
        connector.setListener(listener);
    }

    public void invokeForMessages() {
        connector.invokeForMessages(prepareEntity());
    }

    private ByteArrayEntity prepareEntity() {
        List<Integer> contactIDs = SharedPreferencesUtil.getAddedContactsIDs(context);
        Integer lastMessageID = SharedPreferencesUtil.getLastMessageID(context);
        JSONArray contacts = new JSONArray();
        ByteArrayEntity entity = null;
        try {
            for (int i = 0; i < contactIDs.size(); i++) {
                contacts.put(i, contactIDs.get(i));
            }
            JSONObject obj = new JSONObject();
            obj.put("lastMessageId", lastMessageID);
            obj.put("myUsersIds", contacts);
            entity = new ByteArrayEntity(obj.toString().getBytes());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
