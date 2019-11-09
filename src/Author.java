/**
 * @author Steven Archuleta
 * @date 01 November 2019
 */
public class Author {

    public static final int DEFAULT_YEAR = AuthorBookConstants.UNKNOWN_YEAR;

    // Instance variables
    private String givenName;
    private String surname;
    private int birthYear = DEFAULT_YEAR;
    private int deathYear = DEFAULT_YEAR;

    // class variables
    public static int validYearStart = -1999;
    public static int validYearEnd = 2023;

    /**
     * Creates an author object with the first and/or last name of the author.
     * @param givenName - Enter first name. If left empty will be created with default value.
     * @param surname - Enter last name. If left empty will be create with default value.
     */
    public Author(String givenName, String surname) {
        Author j = AuthorBookConstants.UNKNOWN_AUTHOR;
        if(givenName.equals("")&&surname.equals("")){
            this.givenName = j.givenName;
            this.surname = j.surname;
        }else if(givenName.equals("")) {
            this.givenName = j.givenName;
        }else if(surname.equals("")){
            this.surname = j.surname;
            this.givenName = givenName;
        }else{
            this.givenName = givenName;
            this.surname = surname;
        }
    }

    // Getters

    /**
     * Gets the birth year of the author.
     * @return - Must be a valid birth year.
     */
    public int getBirthYear(){
        return birthYear;
    }

    /**
     * Gets death year of the author.
     * @return - Must be a valid year and after the birth year.
     */
    public int getDeathYear(){
        return deathYear;
    }

    // Setters

    /**
     * Set the birth year of the Author.
     * @param birth - Must be a valid year before -2000 and not 0.
     */
    public void setLifeRange(int birth){
        if(birth < validYearStart || birth > validYearEnd){
            System.out.println(" Birth year not valid");
        }else{
            this.birthYear = birth;
        }
    }

    /**
     * Set the birth year and death year for an author.
     * @param birth - Year of author's birth. Must be a valid year and come before death year.
     * @param death - Year of author's death. Must be after the birth year.
     */
    public void setLifeRange(int birth, int death){
//        this.birthYear = DEFAULT_YEAR;
//        this.deathYear = DEFAULT_YEAR;
        if(death < birth || birth < validYearStart || death > validYearEnd){
            System.out.println(" Birth year or death year are not valid years.");
        }else{
            this.birthYear = birth;
            this.deathYear = death;
        }
    }

    /**
     * Checks to see if authors have the same name or first initial and same last name.
     * @param other - other author's names to be used in comparison.
     * @return - return true if has same first initial or given name and last name.
     */
    public boolean hasSameName(Author other){
        //Get other author names and convert to lowercase for matching
        String given = other.givenName.toLowerCase();
        String sur = other.surname.toLowerCase();

        //Get other authors first initials for both given and first name.
        char firstInitial = given.charAt(0);

        //Make tests to limit line length.
        boolean result = false;
        boolean givenMatch = givenName.toLowerCase().equals(given);
        boolean givenInitialMatch = givenName.toLowerCase().charAt(0)==firstInitial;
        boolean givenLengthOne = (givenName.length()==1||given.length()==1);
        boolean surMatch = surname.toLowerCase().equals(sur);

        if(givenMatch || givenInitialMatch && givenLengthOne) {
            if(surMatch){
                result = true;
            }
        }return result;
    }

    /**
     * Returns a string of authors surname and given name.
     * @return - Returns the author's name as: surname, given name
     */
    public String toString(){
        String proper = "";
        proper += surname + ", " + givenName;
        return proper;
    }

    /**
     * Returns a string containing information about the author if it is not a default value.
     * @return - surname, given name if all other values are default || Surname, given name (born date)
     * || Surname, given name (birth year - death year)
     */
    public String getInfoString(){
        String information = "";
        if(deathYear == DEFAULT_YEAR && this.birthYear == DEFAULT_YEAR) {
            information = toString();
        }else if(deathYear == DEFAULT_YEAR){
            information = toString() + " (born " + birthYear + ")";
        }else{
            information = toString()+" ("+ birthYear + "-" + deathYear+")";
        }
        return information;
    }
}
