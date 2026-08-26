public class DomesticCylinder extends LPGCylinder {
    private String bookingId;
    private String month;
    private double subsidyAmount;
    private String citizenshipNumber;

    // constructor
    public DomesticCylinder(String cylinderId, String bookingId,
                            String month, double basePrice,
                            double weight, double subsidyAmount,
                            String citizenshipNumber) {
        super(cylinderId, "Domestic", basePrice, weight);

        setBookingId(bookingId);
        setMonth(month);
        setSubsidyAmount(subsidyAmount);
        setCitizenshipNumber(citizenshipNumber);
    }

    // getter for booking id
    public String getBookingId() {
        return bookingId;
    }

    // setter for booking id
    public void setBookingId(String bookingId) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Booking ID cannot be empty.");
        }

        this.bookingId = bookingId;
    }

    // getter for month
    public String getMonth() {
        return month;
    }

    // setter for month
    public void setMonth(String month) {
        if (month == null || month.trim().isEmpty()) {
            throw new IllegalArgumentException("Month cannot be empty.");
        }

        this.month = month;
    }

    // getter for subsidy
    public double getSubsidyAmount() {
        return subsidyAmount;
    }

    // setter for subsidy
    public void setSubsidyAmount(double subsidyAmount) {
        if (subsidyAmount < 0) {
            throw new IllegalArgumentException("Subsidy cannot be negative.");
        }

        if (subsidyAmount > getBasePrice()) {
            throw new IllegalArgumentException(
                    "Subsidy cannot be greater than the base price.");
        }

        this.subsidyAmount = subsidyAmount;
    }

    // getter for citizenship number
    public String getCitizenshipNumber() {
        return citizenshipNumber;
    }

    // setter for citizenship number
    public void setCitizenshipNumber(String citizenshipNumber) {
        if (citizenshipNumber == null ||
                !citizenshipNumber.matches("[0-9]{12}")) {
            throw new IllegalArgumentException(
                    "Citizenship number must contain exactly 12 digits.");
        }

        this.citizenshipNumber = citizenshipNumber;
    }

    // calculate price after subsidy
    @Override
    public double calculateFinalPrice() {
        return getBasePrice() - subsidyAmount;
    }

    // display domestic cylinder details
    @Override
    public String display() {
        return "Domestic Cylinder" +
                "\nCylinder ID: " + getCylinderId() +
                "\nBooking ID: " + bookingId +
                "\nMonth: " + month +
                "\nPrice: " + getBasePrice() +
                "\nWeight: " + getWeight() + " kg" +
                "\nSubsidy: " + subsidyAmount +
                "\nFinal Price: " + calculateFinalPrice() +
                "\nCitizenship Number: " + citizenshipNumber +
                "\n-----------------------------";
    }
}