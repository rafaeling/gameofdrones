/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.behaviors.mouse.MouseBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author rafael
 */
public class TheView extends BranchGroup{
    
    private View view;
    private Canvas3D canvas;
    private boolean activada;
      
    public TheView(Camara tipo, Canvas3D canvas, Point3d posicion , Point3d interes , Vector3d vUp )
    {
        this.canvas = canvas;
        this.activada = false;
        // TransformGroup para posicionar y orientar la vista
        
        this.setPickable(false);
        
        Transform3D transform = new Transform3D();
        
        transform.lookAt (posicion, interes, vUp);
        
        transform.invert();
        
        TransformGroup tg = new TransformGroup(transform);
        
        ViewPlatform vp = new ViewPlatform();

        //Solo asignamos zoom, rotación y trasalacion a la vista en perspectiva
        if(tipo == Camara.PERSPECTIVA){
            tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            
            MouseRotate myMouseRotate = new MouseRotate(MouseBehavior.INVERT_INPUT);
            myMouseRotate.setFactor(0.005);
            myMouseRotate.setTransformGroup(tg);
            myMouseRotate.setSchedulingBounds(new BoundingSphere(new Point3d(), 200.0));

            MouseTranslate myMouseTranslate = new MouseTranslate(MouseBehavior.INVERT_INPUT);
            myMouseTranslate.setFactor(0.1);
            myMouseTranslate.setTransformGroup(tg);
            myMouseTranslate.setSchedulingBounds(new BoundingSphere(new Point3d(), 200.0));

            MouseWheelZoom myMouseZoom = new MouseWheelZoom(MouseBehavior.INVERT_INPUT);
            myMouseZoom.setFactor(2.0);
            myMouseZoom.setTransformGroup(tg);
            myMouseZoom.setSchedulingBounds(new BoundingSphere(new Point3d(), 200.0));
            
            tg.addChild(myMouseRotate);
            tg.addChild(myMouseTranslate);
            tg.addChild(myMouseZoom);
        }
        
        tg.addChild(vp);


        //Definición de la vista
        
        view = new View();
        
        view.setPhysicalBody(new PhysicalBody());
        
        view.setPhysicalEnvironment (new PhysicalEnvironment());
        
        if(tipo == Camara.PERSPECTIVA)
        {
            
            view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
            
            view.setFieldOfView(45f);
            
            view.setFrontClipDistance(0.3f);
            
            view.setBackClipDistance(100.0f);
            
        }else if(tipo == Camara.PLANTA)
        {
            
            view.setProjectionPolicy(View.PARALLEL_PROJECTION);

            view.setScreenScalePolicy(View.SCALE_EXPLICIT);
            
            view.setScreenScale(0.002f);
            
            view.setFrontClipDistance(0.3f);
            
            view.setBackClipDistance(100.0f);
                
        }else if(tipo == Camara.NAVE)
        {
            
            view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
            
            view.setScreenScale(45.0f);
            
            view.setFrontClipDistance(0.1f);
            
            view.setBackClipDistance(30.0f);
            
        }else if(tipo == Camara.LUNA)
        {
            
            view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
            
            view.setScreenScale(100.0f);
            
            view.setFrontClipDistance(0.001f);
            
            view.setBackClipDistance(30.0f);
        }
      
        
        //Tenemos que dejar que se asigne el canvas en el momento justo,
        //porque si no una vista comparte el mismo canvas y da fallo 
        
        //view.addCanvas3D(canvas);
        
        view.attachViewPlatform(vp);
        
        this.addChild(tg);
        
    }
    
    public void deactivate()
    { 
        if(this.activada)
        {
            view.removeCanvas3D(this.canvas);
            this.activada = false;
        }
    }
    
    public void activate()
    {
        if(!this.activada)
        {
            view.addCanvas3D(this.canvas);
            this.activada = true;
        }
        
        
    }
    
}
