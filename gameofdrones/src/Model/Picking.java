/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Astros.*;
import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;

/**
 *
 * @author rafael
 */
public class Picking extends Behavior{
/*
    PickCanvas pickcanvas;
    Canvas3D canvas;
    private WakeupOnAWTEvent condition;
  */
    
    private RotPosPathInterpolator referencia;
    WakeupOnAWTEvent condicionRespuesta = new WakeupOnAWTEvent (KeyEvent.KEY_PRESSED);
    private Transform3D rotacion = new Transform3D ( ) ;
    private Transform3D transformAntigua = new Transform3D ( ) ;
    private Transform3D transformNueva = new Transform3D ( ) ;
    Point3f[] puntosTrayectoria = new Point3f[2];
    Quat4f[] puntosOrientacion = new Quat4f[2];
    float [] knots = {0f, 1f};
    private double velocidad = 0;
    
    float x, y, z;
    
    public Picking(RotPosPathInterpolator value)
    {
            referencia = value;
            
            x = 0;
            y = 0;
            z = 0;
            
            
    }
    
    
    
    @Override
    public void initialize() {
        //setEnable(false); // El pick comienza deshabilitado esperando al initSearch
        wakeupOn(condicionRespuesta);
    }

    @Override
    public void processStimulus(Enumeration criterios) {

        WakeupOnAWTEvent unCriterio = (WakeupOnAWTEvent) criterios.nextElement();
        AWTEvent[ ] eventos = unCriterio.getAWTEvent();
        KeyEvent tecla = (KeyEvent) eventos[0];
        boolean teclaCorrecta = true ;
        
        
        switch ( tecla.getKeyCode( ) ) {
            
            
            
            case KeyEvent.VK_LEFT:
                z = z - 1; break ;
            case KeyEvent.VK_RIGHT:
                z = z + 1; ; break ;
            
            
            case KeyEvent.VK_UP:
                y = y + 1; break ;
            case KeyEvent.VK_DOWN:
                y = y - 1 ; break ;
           
            default : teclaCorrecta = false ; break ; }
    
       
        
        puntosTrayectoria[0] = new Point3f(0f,0,0f);
        puntosTrayectoria[1] = new Point3f(10f,y,z);
        
       
        
        puntosOrientacion[0] = new Quat4f();
        puntosOrientacion[0].set(new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(90)));
        puntosOrientacion[1] = new Quat4f();
        puntosOrientacion[1].set(new AxisAngle4f(0f, 0f, 0.0f, (float) Math.toRadians(0)));
   
        
        referencia.setPathArrays(knots, puntosOrientacion, puntosTrayectoria);
        
        
        //interpolator.getTransformAxis();
   /*    
        transformAntigua = referencia.getTransformAxis();
        
        transformNueva.mul ( rotacion , transformAntigua ) ;
        
        referencia.setTransformAxis(transformNueva);

*/
        
        wakeupOn(condicionRespuesta);
        
    }
    
}
