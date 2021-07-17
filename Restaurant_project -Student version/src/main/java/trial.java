import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class trial {
public static void main(String args []){
        System.out.println("Hi");
        trial ty = new trial();
        System.out.println(ty.getCurrentTime());
    }

    public LocalTime getCurrentTime(){
        return  LocalTime.now();
    }
}
