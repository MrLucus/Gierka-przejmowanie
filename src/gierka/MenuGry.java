/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gierka;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Arek
 */


public class MenuGry extends JFrame implements ActionListener{
    
    int iloscGraczy=2;   
    JLabel iloscGraczyL,rozmiarPlanszyL, gracz1L,gracz2L, bladDanych;
    JLabel[] graczeL=new JLabel[4];
    JTextField[] graczeT=new JTextField[4];
    JTextField gracz1T,gracz2T;
    JButton dodajGraczaB,usunGraczaB,rozpocznijGreB;
    ButtonGroup planszaBG;
    JRadioButton malaB,sredniaB,duzaB;
    
    public MenuGry()
    {
        super();
        JFrame mainFrame = new JFrame();
        setSize(1200,800);
        setVisible(true);
        setLayout(null);
        iloscGraczyL=new JLabel("Gracze");
        rozmiarPlanszyL=new JLabel("Wybierz rozmiar planszy:");
        bladDanych=new JLabel();
        bladDanych.setBounds(100,530,500,40);
        for(int i=0;i<4;i++)
        {
            graczeL[i]=new JLabel("Gracz "+(i+1));
            graczeT[i]=new JTextField(30);
            graczeL[i].setBounds(100,110+100*i,90,40);
            add(graczeL[i]);
            graczeT[i].setBounds(170,110+100*i,90,40);
            add(graczeT[i]);
            if(i<iloscGraczy)
              continue;
            graczeL[i].setVisible(false);
            graczeT[i].setVisible(false);                          
        }
        dodajGraczaB=new JButton("Nowy gracz");
        rozpocznijGreB=new JButton("Start Gry");
        iloscGraczyL.setBounds(140,50,90,30);
        rozmiarPlanszyL.setBounds(600,90,190,30);
        dodajGraczaB.setBounds(120,610,140,35);
        rozpocznijGreB.setBounds(550,610,170,30);
        add(bladDanych);
        bladDanych.setVisible(true);
        add(iloscGraczyL);
        add(rozmiarPlanszyL);
        add(rozmiarPlanszyL);
        add(dodajGraczaB);
        add(rozpocznijGreB);
        dodajGraczaB.addActionListener(this);
        rozpocznijGreB.addActionListener(this);
        planszaBG=new ButtonGroup();
        malaB=new JRadioButton("6x6");
        sredniaB=new JRadioButton("8x8",true);
        duzaB=new JRadioButton("10x10");
        planszaBG.add(malaB);
        planszaBG.add(sredniaB);
        planszaBG.add(duzaB);
        add(malaB);
        add(sredniaB);
        add(duzaB);
        malaB.setBounds(650, 140, 70, 45);
        sredniaB.setBounds(650, 200, 70, 45);
        duzaB.setBounds(650, 260, 70, 45);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void wyswietl()
    {
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
       if(source==dodajGraczaB && iloscGraczy<4 )
        {
            graczeT[iloscGraczy].setVisible(true);
            graczeL[iloscGraczy].setVisible(true);
            iloscGraczy++;         
        }
       if(source==rozpocznijGreB )
       {
           boolean zaczac=true;
           for(int i=0;i<iloscGraczy;i++)
           {
               Pattern pattern=Pattern.compile("a*b?");
               if(graczeT[i].getText().length()==0) 
               {
                   zaczac=false;
                   graczeT[i].setBorder(BorderFactory.createLineBorder(Color.red));
                   bladDanych.setText("Wpisz nazwy wszystkich uzytkownikow");
               }
               else if( Pattern.matches("([A-Za-z0-9])*",graczeT[i].getText())==false)
               {
                   zaczac=false;
                   graczeT[i].setBorder(BorderFactory.createLineBorder(Color.red));
                   bladDanych.setText("Nazwy użytkowników mogą zawierać tylko łacińskie znaki i cyfry");
               }
               else// Sprawdzanie czy nie powtarzaja sie
               {
                  graczeT[i].setBorder(BorderFactory.createLineBorder(Color.black));
               }
           }
          if(zaczac==true)
          {
              bladDanych.setText("");
                setVisible(false);
                String []nazwyGraczy= new String[iloscGraczy];
                for(int i=0;i<iloscGraczy;i++)
                {
                    nazwyGraczy[i]=graczeT[i].getText();
                }
                if(malaB.isSelected())
                    new Plansza(6,6, iloscGraczy,nazwyGraczy,this);
                else if(sredniaB.isSelected())
                       new Plansza(8,8, iloscGraczy,nazwyGraczy,this);  
                else
                       new Plansza(10,10, iloscGraczy,nazwyGraczy,this);
                     /* EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
                new Plansza(8,8, this);
			}
        });*/

          }
          
       }
    }

}