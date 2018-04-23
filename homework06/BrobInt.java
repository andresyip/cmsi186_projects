/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  Andre Yip
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;
import java.lang.StringBuffer;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );
   public char[] NumChar = {'0','1','2','3','4','5','6','7','8','9'};
  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this BrobInt
   private int    sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private int[]  intVersion    = null;      // int array for storing the string values
   private int[]  intReversed   = null;
   private int    valueLength   = 0;

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt ( String value ) {
    validateDigits( value );
    setReverse();
    setIntVersion();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits( String value ) {
    for ( int i = 0; i < value.length(); i++ ) {
      boolean check = false;
      for ( int j = 0; j < NumChar.length; j++) {
        if ( value.charAt(i) == NumChar[j] ) {
          check = true;
          break;
        } else if ( i == 0 && (value.charAt(i) == '+' || value.charAt(i) == '-') ) {
          check = true;
          break;
        } 
      }
      if ( !check ) {
        throw new IllegalArgumentException("\n   Please enter a valid giant Integer");
      }
    }

    if ( value.charAt(0) == '-' ) {
      sign = 1;
    } else {
      sign = 0;
    }

    internalValue = value.substring( 0, value.length() );
    valueLength = internalValue.length();
    return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String setReverse() {
    StringBuffer rev = new StringBuffer(internalValue).reverse();
    return reversed = rev.toString();
   }

   public int[] setIntVersion() {
    intVersion = new int[ (valueLength/8) + 1 ];
    for (int i = 0; (i * 8) < valueLength; i++ ) {
      if ( ((i + 1) * 8) > valueLength ) {
        intVersion[i] = Integer.parseInt( internalValue.substring( 0, valueLength - (i * 8) ) );
      } else {
        intVersion[i] = Integer.parseInt( internalValue.substring( valueLength - ((i + 1) * 8) , valueLength - (i * 8) ) );
      }
    }
    return intVersion;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int[] reverser( BrobInt gint ) {
    intReversed = new int[ (valueLength/8) + 1 ];
    for (int i = 0; (i * 8) < valueLength; i++ ) {
      if ( ((i + 1) * 8) > valueLength ) {
        intReversed[i] = Integer.parseInt( internalValue.substring( (i *8), ( (i + 1) * 8) ) );
      } else {
        intReversed[i] = Integer.parseInt( internalValue.substring( (i * 8) ) );
      }
    }
    return intReversed;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt gint ) {
    BrobInt output = new BrobInt("0");
    int carry = 0;

    if ( this.sign == 0 && gint.sign == 1 ) {
      sub(gint);
    } else if ( this.sign > gint.sign ) {
      gint.sub(this);
    } 

    output.sign = this.sign;

    if ( this.intVersion.length == gint.intVersion.length ) {
      output.intVersion = new int[this.intVersion.length];
      for ( int i = 0; i < intVersion.length; i++) {
        output.intVersion[i] = this.intVersion[i] + gint.intVersion[i] + carry;
        carry = 0;
        if ( output.intVersion[i] >= 100000000 ) {
          carry = output.intVersion[i] / 100000000;
          output.intVersion[i] = output.intVersion[i] % 100000000;
        }
      }

      for ( int i = 0; i < output.intVersion.length; i++) {
        if ( i == 0 ) {
          output.internalValue = Integer.toString( output.intVersion[i] );
        } else if ( output.intVersion[i] > 0 ) {
          output.internalValue = output.intVersion[i] + output.internalValue;
        } 
      }

      return output;
    }

    if (this.internalValue.length() > gint.internalValue.length() ) {
      output.intVersion = new int[this.intVersion.length];

      for ( int i = 0; i < gint.intVersion.length; i++) {
        output.intVersion[i] = this.intVersion[i] + gint.intVersion[i] + carry;
        carry = 0;
        if ( output.intVersion[i] >= 100000000 ) {
          carry = output.intVersion[i] / 100000000;
          output.intVersion[i] = output.intVersion[i] % 100000000;
        } 

      }

      for ( int i = gint.intVersion.length; i < this.intVersion.length; i++ ) {
        output.intVersion[i] = this.intVersion[i] + carry;
        carry = 0;
        if ( output.intVersion[i] >= 100000000 ) {
          carry = output.intVersion[i] / 100000000;
          output.intVersion[i] = output.intVersion[i] % 100000000;
        }
      }


      for ( int i = 0; i < output.intVersion.length; i++) {
        if ( i == 0 ) {
          output.internalValue = Integer.toString( output.intVersion[i] );
        } else if ( output.intVersion[i] > 0 ) {
          output.internalValue = output.intVersion[i] + output.internalValue;
        } 

      }

    return output;
    }

    if (this.internalValue.length() < gint.internalValue.length() ) {
      output.intVersion = new int[gint.intVersion.length];

      for ( int i = 0; i < this.intVersion.length; i++) {
        output.intVersion[i] = this.intVersion[i] + gint.intVersion[i] + carry;
        carry = 0;
        if ( output.intVersion[i] >= 100000000 ) {
          carry = output.intVersion[i] / 100000000;
          output.intVersion[i] = output.intVersion[i] % 100000000;
        }
      }

      for ( int i = this.intVersion.length; i < gint.intVersion.length; i++ ) {
        output.intVersion[i] = gint.intVersion[i] + carry;
        carry = 0;
        if ( output.intVersion[i] >= 100000000 ) {
          carry = output.intVersion[i] / 100000000;
          output.intVersion[i] = output.intVersion[i] % 100000000;
        }
      }

      for ( int i = 0; i < output.intVersion.length; i++) {
        if ( i == 0 ) {
          output.internalValue = Integer.toString( output.intVersion[i] );
        } else if ( output.intVersion[i] > 0 ) {
          output.internalValue = output.intVersion[i] + output.internalValue;
        } 
      }

      return output;
    }

    throw new RuntimeException( "\n         Oops, something went wrong in adding." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt sub( BrobInt gint ) {
    BrobInt output = new BrobInt("0");
    int carry = 0;
    if ( gint.sign > this.sign ) {
      gint.sign = 0;
      add(gint);
    } else if ( gint.sign < this.sign ) {
      this.sign = 0;
      add(gint);
    }

    output.sign = this.sign;
    if ( this.intVersion.length == gint.intVersion.length ) {
      output.intVersion = new int[this.intVersion.length];
      if ( this.sign == 0 ) {
        for ( int i = 0; i < this.intVersion.length; i++ ) {
          output.intVersion[i] = this.intVersion[i] - gint.intVersion[i] + carry;
          carry = 0;
          if ( output.intVersion[i] < 0 ) {
            output.intVersion[i] *= -1;
            carry = -1;
          }
        }

        if ( carry == -1 ) {
          output.sign = 1;
        } else {
          output.sign = 0;
        }

        for ( int i = 0; i < output.intVersion.length; i++) {
          if ( i == 0 ) {
            output.internalValue = Integer.toString( output.intVersion[i] );
          } else if ( output.intVersion[i] > 0 ) {
            output.internalValue = output.intVersion[i] + output.internalValue;
          } 
        }

        if ( output.sign == 1 ) {
          output.internalValue = "-" + output.internalValue;
        }

        return output;
      }

      if ( this.sign == 1 ) {
        for ( int i = 0; i < this.intVersion.length; i++ ) {
          output.intVersion[i] = gint.intVersion[i] - this.intVersion[i] + carry;
          carry = 0;
          if ( output.intVersion[i] < 0 ) {
            output.intVersion[i] *= -1;
            carry = -1;
          }
        }

        if ( carry == -1 ) {
          output.sign = 0;
        } else {
          output.sign = 1;
        }

        for ( int i = 0; i < output.intVersion.length; i++) {
          if ( i == 0 ) {
            output.internalValue = Integer.toString( output.intVersion[i] );
          } else if ( output.intVersion[i] > 0 ) {
            output.internalValue = output.intVersion[i] + output.internalValue;
          } 
        }

        if ( output.sign == 1 ) {
          output.internalValue = "-" + output.internalValue;
        }
        return output;
      }
    }
//Method applied when this.intVersion is longer than gint.intVersion
    if ( this.intVersion.length > gint.intVersion.length ) {
      output.intVersion = new int[this.intVersion.length];
      if ( this.sign == 0 ) {
        for ( int i = 0; i < gint.intVersion.length; i++ ) {
          output.intVersion[i] = this.intVersion[i] - gint.intVersion[i] + carry;
          carry = 0;
          if ( output.intVersion[i] < 0 ) {
            output.intVersion[i] *= -1;
            carry = -1;
          }
        }

        for ( int i = gint.intVersion.length; i < this.intVersion.length; i++ ) {
          output.intVersion[i] = this.intVersion[i] + carry;
          carry = 0;
          if ( output.intVersion[i] < 0 ) {
            output.intVersion[i] *= -1;
            carry = -1;
          }
        }

        if ( carry == -1 ) {
          output.sign = 1;
        } else {
          output.sign = 0;
        }

        for ( int i = 0; i < output.intVersion.length; i++) {
          if ( i == 0 ) {
            output.internalValue = Integer.toString( output.intVersion[i] );
          } else if ( output.intVersion[i] > 0 ) {
            output.internalValue = output.intVersion[i] + output.internalValue;
          } 
        }

        if ( output.sign == 1 ) {
          output.internalValue = "-" + output.internalValue;
        }

        return output;
      }

      if ( this.sign == 1 ) {
        for ( int i = 0; i < gint.intVersion.length; i++ ) {
          output.intVersion[i] = gint.intVersion[i] - this.intVersion[i] + carry;
          carry = 0;
          if ( output.intVersion[i] < 0 ) {
            output.intVersion[i] *= -1;
            carry = -1;
          }
        }

        for ( int i = gint.intVersion.length; i < this.intVersion.length; i++ ) {
          output.intVersion[i] = - this.intVersion[i] + carry;
          carry = 0;
          if ( output.intVersion[i] < 0 ) {
            output.intVersion[i] *= -1;
            carry = -1;
          }
        }

        if ( carry == -1 ) {
          output.sign = 0;
        } else {
          output.sign = 1;
        }

        for ( int i = 0; i < output.intVersion.length; i++) {
          if ( i == 0 ) {
            output.internalValue = Integer.toString( output.intVersion[i] );
          } else if ( output.intVersion[i] > 0 ) {
            output.internalValue = output.intVersion[i] + output.internalValue;
          } 
        }

        if ( output.sign == 1 ) {
          output.internalValue = "-" + output.internalValue;
        }
        return output;
      }
    }

  if ( this.intVersion.length < gint.intVersion.length ) {
    output.intVersion = new int[gint.intVersion.length];
    if ( this.sign == 0 ) {
      for ( int i = 0; i < this.intVersion.length; i++ ) {
        output.intVersion[i] = gint.intVersion[i] - this.intVersion[i] + carry;
        carry = 0;
        if ( output.intVersion[i] < 0 ) {
          output.intVersion[i] *= -1;
          carry = -1;
        }
      }

      for ( int i = this.intVersion.length; i < gint.intVersion.length; i++ ) {
        output.intVersion[i] = gint.intVersion[i] + carry;
        carry = 0;
        if ( output.intVersion[i] < 0 ) {
          output.intVersion[i] *= -1;
          carry = -1;
        }
      }

      if ( carry == -1 ) {
        output.sign = 1;
      } else {
        output.sign = 0;
      }

      for ( int i = 0; i < output.intVersion.length; i++) {
        if ( i == 0 ) {
          output.internalValue = Integer.toString( output.intVersion[i] );
        } else if ( output.intVersion[i] > 0 ) {
          output.internalValue = output.intVersion[i] + output.internalValue;
        } 
      }

      if ( output.sign == 1 ) {
        output.internalValue = "-" + output.internalValue;
      }

      return output;
    }

    if ( this.sign == 1 ) {
      for ( int i = 0; i < this.intVersion.length; i++ ) {
        output.intVersion[i] = this.intVersion[i] - gint.intVersion[i] + carry;
        carry = 0;
        if ( output.intVersion[i] < 0 ) {
          output.intVersion[i] *= -1;
          carry = -1;
        }
      }

      for ( int i = this.intVersion.length; i < gint.intVersion.length; i++ ) {
        output.intVersion[i] = - gint.intVersion[i] + carry;
        carry = 0;
        if ( output.intVersion[i] < 0 ) {
          output.intVersion[i] *= -1;
          carry = -1;
        }
      }

      if ( carry == -1 ) {
        output.sign = 0;
      } else {
        output.sign = 1;
      }

      for ( int i = 0; i < output.intVersion.length; i++) {
        if ( i == 0 ) {
          output.internalValue = Integer.toString( output.intVersion[i] );
        } else if ( output.intVersion[i] > 0 ) {
          output.internalValue = output.intVersion[i] + output.internalValue;
        } 
      }

      if ( output.sign == 1 ) {
        output.internalValue = "-" + output.internalValue;
      }
      return output;
    }
  }

  throw new RuntimeException( "\n         Oops, something went wrong in subtracting." );
  }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
    BrobInt output = new BrobInt( "0" );
    int carry = 0;

    if ( this.sign == 1 && gint.sign == 1 ) {
      output.sign = 0;
    } else if ( (this.sign > gint.sign) || (this.sign < gint.sign) ){
      output.sign = 1;
    }

    output.intVersion = new int[ this.intVersion.length + gint.intVersion.length + 1];
    for ( int i = 0; i < output.intVersion.length; i++ ) {
      output.intVersion[i] = 0;
    }
    for ( int i = 0; i < this.intVersion.length; i++ ) {
      for ( int j = 0; j < gint.intVersion.length; j++) {
        output.intVersion[ i + j ] += ( (this.intVersion[i] * gint.intVersion[i]) + carry );
        carry = 0;
        if ( output.intVersion[ i + j ] < 0 ) {
          output.intVersion[ i + j ] *= -1;
        }
        if ( output.intVersion[ i + j ] >= 100000000 ) {
          carry = output.intVersion[ i + j ] / 100000000;
          output.intVersion[i] = output.intVersion[ i + j ] % 100000000;
        }
      }
    }
    for ( int i = 0; i < output.intVersion.length; i++) {
      if ( i == 0 ) {
        output.internalValue = Integer.toString( output.intVersion[i] );
      } else if ( output.intVersion[i] > 0 ) {
        output.internalValue = output.intVersion[i] + output.internalValue;
      } 

    if ( output.sign == 1 ) {
      output.internalValue = "-" + output.internalValue;
    }
    return output;  
    }


    throw new RuntimeException( "\n         Oops, something went wrong in multiplying." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
    BrobInt output = new BrobInt( "0" );
    if ( gint.compareTo(ZERO) == 0 ) {
      throw new IllegalArgumentException("\n Please entere a valid divisor.");
    } else if ( this.compareTo(ZERO) == 0 || this.compareTo(gint) < 0) {
      return output;
    } else if ( this.compareTo(gint) == 0 ) {
      return output = new BrobInt( "1" );
    }

    int n = gint.valueLength;
    BrobInt div = new BrobInt( this.internalValue.substring( 0, n) );
    if ( div.compareTo(gint) == -1) {
      div.internalValue = this.internalValue.substring( 0, n + 1);
    }

    while ( n < this.internalValue.length() ) {
      while (div.compareTo(gint) == 1) {

        div = div.sub(gint);
        output = output.add( ONE );
      }

      if ( n == this.internalValue.length() ) {
        break;
      }

      n++;

      BrobInt plus = new BrobInt( this.internalValue.substring(n -1, n) );
      div = div.multiply( TEN );

      output = output.multiply( TEN );
      div = div.add(plus);
    }

   return output;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
    BrobInt output = new BrobInt( "0" );
    output = this.sub( gint.multiply(this.divide(gint)) );
    return output;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
     if( 1 == sign && 0 == gint.sign ) {
         return -1;
      } else if( 0 == sign && 1 == gint.sign ) {
         return 1;
      }

      if( internalValue.length() > gint.internalValue.length() ) {
         return 1;
      } else if( internalValue.length() < gint.internalValue.length() ) {
         return (-1);

      } else {
         for( int i = 0; i < internalValue.length(); i++ ) {
            Character a = Character.valueOf( internalValue.charAt(i) );
            Character b = Character.valueOf( gint.internalValue.charAt(i) );
            if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
               return 1;
            } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
               return (-1);
            }
         }
      }
      return 0;
}

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( Long.valueOf( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      String output = "";
      output += internalValue;
      return output;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( int[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      BrobInt test0 = new BrobInt( args[0]);
      BrobInt test1 = new BrobInt( args[1]);

      System.exit( 0 );
   }
}
