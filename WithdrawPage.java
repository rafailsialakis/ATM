import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class WithdrawPage extends JFrame{
	private float new_balance;	//The new balance after the withdraw
    private String parts[];	//Parts of the line which we read
	private JPanel p1 = new JPanel();
	private JLabel label_welcome = new JLabel("Please enter the amount to withdraw");

	//Buttons
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

	private JButton exit_button = new JButton("Exit");
	private JButton back_button = new JButton("Back");
	private JTextArea withdrawArea = new JTextArea(5,5);

    public WithdrawPage(String name, String last_name, String gender, float Balance, String id)
    {
		new_balance = Balance;	//Give the Balance amount from the constructor to the new_balance
								//In case the user does not proceed into a withdraw
								//To return back with the amount we started UNCHANGED

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

		withdrawArea.setFont(new Font("Ariel", Font.BOLD, 20));
		withdrawArea.setEditable(false);

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
				if (withdrawArea.getText().length() < 4)
					withdrawArea.append("1");
			}
		});

		twoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (withdrawArea.getText().length() < 4)
					withdrawArea.append("2");
			}
		});

		threeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (withdrawArea.getText().length() < 4)
					withdrawArea.append("3");
			}
		});

		fourButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (withdrawArea.getText().length() < 4)
					withdrawArea.append("4");
			}
		});

		fiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (withdrawArea.getText().length() < 4)
					withdrawArea.append("5");
			}
		});

		sixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (withdrawArea.getText().length() < 4)
					withdrawArea.append("6");
			}
		});

		sevenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (withdrawArea.getText().length() < 4)
					withdrawArea.append("7");
			}
		});

		eightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (withdrawArea.getText().length() < 4)
					withdrawArea.append("8");
			}
		});

		nineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (withdrawArea.getText().length() < 4)
					withdrawArea.append("9");
			}
		});

		zeroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (withdrawArea.getText().length() < 4)
					withdrawArea.append("0");
			}
		});

        cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				withdrawArea.setText("");
			}
		});

        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (Integer.parseInt(withdrawArea.getText()) <= 500)	//The user can withdraw as much as 500$ a time
                {
                    try 
                    {
                        String line;	//The line we are going to read
                        FileReader fr = new FileReader("credentials.txt");	//FileReader
                        BufferedReader br = new BufferedReader(fr);	//BufferedReader
                        int linecount = 0;	//Count the lines of the file
                        int line_that_interests_us = 0;	//This is the line that we will change

                        while ((line = br.readLine()) != null)	//Read the file line by line
                        {
                            parts = line.split(",");	//Split the line into parts separated by comma ","
                            if (parts[8].equals(id))	//Check if the ID of the user who entered the WithdrawPage is the same with the one in the file
                            {
                                line_that_interests_us = linecount;	//Then this is the line that interests us
                                break;	//Exit the loop in order to keep the parts[] array unchanged
                            }
                            linecount++;	//Add 1 every time in the linecount
                        }
                        br.close();	//Close the BufferedReader
                        linecount = 0;	//Reset the lineCount
                        BufferedReader br2 = new BufferedReader(new FileReader("credentials.txt"));		//Create a new BufferedReader
                        FileWriter fw = new FileWriter("temp.txt", false);	//Create a new FileWriter for a temp.txt file as we did in the ForgotPinPage()
                        if (Float.parseFloat(parts[7]) < Integer.parseInt(withdrawArea.getText()))	//If the amount we entered is more than the amount available in our bank account
                        {
                            JOptionPane.showMessageDialog(frame, "You don't have enough money in your bank account");
							withdrawArea.setText("");	//Empty the withDrawArea
                        } 
                        else
                        {
                            try (BufferedWriter bw = new BufferedWriter(fw)) {	//Read the file line by line
                                while((line = br2.readLine()) != null)
                                {
                                    if (linecount != line_that_interests_us)	//Don't write the line that interests us, write it alone in the end
                                    {
                                        bw.write(line + "\n");
                                    }
                                    linecount++;	//Add 1 every time to the line count
                                }
								new_balance = Float.parseFloat(parts[7]) - Float.parseFloat(withdrawArea.getText());	//The new balance is the amount we had in our account minus the amount we inserted
								String new_balance_string = Float.toString(new_balance);	//We convert into a String to write in the file (not necessary)
								//We write the line last in the file with the new balance
                                bw.write(parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + new_balance_string + "," + id + "\n");
                                
                                br2.close();	//Close the bufferedReader
                                File toDelete = new File("credentials.txt");	//Create a file toDelete and a toReplace
                                File toReplace = new File("temp.txt");
                                toReplace.renameTo(toDelete);	//Rename the temp file into credentials.txt
                                toReplace.delete();	//Delete the old credentials.txt
                                JOptionPane.showMessageDialog(frame, "Your " + Integer.parseInt(withdrawArea.getText()) + "$ has been withdrawn");	//Print a message to ensure the user that his amount has been withdrawn
								withdrawArea.setText("");	//Reset the textArea
                            }
                        }

                    } 
					//Exception Handling
                    catch (FileNotFoundException e1) 
                    {
                        e1.printStackTrace();
                    } 
                    catch (IOException e1) 
                    {
                        e1.printStackTrace();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "The limit to withdraw is 500$");	//Message if the user wants to withdraw more than 500$
					withdrawArea.setText("");	//Reset the textArea
                }
            }
        });

		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(1);	//Exit Button
			}
		});
        
        back_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new ActionPage(name, last_name, gender, new_balance,id);	//Go back with the NEW_BALANCE
				//If we don't when we return, the correct amount will be saved in the file but if we check the inquiry right after
				//It will not have been updated
            }
        });
		
		this.setSize(500,600);
		this.setVisible(true);
		this.setTitle("Deposit");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(p1);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		p1.add(numbersPanel, BorderLayout.CENTER);

		JPanel textPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		textPanel.add(withdrawArea);

		p1.add(textPanel, BorderLayout.SOUTH);

		JPanel endPanel = new JPanel(new GridLayout(2, 2, 5, 5));

		endPanel.add(back_button);
		endPanel.add(exit_button);
		textPanel.add(endPanel, BorderLayout.SOUTH);
    }
}
