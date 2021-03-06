/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  Andre Yip
 *  Date          :  01-26-18
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Jave String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-01-22  B.J. Johnson  Fill in methods to make the program actually work
 *  @version 2.0.0  2018-02-01  Andre Yip     Completing all method functions
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {
   
   private static final char[] vowels        = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U',};
   private static final char[] specialVowels = {'y', 'Y'};
   private static final char[] odds          = {'a', 'c', 'e', 'g', 'i', 'k', 'm', 'o', 'q', 's', 'u', 'w', 'y', 
                                                'A', 'C', 'E', 'G', 'I', 'K', 'M', 'O', 'Q', 'S', 'U', 'W', 'Y'};
   private static final char[] evens         = {'b', 'd', 'f', 'h', 'j', 'l', 'n', 'p', 'r', 't', 'v', 'x', 'z',
                                                'B', 'D', 'F', 'H', 'J', 'L', 'N', 'P', 'R', 'T', 'V', 'X', 'Z'};

  /**
   * Method to check if a character contained in an array.
   *
   * @param c Character to be checked
   * @return boolean which is true if character is in array
   */
   private static final boolean checkChar(char c, char[] a) {
      boolean check = false;
      for ( int i = 0; i < a.length; i++ ) {
         if ( c == a[i] ) {
            check = true;
            break;
         }
      }
      return check;
   }

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included. Uses checkChar method to check if character is in the vowel array.
   * 
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */

   public static boolean containsVowel( String s ) {
      boolean output = false;
      for ( int i = 0; i < s.length(); i++) {
         if ( checkChar(s.charAt(i), vowels) ) {
            output = true;
            break;
         }
      }

      if ( !output ) {
         for ( int i = 0; i < s.length(); i++) {
            if (checkChar(s.charAt(i), specialVowels) ) {
               output = true;
               break;
            }
         }
      }

      return output;
   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome( String s ) {
      boolean output = true;
      for ( int i = 0; i < ( s.length() / 2 ); i++ ) {
         if ( s.charAt(i) != s.charAt( s.length() - 1 - i) ) {
            output = false;
            break;
         }
      }
      return output;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   * Uses previous method checkChar to check if character is part of even letters array.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly( String s ) {
      String output = "";
      for ( int i = 0; i < s.length(); i++ ) {
         if (checkChar(s.charAt(i), evens)) {
            output += s.charAt(i);
         }
      }
      return output;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   * Uses previous method checkChar to check if character is part of odd letters array.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly( String s ) {
      String output = "";
      for ( int i = 0; i < s.length(); i++ ) {
         if (checkChar(s.charAt(i), odds)) {
            output += s.charAt(i);
         }
      }
      return output;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   * Checks if each character if the first instance of that character in input string. If true,
   * character is added to output string. Uses previous evensOnly method to check only even
   * letters in input string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes( String s ) {
      String input  = evensOnly(s);
      String output = "";
      for ( int i = 0; i < input.length(); i++) {
         if ( input.indexOf(input.charAt(i)) == i ) {
            output += input.charAt(i);
         } else {
            continue;
         }
      }
      return output;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   * Checks if each character if the first instance of that character in input string. If true,
   * character is added to output string. Uses previous evensOnly method to check only even
   * letters in input string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
      String input  = oddsOnly(s);
      String output = "";
      for ( int i = 0; i < input.length(); i++) {
         if ( input.indexOf(input.charAt(i)) == i) {
            output += input.charAt(i);
         } else {
            continue;
         }
      }
      return output;
   }

  /**
   * Method to return the reverse of a string passed as an argument. Iterates in reverse
   * through input string and adding characters to new output string.
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {
      String output = "";
      for ( int i = (s.length() - 1); i >= 0; i--) {
         output += s.charAt(i);
      }
      return output;
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      String input = args[0];

      System.out.println( containsVowel(input) ? "Does contain vowel." : "Does not contain vowel." );
      System.out.println( isPalindrome(input) ? "Is palindrome." : "Is not palindrome.");
      System.out.println( evensOnly(input) );
      System.out.println( oddsOnly(input) );
      System.out.println( evensOnlyNoDupes(input) );
      System.out.println( oddsOnlyNoDupes(input) );
      System.out.println( reverse(input) );

   }
}