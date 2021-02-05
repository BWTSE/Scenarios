package tickets;

import java.util.Objects;

public class User {

    private final String name;
    private final String email;
    private final int age;
    public enum primaryOccupation {
        STUDENT,
        ADULT,
        RETIREE
    }
    private primaryOccupation occupation;

    public User(String name, String email, int age, primaryOccupation occupation) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public primaryOccupation getOccupation() {
        return this.occupation;
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    @Override
    public String toString() {
        return String.format("User %s (%s)", this.getName(), this.getEmail());
    }
}
