package br.com.sbrunettajr.quote.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import br.com.sbrunettajr.quote.utils.Preference;
import br.com.sbrunettajr.quote.R;

public class ConfigurationActivity extends AppCompatActivity {

    private EditText et_update_time;

    private final String preferenceName = Preference.PreferenceName.PREF_UPD_TYPE.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        et_update_time = (EditText) findViewById(R.id.et_update_time);
        et_update_time.setText(String.valueOf(loadUpdateTime()));
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveUpdateTime();
    }

    private int loadUpdateTime() {
        return Preference.getInteger(ConfigurationActivity.this, preferenceName);
    }

    private void saveUpdateTime() {
        int updateTime = Integer.valueOf(et_update_time.getText().toString());

        Preference.setInteger(ConfigurationActivity.this, preferenceName, updateTime);
    }
}