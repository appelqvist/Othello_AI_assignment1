package main;

import java.util.LinkedList;

/**
 * Created by Andréas Appelqvist on 2016-09-26.
 */
public class Move {
    private int pos;
    private int player;
    private LinkedList<Integer> posOfSwitchBricks;

    public Move(int startPosition, int player){
        this.pos = startPosition;
        this.player = player;
        this.posOfSwitchBricks = new LinkedList<>();
    }

    public void addPosition(int pos){
        posOfSwitchBricks.add(pos);
    }

    public int getMovePlayerInt(){
        return player;
    }

    public int getStartPos(){
        return pos;
    }
    public LinkedList<Integer> getPosOfSwitchingBricks(){
        return posOfSwitchBricks;
    }
}
