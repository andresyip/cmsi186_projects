/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
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

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private final double EPSILON_VALUE              = 0.1;      // small value for double-precision comparisons

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      
      System.out.println( "\n   Welcome to the Clock Solver Program" ) ;
      if( 0 == args.length ) {
         System.out.println( "\n   Please enter at least one argument." +
                             "\n   The first number will be the angle to check." +
                             "\n   The second number will be the slice of time the clock will tick." +
                             "\n   The last number will be the window of error." +
                             "\n   Please try again. The program will now quit." );
         System.exit( 1 );
      }

      Clock clock = new Clock();

      clock.validateAngleArg( args[0] );
      if ( args.length > 1 ) {
      	clock.validateTimeSliceArg( args[1] );
        System.out.println( "\nThe reference angle is [" + args[0] + "]. The time slice is [" + args[1] + "]." );
       } else {
       	System.out.println( "\nThe reference angle is [" + args[0] + "]. The time slice is [60].");
       }

   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      cse.handleInitialArguments( args );
      Clock clock    = new Clock();
      double checkAngle = Double.parseDouble( args[0] );
      double range = 0.1;
      int counter = 0;

      if (args.length > 1 ) {
      	clock.tick(args[1]);
      }	
      if (args.length == 3 ) {
      	range = Double.parseDouble( args[2] );
      }
     	System.out.println( "\nThe angle of the clock's hands is euqal to " + checkAngle + " when:");
      while( clock.time < 43200 ) {
      	if ( Math.abs(clock.getHandAngle() - checkAngle) < range ) {
      		counter++;
      		System.out.println( "  The clock is at " + clock.toString() );
      	}

      	clock.time += clock.tick;
      }

      System.out.println( "For a total of " + counter + " times.");

      System.exit( 0 );
   }
}