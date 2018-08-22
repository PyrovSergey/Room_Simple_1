package com.example.pyrov.room_simple_1.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.pyrov.room_simple_1.db.HobbiesConverter;

import java.util.List;

// тут мы задаем сове имя таблицы и ставим поля first_name и last_name уникальными (по умолчанию false) и задаем им индекс вместе с полем salary
@Entity(tableName = "employees", indices = {@Index("salary"), @Index(value = {"first_name", "last_name"}, unique = false)})
public class Employee {

    // выставляем автогенерацию поля id
    @PrimaryKey(autoGenerate = true)
    private long id;

    // здесь мы задаем свое название столбца в БД (first_name)
    @ColumnInfo(name = "first_name")
    private String firstName;

    // здесь мы задаем свое название столбца в БД (last_name)
    @ColumnInfo(name = "last_name")
    private String lastName;

    private long birthday;

    private int salary;

    @Embedded(prefix = "address")
    private Address address;

    @TypeConverters({HobbiesConverter.class})
    private List<String> hobbies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
