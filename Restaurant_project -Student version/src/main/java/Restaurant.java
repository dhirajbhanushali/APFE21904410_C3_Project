import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    //public LocalTime time = getCurrentTime();


    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }


    public boolean isRestaurantOpen() {
        //return true;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        LocalTime time = getCurrentTime();

        //System.out.println(time);
        //System.out.println(openingTime);
        //System.out.println(closingTime);

        if(time.equals(openingTime) || (time.isAfter(openingTime) && time.isBefore(closingTime)) || time.equals(closingTime)) {
            //System.out.println(time);
            //System.out.println(openingTime);
            //System.out.println(closingTime);
            return true;
        }
        else{

            //System.out.println(time);
            //System.out.println(openingTime);
            //System.out.println(closingTime);
            return false;
        }

    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }



    public List<Item> getMenu() {
        //return null;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
        return Collections.unmodifiableList(menu);
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public int calculateOrderValue(List<Item> selectedItems){
        int totalOrderValue = 0;
        Item item = null;
        for(Item items: selectedItems) {
            totalOrderValue += items.getPrice();
        }
        return totalOrderValue;
        //return 0;
    }
}
