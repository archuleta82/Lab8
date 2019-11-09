/**
 * @author Steven Archuleta
 * @date 01 November 2019
 */
public class Book {

    public static final String DEFAULT_TITLE = AuthorBookConstants.UNKNOWN_TITLE;
    public static final int DEFAULT_PUBLICATION = AuthorBookConstants.UNKNOWN_YEAR;
    public static final Author DEFAULT_AUTHOR = AuthorBookConstants.UNKNOWN_AUTHOR;
    public static final String DEFAULT_ISBN = AuthorBookConstants.UNKNOWN_ISBN;

    private String title;
    private Author author;
    private int year;
    private String isbn;

    /**
     * Creates book object and sets the title and author
     * while making the isbn and year of publication as default values.
     * @param title - Name of the book
     * @param author - name of the author as an Author object.
     */
    public Book(String title, Author author){
        this.title = title;
        this.author = author;
        this.year = DEFAULT_PUBLICATION;
        this.isbn = DEFAULT_ISBN;
    }

    /**
     * Create book object with only the title named other than default values.
     * @param title - Name of the book.
     */
    public Book(String title){
        this.title = title;
        this.author = DEFAULT_AUTHOR;
        this.year = DEFAULT_PUBLICATION;
        this.isbn = DEFAULT_ISBN;
    }

    /**
     * Creates a book object with all default values.
     */
    public Book(){
        this.title = DEFAULT_TITLE;
        this.author = DEFAULT_AUTHOR;
        this.year = DEFAULT_PUBLICATION;
        this.isbn = DEFAULT_ISBN;
    }

    /**
     * Sets the title for the book if it was a default value title.
     * @param title - Must not be an empty string.
     */
    public void setTitle(String title){
        if(!title.equals("")){
            this.title = title;
        }else{
            System.out.println("Title cannot be empty string.");
        }
    }

    /**
     * Returns the title of the book.
     * @return - title of the book.
     */
    public String getTitle(){
        return title;
    }

    /**
     * Sets the author for a book.
     * @param author - creates the Author object to be used as the books author.
     */
    public void setAuthor(Author author){
        this.author = author;
    }

    /**
     * Gets the author of the book.
     * @return - Author object for the book.
     */
    public Author getAuthor(){
        return author;
    }

    /**
     * Sets the year of publication.
     * @param year - must be a valid year and cannot equal 0 or be < -2000 or > 2024.
     */
    public void setPubYear(int year){
        if(year != DEFAULT_PUBLICATION&&year>Author.validYearStart&&year<Author.validYearEnd&&year!=0){
            this.year = year;
        }else{
            System.out.println("Not a valid publication year");
        }
    }

    /**
     * Gets the year of publication for the book.
     * @return - year of publication.
     */
    public int getPubYear(){
        return year;
    }

    /**
     * Set the international standard book number (ISBN) for the book.
     * @param isbn - must be 10 or 13 characters in length.
     */
    public void setIntStdBookNum(String isbn){
        if(isbn.length()==10||isbn.length()==13){
            this.isbn = isbn;
        }else{
            System.out.println("Invalid entry for isbn.\nMust be 10 or 13 characters.");
        }
    }

    /**
     * Gets the ISBN for the book.
     * @return - ISBN for book
     */
    public String getIntStdBookNum(){
        return isbn;
    }

    /**
     * Checks the author of the current book with the author of other book to see if they are the same.
     * @param other - other book that will be compared with the book used during method call.
     * @return - true if authors are same names or same first initial of given and same last name.
     */
    public boolean sameAuthor(Book other){
        boolean result = false;
        if(author.hasSameName(other.author)){
            result = true;
        }
        return result;
    }

    /**
     * Checks the ISBNs of two books to see if they are the same book.
     * @param other - other book's ISBN that will be compared with book used in method call.
     * @return - true if the ISBNs match: false if they do not.
     */
    public boolean equals(Book other){
        boolean result = false;
        if(isbn.equals(other.isbn)){
            result = true;
        }
        return result;
    }

    /**
     * Makes a string for the book depending on the known variables that are not default values.
     * @return - title. if all other values are default || title. author. if year is default
     * || title (year). author if all are set values.
     */
    public String toString(){
        String result = "";
        String authorProper = author.toString();
        if(isbn.equals(DEFAULT_ISBN)&&author.equals(DEFAULT_AUTHOR)&&year==DEFAULT_PUBLICATION){
            result += title+".";
        }else if(year==DEFAULT_PUBLICATION){
            result += title+". "+authorProper +".";
        }else{
            result += title +" (" + year +"). " + authorProper +".";
        }return result;
    }
}
