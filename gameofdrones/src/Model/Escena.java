/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Objetos.Nave;
import Objetos.Aro;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;


/**
 *
 * @author CARLOS
 */
public class Escena extends BranchGroup {    
    
    Texture texturaEscena;
    Material luz;
    Nave nave;
    BranchGroup todo;
        
    public Escena(TheView camaraNave, TheView camaraInterior){
        
        todo = new BranchGroup();
        
        todo.setCapability(ALLOW_CHILDREN_WRITE);        
        todo.setCapability(ALLOW_CHILDREN_EXTEND);
        
        nave = new Nave();
         Transform3D subirNave3d = new Transform3D();
         subirNave3d.setTranslation(new Vector3f(0,4,0) );
         TransformGroup subirNave = new TransformGroup(subirNave3d);
         subirNave.addChild(nave);
        todo.addChild(subirNave);
        
        
        
        Appearance ap = new Appearance();
        ap.setMaterial (new Material (
            new Color3f (0.50f, 1.00f, 0.50f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.80f, 1.00f, 0.80f),   // Color difuso
            new Color3f (0.20f, 0.20f, 0.20f),   // Color especular
            0f ));                            // Brillo

        TextureLoader texturaImg = new TextureLoader ("imgs/cesped2.jpg", null);
        Texture textura = texturaImg.getTexture();
        ap.setTexture(textura);
        
        Box suelo = new Box(250, (float) 0.1, 250, Box.GENERATE_TEXTURE_COORDS, ap);
        
        Transform3D altitud = new Transform3D();
        altitud.setTranslation(new Vector3d(0,0,0) );
        TransformGroup bajarSuelo = new TransformGroup(altitud);
        bajarSuelo.addChild(suelo);
        //todo.addChild(bajarSuelo);
        
        todo.addChild(loadGrass());
        
        crearAros(20);
  
        crearLuces(this);
        this.addChild(todo);
        
        nave.addView(camaraNave);
        nave.addView(camaraInterior);
        
        Colision cl = new Colision(nave);
        todo.addChild(cl);
        
        for (int i=0; i<500; i++){
            Appearance apSp = new Appearance();
            apSp.setMaterial (new Material (
                new Color3f ( 0,0,0 ),   // Color ambiental
                new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
                new Color3f ( (float) Math.random(),(float) Math.random(),(float) Math.random() ),   // Color difuso
                new Color3f ( (float) Math.random(),(float) Math.random(),(float) Math.random() ),   // Color especular
                10f ));                            // Brillo
            
            TransparencyAttributes myTA = new TransparencyAttributes( );
            myTA.setTransparency( (float) Math.random() );
            myTA.setTransparencyMode( TransparencyAttributes.BLENDED );
            apSp.setTransparencyAttributes(myTA);
            
            float radius = (float) Math.random();
            Sphere sp = new Sphere(radius, apSp);
            Transform3D tfSp = new Transform3D();
            tfSp.setTranslation(new Vector3d(Math.random()*220.0 - 110.0 , Math.random()*20 , Math.random()*220.0 - 110.0));
            TransformGroup tgSp = new TransformGroup(tfSp);
            tgSp.addChild(sp);
            todo.addChild(tgSp);
        }
        
        for (int i=0; i<6; i++){
            suelo.getShape(i).setUserData("suelo");
        }
    }
    
    public void crearAros(int cantidad){
        for (int i=0; i<cantidad; i++){
            Aro aroNuevo = new Aro( Math.random()*200.0 - 100.0 , Math.random()*200.0 - 100.0 , (i*90) %360);
            todo.addChild(aroNuevo);
        }
    }
    
    private void crearLuces(BranchGroup bg){

        // LUZ AMBIENTE
         Light aLight;
         //aLight = new AmbientLight (new Color3f (0.2f, 0.2f, 0.2f));
         aLight = new AmbientLight (new Color3f (0.5f, 0.5f, 0.5f));
         aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));
         aLight.setEnable(true);
         bg.addChild(aLight);
         
        // LUZ DEL SOL
         aLight = new PointLight (true, new Color3f (0.5f, 0.5f, 1.0f), new Point3f(0,100,0), new Point3f(1,0,0));
         aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));
         aLight.setCapability(Light.ALLOW_STATE_WRITE);
         aLight.setEnable (true);
         bg.addChild(aLight);
    }
    
    private Shape3D loadGrass(){
        
        float [] coordenadas = {
            -125f , -0.05f , -125f , // Vértice 0. definición de los vértices del cubo
            +125f , -0.05f , -125f , // 1
            -125f , +0.05f , -125f , // 2
            +125f , +0.05f , -125f , // 3
            -125f , -0.05f , +125f , // 4
            +125f , -0.05f , +125f , // 5
            -125f , +0.05f , +125f , // 6
            +125f , +0.05f , +125f , // 7
            0,0,0 , 0,0,0 , 0,0,0 , 0,0,0 , 0,0,0
        };
        
        int [ ] indices = {
            0 , 2 , 3 , 1 , // Cara trasera // Vértices que conforman la cara
            4 , 5 , 7 , 6 , // Frontal
            1 , 3 , 7 , 5 , // Derecha
            0 , 4 , 6 , 2 , // Izquierda
            0 , 1 , 5 , 4 , // Inferior
            2 , 6 , 7 , 3   // Superior
        } ;
        
        float [ ] coordenadasTextura = {
            10*-0.00f , 10*0.00f , // 0
            10*-0.33f , 10*0.00f , // 1
            10*-0.67f , 10*0.00f , // 2
            10*-1.00f , 10*0.00f , // 3
            10*-0.00f , 10*0.50f , // 4
            10*-0.33f , 10*0.50f , // 5
            10*-0.67f , 10*0.50f , // 6
            10*-1.00f , 10*0.50f , // 7
            10*-0.00f , 10*1.00f , // 8
            10*-0.33f , 10*1.00f , // 9
            10*-0.67f , 10*1.00f , // 10
            10*-1.00f , 10*1.00f   // 11
        } ;
        
        int [ ] indicesTextura = {
            0 , 1 , 5 , 4 ,  // Cara trasera    // Ejemplo: Definición de los índices que indican,
            1 , 2 , 6 , 5 ,  // Frontal         // para cada vértice de cada cara,
            2 , 3 , 7 , 6 ,  // Derecha         // que coordenada de textura se tiene que usar
            4 , 5 , 9 , 8 ,  // Izquierda
            5 , 6 , 10 , 9 , // Inferior
            6 , 7 , 11 , 10  // Superior
        } ;
        
        IndexedQuadArray cubo = new IndexedQuadArray (
            coordenadas.length /3 ,
            IndexedQuadArray.COORDINATES |
            IndexedQuadArray.TEXTURE_COORDINATE_2,
            indices.length ) ;
        
        //float [] coordenadas_ = extendCoordinateVector(coordenadas, coordenadasTextura.length);
        
        cubo.setCoordinates (0 , coordenadas ) ;
        cubo.setCoordinateIndices (0 , indices ) ;
        cubo.setTextureCoordinates (0 , 0 , coordenadasTextura ) ;
        cubo.setTextureCoordinateIndices (0 , 0 , indicesTextura ) ;

        Appearance ap = new Appearance();
        ap.setMaterial (new Material (
            new Color3f (0.50f, 1.00f, 0.50f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.80f, 1.00f, 0.80f),   // Color difuso
            new Color3f (0.20f, 0.20f, 0.20f),   // Color especular
            0f ));                            // Brillo

        TextureLoader texturaImg = new TextureLoader ("imgs/cesped2.jpg", null);
        Texture textura = texturaImg.getTexture();
        ap.setTexture(textura);
        
        Shape3D shape = new Shape3D();
        shape.setGeometry(cubo);
        shape.setAppearance(ap);
        
        shape.setUserData("suelo");
        
        return shape;
    }
    
    // Función extraída de los apuntes. NO SÉ CÓMO FUNCIONA. Sirve para que no falle añadiendo vértices innecesarios
    // En verdad no sirve, no se llama en ningún sitio. Está aquí porque no la hemos quitado aún.
    private float [ ] extendCoordinateVector ( float [ ] coordinates , int textureCoordinateVectorSize ) {
        int nPoints = coordinates.length / 3 ;
        int nTextureCoordinates = textureCoordinateVectorSize / 2 ;
        if ( nPoints >= nTextureCoordinates ) {
            return coordinates ; 
        } else {
            int i , j ;
            int resultSize = 3 * nTextureCoordinates;
            float [] result = new float [resultSize] ;
            for ( i = 0; i < coordinates.length ; i++) {
                result[i] = coordinates[i] ; 
            }
            for ( j = i ; j < resultSize ; j++) { 
                result[j] = 0.0f ; 
            }
            return result ;
        }
    }

    
}
