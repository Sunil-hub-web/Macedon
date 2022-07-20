package in.co.macedon.models;

public class TrainerGetSet {

    int image;
    String name, post, speciality;

    public TrainerGetSet(int image, String name, String post, String speciality) {
        this.image = image;
        this.name = name;
        this.post = post;
        this.speciality = speciality;
    }

    public int getImage() {
        return image;
    }

    public TrainerGetSet setImage(int image) {
        this.image = image;
        return this;
    }

    public String getName() {
        return name;
    }

    public TrainerGetSet setName(String name) {
        this.name = name;
        return this;
    }

    public String getPost() {
        return post;
    }

    public TrainerGetSet setPost(String post) {
        this.post = post;
        return this;
    }

    public String getSpeciality() {
        return speciality;
    }

    public TrainerGetSet setSpeciality(String speciality) {
        this.speciality = speciality;
        return this;
    }
}
