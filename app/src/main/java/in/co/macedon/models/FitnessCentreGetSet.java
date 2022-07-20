package in.co.macedon.models;

public class FitnessCentreGetSet {

    int image;
    String name, address, rating, review, ratingcount, timing, gym, zumba, yoga;

    public FitnessCentreGetSet(int image, String name, String address, String rating, String review, String ratingcount, String timing, String gym, String zumba, String yoga) {
        this.image = image;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.review = review;
        this.ratingcount = ratingcount;
        this.timing = timing;
        this.gym = gym;
        this.zumba = zumba;
        this.yoga = yoga;
    }

    public int getImage() {
        return image;
    }

    public FitnessCentreGetSet setImage(int image) {
        this.image = image;
        return this;
    }

    public String getName() {
        return name;
    }

    public FitnessCentreGetSet setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public FitnessCentreGetSet setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public FitnessCentreGetSet setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public String getReview() {
        return review;
    }

    public FitnessCentreGetSet setReview(String review) {
        this.review = review;
        return this;
    }

    public String getRatingcount() {
        return ratingcount;
    }

    public FitnessCentreGetSet setRatingcount(String ratingcount) {
        this.ratingcount = ratingcount;
        return this;
    }

    public String getTiming() {
        return timing;
    }

    public FitnessCentreGetSet setTiming(String timing) {
        this.timing = timing;
        return this;
    }

    public String getGym() {
        return gym;
    }

    public FitnessCentreGetSet setGym(String gym) {
        this.gym = gym;
        return this;
    }

    public String getZumba() {
        return zumba;
    }

    public FitnessCentreGetSet setZumba(String zumba) {
        this.zumba = zumba;
        return this;
    }

    public String getYoga() {
        return yoga;
    }

    public FitnessCentreGetSet setYoga(String yoga) {
        this.yoga = yoga;
        return this;
    }
}
