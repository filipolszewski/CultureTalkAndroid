package olszewski.filip.pl.ctalk.listener;

import java.util.List;

import olszewski.filip.pl.ctalk.fragment.listItems.EventListViewItem;

/**
 * Created by Filip on 2016-06-20.
 */
public interface EventsListener {
    void onListOfEvents(List<EventListViewItem> mItems);

    void onFailure(int statusCode);

}
