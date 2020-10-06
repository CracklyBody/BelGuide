package com.example.newsapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.SimpleDateFormat
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.util.*

object Utils {
    var vibrantLightColorList = arrayOf(
        ColorDrawable(Color.parseColor("#ffeead")),
        ColorDrawable(Color.parseColor("#93cfb3")),
        ColorDrawable(Color.parseColor("#fd7a7a")),
        ColorDrawable(Color.parseColor("#faca5f")),
        ColorDrawable(Color.parseColor("#1ba798")),
        ColorDrawable(Color.parseColor("#6aa9ae")),
        ColorDrawable(Color.parseColor("#ffbf27")),
        ColorDrawable(Color.parseColor("#d93947"))
    )
    fun getRandomDrawbleColor(): ColorDrawable {
        val idx: Int = Random().nextInt(vibrantLightColorList.size)
        return vibrantLightColorList[idx]
    }

    fun DateToTimeFormat(oldstringDate: String): String? {
        val p = PrettyTime(Locale(getCountry()))
        var isTime: String? = null
        try {
            val sdf = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.ENGLISH
            )
            val date: Date = sdf.parse(oldstringDate)
            isTime = p.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }

    fun DateFormat(oldstringDate: String): String? {
        val newDate: String
        val dateFormat = SimpleDateFormat("E, d MMM yyyy", Locale(getCountry()))
        newDate = try {
            val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate)
            dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            oldstringDate
        }
        return newDate
    }

    fun getCountry():String{
        val locale: Locale = Locale.getDefault()
        val country: String = java.lang.String.valueOf(locale.getCountry())
        return country.toLowerCase()
    }
    fun getLanguage(): String {
        val locale = Locale.getDefault()
        val country = locale.language.toString()
        return country.toLowerCase()
    }
}