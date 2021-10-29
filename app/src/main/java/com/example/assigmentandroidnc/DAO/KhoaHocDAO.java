package com.example.assigmentandroidnc.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assigmentandroidnc.model.KhoaHoc;

import java.util.List;

@Dao
public interface  KhoaHocDAO {
    @Insert
    void insertKhoaHoc(KhoaHoc khoaHoc);

    @Query("SELECT*FROM khoahoc")
    List<KhoaHoc> getListKhoaHoc();

    @Update
    void update(KhoaHoc khoaHoc);

    @Delete
    void deleteItem(KhoaHoc khoaHoc);





}
