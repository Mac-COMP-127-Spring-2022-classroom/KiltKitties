package cats;
 
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
   private File catFile1;
   private File catFile2;
   private Market market;
   private int count;
   private Map<File, Double> imageXCoor = new HashMap<File, Double>();
   private Map<File, Double> imageYCoor = new HashMap<File, Double>();
//    private Map<Double, File> imageXCoorReverse = new HashMap<Double, File>();
//    private Map<Double, File> imageYCoorReverse = new HashMap<Double, File>();
   private Map<File, Cat> fileCatMap = new HashMap<File, Cat>();
   private Map<Cat, File> catFileMap = new HashMap<Cat, File>();
   private Map<Integer, Double> xPositionsMap = new HashMap<Integer, Double>();
   private Map<Integer, Double> yPositionsMap = new HashMap<Integer, Double>();
   private Map<List<Double>, File> coorToFile = new HashMap<List<Double>, File>();
   private Map<Integer, List<Double>> intToList = new HashMap<Integer, List<Double>>();
   private Map<List<Double>, Integer> listToInt = new HashMap<List<Double>, Integer>();
   private GraphicsText purchaseError1;
   private GraphicsText saleError1;
   private GraphicsText saleError2;
   private GraphicsText currencyCount;
   private GraphicsText catName;
   private ArrayList<Rectangle> selectedRectangles = new ArrayList<>();
   private double horizontalPadding;
   private double verticalPadding;
   private double availableX;
   private double availableY;
   private List<Double> one = Arrays.asList(120.0, 120.0);
   private List<Double> two = Arrays.asList(120.0 + horizontalPadding, 120.0);
   private List<Double> three =Arrays.asList(120.0 + 2*horizontalPadding, 120.0);
   private List<Double> four = Arrays.asList(120.0 + 3*horizontalPadding, 120.0);
   private List<Double> five = Arrays.asList(120.0 + 4*horizontalPadding, 120.0);
   private List<Double> six = Arrays.asList(120.0, 120.0 + verticalPadding);
   private List<Double> seven = Arrays.asList(120.0 + horizontalPadding, 120.0 + verticalPadding);
   private List<Double> eight = Arrays.asList(120.0 + 2*horizontalPadding, 120.0 + verticalPadding);
   private List<Double> nine = Arrays.asList(120.0 + 3*horizontalPadding, 120.0 + verticalPadding);
   private List<Double> ten = Arrays.asList(120.0 + 4*horizontalPadding, 120.0 + verticalPadding);

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
   horizontalPadding = catImage1.getImageWidth()+20;
   verticalPadding = catImage1.getImageHeight()+20;
   purchaseError1 = new GraphicsText("Insuffcient funds :(");
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
       buyCat();

   });
   sellButton = new Button("Sell");
   sellButton.setPosition(CANVAS_WIDTH*0.6, CANVAS_HEIGHT*0.7);
   sellButton.onClick(() ->{
       removeSalesErrors();

       if (selectedCatList.isEmpty()){
     
           saleError1.setPosition(getCenterX(sellButton)-saleError1.getWidth()*0.5, getCenterY(sellButton)+25);
    
           canvas.add(saleError1);
       }
       else if(selectedCatList.size()>=2){
           saleError2.setPosition(getCenterX(sellButton)-saleError2.getWidth()*0.5, getCenterY(sellButton)+25);
           canvas.add(saleError2);
       }
       else{
       sellCat(selectedCatList.get(0).toString(), 100);
       }
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


   availableX = 120 + 2*horizontalPadding;
   availableY = 120;

 
   count = 0;


   xPositionsMap.put(1, 120.0);
   xPositionsMap.put(2, 120 + horizontalPadding);
   xPositionsMap.put(3, 120 + 2*horizontalPadding);
   xPositionsMap.put(4, 120.0 + 3*horizontalPadding);
   xPositionsMap.put(5, 120.0 + 4*horizontalPadding);
   xPositionsMap.put(6, 120.0);
   xPositionsMap.put(7, 120.0 + horizontalPadding);
   xPositionsMap.put(8, 120.0 + 2*horizontalPadding);
   xPositionsMap.put(9, 120.0 + 3*horizontalPadding);
   xPositionsMap.put(10, 120.0 + 4*horizontalPadding);

   yPositionsMap.put(1, 120.0);
   yPositionsMap.put(2, 120.0);
   yPositionsMap.put(3, 120.0);
   yPositionsMap.put(4, 120.0);
   yPositionsMap.put(5, 120.0);
   yPositionsMap.put(6, 120.0+verticalPadding);
   yPositionsMap.put(7, 120.0+verticalPadding);
   yPositionsMap.put(8, 120.0+verticalPadding);
   yPositionsMap.put(9, 120.0+verticalPadding);
   yPositionsMap.put(10, 120.0+verticalPadding);

   //maps integer representing 10 positions to their coordinate lists
   intToList.put(1, one);
   intToList.put(2, two);
   intToList.put(3, three);
   intToList.put(4, four);
   intToList.put(5, five);
   intToList.put(6, six);
   intToList.put(7, seven);
   intToList.put(8, eight);
   intToList.put(9, nine);
   intToList.put(10, ten);

   //maps all lists of coordinates to an integer value representing 10 positions
   listToInt.put(one, 1);
   listToInt.put(two, 2);
   listToInt.put(three, 3);
   listToInt.put(four, 4);
   listToInt.put(five, 5);
   listToInt.put(six, 6);
   listToInt.put(seven, 7);
   listToInt.put(eight, 8);
   listToInt.put(nine, 9);
   listToInt.put(ten, 10);
  
} 
 
 
public static void main(String[] args){
   WindowManager windowManager = new WindowManager();

   windowManager.addInitialCatPngs(initialCatList);
 
}
public void buyCat(){
        // generates the new cat
       Cat newCat = market.buyCat();
        // checks to make sure the user has enough money to purchase
       if(newCat==null){
           purchaseError1.setPosition(getCenterX(buyButton)-purchaseError1.getWidth()*0.5, getCenterY(buyButton)+25);
           canvas.add(purchaseError1);
       }
     
       else{
           if (canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23)!=null){
                   canvas.remove(canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23));
                  
               }
       }

       if(newCat!=null){
            fileCatMap.put(new File(newCat.getFilepathKilt()), newCat);
            catFileMap.put(newCat, new File(newCat.getFilepathKilt()));
            System.out.println(newCat.getFilepathKilt());
            canvas.pause(750);
            Image catImage = new Image(newCat.getFilepathKilt());
     
            String s = String.valueOf(market.getMoney());
            currencyCount.setText("Available Currency: " +(s));
   
            imageXCoor.put(catFileMap.get(newCat), availableX);
            imageYCoor.put(catFileMap.get(newCat), availableY);
            coorToFile.put(createCoorList(availableX, availableY), catFileMap.get(newCat));
            catImage.setCenter(availableX, availableY);
            addCatButton(availableX, availableY, catFileMap.get(newCat));
            canvas.add(catImage);
            canvas.draw();
          
            if(((availableX < (120 + 4*horizontalPadding)) && availableY > 120)){
                availableX = availableX + horizontalPadding;
            }
            else if(((availableX == (120 + 4*horizontalPadding)) && availableY == 120)){
                availableX = 120;
                availableY = 120 + verticalPadding;


            }
            else if(availableX < 120 + 4*horizontalPadding && availableY == 120){
                    availableX = availableX + horizontalPadding;
                    // availableY = 120;
            }

            System.out.println("Available currency: " + currencyAvailable);
            }

   }
 
public void removeSalesErrors(){
   if (canvas.getElementAt(getCenterX(sellButton)-saleError1.getWidth()*0.5+2, getCenterY(sellButton)+23)!=null){
       canvas.remove(canvas.getElementAt(getCenterX(sellButton)-saleError1.getWidth()*0.5+2, getCenterY(sellButton)+23));
   }
   if (canvas.getElementAt(getCenterX(sellButton)-saleError2.getWidth()*0.5+2, getCenterY(sellButton)+23)!=null){
       canvas.remove(canvas.getElementAt(getCenterX(sellButton)-saleError2.getWidth()*0.5+2, getCenterY(sellButton)+23));
   }
}
 
 
public void sellCat(String fileName, int price){
   File catFile = new File(fileName);
   selectedCatList.remove(catFile);
   int soldCatPos = soldCat(catFile);
   //updates currency
   market.sellCat(fileCatMap.get(catFile));
   String s = String.valueOf(market.getMoney());
   currencyCount.setText("Available Currency: " +(s));

   for(int c = soldCatPos +1; c < 11; c++){
       if(catAt(c)){
            move(c, c-1);
        }
        else{
            availableX = intToList.get(c).get(0);
            availableY = intToList.get(c).get(1);
            break;
        }
    }
}
   

public int soldCat(File catFile){
    double xCoor = imageXCoor.get(catFile);
    double yCoor = imageYCoor.get(catFile);
    List<Double> list = Arrays.asList(xCoor, yCoor);
    int i = listToInt.get(list);
    // gets removed twice because rectangle is on top of cat and both get removed
    canvas.remove(canvas.getElementAt(xCoor, yCoor));
    canvas.remove(canvas.getElementAt(xCoor, yCoor));
    // removes select button
    canvas.remove(canvas.getElementAt(xCoor, yCoor + catImage1.getHeight()*0.5));
    canvas.draw();
    // removes cat file from its maps
    imageXCoor.remove(catFile);
    imageYCoor.remove(catFile);
    coorToFile.remove(list);
    return i;
}

public boolean catAt(int pos){
    double posX = intToList.get(pos).get(0);
    double posY = intToList.get(pos).get(1);
    if(canvas.getElementAt(posX, posY)!=null){
        return true;
    }
    else{
        return false;
    }
}

public void move(int c, int d){
    File catFile = new File(coorToFile.get(intToList.get(c)).toString());
    double x = intToList.get(c).get(0);
    double y = intToList.get(c).get(1);
    double newX = intToList.get(d).get(0);
    double newY = intToList.get(d).get(1);
    //repositions cat
    canvas.getElementAt(x, y).setCenter(newX, newY);
    //removes select button
    canvas.remove(canvas.getElementAt(x, y + catImage1.getHeight()*0.5));
    //re-adds select button
    addCatButton(newX, newY, catFile);
    //updates what the new coordinates for the file are in its maps
    imageXCoor.remove(catFile);
    imageYCoor.remove(catFile);
    imageXCoor.put(catFile, newX);
    imageYCoor.put(catFile, newY);
    coorToFile.remove(intToList.get(c));
    coorToFile.put(intToList.get(d), catFile);
    canvas.draw();
}
 
 
private void addInitialCatPngs(List<File> catList){
   double x = 0;
   double y = 0;
   for(File i : catList){
       Image catImage = new Image(i.toString());
       catImage.setCenter(120 + x, 120 + y);
       imageXCoor.put(i, 120 + x);
       imageYCoor.put(i, 120 + y);
       coorToFile.put(createCoorList(120+x, 120+y), i);
       canvas.add(catImage);
       addCatButton(120 + x, 120 + y, i);
       x += horizontalPadding;
   }
}

public List<Double> createCoorList(double x, double y){
    List<Double> coorList = Arrays.asList(x, y);
    return coorList;
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
           selectedCatList.remove(filePath);
     
       }
   });
   canvas.add(catButton);
 
}
 
}

