package com.example.currentweatherdatabinding.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.currentweatherdatabinding.App
import com.example.currentweatherdatabinding.R

enum class WeatherDisplayDialogMode(private val nameId: Int) {
    DETAILED(R.string.detailed_weather),
    SIMPLE(R.string.simple_weather);

    override fun toString(): String {
        return App.context?.resources?.getString(nameId) ?: "";
    }
}

class WeatherDisplayDialog(private val ctx: Context, private val onAccept: (choice: WeatherDisplayDialogMode) -> Unit) : DialogFragment() {
    private var displayMode: WeatherDisplayDialogMode? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val choices = listOf(WeatherDisplayDialogMode.DETAILED, WeatherDisplayDialogMode.SIMPLE)
        var choice = if (displayMode == WeatherDisplayDialogMode.DETAILED) 1 else 0

        return ctx.let { it ->
            AlertDialog
                .Builder(it)
                .setTitle(R.string.alert_dialog_title)
                .setSingleChoiceItems(
                    (choices.map { it.toString() }).toTypedArray(),
                    choice
                ) { _, which ->
                    choice = which
                }
                .setPositiveButton(R.string.accept) { _, _ ->
                    displayMode = choices[choice]
                    onAccept(displayMode!!)
                }
                .setNegativeButton(R.string.cancel, null)
                .create()
        }
    }
}