package olszewski.filip.pl.ctalk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.activity.MenuActivity;
import olszewski.filip.pl.ctalk.controller.ProvinceController;
import olszewski.filip.pl.ctalk.fragment.adapter.ProvinceListViewAdapter;
import olszewski.filip.pl.ctalk.fragment.listItems.ProvinceListViewItem;
import olszewski.filip.pl.ctalk.listener.ProvinceListener;
import olszewski.filip.pl.ctalk.utility.SharedPreferencesUtil;

/**
 * Created by Filip on 2016-06-20.
 */
public class ProvinceFragment extends ListFragment {

    private ProvinceController controller = new ProvinceController();
    private ProvinceListViewAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller.setContext(getContext());
        controller.setListener(new ProvinceListener() {
            @Override
            public void onProvinceList(List<ProvinceListViewItem> list) {
                listAdapter = new ProvinceListViewAdapter(getActivity(), list);
                setListAdapter(listAdapter);
            }

            @Override
            public void onFailure(Integer statusCode) {
                Toast.makeText(getContext(), "Error occured: " + statusCode, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        controller.invokeForProvinces();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_province, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        SharedPreferencesUtil.setPrefferedProvince(getContext(), listAdapter.getItem(position));
        getActivity().finish();
    }
}
