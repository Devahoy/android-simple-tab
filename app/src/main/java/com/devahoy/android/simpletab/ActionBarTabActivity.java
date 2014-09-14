package com.devahoy.android.simpletab;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ActionBarTabActivity extends Activity implements ActionBar.TabListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (int i = 1; i <= 3; i++) {
            ActionBar.Tab tab = actionBar.newTab()
                    .setText("Tab#" + i)
                    .setTabListener(this);
            actionBar.addTab(tab);
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        Fragment fragment = new MyFragment();
        Bundle args = new Bundle();


        switch (tab.getPosition()) {
            case 0:
                args.putString("color", "#2574a9");
                fragment.setArguments(args);
                break;
            case 1:
                args.putString("color", "#36d5b5");
                fragment.setArguments(args);
                break;
            case 2:
                args.putString("color", "#f9640f");
                fragment.setArguments(args);
                break;
        }

        ft.replace(android.R.id.content, fragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public static class MyFragment extends Fragment {

        private String color;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle args = getArguments();
            color = args.getString("color");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.tab, container, false);

            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear);
            linearLayout.setBackgroundColor(Color.parseColor(color));

            return view;
        }
    }
}
