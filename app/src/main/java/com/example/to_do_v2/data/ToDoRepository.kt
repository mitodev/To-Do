package com.example.to_do_v2.data

import androidx.lifecycle.LiveData
import com.example.to_do_v2.data.model.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    fun getSearched(search: String): LiveData<List<ToDoData>> = toDoDao.getSearched(search)

    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }

    suspend fun deleteAll(){
        toDoDao.deleteAll()
    }

    suspend fun updateData(toDoData: ToDoData){
        toDoDao.updateData(toDoData)
    }
    suspend fun deleteItem(toDoData: ToDoData){
        toDoDao.deleteItem(toDoData)
    }
}