package booking;

import java.util.Objects;

public class User {

    private final String n;
    private final String e;

    public User(String n, String e) {
        this.n = n;
        this.e = e;
    }

    public String getName() {
        return n;
    }

    public String getEmail() {
        return e;
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
