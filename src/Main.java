import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main
{
    private static ArrayList<String> list = new ArrayList<>(); //initialize in main in order to use in methods
    private static JFileChooser chooser = new JFileChooser();
    private static File workingDirectory = new File(System.getProperty("user.dir"));
    private static Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args)
    {


        final String menu = "A - Add  D - Delete  V - View  Q - Quit  O- Open  S- Save  C- Clear "; // initialize menu for user
        boolean done = false; // boolean for do while loop
        boolean needsSaved = false;
        String cmd = "";

        do
        {
            cmd = SafeInput.getRegExString(in, menu, "[AaDdVvQqOoSsCc]"); //asks user for an item from the menu

            switch(cmd)
            {
                case "A": //if they choose "a" it runs addItem method
                    addItem();
                    needsSaved = true;
                    break;
                case "D": //if they choose "d" it runs deleteItem method
                    deleteItem();
                    needsSaved = true;
                    break;
                case "V": //if they choose "p" it runs displayList method
                    displayList();
                    break;
                case "O":
                    openFile();
                    break;
                case "S":
                    if(SafeInput.getYNConfirm(in, "Are you sure you want to override the current saved file"))
                    {
                        saveFile();
                    }
                    break;
                case "C":
                    clearList();
                    needsSaved = true;
                    break;
                case "Q": //if they choose "q" it runs quitList method
                    if(needsSaved)
                    {
                        if(SafeInput.getYNConfirm(in, "Do you want to save your list?"))
                        {
                            saveFile();
                            needsSaved = false;
                            done = quitList();
                        }
                    }
                    else
                    {
                        done = quitList();
                    }
                    break;

            }
        }while(!done);
    }

    private static void clearList()
    {
        if(SafeInput.getYNConfirm(in, "Are you sure you want to clear the list"))//clears list after asking user
        {
            list.subList(0, list.size()).clear();
        }

    }

    private static void saveFile() //writes the current list in the text file
    {
        try
        {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : list)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("File saved");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void openFile() //opens and reads the text file
    {
        String rec = "";
        overrideList();

        try
        {

            InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            int num = 0;


            while (reader.ready())
            {
                num++;
                rec = reader.readLine(); // sets rec to the current line of the file
                System.out.printf("\n%4d%20s",num, rec);
                list.add(num-1, rec);

            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void overrideList()
    {
        if(SafeInput.getYNConfirm(in, "Are you sure you want to override the current list")) //clears list after asking user
        {
           list.subList(0, list.size()).clear();
        }
    }

    private static boolean quitList()
    {
        return SafeInput.getYNConfirm(in, "Are you sure you want to quit?"); //returns the boolean for the users choice of y/n
    }

    private static void deleteItem()
    {
        list.remove( (SafeInput.getRangedInt(in, "What is the number of the item in the list that you want to delete?", 1, list.size()))-1 ); // asks for the ranged int that represent the item they wish to delete and subtract 1 to correspond to the array
    }

    private static void addItem()
    {
        list.add(SafeInput.getNonZeroLenString(in, "Type the item you would like to add to the list")); //asks for a string to add to the list
    }

    private static void displayList()
    {
        if(!list.isEmpty())
        {
            System.out.println();
            for (int x = 0; x < list.size(); x++)
            {
                System.out.printf("%3d%15s", x + 1, list.get(x)); //displays the list number "x + 1" and the list item
                System.out.println(); //new line
            }
        }
        else
        {
            System.out.println("The List is Empty"); //prints if the list is empty
        }
    }
}