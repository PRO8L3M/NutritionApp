package com.nutrition.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.text.isDigitsOnly
import com.nutrition.R
import com.nutrition.common.BaseFragment
import com.nutrition.common.customs.EditTextValidator
import com.nutrition.ext.toast
import kotlinx.android.synthetic.main.fragment_start.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : BaseFragment() {

    private val viewModel: StartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* val isEmpty: (String) -> Boolean = { it.isEmpty() }
         val hasVariousCharTypes: (String) -> Boolean = { !it.isDigitsOnly() }
         val isBiggerThan16Chars: (String) -> Boolean = { it.length > 16 }

         val emptyComparator = EditTextValidator(isEmpty, "String cannot be empty!")
         val onlyDigitsComparator = EditTextValidator(hasVariousCharTypes, "String can have only digits!")
         val sizeComparator = EditTextValidator(isBiggerThan16Chars, "String cannot be bigger than 16 chars!")

         val listOfComparators = listOf(emptyComparator, onlyDigitsComparator, sizeComparator)

         button.setOnClickListener {
             val textToValidate = edit_text.text.toString()
             listOfComparators.forEach {
                 if (it.isValid(textToValidate)) {
                     toast(it.errorMessage)
                 }
             }
             val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.shake_anim)
             edit_text.startAnimation(animation)
         }*/

        val isNotEmpty: (String) -> Boolean = { it.isNotEmpty() }
        val isDigitsOnly: (String) -> Boolean = { it.isDigitsOnly() }
        val isTooLong: (String) -> Boolean = { it.length <= 10 }

        val listOfValidators = mutableListOf(
            EditTextValidator(isNotEmpty, "String is empty."),
            EditTextValidator(isDigitsOnly, "String must be digits only."),
            EditTextValidator(isTooLong, "String is too long.")
        )

        custom_input.setListOfValidators(listOfValidators)

        clear_btn.setOnClickListener {
            custom_input.clearValidationMessages()
        }

        validate_btn.setOnClickListener {
            custom_input.validate()
        }

        val textFromRepo = viewModel.getText()
        toast(textFromRepo)
    }
}
