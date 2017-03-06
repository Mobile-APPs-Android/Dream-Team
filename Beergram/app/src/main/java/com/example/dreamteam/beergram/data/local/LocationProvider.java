package com.example.dreamteam.beergram.data.local;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.dreamteam.beergram.models.Position;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class LocationProvider implements ILocationProvider, GoogleApiClient.ConnectionCallbacks {
    private static final double SOFIA_LATITUDE = 42.696552;
    private static final double SOFIA_LONGITUDE = 23.32601;

    private final Context mContext;
    private final Geocoder mGeocoder;
    private final GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    @Inject
    public LocationProvider(Context context) {
        mContext = context;
        mGeocoder = new Geocoder(mContext, Locale.getDefault());

        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(connectionResult -> {
                })
                .addApi(LocationServices.API)
                .build();

    }

    public String getAddress(Position position) {
        List<Address> addresses = new ArrayList<>();
        try {
            addresses = mGeocoder.getFromLocation(position.getmLatitude(), position.getmLongtitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder result = new StringBuilder();

        if (addresses.size() > 0) {
            result.append(addresses.get(0).getCountryName() == null ? "" : addresses.get(0).getCountryName() + ", ");
            result.append(addresses.get(0).getLocality() == null ? "" : addresses.get(0).getLocality() + ", ");
            result.append(addresses.get(0).getSubLocality() == null ? "" : addresses.get(0).getSubLocality() + ", ");
            result.append(addresses.get(0).getThoroughfare() == null ? "" : addresses.get(0).getThoroughfare() + ", ");
            result.append(addresses.get(0).getSubThoroughfare() == null ? "" : addresses.get(0).getSubThoroughfare());
        } else {
            result.append("No address for this position");
        }

        return result.toString();
    }

    @Override
    public Position getCurrentPosition() {
        Position currentPosition;
        if (mLastLocation != null) {
            currentPosition = new Position(mLastLocation.getLongitude(), mLastLocation.getLatitude());
        } else {
            currentPosition = new Position(SOFIA_LONGITUDE, SOFIA_LATITUDE);
        }

        return currentPosition;
    }

    @Override
    public void connectLocationListener() {
        mGoogleApiClient.connect();
    }

    @Override
    public void disconnectLocationListener() {
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) throws SecurityException {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
