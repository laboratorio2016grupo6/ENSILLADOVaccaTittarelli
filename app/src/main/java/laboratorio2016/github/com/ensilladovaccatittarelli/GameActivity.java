package laboratorio2016.github.com.ensilladovaccatittarelli;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.mikhaellopez.circularimageview.CircularImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import laboratorio2016.github.com.ensilladovaccatittarelli.clases.ElementHorse;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.FemaleVoices;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.LayoutParams;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.MaleVoices;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.Metrics;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.SoundPlayer;
import laboratorio2016.github.com.ensilladovaccatittarelli.interfaces.Voices;

/**
 * Created by Gonzalo on 14/12/2016.
 */

public class GameActivity extends AppCompatActivity {

    private List<CircularImageView> imgpositions = new ArrayList<CircularImageView>();
    private List<ElementHorse> elements;
    private int order = 1;

    private CircularImageView selectedImage;
    private ElementHorse selectedElement;

    private ImageView horseView;

    private Metrics metrics;

    private LayoutParams layoutParams;

    private Vibrator vibrator;

    private SoundPlayer soundPlayer;

    private Voices voices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        init();

        for (int i=0; i<6; i++){
            initImageView(elements.get(i), imgpositions.get(i));
        }

        horseView = (ImageView) findViewById(R.id.horseView);
        horseView.setLayoutParams(layoutParams.getHorseParams());

        horseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedElement != null) {
                    if (selectedElement.getOrder() == order) {
                        CoordinatorLayout c = (CoordinatorLayout)findViewById(R.id.background_horse);
                        c.setBackgroundResource(selectedElement.getImageHorse());
                        selectedImage.setVisibility(View.INVISIBLE);
                        selectedElement = null;
                        order++;
                        soundPlayer.playRelincho();
                    }else{
                        shake(selectedImage);
                        soundPlayer.playResoplido();
                        vibrate(400);
                    }
                    if (order==7) {
                        soundPlayer.playWin1();
                        winAlert();
                    }
                }else {
                    soundPlayer.playCaballo();
                }
            }
        });
    }


    private void init(){
        this.metrics = new Metrics(getWindowManager());
        this.layoutParams = new LayoutParams(this.metrics);
        CircularImageView imgtoplef = (CircularImageView) findViewById(R.id.imgtopleft);
        CircularImageView imgtopright = (CircularImageView) findViewById(R.id.imgtopright);
        CircularImageView imgcenterleft = (CircularImageView) findViewById(R.id.imgcenterleft);
        CircularImageView imgcenterright = (CircularImageView) findViewById(R.id.imgcenterright);
        CircularImageView imgbottomleft = (CircularImageView) findViewById(R.id.imgbottomleft);
        CircularImageView imgbottomright = (CircularImageView) findViewById(R.id.imgbottomright);

        imgtoplef.setLayoutParams(layoutParams.getTopLeftParams());
        imgtopright.setLayoutParams(layoutParams.getTopRightParams());
        imgcenterleft.setLayoutParams(layoutParams.getCenterLeftParams());
        imgcenterright.setLayoutParams(layoutParams.getCenterRightParams());
        imgbottomleft.setLayoutParams(layoutParams.getBottomLeftParams());
        imgbottomright.setLayoutParams(layoutParams.getBottomRightParams());

        imgpositions.add(imgtoplef);
        imgpositions.add(imgtopright);
        imgpositions.add(imgcenterleft);
        imgpositions.add(imgcenterright);
        imgpositions.add(imgbottomleft);
        imgpositions.add(imgbottomright);

        elements = new ArrayList<ElementHorse>();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String voice = sp.getString("pref_key_voice_setting", "");
        voices = ((voice.equals("F")?new FemaleVoices(): new MaleVoices()));
        soundPlayer = new SoundPlayer(voices, this);

        elements.add(new ElementHorse(R.drawable.cabezada, R.drawable.caballo_cabezada_2, 1, soundPlayer.getCabezadaSoundId()));
        elements.add(new ElementHorse(R.drawable.bozal,R.drawable.caballo_bozalceleste_3, 2, soundPlayer.getBozalSoundId()));
        elements.add(new ElementHorse(R.drawable.sudadera,R.drawable.caballo_sudadera_4, 3, soundPlayer.getSudaderaSoundId()));
        elements.add(new ElementHorse(R.drawable.matra,R.drawable.caballo_matra_5, 4, soundPlayer.getMatraSoundId()));
        elements.add(new ElementHorse(R.drawable.bajomontura,R.drawable.caballo_bajomontura_6, 5, soundPlayer.getBajoMonturaSoundId()));
        elements.add(new ElementHorse(R.drawable.montura,R.drawable.caballo_montura_7, 6, soundPlayer.getMonturaSoundId()));
        shuffle();
    }

    private void shuffle(){
        Collections.shuffle(elements, new Random(System.nanoTime()));
    }

    private void initImageView(final ElementHorse element, final CircularImageView image){
        image.setImageResource(element.getImage());
        image.setShadowColor(Color.GRAY);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (element != selectedElement) {
                    if (selectedElement != null) {
                        selectedImage.setShadowColor(Color.GRAY);
                    }
                    image.setShadowColor(Color.CYAN);
                    selectedElement = element;
                    selectedImage = image;
                    soundPlayer.play(element.getSound());
                }else {
                    image.setShadowColor(Color.GRAY);
                    selectedImage = null;
                    selectedElement = null;
                }
            }
        });
    }

    private void vibrate(int duration){
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(duration);
        }
    }

    private void shake(ImageView image) {
        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        image.startAnimation(shake);
    }

    private void winAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        // Add the buttons
        builder.setNegativeButton(R.string.negativeButtonWinAlert, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = getParentActivityIntent();
                finish();
                startActivity(intent);
            }
        });
        builder.setNeutralButton(R.string.neutralButtonWinAlert, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        builder.setPositiveButton(R.string.positiveButtonWinAlert, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        LayoutInflater factory = LayoutInflater.from(GameActivity.this);
        final View view = factory.inflate(R.layout.alert_win, null);
        AlertDialog dialog = builder.create();
        dialog.setView(view);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundPlayer.liberar();
        elements.clear();
        imgpositions.clear();
    }
}
