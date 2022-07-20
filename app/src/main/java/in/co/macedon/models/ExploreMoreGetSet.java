package in.co.macedon.models;

public class ExploreMoreGetSet {

    int image;
    String name;

    public ExploreMoreGetSet(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public ExploreMoreGetSet setImage(int image) {
        this.image = image;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExploreMoreGetSet setName(String name) {
        this.name = name;
        return this;
    }
}
