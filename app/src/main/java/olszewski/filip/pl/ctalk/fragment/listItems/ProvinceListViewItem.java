package olszewski.filip.pl.ctalk.fragment.listItems;

/**
 * Created by Filip on 2016-06-20.
 */
public class ProvinceListViewItem {

    private Integer id;
    public final String name;        // the text for the ListView item title

    public ProvinceListViewItem(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
