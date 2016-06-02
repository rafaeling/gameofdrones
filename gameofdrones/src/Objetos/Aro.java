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
import java.util.ArrayList;
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
    
    private Appearance aparienciaAro;
    private Appearance aparienciaPalo;
    private Appearance aparienciaBorde;
    
    final private float alturaCentro = 3;     // Altura del centro
    final private float anchuraCentro = 3;    // Anchura del centro
    final private float alturaPalo = 5;       // Altura del palo
    
    public Aro(double coordenadaX, double coordenadaZ, double grados){
        
        this.setCapability(ALLOW_DETACH);
        
        setAppearances();
       
        Box caja = new Box(anchuraCentro, alturaCentro, (float) 0.001, aparienciaAro);
        Cylinder palo = new Cylinder((float)0.1, alturaPalo, aparienciaPalo);
        
        ArrayList <TransformGroup> borders = createBorders();
        for (TransformGroup i : borders){
            caja.addChild(i);
        }
        
         Transform3D tf2 = new Transform3D();
         tf2.setTranslation(new Vector3d(0, alturaPalo + (float) 0.1 + (float) +0.1 + alturaCentro, 0));
        TransformGroup tg2 = new TransformGroup(tf2);
        tg2.addChild(caja);
        
         Transform3D tf3 = new Transform3D();
         tf3.setTranslation(new Vector3d(0, alturaPalo/2 + (float) 0.1, 0));
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
        
        for (int i=0; i<6; i++){
            caja.getShape(i).setUserData("centro");
        }
        for (int i=0; i<3; i++){
            palo.getShape(i).setUserData("palo");
        }
    }
    
    private ArrayList <TransformGroup> createBorders(){
        
        ArrayList <TransformGroup> r = new ArrayList <> ();
        
        Box bordeArriba = new Box(anchuraCentro, (float) 0.1, (float) 0.1, aparienciaBorde);
        Transform3D tfArriba = new Transform3D();
         tfArriba.setTranslation(new Vector3d(0, alturaCentro + (float) 0.1, 0));
        TransformGroup tgArriba = new TransformGroup(tfArriba);
        tgArriba.addChild(bordeArriba);
        r.add(tgArriba);

        Box bordeAbajo = new Box(anchuraCentro, (float) 0.1, (float) 0.1, aparienciaBorde);
        Transform3D tfAbajo = new Transform3D();
         tfAbajo.setTranslation(new Vector3d(0, - (alturaCentro  + (float) 0.1 ), 0));
        TransformGroup tgAbajo = new TransformGroup(tfAbajo);
        tgAbajo.addChild(bordeAbajo);
        r.add(tgAbajo);
        
        Box bordeDerecha = new Box((float)0.1, alturaCentro + (float)0.2, (float) 0.1, aparienciaBorde);
        Transform3D tfDerecha = new Transform3D();
         tfDerecha.setTranslation(new Vector3d( anchuraCentro + (float) 0.1, 0 , 0));
        TransformGroup tgDerecha = new TransformGroup(tfDerecha);
        tgDerecha.addChild(bordeDerecha);
        r.add(tgDerecha);
        
        Box bordeIzquierda = new Box((float)0.1, alturaCentro + (float)0.2, (float) 0.1, aparienciaBorde);
        Transform3D tfIzquierda = new Transform3D();
         tfIzquierda.setTranslation(new Vector3d( - (anchuraCentro + (float) 0.1) , 0 , 0));
        TransformGroup tgIzquierda = new TransformGroup(tfIzquierda);
        tgIzquierda.addChild(bordeIzquierda);
        r.add(tgIzquierda);
        
        for (int i=0; i<6; i++){
            bordeArriba.getShape(i).setUserData("borde");
            bordeAbajo.getShape(i).setUserData("borde");
            bordeDerecha.getShape(i).setUserData("borde");
            bordeIzquierda.getShape(i).setUserData("borde");
        }
                
        return r;
    }
    
    private void setAppearances(){
        
        aparienciaAro = new Appearance();
        mt = new Material (
            new Color3f (0.00f, 0.00f, 0.00f),   // Color ambiental
            new Color3f ((float) Math.random(), (float) Math.random(), (float) Math.random()),   // Color emisivo
            new Color3f (0.00f, 0.00f, 0.0f),   // Color difuso
            new Color3f (1.00f, 1.00f, 1.00f),   // Color especular
            17.0f );                             // Brillo
        
        mt.setCapability(Material.ALLOW_COMPONENT_WRITE);
        aparienciaAro.setMaterial (mt); 
             
        TransparencyAttributes myTA = new TransparencyAttributes( );
        myTA.setTransparency( 0.8f );
        myTA.setTransparencyMode( TransparencyAttributes.BLENDED );
        aparienciaAro.setTransparencyAttributes(myTA);
        
        
        aparienciaPalo = new Appearance();
        Material materialPalo = new Material (
            new Color3f (0.00f, 0.00f, 0.00f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.50f, 0.90f, 0.00f),   // Color difuso
            new Color3f (1.00f, 1.00f, 1.00f),   // Color especular
            17.0f );                             // Brillo
        aparienciaPalo.setMaterial(materialPalo);
        
        aparienciaBorde = new Appearance();
        Material materialBorde = new Material (
            new Color3f (0.00f, 0.00f, 0.00f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.50f, 0.90f, 0.00f),   // Color difuso
            new Color3f (1.00f, 1.00f, 1.00f),   // Color especular
            17.0f );                            // Brillo
        aparienciaBorde.setMaterial (materialBorde); 

    }
    
    public void changeColor(Color3f c){
        mt.setEmissiveColor(c);
    }

    public void changeColor(Color cl){
        mt.setEmissiveColor(new Color3f(cl));
    }
    
}
