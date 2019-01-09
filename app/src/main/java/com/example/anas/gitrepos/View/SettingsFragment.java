package com.example.anas.gitrepos.View;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.anas.gitrepos.R;

//This Fragment's role is to let the user choose the corresponding preferences

public class SettingsFragment extends PreferenceFragmentCompat {

    private final String DEBUG_TAG = "SettingsFragmentTAG";

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.settings_preferences);
    }

}
