package olszewski.filip.pl.ctalk.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.activity.MenuActivity;
import olszewski.filip.pl.ctalk.controller.AddContactController;
import olszewski.filip.pl.ctalk.fragment.adapter.AddContactsListViewAdapter;
import olszewski.filip.pl.ctalk.fragment.listItems.ContactListViewItem;
import olszewski.filip.pl.ctalk.listener.AddContactsListener;

/**
 * Created by Filip on 2016-06-21.
 */
public class AddContactFragment extends ListFragment {

    private AddContactController controller = new AddContactController();
    private AddContactsListViewAdapter listAdapter;
    private String currentQuery = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller.setContext(getContext());
        controller.setListener(new AddContactsListener() {

            @Override
            public void onContactList(List<ContactListViewItem> list) {

                listAdapter = new AddContactsListViewAdapter(getActivity(), list);
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tags, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        final ContactListViewItem item = listAdapter.getItem(position);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage("Czy chcesz dodać " + item.getName() + " do kontaktów?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Tak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        controller.addContact(item);
                        getActivity().finish();
                    }
                });

        builder1.setNegativeButton(
                "Nie",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder1.create();
        alert.show();
    }

    public void refreshList(String query) {
        currentQuery = query;
        controller.invokeForContacts(currentQuery);
    }
}
