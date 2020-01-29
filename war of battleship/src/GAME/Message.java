/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GAME;

import java.io.Serializable;

/**
 *
 * @author arpita
 */
public class Message implements Serializable{
   public double[][] c;
   public int type;
    public Message(double[][] c, int type)
    {
        this.c=c;
        this.type=type;
    }
    
}
