import java.text.DecimalFormat;
import java.util.ArrayList;

public class SoccerSim {
    
   public static void main ( String args[] ) {
   	System.out.println( "\n  Welcome to the Soccer Simulation Program." ) ;
   	if( 4 > args.length ) {
   	   System.out.println( "\n   You must enter at least four arguments per ball for at least 1 ball." +
   	                       "\n     The first number will be the x coordinate of the ball." +
   	                       "\n     The second number will be the y coordinate of the ball." +
   	                       "\n     The third number will be the speed the ball moves in the x direction." +
   	                       "\n     The fourth number will be the speed the ball moves in the y direction." +
   	                       "\n     You may add a last number to change the time slice in seconds." +
   	                       "\n   You may repeat for as many balls. Please try again. The program will now quit." +
   	   						  "\n \n       P.S. There exists a pole at a new random location every game.");
   	   System.exit( 1 );
   	}

   	DecimalFormat df = new DecimalFormat("#.00");
   	boolean collision = false;
   	double tick = 1;
   	double time = 0;
   	int numOfBalls = ( args.length - (args.length % 4) ) / 4;
   	double movingBallsOnField = numOfBalls;

   	Ball[] balls = new Ball[numOfBalls + 1];

   	/**
   	 *  A loop to fill the balls array with however many balls were entered into the prompt.
   	**/
   	for ( int i = 0; i < numOfBalls; i++ ) {
   		balls[i] = new Ball();
   		balls[i].locationX = balls[i].validateLocationXArg( args[ i * 4 ] );
   		balls[i].locationY = balls[i].validateLocationYArg( args[ i * 4 + 1 ] );
   		balls[i].vectorX = Double.parseDouble( args[ i * 4 + 2] );
   		balls[i].vectorY = Double.parseDouble( args[ i * 4 + 3] );
   	}

   	/**
   	 *  Adds a stationary poll at the end of the balls array.
   	**/
   	Ball pole = new Ball();
   		pole.locationX = Math.ceil(Math.random() * 195);
   		pole.locationY = Math.ceil(Math.random() * 150);
   		pole.vectorX = pole.locationX;
   		pole.vectorY = pole.locationY;
   	balls[numOfBalls + 1] = pole;

   	System.out.println(" \n    You have entered " + numOfBalls + " balls.");
   	for (int i = 0; i < numOfBalls; i++) {
   		System.out.println("      Ball #" + ( i + 1 )  + " starting at " + balls[i].toString() );
   	}

   	/**
   	 *  If an extra argument is entered to change the tick length, this changes the tick to that length.
   	**/
   	if ( args.length % 4 > 0 ) {
   		tick = Double.parseDouble( args[ args.length - numOfBalls * 4 ]);
   	}

   	/**
   	 *  Loop to compare if a ball collides into another ball or the pole by calculating the distance from the ball from other objects.
   	 *  After every loop, the location and vector of each ball is updated and all balls are compared again.If a ball is no longer on 
   	 *  the field or moving, an index recording the number of moving balls on field is decreased and the loop moves on and 
   	 *  compares other balls. Once there is a collision, the program records a collision occured and then prints the location of the ball 
		 *  where it collides another ball or the pole and exits. If there is no collision and more moving balls on the field, the program 
		 *  says no collisions are possible and exits.
   	**/

   	while ( !collision ) {
   		if ( movingBallsOnField == 0 ) {
   			System.out.println( "\n      No collision possible.");
   			System.exit(0);
   		}

   		for ( int i = 0; i < numOfBalls; i++ ) {
   			if( collision ) {
   				break;
   			}
   			
   			if ( balls[i].checkOnField() ) {
   				for ( int j = i + 1; j < numOfBalls + 1; j++ ) {
   					if ( balls[j].checkOnField() ) {
   						double distanceX = ( balls[i].locationX - balls[j].locationX ) * 12;
   						double distanceY = ( balls[i].locationY - balls[j].locationY ) * 12;

   						if ( Math.pow( distanceX, 2) + Math.pow( distanceY, 2)  <= Math.pow( balls[i].radius * 12 * 2, 2) ) {
   							collision = true;
   						}

   						if ( collision == true && j == numOfBalls) {
   							System.out.println( "\n    At " + time + " seconds, Ball#" + ( i + 1 ) + " collides with the pole at " + balls[i].toString());
   							System.exit(0);
   						} else if ( collision == true ) {
   							System.out.println( "\n    At " + time + " seconds, Ball#" + ( i + 1 ) + " collides with Ball#" + ( j + 1 )+ " at " + balls[i].toString());
   							System.exit(0);
   						}
   					}
   				}
   			} else if ( !balls[i].checkOnField() || ( balls[i].vectorX == 0 && balls[i].vectorY == 0) ) {
   				movingBallsOnField--;
   			}
   		}

   		time += tick;

   		for ( int i = 0; i < numOfBalls; i++ ) {
   			balls[i].newLocationX( tick );
   			balls[i].newLocationY( tick );
   			balls[i].frictionX();
   			balls[i].frictionY();
   		}
   	}

   	System.exit(0);

   }
}