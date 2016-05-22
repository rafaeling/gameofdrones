/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;

/**
 *
 * @author CARLOS
 */
public class Estrella extends Astro {
    Material mt;
 //   Light luz;
    private final BranchGroup bg;
   
    public Estrella(float radio, String texture, int velocidadRotacion){
        
        this.radio = radio;
        bg = new BranchGroup();
        bg.addChild(
                new Sphere (radio, 
                    Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 
                    64, getAppearance(texture) ));
        
        crearRotacionPropia(velocidadRotacion);
        rotacionPropia.addChild(bg);
        
        this.addChild(rotacionPropia);

    }
    private Appearance getAppearance(String texture){
        Appearance ap = new Appearance();
        Texture aTexture = new TextureLoader (texture, null).getTexture();
        ap.setTexture (aTexture);
        ap.setMaterial (new Material (
            new Color3f (0.20f, 0.20f, 0.20f),   // Color ambiental
            new Color3f (1.00f, 0.95f, 0.90f),   // Color emisivo
            new Color3f (0.50f, 0.50f, 0.50f),   // Color difuso
            new Color3f (0.70f, 0.70f, 0.70f),   // Color especular
            17.0f ));                            // Brillo
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        
        ap.setTextureAttributes(ta);
        return ap;
    }
    public void addPlaneta(Planeta p){
        this.addChild(p);
    }
    
}
