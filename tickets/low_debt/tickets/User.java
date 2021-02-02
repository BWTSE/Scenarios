package tickets;

import java.util.Objects;

public class User {

    private final String name;
    private final String email;
    private final int age;
    private final boolean student;
    private final boolean retiree;

    public User(String name, String email, int age, boolean student, boolean retiree) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.student = student;
        this.retiree = retiree;
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

    public boolean isStudent() {
        return student;
    }

    public boolean isRetiree() {
        return retiree;
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