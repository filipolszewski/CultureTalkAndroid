package olszewski.filip.pl.ctalk.connector;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import olszewski.filip.pl.ctalk.fragment.listItems.TagsListViewItem;
import olszewski.filip.pl.ctalk.listener.TagsListener;
import olszewski.filip.pl.ctalk.utility.WebServiceRestClient;

/**
 * Created by Filip on 2016-06-20.
 */
public class TagServiceConnector {

    private TagsListener listener;
    private Context context;


    public void setListener(TagsListener tagsListener) {
        listener = tagsListener;
    }

    public void setContect(Context context) {
        this.context = context;
    }

    public void invokeForTags() {

        WebServiceRestClient.post("/getTags", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray tags) {
                List<TagsListViewItem> list = new ArrayList<>();
                try {
                    for (int i = 0; i < tags.length(); i++) {
                        JSONObject obj = (JSONObject) tags.get(i);
                        list.add(new TagsListViewItem(obj.getInt("id"), obj.getString("name"), false));
                    }
                    listener.onListOfTags(list);
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
}
