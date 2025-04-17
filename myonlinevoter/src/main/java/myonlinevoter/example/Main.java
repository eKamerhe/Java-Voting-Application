package myonlinevoter.example;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    //Vote counters
    static int g1 = 0; // Millennials
    static int g2 = 0; // Gen Z
    static int g3 = 0; // Gen Alpha

    public static void main(String[] args) {
        JFrame frame = new JFrame("Voter Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel headingLabel = new JLabel("VOTING FORM", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headingLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(headingLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 20);
        Font font = new Font("Arial", Font.PLAIN, 20);

        //Name label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        formPanel.add(nameLabel, gbc);

        //Name field
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        //Phone label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(font);
        phoneLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        formPanel.add(phoneLabel, gbc);

        //Phone field
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField phoneField = new JTextField(20);
        formPanel.add(phoneField, gbc);

        //ID label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(font);
        idLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        formPanel.add(idLabel, gbc);

        //ID field
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField idField = new JTextField(20);
        formPanel.add(idField, gbc);

        //Select Party
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel partyLabel = new JLabel("Select Party:");
        partyLabel.setFont(font);
        partyLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        formPanel.add(partyLabel, gbc);

        // Radio buttons
        gbc.gridy = 4;
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JRadioButton radioM = new JRadioButton("Millennials");
        JRadioButton radioZ = new JRadioButton("Gen Z");
        JRadioButton radioA = new JRadioButton("Gen Alpha");

        radioM.setFont(font);
        radioZ.setFont(font);
        radioA.setFont(font);

        ButtonGroup partyGroup = new ButtonGroup();
        partyGroup.add(radioM);
        partyGroup.add(radioZ);
        partyGroup.add(radioA);

        radioM.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioZ.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioA.setAlignmentX(Component.CENTER_ALIGNMENT);

        radioPanel.add(radioM);
        radioPanel.add(radioZ);
        radioPanel.add(radioA);
        formPanel.add(radioPanel, gbc);

        //Adding form panel to main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);

        //Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        JButton btnSubmit = new JButton("Submit Vote");
        JButton btnResults = new JButton("Check Results");
        btnSubmit.setFont(font);
        btnResults.setFont(font);
        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnResults);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Tracking votes by ID
        Set<String> votedIDs = new HashSet<>();

        //btnSubmit Logic
        btnSubmit.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String id = idField.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || id.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled.");
                return;
            }

            if (!name.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(frame, "Name must contain only letters.");
                return;
            }

            if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(frame, "Phone must be 10 digits.");
                return;
            }

            if (!id.matches("\\d{5}")) {
                JOptionPane.showMessageDialog(frame, "ID must be 5 digits.");
                return;
            }

            if (votedIDs.contains(id)) {
                JOptionPane.showMessageDialog(frame, "This ID has already voted.");
                return;
            }

            //Selection
            if (!radioM.isSelected() && !radioZ.isSelected() && !radioA.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Select a Party");
                return;
            }

            if (radioM.isSelected()) g1++;
            else if (radioZ.isSelected()) g2++;
            else g3++;

            votedIDs.add(id);

            JOptionPane.showMessageDialog(frame, "Vote submitted successfully!");

            //Clear form
            nameField.setText("");
            phoneField.setText("");
            idField.setText("");
            partyGroup.clearSelection();
        });

        //btnResults logic
        btnResults.addActionListener(e -> {
            StringBuilder result = new StringBuilder();
            result.append("Vote Counts:\n");
            result.append("Millennials: ").append(g1).append(" votes\n");
            result.append("Gen Z: ").append(g2).append(" votes\n");
            result.append("Gen Alpha: ").append(g3).append(" votes\n\n");

            int max = Math.max(g1, Math.max(g2, g3));
            Set<String> leadingParties = new HashSet<>();

            if (g1 == max) leadingParties.add("Millennials");
            if (g2 == max) leadingParties.add("Gen Z");
            if (g3 == max) leadingParties.add("Gen Alpha");

            if (leadingParties.size() == 3) {
                result.append("All parties are tied!");
            } else if (leadingParties.size() > 1) {
                result.append("There is a tie between: ");
                result.append(String.join(" and ", leadingParties));
            } else {
                result.append("Leading Party: ").append(leadingParties.iterator().next())
                        .append(" with ").append(max).append(" vote(s)");
            }

            JOptionPane.showMessageDialog(frame, result.toString(), "Vote Results", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
