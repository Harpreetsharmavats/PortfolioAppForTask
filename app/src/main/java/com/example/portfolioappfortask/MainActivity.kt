package com.example.portfolioappfortask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolioappfortask.Adapter.CertificationAdapter
import com.example.portfolioappfortask.Adapter.ProjectAdapter
import com.example.portfolioappfortask.Adapter.SkillAdapter
import com.example.portfolioappfortask.Models.Certification
import com.example.portfolioappfortask.Models.Project
import com.example.portfolioappfortask.Models.Skill
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var skillAdapter: SkillAdapter
    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var certAdapter: CertificationAdapter

    private val db = FirebaseFirestore.getInstance()
    private val skills = mutableListOf<Skill>()
    private val projects = mutableListOf<Project>()
    private val certs = mutableListOf<Certification>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnAddSkill).setOnClickListener {
            startActivity(Intent(this, AddSkillActivity::class.java))
        }
        findViewById<Button>(R.id.btnAddProject).setOnClickListener {
            startActivity(Intent(this, AddProjectActivity::class.java))
        }
        findViewById<Button>(R.id.btnAddCertification).setOnClickListener {
            startActivity(Intent(this, AddCertificationActivity::class.java))
        }

        setupRecyclerViews()
        loadAllData()
    }

    private fun setupRecyclerViews() {
        skillAdapter = SkillAdapter(skills) { deleteSkill(it) }
        findViewById<RecyclerView>(R.id.rvSkills).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = skillAdapter
        }

        projectAdapter = ProjectAdapter(projects) { deleteProject(it) }
        findViewById<RecyclerView>(R.id.rvProjects).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = projectAdapter
        }

        certAdapter = CertificationAdapter(certs) { deleteCertification(it) }
        findViewById<RecyclerView>(R.id.rvCertifications).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = certAdapter
        }
    }

    private fun loadAllData() {
        db.collection("skills").get().addOnSuccessListener {
            skills.clear()
            skills.addAll(it.map { doc -> doc.toObject(Skill::class.java) })
            skillAdapter.notifyDataSetChanged()
        }

        db.collection("projects").get().addOnSuccessListener {
            projects.clear()
            projects.addAll(it.map { doc -> doc.toObject(Project::class.java) })
            projectAdapter.notifyDataSetChanged()
        }

        db.collection("certifications").get().addOnSuccessListener {
            certs.clear()
            certs.addAll(it.map { doc -> doc.toObject(Certification::class.java) })
            certAdapter.notifyDataSetChanged()
        }
    }

    private fun deleteSkill(id: String) {
        db.collection("skills").document(id).delete().addOnSuccessListener { loadAllData() }
    }

    private fun deleteProject(id: String) {
        db.collection("projects").document(id).delete().addOnSuccessListener { loadAllData() }
    }

    private fun deleteCertification(id: String) {
        db.collection("certifications").document(id).delete().addOnSuccessListener { loadAllData() }
    }
}