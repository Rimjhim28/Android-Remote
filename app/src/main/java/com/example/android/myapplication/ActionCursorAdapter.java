package com.example.android.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.myapplication.data.ActionContract;

/**
 * Created by HP on 10-09-2017.
 */

public class ActionCursorAdapter extends CursorAdapter {
    public ActionCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView actionTextView = (TextView) view.findViewById(R.id.text_action);
        TextView timeTextView  = (TextView) view.findViewById(R.id.text_time);

        int actionColumnIndex = cursor.getColumnIndex(ActionContract.ActionEntry.COLUMN_ACTION);
        String action = cursor.getString(actionColumnIndex);
        actionTextView.setText(action);

        int timeColumnIndex = cursor.getColumnIndex(ActionContract.ActionEntry.COLUMN_TIME);
        String time = cursor.getString(timeColumnIndex);
        timeTextView.setText(time);
    }
}
