package com.example.portfolioappfortask

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.portfolioappfortask.Models.Project
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class AddProjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_project)


        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val title = findViewById<EditText>(R.id.etField1).text.toString()
            val details = findViewById<EditText>(R.id.etField2).text.toString()
            val id = UUID.randomUUID().toString()
            val project = Project(id, title, details)
            FirebaseFirestore.getInstance().collection("projects").document(id).set(project)
                .addOnSuccessListener { finish() }
        }
    }
}