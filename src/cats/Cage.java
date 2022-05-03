package cats;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.ui.Button;

public class Cage {
    private Grid grid;
    private boolean inhabited;
    private int width, height;
    private int xCenter, yCenter;
    private Image image;
    private boolean selected;
    private GraphicsGroup cageShape;
    private Button selectButton = new Button("Select");

    public Cage(int xCenter, int yCenter, int width, int height, Grid grid) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.width = width;
        this.height = height;
        this.inhabited = false;
        this.cageShape = new GraphicsGroup(width, height);
        this.cageShape.add(new Button("Select"));
        selectButton.onClick(() -> {
            // select(cage);
        });
    }

    public void inhabited(Cat cat) {
        this.inhabited = true;
        // this.image
        

    } 
    
}
