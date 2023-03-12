package com.example.currentweatherdatabinding.dialogs.AlertDialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.app.AlertDialog

enum class WeatherDisplayDialogMode(val mode: String) {
    DETAILED("Detailed"),
    SIMPLE("Simple")
}

class WeatherDisplayDialog(private val ctx: Context, private val onAccept: (choice: WeatherDisplayDialogMode) -> Unit) : DialogFragment() {
    private var displayMode: WeatherDisplayDialogMode? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val choices = listOf(WeatherDisplayDialogMode.DETAILED, WeatherDisplayDialogMode.SIMPLE)
        var choice = if (displayMode == WeatherDisplayDialogMode.DETAILED) 1 else 0

        return ctx.let { it ->
            AlertDialog
                .Builder(it)
                .setTitle("Choose weather display mode:")
                .setSingleChoiceItems(
                    (choices.map { it.mode }).toTypedArray(),
                    choice
                ) { _, which ->
                    choice = which
                }
                .setPositiveButton("Accept") { _, _ ->
                    displayMode = choices[choice]
                    onAccept(displayMode!!)
                }
                .setNegativeButton("Cancel", null)
                .create()
        }
    }
}