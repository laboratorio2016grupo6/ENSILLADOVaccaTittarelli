package laboratorio2016.github.com.ensilladovaccatittarelli;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Gonzalo on 9/12/2016.
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);



    }
}

