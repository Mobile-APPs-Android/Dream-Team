package com.example.dreamteam.beergram.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Position implements Parcelable {
    private double longtitude;
    private double latitude;

    public Position(double longtitude, double latitude) {
        this.setmLongtitude(longtitude);
        this.setmLatitude(latitude);
    }

    public double getmLongtitude() {
        return this.longtitude;
    }

    public void setmLongtitude(double mLongtitude) {
        this.longtitude = mLongtitude;
    }

    public double getmLatitude() {
        return this.latitude;
    }

    public void setmLatitude(double mLatitude) {
        this.latitude = mLatitude;
    }

    protected Position(Parcel in) {
        this.longtitude = in.readDouble();
        this.latitude = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.longtitude);
        dest.writeDouble(this.latitude);
    }

    public static final Parcelable.Creator<Position> CREATOR = new Parcelable.Creator<Position>() {
        @Override
        public Position createFromParcel(Parcel in) {
            return new Position(in);
        }

        @Override
        public Position[] newArray(int size) {
            return new Position[size];
        }
    };
}
