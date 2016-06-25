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
 * Created by Filip on 2016-05-04.
 */
public class ContactsListViewAdapter extends ArrayAdapter<ContactListViewItem> {

    public ContactsListViewAdapter(Context context, List<ContactListViewItem> items) {
        super(context, R.layout.li_contacts_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView nameTv;
        TextView emailTv;
        ContactListViewItem item = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.li_contacts_item, parent, false);

            nameTv = (TextView) convertView.findViewById(R.id.tv_contact_name);
            emailTv = (TextView) convertView.findViewById(R.id.tv_contact_email);
            convertView.setTag(new ViewHolder(nameTv, emailTv));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            nameTv = viewHolder.tvContactName;
            emailTv = viewHolder.tvContactEmail;
        }
        nameTv.setText(item.getName());
        emailTv.setText(item.getEmail());
        return convertView;
    }

    private static class ViewHolder {

        TextView tvContactName;
        TextView tvContactEmail;

        public ViewHolder(TextView tvContactName, TextView tvContactEmail) {
            this.tvContactName = tvContactName;
            this.tvContactEmail = tvContactEmail;
        }
    }
}
