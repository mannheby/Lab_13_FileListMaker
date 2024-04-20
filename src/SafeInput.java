import java.util.Scanner;

public class SafeInput
{
    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }

    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return an Integer
     */
    public static int getInt(Scanner pipe, String prompt)
    {
        int retInt = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" +prompt + ": ");
            if (pipe.hasNextInt()) // input must be int
            {
                retInt = pipe.nextInt(); //sets output to the given int
                pipe.nextLine();
                done= true; //loop until int is given
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("\nYou must enter a valid number not: " + trash); // print error
            }


        }while(!done);

        return retInt;
    }

    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a double
     */

    public static double getDouble(Scanner pipe, String prompt)
    {
        double retDouble = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" +prompt + ": ");
            if (pipe.hasNextDouble()) // must input double
            {
                retDouble = pipe.nextDouble(); // set output to given double
                pipe.nextLine();
                done= true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("\nYou must enter a valid number not: " + trash);
            }


        }while(!done);

        return retDouble;
    }

    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return an integer within a certain range
     */

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int retRangedInt = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" +prompt + " [" + low + " - " + high + "]: " );
            if (pipe.hasNextInt())
            {
                retRangedInt = pipe.nextInt();
                pipe.nextLine();
                if(retRangedInt >= low && retRangedInt <= high) //input must be inside the given range
                {
                    done = true;
                }
                else
                {
                    System.out.println("\nNumber is out of range [" + low + " - " + high + "] not: " + retRangedInt);
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("\nYou must enter a valid number not: " + trash);
            }


        }while(!done);

        return retRangedInt;
    }
    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a double within a certain range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double retRangedDouble = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" +prompt + " [" + low + " - " + high + "]: " );
            if (pipe.hasNextDouble())
            {
                retRangedDouble = pipe.nextDouble();
                pipe.nextLine();
                if(retRangedDouble >= low && retRangedDouble <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("\nNumber is out of range [" + low + " - " + high + "] not: " + retRangedDouble);
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("\nYou must enter a valid number not: " + trash);
            }


        }while(!done);

        return retRangedDouble;
    }

    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a true for Y and a false for N
     */

    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        boolean retYN = true;
        String getYN = "";
        String trash = "";
        boolean done = false;
        do
        {
            System.out.print("\n" + prompt + ": [Y/N]");
            getYN = pipe.nextLine();
            if (getYN.equalsIgnoreCase("Y"))
            {
                retYN = true; // on input Y sets boolean to true
                done = true;
            }
            else if (getYN.equalsIgnoreCase("N"))
            {
                retYN = false; // on input N sets boolean to false
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("\nYou must enter a valid number not: " + trash);
            }
        }while(!done);
        return retYN;

    }

    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String that matches a pattern
     */

    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        String retRegEx = "";
        boolean done = false;
        do
        {
            System.out.print("\n" + prompt + ": ");
            retRegEx= pipe.nextLine();
            if(retRegEx.matches(regEx)) // input must match the set pattern
            {
                done = true;
            }
            else
            {
                System.out.println("\nYou must enter a valid input not: " + retRegEx);
            }
        }while(!done);
        return retRegEx;
    }

}
