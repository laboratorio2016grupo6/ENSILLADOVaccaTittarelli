package laboratorio2016.github.com.ensilladovaccatittarelli.clases;

import android.support.design.widget.CoordinatorLayout;
import android.view.Gravity;

/**
 * Created by Gonzalo on 13/12/2016.
 */

public final class LayoutParams {

    private Metrics metrics;
    private static final int dimen_margin_main = 16;

    public LayoutParams(Metrics metrics){
        this.metrics = metrics;
    }

    public CoordinatorLayout.LayoutParams getSettingsParams(){
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams((metrics.getWidth()/10),(metrics.getWidth()/10));
        params.gravity = Gravity.BOTTOM | Gravity.START;
        int margin = metrics.dpToPixel(dimen_margin_main);
        params.bottomMargin = margin;
        params.leftMargin = margin;
        return params;
    }

    public CoordinatorLayout.LayoutParams getPlayParams(){
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams((metrics.getWidth()/10),(metrics.getWidth()/10));
        params.gravity = Gravity.BOTTOM | Gravity.END;
        int margin = metrics.dpToPixel(dimen_margin_main);
        params.bottomMargin = margin;
        params.rightMargin = margin;
        return params;
    }

    public CoordinatorLayout.LayoutParams getCedicaParams(){
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams((metrics.getWidth()/8),(metrics.getWidth()/8));
        params.gravity = Gravity.TOP | Gravity.CENTER;
        int margin = metrics.dpToPixel(dimen_margin_main);
        params.bottomMargin = margin;
        return params;
    }

    public CoordinatorLayout.LayoutParams getTopLeftParams(){
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams((metrics.getWidth()/6),(metrics.getWidth()/6));
        params.gravity = Gravity.TOP | Gravity.LEFT;
        return params;
    }

    public CoordinatorLayout.LayoutParams getTopRightParams(){
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams((metrics.getWidth()/6),(metrics.getWidth()/6));
        params.gravity = Gravity.TOP | Gravity.RIGHT;
        return params;
    }

    public CoordinatorLayout.LayoutParams getCenterLeftParams(){
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams((metrics.getWidth()/6),(metrics.getWidth()/6));
        params.gravity = Gravity.CENTER | Gravity.LEFT;
        return params;
    }

    public CoordinatorLayout.LayoutParams getCenterRightParams(){
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams((metrics.getWidth()/6),(metrics.getWidth()/6));
        params.gravity = Gravity.CENTER | Gravity.RIGHT;
        return params;
    }

    public CoordinatorLayout.LayoutParams getBottomLeftParams(){
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams((metrics.getWidth()/6),(metrics.getWidth()/6));
        params.gravity = Gravity.BOTTOM | Gravity.LEFT;
        return params;
    }

    public CoordinatorLayout.LayoutParams getBottomRightParams(){
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams((metrics.getWidth()/6),(metrics.getWidth()/6));
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        return params;
    }

    public CoordinatorLayout.LayoutParams getHorseParams(){
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams((metrics.getWidth()/4)*2,(metrics.getHeight()/3)*2);
        params.gravity = Gravity.CENTER;
        return params;
    }

}

