
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.ui.Button;


public class WindowManager {
    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 800;
    public CanvasWindow canvas;
    private List<File> catList = new ArrayList<File>();
    private int currencyAvailable;
    private Button buyButton;
    private Button sellButton;
    public Cat cat;
    public Boolean selected = false;
    private Image catImage;
  
  
public WindowManager(){

    cat = new Cat("marvin");
    cat.setFurColor(55, 110, 22);
    cat.setBellyColor(12, 80, 96);
    canvas = new CanvasWindow("Cats!", CANVAS_WIDTH, CANVAS_HEIGHT);
    catImage = new Image(cat.getFilepathKilt());
    currencyAvailable = 1000;
    buyButton = new Button("Buy");
    buyButton.setPosition(CANVAS_WIDTH*0.4 - buyButton.getWidth(), CANVAS_HEIGHT*0.7);
    buyButton.onClick(() ->{ 
        if(selected){
            this.buyCat(cat.getFilepath(), 100);
        }
    });
    sellButton = new Button("Sell");
    sellButton.setPosition(CANVAS_WIDTH*0.6, CANVAS_HEIGHT*0.7);
    sellButton.onClick(() ->{ 
        if(selected){
            this.sellCat(cat.getFilepath(), 100);
        }
    });
    canvas.add(buyButton);
    canvas.add(sellButton);
  

}  


public static void main(String[] args){
    WindowManager windowManager = new WindowManager();
    windowManager.addCatPng(100,100);

 
}

public void buyCat(String fileName, int price ){
    File catFile = new File(fileName);
    catList.add(catFile);
    currencyAvailable -= price;
    System.out.println("Available currency: " + currencyAvailable);
    System.out.println("Cats owned: " + catList);


}

public void sellCat(String fileName, int price ){
    File catFile = new File(fileName);
    catList.remove(catFile);
    currencyAvailable += price;
    System.out.println("Available currency: " + currencyAvailable);
    System.out.println("Cats owned: " + catList);


}
// public void setTrue(Boolean bool){
//     bool = true;
// }

private void addCatPng(double x, double y){ 
    catImage.setCenter(x, y);
    canvas.add(catImage);
    addCatButton(x, y+catImage.getHeight()/2);
}


private void addCatButton(double x, double y){
    Button catButton = new Button("select");
    catButton.setCenter(x, y);
    catButton.onClick(() -> {
        selected = !selected;
    });
    canvas.add(catButton);

}



}