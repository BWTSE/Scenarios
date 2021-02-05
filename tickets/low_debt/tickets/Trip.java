package tickets;


public class Trip {

    private Zone startZone;
    private Zone endZone;

    public Trip(Zone startZone, Zone endZone) {
        this.startZone = startZone;
        this.endZone = endZone;
    }

    public Zone getStartZone() {
        return this.startZone;
    }

    public Zone getEndZone() {
        return this.endZone;
    }
}
