/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Astros.Nave;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.Bounds;
import javax.media.j3d.Node;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;
import javax.media.j3d.WakeupOnCollisionMovement;
import javax.media.j3d.WakeupOr;

/**
 *
 * @author rafae
 */
public class Colision extends Behavior{

    private Bounds objetoA;
    private WakeupOnCollisionEntry condicion;
    Nave prueba;
    
    protected WakeupCriterion[] theCriteria;

 /** The OR of the separate criteria. */
     protected WakeupOr oredCriteria;
    
    
    public Colision (Nave n, Bounds b)
    {/*
            objetoA = unNodo;

            condicion = new WakeupOnCollisionEntry(objetoA, WakeupOnCollisionEntry.USE_BOUNDS);

            this.setSchedulingBounds(objetoA.getBounds());
*/
        setSchedulingBounds(b);
        prueba = n;
    }
    
    @Override
    public void initialize() {
    
        theCriteria = new WakeupCriterion[3];
        theCriteria[0] = new WakeupOnCollisionEntry(prueba);
        theCriteria[1] = new WakeupOnCollisionExit(prueba);
        theCriteria[2] = new WakeupOnCollisionMovement(prueba);
        oredCriteria = new WakeupOr(theCriteria);
        wakeupOn(oredCriteria);
        
    }

    @Override
    public void processStimulus(Enumeration criterio) {
    
        WakeupCriterion theCriterion = (WakeupCriterion) criterio.nextElement();
    if (theCriterion instanceof WakeupOnCollisionEntry) {
      Node theLeaf = ((WakeupOnCollisionEntry) theCriterion)
          .getTriggeringPath().getObject();
      System.out.println("Collided with " + theLeaf.getUserData());
    } else if (theCriterion instanceof WakeupOnCollisionExit) {
      Node theLeaf = ((WakeupOnCollisionExit) theCriterion)
          .getTriggeringPath().getObject();
      System.out.println("Stopped colliding with  "
      + theLeaf.getUserData());
    } else {
      Node theLeaf = ((WakeupOnCollisionMovement) theCriterion)
      .getTriggeringPath().getObject();
  System.out.println("Moved whilst colliding with "
      + theLeaf.getUserData());
    }
    wakeupOn(oredCriteria);
  }

    
    
}
