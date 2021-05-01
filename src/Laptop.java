/**
 * @author Sush Mullur
 * The Laptop object is responsible for storing the properties of a laptop, and calculating its value score.
 */
class Laptop{

    //Laptop manufacturer, just for reference.
    private final String brand;
    //Amount of RAM.
    private final int ram;
    //Clock speed represented as a double.
    private final double clockSpeed;
    //Boolean representing whether the laptop has a solid state drive
    private final boolean isSSD;
    //Integer representing the storage capacity of the laptop.
    private final int storage;
    //Integer representing the display resolution of the laptop.
    private final int displayResolution;
    //Boolean representing whether the laptop has a dedicated graphics card.
    private final boolean dedicated;
    //Double representing the cost of the laptop.
    private final double cost;
    //Double representing the "value" of the laptop.
    private double score;

    /**
     * Constructor that sets all the values.
     * Also calls a method that calculates the score.
     * @param brand Manufacturer
     * @param ram Amount of RAM
     * @param clockSpeed Clock speed of the processor
     * @param isSSD Solid State Drive status
     * @param storage Amount of storage in the laptop
     * @param displayRes Resolution of the laptop display
     * @param dedicated Dedicated graphics card status
     * @param cost Price of the laptop
     */
    Laptop(String brand, int ram, double clockSpeed, boolean isSSD, int storage, int displayRes, boolean dedicated,
           double cost) {
        this.brand = brand;
        this.ram = ram;
        this.clockSpeed = clockSpeed;
        this.isSSD = isSSD;
        this.storage = storage;
        this.displayResolution = displayRes;
        this.dedicated = dedicated;
        this.cost = cost;
        calculateScore();
    }

    /**
     * Gets the score of the laptop
     * @return "Value" of the laptop.
     */
    double getScore() {
        return this.score;
    }

    /**
     * Turns the object into a String that can be read by the user.
     * @return Properties and value of the laptop as a String.
     */
    public String toString() {
        return String.format("""
                        Brand: %s\s
                        RAM: %dGB
                        Clock Speed: %.2fGHz
                        Has SSD: %b
                        Storage: %dGB
                        Screen Resolution: %dp
                        Has Dedicated Graphics Card: %b
                        Cost: $%.2f""", brand, ram, clockSpeed, isSSD,
                storage, displayResolution, dedicated, cost);
    }

    /**
     * Calculates the value and the score of the laptop based on my personal preferences.
     */
    private void calculateScore() {
        score += ram * 110;
        score += 1002 * clockSpeed;
        if(isSSD) score += 3000;
        score += storage * 1.25;
        score += displayResolution;
        if(dedicated) score += 3000;
        score -= (cost * 7);
    }

}
