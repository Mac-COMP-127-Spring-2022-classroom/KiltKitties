package cats;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;


public class WindowManager {
    public static final int CANVAS_WIDTH = 1200;
    public static final int CANVAS_HEIGHT = 800;
    public CanvasWindow canvas;
    private static List<File> initialCatList = new ArrayList<File>();
    private static List<File> boughtCatList = new ArrayList<File>();
    private static List<File> selectedCatList = new ArrayList<File>();
    private static List<Cat> selectedCatlistCats = new ArrayList<Cat>();
    private int currencyAvailable;
    private Button buyButton;
    private Button sellButton;
    public Cat cat1;
    public Cat cat2;
    public Cat cat3;

    public Boolean selected = false;
    private Image catImage1;
    // private Image catImage2;
    // private Image catImage3;
    // private Rectangle selectedRectangle;
    private File catFile1;
    private File catFile2;
    private File catFile3;
    private Market market;
    private int count;
    private Map<File, Double> imageXCoor = new HashMap<File, Double>();
    private Map<File, Double> imageYCoor = new HashMap<File, Double>();
    private Map<File, Cat> fileCatMap = new HashMap<File, Cat>();
    private GraphicsText purchaseError1;
    private GraphicsText purchaseError2;
    private GraphicsText saleError1;
    private GraphicsText saleError2;
    private GraphicsText currencyCount;
    private ArrayList<Rectangle> selectedRectangles = new ArrayList<>();
// map.put("dog", "type of animal");
// System.out.println(map.get("dog"));
  
public WindowManager(){

    market = new Market(1000);
    cat1 = new Cat("marvin", 1, 1, 1, 1);
    cat2 = new Cat("matthew", 1, 1, 1, 1);
    cat3 = new Cat("nevis", 1, 1, 1, 1);
    cat2.setFurColor(55, 110, 22);
    cat3.setBellyColor(12, 80, 96);
    canvas = new CanvasWindow("Cats!", CANVAS_WIDTH, CANVAS_HEIGHT);
    catImage1 = new Image(cat1.getFilepathKilt());
    // catImage2 = new Image(cat2.getFilepathKilt());
    // catImage3 = new Image(cat3.getFilepathKilt());
    purchaseError1 = new GraphicsText("Cat must be selected to make purchase");
    purchaseError2 = new GraphicsText("Only one cat can be purchased at a time");
    saleError1 = new GraphicsText("Cat must be selected to make sale");
    saleError2 = new GraphicsText("Only one cat can be sold at a time");
    currencyCount = new GraphicsText("Available Currency: 1000");
    currencyCount.setPosition(50, 700);
    canvas.add(currencyCount);
    currencyAvailable = 1000;
    buyButton = new Button("Buy");
    buyButton.setPosition(CANVAS_WIDTH*0.4 - buyButton.getWidth(), CANVAS_HEIGHT*0.7);
    buyButton.onClick(() ->{  
        if (canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23)!=null){
            canvas.remove(canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23));
        }
        if (canvas.getElementAt(getCenterX(buyButton)-purchaseError2.getWidth()*0.5+2, getCenterY(buyButton)+23)!=null){
            canvas.remove(canvas.getElementAt(getCenterX(buyButton)-purchaseError2.getWidth()*0.5+2, getCenterY(buyButton)+23));
        }
        if(selectedCatList.size() == 1){
            buyCat(selectedCatList.get(0).toString(), 100);
        }
        else if (selectedCatList.isEmpty()){
       
            purchaseError1.setPosition(getCenterX(buyButton)-purchaseError1.getWidth()*0.5, getCenterY(buyButton)+25);
      
            canvas.add(purchaseError1);
        }
        else{
            purchaseError2.setPosition(getCenterX(buyButton)-purchaseError2.getWidth()*0.5, getCenterY(buyButton)+25);
            canvas.add(purchaseError2);
        }
    });
    sellButton = new Button("Sell");
    sellButton.setPosition(CANVAS_WIDTH*0.6, CANVAS_HEIGHT*0.7);
    sellButton.onClick(() ->{
        if (canvas.getElementAt(getCenterX(sellButton)-saleError1.getWidth()*0.5+2, getCenterY(sellButton)+23)!=null){
            canvas.remove(canvas.getElementAt(getCenterX(sellButton)-saleError1.getWidth()*0.5+2, getCenterY(sellButton)+23));
        }
        if (canvas.getElementAt(getCenterX(sellButton)-saleError2.getWidth()*0.5+2, getCenterY(sellButton)+23)!=null){
            canvas.remove(canvas.getElementAt(getCenterX(sellButton)-saleError2.getWidth()*0.5+2, getCenterY(sellButton)+23));
        }
        if(selectedCatList.size() == 1){
            sellCat(selectedCatList.get(0).toString(), 100);
        }
        else if (selectedCatList.isEmpty()){
       
            saleError1.setPosition(getCenterX(sellButton)-saleError1.getWidth()*0.5, getCenterY(sellButton)+25);
      
            canvas.add(saleError1);
        }
        else{
            saleError2.setPosition(getCenterX(sellButton)-saleError2.getWidth()*0.5, getCenterY(sellButton)+25);
            canvas.add(saleError2);
        }
    });
    canvas.add(buyButton);
    canvas.add(sellButton);
  
    catFile1 = new File(cat1.getFilepathKilt());
    initialCatList.add(catFile1);
    fileCatMap.put(catFile1, cat1);
    catFile2 = new File(cat2.getFilepathKilt());
    initialCatList.add(catFile2);
    fileCatMap.put(catFile2, cat2);
    catFile3 = new File(cat3.getFilepathKilt());
    initialCatList.add(catFile3);
    fileCatMap.put(catFile3, cat3);
    count = 0;
    
}  


public static void main(String[] args){
    WindowManager windowManager = new WindowManager();
    // windowManager.addCatPng(120,120);
    windowManager.addCatPngs(initialCatList);

}
public void buyCat(String catName, int price){
    // File catFile = new File(catName);
    // if(!boughtCatList.contains(catFile)){
        // selectedCatlistCats.add()
        // selectedRectangles.forEach(canvas::remove);
        // selectedRectangles.clear();
        // selectedCatList.remove(catFile);
        // boughtCatList.add(catFile);
        Cat newCat = market.buyCat(catName);
        selectedCatlistCats.add(newCat);
        // change to be randomly generated name.tofilepath instead of catFile when mapping
        fileCatMap.put(new File(newCat.getFilepath()), newCat);
        // currencyAvailable -= price;
        // String s = String.valueOf(currencyAvailable); 
        // currencyCount.setText("Available Currency: " +(s));
        canvas.draw();
        System.out.println("Available currency: " + currencyAvailable);
        System.out.println("Cats owned: " + boughtCatList);
    }



public void sellCat(String fileName, int price){
    File catFile = new File(fileName);
    if(boughtCatList.contains(catFile)){
        selectedRectangles.forEach(canvas::remove);
        selectedRectangles.clear();
        selectedCatList.remove(catFile);
        boughtCatList.remove(catFile);
        market.sellCat(fileCatMap.get(catFile));
        // currencyAvailable += price;
        // String s = String.valueOf(currencyAvailable);
        // currencyCount.setText("Available Currency: " +(s));
        canvas.draw();
        if(canvas.getElementAt(imageXCoor.get(catFile), imageYCoor.get(catFile))!=null){
            canvas.remove(canvas.getElementAt(imageXCoor.get(catFile), imageYCoor.get(catFile)));
        }
        // canvas.remove(canvas.getElementAt(imageXCoor.get(catFilePath)+20, imageYCoor.get(catFilePath)-20));
        System.out.println("Available currency:  " + currencyAvailable);
        System.out.println("Cats owned: " + boughtCatList);
    }

}


private void addCatPngs(List<File> catList){ 
    double x = 0;
    double y = 0;
    for(File i : catList){
        Image catImage = new Image(i.toString());
        catImage.setCenter(120 + x, 120 + y);
        imageXCoor.put(i, 120 + x);
        imageYCoor.put(i, 120 + y);
        // System.out.println(imageCoor.get("dog"));  
        canvas.add(catImage);
        addCatButton(120 + x, 120 + y, i);
        x += catImage.getImageWidth() + 20;
    }
}

private int selectedCat(File filePath){
    count = selectedCatList.size();
    return count;
}
public double getCenterX(Button myButton){
    double centerX = myButton.getX() + myButton.getWidth()*0.5;
    return centerX;
}

public double getCenterY(Button myButton){
    double centerY = myButton.getY() + myButton.getHeight()*0.5;
    return centerY;
}




private void addCatButton(double x, double y, File filePath){
    Button catButton = new Button("Select");
    catButton.setCenter(x, y + catImage1.getHeight()*0.5);
    catButton.onClick(() -> {
        if (!selectedCatList.contains(filePath)) {
            selectedCatList.add(filePath);
            Rectangle selectedRectangle = new Rectangle(x-catImage1.getHeight()/1.8, y-catImage1.getHeight()/4, catImage1.getWidth()*1.1, catImage1.getHeight()*1.1);
            selectedRectangle.setStrokeColor(Color.GREEN);
            selectedRectangle.setStrokeWidth(5);
            selectedRectangle.setCenter(x, y+2);
            canvas.add(selectedRectangle);
            selectedRectangles.add(selectedRectangle);
        }
 
    });
    canvas.add(catButton);

}

}