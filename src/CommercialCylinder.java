public class CommercialCylinder extends LPGCylinder {
    private String bookingId;
    private String month;
    private int quantity;
    private String businessLicense;

    // constructor
    public CommercialCylinder(String cylinderId, String bookingId,
                              String month, double basePrice,
                              double weight, int quantity,
                              String businessLicense) {
        super(cylinderId, "Commercial", basePrice, weight);

        setBookingId(bookingId);
        setMonth(month);
        setQuantity(quantity);
        setBusinessLicense(businessLicense);
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

    // getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // setter for quantity
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero.");
        }

        this.quantity = quantity;
    }

    // getter for business license
    public String getBusinessLicense() {
        return businessLicense;
    }

    // setter for business license
    public void setBusinessLicense(String businessLicense) {
        if (businessLicense == null ||
                businessLicense.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Business license cannot be empty.");
        }

        this.businessLicense = businessLicense;
    }

    // calculate total price after bulk discount
    @Override
    public double calculateFinalPrice() {
        double totalPrice = getBasePrice() * quantity;
        double discount = 0;

        if (quantity >= 10) {
            discount = totalPrice * 0.05;
        } else if (quantity >= 5) {
            discount = totalPrice * 0.03;
        }

        return totalPrice - discount;
    }

    // display commercial cylinder details
    @Override
    public String display() {
        return "Commercial Cylinder" +
                "\nCylinder ID: " + getCylinderId() +
                "\nBooking ID: " + bookingId +
                "\nMonth: " + month +
                "\nPrice Per Cylinder: " + getBasePrice() +
                "\nWeight: " + getWeight() + " kg" +
                "\nQuantity: " + quantity +
                "\nBusiness License: " + businessLicense +
                "\nTotal Price After Discount: " + calculateFinalPrice() +
                "\n-----------------------------";
    }
}