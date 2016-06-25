package olszewski.filip.pl.ctalk.listener;

import java.util.List;

import olszewski.filip.pl.ctalk.fragment.listItems.ContactListViewItem;

/**
 * Created by Filip on 2016-06-21.
 */
public interface AddContactsListener {

    void onContactList(List<ContactListViewItem> list);

    void onFailure(Integer statusCode);
}
