package olszewski.filip.pl.ctalk.controller;

import android.content.Context;

import olszewski.filip.pl.ctalk.connector.ProvinceServiceConnector;
import olszewski.filip.pl.ctalk.listener.ProvinceListener;

/**
 * Created by Filip on 2016-06-21.
 */
public class ProvinceController {

    private ProvinceServiceConnector conn = new ProvinceServiceConnector();
    private Context context;

    public void setContext(Context context) {
        this.context = context;
        conn.setContext(context);
    }

    public void setListener(ProvinceListener listener) {
        conn.setListener(listener);
    }

    public void invokeForProvinces() {
        conn.invokeForProvinces();
    }
}
