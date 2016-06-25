package olszewski.filip.pl.ctalk.controller;

import android.content.Context;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;

import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import olszewski.filip.pl.ctalk.MessageData;
import olszewski.filip.pl.ctalk.UserData;
import olszewski.filip.pl.ctalk.connector.ConversationServiceConnector;
import olszewski.filip.pl.ctalk.fragment.listItems.ConversationListViewItem;
import olszewski.filip.pl.ctalk.listener.ConversationListener;
import olszewski.filip.pl.ctalk.utility.ConversationItemComparator;

/**
 * Created by Filip on 2016-06-24.
 */
public class ConversationController {

    private ConversationServiceConnector conn = new ConversationServiceConnector();
    private Context context;

    public void setContext(Context context) {
        this.context = context;
        conn.setContext(context);
    }

    public void setListener(ConversationListener listener) {
        conn.setListener(listener);
    }

    public void sendMessage(Integer idUser, String string) {

        MessageData message = new MessageData(null, new UserData(idUser, null, null), string, null);
        Gson gson = new Gson();
        ByteArrayEntity entity = new ByteArrayEntity(gson.toJson(message).getBytes());
        conn.sendMessage(entity, message);
    }

    public List<ConversationListViewItem> sortByDate(List<ConversationListViewItem> items) {
        Collections.sort(items, new ConversationItemComparator());
        return items;
    }
}
