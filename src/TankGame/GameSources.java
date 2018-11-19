package TankGame;

import sun.misc.IOUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.Object;
import java.net.URL;

public class GameSources {


    public static BufferedImage uWall, tank1, tank2, flag1, flag2, bg, bullet, rocket, tank1_txt, tank2_txt, star;
    public static Image win, lose;
    public static BufferedImage[] brWall = new BufferedImage[2];
    public static BufferedImage[] healthBar = new BufferedImage[6];
    public static BufferedImage[] hearts = new BufferedImage[4];
    public static BufferedReader br;

    public static void init(){
        try {
            brWall[0] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/brick2.png"));
            brWall[1] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/brick2.5.png"));
            healthBar[0] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/bar0.png"));
            healthBar[1] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/bar1.png"));
            healthBar[2] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/bar2.png"));
            healthBar[3] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/bar3.png"));
            healthBar[4] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/bar4.png"));
            healthBar[5] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/bar5.png"));
            hearts[0] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/hearts0.png"));
            hearts[1] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/hearts1.png"));
            hearts[2] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/hearts2.png"));
            hearts[3] = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/hearts3.png"));

            uWall = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/brick1.png"));
            tank1 = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/t1.png"));
            tank2 = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/t2.png"));
            flag1 = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/f1.png"));
            flag2 = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/f2.png"));
            bg = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/grass.png"));
            bullet = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/bullet.png"));
            rocket = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/BigBullet.png"));
            star = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/star.gif"));
            tank1_txt = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/p1.png"));
            tank2_txt = ImageIO.read(GameWorld.class.getResource("/TankGame/Resources/p2.png"));

            win = new ImageIcon(GameWorld.class.getClass().getResource("/TankGame/Resources/win.gif")).getImage();
            lose = new ImageIcon(GameWorld.class.getClass().getResource("/TankGame/Resources/lose.gif")).getImage();

            InputStream input = GameSources.class.getResourceAsStream("/TankGame/Resources/testmap.txt");
            br = new BufferedReader(new InputStreamReader(input));

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
