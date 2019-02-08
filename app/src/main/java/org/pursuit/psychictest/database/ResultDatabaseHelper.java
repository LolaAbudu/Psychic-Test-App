package org.pursuit.psychictest.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.pursuit.psychictest.model.Result;

public class ResultDatabaseHelper extends SQLiteOpenHelper {
    private static ResultDatabaseHelper resultDatabaseHelper;
    private static final int SCHEMA_VERSION = 1;
    private static final String DATABASE_NAME = "Results_Database.db";
    private static final String TABLE_NAME = "RESULTS";

    private ResultDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    public static synchronized ResultDatabaseHelper getSingleInstance(Context context) {
        if (resultDatabaseHelper == null) {
            resultDatabaseHelper = new ResultDatabaseHelper(context.getApplicationContext());
        }
        return resultDatabaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "choiceIsCorrect TEXT, totalPercentageCorrect TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addResult(Result result) {
        getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME +
                "(choiceIsCorrect, totalPercentageCorrect) VALUES('" +
                result.getIsCorrect() + "', '" +
                result.getPercentageCorrect() + "');");
    }

    public int getTrueResults() {
        Cursor correctResultsCursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE choiceIsCorrect = '" + true +
                        "';", null);

        if (correctResultsCursor != null) {
            return correctResultsCursor.getCount();
        }
        return 0;
    }

    public int getTotalChoicesMade() {
        Cursor totalChoicesMade = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + ";", null);

        if (totalChoicesMade != null) {
            return totalChoicesMade.getCount();
        }
        return 0;
    }

    public void deleteAllResults() {
        getWritableDatabase().execSQL("DELETE FROM " + TABLE_NAME + ";");
    }
}
