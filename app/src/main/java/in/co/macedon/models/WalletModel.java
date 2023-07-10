package in.co.macedon.models;

public class WalletModel {

    String wallet_id,user_id,amount,payment_type,wallet_status,remark,created_date;

    public WalletModel(String wallet_id, String user_id, String amount, String payment_type,
                       String wallet_status, String remark,String created_date) {
        this.wallet_id = wallet_id;
        this.user_id = user_id;
        this.amount = amount;
        this.payment_type = payment_type;
        this.wallet_status = wallet_status;
        this.remark = remark;
        this.created_date = created_date;
    }

    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getWallet_status() {
        return wallet_status;
    }

    public void setWallet_status(String wallet_status) {
        this.wallet_status = wallet_status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
