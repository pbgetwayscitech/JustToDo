package com.pb.ria.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class single_note_entity (

    @PrimaryKey(true)
    val key : String  = "",
    @ColumnInfo
    val  Title  : String = "",
    @ColumnInfo
    val Description : String  = "",
    @ColumnInfo
    val Timestamp : String = "",
    @ColumnInfo
    val state :String = ""

)