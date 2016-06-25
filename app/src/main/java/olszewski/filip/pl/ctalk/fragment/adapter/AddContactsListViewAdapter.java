package olszewski.filip.pl.ctalk.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.fragment.listItems.ContactListViewItem;

/**
 * Created by Filip on 2016-06-21.
 */
public class AddContactsListViewAdapter extends ArrayAdapter<ContactListViewItem> {

    public AddContactsListViewAdapter(Context context, List<ContactListViewItem> objects) {
        super(context, R.layout.li_contacts_item, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView nameTv;
        TextView emailTv;
        ContactListViewItem item = this.getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.li_contacts_item, parent, false);
            nameTv = (TextView) convertView.findViewById(R.id.tv_contact_name);
            emailTv = (TextView) convertView.findViewById(R.id.tv_contact_email);
            convertView.setTag(new ViewHolder(nameTv, emailTv));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            nameTv = viewHolder.nameTv;
            emailTv = viewHolder.emailTv;
        }
        nameTv.setText(item.getName());
        emailTv.setText(item.getEmail());
        return convertView;
    }

    private class ViewHolder {

        public TextView nameTv;
        public TextView emailTv;

        public ViewHolder(TextView nameTv, TextView emailTv) {
            this.nameTv = nameTv;
            this.emailTv = emailTv;
        }
    }
}
