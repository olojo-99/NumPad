import java.util.*; // Used for Scanner, ArrayList

public class numPad
{
    // Class variable -  array of string where the index correlates to the number pressed
    static String[] charAssign = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"}; // 0 and 1 are empty strings

    // Recursive method - takes input number list, current string and current index, returns array of strings
    // Through using the ArrayList class, we can utilise resizeable arrays with built-in add() and addAll() methods
    public static ArrayList<String> posStrs(int[] numList, String current, int index)
    {
        // Create a new array for storing resultant strings
        ArrayList<String> result = new ArrayList<>(); // Generic type String used for result ArrayList

        // Base Case
        if(index == numList.length)
        {
            result.add(current); // add the current string to our results ArrayList
            return result;
        }

        String mapping = charAssign[numList[index]]; // Get the alphabets of the current digit

        // Ternary Operator to assign correct len value
        int len = (mapping == "") ? 1:mapping.length();

        // use addAll() to add all elements of array from posStrs() call to end of results
        for(int i = 0; i < len; i++)
        {
            // Checking for a 1 or 0
            if (mapping != "")
            {
                result.addAll(posStrs(numList, current + mapping.charAt(i), index + 1));
            }
            else
            {
                // Add an empty character to the current string
                result.addAll(posStrs(numList, current + "", index + 1));
            }
        }

        return result;
    }


    // Print out all possible combinations of chars given a string of nums
    public static void task1(String input)
    {
        String[] inputNums = input.split(""); // convert to string array

        // We need to convert the string array to an int array
        int[] numbers = new int[inputNums.length]; // Create an integer array of same length as input

        // Iterate through the input str arr and add each element in int form to int arr
        for(int i = 0; i < inputNums.length; i++)
        {
            if (Integer.valueOf(inputNums[i]) != 0 && Integer.valueOf(inputNums[i]) != 1)
            {
                numbers[i] = Integer.valueOf(inputNums[i]);
            }
        }

        // Print strings in the returned array to stdout in formatted way
        for(String lttrs : posStrs(numbers, "", 0)) System.out.println(lttrs);
    }


    // Creative element - Tell the user which numbers to press and how many times to spell a given word (sequence of chars)
    public static void task2(String input)
    {
        String lettersToNum = "";
        input = input.toLowerCase();

        int length = input.length();
        for (int i = 0; i < length; i++) // Length of input string
        {
            int sublength = charAssign.length; // Length of charAssign array, start at 2 to exclude 0, 1
            for (int j = 2; j < sublength; j++) // Looping through each string element in charAssign
            {
                int strlen = charAssign[j].length();
                for (int k = 0; k < strlen; k++) // Looping through the contents of each string
                {
                    if (charAssign[j].charAt(k) == input.charAt(i))
                    {
                        // We add 2 to k because indexes start at 0 and first press of button on keypad will return number rather than letter
                        lettersToNum = lettersToNum + "(" + j + " * " + (k + 2) + " times) ";
                    }
                }
            }
        }
        System.out.println("To write the word " + input + " you must press " + lettersToNum);
    }


    //checks if input sequence is a number (task1) or word (task2)
    public static boolean isNum(String seq)
    {
        // Null is not a valid number
        if (seq == null)
        {
            return false;
        }

        // Try to convert string to integer
        try
        {
            int d = Integer.parseInt(seq);
        }
        // If the conversion raises an error - not a number
        catch (NumberFormatException err)
        {

            return false;
        }

        // True otherwise
        return true;
    }


    // Driver code - Example user input "234"
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String input = in.next();

        if (isNum(input))
        {
            //send to task 1 method
            task1(input);
        }
        else
        {
            //send to task 2 method
            task2(input);
        }
    }
}
