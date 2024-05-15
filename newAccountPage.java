

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class newAccountPage extends JFrame{
    private String first_name;
    private String last_name;
    private String id_number;
    private String email;
    private String gender;
    private char[] passwd;
    private String password = "";
    private String final_password;
    private float BankBalance;
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JTextField first_name_field = new JTextField(15); // Initialize text fields
    private JTextField last_name_field = new JTextField(15);
    private JTextField id_number_field = new JTextField(10);
    private JTextField email_field = new JTextField(10);
    private JPasswordField password_field = new JPasswordField(15);
    private JButton registerButton = new JButton("Register");
    private JButton cancelButton = new JButton("Cancel");

    String genders[] = {"Male", "Female", "Prefer not to say"}; //Array for the JComboBox
    JComboBox<String> gender_field = new JComboBox<>(genders);

    public newAccountPage() {
        panel.setLayout(new BorderLayout());

        //Just Graphics
        JPanel information_panel = new JPanel(new GridLayout(7, 7, 6, 6));
        
        information_panel.add(new JLabel("First Name:"));
        information_panel.add(first_name_field);
        information_panel.add(new JLabel("Last Name:"));
        information_panel.add(last_name_field);
        information_panel.add(new JLabel("ID Number:"));
        information_panel.add(id_number_field);
        information_panel.add(new JLabel("Gender:"));
        information_panel.add(gender_field);
        information_panel.add(new JLabel("Email:"));
        information_panel.add(email_field);
        information_panel.add(new JLabel("Password:"));
        information_panel.add(password_field);        

        JPanel buttonPanel = new JPanel(new GridLayout(2,2,3,3));
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                email = email_field.getText();  //Get the value of the email field
                passwd = password_field.getPassword();  //Get the value of the password field, it is char[] and not String
                for(int i=0; i<passwd.length; i++)
                {
                    password += passwd[i];  //Create a String with the passwd (could use String class).
                }
                //Check if any field is empty
                if (first_name_field.getText().equals("") || last_name_field.getText().equals("") || id_number_field.getText().equals("") || email_field.getText().equals("") || password.equals(""))
                {
                    JOptionPane.showMessageDialog(frame, "Please fill out all the forms");  //Warning message, cannot proceed
                }
                else if (!email_checker(email)) //Check if the email is an email
                {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid email"); //Warning message, cannot proceed
                }
                else if(passwd.length < 8)
                {
                    JOptionPane.showMessageDialog(frame, "Please enter a password that contains at least 8 characters");    //Warning message, cannot proceed
                }   
                else if(!password_checker(password))
                {
                    //Warning message, cannot proceed
                    JOptionPane.showMessageDialog(frame, "Please enter a password that contains at least 1 letter, 1 number and 1 special character");
                }
                else
                {
                    //Take all the user inputs into variables
                    first_name = first_name_field.getText();
                    last_name = last_name_field.getText();
                    id_number = id_number_field.getText();
                    gender = (String)gender_field.getSelectedItem();    //Cast needed for the JComboBox
                    
                    int id = 0; //Start enumerating id's for every user who registers
                    BankBalance = 0;
                    
                    try {final_password = passwordHasherandSalter(password);} //The password goes into a function that hashes and salts the password so that whoever gets access in the file cannot see the passwords 
                    
                    catch (NoSuchAlgorithmException e1) //Exception handling for the function
                    {
                        e1.printStackTrace();
                    }

                    int PIN = PINGenerator();   //Pin Generator function
                    
                    try
                    {
                        FileWriter writer = new FileWriter("credentials.txt", true);    //Create a file Writer
                        try {
                            BufferedReader br = new BufferedReader(new FileReader("credentials.txt"));  //read the file
                            while ((br.readLine()) != null) //while the filepointer is not null or count the users
                            {
                                id++;   //add 1 for every user
                            }
                            br.close();     //close the BufferedReader
                        } catch (FileNotFoundException e1) {  //Exception Handling
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        try (BufferedWriter out = new BufferedWriter(writer)) {         //Buffered Writer
                            //Add everything the user inputs plus his identical ID
                            out.write(PIN + "," + first_name + "," + last_name + "," + id_number + "," + email + "," + gender + "," + final_password + "," + BankBalance + "," + id + "\n");
                        }
                    } catch (IOException e1) {

                        e1.printStackTrace();
                    }
                    dispose();  //Close the window
                    new WelcomePage(first_name, last_name, PIN, gender, BankBalance, Integer.toString(id)); //New page to welcome the user who registered

                }

            }
        });

        id_number_field.addKeyListener(new KeyAdapter() { // Add a keyListener to ID number field to add ONLY numbers
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if (Character.isDigit(ch) || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    // If the key pressed is a digit or the backspace key
                    if (id_number_field.getText().length() < 8 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        // Allow input if there are less than 8 digits or if it's a backspace
                        id_number_field.setEditable(true);
                    } else {
                        // Otherwise, restrict input
                        id_number_field.setEditable(false);
                    }
                } else {
                    // Restrict input if the key pressed is not a digit or backspace
                    id_number_field.setEditable(false);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new EntryPage();    //Go back into the previous page
            }
        });

        panel.add(information_panel, BorderLayout.CENTER);
        this.setContentPane(panel);
        this.setSize(500,600);
        this.setVisible(true);
        this.setTitle("User Registration");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public int PINGenerator()
    {
        Random random = new Random();
        int PIN = random.nextInt(9000) + 1000;  //Take a random number from 0 to 8999 and add 1000, so the PRNG generates numbers from 1000 to 9999
        return PIN; //returns the PIN generated
    }

    public boolean email_checker(String email)
    {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"; //Matches an email like example@example.example
        return email.matches(emailRegex);
    }

    public boolean password_checker(String password)
    {
        return password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-+=]).+$");  //Matches passwords with letters AND numbers AND special characters
    }

    public String passwordHasherandSalter(String password) throws NoSuchAlgorithmException
    {
        String salted_password = password + "abcde";    //Salts the password
        MessageDigest md = MessageDigest.getInstance("SHA-256");    //Creates a new digest using SHA-256

        md.update(salted_password.getBytes());  //Update the digest with the bytes of the salted password
        byte[] bytes = md.digest();
            
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));    
        }
        final_password = sb.toString(); //Builds a String and creates the final password after it's hashed
        
        return final_password;
    }
}
        
 

