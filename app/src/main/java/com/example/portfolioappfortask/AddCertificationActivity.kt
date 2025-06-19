package com.example.portfolioappfortask

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.portfolioappfortask.Models.Certification
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class AddCertificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_certification)


        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val title = findViewById<EditText>(R.id.etField1).text.toString()
            val org = findViewById<EditText>(R.id.etField2).text.toString()
            val id = UUID.randomUUID().toString()
            val cert = Certification(id, title, org)
            FirebaseFirestore.getInstance().collection("certifications").document(id).set(cert)
                .addOnSuccessListener { finish() }
        }
    }
}
