package com.tedaindo.roomkotlin.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAllBooks():List<Book>

    @Insert
    fun insert(vararg books: Book)

    @Delete
    fun delete(book: Book)

    @Query("DELETE FROM book")
    fun deleteAll()
}