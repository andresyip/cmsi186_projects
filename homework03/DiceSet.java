/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
  
  public DiceSet( int count, int sides ) {
      
    if ( count < 1 ) {
      throw new IllegalArgumentException("Number of die must be at least 1.");
    } 
    if ( sides < 4 ) {
      throw new IllegalArgumentException("Number of sides must be at least 4.");
    }

    this.count = count;

    ds = new Die[ count ];

    for ( int i = 0; i < ds.length; i++ ) {
      ds[i] = new Die(sides);
    }
  }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
    int output = 0;
    for ( int i = 0; i < ds.length; i++ ) {
      output += ds[i].getValue();
    }
    return output;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
    for ( int i = 0; i < ds.length; i++ ) {
      ds[i].roll();
    }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
      if ( dieIndex > ds.length ) {
        throw new IllegalArgumentException("Die index beyond dice set length.");
      }

      ds[dieIndex - 1].roll();
      int output = ds[dieIndex - 1].getValue();

      return output;
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to get the value
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      if ( dieIndex > ds.length ) {
        throw new IllegalArgumentException("Die index beyond dice set length.");
      }

      int output = ds[dieIndex - 1].getValue();
      return output;
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      String output = "";
      for ( Die d : ds ) {
        output += d.toString();
      }
      return output;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet newSet ) {
      String output = "";
      for ( Die d : newSet.ds ) {
        output += newSet.toString();
      }
      return output;
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet newSet ) {
      /*
      if ( this.length != ds.length ) {
        return false;
      } else {
        for ( int i = 0; i < ds.length; i++ ) {
          if ( this[i].toString() != newSet[i].toString() ) {
            return false;
          }
        }
      }
      */
      return true;
   }
  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      int i = 0;
      while( i < 10 ) {
        DiceSet ds = new DiceSet ( (int) Math.ceil(Math.random() * 7) + 3, (int) Math.ceil(Math.random() * 7) + 3  );
        int index =  (int) Math.ceil(Math.random() * ds.count);
        System.out.println( "string: " + ds.toString() + ". sum: " + ds.sum() + ". getIndividual: " + ds.getIndividual( index ));
        ds.roll();
        System.out.println( "string: " + ds.toString() + ". sum: " + ds.sum() +
                            ". getIndividual: " + ds.getIndividual( index ) +
                            ". rollIndividual: " + ds.rollIndividual( index ) + 
                            ". getIndividual: " + ds.getIndividual( index ) );
        System.out.println("-------------------");
        i++;
      }
   }

}