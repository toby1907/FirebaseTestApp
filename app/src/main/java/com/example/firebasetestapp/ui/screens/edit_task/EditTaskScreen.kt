package com.example.firebasetestapp.ui.screens.edit_task

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firebasetestapp.R
import com.example.firebasetestapp.components.BasicField
import com.example.firebasetestapp.components.CardSelector
import com.example.firebasetestapp.components.RegularCardEditor
import com.example.firebasetestapp.components.card
import com.example.firebasetestapp.components.fieldModifier
import com.example.firebasetestapp.components.spacer
import com.example.firebasetestapp.components.toolbarActions
import com.example.firebasetestapp.components.toolbars.ActionToolbar
import com.example.firebasetestapp.service.Priority
import com.example.firebasetestapp.service.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


@Composable
fun EditTaskScreen(
    popUpScreen: () -> Unit,
    taskId: String,
    modifier: Modifier = Modifier,
    viewModel: EditTaskViewModel = hiltViewModel()
) {
    val task by viewModel.task
    LaunchedEffect(Unit) { viewModel.initialize(taskId) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolbar(
            title = R.string.edit_task,
            endActionIcon =R.drawable.ic_check,
            modifier =Modifier.toolbarActions(),
            endAction = { viewModel.onDoneClick(popUpScreen)}
        )
        Spacer(modifier = Modifier.spacer())

        val fieldModifier = Modifier.fieldModifier()
        BasicField(
            text = R.string.title,
            value = task.title,
            onNewValue = viewModel::onTitleChange,
            modifier = fieldModifier
        )
        BasicField(R.string.description, task.description, viewModel::onDescriptionChange, fieldModifier)
        BasicField(R.string.url, task.url, viewModel::onUrlChange, fieldModifier)

        Spacer(modifier = Modifier.spacer())
        CardEditors(task, viewModel::onDateChange, viewModel::onTimeChange)
        CardSelectors(task, viewModel::onPriorityChange, viewModel::onFlagToggle)

        Spacer(modifier = Modifier.spacer())
    }
}


@Composable
private fun CardEditors(
    task: Task,
    onDateChange: (Long) -> Unit,
    onTimeChange: (Int, Int) -> Unit
) {
    val activity = LocalContext.current as AppCompatActivity

    RegularCardEditor(R.string.date, R.drawable.ic_calendar, task.dueDate, Modifier.card()) {
        showDatePicker(activity, onDateChange)
    }

    RegularCardEditor(R.string.time, R.drawable.ic_clock, task.dueTime, Modifier.card()) {
        showTimePicker(activity, onTimeChange)
    }
}

@Composable
private fun CardSelectors(
    task: Task,
    onPriorityChange: (String) -> Unit,
    onFlagToggle: (String) -> Unit
) {
    val prioritySelection = Priority.getByName(task.priority).name
    CardSelector(R.string.priority, Priority.getOptions(), prioritySelection, Modifier.card()) {
            newValue ->
        onPriorityChange(newValue)
    }

    val flagSelection = EditFlagOption.getByCheckedState(task.flag).name
    CardSelector(R.string.flag, EditFlagOption.getOptions(), flagSelection, Modifier.card()) { newValue
        ->
        onFlagToggle(newValue)
    }
}

private fun showDatePicker(activity: AppCompatActivity?, onDateChange: (Long) -> Unit) {
    val picker = MaterialDatePicker.Builder.datePicker().build()

    activity?.let {
        picker.show(it.supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener { timeInMillis -> onDateChange(timeInMillis) }
    }
}

private fun showTimePicker(activity: AppCompatActivity?, onTimeChange: (Int, Int) -> Unit) {
    val picker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()

    activity?.let {
        picker.show(it.supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener { onTimeChange(picker.hour, picker.minute) }
    }
}

