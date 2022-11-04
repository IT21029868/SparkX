package com.example.sparkx;

import android.os.Parcel;
import android.os.Parcelable;

public class HotelRvModel implements Parcelable {

    private String hotelName;
    private String description;
    private String price;
    private String image;
    private String hotelId;

    public HotelRvModel(String hotelName, String description, String price, String image, String hotelId) {
        this.hotelName = hotelName;
        this.description = description;
        this.price = price;
        this.image = image;
        this.hotelId = hotelId;
    }
    private HotelRvModel(){

    }

    protected HotelRvModel(Parcel in) {
        hotelName = in.readString();
        description = in.readString();
        price = in.readString();
        image = in.readString();
        hotelId = in.readString();
    }

    public static final Creator<HotelRvModel> CREATOR = new Creator<HotelRvModel>() {
        @Override
        public HotelRvModel createFromParcel(Parcel in) {
            return new HotelRvModel(in);
        }

        @Override
        public HotelRvModel[] newArray(int size) {
            return new HotelRvModel[size];
        }
    };

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(hotelName);
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeString(image);
        parcel.writeString(hotelId);
    }
}


