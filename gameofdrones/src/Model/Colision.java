/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import GUI.GameView;
import Objetos.Aro;
import java.awt.Color;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

/**
 *
 * @author CARLOS
 */
public class Colision extends Behavior {
    // Se detectan colisiones del objetoA contra otros
    private Node objetoA ;
    private WakeupOnCollisionEntry condicion ;
    
    private Game game;
    
    public Colision (Node unNodo ) {
        objetoA = unNodo ;
        condicion = new WakeupOnCollisionEntry ( objetoA , WakeupOnCollisionEntry.USE_BOUNDS ) ; // Se usa BOUNDS por rapidez
                 this.setSchedulingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));

//        this.setSchedulingBounds ( objetoA . getBounds ( ) ) ;        // El ámbito del Behavior se ajusta al del objetoA
        game = Game.getInstance();
        game.setColision(this);
    }
    
    @Override
    public void initialize ( ) {
        wakeupOn ( condicion ) ;
        this.setEnable(false);
    }
    
    @Override
    public void processStimulus ( Enumeration criteria ) {
        // Se obtiene el objeto contra el que ha colisionado objetoA
        WakeupOnCollisionEntry disparo = (WakeupOnCollisionEntry ) criteria.nextElement ( ) ;
        Node objetoB = disparo.getTriggeringPath().getObject();
        
        // Se procesa la colisión        
        game.colision(objetoB);
        
        // Se establece de nuevo el disparador
        wakeupOn ( condicion ) ;
        
    }
}