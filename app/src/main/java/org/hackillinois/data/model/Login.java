package org.hackillinois.data.model;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Stephen on 1/26/2015.
 */
public class Login {

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
