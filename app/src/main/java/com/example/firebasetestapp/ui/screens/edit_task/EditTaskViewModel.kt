package com.example.firebasetestapp.ui.screens.edit_task

import androidx.compose.runtime.mutableStateOf
import com.example.firebasetestapp.FirebasetestAppViewModel
import com.example.firebasetestapp.components.idFromParameter
import com.example.firebasetestapp.service.StorageService
import com.example.firebasetestapp.service.Task
import com.example.firebasetestapp.ui.screens.TASK_DEFAULT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    logService: LogService,
    private val storageService: StorageService,
) : FirebasetestAppViewModel(logService){
    val task = mutableStateOf(Task())

    fun initialize(taskId: String){
      launchCatching {
          if(taskId != TASK_DEFAULT_ID){
              task.value = storageService.getTask(taskId.idFromParameter())  ?:Task()
          }
      }
    }
    fun onTitleChange(newValue: String){
        task.value = task.value.copy(title = newValue)
    }
    fun onDescriptionChange(newValue: String){
        task.value = task.value.copy(description = newValue)
    }

    fun onUrlChange(newValue:String){
        task.value = task.value.copy(url = newValue)
    }
    fun onDateChange(newValue: Long){
        val calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC) )
        calendar.timeInMillis = newValue
        val newDueDate = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(calendar.time)
        task.value = task.value.copy(dueDate = newDueDate)
    }
    fun onTimeChange (hour: Int, minute: Int){
        val newDueTime = "${hour.toClockPattern()}:${minute.toClockPattern()}"
        task.value = task.value.copy(dueTime = newDueTime)
    }
    fun onFlagToggle(newValue: String){
        val newFlagOption = EditFlagOption.getBooleanValue(newValue )
        task.value = task.value.copy(flag = newFlagOption)
    }
    fun onPriorityChange(newValue: String) {
        task.value = task.value.copy(priority = newValue)
    }

    fun onDoneClick(popUpScreen: () -> Unit) {
        launchCatching {
            val editedTask = task.value
            if (editedTask.id.isBlank()) {
                storageService.save(editedTask)
            } else {
                storageService.update(editedTask)
            }
            popUpScreen()
        }
    }


    private fun Int.toClockPattern(): String {
        return if (this < 10) "0$this" else "$this"
    }
    companion object {
        private const val UTC = "UTC"
        private const val DATE_FORMAT = "EEE, d MMM yyyy"
    }
}