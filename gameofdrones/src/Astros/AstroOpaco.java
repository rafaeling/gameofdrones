/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author CARLOS
 */
abstract public class AstroOpaco extends Astro {
    
    TransformGroup desplazamiento;
    
    TransformGroup rotacionCentro;
    
    Alpha valueRotacionSol;
    
  

    protected void crearRotacionSobreAlgo(int velocidad){
        TransformGroup transform = new TransformGroup ();
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Transform3D yAxis = new Transform3D ();
        
        valueRotacionSol = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velocidad, 0, 0, 0, 0, 0);
        RotationInterpolator rotator = new RotationInterpolator (valueRotacionSol, transform, yAxis, 0.0f, (float) Math.PI*2.0f);
        
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 10000.0));
        rotator.setEnable(true);
        transform.addChild(rotator);

        rotacionCentro = transform;
    }
    
    protected void crearTranslacion(double distancia){
        TransformGroup transform = new TransformGroup ();
        Transform3D tr = new Transform3D ();
        tr.setTranslation( new Vector3d(distancia,0,0) );
        
        transform.setTransform(tr);
        
        desplazamiento = transform;
    }
    
    protected Appearance getAppearance(String textureString){
        Appearance ap = new Appearance();
        Texture aTexture = new TextureLoader (textureString, null).getTexture();
        ap.setTexture (aTexture);

        ap.setMaterial (new Material (
            new Color3f (0.20f, 0.20f, 0.20f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.50f, 0.50f, 0.50f),   // Color difuso
            new Color3f (0.40f, 0.40f, 0.40f),   // Color especular
            6.0f ));                            // Brillo
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        ap.setTextureAttributes(ta);
        return ap;
    }

    
}
