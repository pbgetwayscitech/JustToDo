package com.pb.ria.db

import androidx.room.Dao

@Dao
interface single_note_dao {

    fun get_all_notes() : List<single_note_entity>

    fun insert_into_note(title : String,desc : String , time : String , state : String)

    fun update_into_database(keey : String ,title : String,desc : String , time : String , state : String)

    fun deletenote(key : String)

    fun search_in_notest(titile_key :String) :  List<single_note_entity>

}