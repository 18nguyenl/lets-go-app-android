package com.example.letsgo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.letsgo.models.Task
import com.example.letsgo.utilities.InjectorUtils
import com.example.letsgo.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.counter_actionbar_title.view.*
import kotlinx.android.synthetic.main.fragment_counter.view.*

/**
 * A simple [Fragment] subclass.
 */
class CounterFragment : Fragment() {
    private val model: TaskViewModel by activityViewModels { InjectorUtils.provideTaskViewModelFactory(requireActivity()) }

    private var currentSet = 0
    private var selectedTask: Task = Task(0, 0, "", 0, "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.selectedTask.observe(viewLifecycleOwner, Observer { task ->
            task?.let {
                selectedTask = it
                view.counterTitleText.text = "${currentSet} of ${selectedTask.sets}"
            }
        })

        view.setOnClickListener { view ->
            if (currentSet < selectedTask.sets) {
                currentSet++
                view.counterTitleText.text = "${currentSet} of ${selectedTask.sets}"
            } else {
                view.findNavController().popBackStack()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        enableCounterToolbar()
    }

    override fun onPause() {
        super.onPause()

        disableCounterToolbar()
    }

    private fun enableCounterToolbar() {
        // https://stackoverflow.com/questions/33219485/add-and-remove-views-from-toolbar-depending-on-fragment-displayed
        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setDisplayShowTitleEnabled(false)
        toolbar?.setDisplayShowHomeEnabled(false)
        toolbar?.setDisplayShowCustomEnabled(true)

        val counterToolbarView = layoutInflater.inflate(R.layout.counter_actionbar_title, null)

        counterToolbarView.counter_intensity_unit_text.text = "${selectedTask.intensity} ${selectedTask.unit}"
        counterToolbarView.counter_sets_reps_text.text = "${selectedTask.sets} × ${selectedTask.reps}"
        counterToolbarView.counter_tag_text.text = "#${selectedTask.tag}"

        toolbar?.customView = counterToolbarView
    }

    private fun disableCounterToolbar() {
        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setDisplayShowCustomEnabled(false)
        toolbar?.setDisplayShowTitleEnabled(true)
        toolbar?.setDisplayShowHomeEnabled(true)
    }
}