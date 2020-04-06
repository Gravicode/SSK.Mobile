package pkdss.corelibrary;

import pkdss.corelibrary.model.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

import java.util.Scanner; // Import the Scanner class to read text files

public class Resources {

    public static String GetResources(String Name) {
        String data = "";
        switch (Name) {
            case "Data":
                data = ReadFromFile("C:\\jobs\\balittanah-pkdss\\SSK.Mobile\\data\\Data.txt");
                return data;
                //break;
            case "NPK":
                data = ReadFromFile("C:\\jobs\\balittanah-pkdss\\SSK.Mobile\\data\\NPK.csv");
                return data;
            //break;
            case "DataRekomendasi":
                data = ReadFromFile("C:\\jobs\\balittanah-pkdss\\SSK.Mobile\\data\\DataRekomendasi.txt");
                return data;
            //break;
            case "Lokasi":
                data = ReadFromFile("C:\\jobs\\balittanah-pkdss\\SSK.Mobile\\data\\Lokasi.txt");
                return data;
            //break;
            default:
                return null;

        }
    }

    public static String ReadFromFile(String FileName){
        String AllText = "";
        try {

            File myObj = new File(FileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                AllText+=data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return AllText;
    }

}
