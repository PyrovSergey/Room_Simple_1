package com.example.pyrov.room_simple_1.db;

import android.arch.persistence.room.Entity;

// возможно не задействован
@Entity(primaryKeys = {"key1", "key2"})
public class Item {
    public long key1;
    public long key2;
}
