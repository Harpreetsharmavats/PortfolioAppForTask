package com.example.portfolioappfortask.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolioappfortask.Models.Certification
import com.example.portfolioappfortask.R

class CertificationAdapter(private val list: List<Certification>, private val onDelete: (String) -> Unit) :
    RecyclerView.Adapter<CertificationAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById<TextView>(R.id.tvTitle)
        val subtitle: TextView = view.findViewById<TextView>(R.id.tvSubtitle)
        val btnDelete: Button = view.findViewById<Button>(R.id.btnDelete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entry, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.title
        holder.subtitle.text = item.organization
        holder.btnDelete.setOnClickListener { onDelete(item.id) }
    }
    override fun getItemCount() = list.size
}
