package com.example.login

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

class DatabaseHelper2(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // create table sql query
    private val CREATE_CAMP_TABLE = ("CREATE TABLE " + TABLE_CAMP + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_REGNO + " TEXT,"+ COLUMN_USER_EMAIL + " TEXT,"+ COLUMN_USER_CGPARANK + " TEXT," + COLUMN_USER_ROOM + " TEXT," +COLUMN_USER_BLOCK + " TEXT,"+ COLUMN_USER_ACNONAC + " TEXT" + ")")

    // drop table sql query
    private val DROP_CAMP_TABLE = "DROP TABLE IF EXISTS $TABLE_CAMP"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_CAMP_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        //Drop User Table if exist
        db.execSQL(DROP_CAMP_TABLE)

        // Create tables again
        onCreate(db)

    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    fun viewUser():List<Campaign>{
        val empList:ArrayList<Campaign> = ArrayList<Campaign>()
        val selectQuery = "SELECT  * FROM $TABLE_CAMP"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var regno: String
        var email: String
        var cgparank: String
        var room: String
        var block: String
        var acnonac: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt()
                regno= cursor.getString(cursor.getColumnIndex(COLUMN_USER_REGNO))
                cgparank = cursor.getString(cursor.getColumnIndex(COLUMN_USER_CGPARANK))
                email =cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL))
                room = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROOM))

                block = cursor.getString(cursor.getColumnIndex(COLUMN_USER_BLOCK))
                acnonac = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ACNONAC))
                val emp= Campaign(id = id, regno = regno, cgparank = cgparank ,email = email ,room = room ,block = block ,acnonac = acnonac )
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }
    fun getAllUser(): List<Campaign> {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID, COLUMN_USER_REGNO, COLUMN_USER_CGPARANK,COLUMN_USER_EMAIL, COLUMN_USER_ROOM,COLUMN_USER_BLOCK,COLUMN_USER_ACNONAC)

        // sorting orders
        val sortOrder = "$COLUMN_USER_REGNO ASC"
        val userList = ArrayList<Campaign>()

        val db = this.readableDatabase

        // query the user table
        val cursor = db.query(TABLE_CAMP, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder)         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val campaign = Campaign(id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt(),
                    regno= cursor.getString(cursor.getColumnIndex(COLUMN_USER_REGNO)),
                    cgparank = cursor.getString(cursor.getColumnIndex(COLUMN_USER_CGPARANK)),
                    email =cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)),
                    room = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROOM)),

                    block = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROOM)),
                acnonac = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROOM)))

                userList.add(campaign)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }


    /**
     * This method is to create user record
     *
     * @param user
     */
    fun addUser(campaign: Campaign) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_REGNO, campaign.regno)
        values.put(COLUMN_USER_CGPARANK, campaign.cgparank)
        values.put(COLUMN_USER_EMAIL, campaign.email)

        values.put(COLUMN_USER_ROOM, campaign.room)
        values.put(COLUMN_USER_BLOCK, campaign.block)
        values.put(COLUMN_USER_ACNONAC, campaign.acnonac)

        // Inserting Row
        db.insert(TABLE_CAMP, null, values)
        db.close()
    }

    fun updateUser(campaign: Campaign) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_REGNO, campaign.regno)
        values.put(COLUMN_USER_CGPARANK, campaign.cgparank)
        values.put(COLUMN_USER_EMAIL, campaign.email)

        values.put(COLUMN_USER_ROOM, campaign.room)
        values.put(COLUMN_USER_BLOCK, campaign.block)
        values.put(COLUMN_USER_ACNONAC, campaign.acnonac)

        // updating row
        db.update(TABLE_CAMP, values, "$COLUMN_USER_ID = ?",
            arrayOf(campaign.id.toString()))
        db.close()
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    fun deleteUser(campaign: Campaign) {

        val db = this.writableDatabase
        // delete user record by id
        db.delete(TABLE_CAMP, "$COLUMN_USER_ID = ?",
            arrayOf(campaign.id.toString()))
        db.close()


    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    fun checkUser(regno: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_USER_CGPARANK = ?"

        // selection argument
        val selectionArgs = arrayOf(regno)

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(TABLE_CAMP, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order


        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0) {
            return true
        }

        return false
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    fun checkUser(regno: String, cgparank: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)

        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_USER_CGPARANK = ? AND $COLUMN_USER_ROOM = ?"

        // selection arguments
        val selectionArgs = arrayOf(regno, cgparank)

        // query user table with conditions

        val cursor = db.query(TABLE_CAMP, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0)
            return true

        return false

    }

    companion object {

        // Database Version
        private val DATABASE_VERSION = 1

        // Database Name
        private val DATABASE_NAME = "campa.db"

        // User table name
        private val TABLE_CAMP = "camp"

        // User Table Columns names
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_REGNO= "user_regno"
        private val COLUMN_USER_EMAIL="user_email"
        private val COLUMN_USER_CGPARANK = "user_cgparank"
        private val COLUMN_USER_ROOM = "user_room"
        private val COLUMN_USER_BLOCK= "user_block"
        private val COLUMN_USER_ACNONAC= "user_acnonac"

    }

}
