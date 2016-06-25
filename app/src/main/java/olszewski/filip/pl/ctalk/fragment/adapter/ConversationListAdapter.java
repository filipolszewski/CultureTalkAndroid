package olszewski.filip.pl.ctalk.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.fragment.listItems.ConversationListViewItem;

/**
 * Created by Filip on 2016-06-24.
 */
public class ConversationListAdapter extends ArrayAdapter<ConversationListViewItem> {

    public ConversationListAdapter(Context context, List<ConversationListViewItem> objects) {
        super(context, R.layout.li_conversation_item, objects);
    }

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView squareIv;
        TextView messageTv;
        TextView dateTv;
        ConversationListViewItem item = this.getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.li_conversation_item, parent, false);
            messageTv = (TextView) convertView.findViewById(R.id.tvConversation_text);
            dateTv = (TextView) convertView.findViewById(R.id.tvConversation_MessageDate);
            squareIv = (ImageView) convertView.findViewById(R.id.ivConversation);
            convertView.setTag(new ViewHolder(squareIv, messageTv, dateTv));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            squareIv = viewHolder.squareIv;
            messageTv = viewHolder.messageTv;
            dateTv = viewHolder.dateTv;
        }
        if (!item.getSentOrReceived()) {
            String string = "Ty: " + item.getMessage();
            messageTv.setText(string);
            squareIv.setBackgroundResource(R.drawable.blue_square);
        } else {
            squareIv.setBackgroundResource(R.drawable.grey_square);
            String string = item.getUserName().substring(0, item.getUserName().indexOf(" ")) + ": " + item.getMessage();
            messageTv.setText(string);
        }
        String date = item.getDateSent();
        try {
            if (isToday(df.parse(date))) {
                dateTv.setText(date.substring(11));
            } else {
                if (isThisYear(df.parse(date))) {
                    String finalDate = date.substring(8, 10) + "-" + date.substring(5, 7);
                    dateTv.setText(finalDate);
                } else {
                    dateTv.setText(date.substring(0, 10));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertView;
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

    private class ViewHolder {

        ImageView squareIv;
        TextView messageTv;
        TextView dateTv;

        public ViewHolder(ImageView squareIv, TextView messageTv, TextView dateTv) {
            this.squareIv = squareIv;
            this.messageTv = messageTv;
            this.dateTv = dateTv;
        }
    }
}
