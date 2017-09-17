package com.example.android.myapplication;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.android.myapplication.data.ActionContract;
import com.example.android.myapplication.data.ActionDbHelper;

public class ActionViewDatabase extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int ACTION_LOADER = 0;
    ActionCursorAdapter mAdapter;
    ActionDbHelper mDbhelper = new ActionDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_view_database);

        ListView dataListView = (ListView) findViewById(R.id.list_view_database);
        mAdapter = new ActionCursorAdapter(this,null);
        dataListView.setAdapter(mAdapter);
        getLoaderManager().initLoader(ACTION_LOADER,null,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_view_database,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.clear_data){
            int rowsDeleted = getContentResolver().delete(ActionContract.ActionEntry.CONTENT_URI, null, null);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {ActionContract.ActionEntry._ID, ActionContract.ActionEntry.COLUMN_ACTION, ActionContract.ActionEntry.COLUMN_TIME};
        return new CursorLoader(this,                //Parent activity context
                ActionContract.ActionEntry.CONTENT_URI,    //Provider content uri to query
                projection,                          //Columns to include in the resulting cursor
                null,                                //No selection clause
                null,                                //No selection Arguments
                null);     }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
