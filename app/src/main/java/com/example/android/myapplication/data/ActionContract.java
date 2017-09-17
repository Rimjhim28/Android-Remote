

package com.example.android.myapplication.data;
import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by HP on 24-06-2017.
 */

public class ActionContract {

    //Setting up the Uri
    public static final String CONTENT_AUTHORITY = "com.example.android.myapplication";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_ACTIONS = "actions";

    public static abstract class ActionEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_ACTIONS);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ACTIONS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +PATH_ACTIONS;

        public static final String TABLE_NAME = "actions";
        public static final String _ID = "_id";
        public static final String COLUMN_ACTION = "actions";
        public static final String COLUMN_TIME = "time";
    }
}

