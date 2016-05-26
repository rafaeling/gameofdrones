/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import Model.Colision;
import Model.Picking;
import Model.TheView;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Node;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.SceneGraphObject;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

/**
 *
 * @author CARLOS
 */
public class Nave extends BranchGroup{
    
    Point3f[] puntosTrayectoria = new Point3f[2];
    Quat4f[] puntosOrientacion = new Quat4f[2];
    //float [] knots = {0f, 0.1f, 0.2f, 0.4f, 0.5f, 0.7f, 0.8f, 1f};
    float [] knots = {0f, 1f};
    Texture textura;
    TransformGroup tg;
    BranchGroup bg;
    private RotPosPathInterpolator interpolator;
    Picking test;

    public Nave(){
        
        bg = new BranchGroup();
        
        Transform3D altitud = new Transform3D();

        
        tg = new TransformGroup(altitud);
        
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        
        
        test = new Picking(tg);

                       
        test.setSchedulingBounds(new BoundingSphere(new Point3d(), 200.0));
        
        tg.addChild(test);
        

       
        Scene modelo = null; 
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE );
        try {
            modelo = archivo.load ("imgs/naveEspacial/naveEspacial.obj");
        } catch (FileNotFoundException e) {
            System.err.println (e);
            System.exit(1);
        } catch (ParsingErrorException e) {
            System.err.println (e);
            System.exit(1);
        } catch (IncorrectFormatException e) {
            System.err.println (e);
            System.exit(1);
        }
        
        
        bg.addChild ( modelo.getSceneGroup() );
        tg.addChild(bg);
        
        this.addChild(tg);
    }
   
   
    
    public void addView(TheView camera)
    {
        bg.addChild(camera);
    }
    
    
}
