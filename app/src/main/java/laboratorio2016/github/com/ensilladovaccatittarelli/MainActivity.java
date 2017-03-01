package laboratorio2016.github.com.ensilladovaccatittarelli;

import android.content.Intent;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import laboratorio2016.github.com.ensilladovaccatittarelli.clases.LayoutParams;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.Metrics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        Metrics metrics = new Metrics(getWindowManager());
        LayoutParams lparams = new LayoutParams(metrics);

        ImageView settingbutton = (ImageView) findViewById(R.id.settings);
        settingbutton.setLayoutParams(lparams.getSettingsParams());

        settingbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(i);
            }
        });

        ImageView playbutton = (ImageView) findViewById(R.id.play);
        playbutton.setLayoutParams(lparams.getPlayParams());

        playbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), GameActivity.class);
                startActivity(i);
            }
        });

        ImageView imgcedica = (ImageView) findViewById(R.id.cedicaimg);
        imgcedica.setLayoutParams(lparams.getCedicaParams());


        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }
}