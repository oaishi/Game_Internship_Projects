/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfx;

import GAME.Message;
import java.util.Vector;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author arpita
 */
public class ClientReaderWriterThread implements Runnable {
    
    NetworkUtil nu;
    Thread thr;
    ImageView iv= new ImageView();
    ImageView iv1= new ImageView();
    ImageView iv2= new ImageView();
    ImageView iv3= new ImageView();
    ImageView iv4= new ImageView();
    ImageView iv5= new ImageView();
    double[][] ships1=new double[2][5];
    double[][] ships2=new double[2][4];
    double[][] ships3=new double[2][3];
    double[][] ships4=new double[2][3];
    double[][] ships5=new double[2][2];
    Vector<Rectangle> v=new Vector<>();

    ClientReaderWriterThread(NetworkUtil nu) {
        System.out.println("inside crwt");
        this.nu=nu;
        thr=new Thread(this);
        thr.start();
        
    }
    public void run()
    {
        
        if(nu.change==0)
        {
            Platform.runLater(()->
            {
                ProjectFX.login();
            });
        }
        Object o=nu.read();
        String str=(String)o;
        if(str.equals("connected"))
        {
            Platform.runLater(()->
            {
                ProjectFX.deploy(iv,iv1,iv2,iv3,iv4,iv5,ships1,ships2,ships3,ships4,ships5);
            });
        }
//        o=nu.read();
//        System.out.println(o);
//        str=(String)o;
//        System.out.println(str);
//        if(str.equals("next screen"))
//        {
//            Platform.runLater(()->
//            {
//                ProjectFX.play(iv,ships1,ships2);
//            });
//        }
        //new booleanread();
        while(true){
        String str4= (String)nu.read();
        if(str4.equals("true"))
        {
            System.out.println("Your turn");
            Platform.runLater(()->
            {
                ProjectFX.play(iv1,iv2,iv3,iv4,iv5,v);
            });
        }
        else if(str4.equals("false"))
        {
            Message msg=(Message)nu.read();
            System.out.println(msg.c[0][0]+" "+msg.c[0][1]+"type: "+msg.type);
            Platform.runLater(()->
            {
                ProjectFX.upgrade(msg.c,msg.type,iv1,iv2,iv3,iv4,iv5,v);
            });
            
        }
        else if(str4.equals("you win"))
        {
            
            Platform.runLater(()->
            {
                ProjectFX.youWin();
            });
        }
        else if(str4.equals("you lose"))
        {
            Platform.runLater(()->
            {
                ProjectFX.gameOver();
            });
            
        }
        }
    }
}
