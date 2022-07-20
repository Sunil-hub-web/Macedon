package in.co.macedon.extras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import in.co.macedon.activities.Login;


public class SessionManager {

    SharedPreferences sharedprefernce;
    SharedPreferences.Editor editor;

    Context context;
    int PRIVATE_MODE=0;

    private static final String PREF_NAME = "sharedcheckLogin";
    private static final String User_OTP = "userotp";
    private static final String USER_EMAIL = "useremail";
    private static final String USER_MOBILENO = "usermobile";
    private static final String USERID = "userid";
    private static final String USERNAME = "username";
    private static final String IS_LOGIN = "islogin";


    public SessionManager(Context context){

        this.context =  context;
        sharedprefernce = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedprefernce.edit();
    }

    public void setUserOTP(String id ){

        editor.putString(User_OTP,id);
        editor.commit();

    }

    public String getUserOTP(){

        return  sharedprefernce.getString(User_OTP,"DEFAULT");
    }

    public void setuserEmail(String email){

        editor.putString(USER_EMAIL,email);
        editor.commit();
    }

    public String getUserEmail(){

        return sharedprefernce.getString(USER_EMAIL,"DEFAULT");
    }

    public void setUserMobileNO(String mobileNO){

        editor.putString(USER_MOBILENO,mobileNO);
        editor.commit();
    }

    public String getUserMobileno(){

        return sharedprefernce.getString(USER_MOBILENO,"DEFAULT");

    }

    public void setUserId(String userid){

        editor.putString(USERID,userid);
        editor.commit();
    }

    public String getUserID(){

        return sharedprefernce.getString(USERID,"DEFAULT");

    }

    public void setUserName(String username){

        editor.putString(USERNAME,username);
        editor.commit();
    }

    public String getUserName(){

        return sharedprefernce.getString(USERNAME,"DEFAULT");

    }

    public Boolean isLogin(){
        return sharedprefernce.getBoolean(IS_LOGIN, false);

    }
    public void setLogin(){

        editor.putBoolean(IS_LOGIN, true);
        editor.commit();

    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);


    }

}
