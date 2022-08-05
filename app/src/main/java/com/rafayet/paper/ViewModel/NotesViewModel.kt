package com.rafayet.paper.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rafayet.paper.Database.Database
import com.rafayet.paper.Model.Notes
import com.rafayet.paper.Repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {
    val repository:NotesRepository
    init {
        val dao =Database.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun getLowNotes():LiveData<List<Notes>> = repository.getLowNotes()
    fun getMediumNotes():LiveData<List<Notes>> = repository.getMediumNotes()
    fun getHighNotes():LiveData<List<Notes>> = repository.getHighNotes()

    fun getNotes():LiveData<List<Notes>> = repository.getallNotes()

    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}