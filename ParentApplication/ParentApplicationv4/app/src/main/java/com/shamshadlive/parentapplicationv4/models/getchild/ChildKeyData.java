
package com.shamshadlive.parentapplicationv4.models.getchild;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ChildKeyData {

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
    private String adharnumber;
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

    public String getAdharnumber() {
        return adharnumber;
    }

    public void setAdharnumber(String adharnumber) {
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
