package com.rafayet.paper.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.rafayet.paper.Model.Notes
import com.rafayet.paper.R
import com.rafayet.paper.ViewModel.NotesViewModel
import com.rafayet.paper.databinding.FragmentHomeBinding
import com.rafayet.paper.databinding.FragmentNewNoteBinding
import java.util.*


class NewNoteFragment : Fragment() {

    lateinit var binding: FragmentNewNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewNoteBinding.inflate(layoutInflater, container, false)
        binding.ivPriorityGreen.setImageResource(R.drawable.ic_baseline_done_24)

        binding.ivPriorityGreen.setOnClickListener {
            priority = "1"
            binding.ivPriorityGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivPriorityRed.setImageResource(0)
            binding.ivPriorityYellow.setImageResource(0)
        }

        binding.ivPriorityRed.setOnClickListener {
            priority = "3"
            binding.ivPriorityRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivPriorityGreen.setImageResource(0)
            binding.ivPriorityYellow.setImageResource(0)
        }

        binding.ivPriorityYellow.setOnClickListener {
            priority = "2"
            binding.ivPriorityYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivPriorityGreen.setImageResource(0)
            binding.ivPriorityRed.setImageResource(0)
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.etTitle.text.toString()
        val subtitle = binding.etSubitle.text.toString()
        val notes = binding.etNewBlankNotes.text.toString()
        val noteDate = getCurrentDate()

        val notesData = Notes(
            id = null,
            title = title,
            subTitle = subtitle,
            notes = notes,
            date = noteDate.toString(),
            priority = priority
        )
        viewModel.addNotes(notesData)
        Toast.makeText(requireActivity(), "Paper Created Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_newNoteFragment_to_homeFragment)


    }

    fun getCurrentDate(): CharSequence {
    val date = Date()
    val formatDate:CharSequence = DateFormat.format("MMMM d, yyyy", date.time)

        return formatDate
    }


}