package com.pb.ria.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = nen.class , version = 1 , exportSchema = false)
public abstract class ndb extends RoomDatabase {
    public abstract ndao ndao();
}
