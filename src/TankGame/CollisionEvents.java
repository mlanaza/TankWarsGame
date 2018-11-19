package TankGame;

import java.awt.*;

public class CollisionEvents {

    Rectangle gameObj1, gameObj2;
    boolean objIntersects;

    public CollisionEvents(Rectangle sq1, Rectangle sq2) {
        this.gameObj1 = sq1;
        this.gameObj2 = sq2;
        this.objIntersects = false;
    }

    public void checkIntersect(){
        if(this.gameObj1.intersects(gameObj2)){
            this.objIntersects = true;
        }
    }

    public boolean getIntersect(){
        return this.objIntersects;
    }


}
