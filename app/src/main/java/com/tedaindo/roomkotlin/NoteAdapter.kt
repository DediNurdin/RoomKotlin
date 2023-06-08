package com.tedaindo.roomkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tedaindo.roomkotlin.room.Note

class NoteAdapter (private var notes: ArrayList<Note>, private val listener: OnAdapterListener) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.adapter_main,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.judul.text = note.title
        holder.judul.setOnClickListener {
            listener.onRead(note)
        }
        holder.edit.setOnClickListener {
            listener.onUpdate(note)
        }
        holder.delete.setOnClickListener {
            listener.onDelete(note)
        }
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val judul: TextView = view.findViewById(R.id.tv_title)
        val edit: ImageView = view.findViewById(R.id.iv_edit)
        val delete: ImageView = view.findViewById(R.id.iv_delete)
    }

    fun setData(newList: List<Note>) {
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onRead(note: Note)
        fun onUpdate(note: Note)
        fun onDelete(note: Note)
    }
}