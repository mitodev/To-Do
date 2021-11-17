package com.example.to_do_v2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.to_do_v2.data.ToDoDatabase
import com.example.to_do_v2.data.ToDoRepository
import com.example.to_do_v2.data.model.Priority
import com.example.to_do_v2.data.model.ToDoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {

    //.............. init the database
    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
    //.............. init the repository
    private val repository: ToDoRepository = ToDoRepository(toDoDao)


    // ! ..................the Dao functions
    val getAllData: LiveData<List<ToDoData>> = repository.getAllData

    fun insertData(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO){
        repository.insertData(toDoData)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }

    fun updateData(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(toDoData)
        }
    }
    fun deleteItem(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(toDoData)
        }
    }

    // ....................change the value of the spinner priority from text to object
    fun priorityValue(priority: String): Priority {
        return when(priority){
            "High Priorities"-> Priority.HIGH
            "Medium Priorities"-> Priority.MEDIUM
            "Low Priorities"-> Priority.LOW
            else -> Priority.LOW
        }
    }
    //====== set the all the data from LiveData to MutubleLiveData in order to be able to change it====

    var emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)
    fun checkIfDatabaseEmpty(toDoData: List<ToDoData>){
        emptyDatabase.value = toDoData.isEmpty()
    }
    //=========================================================
}