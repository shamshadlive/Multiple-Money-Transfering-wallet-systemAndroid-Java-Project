
package com.shamshadlive.childappv4.models.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("log_id")
    @Expose
    private Integer logId;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("profilepicurl")
    @Expose
    private String profilepicurl;
    @SerializedName("adharnumber")
    @Expose
    private Integer adharnumber;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("dateofbirth")
    @Expose
    private String dateofbirth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProfilepicurl() {
        return profilepicurl;
    }

    public void setProfilepicurl(String profilepicurl) {
        this.profilepicurl = profilepicurl;
    }

    public Integer getAdharnumber() {
        return adharnumber;
    }

    public void setAdharnumber(Integer adharnumber) {
        this.adharnumber = adharnumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

}
