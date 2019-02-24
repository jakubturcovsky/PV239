package cz.pv239.seminar5.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cz.pv239.seminar5.R;

public class SettingsFragment
        extends PreferenceFragment {

    @NonNull
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
    }
}
