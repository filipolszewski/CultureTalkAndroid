package olszewski.filip.pl.ctalk.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import olszewski.filip.pl.ctalk.fragment.listItems.MessagesListViewItem;

/**
 * Created by Filip on 2016-06-22.
 */
public class MessageItemComparator implements Comparator<MessagesListViewItem> {

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    public int compare(MessagesListViewItem m1, MessagesListViewItem m2) {
        try {
            Date d1 = df.parse(m1.getDate());
            Date d2 = df.parse(m2.getDate());
            return d2.compareTo(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
