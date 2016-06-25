package olszewski.filip.pl.ctalk.connector;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import olszewski.filip.pl.ctalk.fragment.listItems.ContactListViewItem;
import olszewski.filip.pl.ctalk.listener.AddContactsListener;
import olszewski.filip.pl.ctalk.utility.SharedPreferencesUtil;
import olszewski.filip.pl.ctalk.utility.WebServiceRestClient;

/**
 * Created by Filip on 2016-06-21.
 */
public class AddContactServiceConnector {

    private Context context;
    private AddContactsListener listener;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setListener(AddContactsListener listener) {
        this.listener = listener;
    }

    public void invokeForContacts(HttpEntity entity) {

        WebServiceRestClient.post(context, "/findUsers", entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                try {
                    List<ContactListViewItem> items = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        items.add(new ContactListViewItem(obj.getInt("id"), obj.getString("name"), obj.getString("email")));
                    }
                    listener.onContactList(verifyWithAlreadyAddedContacts(items));
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

    private List<ContactListViewItem> verifyWithAlreadyAddedContacts(List<ContactListViewItem> items) {

        List<ContactListViewItem> verifiedItems = new ArrayList<>();
        List<ContactListViewItem> addedContacts = SharedPreferencesUtil.getAddedContacts(context);
        for (ContactListViewItem item : items) {
            if (!addedContacts.contains(item)) {
                verifiedItems.add(item);
            }
        }
        return verifiedItems;
    }
}
