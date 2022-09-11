package ebookshop.model;

public class Book {
	int id;
	String title;
	String author;
	float price;
	int quantity;

	// Getter and setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author == null ? "" : author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	int categoryId;

	// Book no-arg constructor
	public Book() {
	}

	public Book(String t, float p, int q) {
		title = t;
		price = p;
		quantity = q;
	}

	public Book(int id, String t, float p, int q) {
		title = t;
		this.id = id;
		price = p;
		quantity = q;
	}

	public Book(int id, String title, String author, float price, int quantity, int categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantity = quantity;
		this.categoryId = categoryId;
	}

	// Getter and setter methods
	public String getTitle() {
		return title == null ? "" : title;
	}

	public void setTitle(String t) {
		title = t;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float p) {
		price = p;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int q) {
		quantity = q;
	}
	public String toString ()
	{
		return String.format("%d. %s $%.2f",id, title, price);
	}
//	
}
