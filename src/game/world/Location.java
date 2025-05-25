package game.world;

import game.entity.Enemy;

/**
 * Location.java is the blueprint for each of the 5*5 map tiles
 * @seanlee1991
 */

public class Location {
    private String locationDescripsion;
    private boolean isVisited;
    private boolean isImpossible;
    private Enemy enermy;

    public Location(String locationDescripsion, boolean impossible ) {
        this.locationDescripsion = locationDescripsion;
        this.isVisited = false;
        this.isImpossible = impossible;
    }

    public void describe() {
        System.out.println(this.locationDescripsion);
    }

    //enermy
    public boolean isImpossible() { return this.isImpossible;}
    public void setIsVisited(boolean v) { this.isVisited =v;}

    public boolean hasEnermy() { return enermy != null && enermy.isAlive();}
    
    
    public Enemy getEnermy() { return enermy;}

    public void setEnermy(Enemy enermy) { this.enermy = enermy;}

    public void clearEnermy() { this.enermy = null;}

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}