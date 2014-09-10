import com.jme3.app.SimpleApplication
import com.jme3.collision.CollisionResults
import com.jme3.input.MouseInput
import com.jme3.input.controls.ActionListener
import com.jme3.input.controls.AnalogListener
import com.jme3.input.controls.MouseButtonTrigger
import com.jme3.light.DirectionalLight
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.math.Ray
import com.jme3.math.Vector3f
import com.jme3.scene.Geometry
import com.jme3.scene.shape.Box
import com.jme3.scene.Node
import jme3tools.optimize.GeometryBatchFactory

class Blocknix extends SimpleApplication {

    def private int curCube = 0
    def private Node shootables = null

    public static void main(String[] args) {
        def app = new Blocknix()
        app.start()
    }

    @Override
    def void simpleInitApp() {
        shootables = new Node("shootables")
        rootNode.attachChild(shootables)

        flyCam.setMoveSpeed(30f)
        def material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
        material.setColor("Color", ColorRGBA.Yellow)

        def box = new Box(1000f, 0.1f, 1000f)
        def geometry = new Geometry("floor", box)
        geometry.setLocalTranslation(0, 0, 0)
        geometry.setMaterial(material)
        shootables.attachChild(geometry)

        def sun = new DirectionalLight()
        sun.setColor(ColorRGBA.Green)
        sun.setDirection(new Vector3f(0, -1, 0).normalizeLocal())
        rootNode.addLight(sun)

        initInput()
    }

    def void initInput()
    {
        inputManager.addMapping("Create", new MouseButtonTrigger(MouseInput.BUTTON_LEFT))
        inputManager.addListener(analogListener, "Create")
    }

    def analogListener = new ActionListener() {

        @Override
        void onAction(String name, boolean buttonPressed, float tpf) {
            if (name.equals("Create") && buttonPressed) {
                def results = new CollisionResults()
                def ray = new Ray(cam.getLocation(), cam.getDirection())
                shootables.collideWith(ray, results)
                def closest = results.getClosestCollision()
                def point = closest?.getContactPoint()
                addCube(point)
            }
        }
    }

    def void addCube(Vector3f point) {
        def box = new Box(0.5f, 0.5f, 0.5f)
        def geometry = new Geometry("cube${curCube}", box)
        def material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
        material.setColor("Color", ColorRGBA.Red)
        geometry.setMaterial(material)
        if (point != null) {
            geometry.setLocalTranslation((point.getX() + 0.25f).toFloat(), (point.getY() + 0.25f).toFloat(), (point.getZ() + 0.25f).toFloat())
        }
        shootables.attachChild(geometry)
        GeometryBatchFactory.optimize(shootables)
    }
}