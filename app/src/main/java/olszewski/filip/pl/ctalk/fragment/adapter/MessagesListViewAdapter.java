package olszewski.filip.pl.ctalk.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.fragment.listItems.MessagesListViewItem;

/**
 * Created by Filip on 2016-05-04.
 */
public class MessagesListViewAdapter extends ArrayAdapter<MessagesListViewItem> {

    public MessagesListViewAdapter(Context context, List<MessagesListViewItem> items) {
        super(context, R.layout.li_contacts_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvUserName;
        ImageView tvCircle;
        TextView tvLastMessage;
        TextView tvLastMessagesDate;
        MessagesListViewItem item = getItem(position);

        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.li_messages_item, parent, false);
            tvUserName = (TextView) convertView.findViewById(R.id.tvName);
            tvLastMessage = (TextView) convertView.findViewById(R.id.tvLastMessage);
            tvCircle = (ImageView) convertView.findViewById(R.id.tvGreenCircle);
            tvLastMessagesDate = (TextView) convertView.findViewById(R.id.tvLastMessagesDate);
            convertView.setTag(new ViewHolder(tvUserName, tvCircle, tvLastMessage, tvLastMessagesDate));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            tvUserName = viewHolder.tvUserName;
            tvCircle = viewHolder.tvCircle;
            tvLastMessage = viewHolder.tvLastMessage;
            tvLastMessagesDate = viewHolder.tvLastMessagesDate;
        }
        tvUserName.setText(item.getUserName());
        tvLastMessage.setText(item.getLastMessage());
        if (item.getActive()) {
            tvCircle.setVisibility(View.VISIBLE);
        } else {
            tvCircle.setVisibility(View.INVISIBLE);
        }
        tvLastMessagesDate.setText(getProperDate(item.getDate()));
        return convertView;
    }

    private String getProperDate(String str) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date = null;
        try {
            date = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String fullDate = df.format(date);
        if (isToday(date)) {
            return fullDate.substring(11);
        } else {
            if (isThisYear(date)) {
                return fullDate.substring(8, 10) + "-" + fullDate.substring(5, 7);
            } else {
                return fullDate.substring(0, 10);
            }
        }
    }

    private boolean isThisYear(Date date) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        Calendar cal2 = Calendar.getInstance();
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
    }

    private boolean isToday(Date date) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        Calendar cal2 = Calendar.getInstance();
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    private static class ViewHolder {
        TextView tvUserName;
        ImageView tvCircle;
        TextView tvLastMessage;
        TextView tvLastMessagesDate;

        public ViewHolder(TextView tvUserName, ImageView tvCircle, TextView tvLastMessage, TextView tvLastMessagesDate) {
            this.tvUserName = tvUserName;
            this.tvCircle = tvCircle;
            this.tvLastMessage = tvLastMessage;
            this.tvLastMessagesDate = tvLastMessagesDate;
        }
    }
}
