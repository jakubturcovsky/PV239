package cz.pv239.seminar5.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import cz.pv239.seminar5.R

class SettingsFragment : PreferenceFragmentCompat() {

    companion object {

        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)
    }
}
