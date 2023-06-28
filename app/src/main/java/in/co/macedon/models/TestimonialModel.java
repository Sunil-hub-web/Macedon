package in.co.macedon.models;

public class TestimonialModel {

    String testimonial_id,name,image,message;

    public TestimonialModel(String testimonial_id, String name, String image, String message) {
        this.testimonial_id = testimonial_id;
        this.name = name;
        this.image = image;
        this.message = message;
    }

    public String getTestimonial_id() {
        return testimonial_id;
    }

    public void setTestimonial_id(String testimonial_id) {
        this.testimonial_id = testimonial_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
