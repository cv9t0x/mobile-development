package com.example.recyclerviewcolorsk

import android.graphics.Color
import java.util.*
class Utils {
    companion object {
        fun generatePalette(keys: List<String>): Map<String, List<Int>> {
            val palette = mutableMapOf<String, List<Int>>()
            val rnd = Random()

            keys.forEach { item ->
                palette[item] = List(rnd.nextInt(2..5)) { Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))  }
            }

            return palette
        }
    }
}

private fun Random.nextInt(range: IntRange): Int {
    return range.first + nextInt(range.last - range.first)
}
