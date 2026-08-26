import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class NOCApp implements ActionListener {
    private JFrame frame;

    private JComboBox<String> monthCombo;
    private JComboBox<String> typeCombo;
    private JComboBox<String> weightCombo;

    private JTextField txtSubsidy;
    private JTextField txtCitizenship;
    private JTextField txtLicense;
    private JTextField txtBookingId;
    private JTextField txtCylinderId;
    private JTextField txtPrice;
    private JTextField txtQuantity;
    private JTextField txtSearchId;

    private JTextArea outputArea;

    private JButton addDomesticButton;
    private JButton addCommercialButton;
    private JButton subsidyButton;
    private JButton discountButton;
    private JButton displayButton;
    private JButton identifyButton;
    private JButton clearButton;
    private JButton exportButton;
    private JButton loadButton;

    private ArrayList<LPGCylinder> cylinders;

    private String[] months =
            {
                    "Baishakh",
                    "Jestha",
                    "Ashadh",
                    "Shrawan",
                    "Bhadra",
                    "Ashwin",
                    "Kartik",
                    "Mangsir",
                    "Poush",
                    "Magh",
                    "Falgun",
                    "Chaitra"
            };

    private String[] weights =
            {
                    "6.0",
                    "12.5",
                    "14.2",
                    "19.0"
            };

    public NOCApp() {
        cylinders = new ArrayList<LPGCylinder>();

        frame = new JFrame("NOC LPG Cylinder Management");

        frame.setSize(950, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        createGUI();

        frame.setVisible(true);
    }

    public void createGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // input panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        inputPanel.setBorder(
                BorderFactory.createTitledBorder("Cylinder Details"));

        monthCombo = new JComboBox<String>(months);

        typeCombo = new JComboBox<String>(
                new String[]{"Domestic", "Commercial"});

        weightCombo = new JComboBox<String>(weights);

        txtSubsidy = new JTextField();
        txtCitizenship = new JTextField();
        txtLicense = new JTextField();
        txtBookingId = new JTextField();
        txtCylinderId = new JTextField();
        txtPrice = new JTextField();
        txtQuantity = new JTextField();
        txtSearchId = new JTextField();

        inputPanel.add(new JLabel("Month:"));
        inputPanel.add(monthCombo);

        inputPanel.add(new JLabel("Cylinder Type:"));
        inputPanel.add(typeCombo);

        inputPanel.add(new JLabel("Cylinder Weight (kg):"));
        inputPanel.add(weightCombo);

        inputPanel.add(new JLabel("Cylinder ID:"));
        inputPanel.add(txtCylinderId);

        inputPanel.add(new JLabel("Booking ID:"));
        inputPanel.add(txtBookingId);

        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(txtPrice);

        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(txtQuantity);

        inputPanel.add(new JLabel("Subsidy Amount:"));
        inputPanel.add(txtSubsidy);

        inputPanel.add(new JLabel("Citizenship Number:"));
        inputPanel.add(txtCitizenship);

        inputPanel.add(new JLabel("Business License Number:"));
        inputPanel.add(txtLicense);

        inputPanel.add(new JLabel("Search Cylinder ID:"));
        inputPanel.add(txtSearchId);


        // button panel
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        buttonPanel.setBorder(
                BorderFactory.createTitledBorder("Actions"));

        addDomesticButton =
                new JButton("Add Domestic Cylinder");

        addCommercialButton =
                new JButton("Add Commercial Cylinder");

        subsidyButton =
                new JButton("Calculate Price After Subsidy");

        discountButton =
                new JButton("Calculate Bulk Discount");

        displayButton =
                new JButton("Display All");

        identifyButton =
                new JButton("Identify Cylinder Type");

        clearButton =
                new JButton("Clear");

        exportButton =
                new JButton("Export to File");

        loadButton =
                new JButton("Load From File");


        buttonPanel.add(addDomesticButton);
        buttonPanel.add(addCommercialButton);
        buttonPanel.add(subsidyButton);
        buttonPanel.add(discountButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(identifyButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(loadButton);


        // output area
        outputArea = new JTextArea();

        outputArea.setEditable(false);

        outputArea.setLineWrap(true);

        outputArea.setWrapStyleWord(true);

        JScrollPane scrollPane =
                new JScrollPane(outputArea);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Output"));


        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        topPanel.add(inputPanel, BorderLayout.CENTER);

        topPanel.add(buttonPanel, BorderLayout.SOUTH);


        mainPanel.add(topPanel, BorderLayout.NORTH);

        mainPanel.add(scrollPane, BorderLayout.CENTER);


        frame.add(mainPanel);


        // adding action listeners
        addDomesticButton.addActionListener(this);

        addCommercialButton.addActionListener(this);

        subsidyButton.addActionListener(this);

        discountButton.addActionListener(this);

        displayButton.addActionListener(this);

        identifyButton.addActionListener(this);

        clearButton.addActionListener(this);

        exportButton.addActionListener(this);

        loadButton.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addDomesticButton) {
            addDomesticCylinder();
        } else if (e.getSource() == addCommercialButton) {
            addCommercialCylinder();
        } else if (e.getSource() == subsidyButton) {
            calculateSubsidy();
        } else if (e.getSource() == discountButton) {
            calculateDiscount();
        } else if (e.getSource() == displayButton) {
            displayAll();
        } else if (e.getSource() == identifyButton) {
            identifyCylinder();
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == exportButton) {
            exportFile();
        } else if (e.getSource() == loadButton) {
            loadFile();
        }
    }


    // add domestic cylinder
    public void addDomesticCylinder() {
        try {
            String cylinderId =
                    txtCylinderId.getText().trim();

            String bookingId =
                    txtBookingId.getText().trim();

            String month =
                    monthCombo.getSelectedItem().toString();

            String priceText =
                    txtPrice.getText().trim();

            String weightText =
                    weightCombo.getSelectedItem().toString();

            String subsidyText =
                    txtSubsidy.getText().trim();

            String citizenship =
                    txtCitizenship.getText().trim();


            if (cylinderId.isEmpty() ||
                    bookingId.isEmpty() ||
                    priceText.isEmpty() ||
                    subsidyText.isEmpty() ||
                    citizenship.isEmpty()) {
                throw new IllegalArgumentException(
                        "Please fill all required domestic fields.");
            }


            // check cylinder id
            if (!cylinderId.matches("NOC-[0-9]+")) {
                throw new IllegalArgumentException(
                        "Cylinder ID must be like NOC-001.");
            }


            // check duplicate ids
            if (isDuplicateCylinderId(cylinderId)) {
                throw new IllegalArgumentException(
                        "Cylinder ID already exists.");
            }


            if (isDuplicateBookingId(bookingId)) {
                throw new IllegalArgumentException(
                        "Booking ID already exists.");
            }


            // check citizenship
            if (!citizenship.matches("[0-9]{12}")) {
                throw new IllegalArgumentException(
                        "Citizenship number must contain exactly 12 digits.");
            }


            // check monthly quota
            if (checkDomesticQuota(citizenship, month)) {
                throw new IllegalArgumentException(
                        "Monthly quota exceeded. Maximum is 2 cylinders.");
            }


            double price =
                    Double.parseDouble(priceText);

            double weight =
                    Double.parseDouble(weightText);

            double subsidy =
                    Double.parseDouble(subsidyText);


            if (price <= 0 || weight <= 0) {
                throw new IllegalArgumentException(
                        "Price and weight must be greater than zero.");
            }


            if (subsidy < 0) {
                throw new IllegalArgumentException(
                        "Subsidy cannot be negative.");
            }


            if (subsidy > price) {
                throw new IllegalArgumentException(
                        "Subsidy cannot be greater than price.");
            }


            DomesticCylinder domestic =
                    new DomesticCylinder(
                            cylinderId,
                            bookingId,
                            month,
                            price,
                            weight,
                            subsidy,
                            citizenship);


            cylinders.add(domestic);


            outputArea.append(
                    "Domestic cylinder added successfully.\n");

            outputArea.append(
                    domestic.display() + "\n");


            JOptionPane.showMessageDialog(
                    frame,
                    "Domestic cylinder added successfully.");
        } catch (NumberFormatException ex) {
            showError("Please enter valid numbers.");
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }


    // add commercial cylinder
    public void addCommercialCylinder() {
        try {
            String cylinderId =
                    txtCylinderId.getText().trim();

            String bookingId =
                    txtBookingId.getText().trim();

            String month =
                    monthCombo.getSelectedItem().toString();

            String priceText =
                    txtPrice.getText().trim();

            String weightText =
                    weightCombo.getSelectedItem().toString();

            String quantityText =
                    txtQuantity.getText().trim();

            String license =
                    txtLicense.getText().trim();


            if (cylinderId.isEmpty() ||
                    bookingId.isEmpty() ||
                    priceText.isEmpty() ||
                    quantityText.isEmpty() ||
                    license.isEmpty()) {
                throw new IllegalArgumentException(
                        "Please fill all required commercial fields.");
            }


            // check cylinder id
            if (!cylinderId.matches("NOC-[0-9]+")) {
                throw new IllegalArgumentException(
                        "Cylinder ID must be like NOC-001.");
            }


            // check duplicate ids
            if (isDuplicateCylinderId(cylinderId)) {
                throw new IllegalArgumentException(
                        "Cylinder ID already exists.");
            }


            if (isDuplicateBookingId(bookingId)) {
                throw new IllegalArgumentException(
                        "Booking ID already exists.");
            }


            double price =
                    Double.parseDouble(priceText);

            double weight =
                    Double.parseDouble(weightText);

            int quantity =
                    Integer.parseInt(quantityText);


            if (price <= 0 || weight <= 0) {
                throw new IllegalArgumentException(
                        "Price and weight must be greater than zero.");
            }


            if (quantity <= 0) {
                throw new IllegalArgumentException(
                        "Quantity must be greater than zero.");
            }


            CommercialCylinder commercial =
                    new CommercialCylinder(
                            cylinderId,
                            bookingId,
                            month,
                            price,
                            weight,
                            quantity,
                            license);


            cylinders.add(commercial);


            outputArea.append(
                    "Commercial cylinder added successfully.\n");

            outputArea.append(
                    commercial.display() + "\n");


            JOptionPane.showMessageDialog(
                    frame,
                    "Commercial cylinder added successfully.");
        } catch (NumberFormatException ex) {
            showError("Please enter valid numbers.");
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }


    // calculate domestic price after subsidy
    public void calculateSubsidy() {
        String id =
                txtSearchId.getText().trim();


        if (id.isEmpty()) {
            showError(
                    "Please enter a Cylinder ID.");
            return;
        }


        LPGCylinder cylinder =
                findCylinder(id);


        if (cylinder == null) {
            showError(
                    "Cylinder ID not found.");
            return;
        }


        // check type before casting
        if (cylinder instanceof DomesticCylinder) {
            DomesticCylinder domestic =
                    (DomesticCylinder) cylinder;


            double finalPrice =
                    domestic.calculateFinalPrice();


            outputArea.append(
                    "Price after subsidy: " +
                            finalPrice + "\n");


            JOptionPane.showMessageDialog(
                    frame,
                    "Price after subsidy: " +
                            finalPrice);
        } else if (cylinder instanceof CommercialCylinder) {
            showError(
                    "This is a commercial cylinder. " +
                            "Please use Calculate Bulk Discount.");
        }
    }


    // calculate commercial bulk discount
    public void calculateDiscount() {
        String id =
                txtSearchId.getText().trim();


        if (id.isEmpty()) {
            showError(
                    "Please enter a Cylinder ID.");
            return;
        }


        LPGCylinder cylinder =
                findCylinder(id);


        if (cylinder == null) {
            showError(
                    "Cylinder ID not found.");
            return;
        }


        // check type before casting
        if (cylinder instanceof CommercialCylinder) {
            CommercialCylinder commercial =
                    (CommercialCylinder) cylinder;


            double finalPrice =
                    commercial.calculateFinalPrice();


            outputArea.append(
                    "Total price after bulk discount: " +
                            finalPrice + "\n");


            JOptionPane.showMessageDialog(
                    frame,
                    "Total price after bulk discount: " +
                            finalPrice);
        } else if (cylinder instanceof DomesticCylinder) {
            showError(
                    "This is a domestic cylinder. " +
                            "Please use Calculate Price After Subsidy.");
        }
    }


    // display all cylinders
    public void displayAll() {
        if (cylinders.size() == 0) {
            outputArea.setText(
                    "No cylinder records available.");
            return;
        }


        outputArea.setText(
                "===== ALL CYLINDER DETAILS =====\n\n");


        for (int i = 0;
             i < cylinders.size();
             i++) {
            LPGCylinder cylinder =
                    cylinders.get(i);


            outputArea.append(
                    cylinder.display() + "\n\n");
        }
    }


    // identify cylinder type
    public void identifyCylinder() {
        String id =
                txtSearchId.getText().trim();


        if (id.isEmpty()) {
            showError(
                    "Please enter a Cylinder ID.");
            return;
        }


        LPGCylinder cylinder =
                findCylinder(id);


        if (cylinder == null) {
            showError(
                    "Cylinder ID not found.");
            return;
        }


        if (cylinder instanceof DomesticCylinder) {
            outputArea.append(
                    id + " is a Domestic Cylinder.\n");


            JOptionPane.showMessageDialog(
                    frame,
                    "Domestic Cylinder");
        } else if (cylinder instanceof CommercialCylinder) {
            outputArea.append(
                    id + " is a Commercial Cylinder.\n");


            JOptionPane.showMessageDialog(
                    frame,
                    "Commercial Cylinder");
        } else {
            showError(
                    "Unknown cylinder type.");
        }
    }


    // clear all fields
    public void clearFields() {
        txtSubsidy.setText("");

        txtCitizenship.setText("");

        txtLicense.setText("");

        txtBookingId.setText("");

        txtCylinderId.setText("");

        txtPrice.setText("");

        txtQuantity.setText("");

        txtSearchId.setText("");

        monthCombo.setSelectedIndex(0);

        typeCombo.setSelectedIndex(0);

        weightCombo.setSelectedIndex(0);

        outputArea.setText("");
    }


    // check duplicate cylinder id
    public boolean isDuplicateCylinderId(
            String id) {
        for (int i = 0;
             i < cylinders.size();
             i++) {
            if (cylinders.get(i)
                    .getCylinderId()
                    .equalsIgnoreCase(id)) {
                return true;
            }
        }

        return false;
    }


    // check duplicate booking id
    public boolean isDuplicateBookingId(
            String id) {
        for (int i = 0;
             i < cylinders.size();
             i++) {
            LPGCylinder cylinder =
                    cylinders.get(i);


            if (cylinder instanceof DomesticCylinder) {
                DomesticCylinder domestic =
                        (DomesticCylinder) cylinder;


                if (domestic.getBookingId()
                        .equalsIgnoreCase(id)) {
                    return true;
                }
            } else if (cylinder instanceof CommercialCylinder) {
                CommercialCylinder commercial =
                        (CommercialCylinder) cylinder;


                if (commercial.getBookingId()
                        .equalsIgnoreCase(id)) {
                    return true;
                }
            }
        }

        return false;
    }


    // check domestic monthly quota
    public boolean checkDomesticQuota(
            String citizenship,
            String month) {
        int count = 0;


        for (int i = 0;
             i < cylinders.size();
             i++) {
            LPGCylinder cylinder =
                    cylinders.get(i);


            if (cylinder instanceof DomesticCylinder) {
                DomesticCylinder domestic =
                        (DomesticCylinder) cylinder;


                if (domestic.getCitizenshipNumber()
                        .equals(citizenship)
                        &&
                        domestic.getMonth()
                                .equals(month)) {
                    count++;
                }
            }
        }


        if (count >= 2) {
            return true;
        }


        return false;
    }


    // find cylinder using id
    public LPGCylinder findCylinder(
            String id) {
        for (int i = 0;
             i < cylinders.size();
             i++) {
            LPGCylinder cylinder =
                    cylinders.get(i);


            if (cylinder.getCylinderId()
                    .equalsIgnoreCase(id)) {
                return cylinder;
            }
        }


        return null;
    }


    // export data to file
    public void exportFile() {
        if (cylinders.size() == 0) {
            showError(
                    "There is no data to export.");
            return;
        }


        JFileChooser chooser =
                new JFileChooser();


        chooser.setDialogTitle(
                "Save Cylinder Data");


        chooser.setFileFilter(
                new FileNameExtensionFilter(
                        "Text Files",
                        "txt"));


        int result =
                chooser.showSaveDialog(frame);


        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }


        File file =
                chooser.getSelectedFile();


        if (!file.getName()
                .toLowerCase()
                .endsWith(".txt")) {
            file = new File(
                    file.getAbsolutePath() + ".txt");
        }


        try {
            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(file));


            for (int i = 0;
                 i < cylinders.size();
                 i++) {
                LPGCylinder cylinder =
                        cylinders.get(i);


                if (cylinder instanceof DomesticCylinder) {
                    DomesticCylinder domestic =
                            (DomesticCylinder) cylinder;


                    writer.write(
                            "Domestic|" +
                                    domestic.getCylinderId() + "|" +
                                    domestic.getBookingId() + "|" +
                                    domestic.getMonth() + "|" +
                                    domestic.getBasePrice() + "|" +
                                    domestic.getWeight() + "|" +
                                    domestic.getSubsidyAmount() + "|" +
                                    domestic.getCitizenshipNumber());

                    writer.newLine();
                } else if (cylinder instanceof CommercialCylinder) {
                    CommercialCylinder commercial =
                            (CommercialCylinder) cylinder;


                    writer.write(
                            "Commercial|" +
                                    commercial.getCylinderId() + "|" +
                                    commercial.getBookingId() + "|" +
                                    commercial.getMonth() + "|" +
                                    commercial.getBasePrice() + "|" +
                                    commercial.getWeight() + "|" +
                                    commercial.getQuantity() + "|" +
                                    commercial.getBusinessLicense());

                    writer.newLine();
                }
            }


            writer.close();


            JOptionPane.showMessageDialog(
                    frame,
                    "Data exported successfully.");
        } catch (IOException ex) {
            showError(
                    "Error while exporting file.");
        }
    }


    // load data from file
    public void loadFile() {
        JFileChooser chooser =
                new JFileChooser();


        chooser.setDialogTitle(
                "Load Cylinder Data");


        chooser.setFileFilter(
                new FileNameExtensionFilter(
                        "Text Files",
                        "txt"));


        int result =
                chooser.showOpenDialog(frame);


        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }


        File file =
                chooser.getSelectedFile();


        try {
            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file));


            cylinders.clear();


            String line;

            String allData = "";


            while ((line = reader.readLine()) != null) {
                allData =
                        allData + line + "\n";


                String[] data =
                        line.split("\\|");


                if (data[0].equals("Domestic")) {
                    if (data.length == 8) {
                        DomesticCylinder domestic =
                                new DomesticCylinder(
                                        data[1],
                                        data[2],
                                        data[3],
                                        Double.parseDouble(data[4]),
                                        Double.parseDouble(data[5]),
                                        Double.parseDouble(data[6]),
                                        data[7]);


                        cylinders.add(domestic);
                    }
                } else if (data[0].equals("Commercial")) {
                    if (data.length == 8) {
                        CommercialCylinder commercial =
                                new CommercialCylinder(
                                        data[1],
                                        data[2],
                                        data[3],
                                        Double.parseDouble(data[4]),
                                        Double.parseDouble(data[5]),
                                        Integer.parseInt(data[6]),
                                        data[7]);


                        cylinders.add(commercial);
                    }
                }
            }


            reader.close();


            showLoadedData(
                    file.getName(),
                    allData);


            outputArea.append(
                    "File loaded successfully.\n");


            JOptionPane.showMessageDialog(
                    frame,
                    "Data loaded successfully.");
        } catch (IOException ex) {
            showError(
                    "Error while loading file.");
        } catch (NumberFormatException ex) {
            showError(
                    "Invalid number found in file.");
        } catch (IllegalArgumentException ex) {
            showError(
                    ex.getMessage());
        }
    }


    // show loaded file in a new window
    public void showLoadedData(
            String fileName,
            String data) {
        JFrame newFrame =
                new JFrame(
                        "Loaded Data - " +
                                fileName);


        newFrame.setSize(
                600,
                400);


        newFrame.setLocationRelativeTo(frame);


        JTextArea area =
                new JTextArea();


        area.setEditable(false);


        area.setText(data);


        JScrollPane scroll =
                new JScrollPane(area);


        newFrame.add(scroll);


        newFrame.setVisible(true);
    }


    // show error message
    public void showError(
            String message) {
        outputArea.append(
                "Error: " +
                        message +
                        "\n");


        JOptionPane.showMessageDialog(
                frame,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }


    // main method
    public static void main(String[] args) {
        new NOCApp();
    }
}