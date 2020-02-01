package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     * @throws FileNotFoundException 
     * @throws IOException 
     * @throws CsvValidationException 
     */
    public static void main(String... args) throws IOException, CsvValidationException{

        // Your preparation code …   	
        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        
        App myApp = new App();
        
        // Use the same method for both files
        // File to be called is defined as parameter - call weather.csv first
        dayWithSmallestTempSpread = myApp.readCSV("weather.csv");
        
        // Then call the football.csv
        teamWithSmallestGoalSpread = myApp.readCSV("football.csv");
        
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
	public String readCSV(String fileName) throws IOException, CsvValidationException{
    	// Get the file name
    	String csvFileName = fileName;
    	// Get the correct file based on given file name
    	File csvFile = new File("C:\\Users\\sandr\\eclipse-workspace\\Exxcellent\\resources\\de\\exxcellent\\challenge\\" + csvFileName);
    	// The first 3 columns are read for weather.csv
    	// refer notes for further options
    	CSVReader reader = new CSVReader(new FileReader(csvFile));
    	String [] nextLine;
    	List<String> list = new ArrayList<String>();
        int lineNumber = 0;
    	if(csvFileName == "weather.csv") {    	
         while ((nextLine = reader.readNext()) != null) {
           lineNumber++;
           list.add(nextLine[0] +";"+ nextLine[1] +";"+ nextLine[2]);
         }
    	} else if(csvFileName == "football.csv") {
            while ((nextLine = reader.readNext()) != null) {
                lineNumber++;
                list.add(nextLine[0] +";"+ nextLine[5] +";"+ nextLine[6]);
              }    		
    	} else {
    		// Different file name
    		System.out.println("File name "+ csvFileName + " is not supported!");
    	}
    	return calculateValueDifference(list);
    	}
	   
    public String calculateValueDifference(List csvValues) {
    	int calculatedDifference = 0;
    	List<String> list = new ArrayList<String>();
    	// Header is not needed, hence set i=0 instead of 0
    	for (int i = 1; i < csvValues.size(); i++)
    	{	 
    	  String currentValue = (String) csvValues.get(i);
    	  String [] values =  currentValue.split(";");
    	  // Calculate the difference between the two temperature values
    	  calculatedDifference = (Integer.parseInt(values[1]) - Integer.parseInt(values[2]));
    	  // Still add the day number to the list, because that is what will be printed later
    	  list.add(values[0] + ";" + calculatedDifference);  	 
    	}
    	return findSmallestValue(list);
    }
	public String findSmallestValue(List<String> list) {
		String smallestValue=" ";
		int newSmallestValue = 100;
		String currentListItem = " ";
		List<String> splitValues = new ArrayList<String>();
		
    	for (int i = 1; i < list.size(); i++)
    	{	 
    	  	currentListItem = list.get(i);
    	  	String [] values = currentListItem.split(";");
    	  	// Compare if the currently handled value is smaller than the smallest value that has already been found
    	  	// If current value is smaller, we have a new smallest value
    	  	if(newSmallestValue > Integer.parseInt(values[1])) {
    	  		// newSmallestValue is the actual difference
    	  		newSmallestValue = Integer.parseInt(values[1]);
    	  		// The day or team name which will be printed later
    	  		smallestValue = values[0];
    	  	}
    	  	}	
		return smallestValue;
	}	
   }

