/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofdrones;

import Model.*;
import GUI.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;
/**
 *
 * @author rafaeling, carlosleclop
 */
public class Gameofdrones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
        // Se le da el tamaño deseado
        //canvas.setSize(20, 20);
        
        //7/Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        //canvas.setSize(1000,700);

        // Se crea el Universo con dicho Canvas3D
        //Universo universe = new Universo (canvas);
        
        // Se crea la GUI a partir del Canvas3D y del Universo
        //ControlWindow controlWindow2 = new ControlWindow (canvas, universe);
        // Se muestra la ventana principal de la aplicación
        //controlWindow2.showWindow ();
        
        Game juego;
        
        juego = Game.getInstance();

    }
    
}
