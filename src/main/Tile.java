package main;

/**
 *
 * Created by Andréas Appelqvist on 2016-09-22.
 *
 */
public class Tile {
    int currentColor; // 0=no color -1 = white(AI) 1 = black(player)

    public Tile(){
        currentColor = 0;
    }

    public void setCurrentTileInt(int playerColor){
        this.currentColor = playerColor;
    }

    public void setBlack(){
        this.currentColor = 1;
    }

    public void setWhite(){
        this.currentColor = -1;
    }

    public int getCurrentTileInt(){
        return currentColor;
    }

    public boolean isBlack(){
        if(currentColor == 1){
            return true;
        }
        return false;
    }

    public boolean isWhite(){
        if(currentColor == -1){
            return true;
        }
        return false;
    }

    public boolean isEmpty(){
        if(currentColor == 0){
            return  true;
        }
        return false;
    }
}
