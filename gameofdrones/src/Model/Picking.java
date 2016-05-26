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
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.media.j3d.WakeupOr;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

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
    
    private TransformGroup referencia;
    WakeupOnElapsedFrames condicionRespuesta;
    WakeupOnAWTEvent condicion;
    private Transform3D rotacion = new Transform3D ( ) ;
    private Transform3D transformAntigua = new Transform3D ( ) ;
    private Transform3D transformNueva = new Transform3D ( ) ;
    
    
    private Transform3D uno = new Transform3D ( ) ;
    
    private Transform3D dos = new Transform3D ( ) ;
    
    private Transform3D extuno = new Transform3D ( ) ;
    
    private Transform3D extdos = new Transform3D ( ) ;
    
    Point3f[] puntosTrayectoria = new Point3f[2];
    Quat4f[] puntosOrientacion = new Quat4f[2];
    float [] knots = {0f, 1f};
    private double velocidad = 0;
    WakeupCriterion[] Eventos = new WakeupCriterion[2];
    WakeupOr keyCriterion;
    
    float x, y, z;
    
Matrix4d matrix = new Matrix4d();
    
    public Picking(TransformGroup value)
    {
            referencia = value;
            
            x = 0;
            y = 0;
            z = 0;
    }
    
    
    
    @Override
    public void initialize() {
        //setEnable(false); // El pick comienza deshabilitado esperando al initSearch
        //condicionRespuesta = new WakeupOnElapsedFrames(0);
        //wakeupOn(condicionRespuesta);
        
        
        
        Eventos[0] = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
        Eventos[1] = new WakeupOnElapsedFrames(0);
        
        keyCriterion = new WakeupOr(Eventos);
        
        wakeupOn(keyCriterion);
    }

    @Override
    public void processStimulus(Enumeration criterios) {
        
       
        WakeupCriterion wakeup = null;
        AWTEvent[] event;
        boolean teclaCorrecta = true ;

        while (criterios.hasMoreElements()) {
            wakeup = (WakeupCriterion) criterios.nextElement();

            if (!(wakeup instanceof WakeupOnAWTEvent)) {
                continue;
            }

            event = ((WakeupOnAWTEvent) wakeup).getAWTEvent();

            KeyEvent tecla = (KeyEvent) event[0];
            
            switch ( tecla.getKeyCode( ) ) {
            
            
            
            case KeyEvent.VK_LEFT:
            
                
                
            transformNueva.rotY(Math.PI / 100);
            referencia.getTransform(transformAntigua);
            transformAntigua.get(matrix);
            transformAntigua.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            transformAntigua.mul(transformNueva);
            transformAntigua.setTranslation(new Vector3d(matrix.m03, matrix.m13,matrix.m23));
            referencia.setTransform(transformAntigua);
            
            uno.rotZ(Math.PI / -100);
            referencia.getTransform(dos);
            dos.get(matrix);
            dos.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            dos.mul(uno);
            dos.setTranslation(new Vector3d(matrix.m03, matrix.m13,matrix.m23));
            referencia.setTransform(dos);
            
            
            
            
            break ;
                
                
            case KeyEvent.VK_RIGHT:
            
            
            transformNueva.rotY(Math.PI / -100);
            referencia.getTransform(transformAntigua);
            transformAntigua.get(matrix);
            transformAntigua.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            transformAntigua.mul(transformNueva);
            transformAntigua.setTranslation(new Vector3d(matrix.m03, matrix.m13,matrix.m23));
            referencia.setTransform(transformAntigua);
            
            
            uno.rotZ(Math.PI / 700);
            referencia.getTransform(dos);
            dos.get(matrix);
            dos.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            dos.mul(uno);
            dos.setTranslation(new Vector3d(matrix.m03, matrix.m13,matrix.m23));
            referencia.setTransform(dos);
            
            
            
            break ;
                
            case KeyEvent.VK_UP:
                
                if(y < 0)
                {
                    y = 0;
                }
                y = (float) (y + 0.008); 
                /*
                transformNueva.set(new Vector3d(0.0f, y, 0.00f));
                referencia.getTransform(transformAntigua);
                transformAntigua.mul(transformNueva);
                referencia.setTransform(transformAntigua);
                */
                uno.rotX(Math.PI / -70);
                referencia.getTransform(dos);
                dos.get(matrix);
                dos.setTranslation(new Vector3d(0.0, 0.0, 0.0));
                dos.mul(uno);
                dos.setTranslation(new Vector3d(matrix.m03, matrix.m13,matrix.m23));
                referencia.setTransform(dos);
                
                
                break ;
            case KeyEvent.VK_DOWN:
                if(y > 0)
                {
                    y = 0;
                }
                y = (float) (y - 0.01) ;
                /*
                transformNueva.set(new Vector3d(0.0f, y, 0.0f));
                referencia.getTransform(transformAntigua);
                transformAntigua.mul(transformNueva);
                referencia.setTransform(transformAntigua);
                */
                uno.rotX(Math.PI / 100);
                referencia.getTransform(dos);
                dos.get(matrix);
                dos.setTranslation(new Vector3d(0.0, 0.0, 0.0));
                dos.mul(uno);
                dos.setTranslation(new Vector3d(matrix.m03, matrix.m13,matrix.m23));
                referencia.setTransform(dos);
                break ;
           
            default : teclaCorrecta = false ; break ; }

            
        }
        
        
        /*    
        WakeupOnAWTEvent unCriterio = (WakeupOnAWTEvent) criterios[0];
        AWTEvent[ ] eventos = unCriterio.getAWTEvent();
        KeyEvent tecla = (KeyEvent) eventos[0];
        boolean teclaCorrecta = true ;
       */
        
       
    
   
   transformNueva.set(new Vector3d(0.0f, 0.0f, 0.1f));
   referencia.getTransform(transformAntigua);
   transformAntigua.mul(transformNueva);
   referencia.setTransform(transformAntigua);
   
   /*
            transformNueva.set(new Vector3d(x, y, z));
            referencia.getTransform(transformAntigua);
            transformAntigua.mul(transformNueva);
            referencia.setTransform(transformAntigua);

**/
   
   
   
        wakeupOn(keyCriterion);
        
    }
    
}
