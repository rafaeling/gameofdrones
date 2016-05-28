/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Model.Picking;
import Model.TheView;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Light;
import javax.media.j3d.PointLight;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

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
        } catch (FileNotFoundException | ParsingErrorException | IncorrectFormatException e) {
            System.err.println (e);
            System.exit(1);
        }
        /* Hacer nave más pequeña
        Transform3D hacerPeque3d = new Transform3D();
        hacerPeque3d.setScale(0.1);
        TransformGroup hacerPeque = new TransformGroup(hacerPeque3d);
        hacerPeque.addChild( modelo.getSceneGroup() );
        bg.addChild(hacerPeque);
        tg.addChild(bg);*/

        bg.addChild ( modelo.getSceneGroup() );
        tg.addChild(bg);
        addLight();
        this.addChild(tg);
    }
   
    private void addLight(){
        //SpotLight (Color3f color, Point3f posición, Point3f atenuación, Vector3f dirección, float ángulo, float concentración)

        SpotLight luz = new SpotLight (new Color3f(1,1,(float) 0.5), new Point3f(0,0,0),  
                new Point3f (0,1,0) , new Vector3f(1,0,0), 
                (float) Math.PI *2 , 10);

        luz.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));
        luz.setCapability(SpotLight.ALLOW_DIRECTION_WRITE);
        luz.setCapability(SpotLight.ALLOW_SPREAD_ANGLE_WRITE);
        luz.setCapability(SpotLight.ALLOW_CONCENTRATION_WRITE);
        luz.setEnable(true);
        
        bg.addChild(luz);
    }
    
    public void addView(TheView camera)
    {
        bg.addChild(camera);
    }
    
    
}
