package laboratorio2016.github.com.ensilladovaccatittarelli;

import android.content.ClipData;
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
import android.view.DragEvent;
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

import laboratorio2016.github.com.ensilladovaccatittarelli.clases.Component;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.ElementHorse;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.FemaleVoices;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.LayoutParams;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.Level;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.MaleVoices;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.Metrics;
import laboratorio2016.github.com.ensilladovaccatittarelli.clases.SoundPlayer;
import laboratorio2016.github.com.ensilladovaccatittarelli.interfaces.Voices;

/**
 * Created by Gonzalo on 14/12/2016.
 */

public class GameActivity extends AppCompatActivity {

    private List<CircularImageView> imgpositions = new ArrayList<CircularImageView>();

    private List<ElementHorse> elements = new ArrayList<ElementHorse>();

    private Component selectedComponent = null;

    private ImageView horseView;

    private Metrics metrics;

    private LayoutParams layoutParams;

    private Vibrator vibrator;

    private SoundPlayer soundPlayer;

    private Level level;

    private List<Component> components;

    private int lastElement = 0;

    private int order = 1;

    private SharedPreferences sp;

    private CoordinatorLayout horseBack;

    private Voices voices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        this.metrics = new Metrics(getWindowManager());
        this.layoutParams = new LayoutParams(this.metrics);

        horseView = (ImageView) findViewById(R.id.horseView);
        horseView.setLayoutParams(layoutParams.getHorseParams());

        this.horseBack = (CoordinatorLayout) findViewById(R.id.background_horse);
        this.sp = PreferenceManager.getDefaultSharedPreferences(this);

        int lev = Integer.valueOf(sp.getString("pref_key_level_setting", "0"));

        this.level = Level.values()[lev-1];

        init();
    }


    private void init(){

        this.imgpositions = new ArrayList<CircularImageView>();
        this.elements = new ArrayList<ElementHorse>();
        this.lastElement=0;
        this.order=1;
        boolean sinElementos = sp.getBoolean("pref_key_state_horse_setting", true);
        if (!sinElementos) {
            this.order = 3;
            this.lastElement = 2;
            horseBack.setBackgroundResource(R.drawable.caballo_bozalceleste_3);
        }
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

        if (this.level == Level.EXPERTO) {
            this.imgpositions.add(imgtoplef);
            this.imgpositions.add(imgtopright);
            this.imgpositions.add(imgcenterleft);
            this.imgpositions.add(imgcenterright);
            this.imgpositions.add(imgbottomleft);
            this.imgpositions.add(imgbottomright);
        }else{
            if (level == Level.AVANZADO) {
                this.imgpositions.add(imgtoplef);
                this.imgpositions.add(imgtopright);
                this.imgpositions.add(imgbottomleft);
                this.imgpositions.add(imgbottomright);
            }else{
                if (level == Level.MEDIO) {
                    this.imgpositions.add(imgcenterleft);
                    this.imgpositions.add(imgcenterright);
                }else {
                    if (level == Level.INICIAL) {
                        this.imgpositions.add(imgcenterleft);
                    }
                }
            }
        }

        String voice = sp.getString("pref_key_voice_setting", "");
        voices = ((voice.equals("F")?new FemaleVoices(): new MaleVoices()));
        soundPlayer = new SoundPlayer(voices, this);

        elements.add(new ElementHorse(R.drawable.cabezada, R.drawable.caballo_cabezada_2, 1, soundPlayer.getCabezadaSoundId()));
        elements.add(new ElementHorse(R.drawable.bozal,R.drawable.caballo_bozalceleste_3, 2, soundPlayer.getBozalSoundId()));
        elements.add(new ElementHorse(R.drawable.sudadera,R.drawable.caballo_sudadera_4, 3, soundPlayer.getSudaderaSoundId()));
        elements.add(new ElementHorse(R.drawable.matra,R.drawable.caballo_matra_5, 4, soundPlayer.getMatraSoundId()));
        elements.add(new ElementHorse(R.drawable.bajomontura,R.drawable.caballo_bajomontura_6, 5, soundPlayer.getBajoMonturaSoundId()));
        elements.add(new ElementHorse(R.drawable.montura,R.drawable.caballo_montura_7, 6, soundPlayer.getMonturaSoundId()));

        this.components = new ArrayList<Component>();
        Collections.shuffle(imgpositions, new Random(System.nanoTime()));
        int cantidadImagenes = ((!sinElementos & (level == Level.EXPERTO))?this.imgpositions.size()-lastElement:this.imgpositions.size());
        for (int i=0; i<cantidadImagenes; i++) {
            Component c = new Component(this.imgpositions.get(i), elements.get(i + lastElement));
            this.components.add(c);
            c.getView().setImageResource(c.getElementHorse().getImage());
            c.getView().setOnClickListener(new ImageViewClickListener(c));
            c.getView().setOnLongClickListener(new ImageViewListenerLong(c));
        }
        this.lastElement = lastElement + imgpositions.size()-1;
        horseView.setOnClickListener(new HorseViewClickListener());
        horseView.setOnDragListener(new HorseViewDragListener());
    }

    private class ImageViewListenerLong implements View.OnLongClickListener {

        private Component component;

        public ImageViewListenerLong(Component component) {
            this.component = component;
        }

        @Override
        public boolean onLongClick(View view) {
            if (selectedComponent == null) {
                selectedComponent = this.component;
                selectedComponent.getView().setShadowColor(Color.CYAN);
                soundPlayer.play(selectedComponent.getElementHorse().getSound());
            } else {
                if (!selectedComponent.equals(this.component)) {
                    selectedComponent.getView().setShadowColor(Color.GRAY);
                    this.component.getView().setShadowColor(Color.CYAN);
                    soundPlayer.play(this.component.getElementHorse().getSound());
                    selectedComponent = this.component;
                } else {
                    soundPlayer.play(selectedComponent.getElementHorse().getSound());
                }
            }
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(this.component.getView());
            this.component.getView().startDrag(data, shadowBuilder, this.component.getView(), 0);
            return true;
        }
    }

    private class ImageViewClickListener implements View.OnClickListener{

        private Component component;

        public ImageViewClickListener(Component component) {
            this.component = component;
        }

        @Override
        public void onClick(View view) {
            if (selectedComponent == null) {
                selectedComponent = this.component;
                selectedComponent.getView().setShadowColor(Color.CYAN);
                soundPlayer.play(selectedComponent.getElementHorse().getSound());
            } else {
                if (selectedComponent.equals(this.component)) {
                    this.component.getView().setShadowColor(Color.GRAY);
                    selectedComponent = null;
                } else {
                    selectedComponent.getView().setShadowColor(Color.GRAY);
                    this.component.getView().setShadowColor(Color.CYAN);
                    soundPlayer.play(this.component.getElementHorse().getSound());
                    selectedComponent = this.component;
                }
            }
        }
    }

    public class HorseViewDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View view, DragEvent event) {
            switch (event.getAction()){
                case DragEvent.ACTION_DROP:
                    GameActivity.this.selectionHorse();
                    break;
            }
            return true;
        }
    }

    public class HorseViewClickListener implements View.OnClickListener {
        public void onClick(View v) {
            GameActivity.this.selectionHorse();
        }
    }

    private void selectionHorse(){
        if (selectedComponent != null) {
            if (selectedComponent.getElementHorse().getOrder() == order) {
                if (order==6) {
                    horseBack.setBackgroundResource(selectedComponent.getElementHorse().getImageHorse());
                    soundPlayer.playWin1();
                    for (CircularImageView civ : imgpositions)
                        civ.setVisibility(View.INVISIBLE);
                    winAlert();
                }else{
                    if (order > elements.size() - level.getElements())
                        selectedComponent.getView().setVisibility(View.INVISIBLE);
                    if (lastElement<5) {
                        List<Component> componentsExcept = new ArrayList<Component>();
                        componentsExcept.addAll(components);
                        componentsExcept.remove(selectedComponent);
                        Collections.shuffle(componentsExcept, new Random(System.nanoTime()));
                        Component c;
                        if (level.equals(Level.INICIAL))
                            c=selectedComponent;
                        else
                            c = componentsExcept.get(0);
                        ElementHorse ele = elements.get(lastElement+1);
                        horseBack.setBackgroundResource(selectedComponent.getElementHorse().getImageHorse());
                        selectedComponent.setElementHorse(c.getElementHorse());
                        selectedComponent.getView().setImageResource(c.getElementHorse().getImage());
                        selectedComponent.getView().setOnClickListener(new ImageViewClickListener(new Component(selectedComponent.getView(),c.getElementHorse())));
                        selectedComponent.getView().setShadowColor(Color.GRAY);
                        c.setElementHorse(ele);
                        c.getView().setOnClickListener(new ImageViewClickListener(c));
                        c.getView().setImageResource(ele.getImage());
                        c.getView().setVisibility(View.VISIBLE);
                        c.getView().setShadowColor(Color.GRAY);
                        lastElement++;
                    }else{
                        horseBack.setBackgroundResource(selectedComponent.getElementHorse().getImageHorse());
                    }
                    order++;
                    soundPlayer.playRelincho();
                    selectedComponent = null;
                }
            }else{
                shake(selectedComponent.getView());
                soundPlayer.playResoplido();
                vibrate(400);
            }

        }else {
            soundPlayer.playCaballo();
        }
    }

    private void toActivity(Class activity){
        GameActivity.this.finish();
        Intent intent = new Intent(this.getApplicationContext(), activity);
        startActivity(intent);
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
        builder.setNegativeButton(R.string.negativeButtonWinAlert, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                toActivity(MainActivity.class);
            }
        });
        builder.setNeutralButton(R.string.neutralButtonWinAlert, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        if(this.level != Level.EXPERTO){
            builder.setPositiveButton(R.string.positiveButtonWinAlert, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    if(level.getId() < 3) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("pref_key_level_setting", String.valueOf(level.getId() + 2));
                        editor.commit();
                    }
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });
        }
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
