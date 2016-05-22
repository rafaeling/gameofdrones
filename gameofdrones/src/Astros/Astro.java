/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;

/**
 *
 * @author CARLOS
 */
abstract public class Astro extends TransformGroup {
    
    float radio;
    
    Texture tx;
    
    TransformGroup rotacionPropia;
    
    Sphere sp;
    
    boolean act;
    
    Alpha value;
    
    protected void crearRotacionPropia(int velocidad){
    
        TransformGroup transform = new TransformGroup ();
        
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        Transform3D yAxis = new Transform3D ();
        
        value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velocidad, 0, 0, 0, 0, 0);
        RotationInterpolator rotator = new RotationInterpolator (value, transform, yAxis, 0.0f, (float) Math.PI*2.0f);
        
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 10000.0));
        rotator.setEnable(true);
        transform.addChild(rotator);

        rotacionPropia = transform;
    } 
    
    public void action(){
        if(act == false)
        {
            value.pause();
            act = true;
        }else
        {
            value.resume();
            act = false;
        }
        
    }

}
