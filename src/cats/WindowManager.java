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
/**
 * This class manages the canvas/ui as it keeps track of displaying the cats and buttons and keeping track 
 * of what is/isn't selected by the user and then how to appropriately buy, sell, or evolve new cats
 * based on that information
 */
 
public class WindowManager {
   private static final int CANVAS_WIDTH = 1125;
   private static final int CANVAS_HEIGHT = 800;
   private CanvasWindow canvas;
   private static List<File> selectedCatList = new ArrayList<File>();
   private Button buyButton;
   private Button sellButton;
   private Button evolveButton;
   private Market market;
   private Map<File, Cat> fileCatMap = new HashMap<File, Cat>();
   private Map<Cat, File> catFileMap = new HashMap<Cat, File>();
   private GraphicsText purchaseError1;
   private GraphicsText saleError1;
   private GraphicsText saleError2;
   private GraphicsText catsError;
   private GraphicsText currencyCount;
   private GraphicsText catName;
   private ArrayList<Rectangle> selectedRectangles = new ArrayList<>();
   private ArrayList<Rectangle> backRectangles = new ArrayList<>();
   private ArrayList<Image> catList = new ArrayList<>();
   private ArrayList<Image> selectedImages = new ArrayList<>();
   private ArrayList<Button> buttonList = new ArrayList<>();
   private Rectangle currencyRectangle;

 public WindowManager(){
 
   market = new Market(1200);
   canvas = new CanvasWindow("Cats!", CANVAS_WIDTH, CANVAS_HEIGHT);
   canvas.setBackground(Color.PINK);
   purchaseError1 = new GraphicsText("Insuffcient funds :(");
   saleError1 = new GraphicsText("Cat must be selected to make sale");
   saleError2 = new GraphicsText("Only one cat can be sold at a time");
   catsError = new GraphicsText("Cannot have more than 10 cats");
   catName = new GraphicsText("Kitty");
   currencyCount = new GraphicsText("Available Currency: 1200");
   currencyCount.setCenter(CANVAS_WIDTH*0.5, CANVAS_HEIGHT*0.9);
   canvas.add(currencyCount);
   currencyRectangle = new Rectangle(currencyCount.getCenter().getX(), currencyCount.getCenter().getY(), CANVAS_WIDTH*0.2, CANVAS_HEIGHT*0.1);
   currencyRectangle.setStrokeColor(Color.YELLOW);
   currencyRectangle.setStrokeWidth(5);
   currencyRectangle.setCenter(currencyCount.getCenter().getX(), currencyCount.getCenter().getY());
   canvas.add(currencyRectangle);
   buyButton = new Button("Buy New Cat");
   buyButton.setCenter(CANVAS_WIDTH*0.4 - buyButton.getWidth(), CANVAS_HEIGHT*0.8);
   buyButton.onClick(() ->{ 
       removeBuySellErrors();
       if(catList.size()>=10){
            catsError.setPosition(getCenterX(evolveButton)-catsError.getWidth()*0.5, getCenterY(buyButton));
            canvas.add(catsError);
        }
        else{
            buyCat();
        }
    });
   sellButton = new Button("Sell");
   sellButton.setCenter(CANVAS_WIDTH*0.7, CANVAS_HEIGHT*0.8);
   sellButton.onClick(() ->{
       removeBuySellErrors();
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
   evolveButton = new Button("Evolve");
   evolveButton.setCenter(CANVAS_WIDTH*0.5, CANVAS_HEIGHT*0.7);
   evolveButton.onClick(() ->{
       removeBuySellErrors();
       if(catList.size()>=10){
            catsError.setPosition(getCenterX(evolveButton)-catsError.getWidth()*0.5, getCenterY(buyButton));
            canvas.add(catsError);
       }
       else{    
            evolveCat(selectedCatList.get(0).toString(), selectedCatList.get(1).toString());
        }
   });
   canvas.add(buyButton);
   canvas.add(sellButton);
   canvas.add(evolveButton);
}
 
 
public static void main(String[] args){
   WindowManager windowManager = new WindowManager();
 
}
/**
 * This method uses market to evolve a new cat based on the traits of the 2 cats selected by the user, and then adds it to the canvas in the position
 * next available, which would be to the right of the last cat displayed on the screen. It also updates currency displayed on the screen.
 * @param fileName1 The file of one of the 'parent' cats as a string (Selected by user)
 * @param fileName2 The file of the other 'parent' cat as a string (Selected by user)
 */
private void evolveCat(String fileName1, String fileName2){
    File catFile1 = new File(fileName1);
    File catFile2 = new File(fileName2);
    Cat newCat = market.evolve(fileCatMap.get(catFile1),fileCatMap.get(catFile2));;
    fileCatMap.put(new File(newCat.getFilepathKilt()), newCat);
    catFileMap.put(newCat, new File(newCat.getFilepathKilt()));
    canvas.pause(1200);
    Image catImage = new Image(newCat.getFilepathKilt());
    catList.add(catImage);
    int x = ((catList.size() - 1) % 5) * 220 + 120;
    int y = ((catList.size() - 1) / 5) * 220 + 120;
    catImage.setCenter(x, y);
    Rectangle backRectangle = new Rectangle(x, y, catImage.getWidth()*0.7, catImage.getHeight());
    backRectangles.add(backRectangle);
    backRectangle.setCenter(x, y);
    backRectangle.setFillColor(new Color((int)255 - newCat.getBellyColor().get(0), (int)255 - newCat.getBellyColor().get(1), (int)255 - newCat.getBellyColor().get(2)));
    canvas.add(backRectangle);
    canvas.add(catImage);

    String s = String.valueOf(market.getMoney());
    currencyCount.setText("Available Currency: " +(s));

    addCatButton(x, y, catFileMap.get(newCat), catImage);

    canvas.draw();
}
/**
 * This method uses market to generate a brand new cat, and then adds it to the canvas in the position
 * next available, which would be to the right of the last cat displayed on the screen. It also checks to make
 * sure the cat isn't null, and if it is null it displayed an error for the user telling them they don't have
 * enough money. It also updates currency displayed on the screen.
 */
private void buyCat(){
       Cat newCat = market.buyCat();
       if(newCat==null){
           purchaseError1.setPosition(getCenterX(buyButton)-purchaseError1.getWidth()*0.5, getCenterY(buyButton)+25);
           canvas.add(purchaseError1);
       }
     
       if(newCat!=null){
            fileCatMap.put(new File(newCat.getFilepathKilt()), newCat);
            catFileMap.put(newCat, new File(newCat.getFilepathKilt()));
            canvas.pause(1000);
            Image catImage = new Image(newCat.getFilepathKilt());
            catList.add(catImage);
            int x = ((catList.size() - 1) % 5) * 220 + 120;
            int y = ((catList.size() - 1) / 5) * 220 + 120;
            catImage.setCenter(x, y);
            Rectangle backRectangle = new Rectangle(x, y, catImage.getWidth()*0.7, catImage.getHeight());
            backRectangles.add(backRectangle);
            backRectangle.setCenter(x, y);
            backRectangle.setFillColor(new Color((int)255 - newCat.getBellyColor().get(0), (int)255 - newCat.getBellyColor().get(1), (int)255 - newCat.getBellyColor().get(2)));
            canvas.add(backRectangle);
            canvas.add(catImage);


            String s = String.valueOf(market.getMoney());
            currencyCount.setText("Available Currency: " +(s));
   
            addCatButton(x, y, catFileMap.get(newCat), catImage);
            canvas.draw();
        
            }

   }
 
/**
 * This method removes any lingering error messages that have to do with buying or selling cats by checking
 * to see if the errors are on the canvas and removing them if they are
 */
 
private void removeBuySellErrors(){
    if (canvas.getElementAt(getCenterX(sellButton)-saleError1.getWidth()*0.5+2, getCenterY(sellButton)+23)!=null){
        canvas.remove(canvas.getElementAt(getCenterX(sellButton)-saleError1.getWidth()*0.5+2, getCenterY(sellButton)+23));
    }
    if (canvas.getElementAt(getCenterX(sellButton)-saleError2.getWidth()*0.5+2, getCenterY(sellButton)+23)!=null){
        canvas.remove(canvas.getElementAt(getCenterX(sellButton)-saleError2.getWidth()*0.5+2, getCenterY(sellButton)+23));
    }
    if (canvas.getElementAt(getCenterX(sellButton)-saleError1.getWidth()*0.5+2, getCenterY(sellButton)+23)!=null){
        canvas.remove(canvas.getElementAt(getCenterX(sellButton)-saleError1.getWidth()*0.5+2, getCenterY(sellButton)+23));
    }
    if (canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23)!=null){
        canvas.remove(canvas.getElementAt(getCenterX(buyButton)-purchaseError1.getWidth()*0.5+2, getCenterY(buyButton)+23));
    }
    if(canvas.getElementAt(getCenterX(evolveButton)-catsError.getWidth()*0.5+2, getCenterY(buyButton)-2)!=null){
        canvas.remove(canvas.getElementAt(getCenterX(evolveButton)-catsError.getWidth()*0.5+2, getCenterY(buyButton)-2));
   }
 }
/**
 * This effectively 'sells' a cat that is selected. It makes the cat's file unselected and then calls solCat.
 * It also calls the sellCat version from Market so that the currency can be properly added and displayed.
 * @param fileName The file name as a string of the cat being sold.
 * @param price How much the cat costs, which is how much will be added to the user's currency
 */
 
private void sellCat(String fileName, int price){
   File catFile = new File(fileName);
   selectedCatList.remove(catFile);
   soldCat(catFile);
   //updates currency
   market.sellCat(fileCatMap.get(catFile));
   String s = String.valueOf(market.getMoney());
   currencyCount.setText("Available Currency: " +(s));

}
   
/**
 * This method removes that cat that is being sold from its canvas along with all its other associated graphics
 * such as the select button, the background rectangle, and the green rectangle frame. It then shifts all
 * remaining cats and their associated graphics to the left if they were to the right of the cat that is sold, so
 * that there are no weird gaps on the display
 * @param catFile the file of the cat being sold
 */
private void soldCat(File catFile){
    double xCoor = selectedImages.get(0).getCenter().getX();
    double yCoor = selectedImages.get(0).getCenter().getY();
    selectedImages.clear();
    // removal at same coordinates 3 times because rectangle is on top of cat and other on bottom
    if(canvas.getElementAt(xCoor, yCoor)!=null){
        canvas.remove(canvas.getElementAt(xCoor, yCoor));
    }
    catList.remove(canvas.getElementAt(xCoor, yCoor)); 
    if(canvas.getElementAt(xCoor, yCoor)!=null){
        canvas.remove(canvas.getElementAt(xCoor, yCoor));
    }
    backRectangles.remove(canvas.getElementAt(xCoor, yCoor)); 
    if(canvas.getElementAt(xCoor, yCoor)!=null){
        canvas.remove(canvas.getElementAt(xCoor, yCoor));
    }
    buttonList.remove(canvas.getElementAt(xCoor, yCoor + 200*0.5)); 
    canvas.remove(canvas.getElementAt(xCoor, yCoor + 200*0.5));

    for (int cat = 0; cat < catList.size(); cat++)  {
        int x = (cat % 5) * 220 + 120;
        int y = (cat / 5) * 220 + 120;
        backRectangles.get(cat).setCenter(x,y);
        catList.get(cat).setCenter(x, y);
    }

    for (int button = 0; button < buttonList.size(); button++) {
        int x = (button % 5) * 220 + 120;
        int y = (button / 5) * 220 + 220;
        buttonList.get(button).setCenter(x, y);
    }
    canvas.draw();
}

 
/**
 * This gets the X center of any button
 * @param myButton the button the X center is being found for
 * @return X center of button
 */
public double getCenterX(Button myButton){
   double centerX = myButton.getX() + myButton.getWidth()*0.5;
   return centerX;
}
/**
 * This gets the Y center of any button
 * @param myButton the button the Y center is being found for
 * @return Y center of button
 */
public double getCenterY(Button myButton){
   double centerY = myButton.getY() + myButton.getHeight()*0.5;
   return centerY;
}
 
/**
 * This method adds a button to the canvas that allows you to select a cat below the cat image. The button corresponds 
 * to its particular image. If the button is already selected, it will deselect the cat.
 * @param x the center X of the cat image for which the button is being made
 * @param y the center Y of the cat image for which the button is being made
 * @param filePath the file that corresponds to the image
 * @param catImage the image of the cat itself
 */
private void addCatButton(double x, double y, File filePath, Image catImage){
   Button catButton = new Button("Select");
   buttonList.add(catButton);
   catButton.setCenter(x, y + 200*0.5);
   catButton.onClick(() -> {
       if (!selectedCatList.contains(filePath) && selectedCatList.size()<2) {
           String p = fileCatMap.get(filePath).getName();
           p = p.substring(0,1).toUpperCase() + p.substring(1);
           catName.setText(p);
           catName.setCenter(CANVAS_WIDTH*0.5, CANVAS_HEIGHT*0.75);
           canvas.add(catName);
           selectedCatList.add(filePath);
           selectedImages.add(catImage);
           Rectangle selectedRectangle = new Rectangle(catImage.getCenter().getX()-200/1.8, catImage.getCenter().getY()-200/4, catImage.getWidth()*0.7, catImage.getHeight());
           selectedRectangle.setStrokeColor(Color.GREEN);
           selectedRectangle.setStrokeWidth(5);
           selectedRectangle.setCenter(catImage.getCenter().getX(), catImage.getCenter().getY()+2);
           canvas.add(selectedRectangle);
           selectedRectangles.add(selectedRectangle);
       }
       else if(selectedCatList.contains(filePath)){
           if(canvas.getElementAt(catImage.getCenter().getX(), catImage.getCenter().getY()-80)!=null){
               canvas.remove(canvas.getElementAt(catImage.getCenter().getX(), catImage.getCenter().getY()));
               selectedCatList.remove(filePath);
               selectedImages.remove(catImage);
               canvas.remove(catName);
               if(!selectedCatList.isEmpty()){
                    String p = fileCatMap.get(selectedCatList.get(0)).getName();
                    p = p.substring(0,1).toUpperCase() + p.substring(1);
                    catName.setText(p);
                    catName.setCenter(CANVAS_WIDTH*0.5, CANVAS_HEIGHT*0.75);
                    canvas.add(catName);
               }
            
           }

       }
   });
   canvas.add(catButton);
 
}
 
}

