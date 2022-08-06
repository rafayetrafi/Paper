package com.rafayet.paper.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rafayet.paper.Model.Notes
import com.rafayet.paper.R
import com.rafayet.paper.ViewModel.NotesViewModel
import com.rafayet.paper.databinding.FragmentEditNoteBinding
import java.util.*


class EditNoteFragment : Fragment() {

    val oldNotes by navArgs<EditNoteFragmentArgs>()
    lateinit var binding:FragmentEditNoteBinding
    var priority:String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        binding.etEditTitle.setText(oldNotes.data.title)
        binding.etEditSubtitle.setText(oldNotes.data.subTitle)
        binding.etEditNotes.setText(oldNotes.data.notes)

        when(oldNotes.data.priority){
            "1"->{
                priority = "1"
                binding.ivEditPriorityGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.ivEditPriorityRed.setImageResource(0)
                binding.ivEditPriorityYellow.setImageResource(0)
            }
            "2"->{
                priority = "2"
                binding.ivEditPriorityYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.ivEditPriorityGreen.setImageResource(0)
                binding.ivEditPriorityRed.setImageResource(0)
            }
            "3"->{
                priority = "3"
                binding.ivEditPriorityRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.ivEditPriorityGreen.setImageResource(0)
                binding.ivEditPriorityYellow.setImageResource(0)
            }
        }


        binding.ivEditPriorityGreen.setOnClickListener {
            priority = "1"
            binding.ivEditPriorityGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivEditPriorityRed.setImageResource(0)
            binding.ivEditPriorityYellow.setImageResource(0)
        }

        binding.ivEditPriorityRed.setOnClickListener {
            priority = "3"
            binding.ivEditPriorityRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivEditPriorityGreen.setImageResource(0)
            binding.ivEditPriorityYellow.setImageResource(0)
        }

        binding.ivEditPriorityYellow.setOnClickListener {
            priority = "2"
            binding.ivEditPriorityYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivEditPriorityGreen.setImageResource(0)
            binding.ivEditPriorityRed.setImageResource(0)
        }


        binding.btnEditedSaveNotes.setOnClickListener{

            updateNotes(it)

        }

        return binding.root
    }

    private fun updateNotes(it: View?) {

        val title = binding.etEditTitle.text.toString()
        val subtitle = binding.etEditSubtitle.text.toString()
        val notes = binding.etEditNotes.text.toString()
        val noteDate = getCurrentDate()

        val notesData = Notes(
            oldNotes.data.id,
            title = title,
            subTitle = subtitle,
            notes = notes,
            date = noteDate.toString(),
            priority = priority
        )
        viewModel.updateNotes(notesData)
        Toast.makeText(requireActivity(), "Paper Updated Successful", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    fun getCurrentDate(): CharSequence {
        val date = Date()
        val formatDate:CharSequence = DateFormat.format("MMMM d, yyyy", date.time)

        return formatDate
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.munu_delete)
        {
            val bottomSheet:BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialogue_delete)

            val  tvYes = bottomSheet.findViewById<TextView>(R.id.btnDialog_yes)
            val  tvNo = bottomSheet.findViewById<TextView>(R.id.btnDialog_no)

            tvYes?.setOnClickListener {
            viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
            }

            tvNo?.setOnClickListener {
            bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }


}