/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Objetos.Aro;
import java.awt.Color;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;

/**
 *
 * @author CARLOS
 */
public class Game {
    
    private static final Game INSTANCE = new Game();
    private Game() {}
    public static Game getInstance() { return INSTANCE; }
    
    //--------------------------------------------
    
    int puntuacion = 0;
    boolean jugando = true;
    
    public int getPuntuacion(){ return puntuacion; }
    public void setPuntuacion(int puntuacion){ this.puntuacion = puntuacion; }
    
    public boolean isRunning(){ return jugando; }
    
    public void colision(Node objeto){
        try{
            if ( "centro".equals((String) objeto.getUserData())){
                System.out.println("Centro");
                ( (Aro) ( (Shape3D) objeto ).getParent().getParent().getParent().getParent() ).changeColor(Color.WHITE);
            } else if ( "borde".equals((String) objeto.getUserData()) ) {
                ( (Aro) ( (Shape3D) objeto ).getParent().getParent().getParent().getParent().getParent().getParent() ).changeColor(Color.RED);
                System.out.println("Borde");
            } else if ( "palo".equals((String) objeto.getUserData()) ) {
                //( (Aro) ( (Shape3D) objeto ).getParent().getParent().getParent().getParent().getParent().getParent() ).changeColor(Color.RED);
                System.out.println("palo");
            } else if ( "suelo".equals((String) objeto.getUserData()) ) {
                System.out.println("Suelo");
            } else {
                System.out.println("Otra cosa");
            }
            /*
            System.out.println ( objeto.getUserData() );
System.out.println("1" + objeto.getClass().getName());
            Shape3D choque = (Shape3D) objeto;
            Node node = choque.getParent();
System.out.println("2" + node.getClass().getName());
            node = node.getParent();
System.out.println("3" + node.getClass().getName());
            node = node.getParent();
System.out.println("4" + node.getClass().getName());
            node = node.getParent();
System.out.println("5" + node.getClass().getName());
            Aro aro = (Aro) node;
            //aro.changeColor(Color.ORANGE);*/
        } catch (Exception e){e.printStackTrace();}

    }
    
    
    
}
