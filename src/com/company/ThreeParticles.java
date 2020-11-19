package com.company;


import java .awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ThreeParticles{
    
    public static void main(String s[]) {
        JFrame frame = new JFrame("błądzenie przypadkowe");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        Wykres_Three wykres = new Wykres_Three();
        JButton button = new JButton();
        button.setText("START");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                wykres.rysowac=true;
                wykres.repaint();
            }
        });

        panel.add(button);
        panel.setBackground(Color.orange);
        panel.add(wykres);
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}

class Wykres_Three extends JPanel{
    int wielkosc=600;
    int[][] lattice = new int[wielkosc][wielkosc];  // lattice=0 => puste   lattice=1 => cząstka
    boolean rysowac=false;
    public Wykres_Three(){setBorder(BorderFactory.createLineBorder(Color.green, 2));
    for (int i = 250; i<350;i++) {
        for (int j = 250; j<350;j++){
            lattice[i][j] = 1;
        }
    }
    }

    public Dimension getPreferredSize()

    {return new Dimension(wielkosc,wielkosc);}


    void step() {

        for (int counter = 0; counter < wielkosc * wielkosc; counter++) {
            int x_old = (int) (wielkosc * Math.random());
            int y_old = (int) (wielkosc * Math.random());
            if (lattice[x_old][y_old] != 0) {
                int x = x_old;
                int y = y_old;
                double losowa = Math.random();
                if (losowa < 0.25) {
                    x = x + 1;
                    if (x == wielkosc) {
                        x = 0;
                    }

                } else if (losowa < 0.5) {
                    x = x - 1;
                    if (x == -1) {
                        x = wielkosc - 1;
                    }
                } else if (losowa < 0.75) {
                    y = y + 1;
                    if (y == wielkosc) {
                        y = 0;
                    }
                } else {
                    y = y - 1;
                    if (y == -1) {
                        y = wielkosc - 1;
                    }
                }
                if (lattice[x][y] == 0) {
                    lattice[x_old][y_old] = 0;
                    lattice[x][y] = 1;
                }
            } // end if(siec[x_old][y_old]!=0)
        } // end for
    } // end step()





    public void paintComponent(Graphics graf){
        super.paintComponent(graf);
        for (int i = 0; i < wielkosc; i++) {
            for (int j = 0; j < wielkosc; j++) {
                if (lattice[i][j] == 1) {
                    graf.drawRect(i, j, 1, 1);
                }
            }
        }



        if (rysowac) {
            step();
            try {
                Thread.sleep(20);               // sleep for 10 msec
            } catch (InterruptedException t){}
            repaint();
        }
    }
}