/*
Anne Lanaza
CSC413
Tank Game
=two player fight and try to kill or steal the other's flag.

 */

package TankGame;

import TankGame.GameObjects.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameWorld extends JPanel {
    public static JFrame gameFrame;
    public static GameWorld game;
    public static final String TITLE = "Tank Wars Game";
    public static final int WIDTH= 1500;
    public static final int HEIGHT = 1000;

    private GameMap map;
    private BufferedImage world, leftScrn, rightScrn;
    private Image miniMap;
    private Graphics2D buffer;
    private Tank t1, t2;
    private static boolean running = true;
    private static boolean gameOver = false;
    private boolean t1_win = false;
    private boolean t2_win= false;


    public static void main(String[] args) {
        Thread x;
        GameWorld game = new GameWorld();
        game.init();
        try {
            while (running) {
                game.tick();
                game.repaint();
                System.out.println(game.t1);
                Thread.sleep(1000 / 144);
            }

        } catch (InterruptedException ignored) {

        }
    }


    private void init() {
        GameSources.init();
        this.gameFrame = new JFrame(TITLE);
        this.world = new BufferedImage(GameWorld.WIDTH, GameWorld.HEIGHT, BufferedImage.TYPE_INT_RGB);

        this.t1 = new Tank(100, 100, 0, 0, 0, GameSources.tank1, this, 5);
        this.t2 = new Tank(1350, 900, 0, 0, 0, GameSources.tank2, this, 4);
        KeyboardInput tc1 = new KeyboardInput(t1,KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D , KeyEvent.VK_SPACE);
        KeyboardInput tc2 = new KeyboardInput(t2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
        this.map = new GameMap(GameSources.br,  GameSources.brWall, GameSources.star, GameSources.flag1,
                GameSources.flag2, GameSources.uWall);

        JOptionPane.showMessageDialog(gameFrame, "What you are about to play is a simple tank game and before you do: \n" +
                "- It's a two player game \n" +
                "- Player 1 Controls: WASD to move, Spacebar to shoot \n" +
                "- Player 2 Controls: Arrow Keys to move, Enter to shoot \n" +
                "- You either win by stealing the flag or killing your enemy(or in some cases, you both lose) \n" +
                "- If you get a powerup, you get 5 rockets to use \n" +
                "- Lastly, HAVE FUN ^-^");

        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.add(this);
        this.gameFrame.addKeyListener(tc1);
        this.gameFrame.addKeyListener(tc2);

        this.gameFrame.setSize(game.WIDTH/2 + 50, game.HEIGHT/2 + 100);
        this.gameFrame.setResizable(false);
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setVisible(true);

    }


    void tick(){
        this.t1.tick();
        this.t2.tick();
        for(int i = 0; i<this.t1.weapons.size(); i++){
            Weapon b = this.t1.weapons.get(i);
            b.update();
            b.checkHit(t2);

            if(b.getInterWall() || b.getInterTank()){
                this.t1.weapons.remove(i);
            }
        }

        for(int i = 0; i<this.t2.weapons.size(); i++){
            Weapon b = this.t2.weapons.get(i);
            b.update();
            b.checkHit(this.t1);

            if(b.getInterWall() || b.getInterTank()){
                this.t2.weapons.remove(i);
            }
        }

        if(t1.ifDEAD() || !t2.ifGotFlag()){
            gameOver = true;
            t2_win = true;
        }

        if(t2.ifDEAD() || !t1.ifGotFlag()){
            gameOver = true;
            t1_win = true;
        }

    }


    public GameMap getMap(){
        return this.map;
    }


    int scaleDownX(int x){
        x = x - 150;
        if(x <= 0){
            x = 0;
        } else if ( x >= 1100){
            x = 1100;
        }
        return x;
    }


    int scaleDownY(int y){
        y = y - 350;

        if(y <= 0){
            y = 0;
        } else if (y >= 400) {
            y = 400;
        }
        return y;
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);
        buffer.drawImage(GameSources.bg, 0,0 , GameWorld.WIDTH, GameWorld.HEIGHT, null);
        this.map.render(buffer);
        miniMap = world.getScaledInstance(200, 200, 2);

        if(gameOver){
            buffer.drawImage(GameSources.tank1_txt, 50, 50, this);
            buffer.drawImage(GameSources.tank2_txt, 500, 50, this);

            if(t2_win){
                buffer.drawImage(GameSources.lose, 0, 160, this);
            } else {
                buffer.drawImage(GameSources.win, 0,160, this);
            }

            if(t1_win){
                buffer.drawImage(GameSources.lose, 400, 160, this);
            } else {
                buffer.drawImage(GameSources.win, 410, 160, this);
            }

            leftScrn = world.getSubimage(0,0, 400, 600);
            rightScrn = world.getSubimage(400,0 , 400, 600);
        } else {


            this.t1.render(buffer);
            this.t2.render(buffer);

            for(int i = 0; i<this.t1.weapons.size(); i++){
                this.t1.weapons.get(i).render(buffer);
            }
            for(int i = 0; i<this.t2.weapons.size(); i++){
                this.t2.weapons.get(i).render(buffer);
            }
            leftScrn = world.getSubimage(scaleDownX(t1.x), scaleDownY(t1.y), 400, 600);
            rightScrn = world.getSubimage(scaleDownX(t2.x), scaleDownY(t2.y),  400, 600);
        }

        g2.drawImage(world, 0, 0 ,this);
        g2.drawImage(leftScrn, 0,0 , this);
        g2.drawImage(rightScrn, 400, 0 ,this);
        g2.setColor(SystemColor.ORANGE);
        g2.fillRect(400, 0, 20, 600);
        if(!gameOver){
            g2.drawImage(miniMap, 310,  0, this);
        }
    }

}
