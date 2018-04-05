import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Controller {
	public static boolean gpsValidation(String gps)
	{
		boolean result = GPS.validateGPS(gps);
		return result;
	}
	public static void savePenguin(Animal animal, GPS gps, double pressure) {
		String[] gpsList = gps.getCoordinates();
		int gpsLength = gpsList.length;
		String[] dataEntry = new String[3+gpsLength];
		animal = (Penguin) animal;
		dataEntry[0] = String.valueOf(animal.getSpecies());
		dataEntry[1] = String.valueOf(animal.getSex());
		dataEntry[2] = String.valueOf(pressure);
		for(int a = 0;a<gpsLength;a++)
			dataEntry[3+a] = String.valueOf(gpsList[a]);
		
		try {
			String[][] tempData = readCSVFile("research.csv");
			int numOfColumn = 0;
			for(int i=0;i<tempData.length;i++)
			{
				if(numOfColumn < tempData[i].length)
					numOfColumn = tempData[i].length;
			}
			if(numOfColumn<dataEntry.length)
				numOfColumn = dataEntry.length;
			String[][] data = new String[tempData.length+1][numOfColumn];
			for(int i=0;i<tempData.length;i++)
			{
				for(int j=0;j<tempData[i].length;j++)
				{
					data[i][j] = tempData[i][j];
				}
			}
			
			for(int k=0;k<dataEntry.length;k++)
			{
				data[tempData.length][k] = dataEntry[k];
			}
			saveArray(data);	
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error occured. Check all the inputs and try again.");
		}
	}
	public static void saveSeaLion(Animal animal, GPS gps, int numOfSpots) {
		String[] gpsList = gps.getCoordinates();
		int gpsLength = gpsList.length;
		String[] dataEntry = new String[3+gpsLength];
		animal = (SeaLion) animal;
		dataEntry[0] = String.valueOf(animal.getSpecies());
		dataEntry[1] = String.valueOf(animal.getSex());
		dataEntry[2] = String.valueOf(numOfSpots);
		for(int a = 0;a<gpsLength;a++)
			dataEntry[3+a] = String.valueOf(gpsList[a]);
		try {
			String[][] tempData = readCSVFile("research.csv");
			int numOfColumn = 0;
			for(int i=0;i<tempData.length;i++)
			{
				if(numOfColumn < tempData[i].length)
					numOfColumn = tempData[i].length;
			}
			if(numOfColumn<dataEntry.length)
				numOfColumn = dataEntry.length;
			String[][] data = new String[tempData.length+1][numOfColumn];
			for(int i=0;i<tempData.length;i++)
			{
				for(int j=0;j<tempData[i].length;j++)
				{
					data[i][j] = tempData[i][j];
				}
			}
			
			for(int k=0;k<dataEntry.length;k++)
			{
				data[tempData.length][k] = dataEntry[k];
			}
			saveArray(data);	
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error occured. Check all the inputs and try again.");
		}
	}
	public static void saveWarlus(Animal animal, GPS gps, String dentalHealth) {
		String[] gpsList = gps.getCoordinates();
		int gpsLength = gpsList.length;
		String[] dataEntry = new String[3+gpsLength];
		animal = (Warlus) animal;
		dataEntry[0] = String.valueOf(animal.getSpecies());
		dataEntry[1] = String.valueOf(animal.getSex());
		dataEntry[2] = dentalHealth;
		for(int a = 0;a<gpsLength;a++)
			dataEntry[3+a] = String.valueOf(gpsList[a]);
		try {
			String[][] tempData = readCSVFile("research.csv");
			int numOfColumn = 0;
			for(int i=0;i<tempData.length;i++)
			{
				if(numOfColumn < tempData[i].length)
					numOfColumn = tempData[i].length;
			}
			if(numOfColumn<dataEntry.length)
				numOfColumn = dataEntry.length;
			String[][] data = new String[tempData.length+1][numOfColumn];
			for(int i=0;i<tempData.length;i++)
			{
				for(int j=0;j<tempData[i].length;j++)
				{
					data[i][j] = tempData[i][j];					
				}
			}
			
			for(int k=0;k<dataEntry.length;k++)
			{
				data[tempData.length][k] = dataEntry[k];
			}
			saveArray(data);	
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error occured. Check all the inputs and try again.");
		}
	}
	public static String[][] readCSVFile(String fileName){
		String[][] data = new String[0][];//csv data line count=0 initially
		try
		{
			int i = 0;//line count of csv
			String thisLine;
			FileInputStream fis = new FileInputStream(fileName);
			DataInputStream myInput = new DataInputStream(fis);
			
			while ((thisLine = myInput.readLine()) != null) 
			{
				++i;//increment the line count when new line found
				String[][] newdata = new String[i][2];//create new array for data
				String strar[] = thisLine.split(",");//get contents of line as an array
				newdata[i - 1] = strar;//add new line to the array
				System.arraycopy(data, 0, newdata, 0, i - 1);//copy previously read values to new array
				data = newdata;//set new array as csv data
			}
			for(int j = 0; j < data.length;j++)
			{
				for(int k = 0; k < data[j].length;k++)
				{
					if(data[j][k].equals("null"))
						data[j][k] = null;
				}
			}
		}
		catch(Exception e)
		{
		
		}
		return data;
	}//from https://stackoverflow.com/questions/33034833/converting-csv-file-into-2d-array(Vascellaro(2017))
	
	public static void saveArray(String[][] array) throws IOException {
		BufferedWriter br = new BufferedWriter(new FileWriter("research.csv"));
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<array.length;i++)
		{
			for (String element : array[i]) {
				sb.append(element);
				 sb.append(",");
				}
			sb.append("\n");
			
		}
		String textSB = sb.toString();
		br.write(textSB);
		br.close();//from https://stackoverflow.com/questions/15364342/export-array-values-to-csv-file-java(Tataje(2013))
	}
	
	public static String convertArrayIntoText(String[][] array) {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<array.length;i++)
		{
			for (String element : array[i]) {
				sb.append(element);
				 sb.append(",");
				}
			sb.append("\n");
			
		}
		String text = sb.toString();
		return text;
	}
	
	public static String getReport(String[][] array) {
		String report = null;
		report = "Species: " + array[0][0] + "\nSex: " + array[0][1] + "\nWeight(lb): " + array[0][2];
		if(array[0][0].equals("Penguin"))
		{	
			report = report + "\nBlood Pressure: " + array[0][3] + "\nGPS: ";
			for(int j=4;j<array[0].length;j++)
				report = report + array[0][j] + "   ";
			report = report + "\n";
		}
		else if(array[0][0].equals("Sea Lion"))
		{	
			report = report + "\nNumber of Spots: " + array[0][3] + "\nGPS: ";
			for(int j=4;j<array[0].length;j++)
				report = report + array[0][j] + "   ";
			report = report + "\n";
		}
		else if(array[0][0].equals("Warlus"))
		{	
			report = report + "\nDental Health: " + array[0][3] + "\nGPS: ";
			for(int j=4;j<array[0].length;j++)
				report = report + array[0][j] + "   ";
			report = report + "\n";
		}
		for(int i=1;i<array.length;i++)
		{
			report = report + "Species: " + array[i][0] + "\nSex: " + array[i][1] + "\nWeight(lb): " + array[i][2];
			if(array[i][0].equals("Penguin"))
			{	
				report = report + "\nBlood Pressure: " + array[i][3] + "\nGPS: ";
				for(int j=4;j<array[i].length;j++)
					report = report + array[i][j] + "   ";
				report = report + "\n";
			}
			else if(array[i][0].equals("Sea Lion"))
			{	
				report = report + "\nNumber of Spots: " + array[i][3] + "\nGPS: ";
				for(int j=4;j<array[i].length;j++)
					report = report + array[i][j] + "   ";
				report = report + "\n";
			}
			else if(array[i][0].equals("Warlus"))
			{	
				report = report + "\nDental Health: " + array[i][3] + "\nGPS: ";
				for(int j=4;j<array[i].length;j++)
					report = report + array[i][j] + "   ";
				report = report + "\n";
			}
			
			
		}
		return report;
	}
	
}
