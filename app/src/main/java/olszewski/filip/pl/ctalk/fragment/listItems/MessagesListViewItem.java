package olszewski.filip.pl.ctalk.fragment.listItems;

/**
 * Created by Filip on 2016-05-04.
 */
public class MessagesListViewItem {

    private Integer userID;
    private Boolean active;
    private String userName;
    private String date;
    private String lastMessage;

    public MessagesListViewItem(Integer userID, Boolean active, String userName, String date, String lastMessage) {
        this.userID = userID;
        this.active = active;
        this.userName = userName;
        this.date = date;
        this.lastMessage = lastMessage;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
