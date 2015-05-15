package com.example.ankit.myproject;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ankit on 15-05-2015.
 */
public class MyCursorAdaptor extends CursorAdapter {
    public MyCursorAdaptor(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.imagelayout, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView messageID = (TextView) view.findViewById(R.id.message_id);
        TextView messageType = (TextView) view.findViewById(R.id.message_type);
        ImageView messageDataImage = (ImageView) view.findViewById(R.id.message_dataimage);
        TextView messageDataText = (TextView) view.findViewById(R.id.message_datatext);
        TextView messageTime = (TextView) view.findViewById(R.id.message_time);
        // Extract properties from cursor
        String id = cursor.getString(cursor.getColumnIndexOrThrow("MESSAGE_ID"));
        String type = cursor.getString(cursor.getColumnIndexOrThrow("MESSAGE_TYPE"));
        if(type == "0")
        {
            String data = cursor.getString(cursor.getColumnIndexOrThrow("MESSAGE_DATA"));
            messageDataText.setText(data);
        }
        else
        {
            try{
            String url = cursor.getString(cursor.getColumnIndexOrThrow("MESSAGE_DATA"));
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            messageDataImage.setImageBitmap(bitmap);
            }
            catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        }
        String time = cursor.getString(cursor.getColumnIndexOrThrow("MESSAGE_TIMESTAMP"));
        // Populate fields with extracted properties
        messageID.setText(id);
        messageType.setText(type);
        messageTime.setText(time);
    }

}
