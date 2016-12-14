package laboratorio2016.github.com.ensilladovaccatittarelli.clases;

import laboratorio2016.github.com.ensilladovaccatittarelli.R;
import laboratorio2016.github.com.ensilladovaccatittarelli.interfaces.Voices;

/**
 * Created by Gonzalo on 13/12/2016.
 */

public class MaleVoices implements Voices {
    @Override
    public int getBajoMonturaVoice() {
        return R.raw.masc_bajomontura;
    }

    @Override
    public int getBozalVoice() {
        return R.raw.masc_bozal;
    }

    @Override
    public int getCabezadaVoice() {
        return R.raw.masc_cabezada;
    }

    @Override
    public int getSudaderaVoice() {
        return R.raw.masc_sudadera;
    }

    @Override
    public int getMatraVoice() {
        return R.raw.masc_matra;
    }

    @Override
    public int getMonturaVoice() {
        return R.raw.masc_montura;
    }

    @Override
    public int getCaballoVoice() { return R.raw.masc_caballo; }
}
