package Library;

public import java.util.ArrayList;

// =====================================================
//  LibraryManager class: keeps all books in ONE list
// =====================================================
public class LibraryManager {

    private ArrayList<Book> books = new ArrayList<Book>();

    // Helper: true if the text is not null and not just spaces
    private boolean isNotEmpty(String text) {
        return text != null && text.trim().length() > 0;
    }

    // 1 + 14 (+ rule 5): add a book
    // Rejects: duplicate IDs and empty title/author/category
    public boolean addBook(Book b) {
        if (findById(b.getId()) != null) {
            System.out.println("Cannot add: ID " + b.getId() + " already exists.");
            return false;
        }
        if (!isNotEmpty(b.getTitle()) || !isNotEmpty(b.getAuthor()) || !isNotEmpty(b.getCategory())) {
            System.out.println("Cannot add: title, author and category must not be empty.");
            return false;
        }
        books.add(b);
        return true;
    }

    // 2: display all books
    public void displayAll() {
        System.out.println("===== All books (" + books.size() + ") =====");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }

    // 3: search by ID (returns null if not found)
    public Book findById(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                return books.get(i);
            }
        }
        return null;
    }

    // 4: search by title (the title only has to CONTAIN the search word)
    public ArrayList<Book> searchByTitle(String word) {
        ArrayList<Book> result = new ArrayList<Book>();
        for (int i = 0; i < books.size(); i++) {
            String title = books.get(i).getTitle().toLowerCase();
            if (title.contains(word.toLowerCase())) {
                result.add(books.get(i));
            }
        }
        return result;
    }

    // 5: all books by one author
    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<Book>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getAuthor().equalsIgnoreCase(author)) {
                result.add(books.get(i));
            }
        }
        return result;
    }

    // 6: new list with the books of one category
    public ArrayList<Book> filterByCategory(String category) {
        ArrayList<Book> result = new ArrayList<Book>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getCategory().equalsIgnoreCase(category)) {
                result.add(books.get(i));
            }
        }
        return result;
    }

    // 7: update title, author and category using the ID.
    // Give "" (empty text) for a field you do not want to change.
    public boolean updateBook(int id, String newTitle, String newAuthor, String newCategory) {
        Book b = findById(id);
        if (b == null) {
            return false;
        }
        if (isNotEmpty(newTitle)) {
            b.setTitle(newTitle);
        }
        if (isNotEmpty(newAuthor)) {
            b.setAuthor(newAuthor);
        }
        if (isNotEmpty(newCategory)) {
            b.setCategory(newCategory);
        }
        return true;
    }

    // 8: remove a book using the ID
    public boolean removeBook(int id) {
        Book b = findById(id);
        if (b == null) {
            return false;
        }
        books.remove(b);
        return true;
    }

    // 9: borrow a book (only if it exists and is available)
    public boolean borrowBook(int id) {
        Book b = findById(id);
        if (b == null || !b.isAvailable()) {
            return false;
        }
        b.setAvailable(false);
        return true;
    }

    // 10: return a book (only if it exists and is currently borrowed)
    public boolean returnBook(int id) {
        Book b = findById(id);
        if (b == null || b.isAvailable()) {
            return false;
        }
        b.setAvailable(true);
        return true;
    }

    // 11: total number of books
    public int countBooks() {
        return books.size();
    }

    // 12: number of available books
    public int countAvailable() {
        int count = 0;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).isAvailable()) {
                count++;
            }
        }
        return count;
    }

    // 13: new list with only the borrowed books
    public ArrayList<Book> findBorrowed() {
        ArrayList<Book> result = new ArrayList<Book>();
        for (int i = 0; i < books.size(); i++) {
            if (!books.get(i).isAvailable()) {
                result.add(books.get(i));
            }
        }
        return result;
    }

    // 15: sort books A-Z by title (bubble sort: swap neighbours that are in the wrong order)
    public void sortByTitle() {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - 1 - i; j++) {
                String first = books.get(j).getTitle();
                String second = books.get(j + 1).getTitle();
                if (first.compareToIgnoreCase(second) > 0) {
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }

    // 16: find the category that has the most books.
    // If two categories have the same count, the one found first wins.
    public String mostCommonCategory() {
        if (books.size() == 0) {
            return "No books in the library";
        }

        // Step 1: list the different categories
        ArrayList<String> categories = new ArrayList<String>();
        for (int i = 0; i < books.size(); i++) {
            String c = books.get(i).getCategory();
            if (!categories.contains(c)) {
                categories.add(c);
            }
        }

        // Step 2: count the books of each category and remember the biggest
        String bestCategory = "";
        int bestCount = 0;
        for (int i = 0; i < categories.size(); i++) {
            int count = 0;
            for (int j = 0; j < books.size(); j++) {
                if (books.get(j).getCategory().equals(categories.get(i))) {
                    count++;
                }
            }
            if (count > bestCount) {
                bestCount = count;
                bestCategory = categories.get(i);
            }
        }
        return bestCategory + " (" + bestCount + " books)";
    }

    // 17: remove all books that meet a removal condition.
    // Our condition: the book is in the category given (for example "Withdrawn").
    // Borrowed books in other categories are NOT removed.
    // We go from the END of the list to the start, so removing a book
    // does not move the books we have not checked yet.
    public int removeBooksByCategory(String category) {
        int removed = 0;
        for (int i = books.size() - 1; i >= 0; i--) {
            if (books.get(i).getCategory().equalsIgnoreCase(category)) {
                books.remove(i);
                removed++;
            }
        }
        return removed;
    }

    // 18: does the book exist, and can it be borrowed?
    public String checkAvailability(int id) {
        Book b = findById(id);
        if (b == null) {
            return "Book " + id + " does not exist.";
        }
        if (b.isAvailable()) {
            return "Book " + id + " (" + b.getTitle() + ") is available for borrowing.";
        }
        return "Book " + id + " (" + b.getTitle() + ") is currently borrowed.";
    }
} {
    
}
