import java.text.DecimalFormat;

public class Ball {
   
   /**
    *  FieldX/FieldY are the dimentions of the field in feet going in the positive/negative directions with (0,0) as the center.
    *  LocationX/Y are the coordiates of the center of the ball in feet.
    *  Vector is in feet per seconds. It will contain the speed and direction of the ball along the X/Y axes.
   **/

   public double fieldX = 195.0;
   public double fieldY = 150.0;
   public double locationX;
   public double locationY;
   public double vectorX;
   public double vectorY;
   public double time;
   public double radius = 4.45/12;
   public boolean onField = true;

   public Ball() {
   	this.onField = true;
     	this.locationX = 0;
     	this.locationY = 0;
     	this.vectorX = 0;
     	this.vectorY = 0;
     	this.radius = 4.45/12;
   }

   /**
    *  Method to check if the ball is on the field or not based on the dimensions of the field with 0,0 being the center.
   **/
   public boolean checkOnField() {
   	if ( Math.abs( locationX ) > fieldX || Math.abs( locationY ) > fieldY ) {
   		return false;
   	} else {
   		return true;
   	}
   }

   /**
    *  Method to check if the initial location arguments are valid.
   **/
   public double validateLocationXArg( String arg ) {
   	double locationArg = Double.parseDouble( arg );

   	if ( -195 <= locationArg && locationArg <= 195 ) {
   		return locationArg;
   	} else {
   		throw new IllegalArgumentException("The first argument must be a number between -195 and 195.");
   	}
   }
   public double validateLocationYArg( String arg ) {
   	double locationArg = Double.parseDouble( arg );

   	if ( -150 <= locationArg && locationArg <= 150 ) {
   		return locationArg;
   	} else {
   		throw new IllegalArgumentException("The second argument must be a number between -150 and 150.");
   	}
   }
   /**
    *  Methods to caclculate the new location based on vector and previous location after a tick.
   **/
   public double newLocationX( double tick ) {
		this.locationX += ( this.vectorX * tick ); 
		return this.locationX; 
	}
	public double newLocationY( double tick ) {
		this.locationY += ( this.vectorY * tick );
		return this.locationY;
   }

   /**
    *  Methods to calculate the new vector after slowing down because of friction every tick.
   **/
   public double frictionX() {
   	this.vectorX *= 0.99;
   	if ( this.vectorX <= 1/12 ) {
   		this.vectorX = 0;
   	}
   	return this.vectorX;
   }
   public double frictionY() {
   	this.vectorY *= 0.99;
   	if ( this.vectorY <= 1/12 ) {
   		this.vectorY = 0;
   	}
   	return this.vectorY;
   }

   public String toString() {
   	DecimalFormat df = new DecimalFormat("#.00");
   	return "(" + df.format(this.locationX) + "," + df.format(this.locationY) + ").";
   }

   public static void main( String[] args ) {
   	double time = 0;
   	Ball ball1 = new Ball();
   	ball1.locationX = Double.parseDouble( args[0] );
   	ball1.locationY = Double.parseDouble( args[1] );
   	ball1.vectorX = Double.parseDouble( args[2] );
   	ball1.vectorY = Double.parseDouble( args[3] );
   	System.out.println( ball1.toString() );

   	while ( time < 10 ) {
   		double tick = 1;
   		ball1.newLocationX( tick );
   		ball1.newLocationY( tick );
   		ball1.frictionX();
   		ball1.frictionY();
   		
   		System.out.println( ball1.toString() );
   		time += tick;
   	}
   	
   }


}