package com.tedaindo.roomkotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.tedaindo.roomkotlin.db.Book
import com.tedaindo.roomkotlin.db.BookDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var db:BookDatabase
    lateinit var tvDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tv_display)

        db = Room.databaseBuilder(applicationContext, BookDatabase::class.java, "book-db").build()

        GlobalScope.launch {
            db.bookDao().deleteAll()
            initData()
            displayData()
        }
    }

    private fun displayData() {
        val books: List<Book> = db.bookDao().getAllBooks()
        var displayText = ""
        for (book in books) {
            displayText += "${book.title} | ${book.authorName} | Hal : ${book.totalPages}\n"
        }
        tvDisplay.text = displayText
    }

    private fun initData() {
        val book1 = Book("Ada Apa dengan Hujan", "Andre Senja", 30)
        val book2 = Book("Malam setelah Sore", "Andi Bisa", 40)
        val book3 = Book("Pecahkan Mentari", "Dera", 20)
        //insert data ke database
        db.bookDao().insert(book1,book2,book3)
    }
}