package com.jblearning.candystorev5;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

import java.util.ArrayList;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {
  DatabaseManager dbManager;

  public void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    dbManager = new DatabaseManager( this );
    updateView( );
  }

  // Build a View dynamically with all the candies
  public void updateView( ) {
    ArrayList<Task> taskList = dbManager.selectAll( );
    if( taskList.size( ) > 0 ) {
      // create ScrollView and GridLayout
      ScrollView scrollView = new ScrollView( this );
      GridLayout grid = new GridLayout( this );
      grid.setRowCount( taskList.size( ) );
      grid.setColumnCount( 4 );

      // create arrays of components
      TextView [] ids = new TextView[taskList.size( )];
      EditText [][] taskAndDeadline = new EditText[taskList.size( )][2];
      Button [] buttons = new Button[taskList.size( )];
      ButtonHandler bh = new ButtonHandler( );

      // retrieve width of screen
      Point size = new Point( );
      getWindowManager( ).getDefaultDisplay( ).getSize( size );
      int width = size.x;

      int i = 0;

      for ( Task taskName : taskList ) {
        // create the TextView for the candy's id
        ids[i] = new TextView( this );
        ids[i].setGravity( Gravity.CENTER );
        ids[i].setText( "" + taskName.getId( ) );

        // create the two EditTexts for the candy's name and price
        taskAndDeadline[i][0] = new EditText( this );
        taskAndDeadline[i][1] = new EditText( this );
        taskAndDeadline[i][0].setText( taskName.getName( ) );
        taskAndDeadline[i][1].setText( "" + taskName.getDeadline( ) );
        taskAndDeadline[i][1]
          .setInputType( InputType.TYPE_CLASS_NUMBER );
        taskAndDeadline[i][0].setId( 10 * taskName.getId( ) );
        taskAndDeadline[i][1].setId( 10 * taskName.getId( ) + 1 );

        // create the button
        buttons[i] = new Button( this );
        buttons[i].setText( "Update" );
        buttons[i].setId( taskName.getId( ) );

        // set up event handling
        buttons[i].setOnClickListener( bh );

        // add the elements to grid
        grid.addView( ids[i], width / 10,
                      ViewGroup.LayoutParams.WRAP_CONTENT );
        grid.addView( taskAndDeadline[i][0], ( int ) ( width * .4 ),
                      ViewGroup.LayoutParams.WRAP_CONTENT );
        grid.addView( taskAndDeadline[i][1], ( int ) ( width * .15 ),
                      ViewGroup.LayoutParams.WRAP_CONTENT );
        grid.addView( buttons[i], ( int ) ( width * .35 ),
                      ViewGroup.LayoutParams.WRAP_CONTENT );

        i++;
      }
      scrollView.addView( grid );
      setContentView( scrollView );
    }
  }

  private class ButtonHandler implements View.OnClickListener {
    public void onClick( View v ) {
      // retrieve name and price of the candy
      int taskID = v.getId( );
      EditText nameET = ( EditText ) findViewById( 10 * taskID );
      EditText priceET = ( EditText ) findViewById( 10 * taskID + 1 ); //change to edit date?
      String name = nameET.getText( ).toString( );
      String priceString = priceET.getText( ).toString( );

      // update candy in database
      try {
        Double price = Date;
        dbManager.updateById( taskID, name,  );
        Toast.makeText( UpdateActivity.this, "Task updated",
          Toast.LENGTH_SHORT ).show( );

        // update screen
        updateView( );
      } catch( NumberFormatException nfe ) {
        Toast.makeText( UpdateActivity.this,
                        "Price error", Toast.LENGTH_LONG ).show( );
      }
    }
  }
}
