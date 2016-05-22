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
        
        todo.addChild(aro);
        
        
        
//        Estrella sol = new Estrella(6f, "imgs/sol.jpg", 100000);
//            
//            Planeta mercurio = new Planeta(2f, "imgs/mercury.jpg", 100, 10, (int) (factorVelocidad / 47.87));
//            
//            Planeta venus = new Planeta(2.5f, "imgs/venus.jpg", 1000, 20, (int) (factorVelocidad / 35.02));
//            
//            Planeta tierra = new Planeta(4f, "imgs/tierra.jpg", 5000, 35, (int) (factorVelocidad / 29.78) );
                Satelite luna = new Satelite((float) sqrt(3476f/12756f), "imgs/moon.jpg", 0, 10, 100000);
//                    tierra.addSatelite(luna);
//            
//            Planeta marte = new Planeta(3f, "imgs/mars.jpg", 6000, 42, (int) (factorVelocidad / 24.08));
//          
//            Planeta jupiter = new Planeta((float) sqrt(11.2), "imgs/jupiter.jpg", 7000, 50, (int) (factorVelocidad / 20.07));
//                Satelite io = new Satelite((float) sqrt(3643f/52756f), "imgs/io.jpg", 0, 10, (int) (factorVelocidadS / 39.33));
//                    jupiter.addSatelite(io);
//            
//                Satelite europa = new Satelite((float) sqrt(3122f/52756f), "imgs/moon.jpg", 0, 4, (int) (factorVelocidadS / 68.74));
//                    jupiter.addSatelite(europa);
//            
//                Satelite ganimedes = new Satelite((float) sqrt(5262f/52756f), "imgs/ganymede.jpg", 0, 6, (int) (factorVelocidadS / 47.88));
//                    jupiter.addSatelite(ganimedes);
//            
//                Satelite calisto = new Satelite((float) sqrt(4821f/52756f), "imgs/calisto.jpg", 0, 8, (int) (factorVelocidadS / 29.2));
//                    jupiter.addSatelite(calisto);
//            
//            Planeta saturno = new Planeta((float) sqrt(9.41), "imgs/saturno.jpg", 8000, 60, (int) (factorVelocidad / 15.67));
//                Anillo uno = new Anillo(0.1, 325, 300, "imgs/ring.jpg");
//                    saturno.addAnillo(uno);
//                Anillo dos = new Anillo(0.1, 370, 360, "imgs/ring.jpg");
//                    saturno.addAnillo(dos);
//                Anillo tres = new Anillo(0.1, 420, 400, "imgs/ring.jpg");
//                    saturno.addAnillo(tres);
//          
//            Planeta urano = new Planeta ((float) sqrt(3.98), "imgs/urano.jpg", 9000, 70, (int) (factorVelocidad / 13.81));
//                Anillo ura = new Anillo(0.1, 200.001, 200, "imgs/ura.jpg");
//                    urano.addAnillo(ura);
//            
//            Planeta neptuno = new Planeta((float) sqrt(3.81), "imgs/neptuno.jpg", 10000, 80, (int) (factorVelocidad / 12.48));
//        
//        sol.addPlaneta(mercurio);
//        sol.addPlaneta(venus);
//        sol.addPlaneta(tierra);
//        sol.addPlaneta(marte);
//        sol.addPlaneta(jupiter);
//        sol.addPlaneta(saturno);
//        sol.addPlaneta(urano);
//        sol.addPlaneta(neptuno);
//        
//         Anillo prueba = new Anillo(0.3, 500, 400, "imgs/ring.jpg");
//        sol.addChild(prueba);
//        
//        todo.addChild(sol);
//             
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
        // Vector3f direction = new Vector3f (-4.0f, -2.0f, -3.0f);
         aLight = new PointLight (true, new Color3f (0.5f, 0.5f, 1.0f), new Point3f(0,100,0), new Point3f(1,0,0));
         aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));
         aLight.setCapability(Light.ALLOW_STATE_WRITE);
         aLight.setEnable (true);
         bg.addChild(aLight);
    }
    
}
