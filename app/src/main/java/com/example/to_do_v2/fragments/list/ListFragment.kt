package com.example.to_do_v2.fragments.list

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.View.INVISIBLE
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_v2.R
import com.example.to_do_v2.ToDoViewModel
import com.example.to_do_v2.data.model.ToDoData
import com.example.to_do_v2.databinding.FragmentListBinding

class ListFragment : Fragment() {

    //.........................values
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter: ListAdapter by lazy { ListAdapter() }

    //...........init the viewModel
    private val toDoViewModel: ToDoViewModel by viewModels()

    //.........................functions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        // navigate to other fragment when click on + btn
        binding.addBtn.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment2)
        }

        // set menu
        setHasOptionsMenu(true)

        //set the RC
        val recyclerView = binding.cardsRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        // get all the data and set it in the adapter "data"
        toDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            toDoViewModel.checkIfDatabaseEmpty(data)
            adapter.setData(data)
        })

        //set visibility of text and image
        toDoViewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
            setVisibility(it)
        })

        return view
    }

    private fun setVisibility(empty: Boolean) {
        if (empty){
            binding.nodataTextView.visibility = View.VISIBLE
            binding.nodataImageView.visibility = View.VISIBLE
    } else {
            binding.nodataTextView.visibility = INVISIBLE
            binding.nodataImageView.visibility = INVISIBLE }

    }

    //.................when the item on the menu gets clicked "item.id->do_this_fun"
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all -> {
                toDoViewModel.deleteAll()
                setVisibility(false)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //.....................set the menu to the one in the "R.menu"
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}