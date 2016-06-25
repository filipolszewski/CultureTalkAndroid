package olszewski.filip.pl.ctalk.utility;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

/**
 * Created by Filip on 2016-06-20.
 */
public class WebServiceRestClient {

    private static final String BASE_URL = "http://192.168.1.114:8080/culturetalk/ws";
    private static String userEmail;
    private static String userPassword;
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void setCredentials(String email, String password) {
        userEmail = email;
        userPassword = password;
        client.setBasicAuth(userEmail, userPassword);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, String url, HttpEntity entity, String contentType, AsyncHttpResponseHandler handler) {
        client.post(context, getAbsoluteUrl(url), entity, contentType, handler);
    }

    public static void post(Context context, String url, Header[] headers, RequestParams params,
                            String contentType, AsyncHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(url), headers, params, contentType, responseHandler);
    }

    public static void post(String url, JsonHttpResponseHandler jsonHttpResponseHandler) {
        client.post(getAbsoluteUrl(url), jsonHttpResponseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
