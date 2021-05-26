package booking;

import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom extends ClassRoom {

    private final int bookableStartHour;
    private final int bookableEndHour;

    public ComputerClassRoom(
        String name,
        String description,
        boolean hasProjector,
        int bookableStartHour,
        int bookableEndHour
    ) {
        super(name, description, hasProjector);
        this.bookableStartHour = bookableStartHour;
        this.bookableEndHour = bookableEndHour;
    }

    public int getBookableStartHour() {
        return bookableStartHour;
    }

    public int getBookableEndHour() {
        return bookableEndHour;
    }

    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        Interval openInterval = new Interval(
            interval.getStart().withHour(this.getBookableStartHour()),
            interval.getStart().withHour(this.getBookableEndHour())
        );

        if (
            !openInterval.isDuring(interval.getStart())
            || !openInterval.isDuring(interval.getEnd())
        ){
            return Optional.empty();
        }

        return super.book(interval, customer);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && this.getBookableStartHour() == ((ComputerClassRoom) o).getBookableStartHour()
            && this.getBookableEndHour() == ((ComputerClassRoom) o).getBookableEndHour();
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            this.getBookableStartHour(),
            this.getBookableEndHour()
        );
    }

}
