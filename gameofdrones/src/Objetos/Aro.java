/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import javax.media.j3d.BranchGroup;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import java.awt.Color;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author CARLOS
 */
public class Aro extends BranchGroup {
    
    private Material mt;
    
    public Aro(double coordenadaX, double coordenadaZ, double grados){
       
        Appearance aparienciaAro = new Appearance();
        mt = new Material (
            new Color3f (0.00f, 0.00f, 0.00f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.90f),   // Color emisivo
            new Color3f (0.00f, 0.00f, 0.00f),   // Color difuso
            new Color3f (1.00f, 1.00f, 1.00f),   // Color especular
            17.0f );                            // Brillo
        
        mt.setCapability(Material.ALLOW_COMPONENT_WRITE);
        
        aparienciaAro.setMaterial (mt); 
        
        Appearance aparienciaPalo = (Appearance) aparienciaAro.cloneNodeComponent(true);
     
        TransparencyAttributes myTA = new TransparencyAttributes( );
        myTA.setTransparency( 0.5f );
        myTA.setTransparencyMode( TransparencyAttributes.BLENDED );
        aparienciaAro.setTransparencyAttributes(myTA);
        
        Box caja = new Box(1, 1, (float) 0.1, aparienciaAro);
        Cylinder palo = new Cylinder((float)0.1, 1, aparienciaPalo);
        
         Transform3D tf2 = new Transform3D();
         tf2.setTranslation(new Vector3d(0, 0, 0));
        TransformGroup tg2 = new TransformGroup(tf2);
        tg2.addChild(caja);
        
         Transform3D tf3 = new Transform3D();
         tf3.setTranslation(new Vector3d(0, -1.5, 0));
        TransformGroup tg3 = new TransformGroup(tf3);
        tg3.addChild(palo);
        
         Transform3D desplazamiento = new Transform3D();
          desplazamiento.setTranslation(new Vector3d(coordenadaX, 0, coordenadaZ));
         Transform3D rotacion = new Transform3D();
          rotacion.rotY( (Math.PI * grados ) / 180.0 );
        desplazamiento.mul(rotacion);
        TransformGroup tg = new TransformGroup(desplazamiento);

        tg.addChild(tg2);
        tg.addChild(tg3);
        
        this.addChild(tg);
        
    }
    
    public void changeColor(Color3f c){
        mt.setEmissiveColor(c);
    }

    public void changeColor(Color cl){
        mt.setEmissiveColor(new Color3f(cl));
    }
    
}
