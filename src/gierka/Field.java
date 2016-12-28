/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gierka;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Arek
 */
public class Field  extends JPanel
{
    int wlasciciel;
    Color color;
    Color []tablicaKolorow={Color.RED, Color.GREEN,Color.BLUE,Color.YELLOW,};
    Plansza plansza;
        public Field(int y, int x, Plansza plansza)
        {
            this.plansza=plansza;
            setBorder(BorderFactory.createLineBorder(Color.black));
            wlasciciel=-1;
            MyszkaListener listener= new MyszkaListener(y,x,this.plansza);
            addMouseListener(listener);
        }
        public void setWlasciciel(int i)
        {
            wlasciciel=i;
            if(i!=-1)
                 color=tablicaKolorow[i];
            else
                color=Color.WHITE;
        }

        public void setKolor(Color kolor)
        {
            this.color=kolor;
        }
        public void jakasFUnkcja()
        {
            
        }
        public void pokoloruj()
        {
            setBackground(color);
        }
         public int getWlasciciel()
        {
            return wlasciciel;
        }   
         public void kolorujTymczasowo(Color kolor)
         {
             setBackground(kolor);
         }
}



