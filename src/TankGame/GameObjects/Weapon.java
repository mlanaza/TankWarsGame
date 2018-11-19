package TankGame.GameObjects;

import TankGame.GameWorld;
import TankGame.TileSet;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Weapon {

    GameWorld game;
    BufferedImage wpn;
    Boolean IntersectsWall, IntersectsTank;
    Tank t1;
    int x, y, vx, vy, angle;
    TileSet tile;

    abstract void checkWalls();
    abstract public void checkHit(Tank t2);
    abstract Rectangle getRectangle();
    abstract public boolean getInterWall();
    abstract public boolean getInterTank();
    abstract public void render(Graphics g);

    public void update(){
        this.IntersectsWall = false;
        this.IntersectsTank = false;
        checkWalls();

        vx = (int) Math.round(6 * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(6 * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
    }

}
