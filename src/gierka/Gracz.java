/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gierka;

import java.awt.Color;

/**
 *
 * @author Arek
 */
public class Gracz {
    
    private String nazwa;
    private  int punkty;
    private Color kolor;
    public Gracz(String nazwaGracza, Color kolorGracza)
    {
        this.nazwa=nazwaGracza;
        this.kolor=kolorGracza;
    }
    public String getNazwa()
    {
        return this.nazwa;
    }
    public void setPunkty(int ilosc)
    {
        this.punkty=ilosc;
    }
    public int getPunkty()
    {
        return this.punkty;
    }
    public Color getColor()
    {
        return kolor;
    }
}
