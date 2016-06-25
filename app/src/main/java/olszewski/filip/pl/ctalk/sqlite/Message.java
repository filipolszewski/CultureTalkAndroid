package olszewski.filip.pl.ctalk.sqlite;

/**
 * Created by Filip on 2016-06-23.
 */
public class Message {

    private Integer id;
    private String messageString;
    private String date;
    private String contactID;
    private String contactName;
    private boolean sentOrReceived;

    public Message(Integer id, String messageString, String date, String contactID, String contactName, boolean sentOrReceived) {
        this.id = id;
        this.messageString = messageString;
        this.date = date;
        this.contactID = contactID;
        this.contactName = contactName;
        this.sentOrReceived = sentOrReceived;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public boolean isSentOrReceived() {
        return sentOrReceived;
    }

    public void setSentOrReceived(boolean sentOrReceived) {
        this.sentOrReceived = sentOrReceived;
    }
}
