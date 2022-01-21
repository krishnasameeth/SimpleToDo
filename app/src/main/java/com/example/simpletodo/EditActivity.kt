package com.example.simpletodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val editTextField = findViewById<EditText>(R.id.editTaskField)

        findViewById<Button>(R.id.button2).setOnClickListener {
            // This code executes when a user clicks on the button
            val userUpdatedTask = editTextField.text.toString()
            val position = intent.getIntExtra("position", 0)

            val data = Intent()
            // Pass relevant data back as a result
            data.putExtra("updated_string", userUpdatedTask)
            data.putExtra("position", position)
            setResult(RESULT_OK, data)
            editTextField.setText("")

            this.finish()

        }
    }
}