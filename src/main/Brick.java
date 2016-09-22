package main;

/**
 *
 * Created by Andréas Appelqvist on 2016-09-22.
 *
 */
public class Brick {
    int currentColor; // 0=no color -1 = white 1 = white

    public Brick(){
        currentColor = 0;
    }

    public void setWhite(){
        currentColor = -1;
    }

    public void setBlack(){
        currentColor = 1;
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
}
