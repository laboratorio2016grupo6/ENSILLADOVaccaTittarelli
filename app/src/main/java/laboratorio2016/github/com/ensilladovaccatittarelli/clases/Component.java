package laboratorio2016.github.com.ensilladovaccatittarelli.clases;

import android.widget.ImageView;

import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * Created by ignacio on 13/02/17.
 */

public class Component {
    private CircularImageView view;
    private ElementHorse elementHorse;

    public Component(CircularImageView view, ElementHorse elementHorse) {
        this.view = view;
        this.elementHorse = elementHorse;
    }

    public CircularImageView getView() {
        return view;
    }

    public void setView(CircularImageView view) {
        this.view = view;
    }

    public ElementHorse getElementHorse() {
        return elementHorse;
    }

    public void setElementHorse(ElementHorse elementHorse) {
        this.elementHorse = elementHorse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;

        if (view != null ? !view.equals(component.view) : component.view != null) return false;
        return elementHorse != null ? elementHorse.equals(component.elementHorse) : component.elementHorse == null;

    }

    @Override
    public int hashCode() {
        int result = view != null ? view.hashCode() : 0;
        result = 31 * result + (elementHorse != null ? elementHorse.hashCode() : 0);
        return result;
    }
}
