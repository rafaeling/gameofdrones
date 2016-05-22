/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Box;
import static com.sun.j3d.utils.geometry.Box.*;
import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author CARLOS
 */
public class Aro extends BranchGroup {
    
    
    public Aro(double coordenadaX, double coordenadaZ, double grados){
       
        Appearance ap = new Appearance();
        ap.setMaterial (new Material (
            new Color3f (0.00f, 0.00f, 0.00f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.90f),   // Color emisivo
            new Color3f (0.00f, 0.00f, 0.00f),   // Color difuso
            new Color3f (0.00f, 0.00f, 0.00f),   // Color especular
            17.0f ));                            // Brillo
     
        Box caja = new Box(1, 1, (float) 0.1, ap);
        Cylinder palo = new Cylinder((float)0.1, 1, ap);
        
         Transform3D tf2 = new Transform3D();
         tf2.setTranslation(new Vector3d(0, 1.5, 0));
        TransformGroup tg2 = new TransformGroup(tf2);
        tg2.addChild(caja);
        
         Transform3D desplazamiento = new Transform3D();
          desplazamiento.setTranslation(new Vector3d(coordenadaX, 0, coordenadaZ));
         Transform3D rotacion = new Transform3D();
          rotacion.rotY( (Math.PI * grados ) / 180.0 );
        desplazamiento.mul(rotacion);
        TransformGroup tg = new TransformGroup(desplazamiento);

        tg.addChild(tg2);
        tg.addChild(palo);
        
        this.addChild(tg);
        
    }

    
}
