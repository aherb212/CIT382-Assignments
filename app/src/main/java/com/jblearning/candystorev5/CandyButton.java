package com.jblearning.candystorev5;

import android.content.Context;
import android.widget.Button;

public class CandyButton extends Button {
  private Task candy;

  public CandyButton( Context context, Task newCandy ) {
    super( context );
    candy = newCandy;
  }

  public double getPrice( ) {
    return candy.getDeadline( );
  }
}
