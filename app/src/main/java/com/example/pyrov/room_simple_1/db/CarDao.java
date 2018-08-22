package com.example.pyrov.room_simple_1.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.pyrov.room_simple_1.model.Car;

import java.util.List;

@Dao
public interface CarDao {

    @Query("SELECT * FROM car")
    List<Car> getAll();

    @Insert
    void insert(Car car);

    @Delete
    void delete(Car car);
}
