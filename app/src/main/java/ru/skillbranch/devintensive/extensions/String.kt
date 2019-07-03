package ru.skillbranch.devintensive.extensions

import java.util.*

fun String.truncate(count: Int = 16): String{
    return this.substring(0,count).trim() + "..."
}