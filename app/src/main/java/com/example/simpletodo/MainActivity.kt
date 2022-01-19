package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    // create a list of strings
    var listOfTasks = mutableListOf<String>()
    lateinit var adapter : TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // save data into a file
        // get the file
        fun getDataFile(): File {
            return File(filesDir, "data.txt")
        }

        // load list of items by reading from file
        fun loadItems() {
            try {
                listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
            } catch (ioException: IOException) {
                ioException.printStackTrace()
            }

        }

        // save items by writing to file
        fun saveItems() {
            try {
                FileUtils.writeLines(getDataFile(), listOfTasks)
            } catch (ioException: IOException) {
                ioException.printStackTrace()
            }
        }

        val onLongClickListener = object :  TaskItemAdapter.OnLongClickListener {
            override fun onItemLongClicked(position: Int) {
                // remove item from a list
                listOfTasks.removeAt(position)

                adapter.notifyDataSetChanged()

                saveItems()
            }

        }

        loadItems()

        // look up recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val inputTextField = findViewById<EditText>(R.id.addTaskField)

        findViewById<Button>(R.id.button).setOnClickListener {
            // This code executes when a user clicks on the button
            val userInputtedTask = inputTextField.text.toString()

            listOfTasks.add(userInputtedTask)

            adapter.notifyItemInserted( listOfTasks.size - 1)

            inputTextField.setText("")

            saveItems()

        }

    }
}