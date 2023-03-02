package com.example

import org.ktorm.database.Database

object DatabaseFactory {

    private var database:Database? = null

    fun get() : Database {
        return database!!
    }


    fun create(url:String,user:String,password:String) : Database {
        if(database == null) {
            database = Database.connect(
                url = url,
                user = user,
                password = password
            )
        }

        return database!!
    }
}