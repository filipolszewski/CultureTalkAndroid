package olszewski.filip.pl.ctalk.listener;

import java.util.List;

import olszewski.filip.pl.ctalk.fragment.listItems.TagsListViewItem;

/**
 * Created by Filip on 2016-06-20.
 */
public interface TagsListener {
    void onListOfTags(List<TagsListViewItem> list);

    void onFailure(Integer statusCode);
}
