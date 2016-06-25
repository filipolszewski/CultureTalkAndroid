package olszewski.filip.pl.ctalk.fragment.listItems;

/**
 * Created by Filip on 2016-06-24.
 */
public class ConversationListViewItem {

    private String userName;
    private String message;
    private String dateSent;
    private Boolean sentOrReceived;

    public ConversationListViewItem(String userName, String message, String dateSent, Boolean sentOrReceived) {
        this.userName = userName;
        this.message = message;
        this.dateSent = dateSent;
        this.sentOrReceived = sentOrReceived;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public Boolean getSentOrReceived() {
        return sentOrReceived;
    }

    public void setSentOrReceived(Boolean sentOrReceived) {
        this.sentOrReceived = sentOrReceived;
    }
}
