package com.example.dreamteam.beergram.data.local.LocalDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Inject;

public class SqlLiteDb extends SQLiteOpenHelper implements ILocalDbRepository {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Beergramdb";

    private static final String ORDERS_TABLE_NAME = "Orders";

    // ORDER TABLE COLOUMNS
    private static final String KEY_ID = "id";
    private static final String KEY_RECEIVER_FIRST_NAME = "ReceiverFirstName";
    private static final String KEY_RECEIVER_LAST_NAME = "ReceiverLastName";
    private static final String KEY_RECEIVER_ADDRESS = "ReceiverAddress";
    private static final String KEY_PRODUCT_PICTURE_URL = "PictureUrl";
    private static final String KEY_PRODUCT_TYPE = "BeerType";
    private static final String KEY_PRODUCT_SIZE = "ProductSize";
    private static final String KEY_USER_ID = "UserId";
    private static final String KEY_IS_READY = "IsReady";

    // SQL STATEMENTS
    private static String CREATE_SQL_ORDERS_STATEMENT = " CREATE TABLE IF NOT EXISTS " + ORDERS_TABLE_NAME + "(" +
            KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_RECEIVER_FIRST_NAME + " TEXT, " +
            KEY_RECEIVER_LAST_NAME + " TEXT, " +
            KEY_RECEIVER_ADDRESS + " TEXT, " +
            KEY_PRODUCT_PICTURE_URL + " TEXT, " +
            KEY_PRODUCT_TYPE + " TEXT, " +
            KEY_PRODUCT_SIZE + " TEXT, " +
            KEY_USER_ID + " TEXT, " +
            KEY_IS_READY + " BOOLEAN" + " )";

    private static String DROP_SQL_ORDERS_STATEMENT = "DROP TABLE IF EXISTS " + ORDERS_TABLE_NAME + "; ";

    @Inject
    public SqlLiteDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL_ORDERS_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
