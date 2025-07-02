import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExampleGUI {
    public static void main(String[] args) {
        new GUIComponents();
    }
}
class GUIComponents extends JFrame implements ActionListener, FocusListener {
    // JButton - any J comes from swing
    // anything without awt
    JButton okButton, cancelButton;
    JLabel nameLabel;
    JTextField nameField;
    JCheckBox boldCheck, italicCheck;
    JRadioButton redRadio, yellowRadio;
    JComboBox<String> classCombo;

    public GUIComponents() {
        setTitle("Show GUI");
        setSize(900, 90);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        // using default JFrame panel
        add(okButton);
        add(cancelButton);

        nameLabel = new JLabel("Enter Your Name:");
        nameField = new JTextField("Type Name Here:", 12);
        add(nameLabel);
        add(nameField);

        boldCheck = new JCheckBox("Bold", true); // if checked initially "true"
        italicCheck = new JCheckBox("Italic", true);
        add(boldCheck);
        add(italicCheck);
        
        redRadio = new JRadioButton("Red");
        yellowRadio = new JRadioButton("Yellow");
        ButtonGroup colorGroup = new ButtonGroup(); // this group makes each check exclusive
        colorGroup.add(redRadio);
        colorGroup.add(yellowRadio);
        add(redRadio);
        add(yellowRadio);

        String [] classOptions = {"Freshman", "Sophamore", "Junior", "Senior"};
        classCombo = new JComboBox<>(classOptions);
        classCombo.setSelectedItem("Freshman");
        add(classCombo);

        // Add action listener
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // Add focus listener

        setVisible(true);
    }
    @Override 
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == okButton) {
            JOptionPane.showMessageDialog(this, "Welcome to GUI components, " + nameField.getText());
        }
        else if(e.getSource() == cancelButton) {
            JOptionPane.showMessageDialog(this, "Thanks, bye, " + nameField.getText());
            nameField.setText("");
        }
    }        
    @Override
    public void focusGained(FocusEvent e) {
        nameField.setText("");
    }
    @Override
    public void focusLost(FocusEvent e) {
        
    }
}
