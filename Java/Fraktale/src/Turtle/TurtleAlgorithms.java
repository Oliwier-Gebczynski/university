package Turtle;


import turtlePck.TurtleGraphicsWindow;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZTILabPI
 */
public class TurtleAlgorithms extends TurtleGraphicsWindow{
    
    public void positioningTurtle(int angle){
        right(angle);
    }

     
    public void sierpinski(int bok, int minBok ){
         if (bok<minBok) {
            return;
        }
          for(int i=1;i<4;i++){
              sierpinski(bok/2, minBok);
              forward(bok);
              right(120);
          }
      }

    
  
public void snowFlake(int n, int bok){
    if (n==0){
        forward(bok);
        return;
    }
    snowFlake(n-1, bok/3);
    right(60);
    snowFlake(n-1, bok/3);
    left(120);
    snowFlake(n-1, bok/3);
    right(60);
    snowFlake(n-1, bok/3);

}


public void callSnowFlake(int n, int bok){
    for(int i=1; i<4; i++){
      snowFlake(n, bok);
      left(120);
    }
}


    public void trojkaty(int bok, int min_bok) {
        if (bok < min_bok) {
            return;
        }

        for (int i = 0; i < 3; i++) {  // duzy trojkat
            forward(bok);
            left(120);

            for (int j = 0; j < 3; j++) { // male trojkaty w duzym
                forward(bok / 3);
                left(120);
                trojkaty(bok / 3, min_bok);
            }
        }
    }

    public void szesciokat(int bok, int min_bok) {
        if (bok < min_bok) {
            return;
        }

        for (int i = 0; i < 6; i++){
            forward(bok/5);
            right(120);
            szesciokat(bok/2, min_bok);
            left(120);
            forward(4*(bok/5));
            left(60);
        }
    }

    public void drzewko(int bok, int min_bok) {
        if (bok < min_bok) {
            return;
        }
        forward(bok);
        left(20);
        drzewko(3*(bok/4), min_bok);
        right(40);
        drzewko(3*(bok/4), min_bok);
        left(20);
        back(bok);
    }

    public void kwadraty(int bok, int min_bok) {
        if (bok < min_bok) {
            return;
        }

        for (int i = 0; i < 4; i++){
            forward(bok/4);
            right(90);
            kwadraty(bok/2, min_bok);
            left(90);
            forward(3*(bok/4));
            left(90);
        }
    }

    public void katnakat(int bok, int min_bok) {
        if (bok < min_bok) {
            return;
        }

        for (int i = 0; i < 6; i++) {
            forward(bok);
            right(120);
            katnakat(bok / 2, min_bok);
            left(120);
            left(60);
        }
    }

    public void kart(int bok, int min_bok) {
        if (bok < min_bok) {
            return;
        }

        right(60);
        kart(bok/2, min_bok);
        forward(bok);
        left(120);
        forward(bok);
        left(60);
    }

}
