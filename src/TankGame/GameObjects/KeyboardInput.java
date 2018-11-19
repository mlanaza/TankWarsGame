package TankGame.GameObjects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardInput implements KeyListener {
    private Tank t1;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;

    public KeyboardInput(Tank t1, int up, int down, int left, int right, int shoot) {
        this.t1 = t1;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();System.out.println(key);
        if(key == up){
            this.t1.setUpPressed(true);
        } else if(key == down){
            this.t1.setDownPressed(true);
        } else if(key == right){
            this.t1.setRightPressed(true);
        } else if(key == left){
            this.t1.setLeftPressed(true);
        } else if(key == shoot){

            this.t1.setShootPressed(true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == up){
            this.t1.setUpPressed(false);
        }
        if(key == down){
            this.t1.setDownPressed(false);
        }
        if(key == right){
            this.t1.setRightPressed(false);
        }
        if(key == left){
            this.t1.setLeftPressed(false);
        }
        if(key == shoot){
            this.t1.setShootPressed(false);
        }

    }
}
