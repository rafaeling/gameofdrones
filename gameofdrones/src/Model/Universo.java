package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingBox;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.Texture;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author carlos
 */
public class Universo {

    final private Escena scene;
    final private Background background;
    final private TheView camaraPe;
    //private TheView camaraPa;
    final private TheView camaraNave;
    //private TheView camaraLuna;

    public Universo (Canvas3D canvasModificar) {
      
        VirtualUniverse universe = new VirtualUniverse(); // Raíz del grafo
        Locale locale = new Locale(universe); // Origen de coordenadas  

        //camaraPa = new TheView(Camara.PLANTA, canvasFijo, new Point3d (0,200,0), new Point3d (0,0,0), new Vector3d (0,0,-1));
        camaraPe = new TheView(Camara.PERSPECTIVA, canvasModificar, new Point3d (80,80,80), new Point3d (0,0,0), new Vector3d (0,1,0));
        camaraNave = new TheView(Camara.NAVE, canvasModificar, new Point3d (0,1.3,-8), new Point3d (0,1,1), new Vector3d (0,1,6));
        
        //camaraPa.compile();
        camaraPe.compile();

        //camaraPa.activate();
        camaraPe.activate();

        //locale.addBranchGraph(camaraPa);
        locale.addBranchGraph(camaraPe);
      
        // BranchGroup para el Mundo
        BranchGroup root = new BranchGroup();
    
        background = new Background();
//        background.setApplicationBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0)); 
        background.setApplicationBounds(new BoundingBox (new Point3d(-5000, -5000, -5000), new Point3d(5000, 5000, 5000)));
        Appearance app = new Appearance();
        //ColoringAttributes unColorPlano = new ColoringAttributes( new Color3f(0,0,0) , ColoringAttributes.SHADE_FLAT) ;
        //app.setColoringAttributes(unColorPlano);
        Texture aTexture = new TextureLoader ("imgs/fondo4.jpg", null).getTexture();
        //aTexture.setMinFilter(Texture.MULTI_LEVEL_POINT);
        app.setTexture (aTexture);
        //app.setTexture(txtr);

        Primitive sphere = new Sphere (1.0f, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD, app);

        BranchGroup bgGeometry = new BranchGroup();
        bgGeometry.addChild(sphere);
        background.setGeometry(bgGeometry);
    
        root.addChild(background);

        // Se crea y se añade la escena al universo
        //scene = new Escena (camaraNave, camaraLuna);
        scene = new Escena (camaraNave);
        
        // Se optimiza la escena y se cuelga del universo
        root.compile();
        scene.compile();
        
        //Añadimos a la raiz el universo con la escena
        locale.addBranchGraph(root);
        locale.addBranchGraph(scene);
       // picking.initSearch(scene);
  }
  
  
  // ******* Public
  
    public Escena getScene(){ return scene; }
    
    public void closeApp (int code) {
      System.exit (code);
    }
  
  
    public void camaraPerspectiva()
    {
        System.out.println("PERSPECTIVA");
        camaraNave.deactivate();
        //camaraLuna.deactivate();
        camaraPe.activate();
        
    }
    
    public void camaraLuna()
    {
        camaraPerspectiva();
//        System.out.println("LUNA");
  //      camaraPe.deactivate();
    //    camaraNave.deactivate();
      //  camaraLuna.activate();
        
    }
    
    public void camaraNave()
    {
        System.out.println("NAVE");
        camaraPe.deactivate();
        //camaraLuna.deactivate();
        camaraNave.activate();
    }
}
