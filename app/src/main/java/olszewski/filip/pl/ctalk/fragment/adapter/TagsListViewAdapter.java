package olszewski.filip.pl.ctalk.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.fragment.listItems.TagsListViewItem;

/**
 * Created by Filip on 2016-06-20.
 */
public class TagsListViewAdapter extends ArrayAdapter<TagsListViewItem> {

    public TagsListViewAdapter(Context context, List<TagsListViewItem> items) {
        super(context, R.layout.li_tags_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        CheckBox checkBox;
        TagsListViewItem item = this.getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.li_tags_item, parent, false);
            textView = (TextView) convertView.findViewById(R.id.tv_tagName);
            checkBox = (CheckBox) convertView.findViewById(R.id.tag_checkbox);
            convertView.setTag(new ViewHolder(textView, checkBox));
            checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    TagsListViewItem item = (TagsListViewItem) cb.getTag();
                    item.checked = cb.isChecked();
                }
            });
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView
                    .getTag();
            checkBox = viewHolder.checkBox;
            textView = viewHolder.tvName;
        }
        checkBox.setTag(item);
        checkBox.setChecked(item.checked);
        textView.setText(item.name);
        return convertView;
    }

    private static class ViewHolder {
        TextView tvName;
        CheckBox checkBox;

        public ViewHolder(TextView textView, CheckBox checkBox) {
            tvName = textView;
            this.checkBox = checkBox;
        }
    }
}
