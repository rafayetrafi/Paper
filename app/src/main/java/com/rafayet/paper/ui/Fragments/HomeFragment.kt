package com.rafayet.paper.ui.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rafayet.paper.Model.Notes
import com.rafayet.paper.R
import com.rafayet.paper.ViewModel.NotesViewModel
import com.rafayet.paper.databinding.FragmentHomeBinding
import com.rafayet.paper.ui.Adapter.NotesAdapter
import android.widget.SearchView;


class HomeFragment : Fragment() {

    lateinit var  binding:FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldmyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvAllNotes.layoutManager = staggeredGridLayoutManager

        //Get All Notes
        viewModel.getNotes().observe(viewLifecycleOwner, {notesList ->
            oldmyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rvAllNotes.adapter = adapter

        })

        //Filter Reset
        binding.ivallNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner, {notesList ->
                oldmyNotes = notesList as ArrayList<Notes> /* = java.util.ArrayList<com.rafayet.paper.Model.Notes> */
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = adapter
            })
        }

        binding.tvfilterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, {notesList ->
                oldmyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = adapter
            })
        }

        binding.tvfilterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner, {notesList ->
                oldmyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = adapter
            })
        }

        binding.tvfilterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner, {notesList ->
                oldmyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = adapter

            })

        }

        binding.btnNewNote.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_newNoteFragment)
        }
            return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.serach_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFiltering(newText)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(newText: String?) {

        val newFilteredList = arrayListOf<Notes>()
        for (i in oldmyNotes){
            if (i.title.contains(newText!!) || i.subTitle.contains(newText!!)){
                newFilteredList.add(i)
            }
        }

        adapter.filtering(newFilteredList)

    }


}