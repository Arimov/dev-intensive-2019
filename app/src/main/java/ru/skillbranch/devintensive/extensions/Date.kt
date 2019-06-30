package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}


fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.humanizeDiff(date: Date): String{
    val diff = Date().getTime() - date.getTime()
    when(diff){
        in 0..1*1000 -> return("только что")
        in 1*1000+1..45*1000 -> return("несколько секунд назад")
        in 45*1000+1..75*1000 -> return("минуту назад")
        in 75*1000+1..45*60*1000 -> {
            val min: Int = (diff/1000/60).toInt()
            var minText = "минут"
            when(min.toString().substring(min.toString().length-1,1)){
               "1" -> minText = "минута"
               in (2..4).toString() -> minText = "минуты"
            }
            return("$min $minText назад")
        }
        in 45*60*1000+1..75*60*1000 -> return("час назад")
        in 75*60*1000+1..22*60*60*1000 -> {
            val hours: Int = (diff/1000/60/60).toInt()
            var hourText = "часов"
            when(hours.toString().substring(hours.toString().length-1,1)){
                "1" -> hourText = "час"
                in (2..4).toString() -> hourText = "часа"
            }
            return("$hours $hourText назад")
        }
        in 22*60*60*1000+1..26*60*60*1000 -> return("день назад")
        in 26*60*60*1000+1..360*24*60*60*1000 -> {
            val days: Int = (diff/1000/60/60/24).toInt()
            var daysText = "дней"
            when(days.toString().substring(days.toString().length-1,1)){
                "1" -> daysText = "день"
                in (2..4).toString() -> daysText = "дня"
            }
            return("$days $daysText назад")
        }
        else -> return("более года назад")
    }
}

/*
0с - 1с "только что"
1с - 45с "несколько секунд назад"
45с - 75с "минуту назад"
75с - 45мин "N минут назад"
45мин - 75мин "час назад"
75мин 22ч "N часов назад"
22ч - 26ч "день назад"
26ч - 360д "N дней назад"
>360д "более года назад"*/
