package olszewski.filip.pl.ctalk.listener;

import olszewski.filip.pl.ctalk.EventInfo;

/**
 * Created by Filip on 2016-06-20.
 */
public interface SingleEventListener {
    void onSingleEvent(EventInfo event);

    void onFailure(int statusCode);
}
