/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfx;

import static projectfx.ProjectFX.nu;

/**
 *
 * @author arpita
 */
public class booleanread implements Runnable{
    Thread t;

    public booleanread() {
        t= new Thread(this);
        t.start();
    }
    
    
    @Override
    public void run() {
        while(true){
        String str=(String)nu.read();
        System.out.println("**"+str);
        if(str.equals("true"))
        {
            ProjectFX.isMyTurn=true;
        }
        else if(str.equals("false"))
        {
            ProjectFX.isMyTurn=false;
            ProjectFX.c1=(double[][])nu.read();
        }
    // System.out.println(ProjectFX.isMyTurn);
        }
    }
    
}
