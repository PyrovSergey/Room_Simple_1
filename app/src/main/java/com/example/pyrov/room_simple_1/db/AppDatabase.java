package com.example.pyrov.room_simple_1.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.pyrov.room_simple_1.model.Car;
import com.example.pyrov.room_simple_1.model.Employee;

@Database(entities = {Employee.class, Car.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();

    public abstract CarDao carDao();

    public abstract EmployeeCarDao employeeCarDao();
}
