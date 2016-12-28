/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gierka;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Arek
 */
public class PressListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
           Plansza frame=(Plansza)event.getSource();
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ESCAPE:
                frame.zakoncz();
                return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
