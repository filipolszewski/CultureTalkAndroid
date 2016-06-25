package olszewski.filip.pl.ctalk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.util.List;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.activity.SingleEventActivity;
import olszewski.filip.pl.ctalk.controller.EventController;
import olszewski.filip.pl.ctalk.fragment.adapter.EventListViewAdapter;
import olszewski.filip.pl.ctalk.fragment.listItems.EventListViewItem;
import olszewski.filip.pl.ctalk.listener.EventsListener;

public class EventsFragment extends ListFragment implements OnItemClickListener {

    private List<EventListViewItem> mItems;        // ListView items list
    private EventController controller = EventController.getInstance();

    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller.setContext(this.getContext());
        controller.setListener(new EventsListener() {
            @Override
            public void onListOfEvents(List<EventListViewItem> mItems) {
                EventsFragment.this.mItems = mItems;
                setListAdapter(new EventListViewAdapter(getActivity(), mItems));
            }

            @Override
            public void onFailure(int statusCode) {
                Toast.makeText(getActivity(), "Error: " + statusCode, Toast.LENGTH_LONG)
                        .show();
            }
        });
        controller.invokeForEventList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.invokeForEventList();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        navigateToSingleEventActivity(position);
    }

    private void navigateToSingleEventActivity(int position) {
        Intent intent = new Intent(getContext(), SingleEventActivity.class);
        Integer idEvent = mItems.get(position).getId();
        intent.putExtra("id", idEvent);
        startActivity(intent);
    }
}
