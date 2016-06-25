package olszewski.filip.pl.ctalk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.StateInfo;
import olszewski.filip.pl.ctalk.activity.ConversationActivity;
import olszewski.filip.pl.ctalk.controller.MessagesController;
import olszewski.filip.pl.ctalk.fragment.adapter.MessagesListViewAdapter;
import olszewski.filip.pl.ctalk.fragment.listItems.MessagesListViewItem;
import olszewski.filip.pl.ctalk.listener.MessagesListener;
import olszewski.filip.pl.ctalk.sqlite.DatabaseHelper;
import olszewski.filip.pl.ctalk.utility.MessageListResolver;


public class MessagesFragment extends ListFragment {

    private MessagesController controller = new MessagesController();
    private MessagesListViewAdapter listAdapter;

    public static MessagesFragment newInstance() {
        return new MessagesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller.setContext(getContext());
        controller.setListener(new MessagesListener() {

            @Override
            public void onNewStateInfo(StateInfo stateInfo) {
                DatabaseHelper sqliteHelper = new DatabaseHelper(getContext());
                sqliteHelper.saveReceivedMessages(stateInfo.getNewMessages());
                List<MessagesListViewItem> items = MessageListResolver.getMessageList(stateInfo.getUserStatuses(), getContext());
                listAdapter = new MessagesListViewAdapter(getContext(), items);
                setListAdapter(listAdapter);
            }

            @Override
            public void onFailure(Integer statusCode) {
                Toast.makeText(getActivity(), "Error: " + statusCode, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        MessagesListViewItem item = listAdapter.getItem(position);
        Intent intent = new Intent(getContext(), ConversationActivity.class);
        intent.putExtra("userToID", item.getUserID());
        intent.putExtra("userName", item.getUserName());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshMessagesList();
    }

    private void refreshMessagesList() {
        controller.invokeForMessages();
    }
}
