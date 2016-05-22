package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Locale;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author carlos
 */
public class Universo {

    private Escena scene;
    private Background background;
    private TheView camaraPe;
    private TheView camaraPa;
    private TheView camaraNave;
    private TheView camaraLuna;
    private Picking picking;

        public Universo (Canvas3D canvasFijo, Canvas3D canvasModificar) {
      
        VirtualUniverse universe = new VirtualUniverse(); // Raíz del grafo
        Locale locale = new Locale(universe); // Origen de coordenadas  

        camaraPa = new TheView(Camara.PLANTA, canvasFijo, new Point3d (0,200,0), new Point3d (0,0,0), new Vector3d (0,0,-1));
        camaraPe = new TheView(Camara.PERSPECTIVA, canvasModificar, new Point3d (80,80,80), new Point3d (0,0,0), new Vector3d (0,1,0));
        camaraNave = new TheView(Camara.NAVE, canvasModificar, new Point3d (0,1.5,-4), new Point3d (0,0,1), new Vector3d (0,5,6));
        camaraLuna = new TheView(Camara.LUNA, canvasModificar, new Point3d (1.5,0.5,-0.9), new Point3d (-9,-0.4,-2.1), new Vector3d (0,0.1,0));

        camaraPa.compile();
        camaraPe.compile();

        camaraPa.activate();
        camaraPe.activate();

        locale.addBranchGraph(camaraPa);
        locale.addBranchGraph(camaraPe);
      
        // BranchGroup para el Mundo
        BranchGroup root = new BranchGroup();
    
        background = new Background();
        background.setApplicationBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));    
        Appearance app = new Appearance();
        ColoringAttributes unColorPlano = new ColoringAttributes( new Color3f(0,0,0) , ColoringAttributes.SHADE_FLAT) ;
        app.setColoringAttributes(unColorPlano) ;

        Primitive sphere = new Sphere (1.0f, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD, app);

        BranchGroup bgGeometry = new BranchGroup();
        bgGeometry.addChild(sphere);
        background.setGeometry(bgGeometry);
    
        root.addChild(background);

        // Se crea y se añade la escena al universo
        scene = new Escena (camaraNave, camaraLuna);

        
        // Se optimiza la escena y se cuelga del universo
        root.compile();
        scene.compile();
        
        //Añadimos a la raiz el universo con la escena
        locale.addBranchGraph(root);
        locale.addBranchGraph(scene);
        //HAY QUE PONER SINO NO VA EL PICK JODER!!!!!!!!!!!!!!!
       // picking.initSearch(scene);
  }
  
  
  // ******* Public
  
    public void closeApp (int code) {
      System.exit (code);
    }
  
  
    public void camaraPerspectiva()
    {
        System.out.println("PERSPECTIVA");
        camaraNave.deactivate();
        camaraLuna.deactivate();
        camaraPe.activate();
        
    }
    
    public void camaraLuna()
    {
        System.out.println("LUNA");
        camaraPe.deactivate();
        camaraNave.deactivate();
        camaraLuna.activate();
        
    }
    
    public void camaraNave()
    {
        System.out.println("NAVE");
        camaraPe.deactivate();
        camaraLuna.deactivate();
        camaraNave.activate();
    }
}
