package com.example.med;

public class UserHelperClassH {
    String Hospital_fullname;
    String Hospital_phoneno;
    String Hospital_username;
    String Hospital_password;

    public UserHelperClassH() {

    }

    public UserHelperClassH(String Hospital_fullname, String Hospital_username, String Hospital_password, String Hospital_phoneno) {
        this.Hospital_fullname = Hospital_fullname;
        this.Hospital_phoneno = Hospital_phoneno;
        this.Hospital_username = Hospital_username;
        this.Hospital_password =Hospital_password;

    }

    public String getfullname() {
        return Hospital_fullname;
    }

    public void setfullname(String Hospital_fullname) {
        this.Hospital_fullname = Hospital_fullname;
    }


    public String getPhoneno() {
        return Hospital_phoneno;
    }

    public void setPhoneno(String Hospital_phoneno) {
        this.Hospital_phoneno = Hospital_phoneno;
    }

    public String getUsername() {
        return Hospital_username;
    }

    public void setUsername(String Hospital_username) {
        this.Hospital_username = Hospital_username;
    }

    public String getPassword() {
        return Hospital_password;
    }

    public void setPassword(String Hospital_password) {
        this.Hospital_password = Hospital_password;
    }




}
