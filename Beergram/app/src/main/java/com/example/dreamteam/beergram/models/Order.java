package com.example.dreamteam.beergram.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {

    private String mId;

    private String mReceiverFirstName;

    private String mReceiverLastName;

    private String mReceiverAddress;

    private String mProductPictureImageUrl;

    private BeerType mBeerType;

    private String mProductSize;

    private String mUserId;

    private boolean mIsReady;

    public Order() {

    }

    public Order(
            String id,
            String receiverFirstName,
            String receiverLastName,
            String receiverAddress,
            String productPictureImageUrl,
            BeerType beerType,
            String productSize,
            String userId,
            boolean isReady) {
        setmId(id);
        setmReceiverFirstName(receiverFirstName);
        setmReceiverLastName(receiverLastName);
        setmReceiverAddress(receiverAddress);
        setmProductPictureImageUrl(productPictureImageUrl);
        setmBeerType(beerType);
        setmProductSize(productSize);
        setmUserId(userId);
        setmIsReady(isReady);
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmReceiverFirstName() {
        return mReceiverFirstName;
    }

    public void setmReceiverFirstName(String mReceiverFirstName) {
        this.mReceiverFirstName = mReceiverFirstName;
    }

    public String getmReceiverLastName() {
        return mReceiverLastName;
    }

    public void setmReceiverLastName(String mReceiverLastName) {
        this.mReceiverLastName = mReceiverLastName;
    }

    public String getmReceiverAddress() {
        return mReceiverAddress;
    }

    public void setmReceiverAddress(String mReceiverAddress) {
        this.mReceiverAddress = mReceiverAddress;
    }

    public String getmProductPictureImageUrl() {
        return mProductPictureImageUrl;
    }

    public void setmProductPictureImageUrl(String mProductPictureImageUrl) {
        this.mProductPictureImageUrl = mProductPictureImageUrl;
    }

    public BeerType getmBeerType() {
        return mBeerType;
    }

    public void setmBeerType(BeerType mBeerType) {
        this.mBeerType = mBeerType;
    }

    public String getmProductSize() {
        return mProductSize;
    }

    public void setmProductSize(String mProductSize) {
        this.mProductSize = mProductSize;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public boolean getmIsReady() {
        return mIsReady;
    }

    public void setmIsReady(boolean mIsReady) {
        this.mIsReady = mIsReady;
    }

    protected Order(Parcel in) {
        mId = in.readString();
        mReceiverFirstName = in.readString();
        mReceiverLastName = in.readString();
        mReceiverAddress = in.readString();
        mProductPictureImageUrl = in.readString();
        mBeerType = (BeerType) in.readValue(BeerType.class.getClassLoader());
        mProductSize = in.readString();
        mUserId = in.readString();
        mIsReady = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mReceiverFirstName);
        dest.writeString(mReceiverLastName);
        dest.writeString(mReceiverAddress);
        dest.writeString(mProductPictureImageUrl);
        dest.writeValue(mBeerType);
        dest.writeString(mProductSize);
        dest.writeString(mUserId);
        dest.writeByte((byte) (mIsReady ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}