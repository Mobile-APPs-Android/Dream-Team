package com.example.dreamteam.beergram.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Position implements Parcelable {
    private double mLongtitude;
    private double mLatitude;

    public Position(double longtitude, double latitude) {
        setmLongtitude(longtitude);
        setmLatitude(latitude);
    }

    public double getmLongtitude() {
        return mLongtitude;
    }

    public void setmLongtitude(double mLongtitude) {
        this.mLongtitude = mLongtitude;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    protected Position(Parcel in) {
        mLongtitude = in.readDouble();
        mLatitude = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(mLongtitude);
        dest.writeDouble(mLatitude);
    }

    @SuppressWarnings("unused")
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
