package com.example.android.myapplication.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.android.myapplication.Control;

import static android.R.attr.id;

/**
 * Created by HP on 10-09-2017.
 */

public class ActionProvider extends ContentProvider {

    private ActionDbHelper mDbHelper;
    public static final String LOG_TAG = ActionProvider.class.getName();
    private static final int ACTION = 100;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        sUriMatcher.addURI(ActionContract.CONTENT_AUTHORITY,ActionContract.PATH_ACTIONS,ACTION);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new ActionDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor = null;
        int match = sUriMatcher.match(uri);

        if(match == ACTION)
            cursor = database.query(ActionContract.ActionEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
        else
        {
            throw new IllegalArgumentException("Cannot query! Unknown URI:" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case ACTION:
                return ActionContract.ActionEntry.CONTENT_LIST_TYPE;
            default:
                throw new IllegalArgumentException("Unknown uri " + uri + "with match " +match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        long id;
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        if(match == ACTION) {
            id = database.insert(ActionContract.ActionEntry.TABLE_NAME, null, values);
        }
        else{
            throw new IllegalArgumentException("Cannot insert! Unknown URI:"+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        getContext().getContentResolver().notifyChange(uri, null);
        final int match = sUriMatcher.match(uri);
        if(match == ACTION)
            return database.delete(ActionContract.ActionEntry.TABLE_NAME,selection,selectionArgs);
        else
            throw new IllegalArgumentException("Deletion not supported");
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
