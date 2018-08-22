package com.example.pyrov.room_simple_1.db;

import android.arch.persistence.room.TypeConverter;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class HobbiesConverter {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @TypeConverter
    public String fromHobbies(List<String> hobbies) {
        return hobbies.stream().collect(Collectors.joining(","));
    }

    @TypeConverter
    public List<String> toHobbies(String data) {
        return Arrays.asList(data.split(","));
    }
}
