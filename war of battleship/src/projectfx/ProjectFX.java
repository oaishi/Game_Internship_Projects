/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfx;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import GAME.Game;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 *
 * @author arpita
 */
public class ProjectFX extends Application {

    static Stage primaryStage;
    static NetworkUtil nu;
    static boolean isMyTurn;
    static double[][] c1;
    static String name;

    Rectangle[][] board() {
        Rectangle[][] rect = new Rectangle[10][10];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                rect[i * 2][j * 2] = new Rectangle(10 + j * 100, 10 + i * 100, 50, 50);
                rect[i * 2][j * 2].setFill(Color.AQUAMARINE);
                rect[i * 2][2 * j + 1] = new Rectangle(60 + j * 100, 10 + i * 100, 50, 50);
                rect[i * 2][2 * j + 1].setFill(Color.BLACK);

            }
            for (int j = 0; j < 5; j++) {

                rect[i * 2 + 1][j * 2] = new Rectangle(10 + j * 100, 60 + i * 100, 50, 50);
                rect[i * 2 + 1][j * 2].setFill(Color.BLACK);
                rect[2 * i + 1][2 * j + 1] = new Rectangle(60 + j * 100, 60 + i * 100, 50, 50);
                rect[2 * i + 1][2 * j + 1].setFill(Color.AQUAMARINE);

            }
        }
        return rect;

    }

    public static void gameOver() {
        Group root = new Group();
        ImageView iv = new ImageView();
        Image img = new Image("game over.png");
        iv.setImage(img);
        root.getChildren().add(iv);
        Scene scene = new Scene(root, 1500, 1500);
        primaryStage.setScene(scene);
        primaryStage.setTitle(name);
        primaryStage.show();
    }

    public static void Wait() {
        Group root = new Group();
        ImageView iv = new ImageView();
        Image img = new Image("wait.png");
        iv.setImage(img);
        root.getChildren().add(iv);
        Scene scene = new Scene(root, 1500, 1500);
        primaryStage.setScene(scene);
        primaryStage.setTitle(name);
        primaryStage.show();
    }

    public static void youWin() {
        Group root = new Group();
        ImageView iv = new ImageView();
        Image img = new Image("you win.jpg");
        iv.setImage(img);
        root.getChildren().add(iv);
        Scene scene = new Scene(root, 1500, 1500);
        primaryStage.setScene(scene);
        primaryStage.setTitle(name);
        primaryStage.show();
    }

    public static void play(ImageView iv1, ImageView iv2, ImageView iv3, ImageView iv4, ImageView iv5, Vector<Rectangle> v) {

        System.out.println("your turn");
        Group root = new Group();

        ImageView ivb = new ImageView();
        Image imgb = new Image("background.gif");
        ivb.setImage(imgb);
        ivb.setFitHeight(1500);
        ivb.setFitWidth(1500);
        root.getChildren().add(ivb);

        Text text = new Text(500, 10, "Your Turn");
        text.setFont(Font.font("Verdana", FontPosture.REGULAR, 40));
        root.getChildren().add(text);

        ImageView iv = new ImageView();
        Image img = new Image("board.png");
        iv.setImage(img);
        iv.setLayoutX(50);
        iv.setLayoutY(100);
        iv.setFitHeight(600);
        iv.setFitWidth(600);
        root.getChildren().add(iv);

        ImageView iv0 = new ImageView();
        Image img2 = new Image("board.png");
        iv0.setImage(img2);
        iv0.setLayoutX(750);
        iv0.setLayoutY(100);
        iv0.setFitHeight(600);
        iv0.setFitWidth(600);
        root.getChildren().add(iv0);

        root.getChildren().addAll(iv1, iv2, iv3, iv4, iv5);

        for (int i = 0; i < v.size(); i++) {
            Rectangle rect = v.elementAt(i);
            root.getChildren().add(rect);
        }

        iv0.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                double[][] c = new double[1][2];
                c[0][0] = e.getSceneX() - 700;
                c[0][1] = e.getSceneY();
                System.out.println("mouse clicked: " + c[0][0] + " " + c[0][1]);
                Object obj = (Object) c;
                nu.write(obj);
                int i = (int) (c[0][0] - 50) / 60;
                int j = (int) (c[0][1] - 100) / 60;
                Rectangle rect = new Rectangle(50, 50);
                rect.setX(i * 60.0 + 755);
                rect.setY(j * 60 + 105);
                rect.setFill(Color.BLACK);
                root.getChildren().add(rect);
                v.add(rect);

            }
        });

        Scene scene = new Scene(root, 1500, 1500);
        root.getChildren().remove(text);
        primaryStage.setScene(scene);
        primaryStage.setTitle(name);
        primaryStage.show();

    }

    public static void upgrade(double[][] c, int type, ImageView iv1, ImageView iv2, ImageView iv3, ImageView iv4, ImageView iv5, Vector<Rectangle> v) {
        Group root = new Group();
        ImageView ivb = new ImageView();
        Image imgb = new Image("background.gif");
        ivb.setImage(imgb);
        ivb.setFitHeight(1500);
        ivb.setFitWidth(1500);
        root.getChildren().add(ivb);
        ImageView iv = new ImageView();
        Image img = new Image("board.png");
        iv.setImage(img);
        iv.setLayoutX(50);
        iv.setLayoutY(100);
        iv.setFitHeight(600);
        iv.setFitWidth(600);
        root.getChildren().add(iv);

        ImageView iv0 = new ImageView();
        Image img2 = new Image("board.png");
        iv0.setImage(img2);
        iv0.setLayoutX(750);
        iv0.setLayoutY(100);
        iv0.setFitHeight(600);
        iv0.setFitWidth(600);
        root.getChildren().add(iv0);
        root.getChildren().addAll(iv1, iv2, iv3, iv4, iv5);

        ImageView iv_blast = new ImageView();
        Image img3 = new Image("explosion.gif");
        iv_blast.setImage(img3);
        iv_blast.setFitHeight(60);
        iv_blast.setFitWidth(60);
        iv_blast.setVisible(false);
        root.getChildren().add(iv_blast);

        for (int i = 0; i < v.size(); i++) {
            Rectangle rect = v.elementAt(i);
            root.getChildren().add(rect);
        }

        Scene scene = new Scene(root, 1500, 1000);
        if (type == 1) {

            int i, j;
            if (c[0][0] >= 50 && c[0][1] >= 100) {
                i = (int) (c[0][0] - 50) / 60;
                j = (int) (c[0][1] - 100) / 60;
                Rectangle rect = new Rectangle(50, 50);
                rect.setX(i * 60.0 + 55);
                rect.setY(j * 60 + 105);
                rect.setFill(Color.RED);
                //root.getChildren().add(rect);
                v.add(rect);
                root.getChildren().add(rect);
                Timer timer = new Timer();
                iv_blast.setLayoutX(i * 60.0 + 55);
                iv_blast.setLayoutY(j * 60 + 105);
                iv_blast.setVisible(true);
                TimerTask task = new TimerTask() {
                    public void run() {
                        System.out.println("test");

                        //root.getChildren().add(iv_blast);
                        iv_blast.setVisible(false);
                        timer.cancel();
                    }

                };
                timer.schedule(task, 3000);
                

            }
//         else 
//         {
//              i=0;
//              j=0;
//         }
//         rect[i][j].setFill(Color.RED);

        } else if (type == 2) {
            int i, j;
            if (c[0][0] >= 50 && c[0][1] >= 100) {
                i = (int) (c[0][0] - 50) / 60;
                j = (int) (c[0][1] - 100) / 60;
                Rectangle rect = new Rectangle(50, 50);
                rect.setX(i * 60.0 + 55);
                rect.setY(j * 60 + 105);
                rect.setFill(Color.ORANGE);
                root.getChildren().add(rect);
                v.add(rect);

            }

//         int i,j;
//         if(c[0][0]>=10&&c[0][1]>=10)
//         { i=(int)(c[0][0]-10)/50;
//           j=(int)(c[0][1]-10)/50;
//         }
//         else 
//         {
//              i=0;
//              j=0;
//         }
//         rect[i][j].setFill(Color.ORANGE);
        }
        primaryStage.setScene(scene);
        primaryStage.setTitle(name);
        primaryStage.show();

    }

    public static void login() {
        Group root = new Group();
        ImageView iv = new ImageView();
        Image img = new Image("background.jpg");
        iv.setImage(img);
        iv.setFitHeight(1000);
        iv.setFitWidth(1000);
        ImageView iv1 = new ImageView();
        Image img1 = new Image("logo.png");
        iv1.setImage(img1);
        iv1.setFitHeight(200);
        iv1.setFitWidth(200);
        iv1.setLayoutX(100);
        iv1.setLayoutY(100);

        //  root.getChildren().addAll(iv,iv1);
        TextField tf = new TextField();
        tf.setLayoutX(70);
        tf.setLayoutY(680);
        tf.setStyle("-fx-control-inner-background:grey");
        tf.setPrefWidth(220);
        Text t = new Text(70, 670, "Enter you username.");
        t.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
        t.setStyle("-fx-fill:orange");
        //t.setStyle("-fx-stroke:black");
        //t.setStyle("-fx-stroke-width:1");

        root.getChildren().addAll(iv, iv1, tf, t);
        tf.toFront();
        ScaleTransition st = new ScaleTransition(Duration.millis(3000), iv1);
        st.setToX(1.8);
        st.setToY(1.8);
        st.setCycleCount(Timeline.INDEFINITE);
        st.setAutoReverse(true);
        st.play();

        // AnchorPane.setBottomAnchor(tf, Double.NaN);
        /* ListView list= new ListView();
        list.setLayoutX(400);
        list.setPrefHeight(600);*/
        tf.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String str = tf.getText();
                name=str;
                Object o = (Object) str;
                nu.write(o);
                nu.change = 1;
                System.out.println(str);
                tf.clear();
                //list.getItems().add(str);
                Wait();

            }

        });
        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle(name);
        primaryStage.show();

    }

    public static void deploy(ImageView iv, ImageView iv1, ImageView iv7, ImageView iv4, ImageView iv5, ImageView iv6, double[][] ships1, double[][] ships2, double[][] ships3, double[][] ships4, double[][] ships5) {
        Group root = new Group();
        Game game = new Game(ships1, ships2, ships3, ships4, ships5);
        ImageView ivb = new ImageView();
        Image imgb = new Image("background.gif");
        ivb.setImage(imgb);
        ivb.setFitHeight(1500);
        ivb.setFitWidth(1500);
        root.getChildren().add(ivb);

        Image img = new Image("board.png");
        iv.setImage(img);
        iv.setLayoutX(50);
        iv.setLayoutY(100);
        iv.setFitHeight(600);
        iv.setFitWidth(600);
        root.getChildren().add(iv);

//        ImageView iv2= new ImageView();
//        Image img2= new Image("board.png");
//        iv2.setImage(img2);
//        iv2.setLayoutX(750);
//        iv2.setLayoutY(100);
//        iv2.setFitHeight(600);
//        iv2.setFitWidth(600);
//        root.getChildren().add(iv2);
//        
        //ImageView iv1= new ImageView();
        Image img1 = new Image("5 sq ship.png");
        iv1.setImage(img1);
        iv1.setLayoutX(700);
        iv1.setLayoutY(100);
        iv1.setFitHeight(295);
        iv1.setFitWidth(50);
        root.getChildren().add(iv1);

        //  ImageView iv4= new ImageView();
        Image img4 = new Image("4 sq ship.png");
        iv4.setImage(img4);
        iv4.setLayoutX(760);
        iv4.setLayoutY(100);
        iv4.setFitHeight(235);
        iv4.setFitWidth(50);
        root.getChildren().add(iv4);

        // ImageView iv5= new ImageView();
        Image img5 = new Image("3 sq ship 2.png");
        iv5.setImage(img5);
        iv5.setLayoutX(820);
        iv5.setLayoutY(100);
        iv5.setFitHeight(175);
        iv5.setFitWidth(50);
        root.getChildren().add(iv5);

        //ImageView iv6= new ImageView();
        Image img6 = new Image("3 sq ship 1.png");
        iv6.setImage(img6);
        iv6.setLayoutX(880);
        iv6.setLayoutY(100);
        iv6.setFitHeight(175);
        iv6.setFitWidth(50);
        root.getChildren().add(iv6);

        // ImageView iv7= new ImageView();
        Image img7 = new Image("2 sq ship.png");
        iv7.setImage(img7);
        iv7.setLayoutX(940);
        iv7.setLayoutY(100);
        iv7.setFitHeight(115);
        iv7.setFitWidth(50);
        root.getChildren().add(iv7);
        double[][] coordinates = new double[1][2];

        iv1.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                System.out.println(t.getX() + " " + t.getY());
                //Rectangle rect= (Rectangle)t.getSource();
                System.out.println(t.getSceneX() + " " + t.getSceneY());
                iv1.setX(t.getX());
                iv1.setY(t.getY());
                coordinates[0][0] = t.getX();
                coordinates[0][1] = t.getY();
                int x = (int) (t.getSceneX()) / 60;
                int y = (int) (t.getSceneY()) / 60;
                ships1[0][0] = x * 60;
                ships1[1][0] = y * 60;
                ships1[0][1] = ships1[0][0];
                ships1[0][2] = ships1[0][0];
                ships1[0][3] = ships1[0][0];
                ships1[0][4] = ships1[0][0];

                ships1[1][1] = ships1[1][0] + 60;
                ships1[1][2] = ships1[1][0] + 120;
                ships1[1][3] = ships1[1][0] + 180;
                ships1[1][4] = ships1[1][0] + 240;

            }

        });

        iv1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                iv1.requestFocus();

            }

        });

        iv1.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent k) {
                // iv1.setOnMouseDragged(null);
                if (k.getCode() == KeyCode.RIGHT) {
                    System.out.println("R key pressed");
                    iv1.setRotate(90);
                    iv1.setX(coordinates[0][0] + 120);
                    iv1.setY(coordinates[0][1] - 120);
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships1[0][1] = ships1[0][0] + 60;
                    ships1[0][2] = ships1[0][0] + 120;
                    ships1[0][3] = ships1[0][0] + 180;
                    ships1[0][4] = ships1[0][0] + 240;

                    ships1[1][1] = ships1[1][0];
                    ships1[1][2] = ships1[1][0];
                    ships1[1][3] = ships1[1][0];
                    ships1[1][4] = ships1[1][0];

                }
                if (k.getCode() == KeyCode.DOWN) {
                    System.out.println("D key pressed");
                    iv1.setRotate(180);
                    iv1.setX(coordinates[0][0]);
                    iv1.setY(coordinates[0][1]);
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships1[0][1] = ships1[0][0];
                    ships1[0][2] = ships1[0][0];
                    ships1[0][3] = ships1[0][0];
                    ships1[0][4] = ships1[0][0];

                    ships1[1][1] = ships1[1][0] + 60;
                    ships1[1][2] = ships1[1][0] + 120;
                    ships1[1][3] = ships1[1][0] + 180;
                    ships1[1][4] = ships1[1][0] + 240;

                }
                if (k.getCode() == KeyCode.LEFT) {

                    System.out.println("L key pressed");
                    iv1.setRotate(-90);
                    // System.out.println("## 1"+iv1.getX()+" "+iv1.getY());

                    iv1.setX(coordinates[0][0] - 120);
                    iv1.setY(coordinates[0][1] - 120);
                    // System.out.println(iv1.getX()+" "+iv1.getY());
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships1[0][1] = ships1[0][0] - 60;
                    ships1[0][2] = ships1[0][0] - 120;
                    ships1[0][3] = ships1[0][0] - 180;
                    ships1[0][4] = ships1[0][0] - 240;

                    ships1[1][1] = ships1[1][0];
                    ships1[1][2] = ships1[1][0];
                    ships1[1][3] = ships1[1][0];
                    ships1[1][4] = ships1[1][0];

                }
                if (k.getCode() == KeyCode.UP) {

                    System.out.println("U key pressed");
                    iv1.setRotate(0);
                    //System.out.println("## 1 "+iv1.getX()+" "+iv2.getY());

                    iv1.setX(coordinates[0][0]);
                    iv1.setY(coordinates[0][1] - 240);
                    //System.out.println(iv1.getX()+" "+iv1.getY());
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships1[0][1] = ships1[0][0];
                    ships1[0][2] = ships1[0][0];
                    ships1[0][3] = ships1[0][0];
                    ships1[0][4] = ships1[0][0];

                    ships1[1][1] = ships1[1][0] - 60;
                    ships1[1][2] = ships1[1][0] - 120;
                    ships1[1][3] = ships1[1][0] - 180;
                    ships1[1][4] = ships1[1][0] - 240;

                }

            }

        });
        iv4.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                System.out.println(t.getX() + " " + t.getY());
                //Rectangle rect= (Rectangle)t.getSource();
                System.out.println(t.getSceneX() + " " + t.getSceneY());
                iv4.setX(t.getX());
                iv4.setY(t.getY());
                coordinates[0][0] = t.getX();
                coordinates[0][1] = t.getY();
                int x = (int) (t.getSceneX()) / 60;
                int y = (int) (t.getSceneY()) / 60;
                ships2[0][0] = x * 60;
                ships2[1][0] = y * 60;
                ships2[0][1] = ships2[0][0];
                ships2[0][2] = ships2[0][0];
                ships2[0][3] = ships2[0][0];

                ships2[1][1] = ships2[1][0] + 60;
                ships2[1][2] = ships2[1][0] + 120;
                ships2[1][3] = ships2[1][0] + 180;

            }

        });

        iv4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                iv4.requestFocus();

            }

        });

        iv4.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent k) {
                // iv1.setOnMouseDragged(null);
                if (k.getCode() == KeyCode.RIGHT) {
                    System.out.println("R key pressed");
                    iv4.setRotate(90);
                    iv4.setX(coordinates[0][0] + 90);
                    iv4.setY(coordinates[0][1] - 90);
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships2[0][1] = ships2[0][0] + 60;
                    ships2[0][2] = ships2[0][0] + 120;
                    ships2[0][3] = ships2[0][0] + 180;

                    ships2[1][1] = ships2[1][0];
                    ships2[1][2] = ships2[1][0];
                    ships2[1][3] = ships2[1][0];

                }
                if (k.getCode() == KeyCode.DOWN) {
                    System.out.println("D key pressed");
                    iv4.setRotate(180);
                    iv4.setX(coordinates[0][0]);
                    iv4.setY(coordinates[0][1]);
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships2[0][1] = ships2[0][0];
                    ships2[0][2] = ships2[0][0];
                    ships2[0][3] = ships2[0][0];

                    ships2[1][1] = ships2[1][0] + 60;
                    ships2[1][2] = ships2[1][0] + 120;
                    ships2[1][3] = ships2[1][0] + 180;

                }
                if (k.getCode() == KeyCode.LEFT) {

                    System.out.println("L key pressed");
                    iv4.setRotate(-90);
                    // System.out.println("## 1"+iv1.getX()+" "+iv1.getY());

                    iv4.setX(coordinates[0][0] - 90);
                    iv4.setY(coordinates[0][1] - 90);
                    // System.out.println(iv1.getX()+" "+iv1.getY());
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships2[0][1] = ships2[0][0] - 60;
                    ships2[0][2] = ships2[0][0] - 120;
                    ships2[0][3] = ships2[0][0] - 180;

                    ships2[1][1] = ships2[1][0];
                    ships2[1][2] = ships2[1][0];
                    ships2[1][3] = ships2[1][0];

                }
                if (k.getCode() == KeyCode.UP) {

                    System.out.println("U key pressed");
                    iv4.setRotate(0);
                    //System.out.println("## 1 "+iv1.getX()+" "+iv2.getY());

                    iv4.setX(coordinates[0][0]);
                    iv4.setY(coordinates[0][1] - 180);
                    //System.out.println(iv1.getX()+" "+iv1.getY());
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships2[0][1] = ships2[0][0];
                    ships2[0][2] = ships2[0][0];
                    ships2[0][3] = ships2[0][0];

                    ships2[1][1] = ships2[1][0] - 60;
                    ships2[1][2] = ships2[1][0] - 120;
                    ships2[1][3] = ships2[1][0] - 180;

                }

            }

        });
        iv5.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                System.out.println(t.getX() + " " + t.getY());
                //Rectangle rect= (Rectangle)t.getSource();
                System.out.println(t.getSceneX() + " " + t.getSceneY());
                iv5.setX(t.getX());
                iv5.setY(t.getY());
                coordinates[0][0] = t.getX();
                coordinates[0][1] = t.getY();
                int x = (int) (t.getSceneX()) / 60;
                int y = (int) (t.getSceneY()) / 60;
                ships3[0][0] = x * 60;
                ships3[1][0] = y * 60;
                ships3[0][1] = ships3[0][0];
                ships3[0][2] = ships3[0][0];
                ships3[1][1] = ships3[1][0] + 60;
                ships3[1][2] = ships3[1][0] + 120;

            }

        });

        iv5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                iv5.requestFocus();

            }

        });

        iv5.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent k) {
                // iv1.setOnMouseDragged(null);
                if (k.getCode() == KeyCode.RIGHT) {
                    System.out.println("R key pressed");
                    iv5.setRotate(90);
                    iv5.setX(coordinates[0][0] + 60);
                    iv5.setY(coordinates[0][1] - 60);
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships3[0][1] = ships3[0][0] + 60;
                    ships3[0][2] = ships3[0][0] + 120;
                    ships3[1][1] = ships3[1][0];
                    ships3[1][2] = ships3[1][0];

                }
                if (k.getCode() == KeyCode.DOWN) {
                    System.out.println("D key pressed");
                    iv5.setRotate(180);
                    iv5.setX(coordinates[0][0]);
                    iv5.setY(coordinates[0][1]);
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships3[0][1] = ships3[0][0];
                    ships3[0][2] = ships3[0][0];
                    ships3[1][1] = ships3[1][0] + 60;
                    ships3[1][2] = ships3[1][0] + 120;

                }
                if (k.getCode() == KeyCode.LEFT) {

                    System.out.println("L key pressed");
                    iv5.setRotate(-90);
                    // System.out.println("## 1"+iv1.getX()+" "+iv1.getY());

                    iv5.setX(coordinates[0][0] - 60);
                    iv5.setY(coordinates[0][1] - 60);
                    // System.out.println(iv1.getX()+" "+iv1.getY());
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships3[0][1] = ships3[0][0] + 60;
                    ships3[0][2] = ships3[0][0] + 120;
                    ships3[1][1] = ships3[1][0];
                    ships3[1][2] = ships3[1][0];

                }
                if (k.getCode() == KeyCode.UP) {

                    System.out.println("U key pressed");
                    iv5.setRotate(0);
                    //System.out.println("## 1 "+iv1.getX()+" "+iv2.getY());

                    iv5.setX(coordinates[0][0]);
                    iv5.setY(coordinates[0][1] - 120);
                    //System.out.println(iv1.getX()+" "+iv1.getY());
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships3[0][1] = ships3[0][0];
                    ships3[0][2] = ships3[0][0];
                    ships3[1][1] = ships3[1][0] - 60;
                    ships3[1][2] = ships3[1][0] - 120;

                }

            }

        });
        iv6.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                System.out.println(t.getX() + " " + t.getY());
                //Rectangle rect= (Rectangle)t.getSource();
                System.out.println(t.getSceneX() + " " + t.getSceneY());
                iv6.setX(t.getX());
                iv6.setY(t.getY());
                coordinates[0][0] = t.getX();
                coordinates[0][1] = t.getY();
                int x = (int) (t.getSceneX()) / 60;
                int y = (int) (t.getSceneY()) / 60;
                ships4[0][0] = x * 60;
                ships4[1][0] = y * 60;
                ships4[0][1] = ships4[0][0];
                ships4[0][2] = ships4[0][0];
                ships4[1][1] = ships4[1][0] + 60;
                ships4[1][2] = ships4[1][0] + 120;

            }

        });

        iv6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                iv6.requestFocus();

            }

        });

        iv6.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent k) {
                // iv1.setOnMouseDragged(null);
                if (k.getCode() == KeyCode.RIGHT) {
                    System.out.println("R key pressed");
                    iv6.setRotate(90);
                    iv6.setX(coordinates[0][0] + 60);
                    iv6.setY(coordinates[0][1] - 60);
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships4[0][1] = ships4[0][0] + 60;
                    ships4[0][2] = ships4[0][0] + 120;
                    ships4[1][1] = ships4[1][0];
                    ships4[1][2] = ships4[1][0];

                }
                if (k.getCode() == KeyCode.DOWN) {
                    System.out.println("D key pressed");
                    iv6.setRotate(180);
                    iv6.setX(coordinates[0][0]);
                    iv6.setY(coordinates[0][1]);
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships4[0][1] = ships4[0][0];
                    ships4[0][2] = ships4[0][0];
                    ships4[1][1] = ships4[1][0] + 60;
                    ships4[1][2] = ships4[1][0] + 120;

                }
                if (k.getCode() == KeyCode.LEFT) {

                    System.out.println("L key pressed");
                    iv6.setRotate(-90);
                    // System.out.println("## 1"+iv1.getX()+" "+iv1.getY());

                    iv6.setX(coordinates[0][0] - 60);
                    iv6.setY(coordinates[0][1] - 60);
                    // System.out.println(iv1.getX()+" "+iv1.getY());
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships4[0][1] = ships4[0][0] + 60;
                    ships4[0][2] = ships4[0][0] + 120;
                    ships4[1][1] = ships4[1][0];
                    ships4[1][2] = ships4[1][0];

                }
                if (k.getCode() == KeyCode.UP) {

                    System.out.println("U key pressed");
                    iv6.setRotate(0);
                    //System.out.println("## 1 "+iv1.getX()+" "+iv2.getY());

                    iv6.setX(coordinates[0][0]);
                    iv6.setY(coordinates[0][1] - 120);
                    //System.out.println(iv1.getX()+" "+iv1.getY());
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships4[0][1] = ships4[0][0];
                    ships4[0][2] = ships4[0][0];
                    ships4[1][1] = ships4[1][0] - 60;
                    ships4[1][2] = ships4[1][0] - 120;

                }

            }

        });
        iv7.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                System.out.println(t.getX() + " " + t.getY());
                //Rectangle rect= (Rectangle)t.getSource();
                System.out.println(t.getSceneX() + " " + t.getSceneY());
                iv7.setX(t.getX());
                iv7.setY(t.getY());
                coordinates[0][0] = t.getX();
                coordinates[0][1] = t.getY();
                int x = (int) (t.getSceneX()) / 60;
                int y = (int) (t.getSceneY()) / 60;
                ships5[0][0] = x * 60;
                ships5[1][0] = y * 60;
                ships5[0][1] = ships5[0][0];
                ships5[1][1] = ships5[1][0] + 60;

            }

        });

        iv7.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                iv7.requestFocus();

            }

        });

        iv7.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent k) {
                // iv1.setOnMouseDragged(null);
                if (k.getCode() == KeyCode.RIGHT) {
                    System.out.println("R key pressed");
                    iv7.setRotate(90);
                    iv7.setX(coordinates[0][0] + 30);
                    iv7.setY(coordinates[0][1] - 30);
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships5[0][1] = ships5[0][0] + 60;

                    ships5[1][1] = ships5[1][0];

                }
                if (k.getCode() == KeyCode.DOWN) {
                    System.out.println("D key pressed");
                    iv7.setRotate(180);
                    iv7.setX(coordinates[0][0]);
                    iv7.setY(coordinates[0][1]);
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships5[0][1] = ships5[0][0];

                    ships5[1][1] = ships5[1][0] + 60;

                }
                if (k.getCode() == KeyCode.LEFT) {

                    System.out.println("L key pressed");
                    iv7.setRotate(-90);
                    // System.out.println("## 1"+iv1.getX()+" "+iv1.getY());

                    iv7.setX(coordinates[0][0] - 30);
                    iv7.setY(coordinates[0][1] - 30);
                    // System.out.println(iv1.getX()+" "+iv1.getY());
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships5[0][1] = ships5[0][0] + 60;

                    ships5[1][1] = ships5[1][0];

                }
                if (k.getCode() == KeyCode.UP) {

                    System.out.println("U key pressed");
                    iv7.setRotate(0);
                    //System.out.println("## 1 "+iv1.getX()+" "+iv2.getY());

                    iv7.setX(coordinates[0][0]);
                    iv7.setY(coordinates[0][1] - 60);
                    //System.out.println(iv1.getX()+" "+iv1.getY());
                    System.out.println("## 1 " + iv1.getX() + " " + iv1.getY());
                    ships5[0][1] = ships5[0][0];

                    ships5[1][1] = ships5[1][0] - 60;

                }

            }

        });
        iv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                System.out.println("b1: " + t.getSceneX() + " " + t.getSceneY());

            }

        });

//       iv2.setOnMouseClicked(new EventHandler<MouseEvent>()
//       {
//           public void handle(MouseEvent t)
//           { 
//               System.out.println("b2: "+t.getSceneX()+" "+t.getSceneY());
//               
//           }
//             
//       });
        Button btn = new Button();
        btn.setLayoutX(750);
        root.getChildren().add(btn);
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 5; j++) {
                        System.out.println("5 sq unit ship: " + i + "," + j + ": " + ships1[i][j]);
                    }
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 4; j++) {
                        System.out.println("4 sq unit ship: " + i + "," + j + ": " + ships2[i][j]);
                    }
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.println("3 sq unit ship1: " + i + "," + j + ": " + ships3[i][j]);
                    }
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.println("3 sq unit ship2: " + i + "," + j + ": " + ships4[i][j]);
                    }
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        System.out.println("2 sq unit ship: " + i + "," + j + ": " + ships5[i][j]);
                    }
                }
                System.out.println("next screen");

                Object obj1 = (Object) game;
                nu.write(obj1);
                Object o = (String) "done deployment";
                nu.write(o);
                Wait();

            }
        });
        Scene scene = new Scene(root, 1500, 1500);
        primaryStage.setScene(scene);
        primaryStage.setTitle(name);
        primaryStage.show();

    }

    /*public static void deployment(ImageView[] iv,char[] orientation, double[][] ships1, double[][] ships2)
{
    Group root= new Group();
   // Game game=new Game(ships1,ships2);
    
    ImageView iv1= new ImageView();
    Image image=new Image("Battleship.png");
    iv1.setImage(image);
    iv1.setFitWidth(200);
    iv1.setFitHeight(50);
    iv1.setLayoutX(600);
    iv1.setLayoutY(50);
    
    double[][] coordinates= new double[5][2];
    Rectangle[][] rect=new Rectangle[10][10];
    for(int i=0;i<5;i++)
    {
        for(int j=0;j<5;j++)
        {
            
            rect[i*2][j*2]=new Rectangle(10+j*100,10+i*100,50,50);
            rect[i*2][j*2].setFill(Color.AQUAMARINE);
            rect[i*2][2*j+1]=new Rectangle(60+j*100,10+i*100,50,50);
            rect[i*2][2*j+1].setFill(Color.BLACK);
            
        }
        for(int j=0;j<5;j++)
        {
           
            rect[i*2+1][j*2]=new Rectangle(10+j*100,60+i*100,50,50);
            rect[i*2+1][j*2].setFill(Color.BLACK);
            rect[2*i+1][2*j+1]=new Rectangle(60+j*100,60+i*100, 50, 50);
            rect[2*i+1][2*j+1].setFill(Color.AQUAMARINE);
            
            
        }
    }
    
    
     for(int i=0;i<10;i++)
       {
           for(int j=0;j<10;j++)
           {
               root.getChildren().add(rect[i][j]);
           }
       }
    ImageView iv2= new ImageView();
    Image image2=new Image("Frigate.png");
    iv2.setImage(image2);
    iv2.setFitWidth(250);
    iv2.setFitHeight(50);
    iv2.setLayoutX(600);
    iv2.setLayoutY(150);
    
    ImageView iv3= new ImageView();
    Image image3=new Image("Submarine.png");
    iv3.setImage(image3);
    iv3.setFitWidth(200);
    iv3.setFitHeight(150);
    iv3.setLayoutX(600);
    iv3.setLayoutY(220);
    
    
    ImageView iv4= new ImageView();
    Image image4=new Image("ac2.png");
    iv4.setImage(image4);
    iv4.setFitWidth(250);
    iv4.setFitHeight(60);
    iv4.setLayoutX(600);
    iv4.setLayoutY(350);
    iv[0]=iv1;
    iv[1]=iv2;
    iv[2]=iv3;
    iv[3]=iv4;
    
    Button btn= new Button("Done");
    btn.setLayoutX(500);
    btn.setLayoutY(500);
 
     
    root.getChildren().addAll(iv1,iv2,iv3,iv4,btn);
     
     Scene scene = new Scene(root, 1000,600);
     iv1.setOnMouseDragged(new EventHandler<MouseEvent>()
       {
           public void handle(MouseEvent t)
           {
               System.out.println(t.getX()+" "+t.getY());
               //Rectangle rect= (Rectangle)t.getSource();
               System.out.println(t.getSceneX()+" "+t.getSceneY());
               iv1.setX(t.getX());
               iv1.setY(t.getY());
               coordinates[0][0]=t.getX();
               coordinates[0][1]=t.getY();
               ships1[0][0]=t.getSceneX();
               ships1[1][0]=t.getSceneY();
               ships1[0][1]=ships1[0][0]+50;
               ships1[0][2]=ships1[0][0]+100;
               ships1[0][3]=ships1[0][0]+150;
               ships1[1][1]=ships1[1][0];
               ships1[1][2]=ships1[1][0];
               ships1[1][3]=ships1[1][0];
               
               
           }
             
       });
   
         iv1.setOnMouseClicked(new EventHandler<MouseEvent>(){
                   @Override
                   public void handle(MouseEvent t)
                   { 
                       iv1.requestFocus();
                      
                   }
               
           });
        
         iv1.setOnKeyPressed(new EventHandler<KeyEvent>(){
             double s=75.5;
                   @Override
                   public void handle(KeyEvent k)
                   {
                      // iv1.setOnMouseDragged(null);
                       if(k.getCode()==KeyCode.RIGHT)
                       {
                           System.out.println("R key pressed");
                           iv1.setRotate(180);
                           iv1.setX(coordinates[0][0]);
                           iv1.setY(coordinates[0][1]);
                           System.out.println("## 1 "+iv1.getX()+" "+iv1.getY());
                           orientation[0]='R';
                           ships1[0][1]=ships1[0][0]+50;
                           ships1[0][2]=ships1[0][0]+100;
                           ships1[0][3]=ships1[0][0]+150;
                           ships1[1][1]=ships1[1][0];
                           ships1[1][2]=ships1[1][0];
                           ships1[1][3]=ships1[1][0];
                           
                           
                           
                       }
                       if(k.getCode()==KeyCode.DOWN)
                       {
                           System.out.println("D key pressed");
                           iv1.setRotate(-90);
                           iv1.setX(coordinates[0][0]-s);
                           iv1.setY(coordinates[0][1]+s);
                           System.out.println("## 1 "+iv1.getX()+" "+iv1.getY());
                           orientation[0]='D';
                           ships1[1][1]=ships1[1][0]+50;
                           ships1[1][2]=ships1[1][0]+100;
                           ships1[1][3]=ships1[1][0]+150;
                           ships1[0][1]=ships1[0][0];
                           ships1[0][2]=ships1[0][0];
                           ships1[0][3]=ships1[0][0];
                           
                          
                       }  
                       if(k.getCode()==KeyCode.LEFT)
                       {
                           
                           System.out.println("L key pressed");
                           iv1.setRotate(180);
                          // System.out.println("## 1"+iv1.getX()+" "+iv1.getY());
                          
                           iv1.setX(coordinates[0][0]);
                           iv1.setY(coordinates[0][1]);
                          // System.out.println(iv1.getX()+" "+iv1.getY());
                          System.out.println("## 1 "+iv1.getX()+" "+iv1.getY());
                          orientation[0]='L';
                          ships1[0][1]=ships1[0][0]+50;
                          ships1[0][2]=ships1[0][0]+100;
                          ships1[0][3]=ships1[0][0]+150;
                          ships1[1][1]=ships1[1][0];
                          ships1[1][2]=ships1[1][0];
                          ships1[1][3]=ships1[1][0];
                           
                          
                       }  
                        if(k.getCode()==KeyCode.UP)
                       {
                           
                           System.out.println("U key pressed");
                           iv1.setRotate(90);
                           //System.out.println("## 1 "+iv1.getX()+" "+iv2.getY());
                          
                           iv1.setX(coordinates[0][0]+s);
                           iv1.setY(coordinates[0][1]-s);
                           //System.out.println(iv1.getX()+" "+iv1.getY());
                          System.out.println("## 1 "+iv1.getX()+" "+iv1.getY());
                          orientation[0]='U';
                           ships1[1][1]=ships1[1][0]-50;
                           ships1[1][2]=ships1[1][0]-100;
                           ships1[1][3]=ships1[1][0]-150;
                           ships1[0][1]=ships1[0][0];
                           ships1[0][2]=ships1[0][0];
                           ships1[0][3]=ships1[0][0];
                           
                       }      
                                         
                   }
               
           });
         iv2.setOnMouseDragged(new EventHandler<MouseEvent>()
       {
           public void handle(MouseEvent t)
           {
               //iv1.setOnKeyPressed(null);
               System.out.println(t.getX()+" "+t.getY());
               //Rectangle rect= (Rectangle)t.getSource();
               iv2.setX(t.getX());
               iv2.setY(t.getY());
               coordinates[1][0]=iv2.getX();
               coordinates[1][1]=iv2.getY();
               ships2[0][0]=t.getSceneX();
               ships2[1][0]=t.getSceneY();
               ships2[0][1]=ships2[0][0]+50;
                           ships2[0][2]=ships2[0][0]+100;
                           ships2[0][3]=ships2[0][0]+150;
                           ships2[0][4]=ships2[0][0]+200;
                           ships2[1][1]=ships2[1][0];
                           ships2[1][2]=ships2[1][0];
                           ships2[1][3]=ships2[1][0];
                           ships2[1][3]=ships2[1][0];
               
           }
             
       });
//      iv1.setOnMouseReleased(new EventHandler<MouseEvent>(){
//                   @Override
//                   public void handle(MouseEvent t)
//                   { 
//                      
//                      iv1.setOnMouseDragged(null);
//                      iv1.setOnMouseReleased(null);
//                   }
//               
//           });
          iv2.setOnMouseClicked(new EventHandler<MouseEvent>(){
                   @Override
                   public void handle(MouseEvent t)
                   { 
                       iv2.requestFocus();
                      
                   }
               
           });
         iv2.setOnKeyPressed(new EventHandler<KeyEvent>(){
             int s=100;      
             @Override
                   public void handle(KeyEvent k)
                   { 
                       //iv2.setOnMouseDragged(null);
                       if(k.getCode()==KeyCode.RIGHT)
                       {
                           System.out.println("R key pressed");
                           iv2.setRotate(180);
                           //System.out.println("**"+coordinates[1][0]+coordinates[1][1]);
                           iv2.setX(coordinates[1][0]);
                           iv2.setY(coordinates[1][1]);
                           System.out.println("## 2 "+iv2.getX()+" "+iv2.getY());
                           orientation[1]='R';
                           ships2[0][1]=ships2[0][0]+50;
                           ships2[0][2]=ships2[0][0]+100;
                           ships2[0][3]=ships2[0][0]+150;
                           ships2[0][4]=ships2[0][0]+200;
                           ships2[1][1]=ships2[1][0];
                           ships2[1][2]=ships2[1][0];
                           ships2[1][3]=ships2[1][0];
                           ships2[1][3]=ships2[1][0];
                       }
                       if(k.getCode()==KeyCode.DOWN)
                       {
                           System.out.println("D key pressed");
                           iv2.setRotate(-90);
                           iv2.setX(coordinates[1][0]-s);
                           iv2.setY(coordinates[1][1]+s);
                           System.out.println("## 2 "+iv2.getX()+" "+iv2.getY());
                           orientation[1]='D';
                           ships2[1][1]=ships2[1][0]+50;
                           ships2[1][2]=ships2[1][0]+100;
                           ships2[1][3]=ships2[1][0]+150;
                           ships2[1][4]=ships2[1][0]+200;
                           ships2[0][1]=ships2[0][0];
                           ships2[0][2]=ships2[0][0];
                           ships2[0][3]=ships2[0][0];
                           ships2[0][3]=ships2[0][0];
                       } 
                        if(k.getCode()==KeyCode.LEFT)
                       {
                           
                           System.out.println("L key pressed");
                           iv2.setRotate(180);
                          // System.out.println("## "+iv2.getX()+" "+iv2.getY());
                          
                           iv2.setX(coordinates[1][0]);
                           iv2.setY(coordinates[1][1]);
                          // System.out.println(iv2.getX()+" "+iv2.getY());
                           System.out.println("## 2 "+iv2.getX()+" "+iv2.getY());
                           orientation[1]='L';
                           ships2[0][1]=ships2[0][0]+50;
                           ships2[0][2]=ships2[0][0]+100;
                           ships2[0][3]=ships2[0][0]+150;
                           ships2[0][4]=ships2[0][0]+200;
                           ships2[1][1]=ships2[1][0];
                           ships2[1][2]=ships2[1][0];
                           ships2[1][3]=ships2[1][0];
                           ships2[1][3]=ships2[1][0];
                          
                       }  
                        if(k.getCode()==KeyCode.UP)
                       {
                           
                           System.out.println("U key pressed");
                           iv2.setRotate(90);
                          // System.out.println("## "+iv2.getX()+" "+iv2.getY());
                          
                           iv2.setX(coordinates[1][0]+s);
                           iv2.setY(coordinates[1][1]-s);
                          // System.out.println(iv2.getX()+" "+iv2.getY());
                           System.out.println("## 2 "+iv2.getX()+" "+iv2.getY());
                           orientation[1]='U';
                           ships2[1][1]=ships2[1][0]-50;
                           ships2[1][2]=ships2[1][0]-100;
                           ships2[1][3]=ships2[1][0]-150;
                           ships2[1][4]=ships2[1][0]-200;
                           ships2[0][1]=ships2[0][0];
                           ships2[0][2]=ships2[0][0];
                           ships2[0][3]=ships2[0][0];
                           ships2[0][3]=ships2[0][0];
                          
                       }      
                                         
                   }
         });

//      iv1.setOnMouseReleased(new EventHandler<MouseEvent>(){
//                   @Override
//                   public void handle(MouseEvent t)
//                   { 
//                      
//                      iv1.setOnMouseDragged(null);
//                      iv1.setOnMouseReleased(null);
//                   }
//               
//           });
         
        
    iv3.setOnMouseDragged(new EventHandler<MouseEvent>()
       {
           public void handle(MouseEvent t)
           {
               System.out.println(t.getX()+" "+t.getY());
               //Rectangle rect= (Rectangle)t.getSource();
               iv3.setX(t.getX());
               iv3.setY(t.getY());
               coordinates[2][0]=iv3.getX();
               coordinates[2][1]=iv3.getY();
               ships1[2][0]=t.getSceneX();
               ships1[3][0]=t.getSceneY();
                ships1[2][1]=ships1[0][0]+50;
                           ships1[2][2]=ships1[0][0]+100;
                           ships1[2][3]=ships1[0][0]+150;
                           ships1[3][1]=ships1[1][0];
                           ships1[3][2]=ships1[1][0];
                           ships1[3][3]=ships1[1][0];
          
               
           }
             
       });
     iv3.setOnMouseClicked(new EventHandler<MouseEvent>(){
                   @Override
                   public void handle(MouseEvent t)
                   { 
                       iv3.requestFocus();
                      
                   }
               
           });
//      iv1.setOnMouseReleased(new EventHandler<MouseEvent>(){
//                   @Override
//                   public void handle(MouseEvent t)
//                   { 
//                      
//                      iv1.setOnMouseDragged(null);
//                      iv1.setOnMouseReleased(null);
//                   }
//               
//           });
     
         iv3.setOnKeyPressed(new EventHandler<KeyEvent>(){
             double s=75.5;       
             @Override
                   public void handle(KeyEvent k)
                   { 
                      // iv3.setOnMouseDragged(null);
                       if(k.getCode()==KeyCode.RIGHT)
                       {
                           System.out.println("R key pressed");
                           iv3.setRotate(180);
                        
                           iv3.setX(coordinates[2][0]);
                           iv3.setY(coordinates[2][1]);
                            System.out.println("## 3 "+iv3.getX()+" "+iv3.getY());
                            orientation[2]='R';
                           ships1[2][1]=ships1[0][0]+50;
                           ships1[2][2]=ships1[0][0]+100;
                           ships1[2][3]=ships1[0][0]+150;
                           ships1[3][1]=ships1[1][0];
                           ships1[3][2]=ships1[1][0];
                           ships1[3][3]=ships1[1][0];
                       }
                       if(k.getCode()==KeyCode.DOWN)
                       {
                           System.out.println("D key pressed");
                           iv3.setRotate(-90);
                           iv3.setX(coordinates[2][0]-s);
                           iv3.setY(coordinates[2][1]+s);
                           System.out.println("## 3 "+iv3.getX()+" "+iv3.getY());
                           orientation[2]='D';
                           ships1[3][1]=ships1[1][0]+50;
                           ships1[3][2]=ships1[1][0]+100;
                           ships1[3][3]=ships1[1][0]+150;
                           ships1[2][1]=ships1[0][0];
                           ships1[2][2]=ships1[0][0];
                           ships1[2][3]=ships1[0][0];
                           
                          
                       } 
                        if(k.getCode()==KeyCode.LEFT)
                       {
                           
                           System.out.println("L key pressed");
                           iv3.setRotate(180);
                         //  System.out.println("## "+iv3.getX()+" "+iv3.getY());
                          
                           iv3.setX(coordinates[2][0]);
                           iv3.setY(coordinates[2][1]);
                         //  System.out.println(iv3.getX()+" "+iv3.getY());
                         System.out.println("## 3 "+iv3.getX()+" "+iv3.getY());
                         orientation[2]='L';
                         ships1[2][1]=ships1[0][0]+50;
                         ships1[2][2]=ships1[0][0]+100;
                         ships1[2][3]=ships1[0][0]+150;
                         ships1[3][1]=ships1[1][0];
                         ships1[3][2]=ships1[1][0];
                         ships1[3][3]=ships1[1][0];
                          
                       }  
                        if(k.getCode()==KeyCode.UP)
                       {
                           
                           System.out.println("U key pressed");
                           iv3.setRotate(90);
                          // System.out.println("## "+iv3.getX()+" "+iv3.getY());
                          
                           iv3.setX(coordinates[2][0]+s);
                           iv3.setY(coordinates[2][1]-s);
                          // System.out.println(iv3.getX()+" "+iv3.getY());
                          System.out.println("## 3 "+iv3.getX()+" "+iv3.getY());
                          orientation[2]='U';
                          ships1[3][1]=ships1[1][0]-50;
                           ships1[3][2]=ships1[1][0]-100;
                           ships1[3][3]=ships1[1][0]-150;
                           ships1[2][1]=ships1[0][0];
                           ships1[2][2]=ships1[0][0];
                           ships1[2][3]=ships1[0][0];
                       }      
                                         
                                         
                   }
         });
        iv4.setOnMouseDragged(new EventHandler<MouseEvent>()
       {
           public void handle(MouseEvent t)
           {
              // iv3.setOnKeyPressed(null);
               System.out.println(t.getX()+" "+t.getY());
               //Rectangle rect= (Rectangle)t.getSource();
               iv4.setX(t.getX());
               iv4.setY(t.getY());
               coordinates[3][0]=iv4.getX();
               coordinates[3][1]=iv4.getY();
               ships2[2][0]=t.getSceneX();
               ships2[3][0]=t.getSceneY();
               ships2[2][1]=ships2[2][0]+50;
                           ships2[2][2]=ships2[2][0]+100;
                           ships2[2][3]=ships2[2][0]+150;
                           ships2[2][4]=ships2[2][0]+200;
                           ships2[3][1]=ships2[3][0];
                           ships2[3][2]=ships2[3][0];
                           ships2[3][3]=ships2[3][0];
                           ships2[3][3]=ships2[3][0];
          
               
           }
             
       });
//      iv1.setOnMouseReleased(new EventHandler<MouseEvent>(){
//                   @Override
//                   public void handle(MouseEvent t)
//                   { 
//                      
//                      iv1.setOnMouseDragged(null);
//                      iv1.setOnMouseReleased(null);
//                   }
//               
//           });
         iv4.setOnMouseClicked(new EventHandler<MouseEvent>(){
                   @Override
                   public void handle(MouseEvent t)
                   { 
                       iv4.requestFocus();
                      
                   }
               
           });
        
         iv4.setOnKeyPressed(new EventHandler<KeyEvent>(){
             int s=100;      
             
            @Override
                   
            public void handle(KeyEvent k)
                   { // iv4.setOnMouseDragged(null);
                       if(k.getCode()==KeyCode.RIGHT)
                       {
                           System.out.println("R key pressed");
                           iv4.setRotate(180);
                           iv4.setX(coordinates[3][0]);
                           iv4.setY(coordinates[3][1]);
                           System.out.println("## 4 "+iv4.getX()+" "+iv4.getY());
                           orientation[3]='R';
                           ships2[2][1]=ships2[2][0]+50;
                           ships2[2][2]=ships2[2][0]+100;
                           ships2[2][3]=ships2[2][0]+150;
                           ships2[2][4]=ships2[2][0]+200;
                           ships2[3][1]=ships2[3][0];
                           ships2[3][2]=ships2[3][0];
                           ships2[3][3]=ships2[3][0];
                           ships2[3][3]=ships2[3][0];
                           
                       }
                       if(k.getCode()==KeyCode.DOWN)
                       {
                           
                           System.out.println("D key pressed");
                           iv4.setRotate(-90);
                           //System.out.println("## "+iv4.getX()+" "+iv4.getY());
                          
                           iv4.setX(coordinates[3][0]-s);
                           iv4.setY(coordinates[3][1]+s);
                          // System.out.println(iv4.getX()+" "+iv4.getY());
                           System.out.println("## 4 "+iv4.getX()+" "+iv4.getY());
                           orientation[3]='D';
                           ships2[3][1]=ships2[3][0]+50;
                           ships2[3][2]=ships2[3][0]+100;
                           ships2[3][3]=ships2[3][0]+150;
                           ships2[3][4]=ships2[3][0]+200;
                           ships2[2][1]=ships2[2][0];
                           ships2[2][2]=ships2[2][0];
                           ships2[2][3]=ships2[2][0];
                           ships2[2][3]=ships2[2][0];
                          
                       } 
                       if(k.getCode()==KeyCode.LEFT)
                       {
                           
                           System.out.println("L key pressed");
                           iv4.setRotate(180);
                          // System.out.println("## "+iv4.getX()+" "+iv4.getY());
                          
                           iv4.setX(coordinates[3][0]);
                           iv4.setY(coordinates[3][1]);
                           //System.out.println(iv4.getX()+" "+iv4.getY());
                            System.out.println("## 4 "+iv4.getX()+" "+iv4.getY());
                            orientation[3]='L';
                           ships2[2][1]=ships2[2][0]+50;
                           ships2[2][2]=ships2[2][0]+100;
                           ships2[2][3]=ships2[2][0]+150;
                           ships2[2][4]=ships2[2][0]+200;
                           ships2[3][1]=ships2[3][0];
                           ships2[3][2]=ships2[3][0];
                           ships2[3][3]=ships2[3][0];
                           ships2[3][3]=ships2[3][0];
                           
                          
                       }  
                        if(k.getCode()==KeyCode.UP)
                       {
                           
                           System.out.println("U key pressed");
                           iv4.setRotate(90);
                          // System.out.println("## "+iv4.getX()+" "+iv4.getY());
                          
                           iv4.setX(coordinates[3][0]+s);
                           iv4.setY(coordinates[3][1]-s);
                          // System.out.println(iv4.getX()+" "+iv4.getY());
                           System.out.println("## 4 "+iv4.getX()+" "+iv4.getY());
                           orientation[3]='U';
                           ships2[3][1]=ships2[3][0]-50;
                           ships2[3][2]=ships2[3][0]-100;
                           ships2[3][3]=ships2[3][0]-150;
                           ships2[3][4]=ships2[3][0]-200;
                           ships2[2][1]=ships2[2][0];
                           ships2[2][2]=ships2[2][0];
                           ships2[2][3]=ships2[2][0];
                           ships2[2][3]=ships2[2][0];
                          
                       }  
                      
                 
                                         
                                         
                   }
               
           });
         btn.setOnMouseClicked(new EventHandler<MouseEvent>()
                 {
                     public void handle(MouseEvent event)
                    {
                        System.out.println("next screen");
//                        Object obj1=(Object)ships1;
//                        nu.write(obj1);
//                        Object obj2=(Object)ships2;
//                        nu.write(obj2);
                        Object obj1=(Object)game;
                        nu.write(obj1);
                        Object o=(String)"done deployment";
                        nu.write(o);
                        
                    }
                     
                 });
         
        
      
     
    primaryStage.setScene(scene);
    primaryStage.setTitle("Rectangle");
    primaryStage.show();
       
    
}*/
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        nu = new NetworkUtil("127.0.0.1", 7777);
        new ClientReaderWriterThread(nu);

        // play(stage,iv);
        /* Media media = new Media("http://www.youtube.com/watch?v=k0BWlvnBmIE");
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();

    MediaView mediaView = new MediaView(mediaPlayer);
    //mediaView.setTranslateX(100);
    //mediaView.setTranslateY(100);
    root.getChildren().add(mediaView);
    

      // Scene scene = new Scene(root, 1000,600);
       primaryStage.setScene(scene);
       primaryStage.setTitle("Rectangle");
       primaryStage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
