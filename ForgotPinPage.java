
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ForgotPinPage extends JFrame{
    private String parts[];
    private JPanel panel;
    private JPanel label_panel;
    private JPanel buttonPanel;
    private JTextField email_field = new JTextField(10);
    private JPasswordField password_field = new JPasswordField(10);    //For the password to remain hidden
    private JButton EnterButton = new JButton("Enter");
    private JButton cancelButton = new JButton("Cancel");
    private JPanel imagePanel = new JPanel(new BorderLayout());
    private JLabel imageLabel;    
    public ForgotPinPage()
    {
        //Graphics
        ImageIcon bankImage = new ImageIcon("/home/rafail/Downloads/bank.jpg"); //Local Image
        Image image = (bankImage.getImage()).getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon bankImageFinal = new ImageIcon(image);
        imageLabel = new JLabel(bankImageFinal);

        panel = new JPanel();
        JFrame frame = new JFrame();
        label_panel = new JPanel(new GridLayout(2,2, 3,3));

        label_panel.add(new JLabel("Email:"));
        label_panel.add(email_field);
        label_panel.add(new JLabel("Password:"));
        label_panel.add(password_field); 
        panel.add(label_panel, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.add(EnterButton);
        buttonPanel.add(cancelButton);

        EnterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    FileReader fr = new FileReader("credentials.txt");  //File reader for the file
                    BufferedReader br = new BufferedReader(fr); //Buffered Reader
                    String line;    //This represents the line we read
                    int line_to_change = 0; //This represents the line we must change the PIN
                    int linecount = 0;  //This represents the count of the lines
                    boolean correct_email = false;  //If the email is correct it gets true
                    boolean correct_password = false;   //If the password is correct it gets true
                                                        //In order to proceed both MUST be true
                    try {
                        while ((line = br.readLine()) != null)  //Read the file line by line
                        {
                            parts = line.split(",");    //Split the file 
                            String email = parts[4].trim(); //Separate the email
                            String password_to_check = parts[6].trim(); //Separate the password
                            if (email_field.getText().equals(email))    //If the email inserted matches the email in the line
                            {
                                correct_email = true;   //it gets true
                            }
                            String passwordInserted = "";   //We should make the char[] password from the password field a String 
                            char[] passwd = password_field.getPassword();

                            for (int i=0; i<passwd.length; i++)
                            {
                                passwordInserted += passwd[i]; // ...
                            }
                            
                            if (check_password(passwordInserted, password_to_check) && (email_field.getText().equals(email)))   //Now they BOTH need to be true
                            {                   //Because if only the password matched we would break the loop for no reason
                                correct_password = true;    //I split them into two cases for different messages to appear
                                line_to_change = linecount; //THIS is the line we should change
                                break;  //Exit the loop
                            } 
                            linecount++;    //Add 1 for the line count
                        }
                        br.close(); //Close the BufferedReader()
                        
                        if (correct_email && correct_password)  //If both are true
                        {
                            
                            int new_PIN = newPinGenerator();    //Generates a new PIN

                            JOptionPane.showMessageDialog(frame, "You signed in!! Your new PIN is " + new_PIN);
                            linecount = 0;  //Reset the linecount
                            BufferedReader br2 = new BufferedReader(new FileReader("credentials.txt"));     //Create a new bufferedReader()
                            FileWriter fw = new FileWriter("temp.txt", false);  //A new FileWriter and BufferedWriter for a temporary file
                            //In which we will pass all the lines except the line we want to change, which we will pass last with its new PIN

                            try (BufferedWriter bw = new BufferedWriter(fw)) {
                                while((line = br2.readLine()) != null)  //Read the file
                                {
                                    if (linecount != line_to_change)    //If the line is not the one we want to change
                                    {
                                        bw.write(line + "\n");  //Add it to the file
                                    }
                                    linecount++;    //Add the linecount 1
                                }
                                //Lastly add our line with its new PIN. parts[] was not changed after we broke the loop.
                                bw.write(new_PIN + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + parts[7] + "," + parts[8]);
                            }
                            
                            br2.close();
                            File toDelete = new File("credentials.txt");    //Create a new file ToDelete and ToReplace
                            File toReplace = new File("temp.txt");  
                            toReplace.renameTo(toDelete);   //Rename the new temp.txt file into credentials.txt
                            toReplace.delete(); //Delete the old credentials.txt file
                        }
                        
                        else if(correct_email && !correct_password) //If the email is correct
                        {
                            JOptionPane.showMessageDialog(frame, "Wrong password!!");
                        }
                        else    //If everything is wrong
                        {
                            JOptionPane.showMessageDialog(frame, "Wrong credentials!!");    
                        }
                    } catch (IOException | NoSuchAlgorithmException e1) {
                        
                        e1.printStackTrace();
                    }
                    
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                
            }

        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();  //Dispose the page
                new EntryPage();    //Just go back
            }
        });
        
        panel.add(buttonPanel, BorderLayout.CENTER);

        imagePanel.add(imageLabel);
        panel.add(imagePanel, BorderLayout.SOUTH);
        this.setSize(400,500);
        this.setVisible(true);
        this.setTitle("Forgot Password");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public boolean check_password(String password_inserted, String password) throws NoSuchAlgorithmException
    {
        //As it was stated in the newAccount class. It hashes and salts the password inserted and compares it with the ones saved
        //If they are equal returns true 
        //Else it returns false

        String salted_password = password_inserted + "abcde";   
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(salted_password.getBytes());
        byte[] bytes = md.digest();
            
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        String final_password = sb.toString();
        
        if (final_password.equals(password))
            return true;
        return false;
    }

    public int newPinGenerator() {
        //Generates a PIN as stated in newAccountPage()
        Random random = new Random();
        return random.nextInt(9000) + 1000;

    }
}
