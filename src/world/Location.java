package game.world;

/**
 * Location.java is the blueprint for each of the 5*5 map tiles
 * @seanlee1991
 */

public class Location {
    private String locationDescripsion;
    private boolean isVisited;
    private boolean isImpossible;

    public Location(String locationDescripsion, boolean impossible ) {
        this.locationDescripsion = locationDescripsion;
        this.isVisited = false;
        this.isImpossible = impossible;
    }

    public void describe() {
        System.out.println(this.locationDescripsion);
    }

    public boolean isImpossible() { return this.isImpossible;}
    public void setIsVisited(boolean v) { this.isVisited =v;}

}