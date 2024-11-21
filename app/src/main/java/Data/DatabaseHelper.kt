package Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appgym.R

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_info.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_LAST_NAME = "last_name"
        const val COLUMN_AGE = "age"
        const val COLUMN_GENDER = "gender"
        const val COLUMN_NATIONALITY = "nationality"
        const val COLUMN_WEIGHT = "weight"
        const val COLUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_NAME TEXT,"
                + "$COLUMN_LAST_NAME TEXT,"
                + "$COLUMN_AGE INTEGER,"
                + "$COLUMN_GENDER TEXT,"
                + "$COLUMN_NATIONALITY TEXT,"
                + "$COLUMN_WEIGHT REAL,"
                + "$COLUMN_IMAGE BLOB)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}

