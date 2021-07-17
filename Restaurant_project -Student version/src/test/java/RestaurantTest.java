import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void beforeEach(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //mockRestaurant = Mockito.mock(Restaurant.class);
        boolean restaurantOpen;
        Restaurant spyRestaurant =  Mockito.spy(restaurant);

        //LocalTime validRestaurantTime = LocalTime.parse("11:45:00");
        //Mockito.when(spyRestaurant.isRestaurantOpen()).thenReturn(true);

        //spyRestaurant.time = LocalTime.parse("13:45:00");
        //spyRestaurant.time = LocalTime.parse("10:30:00");

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:30:00"));
        //System.out.println(spyRestaurant.time);
        //System.out.println(spyRestaurant.openingTime);
        //System.out.println(spyRestaurant.getCurrentTime());
        restaurantOpen = spyRestaurant.isRestaurantOpen();
        //System.out.println(restaurantOpen);

        assertThat(restaurantOpen, equalTo(true));

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("13:45:00"));
        restaurantOpen = spyRestaurant.isRestaurantOpen();
        assertThat(restaurantOpen, equalTo(true));

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("22:00:00"));
        restaurantOpen = spyRestaurant.isRestaurantOpen();
        assertThat(restaurantOpen, equalTo(true));

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        boolean restaurantOpen;
        Restaurant spyRestaurant =  Mockito.spy(restaurant);
        //spyRestaurant.time = LocalTime.parse("10:29:00");
        //spyRestaurant.time = LocalTime.parse("22:29:00");

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:29:00"));
        //System.out.println(spyRestaurant.getCurrentTime());
        restaurantOpen = spyRestaurant.isRestaurantOpen();
        //System.out.println(restaurantOpen);
        assertThat(restaurantOpen, equalTo(false));

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("22:29:00"));
        restaurantOpen = spyRestaurant.isRestaurantOpen();
        assertThat(restaurantOpen, equalTo(false));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");
        //restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        //restaurant.addToMenu("Sweet corn soup",119);
        //restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");
        //restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        //restaurant.addToMenu("Sweet corn soup",119);
        //restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");
        //restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        //restaurant.addToMenu("Sweet corn soup",119);
        //restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<Order Cost>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void calculate_order_value_based_on_items_selected(){
        List<String> items = new ArrayList<>();
        int expectedTotalValue = 119;
        int actualOrderValue = restaurant.calculateOrderValue(items);
        assertThat(expectedTotalValue, equalTo(actualOrderValue));

    }
    //<<<<<<<<<<<<<<<<<<<<<<<Order Cost>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}