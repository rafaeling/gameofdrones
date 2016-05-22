/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Astros.*;
import Objetos.Aro;
import com.sun.j3d.utils.geometry.Box;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;


/**
 *
 * @author CARLOS
 */
public class Escena extends BranchGroup {    
    
    Texture texturaEscena;
    Material luz;
    Nave nave;
    BranchGroup todo;
        
    public Escena(TheView camaraNave, TheView camaraLuna){
        int factorVelocidad = 800000;
        int factorVelocidadS = 1000000;
        
        todo = new BranchGroup();
        
        nave = new Nave();
        todo.addChild(nave);
        
        Appearance ap = new Appearance();
        ap.setMaterial (new Material (
            new Color3f (0.50f, 1.00f, 0.50f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.80f, 1.00f, 0.80f),   // Color difuso
            new Color3f (0.00f, 0.00f, 0.00f),   // Color especular
            0f ));                            // Brillo

        Box suelo = new Box(10000, (float) 0.1, 10000, ap);
        Transform3D altitud = new Transform3D();
        altitud.setTranslation(new Vector3d(0,-3,0) );
        TransformGroup bajarSuelo = new TransformGroup(altitud);
        bajarSuelo.addChild(suelo);
        todo.addChild(bajarSuelo);
        
        Aro aro = new Aro(10,10,0);
        
        //Aro [] aros = new Aro[100];
        for (int i=0; i<100; i++){
            //aros[i] = new Aro( Math.random()*1000.0 - 500.0 , Math.random()*1000.0 - 500.0 , 0);
            Aro aroNuevo = new Aro( Math.random()*200.0 - 100.0 , Math.random()*200.0 - 100.0 , 90);
            todo.addChild(aroNuevo);
        }
        
        aro.
        
        //Aro aro2 = new Aro(10,20,0);
        //todo.addChild(aro2);
        todo.addChild(aro);
       
        Satelite luna = new Satelite((float) sqrt(3476f/12756f), "imgs/moon.jpg", 0, 10, 100000);

        todo.addChild(luna);

        crearLuces(this);
        this.addChild(todo);
        
        nave.addView(camaraNave);
        luna.addView(camaraLuna);
        
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
