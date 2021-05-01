
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Sush Mullur
 * This class is the main brains for the UI and logic behind the program.
 */
public class UI {

    //JFrame object for the UI.
    private static final JFrame frame = new JFrame("Compare Laptops");
    //Panel object to place items in.
    private JPanel panel = new JPanel();
    //ArrayList of laptops.
    private final ArrayList<Laptop> laptops = new ArrayList<>();
    //Number of total laptops being compared.
    private int numLaptops;
    //Current laptop being analyzed.
    private int currentLaptop = 1;

    /**
     * Main method that gets run at the start of the program.
     * @param args String array of arguments.
     */
    public  static void main(String[] args) {
        new UI();
    }

    /**
     * Constructor of the UI object that creates the framework and gets the number of laptops being compared.
     */
    private UI() {
        createFrameWork();
        getNumLaptops();
    }

    /**
     * Creates the bare framework for the program.
     */
    private void createFrameWork() {
        frame.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        //Exits program when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(600,400));
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Gets the number of laptops being compared.
     */
    private void getNumLaptops() {
        //Radio buttons to choose the number of laptops.
        JRadioButton two = new JRadioButton("Compare two laptops");
        JRadioButton three = new JRadioButton("Compare three laptops");
        JRadioButton four = new JRadioButton("Compare four laptops");
        //Buttons are added to the panel.
        panel.add(two);
        panel.add(three);
        panel.add(four);
        JButton button = new JButton("Enter");
        panel.add(button);
        //Action listener for the enter button.
        //Sets the number of laptops.
        button.addActionListener(actionEvent -> {
            if(two.isSelected()) numLaptops = 2;
            if(three.isSelected()) numLaptops = 3;
            if(four.isSelected()) numLaptops = 4;
            gatherInfo();
        });
        frame.setVisible(true);
    }

    /**
     * Gathers information about each laptop.
     */
    private void gatherInfo() {
        //Base case for when to stop.
        if(currentLaptop > numLaptops) {
            showResults();
        } else {
            panel.removeAll();
            panel.repaint();
            //Prompts user to enter details
            String promptText = String.format("Laptop %d of %d: Please enter laptop details", currentLaptop, numLaptops);
            JTextArea prompt = new JTextArea(promptText);
            panel.add(prompt);
            //Brand prompt
            JLabel brandPrompt = new JLabel("Manufacturer");
            JTextField brand = new JTextField();
            //RAM prompt
            JLabel ramPrompt = new JLabel("Amount of RAM");
            JTextField ram = new JTextField();
            //Clock Speed prompt
            JLabel csPrompt = new JLabel("Clock Speed");
            JTextField cs = new JTextField();
            //SSD prompt
            JCheckBox SSD = new JCheckBox("Has Solid State Drive");
            //Storage prompt
            JLabel storagePrompt = new JLabel("Amount of Storage");
            JTextField storage = new JTextField();
            //Display resolution prompt
            JLabel resPrompt = new JLabel("Display Resolution");
            JTextField res = new JTextField();
            //Dedicated graphics card prompt
            JCheckBox dedicated = new JCheckBox("Has Dedicated Graphics Card");
            JLabel costPrompt = new JLabel("Cost in $");
            //Cost prompt
            JTextField cost = new JTextField();
            //Button and action listener to move to the next laptop.
            JButton button = new JButton("ENTER");
            button.addActionListener(actionEvent -> {
                updateLaptop(brand, ram, cs, SSD, storage, res, dedicated, cost);
                gatherInfo();
            });

            //All the UI elements are added.
            panel.add(brandPrompt);
            panel.add(brand);
            panel.add(ramPrompt);
            panel.add(ram);
            panel.add(csPrompt);
            panel.add(cs);
            panel.add(SSD);
            panel.add(storagePrompt);
            panel.add(storage);
            panel.add(resPrompt);
            panel.add(res);
            panel.add(dedicated);
            panel.add(costPrompt);
            panel.add(cost);
            panel.add(button);
            frame.setVisible(true);
        }
    }

    /**
     * Updates laptop ArrayList with a new laptop based on user choices.
     * @param brand TextField containing brand name.
     * @param ram TextField containing RAM amount.
     * @param clockSpeed TextField containing clock speed of processor.
     * @param SSD JCheckBox representing the presence of an SSD.
     * @param storage JTextField containing the amount of storage.
     * @param res JTextField containing the resolution of the laptop display.
     * @param dedicated JCheckBox representing the presence of a dedicated graphics card.
     * @param cost JTextField containing the cost of the laptop.
     */
    private void updateLaptop(JTextField brand, JTextField ram, JTextField clockSpeed, JCheckBox SSD,
                              JTextField storage, JTextField res, JCheckBox dedicated, JTextField cost) {
        //Values are turned into Strings, booleans, ints and doubles.
        String sBrand = brand.getText();
        int sRam = Integer.parseInt(ram.getText());
        double sCS = Double.parseDouble(clockSpeed.getText());
        boolean ssd = SSD.isSelected();
        int sStorage = Integer.parseInt(storage.getText());
        int sRest = Integer.parseInt(res.getText());
        boolean sDedicated = dedicated.isSelected();
        double sCost = Double.parseDouble(cost.getText());
        //New laptop objected is created and added to the laptops ArrayList.
        laptops.add(new Laptop(sBrand, sRam, sCS, ssd, sStorage, sRest, sDedicated, sCost));
        currentLaptop++;
    }

    /**
     * Shows the properties of the laptop, and its calculated score.
     */
    private void showResults() {
        panel.removeAll();
        panel.repaint();
        panel.setLayout(new GridLayout(0, numLaptops));
        //Laptops' properties are displayed to the user.
        for(Laptop laptop: laptops) {
            panel.add(new JTextArea(laptop.toString()));
        }
        //Laptops' score is displayed to the user.
        for(Laptop laptop: laptops) {
            String score = String.format("Score: %.2f points", laptop.getScore());
            JLabel label = new JLabel(score);
            label.setFont(new Font("TimesRoman", Font.BOLD, 18));
            panel.add(label);
        }
        frame.setVisible(true);
    }

}
