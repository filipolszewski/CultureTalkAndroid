package olszewski.filip.pl.ctalk.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.activity.AddContactActivity;
import olszewski.filip.pl.ctalk.activity.ProvinceActivity;
import olszewski.filip.pl.ctalk.activity.TagsActivity;

public class SettingsFragment extends Fragment {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inputFragmentView = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView tagsTextView = (TextView) inputFragmentView.findViewById(R.id.tagsTextView);
        tagsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TagsActivity.class);
                startActivity(intent);
            }
        });

        TextView provinceTextView = (TextView) inputFragmentView.findViewById(R.id.provinceTextView);
        provinceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProvinceActivity.class);
                startActivity(intent);
            }
        });

        TextView addContactsTextView = (TextView) inputFragmentView.findViewById(R.id.addContactsTextView);
        addContactsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddContactActivity.class);
                startActivity(intent);
            }
        });


        return inputFragmentView;
    }
}
