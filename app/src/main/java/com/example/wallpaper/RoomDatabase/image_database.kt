package com.example.wallpaper.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [image_entity::class], version = 2, exportSchema = false)
abstract class image_database :RoomDatabase() {


    abstract fun imagedao(): image_dao

    companion object {

        @Volatile
        private var INSTANCE: image_database? = null


        fun getinstance(context: Context): image_database {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.

            kotlin.synchronized(this) {
                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.


                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context, image_database::class.java, "image_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this lesson. You can learn more about
                            // migration with Room in this blog post:
                            // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                            .allowMainThreadQueries().build()
                    INSTANCE =
                        instance                             // Assign INSTANCE to the newly created database.
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}




