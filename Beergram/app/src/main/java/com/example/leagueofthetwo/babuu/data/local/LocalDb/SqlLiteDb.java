package com.example.leagueofthetwo.babuu.data.local.LocalDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.leagueofthetwo.babuu.models.Order;
import com.example.leagueofthetwo.babuu.models.ProductType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SqlLiteDb extends SQLiteOpenHelper implements ILocalDbRepository {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "babuuDb";

    private static final String ORDERS_TABLE_NAME = "Orders";

    // ORDER TABLE COLOUMNS
    private static final String KEY_ID = "id";
    private static final String KEY_RECEIVER_FIRST_NAME = "ReceiverFirstName";
    private static final String KEY_RECEIVER_LAST_NAME = "ReceiverLastName";
    private static final String KEY_RECEIVER_ADDRESS = "ReceiverAddress";
    private static final String KEY_PRODUCT_PICTURE_URL = "PictureUrl";
    private static final String KEY_PRODUCT_TYPE = "ProductType";
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

    @Override
    public void addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        this.addSingleOrder(db, values, order);

        db.close();
    }

    @Override
    public Order getOrderById(String id) {
        return this.getOrderByParam(KEY_ID, id);
    }

    @Override
    public Order getOrderByUserId(String userId) {
        return this.getOrderByParam(KEY_USER_ID, userId);
    }

    @Override
    public List<Order> getAllOrders() {
        SQLiteDatabase db = this.getWritableDatabase();

        String rawQuery = ("SELECT * FROM " + ORDERS_TABLE_NAME);
        Cursor allOrdersCursor = db.rawQuery(rawQuery, null);

        List<Order> orders = new ArrayList<>();

        if (allOrdersCursor != null) {
            while (allOrdersCursor.moveToNext()) {
                orders.add(this.mapCursorToOrder(allOrdersCursor));
            }

            allOrdersCursor.close();
            db.close();

            return orders;
        }

        db.close();
        return null;
    }

    @Override
    public void cleanOrders() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(DROP_SQL_ORDERS_STATEMENT);
        db.execSQL(CREATE_SQL_ORDERS_STATEMENT);

        db.close();
    }

    @Override
    public void addManyOrders(Order[] orders) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();

            for (Order order : orders) {
                this.addSingleOrder(db, values, order);
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    private void addSingleOrder(SQLiteDatabase db, ContentValues values, Order order) {
        values.put(KEY_ID, order.getmId());
        values.put(KEY_RECEIVER_FIRST_NAME, order.getmReceiverFirstName());
        values.put(KEY_RECEIVER_LAST_NAME, order.getmReceiverLastName());
        values.put(KEY_RECEIVER_ADDRESS, order.getmReceiverAddress());
        values.put(KEY_PRODUCT_PICTURE_URL, order.getmProductPictureImageUrl());
        values.put(KEY_PRODUCT_TYPE, order.getmProductType().toString());
        values.put(KEY_PRODUCT_SIZE, order.getmProductSize());
        values.put(KEY_USER_ID, order.getmUserId());
        values.put(KEY_IS_READY, order.getmIsReady());

        db.insert(ORDERS_TABLE_NAME, null, values);
    }

    @Nullable
    private Order getOrderByParam(String paramKey, String paramValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorIterators = db.query(
                ORDERS_TABLE_NAME,
                new String[]{
                        KEY_ID,
                        KEY_RECEIVER_FIRST_NAME,
                        KEY_RECEIVER_LAST_NAME,
                        KEY_RECEIVER_ADDRESS,
                        KEY_PRODUCT_PICTURE_URL,
                        KEY_PRODUCT_TYPE,
                        KEY_PRODUCT_SIZE,
                        KEY_USER_ID,
                        KEY_IS_READY},
                paramKey + "=?",
                new String[]{paramValue},
                null,
                null,
                null,
                null);

        if (cursorIterators != null) {
            cursorIterators.moveToFirst();

            Order foundOrder = this.mapCursorToOrder(cursorIterators);

            cursorIterators.close();
            db.close();
            return foundOrder;
        } else {
            db.close();
            return null;
        }
    }

    private Order mapCursorToOrder(Cursor cursorIterator) {
        Order order = new Order(
                cursorIterator.getString(0),                        // ID
                cursorIterator.getString(1),                        // First name
                cursorIterator.getString(2),                        // Last name
                cursorIterator.getString(3),                        // Address
                cursorIterator.getString(4),                        // Picture url
                ProductType.valueOf(cursorIterator.getString(5)),   // Product type
                cursorIterator.getString(6),                        // Product size
                cursorIterator.getString(7),                        // User id
                Boolean.parseBoolean(cursorIterator.getString(8))); // Is ready

        return order;
    }
}
