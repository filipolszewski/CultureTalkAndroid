package olszewski.filip.pl.ctalk.fragment.listItems;

/**
 * Created by Filip on 2016-05-03.
 */
public class EventListViewItem {

    public final Integer id;
    public final String title;        // the text for the ListView item title
    public final String address;  // the text for the ListView item description
    public final String date;
    public final String institutionName;

    public EventListViewItem(Integer id, String title, String address, String date, String institutionName) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.date = date;
        this.institutionName = institutionName;
    }

    public Integer getId() {
        return id;
    }
}

