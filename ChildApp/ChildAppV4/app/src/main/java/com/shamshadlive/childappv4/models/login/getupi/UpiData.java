
package com.shamshadlive.childappv4.models.login.getupi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UpiData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("last_loginDate")
    @Expose
    private String lastLoginDate;
    @SerializedName("forgotPasswordResetKey")
    @Expose
    private String forgotPasswordResetKey;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("upi_id")
    @Expose
    private String upiId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getForgotPasswordResetKey() {
        return forgotPasswordResetKey;
    }

    public void setForgotPasswordResetKey(String forgotPasswordResetKey) {
        this.forgotPasswordResetKey = forgotPasswordResetKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

}
