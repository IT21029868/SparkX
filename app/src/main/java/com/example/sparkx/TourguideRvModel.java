package com.example.sparkx;

import android.os.Parcel;
import android.os.Parcelable;

public class TourguideRvModel implements Parcelable {

    private String name;
    private String cid;
    private String phoneNo;
    private String gmail;
    private String guideid;

    public TourguideRvModel(){

    }

    public TourguideRvModel(String name, String cid, String phoneNo, String gmail, String guideid) {
        this.name = name;
        this.cid = cid;
        this.phoneNo = phoneNo;
        this.gmail = gmail;
        this.guideid = guideid;
    }


    protected TourguideRvModel(Parcel in) {
        name = in.readString();
        cid = in.readString();
        phoneNo = in.readString();
        gmail = in.readString();
        guideid = in.readString();
    }

    public static final Creator<TourguideRvModel> CREATOR = new Creator<TourguideRvModel>() {
        @Override
        public TourguideRvModel createFromParcel(Parcel in) {
            return new TourguideRvModel(in);
        }

        @Override
        public TourguideRvModel[] newArray(int size) {
            return new TourguideRvModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getGuideid() {
        return guideid;
    }

    public void setGuideid(String guideid) {
        this.guideid = guideid;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(cid);
        parcel.writeString(phoneNo);
        parcel.writeString(gmail);
        parcel.writeString(guideid);
    }
}
