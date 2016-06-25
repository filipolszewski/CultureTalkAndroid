package olszewski.filip.pl.ctalk.fragment.listItems;

/**
 * Created by Filip on 2016-06-20.
 */
public class TagsListViewItem {

    public Integer id;
    public String name;
    public boolean checked;

    public TagsListViewItem(Integer id, String name, boolean checked) {
        this.id = id;
        this.name = name;
        this.checked = checked;
    }
}
