package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

class User (
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false
){
    constructor(id:String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar=null
    )

/*    private constructor(builder: Builder) : this(
        builder.id,
        builder.firstName,
        builder.lastName,
        builder.avatar,
        builder.rating,
        builder.respect,
        builder.lastVisit,
        builder.isOnline
        )*/

    companion object Factory{
        private var lastId : Int = -1
        fun makeUser(fullName:String?) : User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return  User(lastId.toString(), firstName = firstName,lastName = lastName)
        }
    }

    class Builder() {
        private var id: String = (++lastId).toString()
        private var firstName: String? = null
        private var lastName: String? = null
        private var avatar: String? = null
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = Date()
        private var isOnline: Boolean = false

        fun id(value: String): Builder {
            id = value
            return this
        }

        fun firstName(value: String?): Builder {
            firstName = value
            return this
        }

        fun lastName(value: String?): Builder {
            lastName = value
            return this
        }

        fun avatar(value: String?): Builder {
            avatar = value
            return this
        }

        fun rating(value: Int): Builder {
            rating = value
            return this
        }

        fun respect(value: Int): Builder {
            respect = value
            return this
        }

        fun lastVisit(value: Date?): Builder {
            lastVisit = value
            return this
        }

        fun isOnline(value: Boolean): Builder {
            isOnline = value
            return this
        }

        fun build() = User(
            id,
            firstName,
            lastName,
            avatar,
            rating,
            respect,
            lastVisit,
            isOnline
        )
    }
/*
    class Builder{
        var id: String = "0"
            private set

        var firstName: String? = null
            private set

        var lastName: String? = null
            private set

        var avatar: String? = null
            private set

        var rating : Int = 0
            private set

        var respect : Int = 0
            private set

        var lastVisit : Date? = Date()
            private set

        var isOnline : Boolean = false
            private set

        fun id(id: String) = apply { this.id = id }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun avatar(avatar: String?) = apply { this.avatar = avatar }
        fun rating(rating: Int) = apply { this.rating = rating }
        fun respect(respect: Int) = apply { this.respect = respect }
        fun lastVisit(lastVisit: Date?) = apply { this.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }
        fun build() = User(this)
    }*/
}