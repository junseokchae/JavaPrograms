import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class Application {
	boolean[][] airplane = new boolean [4][5];//make a 2D array for the booking
	String[][] airplaneLable = new String [4][5];//make a 2D array for the labeling the seats 
	String[][] passengerList = new String [4][5];
	String[][] passengerInfoList = new String [0][2];
	
	public void start() {
		readingFiles();
		booking();
	}
	
	private void readingFiles() {
		try
		{
			String [][] tempAirplane = readCSVFile("airplane.csv");
			for(int i=0;i<tempAirplane.length;i++)
			{
				for(int j=0;j<tempAirplane[i].length;j++)
				{
					airplane[i][j] = Boolean.parseBoolean(tempAirplane[i][j]);
				}
			}
			passengerList = readCSVFile("passengerlist.csv");
			if(passengerList.length==0)//When there is no file named passengerlist.csv, the passengerList array's length set to 0.
			{
				String[][] blankPassengerList = new String [4][5];
				passengerList = blankPassengerList;
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	private String[][] readCSVFile(String fileName){
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
	
	private void booking() {//method for booking
		airplaneLable = namingTable();//get the labels for the table
		while (true)
		{
			String htmlString = makingTable();//make the table from the array
			int reaction = displayTable(htmlString);//show the table and ask the user if s/he wants to book a seat
			if (reaction==0)//User says yes
			{
				boolean allSeatsBooked = allSeatsBooked();//check if there are available seats for the plane
				if(allSeatsBooked==true)//if no seats are available, show sorry message and close the program
				{
					JOptionPane.showMessageDialog(null, "Sorry, All the Seats Are Already Booked. Next Flight Leaves in 3 Hours.");
					exitProgram();
				}
				String name = getPassengerName();//get the name of the user and validate it
				int seatClass = getPassengerClass();//get the class selection
				if(noSeatsforClassBooked(seatClass)==true) //check if there are available seats for the class
				{
					String[] options = {"Yes", "No"}; //ask if the user wants to book a seat even if there is no seats for that class
					int fullClassReaction = JOptionPane.showOptionDialog(null, "There Are No Seats Available Matching Your Choice. Would You Like a Different Class", "Change Class?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (fullClassReaction==0)//Yes -> choose the other class
					{
						if(seatClass==0)//if the previous selection was 1st class,
							seatClass=1;//go to economy class
						else//if not
							seatClass=0;//go to 1st class
					}
					else//if user doesn't want to book a seat for the other class, say goodbye
					{
						JOptionPane.showMessageDialog(null, "Sorry, There Are No Seats that Match Your Requirements. Next Flight Leaves in 3 Hours.");
						exitProgram();
					}
				}
				int windowOrAisle = windowOrAisle();//get the preference about the seats
				seatAllocation(seatClass,windowOrAisle,name);//allocate the seat based on the information
			}
			else//if the user doesn't want to book a seat, end the program
				exitProgram();
		}
	}
	
	private String[][] namingTable(){//labeling the table: the central cells are for the aisle, so made them blank, the other seats should have rows(A,B,C,D) and columns(1,2,3,4) name
		String[][] table = new String[4][5];
		for(int i=0;i<airplaneLable[0].length/2;i++)
			table[0][i] = "A" + String.valueOf(i + 1);
		for(int i=(table[0].length/2)+1;i<table[0].length;i++)
			table[0][i] = "A" + String.valueOf(i);
		
		for(int i=0;i<table[1].length/2;i++)
			table[1][i] = "B" + String.valueOf(i + 1);
		for(int i=(table[1].length/2)+1;i<table[1].length;i++)
			table[1][i] = "B" + String.valueOf(i);
		
		for(int i=0;i<table[2].length/2;i++)
			table[2][i] = "C" + String.valueOf(i + 1);
		for(int i=(table[2].length/2)+1;i<table[2].length;i++)
			table[2][i] = "C" + String.valueOf(i);
		
		for(int i=0;i<table[3].length/2;i++)
			table[3][i] = "D" + String.valueOf(i + 1);
		for(int i=(table[3].length/2)+1;i<table[3].length;i++)
			table[3][i] = "D" + String.valueOf(i);
		return table;
	}
	
	private String makingTable() {//making a table: aisle -> yellow, available seat(false) -> green, and unavailable seat(true) -> red
		String htmlString="<html><body><table style=\"font-size:36\">";
		for(int i=0; i<airplane.length;i++)
		{
			airplane[i][airplaneLable[0].length/2]=true;
			htmlString+="<tr>";
			for(int j=0; j<airplane[i].length/2;j++)
			{
				if(airplane[i][j]==false)
					htmlString+="<td style=\"background-color:green\">" + airplaneLable[i][j] + "</td>";
				else
					htmlString+="<td style=\"background-color:red\">" + airplaneLable[i][j] + "</td>";
			}
			htmlString+="<td style=\"background-color:yellow\"></td>";
			for(int j=(airplane[i].length/2)+1; j<airplane[i].length;j++)
			{
				if(airplane[i][j]==false)
					htmlString+="<td style=\"background-color:green\">" + airplaneLable[i][j] + "</td>";
				else
					htmlString+="<td style=\"background-color:red\">" + airplaneLable[i][j] + "</td>";
			}
			htmlString+="</tr>";
			}
		return htmlString;
	}
	
	private int displayTable(String table) {//when show the table, ask user if s/he wants to book a seat
		Object[] options = {"Book Your Seat","Quit Program"};
		int reaction = JOptionPane.showOptionDialog(null, table,"Airline Seat Booker", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
		return reaction;
	}
	
	private String getPassengerName() {//get the user's name and validate it
		String name;
		while(true)
		{
			name = JOptionPane.showInputDialog("Please Enter the Passenger's Name: ");//get the name
			boolean ifNameIncludeNumbers=true;//validate if the name includes any number
			String numbers = "1234567890";
			char testChar;
			String testName = name.toLowerCase();
			for(int i=0;i<name.length();i++)
			{
				testChar = testName.charAt(i);
				if(numbers.indexOf(testChar)<0)
					ifNameIncludeNumbers = false;
				else
					ifNameIncludeNumbers = true;
			}
			if(name.length()==0)//if user didn't input anything
				JOptionPane.showMessageDialog(null, "You've entered nothing. Please try again.");
			else if(ifNameIncludeNumbers)//if user's input contains number
				JOptionPane.showMessageDialog(null, "You've entered name including numeric value. Please try again.");
			else if(name.equals("null"))//if user's input was null
				JOptionPane.showMessageDialog(null, "You cannot input the name 'null'. Please try again.");
			else break;//if the name is valid, go further
		}
		return name;//and give the name into the program
	}
	
	private int getPassengerClass() {//get the user's class
		String[] options = {"First Class", "Economy Class"};
		int reaction = JOptionPane.showOptionDialog(null, "Please Select Your Preferred Class: ", "Class Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return reaction;
	}
	
	private int windowOrAisle() {//get the user's preference
		String[] options = {"Window Seat", "Aisle Seat"}; 
		int reaction = JOptionPane.showOptionDialog(null, "Please Select Your Seat Type: ", "Seat Type Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return reaction;
	}
	
	private void seatAllocation(int seatClass, int windowOrAisle, String name) {//allocate the seat
		if(seatClass==0)//1st class
		{
			if(windowOrAisle==0)//window
			{
				if (airplane[0][0]==false)//check A1
				{
					airplane[0][0] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: A1");
					passengerList[0][0] = name;
				}
				else if (airplane[0][4]==false)//check A4
				{
					airplane[0][4] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: A4");
					passengerList[0][4] = name;
				}
				else if (airplane[1][0]==false)//check B1
				{
					airplane[1][0] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: B1");
					passengerList[1][0] = name;
				}
				else if (airplane[1][4]==false)//check B4
				{
					airplane[1][4] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: B4");
					passengerList[1][4] = name;
				}
				else//when there are no available seats for 1st class and window seats
					noAvailableSeats(seatClass,windowOrAisle,name);
			}
			else//1st class, aisle seat
			{
				if (airplane[0][1]==false)//check A2
				{
					airplane[0][1] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: A2");
					passengerList[0][1] = name;
				}
				else if (airplane[0][3]==false)//check A3
				{
					airplane[0][3] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: A3");
					passengerList[0][3] = name;
				}
				else if (airplane[1][1]==false)//check B2
				{
					airplane[1][1] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: B2");
					passengerList[1][1] = name;
				}
				else if (airplane[1][3]==false)//check B3
				{
					airplane[1][3] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: B3");
					passengerList[1][3] = name;
				}
				else//when there are no available seats for 1st class and aisle seats
					noAvailableSeats(seatClass,windowOrAisle,name);
			}
		}
		else//economy class
		{
			if(windowOrAisle==0)//window seats
			{
				if (airplane[2][0]==false)//check C1
				{
					airplane[2][0] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: C1");
					passengerList[2][0] = name;
				}
				else if (airplane[2][4]==false)//check C4
				{
					airplane[2][4] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: C4");
					passengerList[2][4] = name;
				}
				else if (airplane[3][0]==false)//check D1
				{
					airplane[3][0] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: D1");
					passengerList[3][0] = name;
				}
				else if (airplane[3][4]==false)//check D4
				{
					airplane[3][4] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: D4");
					passengerList[3][4] = name;
				}
				else//when there are no available seats for economy class and window seats
					noAvailableSeats(seatClass,windowOrAisle,name);
			}
			else//aisle seats
			{
				if (airplane[2][1]==false)//check C2
				{
					airplane[2][1] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: C2");
					passengerList[2][1] = name;
				}
				else if (airplane[2][3]==false)//check C3
				{
					airplane[2][3] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: C3");
					passengerList[2][3] = name;
				}
				else if (airplane[3][1]==false)//check D2
				{
					airplane[3][1] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: D2");
					passengerList[3][1] = name;
				}
				else if (airplane[3][3]==false)//check D3
				{
					airplane[3][3] = true;
					JOptionPane.showMessageDialog(null, "Name: " + name +"\nAssigned Seat: D3");
					passengerList[3][3] = name;
				}
				else//when there are no available seats for economy class and aisle seats
					noAvailableSeats(seatClass,windowOrAisle,name);
			}
		}
	}
	
	private void noAvailableSeats(int seatClass, int windowOrAisle, String name) {//when specific class and seat type have no available seats
		String[] options = {"Yes", "No"};//check if the user wants to book for another seat type
		int reaction = JOptionPane.showOptionDialog(null, "There Are No Seats Available Matching Your Choice. Would You Like a Different Seat Type", "Change Seat Type?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (reaction == 0)//if yes, try to find another seat for another seat type
		{
			if (windowOrAisle==0)//when user tried to book a seat for window seats
			{
				windowOrAisle = 1;//try to book a seat for aisle seats
				seatAllocation(seatClass,windowOrAisle,name);
				booking();
			}
			else
			{
				windowOrAisle = 0;//if not, try to book a seat for window seats
				seatAllocation(seatClass,windowOrAisle,name);
				booking();
			}
		}
		else//if user doesn't want to book a seat for another seat type, say goodbye
		{
			JOptionPane.showMessageDialog(null, "Sorry, There Are No Seats that Match Your Requirements. Next Flight Leaves in 3 Hours.");
			exitProgram();
		}
	}
	
	private boolean allSeatsBooked() {//check if there are seats available to book
		boolean allSeatsBooked=true;//assume there are no seats available
		for (int i=0;i<airplane.length;i++)//if the program finds any available seat, set the status as false
		{
			for(int j=0;j<airplane[0].length;j++)
			{
				if (airplane[i][j]==false)
					allSeatsBooked=false;
			}
		}
		return allSeatsBooked;
	}
	
	private boolean noSeatsforClassBooked(int seatClass) {//check if there are any available seats for the class
		boolean noSeatsforClassBooked=true;//same as checking for the entire plane
		if(seatClass==0)//1st class
		{
			for(int j=0;j<airplane[0].length;j++)
			{
				if (airplane[0][j]==false)//A row
					noSeatsforClassBooked=false;
				if (airplane[1][j]==false)//B row
					noSeatsforClassBooked=false;
			}
		}
		else//economy class
		{
			for(int j=0;j<airplane[0].length;j++)
			{
				if (airplane[2][j]==false)//C row
					noSeatsforClassBooked=false;
				if (airplane[3][j]==false)//D row
					noSeatsforClassBooked=false;
			}			
		}
		return noSeatsforClassBooked;
	}	
	
	private void exitProgram() {
		try {
			saveArray(airplane,"airplane");
			saveArray(passengerList,"passengerlist");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error occured");
			
		}
		String[] Options = {"Yes","No"};
		int reportOrNot = JOptionPane.showOptionDialog(null, "Would you like to get the passengers' information?", "Want Report?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, Options, Options[0]);
		if(reportOrNot == 0)
			getReport();
		System.exit(0);
		
	}
	
	private void saveArray(String[][] array,String arrayName) throws IOException {
		BufferedWriter br = new BufferedWriter(new FileWriter(arrayName + ".csv"));
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
	
	private void saveArray(boolean[][] array,String arrayName) throws IOException {
		BufferedWriter br = new BufferedWriter(new FileWriter(arrayName + ".csv"));
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<array.length;i++)
		{
			for (boolean element : array[i]) {
				sb.append(element);
				 sb.append(",");
				}
			sb.append("\n");
			
		}
		String textSB = sb.toString();
		br.write(textSB);
		br.close();//from https://stackoverflow.com/questions/15364342/export-array-values-to-csv-file-java(Tataje(2013))
	}
	
	private void makepassengerInfoList()
	{
		for (int i = 0; i < passengerList.length;i++)
		{
			for (int j = 0; j < passengerList[i].length;j++)
			{
				try
				{
					if (!passengerList[i][j].equals(null))
					{
						String[][] temppassengerInfoList = new String [passengerInfoList.length+1][2];
						System.arraycopy(passengerInfoList,0,temppassengerInfoList,0,passengerInfoList.length);
						String[] passengerAndSeat = new String [2];
						passengerAndSeat[0] = passengerList[i][j];
						passengerAndSeat[1] = airplaneLable[i][j];
						temppassengerInfoList[passengerInfoList.length] = passengerAndSeat;
						passengerInfoList = temppassengerInfoList;
					}
				}
				catch(Exception e)
				{
					
				}
			}
		}
	}
	
	private void getReport()
	{
		makepassengerInfoList();
		String[] nameList = new String[passengerInfoList.length];
		for (int i=0;i < nameList.length;i++)
		{
			nameList[i] = passengerInfoList[i][0];
		}
		Arrays.sort(nameList);
		String[][] sortedPassengerInfoList = new String[passengerInfoList.length][2];
		for(int i=0;i < nameList.length;i++)
		{
			for(int j=0;j<passengerInfoList.length;j++)
			{
				if(nameList[i]==passengerInfoList[j][0])
				{
					sortedPassengerInfoList[i][0] = nameList[i];
					sortedPassengerInfoList[i][1] = passengerInfoList[j][1];
				}
			}
		}
		String report = "The following passengers will be onboard.";
		for (int i = 0; i < sortedPassengerInfoList.length;i++)
		{
			report += "\n" + sortedPassengerInfoList[i][0] + " " + sortedPassengerInfoList[i][1];
		}
		JOptionPane.showMessageDialog(null, report);
	}

}


/*
A small airline has just purchased a computer for its new automated reservations system. 
You’ve been asked to develop the new system. 
You are to write an application to assign seats on each flight of the 
airline’s only plane (capacity 16 seats – i.e. 4x4 2D array)
Your application will ask users for both their class and seat type preferences. 
A typical interface will ask “Please enter passenger name”, 
“Please type 1 for First Class or 2 for Economy” then “Please type 1 for Window or  2 for Aisle. 
Your application should then display a boarding pass indicating the person’s name, 
seat number (e.g. Row 1 Seat 1), and class. 
Other than the seat being taken, there is no need to retain ticket 
information once the ticket has been produced unless attempting the research component.
In the program’s code, classes are determined by rows, seats by columns. 
Use a two-dimensional array of primitive type Boolean 
to represent the seating chart of the plane that is traversed using 
nested for loops. 
You must use a single 2D Boolean array for the entire airplane, not one for each class.
 Initialize all the elements of the array to false to indicate that all the seats are empty. 
 As each seat is assigned, set the corresponding element of the array to true 
 to indicate that the seat is no longer available.
Your application should never assign a seat that has already been assigned. 
It is assumed that passengers are
‘class centric’ and would choose any remaining seat in their preferred class 
before electing to move classes.
In other words, class has precedence over aisle/window seating preferences. 
When a class section is full, 
your application should ask the person 
if it’s acceptable to be placed in the other class (and vice versa). 
If yes, ask for their seat type choice and make the appropriate seat assignment. 
In any case where there are 
still seats available on the aircraft but not one that is acceptable to the passenger, display the message “Next
flight leaves in 3 hours” and return to processing the next passenger in line. If the whole plane is full, 
display the same message and provide a means to exit the program. 
In addition, your application must:
Be able to serve several passengers before it closes. It would be wise to provide a means of closing 
(i.e. type -1 to exit) the application before the aircraft is full. Your program does not need to process 
multiple airplanes before shutting down. 
Display a visual representation of the seating on the aircraft when the application starts and each 
time a seat is booked.
On the tickets that the passenger sees, seats will be referred to by their array row and col numbers 
+1. (E.g. Row 0, Col 0 becomes Row 1, Seat 1 on the ticket)
Validation for the name field will include: not empty, letters only
NB: Citations - Remember that citations must be provided for any code, algorithm, text or image copied from another
source (e.g. the Web, a textbook, an online tutorial, etc.).  Not attributing appropriately (plagiarism) or using illegally 
copied materials (copyright breach) are serious academic offenses. If you are in doubt as to when or how to cite, 
consult with your instructor and the resources provided by the college.
*/

/*
SAAD
1. User Input(Class, Seat)
2. Search for seat type in class requested
3-A. Book Seat -> 1
3-B. User Input (Another seat type in same class?)
3-B-1. Book another seat type in same class
3-B-2. Book another seat in another class
Pseudo code
make a 2D array(boolean, 4X5) - default: All FALSE
show the table and ask if the user wants to book a seat
if yes, ask 
	name and verify it
		if it contains number
		if it is nothing(null)
		if it is literally null
	check if there is any available seat
		yes
			ask class and check if there is any available seat for that class
				if there is no available seat for that class, ask user if s/he wants to book for another class, if yes, move there, if no, say goodbye.
				1st - ask if the user wants to book a window seat or aisle seat
					window 
						check A1
							assign
							check A4
								assign
								check B1
									assign
									check B4
										assign
										check if the user wants to book an aisle seat
											move there
											say goodbye
					aisle
						check A2
							assign
							check A3
								assign
								check B2
									assign
									check B3
										assign
										check if the user wants to book an aisle seat
											move there
											say goodbye
				economy - ask if the user wants to book a window seat or aisle seat
					window- ask if the user wants to book a window seat or aisle seat
					window 
						check C1
							assign
							check C4
								assign
								check D1
									assign
									check D4
										assign
										check if the user wants to book an aisle seat
											move there
											say goodbye
					aisle
						check C2
							assign
							check C3
								assign
								check D2
									assign
									check D3
										assign
										check if the user wants to book an aisle seat
											move there
											say goodbye
		no -> say goodbye
if no, program will be closed

Add the analysis part.
When make a table, try to find if the program can open the file including the information.(try-catch)
If there is a file, make a table based on the information from the file.
If it's not make a blank table.



*/