package laboratorio2016.github.com.ensilladovaccatittarelli.clases;


/**
 * Created by Gonzalo on 12/12/2016.
 */

public class ElementHorse {
    private int image;
    private int imageHorse;
    private int order;
    private int sound;

    public ElementHorse(int image, int imageHorse, int order, int sound) {
        this.image = image;
        this.imageHorse = imageHorse;
        this.order = order;
        this.sound = sound;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getImageHorse() {
        return imageHorse;
    }

    public void setImageHorse(int imageHorse) {
        this.imageHorse = imageHorse;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElementHorse that = (ElementHorse) o;

        return order == that.order;

    }

    @Override
    public int hashCode() {
        return order;
    }
}
