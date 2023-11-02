import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Donor {
    private String name;
    private String bloodGroup;
    private int age;

    public Donor(String name, String bloodGroup, int age) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public int getAge() {
        return age;
    }
}

class BloodBankManagementSystem {
    private ArrayList<Donor> donors;

    public BloodBankManagementSystem() {
        donors = new ArrayList<>();
    }

    public void addDonor(String name, String bloodGroup, int age) {
        Donor donor = new Donor(name, bloodGroup, age);
        donors.add(donor);
        JOptionPane.showMessageDialog(null, "Donor added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public String searchDonor(String bloodGroup) {
        ArrayList<Donor> matchingDonors = new ArrayList<>();
        for (Donor donor : donors) {
            if (donor.getBloodGroup().equalsIgnoreCase(bloodGroup)) {
                matchingDonors.add(donor);
            }
        }

        if (!matchingDonors.isEmpty()) {
            StringBuilder result = new StringBuilder("Matching donors:\n");
            for (Donor donor : matchingDonors) {
                result.append("Name: ").append(donor.getName()).append(", Blood Group: ").append(donor.getBloodGroup()).append(", Age: ").append(donor.getAge()).append("\n");
            }
            return result.toString();
        } else {
            return "No donors found with the given blood group.";
        }
    }
}

class BloodBankGUI {
    private BloodBankManagementSystem bloodBank;
    private JFrame frame;
    private JTextField nameField;
    private JTextField bloodGroupField;
    private JTextField ageField;
    private JTextField searchField;
    private JTextArea displayArea;

    public BloodBankGUI() {
        bloodBank = new BloodBankManagementSystem();
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Blood Bank Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 80, 25);
        frame.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 30, 200, 25);
        frame.add(nameField);

        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        bloodGroupLabel.setBounds(30, 70, 80, 25);
        frame.add(bloodGroupLabel);

        bloodGroupField = new JTextField();
        bloodGroupField.setBounds(120, 70, 200, 25);
        frame.add(bloodGroupField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(30, 110, 80, 25);
        frame.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(120, 110, 200, 25);
        frame.add(ageField);

        JButton addButton = new JButton("Add Donor");
        addButton.setBounds(120, 150, 120, 25);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDonor();
            }
        });
        frame.add(addButton);

        JLabel searchLabel = new JLabel("Search by Blood Group:");
        searchLabel.setBounds(30, 190, 150, 25);
        frame.add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(180, 190, 140, 25);
        frame.add(searchField);

        JButton searchButton = new JButton("Search Donor");
        searchButton.setBounds(120, 230, 120, 25);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchDonor();
            }
        });
        frame.add(searchButton);

        displayArea = new JTextArea();
        displayArea.setBounds(30, 270, 330, 80);
        displayArea.setEditable(false);
        frame.add(displayArea);

        frame.setVisible(true);
    }

    private void addDonor() {
        String name = nameField.getText();
        String bloodGroup = bloodGroupField.getText();
        int age = Integer.parseInt(ageField.getText());

        if (!name.isEmpty() && !bloodGroup.isEmpty() && age > 0) {
            bloodBank.addDonor(name, bloodGroup, age);
            nameField.setText("");
            bloodGroupField.setText("");
            ageField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input. Please fill all fields correctly.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchDonor() {
        String bloodGroup = searchField.getText();
        if (!bloodGroup.isEmpty()) {
            String result = bloodBank.searchDonor(bloodGroup);
            displayArea.setText(result);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a blood group to search for donors.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BloodBankGUI();
            }
        });
    }
}
