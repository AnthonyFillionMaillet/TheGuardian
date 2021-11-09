package com.afillionmaillet.theguardian.core.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.toObliqueStrokeFormat(): String{
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(this)
}

fun Date.isDateInCurrentWeek(): Boolean {
    val currentCalendar = Calendar.getInstance()
    currentCalendar.firstDayOfWeek = Calendar.MONDAY
    val week = currentCalendar.get(Calendar.WEEK_OF_YEAR)

    val targetCalendar = Calendar.getInstance()
    targetCalendar.firstDayOfWeek = Calendar.MONDAY
    targetCalendar.time = this
    val targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR)
    return week == targetWeek
}

fun Date.isDateInLastWeek(): Boolean {
    val lastWeekCalendar = Calendar.getInstance()
    lastWeekCalendar.firstDayOfWeek = Calendar.MONDAY
    lastWeekCalendar.add(Calendar.WEEK_OF_YEAR, -1)
    val week = lastWeekCalendar.get(Calendar.WEEK_OF_YEAR)

    val targetCalendar = Calendar.getInstance()
    targetCalendar.firstDayOfWeek = Calendar.MONDAY
    targetCalendar.time = this
    val targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR)
    return week == targetWeek
}