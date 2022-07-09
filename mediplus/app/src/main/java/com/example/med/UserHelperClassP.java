package com.example.med;

public class UserHelperClassP {
    String Patient_fullname;
    String Patient_phoneno;
    String Patient_username;
    String Patient_password;

    public UserHelperClassP() {

    }

    public UserHelperClassP(String Patient_fullname, String Patient_username, String Patient_password, String Patient_phoneno) {
        this.Patient_fullname = Patient_fullname;
        this.Patient_phoneno = Patient_phoneno;
        this.Patient_username = Patient_username;
        this.Patient_password =Patient_password;

    }

    public String getfullname() {
        return Patient_fullname;
    }

    public void setfullname(String Patient_fullname) {
        this.Patient_fullname = Patient_fullname;
    }


    public String getPhoneno() {
        return Patient_phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.Patient_phoneno = Patient_phoneno;
    }

    public String getUsername() {
        return Patient_username;
    }

    public void setUsername(String username) {
        this.Patient_username = Patient_username;
    }

    public String getPassword() {
        return Patient_password;
    }

    public void setPassword(String passwordP) {
        this.Patient_password = Patient_password;
    }

}
