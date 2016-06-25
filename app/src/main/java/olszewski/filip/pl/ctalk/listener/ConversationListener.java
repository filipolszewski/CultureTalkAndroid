package olszewski.filip.pl.ctalk.listener;

import olszewski.filip.pl.ctalk.MessageData;

/**
 * Created by Filip on 2016-06-24.
 */
public interface ConversationListener {

    void onMessageSent(MessageData message);

    void onFailure(Integer statusCode);
}
