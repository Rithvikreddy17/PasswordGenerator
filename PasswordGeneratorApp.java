import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PasswordGeneratorApp extends JFrame {

    private JTextField passwordField;
    private JCheckBox uppercaseCheckbox, lowercaseCheckbox, numbersCheckbox, symbolsCheckbox;
    private JButton generateButton;

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+";

    public PasswordGeneratorApp() {
        setTitle("Password Generator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        passwordField = new JTextField();
        passwordField.setEditable(false);

        uppercaseCheckbox = new JCheckBox("Uppercase");
        lowercaseCheckbox = new JCheckBox("Lowercase");
        numbersCheckbox = new JCheckBox("Numbers");
        symbolsCheckbox = new JCheckBox("Symbols");

        generateButton = new JButton("Generate Password");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(passwordField);
        panel.add(uppercaseCheckbox);
        panel.add(lowercaseCheckbox);
        panel.add(numbersCheckbox);
        panel.add(symbolsCheckbox);
        panel.add(generateButton);

        add(panel);

        setVisible(true);
    }

    private void generatePassword() {
        StringBuilder password = new StringBuilder();

        String charset = "";
        if (uppercaseCheckbox.isSelected()) {
            charset += UPPERCASE;
        }
        if (lowercaseCheckbox.isSelected()) {
            charset += LOWERCASE;
        }
        if (numbersCheckbox.isSelected()) {
            charset += NUMBERS;
        }
        if (symbolsCheckbox.isSelected()) {
            charset += SYMBOLS;
        }

        if (charset.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select at least one option for password generation.");
            return;
        }

        int passwordLength = 12; // You can modify the length as needed

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(charset.length());
            password.append(charset.charAt(randomIndex));
        }

        passwordField.setText(password.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordGeneratorApp();
            }
        });
    }
}

