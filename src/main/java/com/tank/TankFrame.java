package com.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

import static com.tank.DIR.*;

public class TankFrame extends Frame {
    public static final TankFrame INSTANCE = new TankFrame();
    Random random = new Random();
    Tank myTank = new Tank(random.nextInt(GAME_WIDTH),random.nextInt(GAME_HEIGHT),UP,this,Group.good);
    Map<UUID,Tank> tanks = new HashMap<>();
    java.util.List<Bullet> bullets = new LinkedList<>();
    java.util.List<Explode> explodes = new LinkedList<>();

    public void addTank(Tank t) {
        tanks.put(t.getid(), t);
    }

    public Tank findTankByUUID(UUID id) {
        return tanks.get(id);
    }

    static final int GAME_WIDTH=800,GAME_HEIGHT=600;

    public TankFrame() throws HeadlessException {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListner());
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        Color c = g.getColor();
        g.setColor(Color.red);
        g.drawString("bullets:"+bullets.size()+",tanks:"+tanks.size()+",explodes:"+explodes.size(),100,100);
        g.setColor(c);
        for(int i=0;i<bullets.size();i++)
            bullets.get(i).paint(g);

        tanks.values().stream().forEach((e)->e.paint(g));

        for(int i=0;i<bullets.size();i++)
            for(int k=0;k<tanks.size();k++)
                bullets.get(i).collideWith(tanks.get(k));

        for(int i=0;i<explodes.size();i++)
            explodes.get(i).paint(g);
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage==null)
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);

        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    public Tank findbyUUID(UUID id) {
        return tanks.get(id);
    }

    private class MyKeyListner extends KeyAdapter {
        boolean U =false, D=false, L=false, R=false;
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    L = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    R = true;
                    break;
                case KeyEvent.VK_UP:
                    U = true;
                    break;
                case KeyEvent.VK_DOWN:
                    D = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    L = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    R = false;
                    break;
                case KeyEvent.VK_UP:
                    U = false;
                    break;
                case KeyEvent.VK_DOWN:
                    D = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!U&&!D&&!L&&!R) myTank.setMoving(false);
            else {
                if (U) myTank.setDir(UP);
                if (D) myTank.setDir(DOWN);
                if (L) myTank.setDir(LEFT);
                if (R) myTank.setDir(RIGHT);
                myTank.setMoving(true);
                Client.INSTANCE.send(new TankStartMovingMsg(myTank.getid(),myTank.getX(),myTank.getY(),myTank.getDir()));
            }
        }
    }

    public Tank getMainTank(){
        return myTank;
    }
}
