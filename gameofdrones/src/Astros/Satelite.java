/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import Model.TheView;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import java.util.ArrayList;
import javax.media.j3d.BranchGroup;


/**
 *
 * @author CARLOS
 */
public class Satelite extends AstroOpaco {
    
    private final BranchGroup bg;

    public Satelite(float radio, String textureString, int velocidadRotacion, double distanciaPlaneta, int velocidadRotacionPlaneta){
        
        this.radio = radio;
        bg = new BranchGroup();
        bg.addChild(
                new Sphere (radio, 
                    Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 
                    64, getAppearance(textureString) ));

        crearRotacionPropia(velocidadRotacion);
        crearTranslacion(distanciaPlaneta);
        crearRotacionSobreAlgo(velocidadRotacionPlaneta);

        rotacionPropia.addChild(bg);
        desplazamiento.addChild(rotacionPropia);
        rotacionCentro.addChild(desplazamiento);
        
        this.addChild(rotacionCentro);
    }
    
    public void addView(TheView camera)
    {
        bg.addChild(camera);
    }
    
}

