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
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;

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
    WakeupOnAWTEvent condicionRespuesta = new WakeupOnAWTEvent (KeyEvent.KEY_PRESSED);
    private Transform3D rotacion = new Transform3D ( ) ;
    private Transform3D transformAntigua = new Transform3D ( ) ;
    private Transform3D transformNueva = new Transform3D ( ) ;
    
    public Picking(TransformGroup laReferencia)
    {
            referencia = laReferencia;
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
                rotacion.rotZ (-0.009); break ;
            case KeyEvent.VK_RIGHT:
                rotacion.rotZ ( 0.009 ) ; break ;
            case KeyEvent.VK_UP:
                rotacion.rotX (-0.009) ; break ;
            case KeyEvent.VK_DOWN:
                rotacion.rotX ( 0.009 ) ; break ;
            default : teclaCorrecta = false ; break ; }
    
        referencia.getTransform ( transformAntigua ) ;
        
        transformNueva.mul ( rotacion , transformAntigua ) ;
        
        referencia.setTransform(transformNueva);
        
        wakeupOn(condicionRespuesta);
        
    }
    
}
