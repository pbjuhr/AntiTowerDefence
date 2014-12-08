package com.jaap.antitowerdefence.terrain;

import com.jaap.antitowerdefence.unit.Unit;

/**
 * 
 * @author Andreas Bolzyk id10abk
 *
 */
public interface LandOn {

    public void setPostion(Unit unit);

    public void setReachGoal(Unit unit);

    public void setDirections(Unit unit);

    /*
     * TODO Need a interface method her to check if unit is on me. Need to now
     * if unit is on my position
     */

}
