package com.example.letsgo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letsgo.R
import com.example.letsgo.TaskAdapter
import com.example.letsgo.utilities.InjectorUtils
import com.example.letsgo.viewmodels.TaskListViewModel
import kotlinx.android.synthetic.main.fragment_recent_tasks.view.*

/**
 * A simple [Fragment] subclass.
 */
class RecentTasksFragment : Fragment() {

    private val model: TaskListViewModel by viewModels { InjectorUtils.provideAllTasksViewModelFactory(requireActivity()) }

    // Recycler View components
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_create -> {
                    view.findNavController().navigate(R.id.createAction)
                }
            }
            true
        }

        val viewManager = LinearLayoutManager(activity)
        val viewAdapter = TaskAdapter()
        viewAdapter.setViewModel(model)

        recyclerView = view.findViewById<RecyclerView>(R.id.recentTaskList).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }

        // Observe changes for the Tasks in the database
        model.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            tasks?.let { viewAdapter.setTasks(it) }
        })
    }
}