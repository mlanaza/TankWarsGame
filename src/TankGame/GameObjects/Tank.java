package TankGame.GameObjects;

import TankGame.CollisionEvents;
import TankGame.GameSources;
import TankGame.GameWorld;
import TankGame.TileSet;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tank {
    public int x;
    public int y;
    private int vx;
    private int vy;
    private int angle;
    private int rocketCount,flag,prevX, prevY;
    private GameWorld game;
    public ArrayList<Weapon> weapons;
    private Hearts hearts;

    private final int R = 2;
    private final int ROTATIONSPEED = 4;

    private BufferedImage img;
    private BufferedImage[] health;
    private int healthLevel;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    private boolean IntersectsWall;
    private boolean enableRocket;
    private boolean DEAD = false;
    private boolean flag_status = true;



    public Tank(int x, int y, int vx, int vy, int angle, BufferedImage img, GameWorld game, int flag) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.game = game;
        this.flag = flag;
        this.health = GameSources.healthBar;
        this.healthLevel = health.length-1;
        this.hearts = new Hearts(3);
        this.enableRocket = false;
        this.weapons = new ArrayList<>();


    }

    public void setUpPressed(boolean b){
        this.UpPressed = b;
    }

    public void setDownPressed(boolean b){
        this.DownPressed = b;
    }

    public void setRightPressed(boolean b){
        this.RightPressed = b;
    }

    public void setLeftPressed(boolean b){
        this.LeftPressed = b;
    }

    public void setShootPressed(boolean b){
        this.ShootPressed = b;
    }

    public void setIntersects(boolean b ) { this.IntersectsWall = b;}


    public void tick(){
        if(this.UpPressed) {
            this.moveForwards();
        }
        if(this.DownPressed) {
            this.moveBackwards();
        }
        if(this.LeftPressed) {
            this.rotateLeft();
        }
        if(this.RightPressed) {
            this.rotateRight();
        }
        if(this.ShootPressed) {
            this.Shoot();

        }

        this.prevX = this.x;
        this.prevY = this.y;
    }


    private void checkBounds(){
        ArrayList<TileSet> tileMap = this.game.getMap().getTileArray();
        for(int i = 0; i<tileMap.size(); i++ ){
            TileSet tile = tileMap.get(i);
            int val = tile.getVal();
            CollisionEvents c = new CollisionEvents(this.getRectangle(), tile.getRectangle());
            c.checkIntersect();
            this.setIntersects(c.getIntersect());
            int col = tile.getCol();
            int row = tile.getRow();

            if(val == 1 || val == 2 || val==3){
                if(this.IntersectsWall) break;
            } else if(val == 7){
                if(this.IntersectsWall){
                    tileMap.remove(i);
                    this.enableRocket = true;
                    this.rocketCount = 5;
                    this.game.getMap().setTileNum(row, col, 0);
                    break;
                }
            } else if((flag != val) && (val!=0) ){
                if(this.IntersectsWall){
                    tileMap.remove(i);
                    this.flag_status = false;
                    this.game.getMap().setTileNum(row, col, 0);
                    break;
                }
            }
        }
    }


    public Rectangle getRectangle(){
        return new Rectangle(this.x, this.y, 45, 45);
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }


    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= (vx)/2;
        y -= (vy)/2;
        checkBounds();

        if(this.IntersectsWall){
            x = this.prevX;
            y = this.prevY;
            this.setIntersects(false);
        }
    }


    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += (vx)/2;
        y += (vy)/2;
        checkBounds();

        if(this.IntersectsWall){
            x = this.prevX;
            y = this.prevY;
            this.setIntersects(false);
        }
    }


    private void Shoot(){
        if(enableRocket && rocketCount!=0){
            this.weapons.add(new Rocket(GameSources.rocket,x,y,vx,vy,angle,this, game));
            rocketCount--;
        } else {
            this.enableRocket = false;
            this.weapons.add(new Bullet(GameSources.bullet,x,y,vx,vy,angle,this, game));
        }
        this.setShootPressed(false);

    }


    public void healthCheck(Weapon wpn){
        int i = 1;
        if(wpn.getClass() == Rocket.class){
            i = 2;
        }

        if(healthLevel>1){
            healthLevel -= i;
            if(healthLevel<0){
                healthLevel = 0;
                checkHearts();
            }
        } else {
            checkHearts();
        }


    }


    private void checkHearts(){
        hearts.checkHearts(healthLevel);
        if(hearts.getLifeCount()>0){
            healthLevel = health.length-1;
        } else {
            this.DEAD = true;
        }
    }


    public void render(Graphics g){
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
        g2d.drawImage(this.health[this.healthLevel], this.x - 5, this.y+30, null);
        hearts.render(g2d, x, y);

    }


   public boolean ifDEAD(){
        return this.DEAD;
   }
   public boolean ifGotFlag(){return this.flag_status;}

}
