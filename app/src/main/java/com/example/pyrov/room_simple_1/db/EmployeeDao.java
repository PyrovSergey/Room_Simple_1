package com.example.pyrov.room_simple_1.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.example.pyrov.room_simple_1.model.Employee;

import java.util.Date;
import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employees")
    Maybe<List<Employee>> getAll();

    //    или если нужен массив Employee[]
    //    @Query("SELECT * FROM employee")
    //    Employee[] getAll();

    // поиск сотрудников с зарплатой больше заданного значения
    @Query("SELECT * FROM employees WHERE salary > :minSalary")
    List<Employee> getAllWithSalaryMoreThan(int minSalary);

    // поиск сотрудников с зарплатой в заданном диапазоне
    @Query("SELECT * FROM employees WHERE salary BETWEEN :minSalary AND :maxSalary")
    List<Employee> getAllWithSalaryBetween(int minSalary, int maxSalary);

    // поиск сотрудников по имени или фамилии
    @Query("SELECT * FROM employees WHERE first_name LIKE :search OR last_name LIKE :search")
    List<Employee> getAllWithNameLike(String search);

    // поиск сотрудников по списку id
    @Query("SELECT * FROM employees WHERE id IN (:idList)")
    List<Employee> getByIdList(List<Long> idList);

    @Query("SELECT * FROM employees WHERE id = :id")
    Employee getById(long id);

    // позволит получить работника с конкретной датой рождения
    @Query("SELECT * FROM employees WHERE birthday = :birthdayDate")
    Employee getByDate(@TypeConverters({DateConverter.class}) Date birthdayDate);

    // этот вариант позволит вернуть сотрудника в промежутках межуд датами
    @Query("SELECT * FROM employees WHERE birthday BETWEEN :birthdayFrom and :birthdayTo")
    @TypeConverters({DateConverter.class})
    Employee getByDate(Date birthdayFrom, Date birthdayTo);


    // позволит заменить запись, если такая уже есть в БД
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Employee employee);

    // позволит передать и список и коллекцию
    @Insert
    void insert(Iterable<Employee> employees);

    @Insert
    void insert(Employee... employees);

    @Update
    int update(Employee employee);

    @Update
    int update(Employee... employee);

    @Update
    int update(List<Employee> employee);

    // обновление зарплат у сотрудников по списку id
    @Query("UPDATE employees SET salary = :newSalary WHERE id IN (:idList)")
    int updateSalaryByIdList(List<Long> idList, int newSalary);

    @Delete
    void delete(Employee employee);

    @Delete
    int delete(Employee... employee);

    @Delete
    int delete(List<Employee> employee);

    // удаление сотрудников по списку id
    @Query("DELETE from employees WHERE id IN (:idList)")
    int deleteByIdList(List<Long> idList);

}
