package olszewski.filip.pl.ctalk.utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.fragment.listItems.ContactListViewItem;
import olszewski.filip.pl.ctalk.fragment.listItems.ProvinceListViewItem;

/**
 * Created by Filip on 2016-06-22.
 */
public class SharedPreferencesUtil {

    //TODO set/get last message's ID

    public static List<ContactListViewItem> getAddedContacts(Context context) {
        List<ContactListViewItem> addedContacts = new ArrayList<>();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_contacts),
                Context.MODE_PRIVATE);
        String contactsPrefString = sharedPref.getString(context.getString(R.string.saved_contacts), "");
        if (contactsPrefString.equals("") || (contactsPrefString.length() == 0)) {
            return addedContacts;
        }
        String[] contacts = contactsPrefString.split("/");
        for (String string : contacts) {
            String[] data = string.split(";");
            addedContacts.add(new ContactListViewItem(Integer.parseInt(data[0]), data[1], data[2]));
        }
        return addedContacts;
    }

    public static List<Integer> getAddedContactsIDs(Context context) {
        List<ContactListViewItem> addedContacts = getAddedContacts(context);
        List<Integer> contactsIDs = new ArrayList<>();
        for (ContactListViewItem item : addedContacts) {
            contactsIDs.add(item.getId());
        }
        return contactsIDs;
    }

    public static String getCheckedTagsAsString(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_tags),
                Context.MODE_PRIVATE);
        return sharedPref.getString(context.getString(R.string.saved_tags), "");
    }

    public static String getPrefferedProvince(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_province),
                Context.MODE_PRIVATE);
        return sharedPref.getString(context.getString(R.string.saved_province), "1.");
    }

    public static void setPrefferedProvince(Context context, ProvinceListViewItem item) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_province),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.saved_province), item.getId() + "." + item.name);
        editor.apply();
    }

    public static List<String> getCheckedTagsIDs(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_province),
                Context.MODE_PRIVATE);
        List<String> tags = new ArrayList<>();
        Set<String> emptySet = new HashSet<>();
        tags.addAll(sharedPref.getStringSet(context.getString(R.string.saved_tagIds), emptySet));
        return tags;
    }

    public static void setCheckedTags(Context context, String s) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_tags),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.saved_tags), s);
        editor.apply();
    }

    public static void setCheckedTagIDs(Context context, Set<String> savedTagIDs) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_tags),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(context.getString(R.string.saved_tagIds), savedTagIDs);
        editor.apply();
    }

    public static Integer getLastMessageID(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_messages),
                Context.MODE_PRIVATE);
        return Integer.parseInt(sharedPref.getString(context.getString(R.string.lastMessageID), "1"));
    }

    public static void saveLastMessageID(Context context, Integer id) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_messages),
                Context.MODE_PRIVATE);
        Integer current = Integer.parseInt(sharedPref.getString(context.getString(R.string.lastMessageID), "1"));
        if (id > current) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(context.getString(R.string.lastMessageID), id + "");
            editor.apply();
        }
    }
}
