package in.co.macedon.models;

public class ReviewRatingModel {

    String rating_review_id,review,rating,customer_id,full_name,profile_image,service_master_id,service_master_name;

    public ReviewRatingModel(String rating_review_id, String review, String rating, String customer_id,
                             String full_name, String profile_image, String service_master_id,
                             String service_master_name) {

        this.rating_review_id = rating_review_id;
        this.review = review;
        this.rating = rating;
        this.customer_id = customer_id;
        this.full_name = full_name;
        this.profile_image = profile_image;
        this.service_master_id = service_master_id;
        this.service_master_name = service_master_name;
    }

    public String getRating_review_id() {
        return rating_review_id;
    }

    public void setRating_review_id(String rating_review_id) {
        this.rating_review_id = rating_review_id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getService_master_id() {
        return service_master_id;
    }

    public void setService_master_id(String service_master_id) {
        this.service_master_id = service_master_id;
    }

    public String getService_master_name() {
        return service_master_name;
    }

    public void setService_master_name(String service_master_name) {
        this.service_master_name = service_master_name;
    }
}
