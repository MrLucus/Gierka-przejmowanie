
package gierka;

import java.awt.Color;
import java.awt.GridLayout;
import static java.lang.Math.abs;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Arek
 */
public class Plansza extends JFrame {
    
    int sizeX, sizeY;
    Field pola[][];
    MenuGry menu;
    int iloscGraczy;
    int aktualnyGracz;
    String nazwyGraczy[]= new String[4];
     Color []tablicaKolorow={Color.RED, Color.GREEN,Color.BLUE,Color.YELLOW,};
     int przenoszonyY, przenoszonyX;
     int wszedlY,wszedlX;
    public Plansza(int Y, int X, int iloscGraczy, String[] nazwyGraczy, MenuGry menu)
    {
            super();
            this.iloscGraczy=iloscGraczy;
            this.menu=menu;
            for(int i=0;i<iloscGraczy;i++)
            {
                this.nazwyGraczy[i]=nazwyGraczy[i];
            }
            setLayout(new GridLayout(Y,X,1,1));
            setSize(1200,800);
            setVisible(true);
            sizeX=X;
            sizeY=Y;
            this.pola=new Field[sizeY][sizeX];       
            for(int i=0;i<sizeY;i++)
            {
                for(int j=0;j<sizeX;j++)
                {
                    pola[i][j]=new Field(i,j,this);
                    add(pola[i][j]);
                }
            }
            
            pola[Y-1][0].setWlasciciel(0);
            pola[0][X-1].setWlasciciel(1);
            if(iloscGraczy>2)
                pola[0][0].setWlasciciel(2);
            if(iloscGraczy>3)
                pola[Y-1][X-1].setWlasciciel(3); 
         PressListener listener=new PressListener();
         addKeyListener(listener); 
         rysuj();
         aktualnyGracz=0;
         System.out.println("Gracz 0:");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void przenoszony(int y, int x)
    {
        for(int i=y-2;i<=y+2;i++)
        {
            if(i<0 || i>= sizeY)
                continue;
            for(int j=x-2;j<=x+2;j++)
            {
                if(j<0 || j>= sizeX)
                   continue;   
                if(i==y && j==x)
                    continue;
                if(pola[i][j].getWlasciciel()!=-1)
                    continue;
                pola[i][j].kolorujTymczasowo(new Color(188, 0, 0));
            }
        }
        przenoszonyY=y;
        przenoszonyX=x;
    }
    
        public void puszczony()
    {
        int y=wszedlY, x=wszedlX;
        int a=przenoszonyY, b=przenoszonyX;
        if(pola[y][x].getWlasciciel()==-1 && abs(y-a)<=2 && abs(x-b)<=2)
        {
            pola[y][x].setWlasciciel(aktualnyGracz);
            for(int i=y-1;i<=y+1;i++)
            {
                if(i<0 || i>=sizeY)
                    continue;
                for(int j=x-1;j<=x+1;j++)
                {
                    if(j<0 || j>=sizeX)
                        continue;
                    if(pola[i][j].getWlasciciel()!=-1 && pola[i][j].getWlasciciel()!=aktualnyGracz)
                        pola[i][j].setWlasciciel(aktualnyGracz);
                }
            }
            if(abs(y-a)==2 || abs(x-b)==2)
            {
                pola[a][b].setWlasciciel(-1);
            }
            nowaTura();
        }
        rysuj();
        przenoszonyY=-10;
        przenoszonyX=-10;
    }
        private boolean czyJestRuch()
        {
              boolean jestRuch=false;
            for(int k=0;k<sizeY;k++)
            {
                for(int j=0;j<sizeX;j++)
                {
                    if(pola[k][j].getWlasciciel()!=aktualnyGracz)
                        continue;
                    for(int l=k-2;l<=k+2;l++)
                    {
                         if(l<0 || l>=sizeY)
                             continue;
                        for(int m=j-2;m<=j+2;m++)
                        {
                             if(m<0 || m>=sizeY || l==k && m==j)
                              continue;                    
                             if(pola[l][m].getWlasciciel()==-1)
                                 jestRuch=true;
                        }
                    }
                }
            }      
            return jestRuch;
        }
        private void nowaTura()
        {
            int []iloscPunktow= new int[iloscGraczy];  
            for(int i=0;i<iloscGraczy;i++)
                iloscPunktow[i]=0;
            for(int i=0;i<sizeY;i++)
            {
                for(int j=0;j<sizeX;j++)
                {
                    if(pola[i][j].getWlasciciel()!=-1)
                    iloscPunktow[pola[i][j].getWlasciciel()]++;
                }
            }
            int i=0;
            boolean jestRuch;
            do
            {
                i++;
             aktualnyGracz++;
            if(aktualnyGracz==iloscGraczy)
                aktualnyGracz=0;
             jestRuch=czyJestRuch();
            }while(iloscPunktow[aktualnyGracz]==0 && i!=iloscGraczy);
            if(i==iloscGraczy || jestRuch==false)
            {
                rysuj();
                        JOptionPane.showMessageDialog(null,"Wygral gracz "+nazwyGraczy[aktualnyGracz]+", gratulacje!","Zwycięstwo",JOptionPane.INFORMATION_MESSAGE);
                zakoncz();
            }
                    else
            System.out.println("Gracz "+ nazwyGraczy[aktualnyGracz]+":");
            
        }
    public void rysuj()
    {
        for(int i=0;i<sizeY;i++)
        {
            for(int j=0;j<sizeX;j++)
            {
                pola[i][j].pokoloruj();
            }
        }
    }
    public void wykonajRuch()
    {
        
    }
    public void zakoncz()
    {
        menu.wyswietl();
        dispose();
    }

}
