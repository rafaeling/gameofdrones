/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Objetos.Aro;
import java.awt.Color;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.vecmath.Color3f;

/**
 *
 * @author CARLOS
 */
public class Colision extends Behavior {
    // Se detectan colisiones del objetoA contra otros
    private Node objetoA ;
    private WakeupOnCollisionEntry condicion ;
    
    public Colision (Node unNodo ) {
        objetoA = unNodo ;
        condicion = new WakeupOnCollisionEntry ( objetoA , WakeupOnCollisionEntry.USE_BOUNDS ) ; // Se usa BOUNDS por rapidez
        this.setSchedulingBounds ( objetoA . getBounds ( ) ) ;        // El ámbito del Behavior se ajusta al del objetoA
System.out.println("Construido");
    }
    
    @Override
    public void initialize ( ) {
        wakeupOn ( condicion ) ;
System.out.println("Inicializado");
    }
    
    @Override
    public void processStimulus ( Enumeration criteria ) {
System.out.println("FUNCION LLAMADA");
        // Se obtiene el objeto contra el que ha colisionado objetoA
        WakeupOnCollisionEntry disparo = (WakeupOnCollisionEntry ) 
criteria.nextElement ( ) ;
        Node objetoB = disparo . getTriggeringPath ( ) . getObject ( ) ;
        // Se procesa la colisión
        try{
System.out.println("1" + objetoB.getClass().getName());
System.out.println(objetoB.getClass().getSimpleName());
        if (objetoB.getClass().getSimpleName().equals("Shape3D")){
            Shape3D choque = (Shape3D) objetoB;
            Node node = choque.getParent();
System.out.println("2" + node.getClass().getName());
System.out.println(node.getClass().getSimpleName());
            node = node.getParent();
System.out.println("3" + node.getClass().getName());
System.out.println(node.getClass().getSimpleName());
            node = node.getParent();
System.out.println("4" + node.getClass().getName());
System.out.println(node.getClass().getSimpleName());
            node = node.getParent();
System.out.println("5" + node.getClass().getName());
System.out.println(node.getClass().getSimpleName());
            Aro aro = (Aro) node;
            aro.changeColor(Color.ORANGE);
        }
        } catch (Exception e){ e.printStackTrace(); }
        if (objetoB.getClass().getSimpleName().equals("Aro")){
            Aro tmp = (Aro) objetoB;
            tmp.changeColor(new Color3f(0, 0.9f, 0));
        }
        // Se establece de nuevo e l disparador
        wakeupOn ( condicion ) ;
    }
}