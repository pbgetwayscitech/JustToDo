package com.pb.ria.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class nen {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String Title;

    @ColumnInfo
    public String Description;

    @ColumnInfo
    public String Timestamp;

    @ColumnInfo
    public String state;

}
