package com.example.reminderapp;

public class Person {
    String prsnid;
    String prsnname;
    String prsneid;
    String prsnpass;
    public Person(){

    }
    public Person(String prsnid,String prsnname,String prsneid,String prsnpass){
        this.prsnid=prsnid;
        this.prsnname=prsnname;
        this.prsneid=prsneid;
        this.prsnpass=prsnpass;

    }

    public String getPrsnid() {
        return prsnid;
    }

    public String getPrsnname() {
        return prsnname;
    }

    public String getPrsneid() {
        return prsneid;
    }

    public String getPrsnpass() {
        return prsnpass;
    }
}

