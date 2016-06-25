package olszewski.filip.pl.ctalk.fragment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.fragment.listItems.ProvinceListViewItem;
import olszewski.filip.pl.ctalk.utility.SharedPreferencesUtil;

/**
 * Created by Filip on 2016-06-21.
 */
public class ProvinceListViewAdapter extends ArrayAdapter<ProvinceListViewItem> {

    public ProvinceListViewAdapter(Context context, List<ProvinceListViewItem> resource) {
        super(context, R.layout.li_province_item, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        ProvinceListViewItem item = this.getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.li_province_item, parent, false);
            textView = (TextView) convertView.findViewById(R.id.provName);
            convertView.setTag(new ViewHolder(textView));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            textView = viewHolder.textView;
        }
        textView.setText(item.name);
        if (textView.getText().equals(getPrefferedProv())) {
            textView.setTextColor(Color.rgb(20, 190, 20));
        } else {
            textView.setTextColor(Color.DKGRAY);
        }
        return convertView;
    }

    private CharSequence getPrefferedProv() {
        String prefProv = SharedPreferencesUtil.getPrefferedProvince(getContext());
        return prefProv.substring(prefProv.indexOf(".") + 1);
    }

    private class ViewHolder {
        TextView textView;

        public ViewHolder(TextView textView) {
            this.textView = textView;
        }
    }
}
