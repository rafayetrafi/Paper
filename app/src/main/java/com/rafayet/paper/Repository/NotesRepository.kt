package com.rafayet.paper.Repository

import androidx.lifecycle.LiveData
import com.rafayet.paper.Dao.NotesDao
import com.rafayet.paper.Model.Notes

class NotesRepository(val dao: NotesDao) {
    fun getallNotes():LiveData<List<Notes>> = dao.getNotes()

    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()
    fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()
    fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()

    fun insertNotes(notes: Notes){
        dao.insertNotes((notes))
    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}