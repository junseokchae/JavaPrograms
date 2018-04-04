import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Assignment 1: Sales Commission Calculator
// Original Text
	// A large company pays its salespeople on a commission basis. 
	//The salespeople receive $200 per week plus 9% of their gross sales for that week. 
	//For example, 
		//a salesperson who sells $5000 worth of merchandise in a week receives $200 plus 9% of $5000 (450), 
			//for a total of $650 (200+450). 
	//You’ve been supplied with a list of the items sold by each salesperson. 
	//The values of these items are as follows:
		//Item	Value (for one item)
			//1	239.99
			//2	129.75
			//3	99.95
			//4	350.89
	//Develop a Java application 
		//that inputs one salesperson’s (at a time) items sold for a week and calculates 
		//and displays that salesperson’s weekly earnings. 
		//Ask for each item in order and the number sold before moving onto the next. 
		//Note that there is no limit to the number of items that can be sold (e.g. 100 #1’s etc). 
		//Once amounts for all items have been entered, 
			//have the application display the salespersons weekly earning (i.e. base + commission). 
		//Please ensure that the output is displayed in currency format (i.e. $###.##). 
		//In addition, please display the salespersons name (first and last) with the output 
			//and ensure that only numeric amounts may be entered for number sold. 
		//Once you have processed one salesperson, ask if they want to process another or exit the program. 
		//Produce a sales activity report showing the sum of all sales, 
			//average value of a sale (sum of all sales / total # of sales), 
			//the name of the product most sold and the name of the top sales person.
	//Please note: 
		//•	It is possible for a salesperson to not to sell any of one specific type of item.
		//•	It would help you if you made a list of all the required validations before you began coding your program.
		//•	Exception handling must not be used for validation 
		//•	There is more than one correct way to format output to currency– research this.
		//•	Any cancel buttons, when pressed, should return the flow of control back to the point where your program asks if you need to process another salesperson
		//•	A clerk is the one who keys in the data.
		//•	If any two salespeople tie for top sales, record just the last one entered.

//Input

//Entering Data
	//Making Array - 2D
		//Row - Salespeople[1]
		//Column - Name, #1, #2, #3, #4, Total Sales, Final Earning[6]
	//Entering Data
		//Data Validation for Name
			//Name: Should be String except -- for quit the program.
				//String
					//Not --: Expand the Array
					//--: Proceed to Calculation
				//NonString: Require to another input
		//Data Input Name
		//Data Validation for Number
			//Data should be input as number
//Calculation
	//Total Sales: $ 239.99 * #1 + $ 129.75 * #2 + $ 99.95 * #3 + $ 350.89 * #4.
	//Final Earning: $ 200 + 0.09 * total sales($)

//Display
	//After inputing data: show the individual's weekly earning
	//After inputing data for all people: show the average, most sold item, and the top sales person



public class Application {
	private String[][] sales = new String[1][8]; //Making a 2D array for the entire data, it should be used for the entire program.
	private String name; //Making a variable and making it can be used for the entire program.
	public void start()
	{
		enterSalespeopleName();
	}
	
	private String[][] enterSalespeopleName()
	{
		
		
//		String inputName = dataValidationforNames();
//		while(inputName!="--")
		sales[0] = dataInputforSales();	//validating the name and input it
		while (true)//for expanding array
		{			
			int len = sales.length;
			String[][] tempsales = new String[len+1][8];
			System.arraycopy(sales, 0, tempsales, 0, sales.length);
			sales = tempsales;
			sales[len] =dataInputforSales(); 
		}
	}
	
	private String dataValidationforNames()
	{
		
		name = String.valueOf(JOptionPane.showInputDialog("Please enter the name of salesperson or -- to exit"));
		while(true)
		{
			Boolean ifNameIncludeNumbers=true;
			String numbers = "1234567890";
			char testChar;
			String testName = name.toLowerCase();
			for(int i=0;i<name.length();i++)
			{
				testChar = testName.charAt(i);
				if(numbers.indexOf(testChar)<0)
					ifNameIncludeNumbers = false;
				
			}
			
			if(ifNameIncludeNumbers)
			{
			JOptionPane.showMessageDialog(null, "You've entered name including numeric value. Please try again.");
			name = String.valueOf(JOptionPane.showInputDialog("Please enter the name of salesperson or -- to exit"));
			}
			else if(name=="null")
			{
				JOptionPane.showMessageDialog(null, "You cannot input the name 'null'. Please try again.");
				name = String.valueOf(JOptionPane.showInputDialog("Please enter the name of salesperson or -- to exit"));
			}
			else if(name.equals("--")) // need to fix it to stop input when the name is "--"
			{
				
					runSalesCalc();
			}
			
			else break;
			
		}
		return name;
	}
	
	private String[] dataInputforSales()
	{
		String salesData[] = new String[8];
		String inputName = dataValidationforNames();
		salesData[0] = inputName;
		
		
		int[] numofItems = new int [4];
		
		
		try
		{
			numofItems = numofItemsInput();
			
			/* int[] numofItems = new int [4];
			try
			{
				numofItems = numofItemsInput();			
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "You should enter the numeric values. Please try again.");
				numofItems = numofItemsInput();				
			}
			*/
			
			salesData[1] = String.valueOf(numofItems[0]);
			salesData[2] = String.valueOf(numofItems[1]);
			salesData[3] = String.valueOf(numofItems[2]);
			salesData[4] = String.valueOf(numofItems[3]); 
			double totalSales = 239.99 * numofItems[0] + 129.75 * numofItems[1] + 
					99.95 * numofItems[2] + 350.89 * numofItems[3]; 
			salesData[5] = String.format("%10.2f", totalSales);
			double earning = 200 + 0.09 * totalSales;
			salesData[6] = String.format("%10.2f", earning);
			
			int totalSoldItems = numofItems[0] + numofItems[1] + numofItems[2] + numofItems[3]; 
			
			double average = totalSales / totalSoldItems;
			String strAverage = String.valueOf(average);
			salesData[7] = strAverage;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Sorry, I cannot anything divide by 0.");
			numofItems = numofItemsInput();
			
			/* int[] numofItems = new int [4];
			try
			{
				numofItems = numofItemsInput();			
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "You should enter the numeric values. Please try again.");
				numofItems = numofItemsInput();				
			}
			*/
			
			salesData[1] = String.valueOf(numofItems[0]);
			salesData[2] = String.valueOf(numofItems[1]);
			salesData[3] = String.valueOf(numofItems[2]);
			salesData[4] = String.valueOf(numofItems[3]); 
			double totalSales = 239.99 * numofItems[0] + 129.75 * numofItems[1] + 
					99.95 * numofItems[2] + 350.89 * numofItems[3]; 
			salesData[5] = String.format("%10.2f", totalSales);
			double earning = 200 + 0.09 * totalSales;
			salesData[6] = String.format("%10.2f", earning);
			
			int totalSoldItems = numofItems[0] + numofItems[1] + numofItems[2] + numofItems[3]; 
			
			double average = totalSales / totalSoldItems;
			String strAverage = String.valueOf(average);
			salesData[7] = strAverage;
		}
		
		JOptionPane.showMessageDialog(null, salesData[0] + " earned $ " + salesData[6] +" for this week.");
		return salesData;
//		JTextField xField = new JTextField(5);
//	      JTextField yField = new JTextField(5);
//
//	      JPanel myPanel = new JPanel();
//	      myPanel.add(new JLabel("x:"));
//	      myPanel.add(xField);
//	      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
//	      myPanel.add(new JLabel("y:"));
//	      myPanel.add(yField);
//
//	      int result = JOptionPane.showConfirmDialog(null, myPanel, 
//	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
//	      if (result == JOptionPane.OK_OPTION) {
//	         System.out.println("x value: " + xField.getText());
//	         System.out.println("y value: " + yField.getText());
//	      }

	}
	private int[] numofItemsInput()
	{
		
		int[] numofItems = new int[4];
		String numofItem1 = numofItem(1);
		numofItems[0] = Integer.parseInt(numofItem1);
		String numofItem2 = numofItem(2);
		numofItems[1] = Integer.parseInt(numofItem2);
		String numofItem3 = numofItem(3);
		numofItems[2] = Integer.parseInt(numofItem3);
		String numofItem4 = numofItem(4);
		numofItems[3] = Integer.parseInt(numofItem4);
		//need to be cited & changed to get rid of Cancel button
		/* JTextField Item1 = new JTextField(5);
		JTextField Item2 = new JTextField(5);
		JTextField Item3 = new JTextField(5);
		JTextField Item4 = new JTextField(5);
		
		JPanel salesPanel = new JPanel();
		salesPanel.add(new JLabel("The number of #1 items this person sold: "));
		salesPanel.add(Item1);
		salesPanel.add(new JLabel("The number of #2 items this person sold: "));
		salesPanel.add(Item2);
		salesPanel.add(new JLabel("The number of #3 items this person sold: "));
		salesPanel.add(Item3);
		salesPanel.add(new JLabel("The number of #4 items this person sold: "));
		salesPanel.add(Item4);
					
		int result = JOptionPane.showConfirmDialog(null, salesPanel, 
	               "Please Enter the number of items this person sold:", JOptionPane.OK_OPTION);
		
		
		if (result == JOptionPane.OK_OPTION)
		{
			//need to be cited
			String soldItem1 = Item1.getText();
			String soldItem2 = Item2.getText();
			String soldItem3 = Item3.getText();
			String soldItem4 = Item4.getText();	
			
			
			int intSoldItem1 = Integer.parseInt(soldItem1);
			int intSoldItem2 = Integer.parseInt(soldItem2);
			int intSoldItem3 = Integer.parseInt(soldItem3);
			int intSoldItem4 = Integer.parseInt(soldItem4);
			
			//Total Sales: $ 239.99 * #1 + $ 129.75 * #2 + $ 99.95 * #3 + $ 350.89 * #4.
			//Final Earning: $ 200 + 0.09 * total sales($)
			
			numofItems[0] = intSoldItem1;
			numofItems[1] = intSoldItem2;
			numofItems[2] = intSoldItem3;
			numofItems[3] = intSoldItem4;
		}
		else
		{
			runSalesCalc();
		}
		
			*/
		return numofItems;
	}
	private String numofItem(int x)
	{
		String numofItem = null;
		Boolean ifNumberisnotNumber=true;
		while (ifNumberisnotNumber)
		{
			numofItem = String.valueOf(JOptionPane.showInputDialog("Please enter the number of item " + x + " " + name + " sold for this week:"));
			
			String numbers = "1234567890";
			char testChar;
			for(int i=0;i<numofItem.length();i++)
			{
				testChar = numofItem.charAt(i);
				if(numbers.indexOf(testChar)<0)
				{
					JOptionPane.showMessageDialog(null, "You should enter the numeric values. Please try again.");
				}
				else
					ifNumberisnotNumber = false;
			}
		}
		int intnumofItem = Integer.parseInt(numofItem);
		if (intnumofItem<0)
		{
			JOptionPane.showMessageDialog(null,"You should enter the value no less than 0. Please try again.");
			numofItem = String.valueOf(JOptionPane.showInputDialog("Please enter the number of item " + x + " " + name + " sold for this week:"));
			intnumofItem = Integer.parseInt(numofItem);
		}
		
		
		return numofItem;
	}
	private void runSalesCalc() 
	{
		/*
		 show the average, most sold item, and the top sales person
		 */
		int len = sales.length;//for reducing the size of array, since the last row contains only --.
		String[][] tempsales = new String[len-1][8];
		System.arraycopy(sales, 0, tempsales, 0, tempsales.length);
		sales = tempsales;
		
		
		int total = 0;
		int totalnum1 = 0;
		int totalnum2 = 0;
		int totalnum3 = 0;
		int totalnum4 = 0;
		double totalSale = 0;
		double maxSale = 0;
		String maxSalesperson = null;
		
		for (int x=0;x <sales.length;x++)
		{
			total += Integer.parseInt(sales[x][1]) + Integer.parseInt(sales[x][2]) + Integer.parseInt(sales[x][3]) + Integer.parseInt(sales[x][4]);
			totalnum1 += Integer.parseInt(sales[x][1]);
			totalnum2 += Integer.parseInt(sales[x][2]);
			totalnum3 += Integer.parseInt(sales[x][3]);
			totalnum4 += Integer.parseInt(sales[x][4]);
			totalSale += Double.parseDouble(sales[x][5]);
			maxSale = Math.max(maxSale, Double.parseDouble(sales[x][5]));
			if (maxSale == Double.parseDouble(sales[x][5]))
				maxSalesperson = sales[x][0];
		}
		double average = totalSale / total;
		int maxtotal = Math.max(totalnum1,totalnum2);
		maxtotal = Math.max(maxtotal, totalnum3);
		maxtotal = Math.max(maxtotal, totalnum4);
		String maxitem;
		if (maxtotal==totalnum1)
			maxitem = "Item 1";
		else if (maxtotal==totalnum2)
			maxitem = "Item 2";
		else if (maxtotal==totalnum3)
			maxitem = "Item 3";
		else
			maxitem = "Item 4";
		JOptionPane.showMessageDialog(null,"Weekly Sales Report "
				+ "\n Total amount of sales of this week: " + totalSale
				+ "\n The average sales amount of this week's sale is: " + String.format("%10.2f", average) 
				+ "\nThe item which was sold most in this week is: " + maxitem 
				+ "\nThe person who sold most amount of money is: " + maxSalesperson);
		
		System.exit(0);
		/*
		 sales[0]: name
		 sales[1]~[4]: num of item 1~4
		 sales[5]: totalsale
		 sales[6]: earning
		 sales[7]: average
		 */
	}
}