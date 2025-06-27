import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExampleGUI {
    public static void main(String[] args) {
        new GUIComponents();
    }
}
    class GUIComponents extends JFrame {
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
            setSize(800, 90);
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

            setVisible(true);
        }
}
