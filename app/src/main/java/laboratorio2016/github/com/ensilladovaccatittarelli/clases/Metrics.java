package laboratorio2016.github.com.ensilladovaccatittarelli.clases;

import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Gonzalo on 12/12/2016.
 */

public class Metrics {

    private DisplayMetrics displayMetrics;

    public Metrics(WindowManager wm){
        this.displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(this.displayMetrics);
    }

    public int getWidth(){
        int screenWidth = this.displayMetrics.widthPixels;
        return screenWidth;
    }
    public int getHeight(){
        int screenHeight = this.displayMetrics.heightPixels;
        return screenHeight;
    }

    public int dpToPixel(int dp) {
        return (int) (dp * this.displayMetrics.density );
    }

}
