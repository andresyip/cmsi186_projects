public class DynamicChangeMaker{

	/**
	* Method to validate Target argument from string.
	*
	* @param targetInput String target for change maker
	*
	* @return target    if argument is valid
	*/
	public int validateTarget( String targetInput ) {
		int target = 0;
		try {
			target = Integer.parseInt( targetInput );
		} catch ( NumberFormatException e ) {
			throw new IllegalArgumentException( "\n     Please enter a valid target argument. ex: < 1,2,3,4 10 >" );
		}
		if ( target < 0 ) {
			throw new IllegalArgumentException( "\n     Target argument is negative. Please enter a valid target argument." );
		}

		return target;
	}

	/**
	* Method to validate coin argument from string.
	*
	* @param args String array of coins for change maker
	*
	* @return coinInt    if coins are valid
	*/
	public int[] validateCoins ( String args ) {
		String[] coinString = args.split(",");
		int[] coinInt = new int [coinString.length];
		for ( int i = 0; i < coinString.length; i++ ) {
			try {
				coinInt[i] = Integer.parseInt( coinString[i] );
			} catch ( NumberFormatException e ) {
				throw new IllegalArgumentException( "\n Please enter valid coin arguments, positive real integers separated by a comma. ex: < 1,2,3,4 >" );
			}

			if ( coinInt[i] <= 0 ) {
				System.out.println( "\n Please enter valid coin arguments, positive real integers separated by a comma. ex: < 1,2,3,4 >");
				System.exit(0);
			}
		}

		for ( int i = 0; i < coinInt.length; i++ ) {
			int count = 0;
			for ( int j = 0; j < coinInt.length; j++) {
				if ( coinInt[i] == coinInt[j] ) {
					count += 1;
				}

				if ( count > 1 ) {
					throw new IllegalArgumentException( "\n" + "\n Please enter valid coin arguments with no duplicate coins." + "\n" );
				}
			}
		}

		return coinInt;
	}

	/**
	* Method to find optimal change for target value. Validation arguments are within as it is static and methods use string inputs, not int.
	*
	* @param coinInput int array of coins for change maker
	* @param targetInput target value for change maker
	*
	* @return output    IMPOSSIBLE if agruments aren't valid or change not possible, otherwise returns smallest change for target value.
	*/
	public static Tuple makeChangeWithDynamicProgramming( int[] coinInput , int targetInput ) {
		Tuple output = new Tuple(0);
		Tuple IMPOSSIBLE = new Tuple( new int[0] );
		

		int target = 0;
		try {
			target = targetInput;
		} catch ( Exception e ) {
			System.out.println( "\n     Please enter a valid target argument. ex: < 1,2,3,4 10 >" );
			return IMPOSSIBLE;
		}
		if ( target < 0 ) {
			System.out.println( "\n     Target argument is negative. Please enter a valid target argument." );
			return IMPOSSIBLE;
		}

		int[] coinInt = new int [coinInput.length];
		for ( int i = 0; i < coinInt.length; i++ ) {
			try {
				coinInt[i] = coinInput[i];
			} catch ( Exception e ) {
				System.out.println( "\n      Please enter valid coin arguments, positive real integers separated by a comma. ex: < 1,2,3,4 >" );
				return IMPOSSIBLE;
			}

			if ( coinInt[i] <= 0 ) {
				System.out.println( "\n      Please enter valid coin arguments, positive real integers separated by a comma. ex: < 1,2,3,4 >");
				return IMPOSSIBLE;
			}
		}

		for ( int i = 0; i < coinInt.length; i++ ) {
			int count = 0;
			for ( int j = 0; j < coinInt.length; j++) {
				if ( coinInt[i] == coinInt[j] ) {
					count += 1;
				}

				if ( count > 1 ) {
					System.out.println( "\n" + "\n Please enter valid coin arguments with no duplicate coins." + "\n" );
					return IMPOSSIBLE;
				}
			}
		}

		Tuple[][] storage = new Tuple [coinInt.length][target + 1];
		
		for ( int i = 0; i < coinInt.length; i++ ) {
			for ( int j = 0; j < (target + 1); j++ ) {
				storage[i][j] = new Tuple(coinInt.length);
				if ( j == 0 ) {
					continue;
				}

				if ( coinInt[i] > j ) {
					storage[i][j] = IMPOSSIBLE;
				}

				if ( coinInt[i] <= j ) {
					storage[i][j].setElement( i, 1);
					if ( storage[i][j - coinInt[i]].isImpossible() ) {
						storage[i][j] = IMPOSSIBLE;
					} else {
						storage[i][j] = storage[i][j].add(storage[i][j - coinInt[i]]);
					}
				}

				if ( (i > 0) && storage[i - 1][j].isImpossible() ) {
					continue;
				} else if ( (i > 0) && (storage[i][j].isImpossible() || ( (storage[i][j].total() > storage[i -1][j].total()) )) ) {
					storage[i][j] = storage[i - 1][j];
				}
			}
		}

		output = storage[coinInt.length - 1][target];
		return output;
	}

	public static void main ( String args[] ) {
		Tuple output = new Tuple(0);

		if ( args.length != 2 ) {
			throw new IllegalArgumentException(" BAD DATA. Need two arguments. ex: < 1,2,3,4 10>");
		}
		
		DynamicChangeMaker yay = new DynamicChangeMaker();
		int[] coinInput = yay.validateCoins( args[0] );
		int targetInput = yay.validateTarget( args[1] );

		try {
			output = yay.makeChangeWithDynamicProgramming( coinInput, targetInput );
		} catch ( Exception e ) {
			throw new IllegalArgumentException ( "\n Please enter valid arguments. ex: < 1,2,3,4 10>" );
		}

		if ( output.isImpossible() ) {
			System.out.println( "\n No combination of " + args[0] + " can make " + args[1] + "." );
		} else {
			System.out.println( "\n      The least number of change needed is " + output.toString() + " for coins " + args[0] + "." );
		}

		System.exit(1);

	}

}