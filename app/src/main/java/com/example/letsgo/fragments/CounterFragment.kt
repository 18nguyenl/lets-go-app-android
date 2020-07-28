package com.example.letsgo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.letsgo.R
import com.example.letsgo.utilities.InjectorUtils
import com.example.letsgo.viewmodels.CounterViewModel
import kotlinx.android.synthetic.main.fragment_counter.view.*

/**
 * A simple [Fragment] subclass.
 */
class CounterFragment : Fragment() {

    // need to pass the task ID from the list view to the counter view
    //      perhaps by using an overarching VM, or some other way
    private val args: CounterFragmentArgs by navArgs()
    private val viewModel: CounterViewModel by viewModels { InjectorUtils.provideCounterViewModelFactory(this, args.taskID) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.counterTitleText.text = viewModel.counter.progress()
        view.counter_intensity_unit_text.text = viewModel.counter.intensity
        view.counter_sets_reps_text.text = viewModel.counter.volume
        view.counter_tag_text.text = viewModel.counter.hashtag

        view.setOnClickListener { view ->
            if (viewModel.counter.isInProgress()) {
                viewModel.incrementCounter()
                view.counterTitleText.text = viewModel.counter.progress()
            } else {
                view.findNavController().popBackStack()
            }
        }

        // Pass the viewModel down to the Canvas View Object
        view.counterCanvas.initCanvas(viewModel)
    }

    override fun onResume() {
        super.onResume()

        disableCounterToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()

        enableCounterToolbar()
    }

    private fun enableCounterToolbar() {
        val toolbar = (activity as AppCompatActivity).supportActionBar

        toolbar?.show()
    }

    private fun disableCounterToolbar() {
        val toolbar = (activity as AppCompatActivity).supportActionBar

        toolbar?.hide()
    }
}