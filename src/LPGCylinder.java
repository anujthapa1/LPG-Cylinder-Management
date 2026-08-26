public abstract class LPGCylinder {
    private String cylinderId;
    private String cylinderType;
    private double basePrice;
    private double weight;

    // constructor
    public LPGCylinder(String cylinderId, String cylinderType,
                       double basePrice, double weight) {
        setCylinderId(cylinderId);
        setCylinderType(cylinderType);
        setBasePrice(basePrice);
        setWeight(weight);
    }

    // getter for cylinder id
    public String getCylinderId() {
        return cylinderId;
    }

    // setter for cylinder id
    public void setCylinderId(String cylinderId) {
        if (cylinderId == null || cylinderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Cylinder ID cannot be empty.");
        }

        this.cylinderId = cylinderId;
    }

    // getter for cylinder type
    public String getCylinderType() {
        return cylinderType;
    }

    // setter for cylinder type
    public void setCylinderType(String cylinderType) {
        if (cylinderType == null || cylinderType.trim().isEmpty()) {
            throw new IllegalArgumentException("Cylinder type cannot be empty.");
        }

        this.cylinderType = cylinderType;
    }

    // getter for price
    public double getBasePrice() {
        return basePrice;
    }

    // setter for price
    public void setBasePrice(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        this.basePrice = basePrice;
    }

    // getter for weight
    public double getWeight() {
        return weight;
    }

    // setter for weight
    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero.");
        }

        this.weight = weight;
    }
    // child classes must calculate final price
    public abstract double calculateFinalPrice();

    // child classes must display their details
    public abstract String display();

    @Override
    public String toString() {
        return display();
    }
}