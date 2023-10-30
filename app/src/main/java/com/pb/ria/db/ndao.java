package com.pb.ria.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ndao {

    @Query("SELECT * FROM nen WHERE state LIKE :mstate")
    List<nen> get_all_note_with_state(String mstate);

    @Query("SELECT * FROM nen")
    List<nen> get_all();

    @Query("SELECT * FROM nen WHERE id LIKE :title_key")
    List<nen> search_in_notest(String title_key);

    @Insert
    void insert_note(nen nen);

    /*

    @Update
    void update_into_database(nen mnen) ;

    @Query("DELETE FROM nen WHERE id LIKE :mkey")
    void deletenote(String mkey);

     */

}
