package com.example.letsgo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.letsgo.databinding.FragmentCreateTaskBinding
import com.example.letsgo.utilities.InjectorUtils
import com.example.letsgo.viewmodels.CreateTaskViewModel
import kotlinx.android.synthetic.main.fragment_create_task.view.*

/**
 * A simple [Fragment] subclass.
 */
class CreateTaskFragment : Fragment() {
    private val model: CreateTaskViewModel by viewModels { InjectorUtils.provideCreateTaskViewModelFactory(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentCreateTaskBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.floatingActionButton.setOnClickListener { v ->
            model.createTask(
                view.createSetInputText.text.toString().toInt(),
                view.createRepInputText.text.toString().toInt(),
                view.createIntensityInputText.text.toString().toInt(),
                view.createUnitInputText.text.toString(),
                view.createTagInputText.text.toString()
            )
            view.findNavController().popBackStack()
        }
    }
}
