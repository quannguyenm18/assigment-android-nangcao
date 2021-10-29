package com.example.assigmentandroidnc.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assigmentandroidnc.DAO.KhoaHocDAO;
import com.example.assigmentandroidnc.model.KhoaHoc;

@Database(entities = {KhoaHoc.class},version = 1)
public abstract class KhoaHocDatabase extends RoomDatabase {

    private  static  final  String DBName="user.db";
    private  static KhoaHocDatabase instance;

    public static synchronized KhoaHocDatabase getInstance(Context context){

        if(instance==null){

            instance= Room.databaseBuilder(context.getApplicationContext(),
                    KhoaHocDatabase.class,DBName).allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract KhoaHocDAO KhoaHocDAO();
}
