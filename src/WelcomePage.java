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

public class WelcomePage extends JFrame{
    private JPanel panel = new JPanel();
    private JPanel imagePanel = new JPanel();
    private JLabel imageLabel;
    private JPanel labelPanel;
    private JLabel wLabel;
    private JLabel pinLabel;
    private JButton proceedButton = new JButton("Proceed"); 
    private JButton leaveButton = new JButton("Exit");

    public WelcomePage(String name, String last_name, int PIN, String gender, float BankBalance, String id)
    {
        //Graphics
        ImageIcon bankImage = new ImageIcon("icon/bank.jpg"); //Use of a local icon representing a bank
        Image image = (bankImage.getImage()).getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon bankImageFinal = new ImageIcon(image);

        labelPanel = new JPanel(new GridLayout(4,4,2,2));
        wLabel = new JLabel("Welcome to our services");
        pinLabel = new JLabel("Your PIN is " + PIN);    //Informing the user of his generated PIN after his register

        wLabel.setFont(new Font("Ariel", Font.ITALIC, 25));
        wLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pinLabel.setFont(new Font("Ariel", Font.BOLD, 30));
        pinLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.setLayout(new BorderLayout());
        labelPanel.add(wLabel);
        labelPanel.add(pinLabel);
        panel.add(labelPanel, BorderLayout.NORTH);

        imageLabel = new JLabel(bankImageFinal);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel);
        panel.add(imagePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2,2,5,5));
        buttonPanel.add(proceedButton);
        buttonPanel.add(leaveButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();  //Close the window
                new ActionPage(name,last_name,gender,BankBalance,id);   //New page for the user to proceed into actions like deposits and withdraws
            }
            
        });
        leaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(1); //Button for the user to exit the program
            }
        });

        this.setSize(500,600);
        this.setTitle("Welcome");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
    }
}
