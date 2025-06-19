package com.example.portfolioappfortask

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.portfolioappfortask.Models.Skill
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class AddSkillActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_skill)


        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val name = findViewById<EditText>(R.id.etField1).text.toString()
            val desc = findViewById<EditText>(R.id.etField2).text.toString()
            val id = UUID.randomUUID().toString()
            val skill = Skill(id, name, desc)
            FirebaseFirestore.getInstance().collection("skills").document(id).set(skill)
                .addOnSuccessListener { finish() }
        }
    }
}