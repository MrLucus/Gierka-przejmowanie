/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gierka;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Arek
 */
public class MyszkaListener implements MouseListener{

    Plansza plansza;
    int Y, X;
    public MyszkaListener(int y, int x,Plansza plansza)
    {
        this.plansza=plansza;
        this.Y=y;
        this.X=x;

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent event) {
        if(plansza.pola[Y][X].getWlasciciel()==plansza.aktualnyGracz)
        {
            plansza.przenoszony(Y,X);
        }
             
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        plansza.puszczony();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        plansza.wszedlX=X;
        plansza.wszedlY=Y;
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}
