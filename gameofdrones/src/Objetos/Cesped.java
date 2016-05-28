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
import com.sun.j3d.utils.image.TextureLoader;
import java.io.FileNotFoundException;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

public class Cesped extends BranchGroup{
    
    TransformGroup tg;
    BranchGroup bg;
    private Material m;

    public Cesped(double coordenadaX, double coordenadaY, double coordenadaZ, double escala, String url){
        
        bg = new BranchGroup();
        
        
        Appearance ap = new Appearance();
        
        m = new Material (
            new Color3f (0.00f, 0.00f, 0.00f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.90f),   // Color emisivo
            new Color3f (0.00f, 0.00f, 0.00f),   // Color difuso
            new Color3f (1.00f, 1.00f, 1.00f),   // Color especular
            17.0f );                            // Brillo
        
        m.setCapability(Material.ALLOW_COMPONENT_WRITE);
        
        ap.setMaterial (m);
        
        Texture aTexture = new TextureLoader ("imgs/naveEspacial/Gras_mitWeg.png", null).getTexture();
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
        
        
        
        Transform3D desplazamiento = new Transform3D();
        desplazamiento.setTranslation(new Vector3d(coordenadaX,  coordenadaY, coordenadaZ));
        desplazamiento.setScale(escala);
        tg = new TransformGroup(desplazamiento);
        
        

        Scene modelo = null; 
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE );
        try {
            modelo = archivo.load (url);
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
   
    
    
}
