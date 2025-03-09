/**
 * Abstract base class representing a person in the project assignment system.
 * Provides common attributes and behaviors for both students and teachers.
 */
public abstract class Person {
    /** The name of the person. */
    protected String name;
    /** The date of birth of the person. */
    private String dob;

    /**
     * Default constructor for Person class.
     */
    public Person() {
    };

    /**
     * Creates a new person with specified name and date of birth.
     * 
     * @param name The name of the person
     * @param dob  The date of birth of the person
     */
    public Person(String name, String dob) {
        this.name = name;
        this.dob = dob;
    }

    /**
     * Gets the name of the person.
     * 
     * @return The person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the date of birth of the person.
     * 
     * @return The person's date of birth
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets the name of the person.
     * 
     * @param name The new name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the date of birth of the person.
     * 
     * @param dob The new date of birth to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * Returns a string representation of the person.
     * 
     * @return A string containing the person's name and date of birth
     */
    @Override
    public String toString() {
        return "Name: " + name + "\nDOB: " + dob;
    }
}