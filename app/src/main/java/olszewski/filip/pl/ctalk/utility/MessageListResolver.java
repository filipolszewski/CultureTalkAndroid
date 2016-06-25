package olszewski.filip.pl.ctalk.utility;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import olszewski.filip.pl.ctalk.UserStatus;
import olszewski.filip.pl.ctalk.fragment.listItems.ContactListViewItem;
import olszewski.filip.pl.ctalk.fragment.listItems.MessagesListViewItem;
import olszewski.filip.pl.ctalk.sqlite.DatabaseHelper;
import olszewski.filip.pl.ctalk.sqlite.Message;

/**
 * Created by Filip on 2016-06-22.
 */
public class MessageListResolver {

    public static List<MessagesListViewItem> getMessageList(List<UserStatus> userStatuses, Context context) {
        List<ContactListViewItem> addedContects = SharedPreferencesUtil.getAddedContacts(context);
        List<MessagesListViewItem> messageItemsList = new ArrayList<>();
        for (ContactListViewItem item : addedContects) {
            Integer id = item.getId();
            Boolean status = findStatus(id, userStatuses);
            Message lastMessage = findLastMessage(id, context);
            String userName = item.getName();
            if (lastMessage != null) {
                if (!lastMessage.isSentOrReceived()) {
                    lastMessage.setMessageString("Ty: " + lastMessage.getMessageString());
                }
                if (lastMessage.getMessageString().length() >= 30) {
                    lastMessage.setMessageString(lastMessage.getMessageString().substring(0, 25) + "..");
                }
                messageItemsList.add(new MessagesListViewItem(id, status, userName, lastMessage.getDate(), lastMessage.getMessageString()));
            }
        }
        return sortByDate(messageItemsList);
    }

    private static List<MessagesListViewItem> sortByDate(List<MessagesListViewItem> messageItemsList) {
        Collections.sort(messageItemsList, new MessageItemComparator());
        return messageItemsList;
    }

    private static Message findLastMessage(Integer id, Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        return dbHelper.getLastMessageWithContactID(id);
    }

    private static Boolean findStatus(Integer id, List<UserStatus> userStatuses) {
        for (UserStatus status : userStatuses) {
            if (status.getUserId().equals(id)) {
                return status.isActive();
            }
        }
        return null;
    }
}