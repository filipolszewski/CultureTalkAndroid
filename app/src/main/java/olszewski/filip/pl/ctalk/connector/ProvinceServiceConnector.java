package olszewski.filip.pl.ctalk.connector;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import olszewski.filip.pl.ctalk.fragment.listItems.ProvinceListViewItem;
import olszewski.filip.pl.ctalk.listener.ProvinceListener;
import olszewski.filip.pl.ctalk.utility.WebServiceRestClient;

/**
 * Created by Filip on 2016-06-21.
 */
public class ProvinceServiceConnector {

    private Context context;
    private ProvinceListener listener;


    public void setContext(Context context) {
        this.context = context;
    }

    public void setListener(ProvinceListener listener) {
        this.listener = listener;
    }

    public void invokeForProvinces() {

        WebServiceRestClient.post("/getProvinces", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray provinces) {
                List<ProvinceListViewItem> list = new ArrayList<>();
                try {
                    for (int i = 1; i < provinces.length(); i++) {
                        JSONObject obj = (JSONObject) provinces.get(i);
                        list.add(new ProvinceListViewItem(obj.getInt("idProvince"), obj.getString("name")));
                    }
                    listener.onProvinceList(list);
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
