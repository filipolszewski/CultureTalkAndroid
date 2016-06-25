package olszewski.filip.pl.ctalk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 2016-06-20.
 */
public class EventInfo {

    private Integer id;
    private String name;
    private String date;
    private String institution;
    private String province;
    private String address;
    private String ticketLink;
    private String description;
    private List<String> tags = new ArrayList<>();

    public EventInfo(Integer id, String name, String date, String institution, String province, String address,
                     String ticketLink, String description, List<String> tags) {
        super();
        this.id = id;
        this.name = name;
        this.date = date;
        this.institution = institution;
        this.province = province;
        this.address = address;
        this.ticketLink = ticketLink;
        this.description = description;
        this.tags = tags;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTicketLink() {
        return ticketLink;
    }

    public void setTicketLink(String ticketLink) {
        this.ticketLink = ticketLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
