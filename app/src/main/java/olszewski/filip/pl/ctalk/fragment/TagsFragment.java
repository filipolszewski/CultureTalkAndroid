package olszewski.filip.pl.ctalk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.activity.MenuActivity;
import olszewski.filip.pl.ctalk.controller.TagsController;
import olszewski.filip.pl.ctalk.fragment.adapter.TagsListViewAdapter;
import olszewski.filip.pl.ctalk.fragment.listItems.TagsListViewItem;
import olszewski.filip.pl.ctalk.listener.TagsListener;
import olszewski.filip.pl.ctalk.utility.SharedPreferencesUtil;

/**
 * Created by Filip on 2016-06-20.
 */
public class TagsFragment extends ListFragment {

    private TagsController controller = new TagsController();
    private TagsListViewAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller.setContext(getContext());
        controller.setListener(new TagsListener() {
            @Override
            public void onListOfTags(List<TagsListViewItem> list) {
                listAdapter = new TagsListViewAdapter(getActivity(), list);
                setListAdapter(listAdapter);
                validateWithPreferences();
            }

            @Override
            public void onFailure(Integer statusCode) {
                Toast.makeText(getContext(), "Error occured: " + statusCode, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tags, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        controller.invokeForTags();
    }

    private void validateWithPreferences() {
        String checkedTags = SharedPreferencesUtil.getCheckedTagsAsString(getContext());
        for (int i = 0; i < listAdapter.getCount(); i++) {
            TagsListViewItem item = listAdapter.getItem(i);
            if (checkedTags.contains(item.name)) {
                listAdapter.getItem(i).checked = true;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        StringBuilder savedTags = new StringBuilder("");
        for (int i = 0; i < listAdapter.getCount(); i++) {
            TagsListViewItem item = listAdapter.getItem(i);
            if (item.checked) {
                savedTags.append(item.name);
            }
        }
        Set<String> savedTagIDs = new HashSet<>();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            TagsListViewItem item = listAdapter.getItem(i);
            if (item.checked) {
                savedTagIDs.add(item.id + "");
            }
        }
        SharedPreferencesUtil.setCheckedTags(getContext(), savedTags.toString());
        SharedPreferencesUtil.setCheckedTagIDs(getContext(), savedTagIDs);
    }
}
