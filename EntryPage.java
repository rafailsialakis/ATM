
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class EntryPage extends JFrame{
	//Count of the attemps to log in
	private static int count = 3;

	private JPanel p1 = new JPanel();
	//Entry Label
	private JLabel label_welcome = new JLabel("Welcome to MyBank ATM. Please enter your PIN");

	//Creating the buttons of the ATM
	private JButton zeroButton = new JButton("0");
	private JButton oneButton = new JButton("1");
	private JButton twoButton = new JButton("2");
	private JButton threeButton = new JButton("3");
	private JButton fourButton = new JButton("4");
	private JButton fiveButton = new JButton("5");
	private JButton sixButton = new JButton("6");
	private JButton sevenButton = new JButton("7");
	private JButton eightButton = new JButton("8");
	private JButton nineButton = new JButton("9");
	private JButton cancelButton = new JButton("X");
	private JButton proceedButton = new JButton("✓");

	//Additional button functionalities
	private JButton forgotPINButton = new JButton("Forgot your PIN?");
	private JButton createAccountButton = new JButton("Create an Account");
	private JTextArea PINfield = new JTextArea(5,5);

	
	public EntryPage() {

		//Just the design of the panel
		p1.setLayout(new BorderLayout());
	
		label_welcome.setFont(new Font("Arial", Font.BOLD, 20));
        label_welcome.setHorizontalAlignment(SwingConstants.CENTER);

        p1.add(label_welcome, BorderLayout.NORTH);
		
        JPanel numbersPanel = new JPanel(new GridLayout(4, 4, 6, 6));
        JFrame frame = new JFrame();

		oneButton.setFont(new Font("Ariel", Font.BOLD, 20));
		twoButton.setFont(new Font("Ariel", Font.BOLD, 20));
		threeButton.setFont(new Font("Ariel", Font.BOLD, 20));
		fourButton.setFont(new Font("Ariel", Font.BOLD, 20));
		fiveButton.setFont(new Font("Ariel", Font.BOLD, 20));
		sixButton.setFont(new Font("Ariel", Font.BOLD, 20));
		sevenButton.setFont(new Font("Ariel", Font.BOLD, 20));
		eightButton.setFont(new Font("Ariel", Font.BOLD, 20));
		nineButton.setFont(new Font("Ariel", Font.BOLD, 20));
		zeroButton.setFont(new Font("Ariel", Font.BOLD, 20));
		cancelButton.setFont(new Font("Ariel", Font.BOLD, 20));
		proceedButton.setFont(new Font("Ariel", Font.BOLD, 30));

		PINfield.setFont(new Font("Ariel", Font.BOLD, 20));
		PINfield.setEditable(false);  //The PINfield should be editable only via buttons

		numbersPanel.add(oneButton);
        numbersPanel.add(twoButton);
        numbersPanel.add(threeButton);
        numbersPanel.add(fourButton);
        numbersPanel.add(fiveButton);
        numbersPanel.add(sixButton);
        numbersPanel.add(sevenButton);
        numbersPanel.add(eightButton);
        numbersPanel.add(nineButton);
		numbersPanel.add(cancelButton);
		numbersPanel.add(zeroButton);
		numbersPanel.add(proceedButton);

		oneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (PINfield.getText().length() < 4)  //Make it impossible to enter more than 4 numbers in the PINfield
					PINfield.append("1");
			}
		});

		twoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (PINfield.getText().length() < 4)
					PINfield.append("2");
			}
		});

		threeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (PINfield.getText().length() < 4)
					PINfield.append("3");
			}
		});

		fourButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (PINfield.getText().length() < 4)
					PINfield.append("4");
			}
		});

		fiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (PINfield.getText().length() < 4)
					PINfield.append("5");
			}
		});

		sixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (PINfield.getText().length() < 4)
					PINfield.append("6");
			}
		});

		sevenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (PINfield.getText().length() < 4)
					PINfield.append("7");
			}
		});

		eightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (PINfield.getText().length() < 4)
					PINfield.append("8");
			}
		});

		nineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (PINfield.getText().length() < 4)
					PINfield.append("9");
			}
		});

		zeroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (PINfield.getText().length() < 4)
					PINfield.append("0");
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				PINfield.setText("");  //Clear the PINField
			}
		});

		proceedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (PINfield.getText().length() == 4)  //Cannot proceed if the pin is less than 4 numbers
				{
					count--;
					try {
						String line;
						FileReader fr = new FileReader("credentials.txt");
						try (BufferedReader br = new BufferedReader(fr)) {
							boolean enter = false;
							while ((line = br.readLine()) != null)	//Read the file line by line using a BufferedReader()
							{
								String[] parts = line.split(",");	//Split the parts of the file separated via comma ","
								String PIN = parts[0].trim();	//Take the PIN that interests us in order to log in

								if (PINfield.getText().equals(PIN))	//Check if the PIN entered exists in the file
								{
									enter = true;	//the enter flag becomes true, the user can proceed
									count = 3;	//The count resets to 3 
									dispose();	//This window closes
									new ActionPage(parts[1].trim(), parts[2].trim(), parts[5].trim(), Float.parseFloat(parts[7].trim()), parts[8]);	//Create an ActionPage passing the parameters of the user that logged in with the constructor.
								}
							}
							if (enter == false)	//If the PIN doesn't exist in the file
							{
								if (count == 1)
									JOptionPane.showMessageDialog(frame, "You have " + count + " attempt left");
								else
									JOptionPane.showMessageDialog(frame, "You have " + count + " attempts left");
								PINfield.setText("");
								if (count == 0)	//If the user runs out of tries:
								{
									System.exit(1);	//The program exits
								}
							}
						} catch (HeadlessException e1) {
							//Exception handling
							e1.printStackTrace();
						}

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		forgotPINButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				dispose();	//The window closes
				new ForgotPinPage();	//Create a new window for the users that forgot the PIN
			}
		});

		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				dispose();	//The window closes
				new newAccountPage();	//Creates a new window for the users that want to create a new Account/Register
				
			}
		});
		
		this.setSize(500,600);
		this.setVisible(true);
		this.setTitle("MyBank ATM");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(p1);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		p1.add(numbersPanel, BorderLayout.CENTER);

		JPanel textPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		textPanel.add(PINfield);

		p1.add(textPanel, BorderLayout.SOUTH);

		JPanel endPanel = new JPanel(new GridLayout(2, 2, 5, 5));

		endPanel.add(forgotPINButton);
		endPanel.add(createAccountButton);
		textPanel.add(endPanel, BorderLayout.SOUTH);
	}
		
	
}
