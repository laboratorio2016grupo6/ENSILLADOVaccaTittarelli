package laboratorio2016.github.com.ensilladovaccatittarelli.clases;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import laboratorio2016.github.com.ensilladovaccatittarelli.R;
import laboratorio2016.github.com.ensilladovaccatittarelli.interfaces.Voices;


/**
 * Created by Gonzalo on 13/12/2016.
 */

public class SoundPlayer {
    private SoundPool soundPool;
    private SoundPool.Builder soundPoolBuilder;

    private AudioAttributes attributes;
    private AudioAttributes.Builder attributesBuilder;

    private int[] sounds_id = new int[15];

    private int i = 5;
    private Context context;
    private Voices voices;

    public SoundPlayer(Voices voices, Context context){
        this.context = context;
        this.voices = voices;
        createSoundPool();
        loadVoices();
    }

    private void createSoundPool(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            attributesBuilder = new AudioAttributes.Builder();
            attributesBuilder.setUsage(AudioAttributes.USAGE_GAME);
            attributesBuilder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
            attributes = attributesBuilder.build();

            soundPoolBuilder = new SoundPool.Builder().setMaxStreams(15);
            soundPoolBuilder.setAudioAttributes(attributes);
            soundPool = soundPoolBuilder.build();
        }else{
            soundPool = new SoundPool(15, AudioManager.STREAM_MUSIC, 0);
        }
    }

    private void loadVoices(){
        sounds_id[0] = soundPool.load(context, voices.getCaballoVoice(), 1);
        sounds_id[1] = soundPool.load(context, R.raw.resoplido, 1);
        sounds_id[2] = soundPool.load(context, R.raw.relincho, 1);
        sounds_id[3] = soundPool.load(context, R.raw.win1, 1);
        sounds_id[4] = soundPool.load(context, R.raw.win2, 1);
        sounds_id[5] = soundPool.load(context, voices.getCabezadaVoice(), 1);
        sounds_id[6] = soundPool.load(context, voices.getBozalVoice(), 1);
        sounds_id[7] = soundPool.load(context, voices.getSudaderaVoice(), 1);
        sounds_id[8] = soundPool.load(context, voices.getMatraVoice(), 1);
        sounds_id[9] = soundPool.load(context, voices.getBajoMonturaVoice(), 1);
        sounds_id[10] = soundPool.load(context, voices.getMonturaVoice(), 1);
    }

    public int getCabezadaSoundId() {
        return sounds_id[5];
    }
    public int getBozalSoundId() {
        return sounds_id[6];
    }
    public int getSudaderaSoundId() {
        return sounds_id[7];
    }
    public int getMatraSoundId() {
        return sounds_id[8];
    }
    public int getBajoMonturaSoundId() {
        return sounds_id[9];
    }
    public int getMonturaSoundId() {
        return sounds_id[10];
    }

    public int loadVoice(int voice){
        return soundPool.load(context,voice,1);
    }

    public void liberar(){
        soundPool.release();
    }

    public void play(int sound_id){
        soundPool.play(sound_id,1,1,0,0,1);
    }

    public void playRelincho(){
        play(sounds_id[2]);
    }

    public void playResoplido(){
        play(sounds_id[1]);
    }

    public void playCaballo(){
        play(sounds_id[0]);
    }

    public void playWin1(){
        play(sounds_id[3]);
    }

    public void playWin2(){
        play(sounds_id[4]);
    }

}

