// MainActivity.java
package com.example.studentmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    private static final String TABLE_STUDENTS = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";

    // SQL statement to create the table
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_STUDENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_PHONE + " TEXT NOT NULL)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);

        // Recreate the table
        onCreate(db);
    }

    public void addStudent(Student student) {
        // Get a writable database
        SQLiteDatabase db = getWritableDatabase();

        // Create a ContentValues object to store the student data
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_PHONE, student.getPhone());

        // Insert the student data into the database
        db.insert(TABLE_STUDENTS, null, values);

        // Close the database connection
        db.close();
    }

    public void updateStudent(Student student) {
        // Get a writable database
        SQLiteDatabase db = getWritableDatabase();

        // Create a ContentValues object to store the student data
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_PHONE, student.getPhone());

        // Update the student data in the database
        db.update(TABLE_STUDENTS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(student.getId())});

        // Close the database connection
        db.close();
    }

    public void deleteStudent(Student student) {
        // Get a writable database
        SQLiteDatabase db = getWritableDatabase();

        // Delete the student data from the database
        db.delete(TABLE_STUDENTS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(student.getId())});

        // Close the database connection
        db.close();
    }

    public Student getStudent(int id) {
        // Get a readable database
        SQLiteDatabase db = getReadableDatabase();

        // Define the projection (i.e. the columns to return)
        String[] projection = {COLUMN_ID, COLUMN_NAME, COLUMN_PHONE};

        // Define the selection (i.e. the WHERE clause)
        String selection = COLUMN_ID + " = ?";

        // Define the selection arguments (i.e. the values to substitute for ?)
        String[] selectionArgs = {String.valueOf(id)};

        // Query the database
        Cursor cursor = db.query(TABLE_STUDENTS, projection, selection, selectionArgs, null, null, null);

        // Create a Student object from the first row of the cursor
        Student student = null;
        if (cursor.moveToFirst()) {
            int studentId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE));
            student = new Student(studentId, name, phone);
        }

        // Close the cursor and database connection
        cursor.close();
        db.close();

        // Return the Student object (or null if not found)
        return student;
    }

}

