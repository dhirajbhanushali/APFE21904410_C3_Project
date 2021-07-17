import java.time.LocalTime;

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
