package com.project.trackmydayapp.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.project.trackmydayapp.model.FeedbackModel
import com.project.trackmydayapp.model.FoodModel
import com.project.trackmydayapp.model.UserModel
import com.project.trackmydayapp.model.UserProfileModel

//creating the database logic, extending the SQLiteOpenHelper base class  
class DatabaseHandler(val context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "TrackMyDayDb"
        private val REGISTRATION_TABLE = "RegistrationTable"
        private val KEY_REG_ID = "Reg_id"
        private val KEY_REG_EMAIL = "Email"
        private val KEY_REG_PASSWORD = "Password"

        private val PROFILE_TABLE = "ProfileTable"
        private val KEY_PROFILE_ID = "ProfileId"
        private val KEY_PROFILE_REGID = "ProfileRegId"
        private val KEY_FIRST_NAME = "FirstName"
        private val KEY_HEIGHT = "Height"
        private val KEY_WEIGHT = "Weight"
        private val KEY_AGE = "Age"
        private val KEY_GENDER = "Gender"

        private val FOOD_TABLE = "FoodTable"
        private val KEY_FOOD_ID = "FoodId"
        private val KEY_FOOD_NAME = "FoodName"
        private val KEY_COMMON_NAME = "CommonName"
        private val KEY_DESCRIPTION = "FoodDescription"
        private val KEY_NITROGEN_FACTOR = "NitrogenFactor"
        private val KEY_FAT_FACTOR = "FatFactor"
        private val KEY_SPECIFIC_GRAVITY = "SpecificGravity"
        private val KEY_SAMPLING_DETAILS = "SamplingDetail"
        private val KEY_ANALYSED_PORTION = "AnalysedPortion"
        private val KEY_UNANALYSED_PORTION = "UnanalysedPortion"

        private val FEEDBACK_TABLE = "FeedBackTable"
        private val KEY_FEEDBACK_ID = "FeedBackTable"
        private val KEY_FEEDBACK_USERID = "FeedBackUserId"
        private val KEY_FEEDBACK_TITLE = "FeedbackTitle"
        private val KEY_FEEDBACK_DES = "FeedBack"

    }


    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_REG_TABLE =
            ("CREATE TABLE " + REGISTRATION_TABLE + "(" + KEY_REG_ID + " INTEGER PRIMARY KEY," + KEY_REG_EMAIL + " TEXT," + KEY_REG_PASSWORD + " TEXT" + ")")
        val CREATE_PROFILE_TABLE =
            ("CREATE TABLE " + PROFILE_TABLE + "(" + KEY_PROFILE_ID + " INTEGER PRIMARY KEY," + KEY_PROFILE_REGID + " INTEGER," + KEY_FIRST_NAME + " TEXT," + KEY_HEIGHT + " REAL," + KEY_WEIGHT + " REAL," + KEY_AGE + " INTEGER," + KEY_GENDER + " TEXT" + ")")
        val CREATE_FOOOD_TABLE =
            ("CREATE TABLE " + FOOD_TABLE + "(" + KEY_FOOD_ID + " INTEGER PRIMARY KEY," + KEY_FOOD_NAME + " TEXT," + KEY_COMMON_NAME + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_NITROGEN_FACTOR + " REAL," + KEY_FAT_FACTOR + " REAL," + KEY_SPECIFIC_GRAVITY + " REAL," + KEY_SAMPLING_DETAILS + " TEXT," + KEY_ANALYSED_PORTION + " REAL," + KEY_UNANALYSED_PORTION + " REAL" + ")")
        val CREATE_FEEDBACK_TABLE =
            ("CREATE TABLE " + FEEDBACK_TABLE + "(" + KEY_FEEDBACK_ID + " INTEGER PRIMARY KEY," + KEY_FEEDBACK_USERID + " INTEGER," + KEY_FEEDBACK_TITLE + " TEXT," + KEY_FEEDBACK_DES + " TEXT" + ")")
        db?.execSQL(CREATE_REG_TABLE)
        db?.execSQL(CREATE_PROFILE_TABLE)
        db?.execSQL(CREATE_FOOOD_TABLE)
        db?.execSQL(CREATE_FEEDBACK_TABLE)
        val contentValues = ContentValues()
        contentValues.put(KEY_REG_EMAIL, "admin@gmail.com")
        contentValues.put(KEY_REG_PASSWORD, "123456")
        val success = db?.insert(REGISTRATION_TABLE, null, contentValues)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + REGISTRATION_TABLE)
        db!!.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE)
        db!!.execSQL("DROP TABLE IF EXISTS " + FOOD_TABLE)
        db!!.execSQL("DROP TABLE IF EXISTS " + FEEDBACK_TABLE)
        onCreate(db)
    }


    ///method to insert data
    fun addFeedback(userid: Int, title: String, description: String): Long {
        val dbase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_FEEDBACK_USERID, userid)
        contentValues.put(KEY_FEEDBACK_TITLE, title)
        contentValues.put(KEY_FEEDBACK_DES, description)
        // Inserting Row
        val success = dbase.insert(FEEDBACK_TABLE, null, contentValues)
        //2nd argument is String containing nullColumnHack
        dbase.close() // Closing database connection
        return success
    }

    fun addUser(email: String, password: String): Long {
        val dbase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_REG_EMAIL, email)
        contentValues.put(KEY_REG_PASSWORD, password)
        // Inserting Row
        val success = dbase.insert(REGISTRATION_TABLE, null, contentValues)
        //2nd argument is String containing nullColumnHack
        dbase.close() // Closing database connection
        return success
    }

    ///method to insert data
    fun addProfileUser(
        regid: Int,
        firstName: String,
        weight: Double,
        height: Double,
        age: String,
        gender: String
    ): Long {
        val dbase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_PROFILE_REGID, regid)
        contentValues.put(KEY_FIRST_NAME, firstName)
        contentValues.put(KEY_WEIGHT, weight)
        contentValues.put(KEY_HEIGHT, height)
        contentValues.put(KEY_AGE, age)
        contentValues.put(KEY_GENDER, gender)
        // Inserting Row
        val success = dbase.insert(PROFILE_TABLE, null, contentValues)
        //2nd argument is String containing nullColumnHack
        dbase.close() // Closing database connection
        return success
    }

    ///method to insert data
    fun addFood(
        foodname: String,
        commonname: String,
        description: String,
        nitrogen: Double,
        fat: Double,
        specificgravity: Int,
        analysedportion: Int,
        unanalysedportion: Int,
        samplingdetails: String,
    ): Long {
        val dbase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_FOOD_NAME, foodname)
        contentValues.put(KEY_COMMON_NAME, commonname)
        contentValues.put(KEY_DESCRIPTION, description)
        contentValues.put(KEY_NITROGEN_FACTOR, nitrogen)
        contentValues.put(KEY_FAT_FACTOR, fat)
        contentValues.put(KEY_SPECIFIC_GRAVITY, specificgravity)
        contentValues.put(KEY_ANALYSED_PORTION, analysedportion)
        contentValues.put(KEY_UNANALYSED_PORTION, unanalysedportion)
        contentValues.put(KEY_SAMPLING_DETAILS, samplingdetails)
        // Inserting Row
        val success = dbase.insert(FOOD_TABLE, null, contentValues)
        dbase.close() // Closing database connection
        return success
    }

    //method to read data
    @SuppressLint("Range")
    fun viewFood(foodid: Int): ArrayList<FoodModel> {
        val foodlist: ArrayList<FoodModel> = ArrayList<FoodModel>()
        val selectQuery = "SELECT  * FROM $FOOD_TABLE where " + KEY_FOOD_ID + " = '" + foodid + "'"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var foodid: Int
        var foodname: String
        var commonname: String
        var description: String
        var nitrogen: Double
        var fat: Double
        var specificgravity: Int
        var analysedportion: Int
        var unanalysedportion: Int
        var samplingdetails: String
        if (cursor.moveToFirst()) {
            do {
                foodid = cursor.getInt(cursor.getColumnIndex(KEY_FOOD_ID))
                foodname = cursor.getString(cursor.getColumnIndex(KEY_FOOD_NAME))
                commonname = cursor.getString(cursor.getColumnIndex(KEY_COMMON_NAME))
                description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
                nitrogen = cursor.getDouble(cursor.getColumnIndex(KEY_NITROGEN_FACTOR))
                fat = cursor.getDouble(cursor.getColumnIndex(KEY_FAT_FACTOR))
                specificgravity = cursor.getInt(cursor.getColumnIndex(KEY_SPECIFIC_GRAVITY))
                analysedportion = cursor.getInt(cursor.getColumnIndex(KEY_ANALYSED_PORTION))
                unanalysedportion = cursor.getInt(cursor.getColumnIndex(KEY_UNANALYSED_PORTION))
                samplingdetails = cursor.getString(cursor.getColumnIndex(KEY_SAMPLING_DETAILS))
                foodlist.add(
                    FoodModel(
                        foodid,
                        foodname,
                        commonname,
                        description,
                        samplingdetails,
                        nitrogen,
                        fat,
                        specificgravity,
                        analysedportion,
                        unanalysedportion,
                    )
                )
            } while (cursor.moveToNext())
        }
        return foodlist
    }

    //method to read data
    @SuppressLint("Range")
    fun viewFood(): ArrayList<FoodModel> {
        val foodlist: ArrayList<FoodModel> = ArrayList<FoodModel>()
        val selectQuery = "SELECT  * FROM $FOOD_TABLE"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var foodid: Int
        var foodname: String
        var commonname: String
        var description: String
        var nitrogen: Double
        var fat: Double
        var specificgravity: Int
        var analysedportion: Int
        var unanalysedportion: Int
        var samplingdetails: String
        if (cursor.moveToFirst()) {
            do {
                foodid = cursor.getInt(cursor.getColumnIndex(KEY_FOOD_ID))
                foodname = cursor.getString(cursor.getColumnIndex(KEY_FOOD_NAME))
                commonname = cursor.getString(cursor.getColumnIndex(KEY_COMMON_NAME))
                description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
                nitrogen = cursor.getDouble(cursor.getColumnIndex(KEY_NITROGEN_FACTOR))
                fat = cursor.getDouble(cursor.getColumnIndex(KEY_FAT_FACTOR))
                specificgravity = cursor.getInt(cursor.getColumnIndex(KEY_SPECIFIC_GRAVITY))
                analysedportion = cursor.getInt(cursor.getColumnIndex(KEY_ANALYSED_PORTION))
                unanalysedportion = cursor.getInt(cursor.getColumnIndex(KEY_UNANALYSED_PORTION))
                samplingdetails = cursor.getString(cursor.getColumnIndex(KEY_SAMPLING_DETAILS))
                foodlist.add(
                    FoodModel(
                        foodid,
                        foodname,
                        commonname,
                        description,
                        samplingdetails,
                        nitrogen,
                        fat,
                        specificgravity,
                        analysedportion,
                        unanalysedportion,
                    )
                )
            } while (cursor.moveToNext())
        }
        return foodlist
    }

    @SuppressLint("Range")
    fun viewUser(): ArrayList<UserModel> {
        val userlist: ArrayList<UserModel> = ArrayList<UserModel>()
        val selectQuery = "SELECT  * FROM $REGISTRATION_TABLE" + " where " + KEY_REG_ID + " != 1"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userId: Int
        var userEmail: String
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex(KEY_REG_ID))
                userEmail = cursor.getString(cursor.getColumnIndex(KEY_REG_EMAIL))
                userlist.add(UserModel(userId, userEmail))
            } while (cursor.moveToNext())
        }
        return userlist
    }

    @SuppressLint("Range")
    fun viewFeedback(): ArrayList<FeedbackModel> {
        val userlist: ArrayList<FeedbackModel> = ArrayList<FeedbackModel>()
        val selectQuery = "SELECT  * FROM $FEEDBACK_TABLE"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userId: Int
        var feedbackId: Int
        var title: String
        var feedback: String
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex(KEY_FEEDBACK_USERID))
                feedbackId = cursor.getInt(cursor.getColumnIndex(KEY_FEEDBACK_ID))
                title = cursor.getString(cursor.getColumnIndex(KEY_FEEDBACK_TITLE))
                feedback = cursor.getString(cursor.getColumnIndex(KEY_FEEDBACK_DES))
                userlist.add(FeedbackModel(userId, feedbackId, title, feedback))
            } while (cursor.moveToNext())
        }
        return userlist
    }

    //method to read data
    @SuppressLint("Range")
    fun viewProfileUser(regid: Int): ArrayList<UserProfileModel> {
        val userlist: ArrayList<UserProfileModel> = ArrayList<UserProfileModel>()
        val selectQuery =
            "SELECT  * FROM $PROFILE_TABLE  Where " + KEY_PROFILE_REGID + " = '" + regid + "'"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            println("count" + e.message)

            return ArrayList()
        }
        println("count" + cursor.count)
        var profile_id: Int
        var reg_id: Int
        var firstName: String
        var gender: String
        var height: Double
        var weight: Double
        var age: Double
        if (cursor.moveToFirst()) {
            do {
                profile_id = cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_ID))
                reg_id = cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_REGID))
                firstName = cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME))
                height = cursor.getDouble(cursor.getColumnIndex(KEY_HEIGHT))
                weight = cursor.getDouble(cursor.getColumnIndex(KEY_WEIGHT))
                age = cursor.getDouble(cursor.getColumnIndex(KEY_AGE))
                gender = cursor.getString(cursor.getColumnIndex(KEY_GENDER))
                userlist.add(
                    UserProfileModel(
                        profile_id,
                        reg_id,
                        firstName,
                        gender,
                        height,
                        weight,
                        age
                    )
                )
            } while (cursor.moveToNext())
        }
        return userlist
    }

    //method to read data
    @SuppressLint("Range")
    fun login(email: String, password: String): Int {
        val selectQuery =
            "SELECT  * FROM $REGISTRATION_TABLE" + " Where " + KEY_REG_EMAIL + " = \'" + email + "\' AND " + KEY_REG_PASSWORD + " = " + password
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return 0;
        }
        var userId: Int
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex(KEY_REG_ID))
                return userId;
            } while (cursor.moveToNext())
        }
        return 0
    }//method to read data

    @SuppressLint("Range")
    fun getUserId(): Int {
        val selectQuery =
            "SELECT *  FROM " + REGISTRATION_TABLE + " WHERE  " + KEY_REG_ID + " = (SELECT MAX(" + KEY_REG_ID + ")  FROM " + REGISTRATION_TABLE + " );"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return 0;
        }
        var userId: Int
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex(KEY_REG_ID))
                return userId;
            } while (cursor.moveToNext())
        }
        return 0
    }

    //method to read data
    @SuppressLint("Range")
    fun isProfile(id: Int): Boolean {
        val selectQuery =
            "SELECT  * FROM $PROFILE_TABLE" + " Where " + KEY_PROFILE_REGID + " = \'" + id + "\' "
        val db = this.readableDatabase
        var cursor: Cursor? = null
        var status = false
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return status;
        }
        if (cursor.moveToFirst()) {
            do {
                status = true
                return status;
            } while (cursor.moveToNext())
        }
        return status
    }


    //method to update data
    fun updateFood(
        foodid: Int,
        foodname: String,
        commonname: String,
        description: String,
        nitrogen: Double,
        fat: Double,
        specificgravity: Int,
        analysedportion: Int,
        unanalysedportion: Int,
        samplingdetails: String,
    ): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_FOOD_NAME, foodname)
        contentValues.put(KEY_COMMON_NAME, commonname)
        contentValues.put(KEY_DESCRIPTION, description)
        contentValues.put(KEY_NITROGEN_FACTOR, nitrogen)
        contentValues.put(KEY_FAT_FACTOR, fat)
        contentValues.put(KEY_SPECIFIC_GRAVITY, specificgravity)
        contentValues.put(KEY_ANALYSED_PORTION, analysedportion)
        contentValues.put(KEY_UNANALYSED_PORTION, unanalysedportion)
        contentValues.put(KEY_SAMPLING_DETAILS, samplingdetails)

        // Updating Row  
        val success =
            db.update(FOOD_TABLE, contentValues, KEY_FOOD_ID + " = '" + foodid + "'", null)
        db.close() // Closing database connection
        return success
    }

    //method to delete data
    fun deleteFood(foodid: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //   contentValues.put(KEY_ID, userModel.userId)
        // Deleting Row  
        val success = db.delete(FOOD_TABLE, KEY_FOOD_ID + "= ' " + foodid + " ' ", null)
        //2nd argument is String containing nullColumnHack  
        db.close() // Closing database connection  
        return success
    }

    //method to delete data
    fun deleteUser(userModel: UserModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //   contentValues.put(KEY_ID, userModel.userId)
        // Deleting Row
        val success = db.delete(REGISTRATION_TABLE, KEY_REG_ID + "=" + userModel.userId, null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to delete data
    fun deleteUser(userid: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //   contentValues.put(KEY_ID, userModel.userId)
        // Deleting Row
        val success = db.delete(REGISTRATION_TABLE, KEY_REG_ID + "=" + userid, null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    fun deleteProfileUser(userProfileModel: UserProfileModel): Int {
        val db = this.writableDatabase
        // Deleting Row
        val success =
            db.delete(PROFILE_TABLE, KEY_PROFILE_REGID + "=" + userProfileModel.pro_reg_id, null)
        db.close()
        return success
    }
}  