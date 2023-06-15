import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    public List<Horse> initHorse(int j){
        List <Horse>  mockHorseList = Mockito.spy(new ArrayList<Horse>());
        for(int i=0;i<j;i++){
           String name = "horse "+i;
           int max =4;
           int min =2;
           max-=min;
           double speed =(Math.random() * ++ max) + min;

           speed = new BigDecimal(speed).setScale(1, RoundingMode.HALF_UP).doubleValue();

           mockHorseList.add(new Horse(name,speed));
         // System.out.println(mockHorseList.get(i).getName() +" - " + mockHorseList.get(i).getSpeed());
        }
        return mockHorseList;
    }
    
    
    public List<Horse> initMockHorse(int j){
        List <Horse>  mockHorseList = Mockito.spy(new ArrayList<Horse>());
        Horse mockHorse = Mockito.mock(Horse.class);
       for(int i=0;i<j;i++){
           mockHorseList.add(mockHorse);   
       }
       return mockHorseList;
    }
    
    @Test
    void whenHrseListIsNuulAssertingException(){

    List<Horse> horses=null;

        Throwable exception = assertThrows(IllegalArgumentException.class,()-> {
            Hippodrome mockedHippodrome = Mockito.spy(new Hippodrome(horses));

            if (isNull(horses)) {
                throw new IllegalArgumentException("Horses cannot be null.");
            }
        });
        assertEquals("Horses cannot be null.",exception.getMessage());
    }

    @Test
    void whenHrseListIsEmptyAssertingException(){

        List<Horse> horses=new ArrayList<>();

        Throwable exception = assertThrows(IllegalArgumentException.class,()-> {
            Hippodrome mockedHippodrome = Mockito.spy(new Hippodrome(horses));

            if (horses.isEmpty()) {
                throw new IllegalArgumentException("Horses cannot be empty.");
            }
        });
        assertEquals("Horses cannot be empty.",exception.getMessage());
    }

    @Test
    void getHorses() {
    int j =30;
       List<Horse> list = initHorse(j);

       Hippodrome hippodrome = Mockito.spy(new Hippodrome(list));
       for(int i=0; i<j;i++){
           assertEquals(list.get(i),hippodrome.getHorses().get(i));
       }
    }

    @Test
    void move() {
        int j =50;
        Hippodrome hippodrome = new Hippodrome(initMockHorse(50));
        Horse horse = null;
        for(Horse hors:hippodrome.getHorses()){
            horse = hors;
            horse.move();
        }
        Mockito.verify(horse,times(j)).move();
    }

    @Test
    void getWinner() {
        int j =30;
        double maxValue =0;
        List<Horse> list = initHorse(j);
        Hippodrome hippodrome = Mockito.spy(new Hippodrome(list));
        hippodrome.move();
        for(Horse horse:hippodrome.getHorses()){
            if(maxValue < horse.getDistance())
            maxValue = horse.getDistance();
       }
       assertEquals(maxValue,hippodrome.getWinner().getDistance());

    }
}