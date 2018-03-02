/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 360.0/43200.0;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   double time;
   double tick;
   double hourAngle;
   double minuteAngle;
   double angle;

  /**
   *  Constructor goes here
   *  All time is in seconds.
   */
   public Clock() {
   	this.time = 0;
   	this.tick = 60;
   	this.hourAngle = 0;
   	this.minuteAngle = 0;
   	this.angle = 0;
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick( String timeSlice ) {
      this.tick = this.validateTimeSliceArg( timeSlice );
      return this.tick;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
      double argAngle = Double.parseDouble( argValue );
      
      if ( argAngle > 360 ) {
      	throw new IllegalArgumentException("Angle must be at less than or equal to 360.");
      }
      
      return argAngle;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
      double argTick = Double.parseDouble( argValue );

      if ( argTick <= 0 || argTick > 1800 ) {
      	throw new IllegalArgumentException("Time slice must be less than or euqal to 1800.");
      }

      return argTick;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
   	  this.hourAngle = HOUR_HAND_DEGREES_PER_SECOND * this.time;
      return this.hourAngle;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      this.minuteAngle = (MINUTE_HAND_DEGREES_PER_SECOND * this.time) % 360;
      return this.minuteAngle;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      this.angle = Math.abs( getHourHandAngle() - getMinuteHandAngle() );
      return angle;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return this.time;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
   		int hour = (int)  this.time / 3600;
   		if ( hour == 0 ) {
   			hour = 12;
   		}
   		int minute = (int) Math.floor( ( this.time % 3600 ) / 60 );
   		int second = (int) Math.floor( ( this.time % 3600 ) % 60 );
   		int milisecond = (int) ((( this.time % 60 ) % 60 ));
   		if (minute < 10) {
      	return (hour + ":0" + minute + " and " + second + "." + milisecond + " seconds.") ;
   		} else {
   			return (hour + ":" + minute + " and " + second + "." + milisecond + " seconds.") ;
   		}
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      clock.time = Math.ceil( Math.random() * 43200 );
      String testArgs = Double.toString( Math.ceil(Math.random() * 360) );
      double testArgsDouble = Double.parseDouble(testArgs);
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    The angle between hands is " + clock.getHandAngle() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '" + testArgs + " degrees ', expecting double value " + testArgsDouble + "." );
      try { System.out.println( (testArgsDouble == clock.validateAngleArg( testArgs )) ? " - got " + testArgsDouble : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
