package com.example.sparkx;

public class GuideBookings {

    String name2,email2,date2,day,dest;

    public GuideBookings(){}

    public GuideBookings(String name2, String email2, String date2, String day, String dest) {
        this.name2 = name2;
        this.email2 = email2;
        this.date2 = date2;
        this.day = day;
        this.dest = dest;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }
}
