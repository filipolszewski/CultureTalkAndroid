package olszewski.filip.pl.ctalk.listener;

import olszewski.filip.pl.ctalk.StateInfo;

/**
 * Created by Filip on 2016-06-22.
 */
public interface MessagesListener {

    void onNewStateInfo(StateInfo stateInfo);

    void onFailure(Integer statusCode);

}
