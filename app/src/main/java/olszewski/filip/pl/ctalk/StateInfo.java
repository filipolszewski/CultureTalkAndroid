package olszewski.filip.pl.ctalk;

import java.util.List;

/**
 * Created by Filip on 2016-06-22.
 */
public class StateInfo {

    private List<MessageData> newMessages;
    private List<UserStatus> userStatuses;

    public StateInfo() {
        // TODO Auto-generated constructor stub
    }

    public StateInfo(List<MessageData> newMessages, List<UserStatus> userStatuses) {
        super();
        this.newMessages = newMessages;
        this.userStatuses = userStatuses;
    }

    public List<MessageData> getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(List<MessageData> newMessages) {
        this.newMessages = newMessages;
    }

    public List<UserStatus> getUserStatuses() {
        return userStatuses;
    }

    public void setUserStatuses(List<UserStatus> userStatuses) {
        this.userStatuses = userStatuses;
    }

}
