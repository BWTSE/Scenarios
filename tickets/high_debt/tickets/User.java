package tickets;

import java.util.Objects;

public class User {

    public enum Occupation {
        STUDENT,
        ADULT,
        RETIREE
    }

    private final String n;
    private final String e;
    private final int a;
    private final Occupation o;


    public User(String n, String e, int a, Occupation o) {
        this.n = n;
        this.e = e;
        this.a = a;
        this.o = o;
    }

    public String getName() {
        return n;
    }

    public String getEmail() {
        return e;
    }

    public int getAge() {
        return a;
    }

    public Occupation getOccupation() {
        return this.o;
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        User u = (User) o;
        return Objects.equals(getEmail(), u.getEmail());
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
