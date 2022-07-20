package in.co.macedon.models;

public class FitnessFilterGetSet {

    int image;
    String name;

    public FitnessFilterGetSet(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public FitnessFilterGetSet setImage(int image) {
        this.image = image;
        return this;
    }

    public String getName() {
        return name;
    }

    public FitnessFilterGetSet setName(String name) {
        this.name = name;
        return this;
    }
}
