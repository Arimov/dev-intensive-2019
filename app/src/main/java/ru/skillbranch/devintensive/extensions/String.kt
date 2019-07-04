package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String{
    if(this.toString().length <= count){
        return this.toString().trimEnd()
    }
    else{
        return this.substring(0,count).trimEnd() + "..."
    }
}