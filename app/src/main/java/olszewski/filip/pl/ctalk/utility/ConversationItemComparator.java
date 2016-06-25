package olszewski.filip.pl.ctalk.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import olszewski.filip.pl.ctalk.fragment.listItems.ConversationListViewItem;

/**
 * Created by Filip on 2016-06-25.
 */
public class ConversationItemComparator implements java.util.Comparator<ConversationListViewItem> {

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    @Override
    public int compare(ConversationListViewItem item1, ConversationListViewItem item2) {
        try {
            Date d1 = df.parse(item1.getDateSent());
            Date d2 = df.parse(item2.getDateSent());
            return d1.compareTo(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
