/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Command line program for use with DiceSet and Die
 *  Author        :  Andre Yip
 *  Date          :  02-15-2018
 *  Description   :  
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  02-15-2018  Andre Yip     Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

  public static void main( String args[] ) {
    int highScore = 0;
    System.out.println("\n      Welcome to java High Roll.");
    System.out.println("How many die would you like to play with?");

    BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
    System.out.print( "\n>>" );
    int inputCount = 0;
    try {
      inputCount = Integer.parseInt( input.readLine() );
    }
    catch( IOException ioe ) {
    System.out.println( "Caught IOException" );
    }

    System.out.println("\nHow many sides should each dice have?");
    System.out.print( "\n>>" );
    int inputSides = 0;
    try {
      inputSides = Integer.parseInt( input.readLine() );
    }
    catch( IOException ioe ) {
    System.out.println( "Caught IOException" );
    }

    DiceSet highGame = new DiceSet( inputCount, inputSides);

    while( true ) {
      System.out.println( "\nYour set is: [" + highGame.toString().trim() + "]." );
      System.out.println( "\nTime to play. Please choose one of the following options:" );
      System.out.println( "   Roll  : rolls all dice." );
      System.out.println( "   Single: rolls an individual die at that number place in the set." );
      System.out.println( "   Score : calculates your score." );
      System.out.println( "   Save  : saves your score." );
      System.out.println( "   High  : displays the high score." );
      System.out.println( "\nEnter q to quit." );


      System.out.print( "\n>>" );
      String inputLine = null;

      try {
        inputLine = input.readLine();

        if ( inputLine.contentEquals( "Roll" ) || inputLine.contentEquals( "roll" ) ) {
          highGame.roll();
        }

        if ( inputLine.contentEquals( "Single" ) || inputLine.contentEquals( "single" ) ) {
          System.out.println("Which die would you like to roll?");
          System.out.print( ">>" );
          int inputPlace = Integer.parseInt( input.readLine() );
          highGame.rollIndividual(inputPlace);
        }

        if ( inputLine.contentEquals( "Score" ) || inputLine.contentEquals( "score" ) ) {
          System.out.println( "Your score is " + highGame.sum() + "." );
        }

        if ( inputLine.contentEquals( "Save" ) || inputLine.contentEquals( "save" ) ) {
          highScore = highGame.sum();
        }

        if ( inputLine.contentEquals( "High" ) || inputLine.contentEquals( "high" ) ) {
          System.out.println( "The high score is " + highScore + ".");
        }            

        if( (inputLine.length() == 1 ) && ('q' == inputLine.charAt(0)) ) {
          break;
        }
      }
      catch( IOException ioe ) {
        System.out.println( "Caught IOException" );
      }
    }
  }
}
