package com.pb.ria.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class single_note_entity (

    @NonNull
    @PrimaryKey(autoGenerate = true)
    val mkey : String ,

    @ColumnInfo
    var Title  : String,

    @ColumnInfo
    var Description : String,

    @ColumnInfo
    var Timestamp : String,

    @ColumnInfo
    var state :String

)