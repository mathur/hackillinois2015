package org.hackillinois.data.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Stephen on 1/26/2015.
 */
@Parcel
public class User extends ApiItem {

    @SerializedName("first_name")
    String firstName;

    @SerializedName("last_name")
    String lastName;

    @SerializedName("email")
    String email;

    @SerializedName("phone")
    String phoneNumber;

    @SerializedName("code")
    String code;

    @SerializedName("type")
    String type;

    @SerializedName("school")
    String school;

    @SerializedName("major")
    String major;

    @SerializedName("vegetarian")
    boolean isVegetarian;

    @SerializedName("vegan")
    boolean isVegan;

    @SerializedName("gluten")
    boolean isGluten;

    @SerializedName("other_diet")
    String otherDiet;

    @SerializedName("hardware_equipment")
    String hardwareEquipment;

    @SerializedName("hardware")
    boolean hasHardware;

    @SerializedName("team")
    String team;

    @SerializedName("url_github")
    String githubUrl;

    @SerializedName("url_website")
    String websiteUrl;

    @SerializedName("url_linkedin")
    String linkedInUrl;

    @SerializedName("graduation_year")
    String universityClass;

    @SerializedName("shirt")
    String shirt;

    @SerializedName("contact_first_name")
    String contactFirstName;

    @SerializedName("contact_last_name")
    String contactLastName;

    @SerializedName("contact_email")
    String contactEmail;

    @SerializedName("contact_phone")
    String contactPhoneNumber;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getType() {
        return type;
    }

    public String getSchool() {
        return school;
    }

    public String getMajor() {
        return major;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public boolean isGluten() {
        return isGluten;
    }

    public String getOtherDiet() {
        return otherDiet;
    }

    public String getHardwareEquipment() {
        return hardwareEquipment;
    }

    public boolean hasHardware() {
        return hasHardware;
    }

    public String getTeam() {
        return team;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getLinkedInUrl() {
        return linkedInUrl;
    }

    public String getUniversityClass() {
        return universityClass;
    }

    public String getShirt() {
        return shirt;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactName() {
        return contactFirstName + " " + contactLastName;
    }
}
