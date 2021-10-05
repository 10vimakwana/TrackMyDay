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
        private val KEY_REG_OTP = "OTP"

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
        private val KEY_FEEDBACK_ISREPLYBYADMIN = "FeedbackIsReply"
        private val KEY_FEEDBACK_ISSEND = "FeedbackIsSend"
        private val KEY_FEEDBACK_REPLY = "FeedbackReply"

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
            ("CREATE TABLE " + REGISTRATION_TABLE + "(" + KEY_REG_ID + " INTEGER PRIMARY KEY," + KEY_REG_EMAIL + " TEXT," + KEY_REG_OTP + " INTEGER," + KEY_REG_PASSWORD + " TEXT" + ")")
        val CREATE_PROFILE_TABLE =
            ("CREATE TABLE " + PROFILE_TABLE + "(" + KEY_PROFILE_ID + " INTEGER PRIMARY KEY," + KEY_PROFILE_REGID + " INTEGER," + KEY_FIRST_NAME + " TEXT," + KEY_HEIGHT + " REAL," + KEY_WEIGHT + " REAL," + KEY_AGE + " INTEGER," + KEY_ACTIVITY + " TEXT," + KEY_GENDER + " TEXT" + ")")
        val CREATE_FOOOD_TABLE =
            ("CREATE TABLE " + FOOD_TABLE + "(" + KEY_FOOD_ID + " INTEGER PRIMARY KEY," + KEY_FOOD_NAME + " TEXT," + KEY_COMMON_NAME + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_NITROGEN_FACTOR + " REAL," + KEY_FAT_FACTOR + " REAL," + KEY_CALORIES_FACTOR + " INTEGER," + KEY_SPECIFIC_GRAVITY + " REAL," + KEY_SAMPLING_DETAILS + " TEXT," + KEY_ANALYSED_PORTION + " REAL," + KEY_UNANALYSED_PORTION + " REAL" + ")")
        val CREATE_FEEDBACK_TABLE =
            ("CREATE TABLE " + FEEDBACK_TABLE + "(" + KEY_FEEDBACK_ID + " INTEGER PRIMARY KEY," + KEY_FEEDBACK_USERID + " INTEGER," + KEY_FEEDBACK_TITLE + " TEXT," + KEY_FEEDBACK_REPLY + " TEXT," + KEY_FEEDBACK_ISREPLYBYADMIN + " INTEGER," + KEY_FEEDBACK_ISSEND + " INTEGER," + KEY_FEEDBACK_DES + " TEXT" + ")")
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
        food1.put(KEY_FOOD_NAME, "Cardamom seed, dried, ground")
        food1.put(KEY_COMMON_NAME, "Elettaria cardamom")
        food1.put(
            KEY_DESCRIPTION,
            "Ground spice commonly used in Indian cooking and drinks, in Middle Eastern cooking and in Scandinavian baking"
        )
        food1.put(KEY_NITROGEN_FACTOR, 6.25)
        food1.put(KEY_FAT_FACTOR, 0.956)
        food1.put(KEY_CALORIES_FACTOR, 50)
        food1.put(KEY_SPECIFIC_GRAVITY, 0)
        food1.put(KEY_ANALYSED_PORTION, 100)
        food1.put(KEY_UNANALYSED_PORTION, 0)
        food1.put(
            KEY_SAMPLING_DETAILS,
            "The majority of nutrient data were borrowed from USDA SR28, 02006 - Spices, cardamom. Proximates (starch, total sugars), vitamins (alpha-tocopherol, beta-carotene, folate) and Se were borrowed from Indian Food Composition Tables 2017, G020 - Cardamom, green. Dry matter adjustment was applied to starch and sugar. Tryptophan was imputed from dried fenugreek seed. Alcohol, vitamins (B12, folic acid, retinol, D), I, caffeine and cholesterol were imputed zero. Note low sum of proximates - data should be used with caution."
        )
        db?.insert(FOOD_TABLE, null, food1)
        val food2 = ContentValues()
        food2.put(KEY_FOOD_NAME, "Chilli (chili), dried, ground")
        food2.put(KEY_COMMON_NAME, "Capsicum spp")
        food2.put(
            KEY_DESCRIPTION,
            "Dried and ground red pepper, characterised by a hot sensation when consumed as a spice."
        )
        food2.put(KEY_NITROGEN_FACTOR, 6.25)
        food2.put(KEY_FAT_FACTOR, 0.8)
        food2.put(KEY_CALORIES_FACTOR, 30)
        food2.put(KEY_SPECIFIC_GRAVITY, 0)
        food2.put(KEY_ANALYSED_PORTION, 100)
        food2.put(KEY_UNANALYSED_PORTION, 0)
        food2.put(
            KEY_SAMPLING_DETAILS,
            "The majority of nutrient data was borrowed USDA SR28, 02009 - Spices, chili powder. Iodine was borrowed from NZ FOODfiles 2014, P28 - Spice, chilli powder. Alcohol, vitamins (B12, folic acid, retinol, C, D), caffeine and cholesterol were imputed zero."
        )
        db?.insert(FOOD_TABLE, null, food2)
        val food3 = ContentValues()
        food3.put(KEY_FOOD_NAME, "Cinnamon, dried, ground")
        food3.put(KEY_COMMON_NAME, "Cinnamomum verum, C zeylanicum or C. aromaticum")
        food3.put(
            KEY_DESCRIPTION,
            "Dried and ground bark or quills of Cinnamomum verum, C. zeylanicum or C. aromaticum, used as a spice."
        )
        food3.put(KEY_NITROGEN_FACTOR, 6.25)
        food3.put(KEY_FAT_FACTOR, 0.956)
        food3.put(KEY_CALORIES_FACTOR, 40)
        food3.put(KEY_SPECIFIC_GRAVITY, 0)
        food3.put(KEY_ANALYSED_PORTION, 100)
        food3.put(KEY_UNANALYSED_PORTION, 0)
        food3.put(
            KEY_SAMPLING_DETAILS,
            "The majority of nutrient data was borrowed USDA SR28, 02009 - Spices, chili powder. Iodine was borrowed from NZ FOODfiles 2014, P28 - Spice, chilli powder. Alcohol, vitamins (B12, folic acid, retinol, C, D), caffeine and cholesterol were imputed zero."
        )
        db?.insert(FOOD_TABLE, null, food3)
        val food4 = ContentValues()
        food4.put(KEY_FOOD_NAME, "Cloves, dried, ground")
        food4.put(KEY_COMMON_NAME, "Syzygium aromaticum")
        food4.put(
            KEY_DESCRIPTION,
            "Dried and ground, unopened flower buds of Syzygium aromaticum, used as a spice."
        )
        food4.put(KEY_NITROGEN_FACTOR, 6.25)
        food4.put(KEY_FAT_FACTOR, 0.956)
        food4.put(KEY_CALORIES_FACTOR, 40)
        food4.put(KEY_SPECIFIC_GRAVITY, 0)
        food4.put(KEY_ANALYSED_PORTION, 100)
        food4.put(KEY_UNANALYSED_PORTION, 0)
        food4.put(
            KEY_SAMPLING_DETAILS,
            "The majority of nutrient data were borrowed from USDA SR28, 02011 - Spices, cloves, ground. Iodine was borrowed from NZ FOODfiles 2014, P30 - Spice, cloves, ground. Alcohol, vitamins (B12, folic acid, retinol, D), caffeine and cholesterol were imputed zero."
        )
        db?.insert(FOOD_TABLE, null, food4)
        val food5 = ContentValues()
        food5.put(KEY_FOOD_NAME, "Coriander seed, dried, ground")
        food5.put(KEY_COMMON_NAME, "Coriandrum sativum")
        food5.put(
            KEY_DESCRIPTION,
            "Dried and ground fruit or seed of Coriandrum sativum, used as a spice."
        )
        food5.put(KEY_NITROGEN_FACTOR, 6.25)
        food5.put(KEY_FAT_FACTOR, 0.956)
        food5.put(KEY_CALORIES_FACTOR, 40)
        food5.put(KEY_SPECIFIC_GRAVITY, 0)
        food5.put(KEY_ANALYSED_PORTION, 100)
        food5.put(KEY_UNANALYSED_PORTION, 0)
        food5.put(
            KEY_SAMPLING_DETAILS,
            "The majority of nutrient data were borrowed from USDA SR28, 02013 - Spices, coriander seed. Proximates (starch, total sugars) and vitamins (B6, alpha-tocopherol, beta-carotene) were borrowed from Indian FCDB 2011-13, G024 - Coriander seeds. Iodine was borrowed from NZ FOODfiles 2014, P32 - Seed, coriander. Tryptophan was imputed from dried fenugreek seed. Alcohol, vitamins (B12, folic acid, retinol, D), caffeine and cholesterol were imputed zero. Note low sum of proximates - data should be used with caution."
        )
        db?.insert(FOOD_TABLE, null, food5)
        val food6 = ContentValues()
        food6.put(KEY_FOOD_NAME, "Cumin (cummin) seed, dried, ground")
        food6.put(KEY_COMMON_NAME, "Cuminum cymimum")
        food6.put(
            KEY_DESCRIPTION,
            "Dried and ground fruit or seeds of Cuminum cymimum, used as a spice.\n"
        )
        food6.put(KEY_NITROGEN_FACTOR, 6.25)
        food6.put(KEY_FAT_FACTOR, 0.956)
        food6.put(KEY_CALORIES_FACTOR, 40)
        food6.put(KEY_SPECIFIC_GRAVITY, 0)
        food6.put(KEY_ANALYSED_PORTION, 100)
        food6.put(KEY_UNANALYSED_PORTION, 0)
        food6.put(
            KEY_SAMPLING_DETAILS,
            "The majority of nutrient data were borrowed from USDA SR28, 02014 - Spices, cumin seed. Iodine was borrowed from NZ FOODfiles 2014, P33 - Seed, cumin. Tryptophan was imputed from dried fenugreek seed. Alcohol, vitamins (B12, folic acid, retinol, D), caffeine and cholesterol were imputed zero."
        )
        db?.insert(FOOD_TABLE, null, food6)
        val food7 = ContentValues()
        food7.put(KEY_FOOD_NAME, "Curry powder")
        food7.put(KEY_COMMON_NAME, "")
        food7.put(
            KEY_DESCRIPTION,
            "Dried and ground mixture of spices for use in the preparation of Indian-style meals. Typical ingredients include the spices cumin, coriander seed and turmeric."
        )
        food7.put(KEY_NITROGEN_FACTOR, 6.25)
        food7.put(KEY_FAT_FACTOR, 0.956)
        food7.put(KEY_CALORIES_FACTOR, 40)
        food7.put(KEY_SPECIFIC_GRAVITY, 0)
        food7.put(KEY_ANALYSED_PORTION, 100)
        food7.put(KEY_UNANALYSED_PORTION, 0)
        food7.put(
            KEY_SAMPLING_DETAILS,
            "The majority of nutrient data were borrowed from USDA SR28, 02015 Spices, curry powder. Alcohol, vitamins (B12, folic acid, retinol, D), caffeine and cholesterol were imputed zero."
        )
        db?.insert(FOOD_TABLE, null, food7)

        val food8 = ContentValues()
        food8.put(KEY_FOOD_NAME, "Fenugreek seed, dried")
        food8.put(KEY_COMMON_NAME, "Trigonella foenum-graecum")
        food8.put(
            KEY_DESCRIPTION,
            "Dried fenugreek seeds used as a spice."
        )
        food8.put(KEY_NITROGEN_FACTOR, 6.25)
        food8.put(KEY_FAT_FACTOR, 0.956)
        food8.put(KEY_CALORIES_FACTOR, 40)
        food8.put(KEY_SPECIFIC_GRAVITY, 0)
        food8.put(KEY_ANALYSED_PORTION, 100)
        food8.put(KEY_UNANALYSED_PORTION, 0)
        food8.put(
            KEY_SAMPLING_DETAILS,
            "The majority of nutrient data were borrowed from USDA SR28, 02019 - Spices, fenugreek seed. Alpha-tocopherol, beta-carotene, proximates (starch, total sugars, fibre) and fatty acids were borrowed from Indian FCT G026 - Fenugreek seeds. Iodine was borrowed from NZ FOODfiles 2014, P37 - Seed, fenugreek. Alcohol, vitamins (B12, folic acid, retinol, D), caffeine and cholesterol were imputed zero. Note low sum of proximates - data should be used with caution."
        )
        db?.insert(FOOD_TABLE, null, food8)
        val food9 = ContentValues()
        food9.put(KEY_FOOD_NAME, "Ginger, dried, ground")
        food9.put(KEY_COMMON_NAME, "Zingiber officinale")
        food9.put(
            KEY_DESCRIPTION,
            "Dried and ground rhizome of Zingiber officinale, used as a spice."
        )
        food9.put(KEY_NITROGEN_FACTOR, 6.25)
        food9.put(KEY_FAT_FACTOR, 0.956)
        food9.put(KEY_CALORIES_FACTOR, 40)
        food9.put(KEY_SPECIFIC_GRAVITY, 0)
        food9.put(KEY_ANALYSED_PORTION, 100)
        food9.put(KEY_UNANALYSED_PORTION, 0)
        food9.put(
            KEY_SAMPLING_DETAILS,
            "The majority of nutrient data were borrowed from USDA SR28, 02021 Spices, ginger ground. Iodine was borrowed from NZ FOODfiles 2014, P4 - Spice, ginger, dried, ground. Alcohol, vitamins (B12, folic acid, retinol, D), caffeine and cholesterol were imputed zero."
        )
        db?.insert(FOOD_TABLE, null, food9)
        val food10 = ContentValues()
        food10.put(KEY_FOOD_NAME, "Nutmeg, dried, ground")
        food10.put(KEY_COMMON_NAME, "")
        food10.put(
            KEY_DESCRIPTION,
            "Dry powder composed of ground yellow mustard seeds, for use in preparation of mustard paste or incorporation into other foods."
        )
        food10.put(KEY_NITROGEN_FACTOR, 6.25)
        food10.put(KEY_FAT_FACTOR, 0.956)
        food10.put(KEY_CALORIES_FACTOR, 40)
        food10.put(KEY_SPECIFIC_GRAVITY, 0)
        food10.put(KEY_ANALYSED_PORTION, 100)
        food10.put(KEY_UNANALYSED_PORTION, 0)
        food10.put(
            KEY_SAMPLING_DETAILS,
            "The majority of nutrient data were derived from a composite sample of 6 purchases (4x Keens, 2x McCormick) of mustard powder purchased in Adelaide in 1990. Folate, C, B6, alpha-tocopherol and tryptophan were borrowed from the USDA SR28, 02024 - Spices, mustard seed, ground. Fibre is estimated based on products available in 2012. Alcohol, vitamins (retinol, folic acid, B12, D), iodine, caffeine and cholesterol were imputed zero."
        )
        db?.insert(FOOD_TABLE, null, food10)
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
    fun addFeedback(
        userid: Int,
        title: String,
        description: String,
        isadmin: Int,
        isSend: Int
    ): Long {
        val dbase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_FEEDBACK_USERID, userid)
        contentValues.put(KEY_FEEDBACK_TITLE, title)
        contentValues.put(KEY_FEEDBACK_DES, description)
        contentValues.put(KEY_FEEDBACK_ISREPLYBYADMIN, isadmin)
        contentValues.put(KEY_FEEDBACK_ISSEND, isSend)
        contentValues.put(KEY_FEEDBACK_REPLY, "")
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
        contentValues.put(KEY_REG_OTP, "null")
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

    //method to update data
    fun updateForgot(
        otp: Int,
        email: String,
    ): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_REG_PASSWORD, "")
        contentValues.put(KEY_REG_OTP, otp)
        val success =
            db.update(
                REGISTRATION_TABLE,
                contentValues,
                KEY_REG_EMAIL + " = '" + email + "'",
                null
            )
        db.close()
        return success
    }

    fun updatePassword(
        userid: Int,
        email: String,
        password: String,
    ): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_REG_EMAIL, email)
        contentValues.put(KEY_REG_PASSWORD, password)
        contentValues.put(KEY_REG_OTP, "")
        val success =
            db.update(
                REGISTRATION_TABLE,
                contentValues,
                KEY_REG_ID + " = '" + userid + "'",
                null
            )
        db.close()
        return success
    }

    fun updateUserData(
        userid: Int,
        firstName: String,
        weight: Double,
        height: Double,
        age: String,
        gender: String,
        activity: String
    ): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_FIRST_NAME, firstName)
        contentValues.put(KEY_WEIGHT, weight)
        contentValues.put(KEY_HEIGHT, height)
        contentValues.put(KEY_AGE, age)
        contentValues.put(KEY_GENDER, gender)
        contentValues.put(KEY_ACTIVITY, activity)
        val success =
            db.update(
                PROFILE_TABLE,
                contentValues,
                KEY_PROFILE_REGID + " = '" + userid + "'",
                null
            )
        db.close()
        return success
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
        var calories: Int
        var recipeName: String
        var recipeDate: String
        if (cursor.moveToFirst()) {
            do {
                recipeId = cursor.getInt(cursor.getColumnIndex(KEY_RECIPE_ID))
                userId = cursor.getInt(cursor.getColumnIndex(KEY_RECIPE_USERID))
                foodid = cursor.getInt(cursor.getColumnIndex(KEY_RECIPE_FOODID))
                calories = cursor.getInt(cursor.getColumnIndex(KEY_RECIPE_CALORIES))
                recipeName = cursor.getString(cursor.getColumnIndex(KEY_RECIPE_NAME))
                recipeDate = cursor.getString(cursor.getColumnIndex(KEY_RECIPE_DATE))
                userlist.add(
                    RecipeModel(
                        recipeId,
                        userId,
                        foodid,
                        calories,
                        recipeName,
                        recipeDate
                    )
                )
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
        var isAdmin: Int
        var isSend: Int
        var reply: String
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex(KEY_FEEDBACK_USERID))
                feedbackId = cursor.getInt(cursor.getColumnIndex(KEY_FEEDBACK_ID))
                title = cursor.getString(cursor.getColumnIndex(KEY_FEEDBACK_TITLE))
                feedback = cursor.getString(cursor.getColumnIndex(KEY_FEEDBACK_DES))
                isAdmin = cursor.getInt(cursor.getColumnIndex(KEY_FEEDBACK_ISREPLYBYADMIN))
                isSend = cursor.getInt(cursor.getColumnIndex(KEY_FEEDBACK_ISSEND))
                reply = cursor.getString(cursor.getColumnIndex(KEY_FEEDBACK_REPLY))
                userlist.add(
                    FeedbackModel(
                        userId,
                        feedbackId,
                        title,
                        feedback,
                        reply,
                        isAdmin,
                        isSend
                    )
                )
            } while (cursor.moveToNext())
        }
        return userlist
    }

    @SuppressLint("Range")
    fun viewFeedback(userid: Int): ArrayList<FeedbackModel> {
        val userlist: ArrayList<FeedbackModel> = ArrayList<FeedbackModel>()
        val selectQuery =
            "SELECT  * FROM $FEEDBACK_TABLE" + " Where " + KEY_FEEDBACK_USERID + " = \'" + userid + "\' "

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
        var isAdmin: Int
        var isSend: Int
        var feedback_reply: String
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex(KEY_FEEDBACK_USERID))
                feedbackId = cursor.getInt(cursor.getColumnIndex(KEY_FEEDBACK_ID))
                title = cursor.getString(cursor.getColumnIndex(KEY_FEEDBACK_TITLE))
                feedback = cursor.getString(cursor.getColumnIndex(KEY_FEEDBACK_DES))
                isAdmin = cursor.getInt(cursor.getColumnIndex(KEY_FEEDBACK_ISREPLYBYADMIN))
                isSend = cursor.getInt(cursor.getColumnIndex(KEY_FEEDBACK_ISSEND))
                feedback_reply = cursor.getString(cursor.getColumnIndex(KEY_FEEDBACK_REPLY))
                userlist.add(
                    FeedbackModel(
                        userId,
                        feedbackId,
                        title,
                        feedback,
                        feedback_reply,
                        isAdmin,
                        isSend
                    )
                )
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
    fun otpmatch(email: String, otp: Int): Int {
        val selectQuery =
            "SELECT  * FROM $REGISTRATION_TABLE" + " Where " + KEY_REG_EMAIL + " = \'" + email + "\' AND " + KEY_REG_OTP + " = " + otp
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
    fun isFeedbackSend(userid: Int): Int {
        val selectQuery =
            "SELECT  * FROM $FEEDBACK_TABLE" + " Where " + KEY_FEEDBACK_USERID + " = \'" + userid + "\'"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return 0;
        }
        if (cursor.moveToFirst()) {
            do {
                return 1;
            } while (cursor.moveToNext())
        }
        return 0
    }

    //method to read data
    @SuppressLint("Range")
    fun isFeedbackReply(userid: Int): Int {
        val selectQuery =
            "SELECT  * FROM $FEEDBACK_TABLE" + " Where " + KEY_FEEDBACK_USERID + " = \'" + userid + "\'"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return 0;
        }
        var isreply: Int
        if (cursor.moveToFirst()) {
            do {
                isreply = cursor.getInt(cursor.getColumnIndex(KEY_FEEDBACK_ISREPLYBYADMIN))
                return isreply;
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


    //method to update data
    fun updateFeedbackReply(
        feedid: Int,
        userid: Int,
        title: String,
        description: String,
        isadmin: Int,
        isSend: Int,
        reply: String,
    ): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_FEEDBACK_USERID, userid)
        contentValues.put(KEY_FEEDBACK_TITLE, title)
        contentValues.put(KEY_FEEDBACK_DES, description)
        contentValues.put(KEY_FEEDBACK_ISREPLYBYADMIN, isadmin)
        contentValues.put(KEY_FEEDBACK_ISSEND, isSend)
        contentValues.put(KEY_FEEDBACK_REPLY, reply)
        val success =
            db.update(
                FEEDBACK_TABLE,
                contentValues,
                KEY_FEEDBACK_ID + " = '" + feedid + "'",
                null
            )
        db.close()
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

    fun deleteSteps(stepid: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //   contentValues.put(KEY_ID, userModel.userId)
        // Deleting Row
        val success = db.delete(STEP_TABLE, KEY_STEP_ID + "= ' " + stepid + " ' ", null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    fun deleteRecipes(recipeid: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //   contentValues.put(KEY_ID, userModel.userId)
        // Deleting Row
        val success = db.delete(RECIPE_TABLE, KEY_RECIPE_ID + "= ' " + recipeid + " ' ", null)
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