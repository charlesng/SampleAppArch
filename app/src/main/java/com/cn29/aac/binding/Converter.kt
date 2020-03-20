package com.cn29.aac.binding

import androidx.databinding.InverseMethod

/**
 * Created by Charles Ng on 8/9/2017. From @George Mount answer https://stackoverflow.com/questions/44769054/android-two-way-databinding-for-methods-with-parameters/44782542#44782542
 * The inverse method can also be in class scope , not only in static scope
 * ***Can be extended
 */
open class Converter protected constructor() {
    @InverseMethod("fromDouble")
    fun toDouble(text: String?): Double {
        return java.lang.Double.valueOf(text!!)
    }

    fun fromDouble(value: Double): String {
        return value.toString()
    }

    @InverseMethod("fromInteger")
    fun toInteger(text: String?): Int {
        return Integer.valueOf(text!!)
    }

    fun fromInteger(value: Int): String {
        return value.toString()
    }

    companion object {
        private var converter: Converter? = null
        val default: Converter?
            get() {
                if (converter == null) {
                    converter = Converter()
                }
                return converter
            }
    }
}