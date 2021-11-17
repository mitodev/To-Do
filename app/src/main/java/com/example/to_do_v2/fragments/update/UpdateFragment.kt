package com.example.to_do_v2.fragments.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.to_do_v2.R
import com.example.to_do_v2.ToDoViewModel
import com.example.to_do_v2.data.model.ToDoData
import com.example.to_do_v2.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {
    //.........................values
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    //...........safe args data
    private val args by navArgs<UpdateFragmentArgs>()

    //...........init the viewModel
    private val toDoViewModel: ToDoViewModel by viewModels()

    //.........................functions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        // set menu
        setHasOptionsMenu(true)

        // reading the data from safe args
        binding.titleUpdate.setText(args.cuttentItem.title)
        binding.priorityUpdate.setSelection(priorityNumber(args.cuttentItem.priority.name))
        binding.descriptionUpdate.setText(args.cuttentItem.description)

        return view
    }

    //.................when the item on the menu gets clicked "item.id->do_this_fun"
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_btn -> saveChanges()
            R.id.menu_delete -> deleteThis()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteThis() {
        val thisItem = ToDoData(
            args.cuttentItem.id,
            binding.titleUpdate.text.toString(),
            toDoViewModel.priorityValue(binding.priorityUpdate.selectedItem.toString()),
            binding.descriptionUpdate.text.toString())

        toDoViewModel.deleteItem(thisItem)

        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun saveChanges() {

        val newData = ToDoData(
            args.cuttentItem.id,
            binding.titleUpdate.text.toString(),
            toDoViewModel.priorityValue(binding.priorityUpdate.selectedItem.toString()),
            binding.descriptionUpdate.text.toString())
        toDoViewModel.updateData(newData)

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun priorityNumber(prio: String): Int {
        return when(prio){
            "HIGH"-> 0
            "MEDIUM"-> 1
            "LOW"-> 2
            else-> 0
        }
    }

    //.....................set the menu to the one in the "R.menu"
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
