import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ActionPage extends JFrame{
    private JPanel panel = new JPanel();
    private JButton balance_inquiry_button = new JButton("Balance Inquiry");
    private JButton deposit_button = new JButton("Deposit");
    private JButton withdraw_button = new JButton("Withdraw");
    private JButton logout_button = new JButton("Logout");
    private JButton exit_button = new JButton("Exit");
    private JLabel nameLabel; 
    private JLabel imageLabel;

    public ActionPage(String name, String last_name, String gender, float balance, String id)
    {
        //Graphics
        JPanel labelPanel = new JPanel(new GridLayout(4,4,3,3));
        JLabel wLabel = new JLabel("Welcome back");

        //Insert an Image Locally
        ImageIcon bankImage = new ImageIcon("/home/rafail/Downloads/bank.jpg");
        Image image = (bankImage.getImage()).getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon bankImageFinal = new ImageIcon(image);
        imageLabel = new JLabel(bankImageFinal);

        wLabel.setFont(new Font("Sans Serif", Font.ITALIC, 22));
        wLabel.setHorizontalAlignment(SwingConstants.CENTER);

        
        if (gender.equals("Male")){
            nameLabel = new JLabel("MR " + last_name.toUpperCase() + " " + name.toUpperCase()); //Mr if he is a male
        }
        else if (gender.equals("Female"))
        {
            nameLabel = new JLabel("MS " + last_name.toUpperCase() + " " + name.toUpperCase()); //Ms if she is a female
        }
        else
        {
            nameLabel = new JLabel(last_name.toUpperCase() + " " + name.toUpperCase());
        }
        nameLabel.setFont(new Font("Ariel", Font.BOLD, 23));

        labelPanel.add(wLabel);
        labelPanel.add(nameLabel);

        panel.add(labelPanel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new GridLayout(5,5,3,3));


        JPanel imagePanel = new JPanel(new GridLayout(1,1,3,3));
        imagePanel.add(imageLabel);
        panel.add(imagePanel, BorderLayout.EAST);

        buttonPanel.add(deposit_button);
        buttonPanel.add(withdraw_button);
        buttonPanel.add(balance_inquiry_button);
        buttonPanel.add(logout_button);
        buttonPanel.add(exit_button);
        panel.add(buttonPanel, BorderLayout.WEST);

        exit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(1); //Exit the program
            }
        });

        //In the ActionListeners when i create a new DepositPage, WithDrawPage and InquiryPage
        //I pass all the arguments into their constuctor because i want the user to be able to go back
        //After his action
        
        deposit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new DepositPage(name, last_name, gender, balance,id);   //Enter the deposit page
            }
        });

        withdraw_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new WithdrawPage(name, last_name, gender, balance, id); //Enter the withdraw page
            }
        });

        balance_inquiry_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new InquiryPage(name, last_name, gender, balance, id);  //Enter the inquiry page
            }
        });

        logout_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new EntryPage();
            }
        });

        this.setContentPane(panel);

        this.setVisible(true);
        this.setSize(400,350);
        this.setTitle("MyBank Account");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }
}
