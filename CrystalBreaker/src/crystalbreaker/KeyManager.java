/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbreaker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyManager Class
 * @author Luis Felipe Alvarez Sanchez A01194173
 * 4 Feb 2019
 */
public class KeyManager implements KeyListener{
    //Flags to move the player
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    
    private boolean keys[]; // to store all the flags for every key
    /**
     * KeyManager Constructor
     */
    public KeyManager(){
        keys = new boolean[256];
    }
    /**
     * keyTyped method
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e){
    }
    /**
     * keyPressed method
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //keys[e.getKeyCode()] = false;
    }
    /**
     * keyReleased method
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    /**
     * Sets a boolean to the key array
     * @param key
     * @param checker 
     */
    public void keyCheck(int key, boolean checker){
        keys[key] = checker;
    }
    /**
     * to enable or disable moves on every tick
     */
    public void tick(){
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }
}

   