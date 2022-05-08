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
 
 
public class WindowManager extends Achievement{
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
   private Button evolveButton;
   public Cat cat1;
   public Cat cat2;
   public Cat cat3;
 
   public Boolean selected = false;
   private Image catImage1;
   private File catFile1;
   private File catFile2;
   private Market market;
   private Map<File, Cat> fileCatMap = new HashMap<File, Cat>();
   private Map<Cat, File> catFileMap = new HashMap<Cat, File>();
   private GraphicsText purchaseError1;
   private GraphicsText saleError1;
   private GraphicsText saleError2;
   private GraphicsText evolveError;
   private GraphicsText currencyCount;
   private GraphicsText catName;
   private ArrayList<Rectangle> selectedRectangles = new ArrayList<>();
   private double horizontalPadding;
   private double verticalPadding;
   private ArrayList<Image> catList = new ArrayList<>();
   private ArrayList<Image> selectedImages = new ArrayList<>();
   private ArrayList<Button> buttonList = new ArrayList<>();
// map.put("dog", "type of animal");
// System.out.println(map.get("dog"));
 public WindowManager(){
 
   market = new Market(1000);
//    cat1 = new Cat("maca", 1, 1, 1, 1);
//    cat2 = new Cat("lester", 1, 1, 1, 1);
//    cat3 = new Cat("2022", 1, 1, 1, 1);
//    cat2.setFurColor(55, 110, 22);
//    cat3.setBellyColor(12, 80, 96);
   canvas = new CanvasWindow("Cats!", CANVAS_WIDTH, CANVAS_HEIGHT);
//    horizontalPadding = catImage1.getImageWidth()+20;
//    verticalPadding = catImage1.getImageHeight()+20;
   purchaseError1 = new GraphicsText("Insuffcient funds :(");
   saleError1 = new GraphicsText("Cat must be selected to make sale");
   saleError2 = new GraphicsText("Only one cat can be sold at a time");
   evolveError = new GraphicsText("Two cats must be selected");
   catName = new GraphicsText("Kitty");
   currencyCount = new GraphicsText("Available Currency: 1000");
   currencyCount.setPosition(50, 700);
   canvas.add(currencyCount);
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
        System.out.println(selectedCatList.get(0));    
        sellCat(selectedCatList.get(0).toString(), 100);
       }
   });
   evolveButton = new Button("Evolve");
   evolveButton.setPosition(CANVAS_WIDTH*0.5, CANVAS_HEIGHT*0.7);
   evolveButton.onClick(() ->{

       if (selectedCatList.size()!=2){
     
        evolveError.setPosition(getCenterX(evolveButton)-evolveError.getWidth()*0.5, getCenterY(evolveButton)+25);
 
        canvas.add(evolveError);
    }
       else{
       evolveCat(selectedCatList.get(0).toString(), selectedCatList.get(1).toString());
       }
   });
   canvas.add(buyButton);
   canvas.add(sellButton);
   canvas.add(evolveButton);
//    catFile1 = new File(cat1.getFilepathKilt());
//    initialCatList.add(catFile1);
//    fileCatMap.put(catFile1, cat1);
//    catFileMap.put(cat1, catFile1);
//    catFile2 = new File(cat2.getFilepathKilt());
//    initialCatList.add(catFile2);
//    fileCatMap.put(catFile2, cat2);
//    catFileMap.put(cat2, catFile2);
    // Cat newCat = market.buyCat();
    // fileCatMap.put(new File(newCat.getFilepathKilt()), newCat);
    // catFileMap.put(newCat, new File(newCat.getFilepathKilt()));
    // // System.out.println(newCat.getFilepathKilt());
    // canvas.pause(1000);
    // Image catImage = new Image(newCat.getFilepathKilt());
    // catList.add(catImage);
    // int x = ((catList.size() - 1) % 5) * 220 + 120;
    // int y = ((catList.size() - 1) / 5) * 220 + 120;
    // catImage.setCenter(x, y);
    // canvas.add(catImage);


    // String s = String.valueOf(market.getMoney());
    // currencyCount.setText("Available Currency: " +(s));

    // addCatButton(x, y, catFileMap.get(newCat), catImage);
    // canvas.draw();

    // Cat newCat1 = market.buyCat();
    // fileCatMap.put(new File(newCat1.getFilepathKilt()), newCat1);
    // catFileMap.put(newCat1, new File(newCat1.getFilepathKilt()));
    // // System.out.println(newCat.getFilepathKilt());
    // canvas.pause(1000);
    // Image catImage1 = new Image(newCat1.getFilepathKilt());
    // catList.add(catImage1);
    // int x1 = ((catList.size() - 1) % 5) * 220 + 120;
    // int y1 = ((catList.size() - 1) / 5) * 220 + 120;
    // catImage1.setCenter(x1, y1);
    // canvas.add(catImage1);


    // String r = String.valueOf(market.getMoney());
    // currencyCount.setText("Available Currency: " +(r));

    // addCatButton(x1, y1, catFileMap.get(newCat1), catImage1);
    // canvas.draw();
  

    }
 
 
public static void main(String[] args){
   WindowManager windowManager = new WindowManager();
   windowManager.setUpUncompleted();

//    windowManager.addInitialCatPngs(initialCatList);
 
}

public void evolveCat(String fileName1, String fileName2){
    File catFile1 = new File(fileName1);
    File catFile2 = new File(fileName2);
    // // System.out.println(fileName);
    // selectedCatList.remove(catFile1);
    // selectedCatList.remove(catFile2);
    // // System.out.println(selectedCatList);
    // soldCat(catFile1);
    // soldCat(catFile2);
    //updates currency
    Cat newCat = market.evolve(fileCatMap.get(catFile1),fileCatMap.get(catFile2));;
    // market.sellCat(fileCatMap.get(catFile2));
    fileCatMap.put(new File(newCat.getFilepathKilt()), newCat);
    catFileMap.put(newCat, new File(newCat.getFilepathKilt()));
    // System.out.println(newCat.getFilepathKilt());
    canvas.pause(1000);
    Image catImage = new Image(newCat.getFilepathKilt());
    catList.add(catImage);
    int x = ((catList.size() - 1) % 5) * 220 + 120;
    int y = ((catList.size() - 1) / 5) * 220 + 120;
    catImage.setCenter(x, y);
    canvas.add(catImage);


    String s = String.valueOf(market.getMoney());
    currencyCount.setText("Available Currency: " +(s));

    addCatButton(x, y, catFileMap.get(newCat), catImage);
    canvas.draw();
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
            // System.out.println(newCat.getFilepathKilt());
            canvas.pause(1000);
            Image catImage = new Image(newCat.getFilepathKilt());
            catList.add(catImage);
            int x = ((catList.size() - 1) % 5) * 220 + 120;
            int y = ((catList.size() - 1) / 5) * 220 + 120;
            catImage.setCenter(x, y);
            canvas.add(catImage);


            String s = String.valueOf(market.getMoney());
            currencyCount.setText("Available Currency: " +(s));
   
            addCatButton(x, y, catFileMap.get(newCat), catImage);
            canvas.draw();
          

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
 // doesn't show more than 10 cats
 // squares belly color behind cats
 // names change and two names when evolving
 // background color 
 // make bigger and center available currency
 // move buttons
 
public void sellCat(String fileName, int price){
   File catFile = new File(fileName);
   System.out.println(fileName);
   selectedCatList.remove(catFile);
   System.out.println(selectedCatList);
   soldCat(catFile);
   //updates currency
   market.sellCat(fileCatMap.get(catFile));
   String s = String.valueOf(market.getMoney());
   currencyCount.setText("Available Currency: " +(s));

}
   

public void soldCat(File catFile){
    double xCoor = selectedImages.get(0).getCenter().getX();
    double yCoor = selectedImages.get(0).getCenter().getY();
    System.out.println(selectedImages);
    System.out.println(xCoor);
    System.out.println(yCoor);
    selectedImages.clear();
    // List<Double> list = Arrays.asList(xCoor, yCoor);
    // int i = listToInt.get(list);
    // gets removed twice because rectangle is on top of cat and both get removed

    if(canvas.getElementAt(xCoor, yCoor)!=null){
        System.out.println(canvas.getElementAt(xCoor, yCoor));
        canvas.remove(canvas.getElementAt(xCoor, yCoor));
    }
    catList.remove(canvas.getElementAt(xCoor, yCoor)); // Remove cat from list
    if(canvas.getElementAt(xCoor, yCoor)!=null){
        canvas.remove(canvas.getElementAt(xCoor, yCoor));
    }
    // removes select button
    buttonList.remove(canvas.getElementAt(xCoor, yCoor + 200*0.5)); // Remove button from buttonList
    canvas.remove(canvas.getElementAt(xCoor, yCoor + 200*0.5));
    System.out.println(catList);
    // removes cat file from its maps
    // coorToFile.remove(list);
    // return i;

    // Repositions all cats
    // for (int cat = 0; cat < catList.size(); cat++) 
    // int cat = -1;
    for (int cat = 0; cat < catList.size(); cat++)  {
        // cat = cat + 1;
        System.out.println(catList.get(cat));
        int x = (cat % 5) * 220 + 120;
        int y = (cat / 5) * 220 + 120;
        catList.get(cat).setCenter(x, y);
        // catImage.setCenter(x, y);
    }

    // Reposition all the buttons
    for (int button = 0; button < buttonList.size(); button++) {
        int x = (button % 5) * 220 + 120;
        int y = (button / 5) * 220 + 220;
        buttonList.get(button).setCenter(x, y);
    }
    canvas.draw();
}

// public boolean catAt(int pos){
//     double posX = intToList.get(pos).get(0);
//     double posY = intToList.get(pos).get(1);
//     if(canvas.getElementAt(posX, posY)!=null){
//         return true;
//     }
//     else{
//         return false;
//     }
// }

// public void move(int c, int d){
//     File catFile = new File(coorToFile.get(intToList.get(c)).toString());
//     double x = intToList.get(c).get(0);
//     double y = intToList.get(c).get(1);
//     double newX = intToList.get(d).get(0);
//     double newY = intToList.get(d).get(1);
//     //repositions cat
//     canvas.getElementAt(x, y).setCenter(newX, newY);
//     //removes select button
//     canvas.remove(canvas.getElementAt(x, y + 200*0.5));
//     //re-adds select button
//     addCatButton(newX, newY, catFile);
//     //updates what the new coordinates for the file are in its maps
//     imageXCoor.remove(catFile);
//     imageYCoor.remove(catFile);
//     imageXCoor.put(catFile, newX);
//     imageYCoor.put(catFile, newY);
//     coorToFile.remove(intToList.get(c));
//     coorToFile.put(intToList.get(d), catFile);
//     canvas.draw();
// }
 
 
// private void addInitialCatPngs(List<File> catFileList){
//    double x = 0;
//    double y = 0;
//    for(File i : catFileList){
//        Image catImage = new Image(i.toString());
//        catList.add(catImage);
//        catImage.setCenter(120 + x, 120 + y);
//        imageXCoor.put(i, 120 + x);
//        imageYCoor.put(i, 120 + y);
//        coorToFile.put(createCoorList(120+x, 120+y), i);
//        canvas.add(catImage);
//        addCatButton(120 + x, 120 + y, i);
//        x += horizontalPadding;
//    }
// }

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
 
 
private void addCatButton(double x, double y, File filePath, Image catImage){
   Button catButton = new Button("Select");
   buttonList.add(catButton);
   catButton.setCenter(x, y + 200*0.5);
   catButton.onClick(() -> {
       if (!selectedCatList.contains(filePath) && selectedCatList.size()<2) {
           String p = fileCatMap.get(filePath).getName();
           catName.setText(p);
           catName.setCenter(CANVAS_WIDTH*0.5, CANVAS_HEIGHT*0.75);
           canvas.add(catName);
           selectedCatList.add(filePath);
           selectedImages.add(catImage);
           Rectangle selectedRectangle = new Rectangle(catImage.getCenter().getX()-200/1.8, catImage.getCenter().getY()-200/4, 150*1.1, 200*1.1);
           selectedRectangle.setStrokeColor(Color.GREEN);
           selectedRectangle.setStrokeWidth(5);
           selectedRectangle.setCenter(catImage.getCenter().getX(), catImage.getCenter().getY()+2);
           canvas.add(selectedRectangle);
           selectedRectangles.add(selectedRectangle);
       }
       else if(selectedCatList.contains(filePath)){
           System.out.println("1");
           System.out.println(selectedCatList);
           if(canvas.getElementAt(catImage.getCenter().getX(), catImage.getCenter().getY()-80)!=null){
               canvas.remove(canvas.getElementAt(catImage.getCenter().getX(), catImage.getCenter().getY()));
               selectedCatList.remove(filePath);
               selectedImages.remove(catImage);
           }
           System.out.println(selectedCatList);
        //    selectedCatList.remove(filePath);
     
       }
   });
   canvas.add(catButton);
 
}
 
}

