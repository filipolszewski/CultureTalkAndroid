package olszewski.filip.pl.ctalk.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import olszewski.filip.pl.ctalk.R;
import olszewski.filip.pl.ctalk.fragment.ContactsFragment;
import olszewski.filip.pl.ctalk.fragment.EventsFragment;
import olszewski.filip.pl.ctalk.fragment.MessagesFragment;
import olszewski.filip.pl.ctalk.fragment.SettingsFragment;

public class MenuActivity extends FragmentActivity {

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        PagerTitleStrip titleStrip = (PagerTitleStrip) findViewById(R.id.pager_header);
        titleStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        vpPager.setCurrentItem(2);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - ContactsFragment
                    return ContactsFragment.newInstance();
                case 1: // Fragment # 1 - MessagesFragment
                    return MessagesFragment.newInstance();
                case 2: // Fragment # 2 - EventsFragment
                    return EventsFragment.newInstance();
                case 3: // Fragment # 3 - SettingsFragment
                    return SettingsFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: // Fragment # 0 - ContactsFragment
                    return "Kontakty";
                case 1: // Fragment # 1 - MessagesFragment
                    return "Wiadomości";
                case 2: // Fragment # 2 - EventsFragment
                    return "Wydarzenia";
                case 3: // Fragment # 3 - SettingsFragment
                    return "Ustawienia";
                default:
                    return null;
            }
        }
    }
}
