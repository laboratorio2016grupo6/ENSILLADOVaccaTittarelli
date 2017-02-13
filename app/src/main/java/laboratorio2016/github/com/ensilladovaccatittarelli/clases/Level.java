package laboratorio2016.github.com.ensilladovaccatittarelli.clases;

/**
 * Created by Gonzalo on 12/2/2017.
 */

public enum Level {
    INICIAL(0, 1, "Inicial"),
    MEDIO(1, 2, "Medio"),
    AVANZADO(2, 4, "Avanzado"),
    EXPERTO(3, 6, "Experto");

    private Integer id;
    private Integer elements;
    private String description;

    Level(Integer id, Integer elements, String description){
        this.id = id;
        this.elements = elements;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setElements(Integer elements) {
        this.elements = elements;
    }

    public Integer getId() {
        return id;
    }

    public Integer getElements() {
        return elements;
    }
}
