package com.jblearning.candystorev5;

public class Task {
  private int id;
  private String name;
  private double price;

  public Task(int newId, String newName, Double newPrice ) {
    setId( newId );
    setName( newName );
    setPrice( newPrice );
  }

  public void setId( int newId ) {
    id = newId;
  }

  public void setName( String newName ) {
    name = newName;
  }

  public void setPrice( double newPrice ) {
    if( newPrice >= 0.0 )
      price = newPrice;
  }

  public int getId( ) {
    return id;
  }

  public String getName( ) {
    return name;
  }

  public double getDeadline( ) {
    return price;
  }

  public String toString( ) {
    return id + "; " + name + "; " + price;
  }
}