package TankGame.GameObjects;

import TankGame.CollisionEvents;
import TankGame.GameWorld;
import TankGame.GameSources;
import TankGame.TileSet;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Rocket extends Weapon {

    public Rocket(BufferedImage wpn, int x, int y, int vx, int vy, int angle, Tank t1, GameWorld game){
        this.wpn = wpn;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
        this.t1 = t1;
        this.game = game;
    }

    public void checkWalls(){
        ArrayList<TileSet> tileMap = this.game.getMap().getTileArray();
        for(int i = 0; i<tileMap.size(); i++ ){
            tile  = tileMap.get(i);

            CollisionEvents c = new CollisionEvents(this.getRectangle(), tile.getRectangle());
            c.checkIntersect();
            this.IntersectsWall = c.getIntersect();

            if(this.IntersectsWall){

                int col = tile.getCol();
                int row = tile.getRow();
                int num = this.game.getMap().getTileNum(row,col);

                switch(num){
                    case 1:
                    case 3:
                        this.game.getMap().setTileNum(row,col, 0);
                        tileMap.remove(i);
                        break;
                }
                break;
            }
        }
    }

    public void checkHit(Tank t2){
        CollisionEvents c = new CollisionEvents(this.getRectangle(), t2.getRectangle());
        c.checkIntersect();
        if(c.getIntersect()){
            this.IntersectsTank = true;
            t2.healthCheck(this);
        } else {
            this.IntersectsTank = false;
        }
    }

    public Rectangle getRectangle(){
        return new Rectangle(this.x, this.y, this.wpn.getWidth(), this.wpn.getHeight());
    }

    public void render(Graphics g){
        AffineTransform bullet = AffineTransform.getTranslateInstance(x,y);
        bullet.rotate(Math.toRadians(angle), 25 / 2, 25 /2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.wpn, bullet, null);

    }


    public boolean getInterWall(){
        return this.IntersectsWall;
    }

    public boolean getInterTank(){
        return this.IntersectsTank;
    }

}
