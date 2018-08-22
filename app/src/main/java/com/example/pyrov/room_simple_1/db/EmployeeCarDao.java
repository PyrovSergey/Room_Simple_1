package com.example.pyrov.room_simple_1.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Transaction;

import com.example.pyrov.room_simple_1.model.Car;
import com.example.pyrov.room_simple_1.model.Employee;

// данный класс позволяет выполнять несколько методов в рамках одной транзакции
@Dao
public abstract class EmployeeCarDao {

    @Insert
    public abstract void insertEmployee(Employee employee);

    @Insert
    public abstract void insertCar(Car car);

    @Transaction
    public void insertCarAndEmployee(Car car, Employee employee) {
        insertCar(car);
        insertEmployee(employee);
    }
}
