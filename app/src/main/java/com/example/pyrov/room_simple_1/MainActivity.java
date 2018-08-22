package com.example.pyrov.room_simple_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.pyrov.room_simple_1.db.AppDatabase;
import com.example.pyrov.room_simple_1.db.EmployeeDao;
import com.example.pyrov.room_simple_1.model.Address;
import com.example.pyrov.room_simple_1.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private EmployeeDao employeeDao;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = App.getDatabase();

        employeeDao = db.employeeDao();

        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Smith");
        employee.setSalary(10000);
        List<String> hobby = new ArrayList<>();
        hobby.add("cats");
        hobby.add("dogs");
        employee.setHobbies(hobby);
        Address address = new Address();
        address.setCity("London");
        address.setStreet("Baker Street");
        address.setNumber(221);
        employee.setAddress(address);


        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                employeeDao.insert(employee);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        log("onComplete");
                        printDbData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        StackTraceElement[] elements = e.getStackTrace();
                        log("onError");
                        for (int i = 0; i < elements.length; i++) {
                            log(elements[i].toString());
                        }
                    }
                });

//        employeeDao.insert(employee);


    }

    private void log(String s) {
        Log.i("MyTAG", s);
    }

    @SuppressLint("CheckResult")
    private void printDbData() {
        List<Employee> list = new ArrayList<>();

        employeeDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Employee>>() {
                    @Override
                    public void accept(List<Employee> employees) throws Exception {
                        list.addAll(employees);
                        log("Success");

                        for (int i = 0; i < list.size(); i++) {
                            Employee myEmployee = list.get(i);
                            Address myEmployeeAddress = myEmployee.getAddress();
                            log(myEmployee.getFirstName());
                            log(myEmployee.getLastName());
                            log(String.valueOf(myEmployee.getSalary()));
                            log(myEmployeeAddress.getCity());
                            log(myEmployeeAddress.getStreet());
                            log(String.valueOf(myEmployeeAddress.getNumber()));
                        }
                    }
                });


    }
}
