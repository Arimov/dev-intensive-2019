package ru.skillbranch.devintensive.utils

object Utils {
    fun ParseFullName(fullName: String?):Pair<String?, String?>{
        val parts : List<String>? = fullName?.trim()?.split(" ")
        var firstName = parts?.getOrNull(0)
        if(firstName.isNullOrEmpty() || firstName.isNullOrBlank()) {
            firstName = null
        }
        var lastName = parts?.getOrNull(1)
        if(lastName.isNullOrEmpty() || lastName.isNullOrBlank()) {
            lastName = null
        }
        return firstName to lastName
    }


    fun toInitials(firstName: String?, lastName: String?): String?{
        val result: String
        var firstSymbol = ""
        var lastSymbol = ""
        if(!firstName.isNullOrEmpty() && !firstName.isNullOrBlank()){
            firstSymbol = firstName.substring(0,1).toUpperCase()
        }
        if(!lastName.isNullOrEmpty() && !lastName.isNullOrBlank()){
            lastSymbol = lastName.substring(0,1).toUpperCase()
        }
        result = firstSymbol + lastSymbol
        if(result == ""){
            return null
        }
        else{
            return result
        }
    }


    fun transliteration(payload: String?, divider: String = " "): String?{
        val translDist = mapOf(
            "а" to "a",

            "б" to "b",

            "в" to "v",

            "г" to "g",

            "д" to "d",

            "е" to "e",

            "ё" to "e",

            "ж" to "zh",

            "з" to "z",

            "и" to "i",

            "й" to "i",

            "к" to "k",

            "л" to "l",

            "м" to "m",

            "н" to "n",

            "о" to "o",

            "п" to "p",

            "р" to "r",

            "с" to "s",

            "т" to "t",

            "у" to "u",

            "ф" to "f",

            "х" to "h",

            "ц" to "c",

            "ч" to "ch",

            "ш" to "sh",

            "щ" to "sh'",

            "ъ" to "",

            "ы" to "i",

            "ь" to "",

            "э" to "e",

            "ю" to "yu",

            "я" to "ya",

            "А" to "A",

            "Б" to "B",

            "В" to "V",

            "Г" to "G",

            "Д" to "D",

            "Е" to "E",

            "Ё" to "E",

            "Ж" to "Zh",

            "З" to "Z",

            "И" to "I",

            "Й" to "I",

            "К" to "K",

            "Л" to "L",

            "М" to "M",

            "Н" to "N",

            "О" to "O",

            "П" to "P",

            "Р" to "R",

            "С" to "S",

            "Т" to "T",

            "У" to "U",

            "Ф" to "F",

            "Х" to "H",

            "Ц" to "C",

            "Ч" to "Ch",

            "Ш" to "Sh",

            "Щ" to "Sh'",

            "Ъ" to "",

            "Ы" to "I",

            "Ь" to "",

            "Э" to "E",

            "Ю" to "Yu",

            "Я" to "Ya"
        )
        if(payload == null) {
            return null
        }
        var result = ""
        val arChar = payload.toCharArray()
        for(c: Char in arChar){
            if(c.compareTo(" ".single())==0){
                result += divider
            }
            else{
                result += translDist["$c"]
            }
        }
        return result
    }
}
