package TankGame.GameObjects;

import TankGame.GameSources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Hearts  {
    private int lifeCount;
    private BufferedImage img;

    Hearts(int x){
        this.lifeCount = x;
        this.img = GameSources.hearts[3];
    }

    public void checkHearts(int health){
        if(health <= 1 && lifeCount!=0){
            lifeCount--;
        }

        this.img = GameSources.hearts[lifeCount];
    }

    public int getLifeCount(){
        return this.lifeCount;
    }

    public void render(Graphics g2d, int x, int y){
        g2d.drawImage(img, x-10, y - 30, null);
    }
}
