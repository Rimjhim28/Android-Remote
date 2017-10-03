package com.example.android.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.myapplication.data.ActionContract;
import com.example.android.myapplication.data.ActionDbHelper;

import java.util.Date;

/**
 * Created by HP on 04-09-2017.
 */

public class Control extends AppCompatActivity {

    Button btnUp,btnLeft,btnRight,btnStop,btnViewData;
    Button btnDown;
    ActionDbHelper mDbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        btnUp = (Button) findViewById(R.id.button_up);
        btnDown = (Button) findViewById(R.id.button_down);
        btnLeft = (Button) findViewById(R.id.button_left);
        btnRight = (Button) findViewById(R.id.button_right);
        btnStop = (Button) findViewById(R.id.button_stop);
        btnViewData = (Button) findViewById(R.id.button_view_database);

        btnUp.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        insertData("UP button pressed");
                        //TODO : Implement the command to Arduino
                        return true;
                    case MotionEvent.ACTION_UP:
                        insertData("UP button released");
                        //TODO : Implement the command to Arduino
                        return true;
                }
                return false;
            }
        });

        btnDown.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        insertData("DOWN button pressed");
                        //TODO : Implement the command to Arduino
                        return true;
                    case MotionEvent.ACTION_UP:
                        insertData("DOWN button released");
                         //TODO : Implement the command to Arduino
                        return true;
                }
                return false;
            }
        });

        btnLeft.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        insertData("LEFT button pressed");
                         //TODO : Implement the command to Arduino
                        return true;
                    case MotionEvent.ACTION_UP:
                        insertData("LEFT button released");
                         //TODO : Implement the command to Arduino
                        return true;
                }
                return false;
            }
        });

        btnRight.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        insertData("RIGHT button pressed");
                         //TODO : Implement the command to Arduino
                        return true;
                    case MotionEvent.ACTION_UP:
                        insertData("RIGHT button released");
                         //TODO : Implement the command to Arduino
                        return true;
                }
                return false;
            }
        });

        btnStop.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        insertData("STOP button pressed");
                         //TODO : Implement the command to Arduino
                        return true;
                    case MotionEvent.ACTION_UP:
                        insertData("STOP button released");
                         //TODO : Implement the command to Arduino
                        return true;
                }
                return false;
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Control.this,ActionViewDatabase.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void insertData(String action){
        ContentValues values = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());

        values.put(ActionContract.ActionEntry.COLUMN_ACTION,action);
        values.put(ActionContract.ActionEntry.COLUMN_TIME,strDate);

        Uri newUri = getContentResolver().insert(ActionContract.ActionEntry.CONTENT_URI,values);
    }
}
