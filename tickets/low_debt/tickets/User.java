package tickets;

import java.util.Objects;

public class User {

    public enum Occupation {
        STUDENT,
        ADULT,
        RETIREE
    }

    private final String name;
    private final String email;
    private final int age;
    private final Occupation occupation;

    public User(String name, String email, int age, Occupation occupation) {
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

    public Occupation getOccupation() {
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
