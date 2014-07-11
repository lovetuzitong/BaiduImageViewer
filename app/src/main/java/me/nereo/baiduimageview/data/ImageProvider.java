package me.nereo.baiduimageview.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.sql.SQLException;
import java.util.HashMap;

import me.nereo.baiduimageview.vender.BaiduApi;

/**
 * Created by Administrator on 2014-07-09.
 */
public class ImageProvider extends ContentProvider {

    private DatabaseHelper mDbOpenHelper;
    private static final UriMatcher sUriMatcher;
    private static HashMap<String, String> sImageProjection;

    private static final int IMAGES = 1;
    private static final int IMAGE_ID = 2;


    static {
        // New instance
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(BaiduData.AUTHORITY, "images", IMAGES);
        sUriMatcher.addURI(BaiduData.AUTHORITY, "images/#", IMAGE_ID);

        sImageProjection = new HashMap<String, String>();
        sImageProjection.put(BaiduData.Images._ID, BaiduData.Images._ID);
        sImageProjection.put(BaiduData.Images.COLUMN_CONTENT, BaiduData.Images.COLUMN_CONTENT);
        sImageProjection.put(BaiduData.Images.COLUMN_TAG, BaiduData.Images.COLUMN_TAG);
        sImageProjection.put(BaiduData.Images.COLUMN_CACHE_DATE, BaiduData.Images.COLUMN_CACHE_DATE);
    }

    @Override
    public boolean onCreate() {
        mDbOpenHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //System.out.println("2:"+uri);
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(BaiduData.Images.TABLE_NAME);
        switch (sUriMatcher.match(uri)){
            case IMAGES:
                qb.setProjectionMap(sImageProjection);
                break;
            case IMAGE_ID:
                qb.setProjectionMap(sImageProjection);
                qb.appendWhere(
                        BaiduData.Images._ID + " = " + uri.getPathSegments().get(BaiduData.Images.IMAGE_ID_PATH_POSITION)
                );
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "+uri);
        }
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        Cursor c = qb.query(
                db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)){
            case IMAGES:
                return BaiduData.Images.CONTENT_TYPE;
            case IMAGE_ID:
                return BaiduData.Images.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI: "+uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        if (sUriMatcher.match(uri) != IMAGES) {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        ContentValues values;
        if (contentValues != null) {
            values = new ContentValues(contentValues);
        } else {
            values = new ContentValues();
        }

        Long now = Long.valueOf(System.currentTimeMillis());
        if (values.containsKey(BaiduData.Images.COLUMN_CACHE_DATE) == false) {
            values.put(BaiduData.Images.COLUMN_CACHE_DATE, now);
        }

        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        long rowId = db.insert(BaiduData.Images.TABLE_NAME, BaiduData.Images.COLUMN_CONTENT, values);
        if (rowId > 0) {
            Uri imageUri = ContentUris.withAppendedId(BaiduData.Images.CONTENT_ID_URI_BASE, rowId);
            getContext().getContentResolver().notifyChange(imageUri, null);
            return imageUri;
        }

        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        String finalWhere;
        int count;
        switch (sUriMatcher.match(uri)){
            case IMAGES:
                count = db.delete(
                        BaiduData.Images.TABLE_NAME,
                        s,
                        strings);
                break;
            case IMAGE_ID:
                finalWhere =
                        BaiduData.Images._ID + " = " + uri.getPathSegments().get(BaiduData.Images.IMAGE_ID_PATH_POSITION);
                if(s != null){
                    finalWhere = finalWhere + " AND " + s;
                }
                count = db.delete(
                        BaiduData.Images.TABLE_NAME,
                        finalWhere,
                        strings
                );
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "+uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        int count;
        String finalWhere;
        switch (sUriMatcher.match(uri)){
            case IMAGES:
                count = db.update(
                        BaiduData.Images.TABLE_NAME,
                        contentValues,
                        s,
                        strings);
                break;
            case IMAGE_ID:
                finalWhere =
                        BaiduData.Images._ID + " = " + uri.getPathSegments().get(BaiduData.Images.IMAGE_ID_PATH_POSITION);
                if(s != null){
                    finalWhere = finalWhere + " AND " + s;
                }
                count = db.update(
                        BaiduData.Images.TABLE_NAME,
                        contentValues,
                        finalWhere,
                        strings
                );
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "+uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    static class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context) {
            super(context, BaiduData.DATABASE_NAME, null, BaiduData.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            BaiduData.Images.TABLE.create(sqLiteDatabase);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            BaiduData.Images.TABLE.delete(sqLiteDatabase);
            onCreate(sqLiteDatabase);
        }
    }

}
