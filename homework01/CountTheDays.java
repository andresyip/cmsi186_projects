/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  CountTheDays.java
 *  Purpose       :  Executes daysBetween method from CalendarStuff.java
 *  Author        :  Andre Yip
 *  Date          :  2018-01-25
 *  Description   :  see purpose
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-16  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class CountTheDays {

  public static void main( String args[] ) {

    long month1 = 0;
    long day1   = 0;
    long year1  = 0;
    long month2 = 0;
    long day2   = 0;
    long year2  = 0;
    long dayCount = 0;

    System.out.println( "\n   Welcome to the Count the Days.\n" );
    if( args.length < 6 ) {
      System.out.println( "\n  Please enter 6 numbers represetning 2 dates:\n" +
                          "   the date will be structured month, day, year, and another month, day, year." );
      System.exit( -1 );      // Negative means program failure
    } else {
      try {
        month1 = Long.parseLong( args[0] );
        day1   = Long.parseLong( args[1] );
        year1  = Long.parseLong( args[2] );
        month2 = Long.parseLong( args[3] );
        day2   = Long.parseLong( args[4] );
        year2  = Long.parseLong( args[5] );
      }
      catch( Exception e ) { System.out.println( "\n\nEXCEPTION: " + e.toString() ); };
        dayCount = CalendarStuff.daysBetween( month1, day1, year1, month2, day2, year2 );
    }

    System.out.println("The days between are " + dayCount + ".");
  }
}