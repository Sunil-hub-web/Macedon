package in.co.macedon.models;

public class OfferModelClass {

    String coupon_code_id,name,code,city_id,discount_type,discount_value,valid_uo_to,used_up_to,no_of_use_user,
            price_cart,img;

    public OfferModelClass(String coupon_code_id, String name, String code, String city_id, String discount_type,
                           String discount_value, String valid_uo_to, String used_up_to, String no_of_use_user,
                           String price_cart, String img) {

        this.coupon_code_id = coupon_code_id;
        this.name = name;
        this.code = code;
        this.city_id = city_id;
        this.discount_type = discount_type;
        this.discount_value = discount_value;
        this.valid_uo_to = valid_uo_to;
        this.used_up_to = used_up_to;
        this.no_of_use_user = no_of_use_user;
        this.price_cart = price_cart;
        this.img = img;
    }

    public String getCoupon_code_id() {
        return coupon_code_id;
    }

    public void setCoupon_code_id(String coupon_code_id) {
        this.coupon_code_id = coupon_code_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public String getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(String discount_value) {
        this.discount_value = discount_value;
    }

    public String getValid_uo_to() {
        return valid_uo_to;
    }

    public void setValid_uo_to(String valid_uo_to) {
        this.valid_uo_to = valid_uo_to;
    }

    public String getUsed_up_to() {
        return used_up_to;
    }

    public void setUsed_up_to(String used_up_to) {
        this.used_up_to = used_up_to;
    }

    public String getNo_of_use_user() {
        return no_of_use_user;
    }

    public void setNo_of_use_user(String no_of_use_user) {
        this.no_of_use_user = no_of_use_user;
    }

    public String getPrice_cart() {
        return price_cart;
    }

    public void setPrice_cart(String price_cart) {
        this.price_cart = price_cart;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
