package olszewski.filip.pl.ctalk.listener;

import java.util.List;

import olszewski.filip.pl.ctalk.fragment.listItems.ProvinceListViewItem;

/**
 * Created by Filip on 2016-06-21.
 */
public interface ProvinceListener {
    void onProvinceList(List<ProvinceListViewItem> list);

    void onFailure(Integer statusCode);
}
