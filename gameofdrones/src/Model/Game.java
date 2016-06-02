/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import GUI.GameView;
import Objetos.Aro;
import Objetos.PlaySound;
import java.io.IOException;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;

/**
 *
 * @author CARLOS
 */
public class Game {
    
    private static Game INSTANCE = null;
    private static GameView vista = null;
    private Game() { }
    public static Game getInstance() { 
        if(INSTANCE == null) {
            INSTANCE = new Game();
            vista = new GameView();
            vista.showWindow();
        }
        return INSTANCE;
    }
    
    //--------------------------------------------
    
    int puntuacion = 0;
    int cantidadDeAros = 20;
    int nivel = 1;
    boolean jugando = false;
    
    PlaySound sonido;
    
    Colision cl;
    Picking pk;
    
    public int getPuntuacion(){ return puntuacion; }
    public void addPuntuacion(int puntuacion){ 
        this.puntuacion += puntuacion;
        vista.setScore(this.puntuacion);
        //System.out.println(this.puntuacion);
    }
    
    public int getNivel(){ return nivel; }
    public void oneMoreLevel(){ 
        this.nivel ++;
        vista.setLevel(this.nivel);
        //System.out.println(this.puntuacion);
    }    
    
    public boolean isRunning(){ return jugando; }
    
    public void setColision(Colision cl){ this.cl = cl; }
    public void setPicking(Picking pk){ this.pk = pk; }
    
    public void start(){
        if (cl != null && pk != null) {
            jugando = true;
            
            if (nivel > 1)
                vista.getUniverse().getScene().crearAros(20*nivel);
            
            cl.setEnable(true);
            pk.setEnable(true);
        }
    }

    private void verifyNewLevel() throws IOException{
        cantidadDeAros--;

        if (cantidadDeAros == 0){

            
            
            vista.setText("Enhorabuena has conseguido pasarte el nivel.\n Ahora más de lo mismo pero con una novedad: hay más aros. 'Aros' que son cuadrados.");
            jugando = false;

            cl.setEnable(false);
            pk.setEnable(false);

            oneMoreLevel();
            cantidadDeAros = nivel * 20;
            
            sonido = new PlaySound("sonido/horn.wav");
                
            sonido.play();
            sonido.play();
            sonido.play();
        }
    }
    
    public void colision(Node objeto){
        try{
            if ( "centro".equals((String) objeto.getUserData())){
                System.out.println("Centro");
                //( (Aro) ( (Shape3D) objeto ).getParent().getParent().getParent().getParent() ).changeColor(Color.WHITE);
                Aro aro = ( (Aro) ( (Shape3D) objeto ).getParent().getParent().getParent().getParent() );
                aro.detach();
                
                verifyNewLevel();
                
                sonido = new PlaySound("sonido/beep3.wav");
                
                sonido.play();
                
                this.addPuntuacion(100);
            
            } else if ( "borde".equals((String) objeto.getUserData()) ) {
                //( (Aro) ( (Shape3D) objeto ).getParent().getParent().getParent().getParent().getParent().getParent() ).changeColor(Color.RED);
                Aro aro = ( (Aro) ( (Shape3D) objeto ).getParent().getParent().getParent().getParent().getParent().getParent() );
                aro.detach();
                
                verifyNewLevel();
                
                sonido = new PlaySound("sonido/explosion.wav");
                
                sonido.play();
                
                this.addPuntuacion(5);
                
            } else if ( "palo".equals((String) objeto.getUserData()) ) {
                //( (Aro) ( (Shape3D) objeto ).getParent().getParent().getParent().getParent().getParent().getParent() ).changeColor(Color.RED);
                System.out.println("palo");
                
                sonido = new PlaySound("sonido/explosion.wav");
                
                sonido.play();
                
            } else if ( "suelo".equals((String) objeto.getUserData()) ) {
                
                System.out.println("Suelo");
                
                sonido = new PlaySound("sonido/explosion.wav");
                
                sonido.play();
                
                addPuntuacion(-10);
                
                Transform3D nueva = new Transform3D();
                nueva.setTranslation(new Vector3d(0f, 10, 0));
                pk.getTransformAntigua().setTransform(nueva);
                
                
                
            } else {
                System.out.println("Otra cosa");
            }
        } catch (Exception e){e.printStackTrace();}

    }
    
    
    
}
