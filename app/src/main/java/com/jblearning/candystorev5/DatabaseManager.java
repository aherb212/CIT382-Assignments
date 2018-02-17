package com.jblearning.candystorev5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
  private static final String DATABASE_NAME = "ToDoDB";
  private static final int DATABASE_VERSION = 1;
  private static final String TABLE_TASKS = "Tasks";
  private static final String ID = "Task_ID";
  private static final String NAME = "Task_Name";
  private static final String DEADLINE = "Deadline";
	
  public DatabaseManager( Context context ) {
    super( context, DATABASE_NAME, null, DATABASE_VERSION );
  }
 
  public void onCreate( SQLiteDatabase db ) {
    // build sql create statement
    String sqlCreate = "create table " + TABLE_TASKS + "( " + ID;
    sqlCreate += " integer primary key autoincrement, " + NAME;
    sqlCreate += " text, " + DEADLINE + " real )" ;
    
    db.execSQL( sqlCreate );
  }
 
  public void onUpgrade( SQLiteDatabase db,
                         int oldVersion, int newVersion ) {
    // Drop old table if it exists
    db.execSQL( "drop table if exists " + TABLE_TASKS );
    // Re-create tables
    onCreate( db );
  }
  //Insert method
  public void insert( Task task ) {                  //Task task will need changed
    SQLiteDatabase db = this.getWritableDatabase( );
    String sqlInsert = "insert into " + TABLE_TASKS;
    sqlInsert += " values( null, '" + task.getName( );
    sqlInsert += "', '" + task.getDeadline( ) + "' )";   //getDeadline() will need to be changed
 
    db.execSQL( sqlInsert );
    db.close( );
  }
  //Delete method
  public void deleteById( int id ) {
    SQLiteDatabase db = this.getWritableDatabase( );
    String sqlDelete = "delete from " + TABLE_TASKS;
    sqlDelete += " where " + ID + " = " + id;
    
    db.execSQL( sqlDelete );
    db.close( );
  }
  //Update method
  public void updateById( int id, String name, Date Deadline ) {
    SQLiteDatabase db = this.getWritableDatabase();
 
    String sqlUpdate = "update " + TABLE_TASKS;
    sqlUpdate += " set " + NAME + " = '" + name + "', ";
    sqlUpdate += DEADLINE + " = '" + Deadline + "'";
    sqlUpdate += " where " + ID + " = " + id;

    db.execSQL( sqlUpdate );
    db.close( );
  }

  public ArrayList<Task> selectAll( ) {
    String sqlQuery = "select * from " + TABLE_TASKS;
 
    SQLiteDatabase db = this.getWritableDatabase( );
    Cursor cursor = db.rawQuery( sqlQuery, null );
    
    ArrayList<Task> candies = new ArrayList<Task>( );
    while( cursor.moveToNext( ) ) {
      Task currentCandy
          = new Task( Integer.parseInt( cursor.getString( 0 ) ),
        		        cursor.getString( 1 ), cursor.getDouble( 2 ) );
      candies.add( currentCandy );
    }
    db.close( );
    return candies;
  }
    
  public Task selectById(int id ) {
    String sqlQuery = "select * from " + TABLE_TASKS;
    sqlQuery += " where " + ID + " = " + id;
    
    SQLiteDatabase db = this.getWritableDatabase( );
    Cursor cursor = db.rawQuery( sqlQuery, null );
 
    Task candy = null;
    if( cursor.moveToFirst( ) )
      candy = new Task( Integer.parseInt( cursor.getString( 0 ) ),
		              cursor.getString( 1 ), cursor.getDouble( 2 ) );
    return candy;
  }
}
