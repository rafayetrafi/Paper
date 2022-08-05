package com.rafayet.paper.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rafayet.paper.Model.Notes
import com.rafayet.paper.R
import com.rafayet.paper.databinding.ItemNotesBinding
import com.rafayet.paper.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) :RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()

    }


    class notesViewHolder(val binding:ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNotesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]

        holder.binding.tvNotesTitle.text = data.title
        holder.binding.tvNotesSubTitle.text = data.subTitle
        holder.binding.tvNotesDate.text = data.date

        when(data.priority){
            "1"->{holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)}
            "2"->{holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)}
            "3"->{holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)}
        }

        holder.binding.root.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)

        }

    }

    override fun getItemCount() = notesList.size
}