/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import com.sun.j3d.utils.image.TextureLoader;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Color3f;

/**
 *
 * @author CARLOS
 */
public class Anillo extends TransformGroup {
    
    double radio;
    
    TransformGroup tf;
    
    double inclinacion;
    
    public Anillo(double anchura, double radioGrande, double radioPeq, String textura){
        
        BranchGroup bg = new BranchGroup();
    
        Shape3D shape;
                
        double [] coordenadasArriba = new double[360*2*3];
        double [] coordenadasAbajo = new double[360*2*3];
        double [] coordenadasExterno = new double[360*2*3];
        double [] coordenadasInterno = new double[360*2*3];
        
        TriangleStripArray arriba, abajo, externo,interno;
        
        int [] cadenas = { 360*2 };
                
        for(int i=0; i<360;i++){
            coordenadasArriba[(i*6)+0] = radioGrande*toRadians(cos(i));
            coordenadasArriba[(i*6)+1] = anchura/2;
            coordenadasArriba[(i*6)+2] = radioGrande*toRadians(sin(i));
            coordenadasArriba[(i*6)+3] = radioPeq*toRadians(cos(i));
            coordenadasArriba[(i*6)+4] = anchura/2;
            coordenadasArriba[(i*6)+5] = radioPeq*toRadians(sin(i));

            coordenadasAbajo[(i*6)+0] = radioGrande*toRadians(sin(i));
            coordenadasAbajo[(i*6)+1] = - anchura/2;
            coordenadasAbajo[(i*6)+2] = radioGrande*toRadians(cos(i));
            coordenadasAbajo[(i*6)+3] = radioPeq*toRadians(sin(i));
            coordenadasAbajo[(i*6)+4] = - anchura/2;
            coordenadasAbajo[(i*6)+5] = radioPeq*toRadians(cos(i));
            
            coordenadasExterno[(i*6)+0] = radioGrande*toRadians(sin(i));
            coordenadasExterno[(i*6)+1] = anchura/2;
            coordenadasExterno[(i*6)+2] = radioGrande*toRadians(cos(i));
            coordenadasExterno[(i*6)+3] = radioGrande*toRadians(sin(i));
            coordenadasExterno[(i*6)+4] = - anchura/2;
            coordenadasExterno[(i*6)+5] = radioGrande*toRadians(cos(i));

            coordenadasInterno[(i*6)+0] = radioPeq*toRadians(cos(i));
            coordenadasInterno[(i*6)+1] = anchura/2;
            coordenadasInterno[(i*6)+2] = radioPeq*toRadians(sin(i));
            coordenadasInterno[(i*6)+3] = radioPeq*toRadians(cos(i));
            coordenadasInterno[(i*6)+4] = - anchura/2;
            coordenadasInterno[(i*6)+5] = radioPeq*toRadians(sin(i));
        }
        
        arriba = new TriangleStripArray(coordenadasArriba.length/3, GeometryArray.COORDINATES, cadenas);
        arriba.setCoordinates(0, coordenadasArriba);
        
        abajo = new TriangleStripArray(coordenadasAbajo.length/3, GeometryArray.COORDINATES, cadenas);
        abajo.setCoordinates(0, coordenadasAbajo);
        
        externo = new TriangleStripArray(coordenadasExterno.length/3, GeometryArray.COORDINATES, cadenas);
        externo.setCoordinates(0,coordenadasExterno);
        
        interno = new TriangleStripArray(coordenadasInterno.length/3, GeometryArray.COORDINATES, cadenas);
        interno.setCoordinates(0,coordenadasInterno);
        
        shape = new Shape3D(arriba);
        
        shape.insertGeometry(abajo, 0);
        shape.insertGeometry(externo, 0);
        shape.insertGeometry(interno, 0);
        
        shape.setAppearance(getAppearance(textura));
         
        bg.addChild(shape);
        
        tf = new TransformGroup();
        
        Transform3D tf3 = new Transform3D();
        
        tf3.rotZ(inclinacion);
        
        tf.setTransform(tf3);
        
        tf.addChild(bg);
        
        this.addChild(tf);
        
    }
    private Appearance getAppearance(String textura){
        
        Appearance ap = new Appearance();
        
        Texture aTexture = new TextureLoader (textura, null).getTexture();
        ap.setTexture (aTexture);
        
        ColoringAttributes unColorPlano = new ColoringAttributes( new Color3f(0.6f,0.6f,0.6f) , ColoringAttributes.SHADE_FLAT);
        ap.setColoringAttributes(unColorPlano);
        
        ap.setMaterial (new Material (
            new Color3f (0.10f, 0.10f, 0.10f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.90f, 0.90f, 0.90f),   // Color difuso
            new Color3f (0.10f, 0.10f, 0.10f),   // Color especular
            6.0f ));                            // Brillo
        ap.setTransparencyAttributes( new TransparencyAttributes ( TransparencyAttributes.BLENDED, 0.94f) );
        return ap;
    }
}