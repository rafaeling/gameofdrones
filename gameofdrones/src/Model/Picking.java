/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Astros.*;
import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.WakeupOnAWTEvent;

/**
 *
 * @author rafael
 */
public class Picking extends Behavior{

    PickCanvas pickcanvas;
    Canvas3D canvas;
    private WakeupOnAWTEvent condition;
    
    public Picking(Canvas3D aCanvas)
    {
            canvas = aCanvas ;
            condition = new WakeupOnAWTEvent (MouseEvent.MOUSE_CLICKED) ;
    }
    
    
    //Método disponible en las diapositivas
    public void initSearch(BranchGroup bg) {
        pickcanvas = new PickCanvas(canvas, bg);
        pickcanvas.setTolerance(0.0f);
        pickcanvas.setMode(PickInfo.PICK_GEOMETRY);
        pickcanvas.setFlags(PickInfo.SCENEGRAPHPATH | PickInfo.CLOSEST_INTERSECTION_POINT);
        setEnable(true);
        
    }
    
    @Override
    public void initialize() {
        setEnable(false); // El pick comienza deshabilitado esperando al initSearch
        wakeupOn(condition);
    }

    @Override
    public void processStimulus(Enumeration enmrtn) {
    
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) enmrtn.nextElement();
        AWTEvent[] e = c.getAWTEvent();
        MouseEvent mouse = (MouseEvent) e [0];
        pickcanvas.setShapeLocation (mouse);
        PickInfo pi = pickcanvas.pickClosest();
        
        
        //Comprobamos si contiene algo pi
        if(pi != null)
        {
            //Devuelve el camino de nodos del grafo,
            //desde el Locale hasta el nodo terminal clicado
            SceneGraphPath sgp = pi.getSceneGraphPath();
               
            try {
                if (sgp.nodeCount() == 2) {
                    AstroOpaco a = (AstroOpaco) sgp.getNode( 0 );
                    a.action();
                }
            } catch (Exception exc){
                System.err.println(exc);
            }
            
        }
        
        wakeupOn(condition);
    
    }
    
}
