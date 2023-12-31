package com.pb.ria.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ndao {

    @Query("SELECT * FROM nen")
    List<nen> get_all();

    @Query("SELECT * FROM nen WHERE id LIKE :id")
    List<nen> search_in_noteid(String id);

    @Query("SELECT * FROM nen WHERE state LIKE :mstate")
    List<nen> get_all_note_with_state(String mstate);

    @Insert
    void insert_note(nen nen);

    @Update
    void update_into_database(nen mnen) ;

    @Query("DELETE FROM nen WHERE id LIKE :mkey")
    void deletenote(String mkey);

}
