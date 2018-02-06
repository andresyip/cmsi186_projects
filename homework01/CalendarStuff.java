/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Andre Yip
 *  Date          :  2018-1-18
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 */
public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;
  
  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;

  /**
   * One array containing the number of days in each month for normal years
   * One array containing the number of days in each month for leap years
   */
   private static final long[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   private static final long[]    leapDays    = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      if ( ( year % 4 == 0 ) && (( year % 400 == 0 ) || !( year % 200 == 0 )) ) {
      return true;
      } else {
      return false;
      }
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
      int m = (int) month;
      if ( isLeapYear(year) ) {
        return leapDays[ m - 1];
      } else {
        return days[ m - 1 ];
      }
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if (month1 == month2 && day1 == day2 && year1 == year2) {
        return true;
      } else {
        return false;
      }  
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if (dateEquals(month1, day1, year1, month2, day2, year2)) {
        return 0;
      } else if ((year1 > year2) || (year1 == year2 && month1 > month2) || (year1 == year2 && month1 == month2 && day1 > day2)){
        return +1;
      } else {
        return -1;
      }
    }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      int m = (int) month;
      if ( isLeapYear(year) && (1 <= month && month <= 12) && (1 <= day && day <= leapDays[m - 1]) && (1000 <= year && year <= 9999) ) {
        return true;
      } else if ( (1 <= month && month <= 12) && (1 <= day && day <= days[m - 1]) && (1000 <= year && year <= 9999) ) {
        return true;
      } else {
        return false;
      }
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
      switch( month - 1 ) {
         case  JANUARY  : return "January";
         case  FEBRUARY : return "February";
         case  MARCH    : return "March";
         case  APRIL    : return "April";
         case  MAY      : return "May";
         case  JUNE     : return "June";
         case  JULY     : return "July";
         case  AUGUST   : return "August";
         case  SEPTEMBER: return "September";
         case  OCTOBER  : return "October";
         case  NOVEMBER : return "November";
         case  DECEMBER : return "December";
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      switch( day - 1 ) {
         case  0: return "Sunday";
         case  1: return "Monday";
         case  2: return "Tuesday";
         case  3: return "Wednesday";
         case  4: return "Thursday";
         case  5: return "Friday";
         case  6: return "Saturday";
         default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   * Notes: The dates are checked to be valid and then the order of dates compared. If equal, the day count is 0 and
   * returns the day count. Otherwise, if the first date is first, the code moves to case -1, and +1 if the 
   * second date is first. Cases -1 and +1 are the same except for the order of dates. 
   * First, the number of days between the years of the dates are counted by iterating through each year.
   * Next, the number of days from the months passed and the days passed from the current month for the 
   * first date are subtracted. Lastly, the number of days from the months passed and the days passed
   * from the current month for the second date are added. The final count of days is the number of days
   * between both dates, accounting for leap years.
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      long dayCount = 0;
      if ( isValidDate (month1, day1, year1) && isValidDate(month2, day2, year2) ) {
        switch( compareDate( month1, day1, year1, month2, day2, year2 ) ){
          case  0:  return dayCount;
          case -1:  for (int i = (int) year1; i < year2; i++) {
                      if ( isLeapYear(i) ) {
                        dayCount += 366;
                      } else {
                        dayCount += 365;
                      }
                    }           
                    
                    for (int i = 0; i < (month1 - 1); i++ ) {
                      if (isLeapYear(year1)) {
                        dayCount -= leapDays[i];
                      } else {
                        dayCount -= days[i];
                      } 
                    }
                    
                    dayCount -= day1; 
                    
                    for (int i = 0; i < (month2 - 1); i++ ) {
                      if (isLeapYear(year2)) {       
                        dayCount += leapDays[i];
                      } else {
                        dayCount += days[i];
                      }
                    }
                    
                    dayCount += day2;  
                    return dayCount;

          case +1:  for (int i = (int) year2; i < year1; i++) {
                      if (isLeapYear(i)) {
                        dayCount += 366;
                      } else {
                        dayCount += 365;
                      }
                    }
             
                    for (int i = 0; i < (month2 - 1); i++ ) {
                      if (isLeapYear(year2)) {
                        dayCount -= leapDays[i];
                      } else {
                        dayCount -= days[i];
                      }
                    }
                    
                    dayCount -= day2;

                    for (int i = 0; i < (month1 - 1); i++ ) {
                      if (isLeapYear(year1)) {
                        dayCount += leapDays[i];
                      } else {
                        dayCount += days[i];
                      }
                    }              
                    
                    dayCount += day1; 
                    return dayCount; 
          default: break;
        }
      } else {
        throw new IllegalArgumentException("Please enter valid dates.");
      }
    return dayCount; 
   }  

}