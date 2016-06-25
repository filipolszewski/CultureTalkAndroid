package olszewski.filip.pl.ctalk.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.fragment.listItems.EventListViewItem;

/**
 * Created by Filip on 2016-05-03.
 */
public class EventListViewAdapter extends ArrayAdapter<EventListViewItem> {

    public EventListViewAdapter(Context context, List<EventListViewItem> items) {
        super(context, R.layout.li_events_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.li_events_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            viewHolder.tvInst = (TextView) convertView.findViewById(R.id.tvInst);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        EventListViewItem item = getItem(position);
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvAddress.setText(item.address);
        viewHolder.tvDate.setText(item.date);
        viewHolder.tvInst.setText(item.institutionName);

        return convertView;
    }

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvAddress;
        TextView tvDate;
        TextView tvInst;
    }
}
