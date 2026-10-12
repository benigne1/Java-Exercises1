package Library;
// =====================================================
//  Book class: one object = one book
// =====================================================
public class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private boolean available;   // true = on the shelf, false = borrowed

    public Book(int id, String title, String author, String category, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = available;
    }

    // ---------- Getters ----------
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }

    // ---------- Setters ----------
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setCategory(String category) { this.category = category; }
    public void setAvailable(boolean available) { this.available = available; }

    // Text shown when we print a book
    public String toString() {
        String status;
        if (available) {
            status = "Available";
        } else {
            status = "Borrowed";
        }
        return "ID: " + id + " | " + title + " | " + author + " | " + category + " | " + status;
    }
}