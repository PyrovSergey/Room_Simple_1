package com.example.pyrov.room_simple_1.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

// тут мы задаем сборный ключ, который будет хранится в поле employeeId. удаление будет происходить при удалении parent id, так как стоит CASCADE
@Entity(foreignKeys = @ForeignKey(entity = Employee.class, parentColumns = "id", childColumns = "employee_id", onDelete = CASCADE))
public class Car {

    // автогенерация id
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String model;

    private int year;

    // просто задаем свое имя для столбца
    @ColumnInfo(name = "employee_id")
    private long employeeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }
}
