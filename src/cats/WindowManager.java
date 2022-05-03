package cats;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
    // private static List<File> boughtCatList = new ArrayList<File>();
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
    private Map<Double, File> imageXCoorReverse = new HashMap<Double, File>();
    private Map<Double, File> imageYCoorReverse = new HashMap<Double, File>();
    private Map<File, Cat> fileCatMap = new HashMap<File, Cat>();
    private Map<Cat, File> catFileMap = new HashMap<Cat, File>();
    private GraphicsText purchaseError1;
    private GraphicsText purchaseError2;
    private GraphicsText saleError1;
    private GraphicsText saleError2;
    private GraphicsText currencyCount;
    private GraphicsText catName;
    private ArrayList<Rectangle> selectedRectangles = new ArrayList<>();
    private List<Double> availableXCoor = new ArrayList<Double>();
    private List<Double> availableYCoor = new ArrayList<Double>();
// map.put("dog", "type of animal");
// System.out.println(map.get("dog"));
  
public WindowManager(){

    market = new Market(1000);
    cat1 = new Cat("maca", 1, 1, 1, 1);
    cat2 = new Cat("lester", 1, 1, 1, 1);
    cat3 = new Cat("2022", 1, 1, 1, 1);
    cat2.setFurColor(55, 110, 22);
    cat3.setBellyColor(12, 80, 96);
    canvas = new CanvasWindow("Cats!", CANVAS_WIDTH, CANVAS_HEIGHT);
    catImage1 = new Image(cat1.getFilepathKilt());
    // catImage2 = new Image(cat2.getFilepathKilt());
    // catImage3 = new Image(cat3.getFilepathKilt());
    purchaseError1 = new GraphicsText("Insuffcient funds :(");
    purchaseError2 = new GraphicsText("Only one cat can be purchased at a time");
    saleError1 = new GraphicsText("Cat must be selected to make sale");
    saleError2 = new GraphicsText("Only one cat can be sold at a time");
    catName = new GraphicsText("Kitty");
    currencyCount = new GraphicsText("Available Currency: 1000");
    currencyCount.setPosition(50, 700);
    canvas.add(currencyCount);
    currencyAvailable = 1000;
    buyButton = new Button("Buy New Cat");
    buyButton.setPosition(CANVAS_WIDTH*0.4 - buyButton.getWidth(), CANVAS_HEIGHT*0.7);
    buyButton.onClick(() ->{  
        // if (canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23)!=null){
        //     canvas.remove(canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23));
        // }
        // if (canvas.getElementAt(getCenterX(buyButton)-purchaseError2.getWidth()*0.5+2, getCenterY(buyButton)+23)!=null){
        //     canvas.remove(canvas.getElementAt(getCenterX(buyButton)-purchaseError2.getWidth()*0.5+2, getCenterY(buyButton)+23));
        // }
        // if(selectedCatList.size() == 1){
        buyCat();
        // if(market.buyCat()==null){
        //     purchaseError1.setPosition(getCenterX(buyButton)-purchaseError1.getWidth()*0.5, getCenterY(buyButton)+25);
        //     canvas.add(purchaseError1);
        // }
       
        // else{
        //     if (canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23)!=null){
        //             canvas.remove(canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23));
        //         }
     
        // }
        // else if (selectedCatList.isEmpty()){
       
        //     purchaseError1.setPosition(getCenterX(buyButton)-purchaseError1.getWidth()*0.5, getCenterY(buyButton)+25);
      
        //     canvas.add(purchaseError1);
        // }
        // else{
        //     purchaseError2.setPosition(getCenterX(buyButton)-purchaseError2.getWidth()*0.5, getCenterY(buyButton)+25);
        //     canvas.add(purchaseError2);
        // }
    });
    sellButton = new Button("Sell");
    sellButton.setPosition(CANVAS_WIDTH*0.6, CANVAS_HEIGHT*0.7);
    sellButton.onClick(() ->{
        // if (canvas.getElementAt(getCenterX(sellButton)-saleError1.getWidth()*0.5+2, getCenterY(sellButton)+23)!=null){
        //     canvas.remove(canvas.getElementAt(getCenterX(sellButton)-saleError1.getWidth()*0.5+2, getCenterY(sellButton)+23));
        // }
        // if (canvas.getElementAt(getCenterX(sellButton)-saleError2.getWidth()*0.5+2, getCenterY(sellButton)+23)!=null){
        //     canvas.remove(canvas.getElementAt(getCenterX(sellButton)-saleError2.getWidth()*0.5+2, getCenterY(sellButton)+23));
        // }
        // // // if(selectedCatList.size() == 1){
        // // sellCat(selectedCatList.get(0).toString(), 100);
        // // }
        // if (selectedCatList.isEmpty()){
       
        //     saleError1.setPosition(getCenterX(sellButton)-saleError1.getWidth()*0.5, getCenterY(sellButton)+25);
      
        //     canvas.add(saleError1);
        // }
        // else if(selectedCatList.size()>=2){
        //     saleError2.setPosition(getCenterX(sellButton)-saleError2.getWidth()*0.5, getCenterY(sellButton)+25);
        //     canvas.add(saleError2);
        // }
        // else{
        sellCat(selectedCatList.get(0).toString(), 100);
        // }
    });
    canvas.add(buyButton);
    canvas.add(sellButton);
  
    catFile1 = new File(cat1.getFilepathKilt());
    initialCatList.add(catFile1);
    fileCatMap.put(catFile1, cat1);
    catFileMap.put(cat1, catFile1);
    catFile2 = new File(cat2.getFilepathKilt());
    initialCatList.add(catFile2);
    fileCatMap.put(catFile2, cat2);
    catFileMap.put(cat2, catFile2);
    catFile3 = new File(cat3.getFilepathKilt());
    initialCatList.add(catFile3);
    catFileMap.put(cat3, catFile3);
    fileCatMap.put(catFile3, cat3);

    // boughtCatList.add(catFile1);
    // boughtCatList.add(catFile2);
    // boughtCatList.add(catFile3);

    count = 0;
    
}  


public static void main(String[] args){
    WindowManager windowManager = new WindowManager();
    // windowManager.addCatPng(120,120);
    windowManager.addInitialCatPngs(initialCatList);

}
public void buyCat(){
    // File catFile = new File(catName);
    // if(!boughtCatList.contains(catFile)){
        // selectedCatlistCats.add()
        // selectedRectangles.forEach(canvas::remove);
        // selectedRectangles.clear();
        // selectedCatList.remove(catFile);
        // boughtCatList.add(catFile);
        Cat newCat = market.buyCat();
        //purchase error
        if(newCat==null){
            purchaseError1.setPosition(getCenterX(buyButton)-purchaseError1.getWidth()*0.5, getCenterY(buyButton)+25);
            canvas.add(purchaseError1);
        }
       
        else{
            if (canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23)!=null){
                    canvas.remove(canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23));
                    
                }
        }
        // selectedCatlistCats.add(newCat);
        // initialCatList.add(newCat);
        // change to be randomly generated name.tofilepath instead of catFile when mapping
        if(newCat!=null){
        fileCatMap.put(new File(newCat.getFilepathKilt()), newCat);
        catFileMap.put(newCat, new File(newCat.getFilepathKilt()));
        System.out.println(newCat.getFilepathKilt());
        canvas.pause(750);
        Image catImage = new Image(newCat.getFilepathKilt());
        // initialCatList.add(catFileMap.get(newCat));
        // catImage.setCenter(1000, 700);
        // imageXCoor.put(i, 120 + x);
        // imageYCoor.put(i, 120 + y);
        // System.out.println(imageCoor.get("dog"));  
       
        String s = String.valueOf(market.getMoney());
        currencyCount.setText("Available Currency: " +(s));
        catImage.setCenter(Collections.min(availableXCoor), 120);
        imageXCoor.put(catFileMap.get(newCat),Collections.min(availableXCoor));
        imageYCoor.put(catFileMap.get(newCat),Collections.min(availableYCoor));
        addCatButton(Collections.min(availableXCoor), 120, catFileMap.get(newCat));
        // String p = newCat.getName();
        // catName.setText(p);
        // catName.setCenter(CANVAS_WIDTH*0.5, CANVAS_HEIGHT*0.75);
        // canvas.add(catName);
        availableXCoor.remove(Collections.min(availableXCoor));
        canvas.add(catImage);

        // addCatButton(120 + x, 120 + y, i)
        // currencyAvailable -= price;
        // String s = String.valueOf(currencyAvailable); 
        // currencyCount.setText("Available Currency: " +(s));
        // canvas.draw();
        System.out.println("Available currency: " + currencyAvailable);
        }
        // System.out.println("Cats owned: " + boughtCatList);
    }



public void sellCat(String fileName, int price){
    File catFile = new File(fileName);
    // if(boughtCatList.contains(catFile)){
        // if((canvas.getElementAt(CANVAS_WIDTH*0.5, CANVAS_HEIGHT*0.75)!=null)){
        //     canvas.remove(canvas.getElementAt(CANVAS_WIDTH*0.5, CANVAS_HEIGHT*0.75));
        // }
        // canvas.remove(canvas.getElementAt(CANVAS_WIDTH*0.5, CANVAS_HEIGHT*0.75));
        // What is going on here
        if(selectedCatList.contains(catFile) && selectedCatList.size()==1){

        
        // selectedRectangles.forEach(canvas::remove);
        // selectedRectangles.clear();
        selectedCatList.remove(catFile);
        // boughtCatList.remove(catFile);
        market.sellCat(fileCatMap.get(catFile));
        String s = String.valueOf(market.getMoney());
        currencyCount.setText("Available Currency: " +(s));
        // currencyAvailable += price;
        // String s = String.valueOf(currencyAvailable);
        // currencyCount.setText("Available Currency: " +(s));
        // canvas.draw();
        // if(canvas.getElementAt(imageXCoor.get(catFile), imageYCoor.get(catFile))!=null){
            canvas.remove(canvas.getElementAt(imageXCoor.get(catFile), imageYCoor.get(catFile)));
            canvas.remove(canvas.getElementAt(imageXCoor.get(catFile), imageYCoor.get(catFile)));
            canvas.remove(canvas.getElementAt(imageXCoor.get(catFile), imageYCoor.get(catFile)+catImage1.getHeight()*0.5));
            availableXCoor.add(imageXCoor.get(catFile));
            availableYCoor.add(imageYCoor.get(catFile));
            canvas.draw();
        //     if(canvas.getElementAt(imageXCoor.get(catFile) + catImage1.getWidth()+20, imageYCoor.get(catFile))!=null){
        //         canvas.getElementAt(imageXCoor.get(catFile) + catImage1.getWidth()+20, imageYCoor.get(catFile))
        //         .setCenter(availableXCoor.get(0), availableYCoor.get(0));
        //         canvas.remove(canvas.getElementAt(imageXCoor.get(catFile) + catImage1.getWidth()+20, imageYCoor.get(catFile)+catImage1.getHeight()*0.5));
        //         addCatButton(availableXCoor.get(0), availableYCoor.get(0), imageXCoorReverse.get(imageXCoor.get(catFile) + catImage1.getWidth()+20));
   
        //         // canvas.getElementAt(imageXCoor.get(catFile) + catImage1.getWidth()+20, imageYCoor.get(catFile)+catImage1.getHeight()*0.5).
        //         // setCenter(availableXCoor.get(0), availableYCoor.get(0)+catImage1.getHeight()*0.5);
        //         imageXCoor.replace(imageXCoorReverse.get(imageXCoor.get(catFile) + catImage1.getWidth()+20), 
        //         availableXCoor.get(0));
        //         imageXCoor.remove(catFile);
        //         // imageXCoorReverse.put(120 + x, i);
        //         // imageYCoor.put(i, 120 + y);
        //         // imageYCoor.replace(imageYCoorReverse.get(imageYCoor.get(catFile)), 
        //         // availableYCoor.get(0));
        //         // canvas.remove(canvas.getElementAt(imageXCoor.get(catFile) + catImage1.getWidth()+20, imageYCoor.get(catFile)+catImage1.getHeight()*0.5));
        //         // System.out.println("TEST: " + imageXCoorReverse.get(imageXCoor.get(catFile) + catImage1.getWidth()+20));
        //         // addCatButton(availableXCoor.get(0), availableYCoor.get(0), imageXCoorReverse.get(imageXCoor.get(catFile) + catImage1.getWidth()+20));
        //         // .setCenter(availableXCoor.get(0), availableYCoor.get(0)+catImage1.getHeight()*0.5);
        //         availableXCoor.remove(0);
        //         availableYCoor.remove(0);
        //         // availableYCoor.remove(imageYCoor.get(catFile));

        //         // canvas.remove(canvas.getEl)
        //         canvas.draw();
        //     }
        //     else if (canvas.getElementAt(imageXCoor.get(catFile) + 2*(catImage1.getWidth()+20), imageYCoor.get(catFile))!=null){
        //         canvas.getElementAt(imageXCoor.get(catFile) + 2*(catImage1.getWidth()+20), imageYCoor.get(catFile))
        //         .setCenter(availableXCoor.get(0), availableYCoor.get(0));
        //         canvas.remove(canvas.getElementAt(imageXCoor.get(catFile) +2*(catImage1.getWidth()+20), imageYCoor.get(catFile)+catImage1.getHeight()*0.5));
        //         addCatButton(availableXCoor.get(0), availableYCoor.get(0), imageXCoorReverse.get(imageXCoor.get(catFile) +2*(catImage1.getWidth()+20)));
   
        //         // canvas.getElementAt(imageXCoor.get(catFile) + catImage1.getWidth()+20, imageYCoor.get(catFile)+catImage1.getHeight()*0.5).
        //         // setCenter(availableXCoor.get(0), availableYCoor.get(0)+catImage1.getHeight()*0.5);
        //         imageXCoor.replace(imageXCoorReverse.get(imageXCoor.get(catFile) + 2*(catImage1.getWidth()+20)), 
        //         availableXCoor.get(0));
        //         imageXCoor.remove(catFile);
        //         // imageXCoorReverse.put(120 + x, i);
        //         // imageYCoor.put(i, 120 + y);
        //         // imageYCoor.replace(imageYCoorReverse.get(imageYCoor.get(catFile)), 
        //         // availableYCoor.get(0));
        //         // canvas.remove(canvas.getElementAt(imageXCoor.get(catFile) + catImage1.getWidth()+20, imageYCoor.get(catFile)+catImage1.getHeight()*0.5));
        //         // System.out.println("TEST: " + imageXCoorReverse.get(imageXCoor.get(catFile) + catImage1.getWidth()+20));
        //         // addCatButton(availableXCoor.get(0), availableYCoor.get(0), imageXCoorReverse.get(imageXCoor.get(catFile) + catImage1.getWidth()+20));
        //         // .setCenter(availableXCoor.get(0), availableYCoor.get(0)+catImage1.getHeight()*0.5);
        //         availableXCoor.remove(0);
        //         availableYCoor.remove(0);
        //         // availableYCoor.remove(imageYCoor.get(catFile));

        //         // canvas.remove(canvas.getEl)
        //         canvas.draw();
        //     }
        // 
        // canvas.remove(canvas.getElementAt(imageXCoor.get(catFilePath)+20, imageYCoor.get(catFilePath)-20));
        System.out.println("Available currency:  " + currencyAvailable);
        // System.out.println("Cats owned: " + boughtCatList);
    // }
        }
    }



private void addInitialCatPngs(List<File> catList){ 
    double x = 0;
    double y = 0;
    for(File i : catList){
        Image catImage = new Image(i.toString());
        catImage.setCenter(120 + x, 120 + y);
        imageXCoor.put(i, 120 + x);
        imageXCoorReverse.put(120 + x, i);
        imageYCoor.put(i, 120 + y);
        imageYCoorReverse.put(120 + y, i);
        // System.out.println(imageCoor.get("dog"));  
        canvas.add(catImage);
        addCatButton(120 + x, 120 + y, i);
        x += catImage.getImageWidth() + 20;
    }
}

// private void addCatPng(List<File> catList){ 
//     double x = 0;
//     double y = 0;
//     for(File i : catList){
//         Image catImage = new Image(i.toString());
//         catImage.setCenter(120 + x, 120 + y);
//         imageXCoor.put(i, 120 + x);
//         imageXCoorReverse.put(120 + x, i);
//         imageYCoor.put(i, 120 + y);
//         imageYCoorReverse.put(120 + y, i);
//         // System.out.println(imageCoor.get("dog"));  
//         canvas.add(catImage);
//         addCatButton(120 + x, 120 + y, i);
//         x += catImage.getImageWidth() + 20;
//     }
// }

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
        if (!selectedCatList.contains(filePath) && selectedCatList.size()<2) {
            String p = fileCatMap.get(filePath).getName();
            catName.setText(p);
            catName.setCenter(CANVAS_WIDTH*0.5, CANVAS_HEIGHT*0.75);
            canvas.add(catName);
            selectedCatList.add(filePath);
            Rectangle selectedRectangle = new Rectangle(x-catImage1.getHeight()/1.8, y-catImage1.getHeight()/4, catImage1.getWidth()*1.1, catImage1.getHeight()*1.1);
            selectedRectangle.setStrokeColor(Color.GREEN);
            selectedRectangle.setStrokeWidth(5);
            selectedRectangle.setCenter(x, y+2);
            canvas.add(selectedRectangle);
            selectedRectangles.add(selectedRectangle);
        }
        else if(selectedCatList.contains(filePath)){
            canvas.remove(canvas.getElementAt(x-catImage1.getHeight()/1.8+2, y-catImage1.getHeight()/4-2));
        //     selectedRectangles.forEach(canvas::remove);
        //     selectedRectangles.clear();
            selectedCatList.remove(filePath);
       
        }
 
    });
    canvas.add(catButton);

}

}