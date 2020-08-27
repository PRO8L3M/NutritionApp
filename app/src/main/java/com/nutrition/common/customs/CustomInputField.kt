package com.nutrition.common.customs

import android.content.Context
import android.graphics.Color.red
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.nutrition.R
import kotlinx.android.synthetic.main.custom_input_field.view.*
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.android.synthetic.main.fragment_start.view.*
import java.lang.Exception

class CustomInputField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val listOfValidators = mutableListOf<EditTextValidator>()
    private val listOfValidationMessages = mutableSetOf<String>()

    init {
        initUI()
    }

    private fun initUI() {
        View.inflate(context, R.layout.custom_input_field, this)
    }

    private fun onValidationError() {
        launchValidationErrorAnimation()
        showValidationErrorMessages()
        custom_input_layout.boxStrokeColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
    }

    private fun launchValidationErrorAnimation() {
        val animation = AnimationUtils.loadAnimation(context, R.anim.shake_anim)
        custom_input_layout.startAnimation(animation)
    }

    private fun showValidationErrorMessages() {
        groupValidationMessages()
    }

    private fun groupValidationMessages() {
        listOfValidationMessages.forEach {
            validation_messages.append("* $it\n")
        }
    }

    fun validate() {
        clearValidationMessages()
        throwIfListOfValidatorsIsEmpty()
        val inputText = custom_input_field_edit_text.text.toString()
        listOfValidators.forEach { editTextValidator ->
            if (!editTextValidator.isValid(inputText)) addValidationErrorMessage(editTextValidator.errorMessage)
        }
        if (listOfValidationMessages.isNotEmpty()) onValidationError()
    }

    private fun throwIfListOfValidatorsIsEmpty() =
        if (listOfValidators.isEmpty()) throw Exception("List of validators can not be empty. Use setListOfValidators(validators: List<EditTextValidator>) to set wanted validators.") else Unit

    fun clearValidationMessages() {
        listOfValidationMessages.clear()
        validation_messages.text = ""
        custom_input_layout.boxStrokeColor = ContextCompat.getColor(context, R.color.colorPrimary)
        invalidate()
    }

    private fun addValidationErrorMessage(errorMessage: String) = listOfValidationMessages.add(errorMessage)

    fun setListOfValidators(validators: List<EditTextValidator>) {
        listOfValidators.addAll(validators)
    }
}

class EditTextValidator(
    private val validationLambda: (String) -> Boolean,
    override val errorMessage: String
) : Validator() {
    override fun isValid(input: String) = validationLambda(input)
}

abstract class Validator {
    abstract val errorMessage: String
    abstract fun isValid(input: String): Boolean
}
