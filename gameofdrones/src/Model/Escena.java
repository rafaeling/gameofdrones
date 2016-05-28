/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Objetos.Nave;
import Objetos.Aro;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;


/**
 *
 * @author CARLOS
 */
public class Escena extends BranchGroup {    
    
    Texture texturaEscena;
    Material luz;
    Nave nave;
    BranchGroup todo;
        
    public Escena(TheView camaraNave){
        
        todo = new BranchGroup();
        
        nave = new Nave();
         Transform3D subirNave3d = new Transform3D();
         subirNave3d.setTranslation(new Vector3f(0,4,0) );
         TransformGroup subirNave = new TransformGroup(subirNave3d);
         subirNave.addChild(nave);
        todo.addChild(subirNave);
        
        Appearance ap = new Appearance();
        ap.setMaterial (new Material (
            new Color3f (0.50f, 1.00f, 0.50f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.80f, 1.00f, 0.80f),   // Color difuso
            new Color3f (0.20f, 0.20f, 0.20f),   // Color especular
            0f ));                            // Brillo

        Box suelo = new Box(10000, (float) 0.1, 10000, ap);
        Transform3D altitud = new Transform3D();
        altitud.setTranslation(new Vector3d(0,0,0) );
        TransformGroup bajarSuelo = new TransformGroup(altitud);
        bajarSuelo.addChild(suelo);
        todo.addChild(bajarSuelo);
        
        /*
        Appearance apTotal = new Appearance();
        apTotal.setMaterial (new Material (
            new Color3f (0.50f, 1.00f, 0.50f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.80f, 1.00f, 0.80f),   // Color difuso
            new Color3f (0.20f, 0.20f, 0.20f),   // Color especular
            10f ));                            // Brillo
        TransparencyAttributes myTA = new TransparencyAttributes( );
        myTA.setTransparency( 0.3f );
        myTA.setTransparencyMode( TransparencyAttributes.BLENDED );
        apTotal.setTransparencyAttributes(myTA);

        Box cuadroTotal = new Box(250, 100, 250, Box.GENERATE_NORMALS_INWARD | Box.GENERATE_NORMALS, apTotal);
        Transform3D altitudTotal = new Transform3D();
        altitud.setTranslation(new Vector3d(0,100,0) );
        TransformGroup altitudTotalg = new TransformGroup(altitudTotal);
        altitudTotalg.addChild(cuadroTotal);
        todo.addChild(altitudTotalg);

        */
        
        for (int i=0; i<100; i++){
            Aro aroNuevo = new Aro( Math.random()*200.0 - 100.0 , Math.random()*200.0 - 100.0 , 90);
            todo.addChild(aroNuevo);
        }
  
        crearLuces(this);
        this.addChild(todo);
        
        nave.addView(camaraNave);
        
        Colision cl = new Colision(nave);
        todo.addChild(cl);
        
        for (int i=0; i<1000; i++){
            Appearance apSp = new Appearance();
            apSp.setMaterial (new Material (
                new Color3f ( 0,0,0 ),   // Color ambiental
                new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
                new Color3f ( (float) Math.random(),(float) Math.random(),(float) Math.random() ),   // Color difuso
                new Color3f ( (float) Math.random(),(float) Math.random(),(float) Math.random() ),   // Color especular
                10f ));                            // Brillo
            float radius = (float) Math.random();
            Sphere sp = new Sphere(radius, apSp);
            Transform3D tfSp = new Transform3D();
            tfSp.setTranslation(new Vector3d(Math.random()*200.0 - 100.0 , radius + 0.1 , Math.random()*200.0 - 100.0));
            TransformGroup tgSp = new TransformGroup(tfSp);
            tgSp.addChild(sp);
            todo.addChild(tgSp);
        }
        
        for (int i=0; i<6; i++){
            suelo.getShape(i).setUserData("suelo");
        }
    }
    
    
    private void crearLuces(BranchGroup bg){

        // LUZ AMBIENTE
         Light aLight;
         //aLight = new AmbientLight (new Color3f (0.2f, 0.2f, 0.2f));
         aLight = new AmbientLight (new Color3f (0.5f, 0.5f, 0.5f));
         aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));
         aLight.setEnable(true);
         bg.addChild(aLight);
         
        // LUZ DEL SOL
         aLight = new PointLight (true, new Color3f (0.5f, 0.5f, 1.0f), new Point3f(0,100,0), new Point3f(1,0,0));
         aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));
         aLight.setCapability(Light.ALLOW_STATE_WRITE);
         aLight.setEnable (true);
         bg.addChild(aLight);
    }
    
}
