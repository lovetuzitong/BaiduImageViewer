package me.nereo.baiduimageview.data;

import android.net.Uri;
import android.provider.BaseColumns;

import me.nereo.baiduimageview.util.database.Column;
import me.nereo.baiduimageview.util.database.SQLiteTable;

/**
 * Created by Administrator on 2014-07-09.
 */
public class BaiduData {

    public static final String AUTHORITY = "me.nereo.provider.BAIDU_DATA";

    public static final String DATABASE_NAME = "baidu_data.db";
    public static final int DATABASE_VERSION = 1;

    public static abstract class Images implements BaseColumns{

        // Column Define
        public static final String TABLE_NAME = "baidu_image";
        public static final String COLUMN_CONTENT = "json";
        public static final String COLUMN_TAG = "tag";
        public static final String COLUMN_CACHE_DATE = "cached";
        public static final String COLUMN_SORT = "sort";

        public static final SQLiteTable TABLE = new SQLiteTable(TABLE_NAME)
                .addColumn(COLUMN_CONTENT, Column.DataType.TEXT)
                .addColumn(COLUMN_TAG, Column.DataType.TEXT)
                .addColumn(COLUMN_CACHE_DATE, Column.DataType.INTEGER)
                .addColumn(COLUMN_SORT, Column.DataType.INTEGER);

        //Content provider defines
        private static final String SCHEME = "content://";
        private static final String PATH_IMAGES = "/images";
        private static final String PATH_IMAGE_ID = "/images/";

        public static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + PATH_IMAGES);
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_IMAGE_ID);
        public static final Uri CONTENT_ID_URI_PATTERN = Uri.parse(SCHEME + AUTHORITY + PATH_IMAGE_ID + "/#");

        public static final int IMAGE_ID_PATH_POSITION = 1;

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.nereo.image";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.nereo.image";

    }

}
