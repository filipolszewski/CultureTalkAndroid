package olszewski.filip.pl.ctalk.controller;

import android.content.Context;

import olszewski.filip.pl.ctalk.connector.TagServiceConnector;
import olszewski.filip.pl.ctalk.listener.TagsListener;

/**
 * Created by Filip on 2016-06-20.
 */
public class TagsController {

    private TagServiceConnector conn = new TagServiceConnector();

    public void invokeForTags() {
        conn.invokeForTags();
    }

    public void setListener(TagsListener tagsListener) {
        conn.setListener(tagsListener);
    }

    public void setContext(Context context) {
        conn.setContect(context);
    }
}
