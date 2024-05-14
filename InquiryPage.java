import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InquiryPage extends JFrame{

    private JPanel label_panel = new JPanel(new GridLayout(3,3,5,5)); 
    private JLabel balance_label = new JLabel();
    private JLabel money = new JLabel();
    private JPanel buttonPanel;
    private JButton exit_button = new JButton("Exit");
    private JButton back_button = new JButton("Back");

    public InquiryPage(String name, String last_name, String gender, float Balance, String id)
    {
        //Graphics
        balance_label.setText("Your account balance is:");
        balance_label.setFont(new Font("Sans Serif", Font.ITALIC, 17));
        balance_label.setHorizontalAlignment(SwingConstants.CENTER);

        money.setText(Balance + "$");
        money.setFont(new Font("Sans Serif", Font.BOLD, 20));
        money.setHorizontalAlignment(SwingConstants.CENTER);
        label_panel.add(balance_label);
        label_panel.add(money);

        JPanel panel = new JPanel(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.add(exit_button);
        buttonPanel.add(back_button);
        panel.add(label_panel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        exit_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.exit(1); //Exit the program
            }
        });

        back_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                dispose();    //Close the window
                new ActionPage(name, last_name, gender, Balance, id);   //Go back into the ActionPage
            }
        });

        this.setContentPane(panel);

        this.setVisible(true);
        this.setSize(400,150);
        this.setTitle("Inquiry");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }
}
