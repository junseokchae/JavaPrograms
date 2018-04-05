import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.AbstractListModel;
import javax.swing.border.LineBorder;

public class AntarticWildlife extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldWeight;
	private JTextField textFieldBloodPressure;
	private JTextField textFieldNumOfSpots;
	private JTextField textFieldGPSPoint;
	private double weight = 0;
	private int selectedSpecies = 0;
	private double bloodPressure = 0;
	private int numOfSpots = 0;
	private String sex;
	private String dentalHealth;
	private String existingString = "";
	private String[] gpsList = new String[0];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AntarticWildlife frame = new AntarticWildlife();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AntarticWildlife() {
		setTitle("Antartic Wildlife");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelGUI = new JPanel();
		contentPane.add(panelGUI, "name_351640995102674");
		panelGUI.setLayout(null);
		
		JLabel lblChooseAnimal = new JLabel("Choose Animal");
		lblChooseAnimal.setBounds(10, 11, 150, 14);
		panelGUI.add(lblChooseAnimal);
		
		JComboBox comboBoxAnimal = new JComboBox();
		comboBoxAnimal.setModel(new DefaultComboBoxModel(new String[] {"", "Penguin", "Sea Lion", "Warlus"}));
		comboBoxAnimal.setBounds(105, 8, 98, 20);
		panelGUI.add(comboBoxAnimal);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setBounds(10, 36, 46, 14);
		panelGUI.add(lblSex);
		
		JComboBox comboBoxSex = new JComboBox();
		comboBoxSex.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboBoxSex.setBounds(105, 36, 98, 20);
		panelGUI.add(comboBoxSex);
		
		JLabel lblWeightInPounds = new JLabel("Weight in Pounds");
		lblWeightInPounds.setBounds(10, 61, 125, 14);
		panelGUI.add(lblWeightInPounds);
		
		textFieldWeight = new JTextField();
		textFieldWeight.setBounds(115, 58, 86, 20);
		panelGUI.add(textFieldWeight);
		textFieldWeight.setColumns(10);
		
		JLabel lblBloodPressure = new JLabel("Blood Pressure");
		lblBloodPressure.setVisible(false);
		lblBloodPressure.setBounds(10, 86, 108, 14);
		panelGUI.add(lblBloodPressure);
		
		textFieldBloodPressure = new JTextField();
		textFieldBloodPressure.setVisible(false);
		textFieldBloodPressure.setBounds(117, 85, 86, 20);
		panelGUI.add(textFieldBloodPressure);
		textFieldBloodPressure.setColumns(10);
		
		JLabel lblNumberOfSpots = new JLabel("Number of Spots");
		lblNumberOfSpots.setVisible(false);
		lblNumberOfSpots.setBounds(10, 86, 97, 14);
		panelGUI.add(lblNumberOfSpots);
		
		textFieldNumOfSpots = new JTextField();
		textFieldNumOfSpots.setVisible(false);
		textFieldNumOfSpots.setBounds(117, 83, 86, 20);
		panelGUI.add(textFieldNumOfSpots);
		textFieldNumOfSpots.setColumns(10);
		
		JLabel lblDentalHealth = new JLabel("Dental Health");
		lblDentalHealth.setVisible(false);
		lblDentalHealth.setBounds(9, 84, 98, 14);
		panelGUI.add(lblDentalHealth);
		
		JComboBox comboBoxDentalHealth = new JComboBox();
		comboBoxDentalHealth.setVisible(false);
		comboBoxDentalHealth.setModel(new DefaultComboBoxModel(new String[] {"GOOD", "AVERAGE", "POOR"}));
		comboBoxDentalHealth.setBounds(125, 86, 86, 20);
		panelGUI.add(comboBoxDentalHealth);
		
		JLabel lblGpsCoordinates = new JLabel("GPS Coordinates");
		lblGpsCoordinates.setBounds(233, 11, 150, 14);
		panelGUI.add(lblGpsCoordinates);
		
		JTextPane textPaneGPSCoordinates = new JTextPane();
		textPaneGPSCoordinates.setEditable(false);
		textPaneGPSCoordinates.setBounds(233, 36, 169, 77);
		panelGUI.add(textPaneGPSCoordinates);
		
		textFieldGPSPoint = new JTextField();
		textFieldGPSPoint.setText("");
		textFieldGPSPoint.setBounds(233, 124, 125, 20);
		panelGUI.add(textFieldGPSPoint);
		textFieldGPSPoint.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(363, 124, 61, 23);
		panelGUI.add(btnAdd);
		
		JButton btnSaveEntry = new JButton("Save Entry");
		btnSaveEntry.setBounds(54, 161, 125, 23);
		panelGUI.add(btnSaveEntry);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.setBounds(233, 161, 150, 23);
		panelGUI.add(btnGenerateReport);
		
		JPanel panelReport = new JPanel();
		panelReport.setVisible(false);
		contentPane.add(panelReport, "name_5986168952975");
		panelReport.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(10, 11, 404, 171);
		panelReport.add(scrollPane);
		
		comboBoxAnimal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedSpecies = comboBoxAnimal.getSelectedIndex();
				if(e.getItem()=="Penguin")
				{	
					lblBloodPressure.setVisible(true);
					textFieldBloodPressure.setVisible(true);
					lblNumberOfSpots.setVisible(false);
					textFieldNumOfSpots.setVisible(false);
					lblDentalHealth.setVisible(false);
					comboBoxDentalHealth.setVisible(false);
				}
				else if(e.getItem()=="Sea Lion")
				{
					lblBloodPressure.setVisible(false);
					textFieldBloodPressure.setVisible(false);
					lblNumberOfSpots.setVisible(true);
					textFieldNumOfSpots.setVisible(true);
					lblDentalHealth.setVisible(false);
					comboBoxDentalHealth.setVisible(false);
				}
				else if(e.getItem()=="Warlus")
				{	
					lblBloodPressure.setVisible(false);
					textFieldBloodPressure.setVisible(false);
					lblNumberOfSpots.setVisible(false);
					textFieldNumOfSpots.setVisible(false);
					lblDentalHealth.setVisible(true);
					comboBoxDentalHealth.setVisible(true);
				}
				else
				{	
					lblBloodPressure.setVisible(false);
					textFieldBloodPressure.setVisible(false);
					lblNumberOfSpots.setVisible(false);
					textFieldNumOfSpots.setVisible(false);
					lblDentalHealth.setVisible(false);
					comboBoxDentalHealth.setVisible(false);
				}	
			}
		});			
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String gps = textFieldGPSPoint.getText();
				existingString = textPaneGPSCoordinates.getText();
				boolean validation = Controller.gpsValidation(gps);
				if(validation)
				{	
					int len = gpsList.length;
					String[] tempGpsList = new String[len+1];
					System.arraycopy(gpsList, 0, tempGpsList, 0, gpsList.length);
					gpsList = tempGpsList;
					gpsList[len] = gps;
					if(existingString.equals(""))
						existingString = gps;
					else
						existingString = existingString + "\n" + gps;
					textPaneGPSCoordinates.setText(existingString);
				}
				else
					JOptionPane.showMessageDialog(null, "This is not a valid expression for gps coordinate. The proper format is (-)dd.ddddddd (-)dd.ddddddd.");
			}
		});

		btnSaveEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sex = (String) comboBoxSex.getSelectedItem();
				dentalHealth = (String) comboBoxDentalHealth.getSelectedItem();
				
				if(checkInput())
					if(existingString == "")
						JOptionPane.showMessageDialog(null, "To save data entry, you need to put GPS data.");
					else
						try {
							weight = Double.parseDouble(textFieldWeight.getText());
							if(selectedSpecies==1)
							{
								try
								{
									bloodPressure = Double.parseDouble(textFieldBloodPressure.getText());
									GPS gps = new GPS(gpsList);
									Animal penguin = new Penguin("Penguin", sex, weight, bloodPressure);
									Controller.savePenguin(penguin, gps, bloodPressure);
									comboBoxAnimal.setSelectedIndex(0);
									textFieldWeight.setText(null);
									comboBoxSex.setSelectedIndex(0);
									textFieldBloodPressure.setText(null);
									textPaneGPSCoordinates.setText(null);
									textFieldGPSPoint.setText(null);
								}
								catch(Exception e)
								{
									JOptionPane.showMessageDialog(null, "Penguin's blood pressure should be a number.");
								}
							}
							else if(selectedSpecies==2)
							{
								try
								{
									GPS gps = new GPS(gpsList);
									SeaLion seaLion = new SeaLion("Sea Lion", sex, weight, numOfSpots);
									numOfSpots = Integer.parseInt(textFieldNumOfSpots.getText());
									Controller.saveSeaLion(seaLion, gps,numOfSpots);
									comboBoxAnimal.setSelectedIndex(0);
									textFieldWeight.setText(null);
									comboBoxSex.setSelectedIndex(0);
									textFieldNumOfSpots.setText(null);
									textPaneGPSCoordinates.setText(null);
									textFieldGPSPoint.setText(null);
								}
								catch(Exception e)
								{
									JOptionPane.showMessageDialog(null, "Sea Lion's number of spots should be an integer.");
								}
							}
							else if(selectedSpecies==3)
							{
								GPS gps = new GPS(gpsList);
								Warlus warlus = new Warlus("Warlus", sex, weight, dentalHealth);
								Controller.saveWarlus(warlus, gps,dentalHealth);
								comboBoxAnimal.setSelectedIndex(0);
								textFieldWeight.setText(null);
								comboBoxSex.setSelectedIndex(0);
								comboBoxDentalHealth.setSelectedIndex(0);
								textPaneGPSCoordinates.setText(null);
								textFieldGPSPoint.setText(null);
							}
						}
						catch(Exception e) {
							JOptionPane.showMessageDialog(null, "Weight should be number.");
						}
				
			}
		});
		
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[][] data = Controller.readCSVFile("research.csv");
				panelGUI.setVisible(false);
				panelReport.setVisible(true);
				String report = Controller.getReport(data);
				textPane.setText(report);
				/*DefaultListModel demoList = new DefaultListModel();
				
					demoList.addElement(data[i][0]);
				System.out.println(demoList);
				JList<String> list = new JList<>(demoList);
				list.setBounds(10, 177, 97, -165);
				panelReport.add(list);*/
			}
		});
	}

	public boolean checkInput() {
		boolean check = false;
		switch(selectedSpecies) {
			case 0:
				JOptionPane.showMessageDialog(null, "To save the data entry, you should select the species of animal.");
				break;
			case 1:
			case 2:
			case 3:
				check = true;
				break;
		}
		return check;
	}
}
