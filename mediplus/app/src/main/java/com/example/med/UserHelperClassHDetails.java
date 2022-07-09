package com.example.med;

public class UserHelperClassHDetails {
    String name;
    String add;
    String phone;
    String web;
    String email;
    String doc1;
    String doc2;
    String doc3;
    String e1;
    String e2;

    public UserHelperClassHDetails()
    {

    }

    public UserHelperClassHDetails( String name, String add,String phone,String web,String email,String doc1,String doc2,String doc3,String e1,String e2)
    {
        this.name=name;
        this.add=add;
        this.phone=phone;
        this.web=web;
        this.email=email;
        this.doc1=doc1;
        this.doc2=doc2;
        this.doc3=doc3;
        this.e1=e1;
        this.e2=e2;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDoc1() {
        return doc1;
    }

    public void setDoc1(String doc1) {
        this.doc1 = doc1;
    }

    public String getDoc2() {
        return doc2;
    }

    public void setDoc2(String doc2) {
        this.doc2 = doc2;
    }

    public String getDoc3() {
        return doc3;
    }

    public void setDoc3(String doc3) {
        this.doc3 = doc3;
    }

    public String getE1() {
        return e1;
    }

    public void setE1(String e1) {
        this.e1 = e1;
    }

    public String getE2() {
        return e2;
    }

    public void setE2(String e2) {
        this.e2 = e2;
    }
}
