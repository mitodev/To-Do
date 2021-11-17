package com.example.to_do_v2.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.to_do_v2.R
import com.example.to_do_v2.ToDoViewModel
import com.example.to_do_v2.data.model.ToDoData
import com.example.to_do_v2.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    //.........................values
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    //...........init the viewModel
    private val toDoViewModel: ToDoViewModel by viewModels()

    //.........................functions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        // set menu
        setHasOptionsMenu(true)

        return view
    }

    //.................when the item on the menu gets clicked "item.id->do_this_fun"
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_note -> insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {

        val title = binding.titleAdd.text.toString()
        val priority = toDoViewModel.priorityValue(binding.priorityAdd.selectedItem.toString())
        val description = binding.descriptionAdd.text.toString()

        if (title.isNotEmpty()&&description.isNotEmpty()){
            val newData =
                ToDoData(0,
                    title,
                    priority,
                    description)

            toDoViewModel.insertData(newData)

            findNavController().navigate(R.id.action_addFragment2_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    //.....................set the menu to the one in the "R.menu"
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    // for the viewBinding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}