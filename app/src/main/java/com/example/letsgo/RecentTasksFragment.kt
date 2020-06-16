package com.example.letsgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letsgo.utilities.InjectorUtils
import com.example.letsgo.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.fragment_recent_tasks.view.*

/**
 * A simple [Fragment] subclass.
 */
class RecentTasksFragment : Fragment() {
    private val model: TaskViewModel by activityViewModels { InjectorUtils.provideTaskViewModelFactory(requireActivity()) }

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
                    view.findNavController().navigate(R.id.action_fragmentRecentTasks_to_createTaskFragment)
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

        // REVIEW: wtf is `ViewLifecycleOwner`, is `Observer` an iterator? wtf is `it`?
        // Observe changes for the Tasks in the database
        model.getTasks().observe(viewLifecycleOwner, Observer { tasks ->
            tasks?.let { viewAdapter.setTasks(it) }
        })
    }
}