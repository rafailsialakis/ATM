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

public class DepositPage extends JFrame{

    private String parts[];
	private float new_balance;
    private JPanel p1 = new JPanel();
	private JLabel label_welcome = new JLabel("Please enter the amount to deposit");

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

	private JButton exit_Button = new JButton("Exit");
	private JButton back_button = new JButton("Back");
	private JTextArea DepositArea = new JTextArea(5,5);

    public DepositPage(String name, String last_name, String gender, float Balance, String id)
    {
		//As we did on WithDrawPage
		new_balance = Balance;

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

		DepositArea.setFont(new Font("Ariel", Font.BOLD, 20));
		DepositArea.setEditable(false);

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
				if (DepositArea.getText().length() < 4)
					DepositArea.append("1");
			}
		});

		twoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (DepositArea.getText().length() < 4)
					DepositArea.append("2");
			}
		});

		threeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (DepositArea.getText().length() < 4)
					DepositArea.append("3");
			}
		});

		fourButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (DepositArea.getText().length() < 4)
					DepositArea.append("4");
			}
		});

		fiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (DepositArea.getText().length() < 4)
					DepositArea.append("5");
			}
		});

		sixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (DepositArea.getText().length() < 4)
					DepositArea.append("6");
			}
		});

		sevenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (DepositArea.getText().length() < 4)
					DepositArea.append("7");
			}
		});

		eightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (DepositArea.getText().length() < 4)
					DepositArea.append("8");
			}
		});

		nineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (DepositArea.getText().length() < 4)
					DepositArea.append("9");
			}
		});

		zeroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (DepositArea.getText().length() < 4)
					DepositArea.append("0");
			}
		});

        cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				DepositArea.setText("");
			}
		});

        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try 
                {
                    String line;
                    FileReader fr = new FileReader("credentials.txt");
                    BufferedReader br = new BufferedReader(fr);
                    int linecount = 0;
                    int line_that_interests_us = 0;

                    while ((line = br.readLine()) != null)
                    {
                        parts = line.split(",");	//Separate the line
                        if (parts[8].equals(id))	//Check if the id we passed from the constructor is equal to the id in the file
                        {
                            line_that_interests_us = linecount;
                            break;
                        }
                        linecount++;
                    }
                    br.close();
                    linecount = 0;
                    BufferedReader br2 = new BufferedReader(new FileReader("credentials.txt"));	//Same technique with two files
                    FileWriter fw = new FileWriter("temp.txt", false);
                    
                    try (BufferedWriter bw = new BufferedWriter(fw)) {
                        while((line = br2.readLine()) != null)
                        {
                            if (linecount != line_that_interests_us)
                            {
                                bw.write(line + "\n");	//write every line except the one that interests us
                            }
                            linecount++;
                        }
						new_balance = Float.parseFloat(parts[7])+Float.parseFloat(DepositArea.getText()); 
                        String new_balance_string = Float.toString(new_balance);
						
						bw.write(parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + new_balance_string + "," + id + "\n");
                        
                        br2.close();
                        File toDelete = new File("credentials.txt");
                        File toReplace = new File("temp.txt");
                        toReplace.renameTo(toDelete);
                        toReplace.delete();
                        JOptionPane.showMessageDialog(frame, "Your " + Integer.parseInt(DepositArea.getText()) + "$ has been deposited");
						DepositArea.setText("");
                    }
                } 
                catch (FileNotFoundException e1) 
                {
                    e1.printStackTrace();
                } 
                catch (IOException e1) 
                {
                    e1.printStackTrace();
                }
                
            }
        });

		exit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(1);
			}
		});

        back_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new ActionPage(name, last_name, gender, new_balance,id);	//Go back with the NEW_BALANCE
				//Super Important!!!
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
		textPanel.add(DepositArea);

		p1.add(textPanel, BorderLayout.SOUTH);

		JPanel endPanel = new JPanel(new GridLayout(2, 2, 5, 5));

		endPanel.add(back_button);
		endPanel.add(exit_Button);
		textPanel.add(endPanel, BorderLayout.SOUTH);
    }
    
}
