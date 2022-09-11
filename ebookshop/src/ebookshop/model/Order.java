package ebookshop.model;

public class Order {
	int id;

	String deliveryName;
	String deliveryAddress;
	String creditCardNumber;
	String creditCardName;
	String expiryDate;
	float totalSale;
	int totalQuantity;
	
	// Getter and setter methods
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public float getTotalSale() {
		return totalSale;
	}
	public void setTotalSale(float totalSale) {
		this.totalSale = totalSale;
	}
	// Order no-arg constructor
	public Order() {}
	
	public Order(String deliveryName, String deliveryAddress, String creditCardNumber, String creditCardName,
			String expiryDate) {
		super();
		this.deliveryName = deliveryName;
		this.deliveryAddress = deliveryAddress;
		this.creditCardNumber = creditCardNumber;
		this.creditCardName = creditCardName;
		this.expiryDate = expiryDate;
	}
	
	// Getter and setter methods
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getCreditCardName() {
		return creditCardName;
	}
	public void setCreditCardName(String creditCardName) {
		this.creditCardName = creditCardName;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
