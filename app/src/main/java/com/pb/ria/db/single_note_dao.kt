package com.pb.ria.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface single_note_dao {

    @Query("SELECT * FROM notedb")
    fun get_all_notes() : List<single_note_entity>

    @Insert
    fun insert_into_note( single_note_en : single_note_entity)

    @Update
    fun update_into_database(single_note_en : single_note_entity)

    @Query("DELETE FROM notedb WHERE mkey LIKE :key")
    fun deletenote(key : String)

    @Query("SELECT * FROM notedb WHERE mkey LIKE :title_key")
    fun search_in_notest(title_key :String) :  List<single_note_entity>

}