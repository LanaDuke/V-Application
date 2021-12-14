package visa;
import java.util.*;


public class betterCompression {
	
	//"a2b3c8d10e5" <--- example of good string compression
	//"a2b4a123c2d9183" <--- example of bad string compression
	//"a2b5b1c3d2e4a2" <--- another example of bad string compression
	
	//For the purposes of this exercise, edge cases will not include strings that deviate
	//		from the string format of STRING(char/int/char/int/char/int), but rather test
	//		the scalability of the program. (Large ints, etc.)

	public static String goodCompression (String s)
	{
		String currentNumber = "";
		List<String> stringAsArray = new ArrayList();
		
		//The commented out code below easily converts the entire string into an array
		//		of characters, but this is not what I'm working towards, I want letters to be each even index
		//		and numbers to be each odd index
		
		//char[] stringAsCharArray = s.toCharArray();
		
		
		//this for loop sections adds the entire contents of the string to a string array
		// 		that arranges it so that every character is an even index and every integer is
		// 		and odd index
		for (int i = 0; i < s.length(); i++)
		{
			int lengthOfString = s.length() - 1;
			if ((!Character.isDigit(s.charAt(i))) && (i != 0))
			{
				stringAsArray.add(currentNumber);
				stringAsArray.add(Character.toString(s.charAt(i)));
//				System.out.println(s.charAt(i));
				currentNumber = "";
			}
			else if (!Character.isDigit(s.charAt(i)))
			{
				stringAsArray.add(Character.toString(s.charAt(i)));
//				System.out.println(s.charAt(i));
				currentNumber = "";
			}
			else
			{
				currentNumber += Character.toString(s.charAt(i));
//				System.out.println("CURRENT NUM:" + currentNumber);
			}
		}
		stringAsArray.add(currentNumber);
//		
//		System.out.println("CURRENT ARRAY BEFORE EVERYTHING:");
//		System.out.println(stringAsArray);
		
		//now to go through all the characters in the array and add them to a set (which
		// has unique entries!!)
		
		Set<Character> letterSet = new HashSet<Character>();
		int currentLengthOfOddArray = stringAsArray.size()-2;
//		System.out.println(currentLengthOfOddArray);
		for (int i = 0; i <= currentLengthOfOddArray; i += 2)
		{
			if (letterSet.contains((stringAsArray.get(i).charAt(0))))
			{
				for (int j = 0; j <= currentLengthOfOddArray; j+=1)
				{
					if ((stringAsArray.get(i).charAt(0)) == (stringAsArray.get(j).charAt(0)))
					{
						int addedNumber = Integer.parseInt(stringAsArray.get(i+1));
						int baseNumber = Integer.parseInt(stringAsArray.get(j+1));
						int newNumber = addedNumber + baseNumber;
						String newNumberAsString = Integer.toString(newNumber);
						stringAsArray.set(j+1, newNumberAsString);
						stringAsArray.remove(i);
						stringAsArray.remove(i);
						currentLengthOfOddArray = currentLengthOfOddArray -2;
						i = i-2;
						break;
					}
				}
				
			}
			else
			{
				letterSet.add((stringAsArray.get(i).charAt(0)));
//				System.out.println(letterSet);
			}
		}
		
		
//		System.out.println("ARRAY AFTER ALL THAT STUFF");
//		System.out.println(stringAsArray);
		String listString = String.join("", stringAsArray);
		System.out.println(listString);
		return listString;
	}
	
	public static void main (String args[])
	{
		goodCompression("a2000b4c26b2a2a3a3");
		goodCompression("a2b3c4d10e7f8g7a4a10a8a9a4b2");
		goodCompression("a2a1a5a10");
		
	}
	
}
