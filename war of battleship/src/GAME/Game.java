/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GAME;

import java.io.Serializable;
import projectfx.NetworkUtil;

/**
 *
 * @author arpita
 */
public class Game implements Serializable{
   
    public double[][] ships1,ships2,ships3,ships4,ships5;
    //String playerName;
   public Game( double[][] ships1, double[][] ships2,double[][] ships3,double[][] ships4,double[][] ships5)
    {
       
        this.ships1=ships1;
        this.ships2=ships2;
        this.ships3=ships3;
        this.ships4=ships4;
        this.ships5=ships5;
       // this.playerName=playerName;
        
    }
    
}
