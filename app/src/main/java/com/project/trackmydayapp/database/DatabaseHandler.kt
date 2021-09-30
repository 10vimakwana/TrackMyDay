package com.project.trackmydayapp.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.project.trackmydayapp.model.*

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
        private val KEY_ACTIVITY = "Activity"

        private val FOOD_TABLE = "FoodTable"
        private val KEY_FOOD_ID = "FoodId"
        private val KEY_FOOD_NAME = "FoodName"
        private val KEY_COMMON_NAME = "CommonName"
        private val KEY_DESCRIPTION = "FoodDescription"
        private val KEY_NITROGEN_FACTOR = "NitrogenFactor"
        private val KEY_FAT_FACTOR = "FatFactor"
        private val KEY_CALORIES_FACTOR = "CaloriesFactor"
        private val KEY_SPECIFIC_GRAVITY = "SpecificGravity"
        private val KEY_SAMPLING_DETAILS = "SamplingDetail"
        private val KEY_ANALYSED_PORTION = "AnalysedPortion"
        private val KEY_UNANALYSED_PORTION = "UnanalysedPortion"

        private val FEEDBACK_TABLE = "FeedBackTable"
        private val KEY_FEEDBACK_ID = "FeedBackTable"
        private val KEY_FEEDBACK_USERID = "FeedBackUserId"
        private val KEY_FEEDBACK_TITLE = "FeedbackTitle"
        private val KEY_FEEDBACK_DES = "FeedBack"

        private val STEP_TABLE = "StepTable"
        private val KEY_STEP_ID = "StepId"
        private val KEY_STEP_USERID = "StepUserId"
        private val KEY_STEPS = "Steps"
        private val KEY_STEP_DATE = "StepDate"


        private val RECIPE_TABLE = "RecipeTable"
        private val KEY_RECIPE_ID = "RecipeId"
        private val KEY_RECIPE_USERID = "RecipeUserId"
        private val KEY_RECIPE_FOODID = "RecipeFoodId"
        private val KEY_RECIPE_CALORIES = "RecipeCalories"
        private val KEY_RECIPE_NAME = "RecipeName"
        private val KEY_RECIPE_DATE = "RecipeDate"

    }


    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_REG_TABLE =
            ("CREATE TABLE " + REGISTRATION_TABLE + "(" + KEY_REG_ID + " INTEGER PRIMARY KEY," + KEY_REG_EMAIL + " TEXT," + KEY_REG_PASSWORD + " TEXT" + ")")
        val CREATE_PROFILE_TABLE =
            ("CREATE TABLE " + PROFILE_TABLE + "(" + KEY_PROFILE_ID + " INTEGER PRIMARY KEY," + KEY_PROFILE_REGID + " INTEGER," + KEY_FIRST_NAME + " TEXT," + KEY_HEIGHT + " REAL," + KEY_WEIGHT + " REAL," + KEY_AGE + " INTEGER," + KEY_ACTIVITY + " TEXT," + KEY_GENDER + " TEXT" + ")")
        val CREATE_FOOOD_TABLE =
            ("CREATE TABLE " + FOOD_TABLE + "(" + KEY_FOOD_ID + " INTEGER PRIMARY KEY," + KEY_FOOD_NAME + " TEXT," + KEY_COMMON_NAME + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_NITROGEN_FACTOR + " REAL," + KEY_FAT_FACTOR + " REAL," + KEY_CALORIES_FACTOR + " INTEGER," + KEY_SPECIFIC_GRAVITY + " REAL," + KEY_SAMPLING_DETAILS + " TEXT," + KEY_ANALYSED_PORTION + " REAL," + KEY_UNANALYSED_PORTION + " REAL" + ")")
        val CREATE_FEEDBACK_TABLE =
            ("CREATE TABLE " + FEEDBACK_TABLE + "(" + KEY_FEEDBACK_ID + " INTEGER PRIMARY KEY," + KEY_FEEDBACK_USERID + " INTEGER," + KEY_FEEDBACK_TITLE + " TEXT," + KEY_FEEDBACK_DES + " TEXT" + ")")
        val CREATE_STEP_TABLE =
            ("CREATE TABLE " + STEP_TABLE + "(" + KEY_STEP_ID + " INTEGER PRIMARY KEY," + KEY_STEP_USERID + " INTEGER," + KEY_STEPS + " INTEGER," + KEY_STEP_DATE + " TEXT" + ")")
        val CREATE_RECIPE_TABLE =
            ("CREATE TABLE " + RECIPE_TABLE + "(" + KEY_RECIPE_ID + " INTEGER PRIMARY KEY," + KEY_RECIPE_USERID + " INTEGER," + KEY_RECIPE_FOODID + " INTEGER," + KEY_RECIPE_CALORIES + " INTEGER," + KEY_RECIPE_NAME + " TEXT," + KEY_RECIPE_DATE + " TEXT" + ")")
        db?.execSQL(CREATE_REG_TABLE)
        db?.execSQL(CREATE_PROFILE_TABLE)
        db?.execSQL(CREATE_FOOOD_TABLE)
        db?.execSQL(CREATE_FEEDBACK_TABLE)
        db?.execSQL(CREATE_STEP_TABLE)
        db?.execSQL(CREATE_RECIPE_TABLE)
        val contentValues = ContentValues()
        contentValues.put(KEY_REG_EMAIL, "admin@gmail.com")
        contentValues.put(KEY_REG_PASSWORD, "123456")
        val success = db?.insert(REGISTRATION_TABLE, null, contentValues)
        val food1 = ContentValues()
//        food1.put(KEY_FOOD_NAME, foodname)
//        food1.put(KEY_COMMON_NAME, commonname)
//        food1.put(KEY_DESCRIPTION, description)
//        food1.put(KEY_NITROGEN_FACTOR, nitrogen)
//        food1.put(KEY_FAT_FACTOR, fat)
//        food1.put(KEY_SPECIFIC_GRAVITY, specificgravity)
//        food1.put(KEY_ANALYSED_PORTION, analysedportion)
//        food1.put(KEY_UNANALYSED_PORTION, unanalysedportion)
//        food1.put(KEY_SAMPLING_DETAILS, samplingdetails)
        val food1suc = db?.insert(REGISTRATION_TABLE, null, food1)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + REGISTRATION_TABLE)
        db!!.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE)
        db!!.execSQL("DROP TABLE IF EXISTS " + FOOD_TABLE)
        db!!.execSQL("DROP TABLE IF EXISTS " + FEEDBACK_TABLE)
        db!!.execSQL("DROP TABLE IF EXISTS " + STEP_TABLE)
        db!!.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE)
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

    ///method to insert data
    fun addStep(userid: Int, steps: Int, date: String): Long {
        val dbase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_STEP_USERID, userid)
        contentValues.put(KEY_STEPS, steps)
        contentValues.put(KEY_STEP_DATE, date)
        // Inserting Row
        val success = dbase.insert(STEP_TABLE, null, contentValues)
        //2nd argument is String containing nullColumnHack
        dbase.close() // Closing database connection
        return success
    }

    ///method to insert data
    fun addRecipe(
        userid: Int,
        foodid: Int,
        calories: Int,
        recipeName: String,
        recipeDate: String
    ): Long {
        val dbase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_RECIPE_USERID, userid)
        contentValues.put(KEY_RECIPE_FOODID, foodid)
        contentValues.put(KEY_RECIPE_CALORIES, calories)
        contentValues.put(KEY_RECIPE_NAME, recipeName)
        contentValues.put(KEY_RECIPE_DATE, recipeDate)
        // Inserting Row
        val success = dbase.insert(RECIPE_TABLE, null, contentValues)
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
        gender: String,
        activity: String
    ): Long {
        val dbase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_PROFILE_REGID, regid)
        contentValues.put(KEY_FIRST_NAME, firstName)
        contentValues.put(KEY_WEIGHT, weight)
        contentValues.put(KEY_HEIGHT, height)
        contentValues.put(KEY_AGE, age)
        contentValues.put(KEY_GENDER, gender)
        contentValues.put(KEY_ACTIVITY, activity)
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
        calories: Int,
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
        contentValues.put(KEY_CALORIES_FACTOR, calories)
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
        var calories: Int
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
                calories = cursor.getInt(cursor.getColumnIndex(KEY_CALORIES_FACTOR))
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
                        calories,
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
        var calories: Int
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
                calories = cursor.getInt(cursor.getColumnIndex(KEY_CALORIES_FACTOR))
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
                        calories,
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
    fun viewSteps(userid: Int, date: String): ArrayList<StepModel> {
        val userlist: ArrayList<StepModel> = ArrayList<StepModel>()
        val selectQuery =
            "SELECT  * FROM $STEP_TABLE" + " where " + KEY_STEP_USERID + " = '" + userid + "'" + " AND " + KEY_STEP_DATE + " = '" + date + "'"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var stepId: Int
        var userId: Int
        var step: Int
        var date: String
        if (cursor.moveToFirst()) {
            do {
                stepId = cursor.getInt(cursor.getColumnIndex(KEY_STEP_ID))
                userId = cursor.getInt(cursor.getColumnIndex(KEY_STEP_USERID))
                step = cursor.getInt(cursor.getColumnIndex(KEY_STEPS))
                date = cursor.getString(cursor.getColumnIndex(KEY_STEP_DATE))
                userlist.add(StepModel(stepId, userId, step, date))
            } while (cursor.moveToNext())
        }
        return userlist
    }

    @SuppressLint("Range")
    fun viewStepsCal(userid: Int, date: String): Int {
        var totalstep = 0
        val selectQuery = "SELECT SUM($KEY_STEPS) as Total FROM $STEP_TABLE"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return totalstep
        }
        if (cursor.moveToFirst()) {
            do {

                val total = cursor.getInt(cursor.getColumnIndex("Total"))
                totalstep = total;
            } while (cursor.moveToNext())
        }
        return totalstep
    }

    @SuppressLint("Range")
    fun viewRecipe(userid: Int, date: String): ArrayList<RecipeModel> {
        val userlist: ArrayList<RecipeModel> = ArrayList<RecipeModel>()
        val selectQuery =
            "SELECT  * FROM $RECIPE_TABLE" + " where " + KEY_RECIPE_USERID + " = '" + userid + "'" + " AND " + KEY_RECIPE_DATE + " = '" + date + "'"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var recipeId: Int
        var userId: Int
        var foodid: Int
        var recipeName: String
        var recipeDate: String
        if (cursor.moveToFirst()) {
            do {
                recipeId = cursor.getInt(cursor.getColumnIndex(KEY_RECIPE_ID))
                userId = cursor.getInt(cursor.getColumnIndex(KEY_RECIPE_USERID))
                foodid = cursor.getInt(cursor.getColumnIndex(KEY_RECIPE_FOODID))
                recipeName = cursor.getString(cursor.getColumnIndex(KEY_RECIPE_NAME))
                recipeDate = cursor.getString(cursor.getColumnIndex(KEY_RECIPE_DATE))
                userlist.add(RecipeModel(recipeId, userId, foodid, recipeName, recipeDate))
            } while (cursor.moveToNext())
        }
        return userlist
    }


    @SuppressLint("Range")
    fun viewRecipeCal(userid: Int, date: String): Int {
        var totalCalories = 0
        val selectQuery = "SELECT SUM($KEY_RECIPE_CALORIES) as Total FROM $RECIPE_TABLE"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return totalCalories
        }
        if (cursor.moveToFirst()) {
            do {
                val total = cursor.getInt(cursor.getColumnIndex("Total"))
                totalCalories = total;
            } while (cursor.moveToNext())
        }
        return totalCalories
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
        var activity: String
        if (cursor.moveToFirst()) {
            do {
                profile_id = cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_ID))
                reg_id = cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_REGID))
                firstName = cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME))
                height = cursor.getDouble(cursor.getColumnIndex(KEY_HEIGHT))
                weight = cursor.getDouble(cursor.getColumnIndex(KEY_WEIGHT))
                age = cursor.getDouble(cursor.getColumnIndex(KEY_AGE))
                gender = cursor.getString(cursor.getColumnIndex(KEY_GENDER))
                activity = cursor.getString(cursor.getColumnIndex(KEY_ACTIVITY))
                userlist.add(
                    UserProfileModel(
                        profile_id,
                        reg_id,
                        firstName,
                        gender,
                        height,
                        weight,
                        age,
                        activity
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
    }

    //method to read data
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