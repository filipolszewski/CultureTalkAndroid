package olszewski.filip.pl.ctalk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import olszewski.filip.pl.ctalk.MessageData;
import olszewski.filip.pl.ctalk.controller.ConversationController;
import olszewski.filip.pl.ctalk.fragment.adapter.ConversationListAdapter;
import olszewski.filip.pl.ctalk.fragment.listItems.ConversationListViewItem;
import olszewski.filip.pl.ctalk.listener.ConversationListener;
import olszewski.filip.pl.ctalk.sqlite.DatabaseHelper;
import olszewski.filip.pl.ctalk.sqlite.Message;

/**
 * Created by Filip on 2016-06-24.
 */
public class ConversationFragment extends ListFragment {

    private ConversationController controller = new ConversationController();
    private ConversationListAdapter listAdapter;
    private Integer idUser;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller.setContext(getContext());
        controller.setListener(new ConversationListener() {
            @Override
            public void onMessageSent(MessageData message) {
                DatabaseHelper dh = new DatabaseHelper(getContext());
                dh.saveMessage(message.getToUser().getId(), message.getMessage());
                refreshList();
            }

            @Override
            public void onFailure(Integer statusCode) {
                Toast.makeText(getActivity(), "Error: " + statusCode, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void refreshList() {
        DatabaseHelper dh = new DatabaseHelper(getContext());
        List<Message> list = dh.getConversationWith(idUser);
        List<ConversationListViewItem> items = new ArrayList<>();
        for (Message message : list) {
            items.add(new ConversationListViewItem(message.getContactName(), message.getMessageString(), message.getDate(), message.isSentOrReceived()));
        }
        setListAdapter(new ConversationListAdapter(getContext(), controller.sortByDate(items)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        idUser = intent.getIntExtra("userToID", 1);
        refreshList();
    }

    public void sendMessage(Integer idUser, String string) {
        controller.sendMessage(idUser, string);
    }
}
