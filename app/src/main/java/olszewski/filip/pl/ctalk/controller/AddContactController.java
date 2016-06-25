package olszewski.filip.pl.ctalk.controller;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.connector.AddContactServiceConnector;
import olszewski.filip.pl.ctalk.fragment.listItems.ContactListViewItem;
import olszewski.filip.pl.ctalk.listener.AddContactsListener;

/**
 * Created by Filip on 2016-06-21.
 */
public class AddContactController {

    private Context context;
    private AddContactServiceConnector conn = new AddContactServiceConnector();

    public void setContext(Context context) {
        this.context = context;
        conn.setContext(context);
    }

    public void setListener(AddContactsListener listener) {
        conn.setListener(listener);
    }

    public void invokeForContacts(String query) {
        try {
            ByteArrayEntity entity = new ByteArrayEntity(query.getBytes("UTF-8"));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            conn.invokeForContacts(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addContact(ContactListViewItem item) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_contacts),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String addedContacts = sharedPref.getString(context.getString(R.string.saved_contacts), "");
        addedContacts = addedContacts + item.getId() + ";" + item.getName() + ";" + item.getEmail() + "/";
        editor.putString(context.getString(R.string.saved_contacts), addedContacts);
        editor.apply();
    }
}
