package com.pb.ria.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database ( entities = [single_note_entity::class], version = 1)
abstract class note_database() : RoomDatabase(){
    abstract fun notedao()  : single_note_dao
}

