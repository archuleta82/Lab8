public class Library {

    /** Unique books in the library. */
    private Book[] books;

    /** Number of copies for each book. */
    private int[] copies;

    /** Number of copies currently on the shelf (checked in) for each book. */
    private int[] checkedIn;

    /** Number of unique books in the library. */
    private int numBooks;

    /** Construct a new empty Library. */
    public Library(int librarySize) {
        books = new Book[librarySize];
        copies = new int[librarySize];
        checkedIn = new int[librarySize];
        numBooks = 0;
    }

    /**
     * Get the number of total copies of all books that exist in the
     * library.
     * @return Total number of copies in the library.
     */
    public int getTotalCopies() {
        int sum = 0;
        for(int c: copies){
            sum += c;
        }
        return sum;
    }

    /**
     * Get the number of copies currently checked out.
     * @return Total number of copies checked out.
     */
    public int getNumCheckedOut() {
        int totalCopies = getTotalCopies();
        int inBooks = 0;
        for(int in: checkedIn){
            inBooks += in;
        }
        return totalCopies - inBooks;
    }

    /**
     * Get a String representing the status of the library.
     * @return Status string.
     */
    public String getStatus() {
        int count = 0;
        for (int i = 0; i < numBooks; i++) {
            if(books[i]!=null){
                count++;
            }
        }
        String unique = "Total unique books: " + count;
        String total = "Total number of copies: " + getTotalCopies();
        String outCheck = "Total checked out: " + getNumCheckedOut();
        return unique + "\n" + total + "\n" + outCheck;
    }

    /**
     * Add a single book to the library, on the shelf.
     *
     * If the book is already present, adds another copy.
     * If the book is new, add it after the existing books.
     * @param b Book to add.
     */
    public void addBook( Book b ) {
        boolean isCopy = false;
        if(numBooks > 0 && b != null) {
            for (int i = 0; i < numBooks; i++) {
                if (b.getIntStdBookNum().equals(books[i].getIntStdBookNum())&&b.getTitle().equals(books[i].getTitle())) {
                    isCopy = true;
                    copies[i]++;
                    checkedIn[i]++;
                }
            }
        }if(!isCopy) {
            books[numBooks] = b;
            copies[numBooks] += 1;
            checkedIn[numBooks] += 1;
            numBooks++;

        }
    }

    /**
     * Add all the books in the array to the library. Adds one copy of
     * each book.
     * @param newBooks Books to add.
     */
    public void addBooks( Book[] newBooks ) {
        for(Book b: newBooks){
            addBook(b);
        }
    }

    /**
     * Checks out a book from the library if possible.
     * @param b Book to check out.
     * @return String denoting success or failure.
     */
    public String checkOut ( Book b ) {
        String result = "";
        String bISBN = b.getIntStdBookNum();
        String bTitle = b.getTitle();

        for (int i = 0; i < numBooks; i++) {
            if (books[i].getIntStdBookNum().equals(bISBN) || books[i].getTitle().equals(bTitle)) {
                if (checkedIn[i] == 0) {
                    result = "All out of copies.";
                } else {
                    checkedIn[i]--;
                    result = "Checked out!";
                }
            }
        }if(result.equals("")){
            result = "Book not found.";
        }
        return result;
    }

    /**
     * Checks in a book to the library if possible.
     * @param b Book to check in.
     * @return String denoting success or failure.
     */
    public String checkIn ( Book b ) {
        String result = "";
        String bISBN = b.getIntStdBookNum();
        String bTitle = b.getTitle();

        for (int i = 0; i < numBooks; i++) {
            if (books[i].getIntStdBookNum().equals(bISBN) || books[i].getTitle().equals(bTitle)) {
                if (checkedIn[i] == copies[i]) {
                    result = "All of our copies are already checked in.";
                } else {
                    checkedIn[i]++;
                    result = "Checked in!";
                }
            }
        }
        if (result.equals("")) {
            result = "Book not found.";
        }
        return result;
    }

    /**
     * Get string representation of entire library collection and status.
     * @return String representation of library.
     */
    public String toString() {
        String line = "";

        for(int i = 0; i < numBooks; i++){
            String title = books[i].getTitle();
            String author = books[i].getAuthor().toString();
            line += i + ". " + title + ". " + author + "." + " : " + checkedIn[i] + "/" + copies[i]+ "\n";
        }
        line += "\n" + getStatus();
        return line;
    }

    /**
     * Get number of unique books that exist for a given author.
     * @param a The author to check.
     * @return Number of books by the author.
     */
    public int numBooksByAuthor( Author a ) {
        int result = 0;
        for(int i = 0; i < numBooks; i++){
            if(books[i].getAuthor().hasSameName(a)){
                result ++;
            }
        }
        return result;
    }

    /**
     * Returns a String that lists the unique books which exist for a
     * given author, in standard book format, with a newline after
     * each.  If no books are found for the author, returns string
     * that says so.
     *
     * @param a The author in question.
     * @return String listing books by the author.
     */
    public String listBooksByAuthor( Author a ) {
        String result = "";
        if(numBooksByAuthor(a) > 0){
            for(int i = 0; i < numBooks; i++){
                if (books[i].getAuthor().hasSameName(a)){
                    result += books[i].getTitle() + ". " + a.toString() + ".\n";
                }
            }
        }else{
            result += "No books by " + a.toString() + ".";
        }
        return result;
    }

    /**
     * Returns string that lists the unique books with contain the
     * given string within their titles, without regard for case, with
     * a newline after each.  If no books are found containing the
     * string, returns string that says so.
     * @param s The string to look for in the titles.
     * @return String listing books that contain given string in titles.
     */
    public String listBooksByTitle( String s ) {
        String line = "";
        for(int i = 0; i < numBooks; i++){
            String titleLC = books[i].getTitle().toLowerCase();
            String checkLC = s.toLowerCase();
            if(titleLC.contains(checkLC)){
                line += books[i].getTitle() + ". " + books[i].getAuthor().toString()+ ".\n";
            }
        }
        if(line.equals("")){
            line += "No books with " + "\"" + s + "\""+ " in the title.";
        }
        //System.out.println(line);
        return line;
    }

    /**
     * Deletes book entirely from the library.
     * @param b Book to remove.
     * @return String denoting success or failure.
     */
    public String deleteBook( Book b ) {
        // This method is for optional bonus points.
        String line = "";
        int index = -1;
        for(int i = 0; i < numBooks; i++){
            if(books[i].getIntStdBookNum().equals(b.getIntStdBookNum()) || books[i].getTitle().equals(b.getTitle())){
                index = i;
            }
        }if(index > -1){
            for(int j = index; j < numBooks;j++){
                checkedIn[j] = checkedIn[j+1];
                copies[j] = copies[j+1];
                books[j] = books[j+1];
            }
            books[numBooks] = null;
            copies[numBooks] = 0;
            checkedIn[numBooks] = 0;
            numBooks--;
            line += "Book removed.";
        }else{
            line += "Book not found.";
        }
        return line;
    }

}