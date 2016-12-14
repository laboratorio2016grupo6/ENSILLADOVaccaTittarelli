package laboratorio2016.github.com.ensilladovaccatittarelli.clases;

import laboratorio2016.github.com.ensilladovaccatittarelli.R;
import laboratorio2016.github.com.ensilladovaccatittarelli.interfaces.Voices;


/**
 * Created by Gonzalo on 13/12/2016.
 */

public class FemaleVoices implements Voices {
    @Override
    public int getBajoMonturaVoice() {
        return R.raw.fem_bajomontura;
    }

    @Override
    public int getBozalVoice() {
        return R.raw.fem_bozal;
    }

    @Override
    public int getCabezadaVoice() {
        return R.raw.fem_cabezada;
    }

    @Override
    public int getSudaderaVoice() {
        return R.raw.fem_sudadera;
    }

    @Override
    public int getMatraVoice() { return R.raw.fem_matra; }

    @Override
    public int getMonturaVoice() {
        return R.raw.fem_montura;
    }

    @Override
    public int getCaballoVoice() { return R.raw.fem_caballo; }
}

