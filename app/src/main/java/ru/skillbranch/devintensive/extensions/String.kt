package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String{
    return this.substring(0,count).trimEnd() + "..."
}