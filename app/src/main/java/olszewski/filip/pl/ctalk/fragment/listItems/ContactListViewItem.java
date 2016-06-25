package olszewski.filip.pl.ctalk.fragment.listItems;

/**
 * Created by Filip on 2016-06-21.
 */
public class ContactListViewItem {

    private Integer id;
    private String name;
    private String email;

    public ContactListViewItem(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {

        boolean same = false;
        if (o != null && o instanceof ContactListViewItem) {
            same = name.equals(((ContactListViewItem) o).getName()) &&
                    id.equals(((ContactListViewItem) o).getId()) &&
                    email.equals(((ContactListViewItem) o).getEmail());
        }
        return same;
    }
}