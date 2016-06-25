package olszewski.filip.pl.ctalk;

import olszewski.filip.pl.ctalk.sqlite.Message;

/**
 * Created by Filip on 2016-06-22.
 */
public class MessageData {

    private Integer id;
    private UserData toUser;
    private String message;
    private String dateSent;

    public MessageData(Integer id, UserData userFrom, String message, String dateSent) {
        this.id = id;
        this.toUser = userFrom;
        this.message = message;
        this.dateSent = dateSent;
    }

    public MessageData(Message message) {
        this.id = message.getId();
        this.toUser = new UserData(Integer.parseInt(message.getContactID()), message.getContactName(), null);
        this.message = message.getMessageString();
        this.dateSent = message.getDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserData getToUser() {
        return toUser;
    }

    public void setToUser(UserData fromUser) {
        this.toUser = fromUser;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String date) {
        this.dateSent = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
