
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class HorseTest {


        private static Horse horse;
@BeforeAll
static void init(){
    horse = new Horse("name", 15.0, 1.0);
}

    @Test
    void whenNameIsNuulAssertingException(){

        Throwable exception = assertThrows(IllegalArgumentException.class,()-> {
        Horse mockedHorse = Mockito.spy(new Horse(null, 15.0, 1.0));

            if (isNull(mockedHorse.getName())) {
                throw new IllegalArgumentException("Name cannot be null.");
            }
        });
        assertEquals("Name cannot be null.",exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("Parametr")
   void whenNameIsBlankAssertingException(Character chr){

           Throwable exception = assertThrows(IllegalArgumentException.class,()-> {

               Horse mockedHorse = Mockito.spy(new Horse(String.valueOf(chr), 15.0, 1.0));

            if (mockedHorse.getName().isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank.");
            }
        });
        assertEquals("Name cannot be blank.",exception.getMessage());
    }

    @Test
    void whenSpeedDigitIsNegativ(){
        Throwable exception = assertThrows(IllegalArgumentException.class,()-> {
            Horse mockedHorse = Mockito.spy(new Horse("name", -15.0, 1.0));

            if (mockedHorse.getSpeed() < 0) {
                throw new IllegalArgumentException("Speed cannot be negative.");
        }
        });
        assertEquals("Speed cannot be negative.",exception.getMessage());
    }

    @Test
    void whenSpeedDistanceIsNegativ(){
        Throwable exception = assertThrows(IllegalArgumentException.class,()-> {

            Horse mockedHorse = Mockito.spy(new Horse("name", 15.0, -1.0));

            if (mockedHorse.getDistance() < 0) {
                throw new IllegalArgumentException("Distance cannot be negative.");
            }
        });
        assertEquals("Distance cannot be negative.",exception.getMessage());
    }

  static List<Character> Parametr (){

        return List.of('\u0020','\u0009','\u000B', '\u000C', '\u001C', '\u001D', '\u001E', '\u001F');
    }

    @Test
    void getName() {
        assertEquals("name",horse.getName());
    }

    @Test
    void getSpeed() {

        assertEquals(15,horse.getSpeed());

    }

    @Test
    void getDistance() {
        Horse mockedHorse = Mockito.spy(new Horse("name", 15.0));

        assertEquals(1,horse.getDistance());
        assertEquals(0,mockedHorse.getDistance());
    }
    //https://www.baeldung.com/mockito-mock-static-methods  классная шпора
    @Test
    void move() {
        try (MockedStatic mockStatic = mockStatic(Horse.class)){
            mockStatic.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(0.5);
            double distance = horse.getDistance();
            double speed = horse.getSpeed();
              distance += speed * Horse.getRandomDouble(0.2, 0.9);
           // System.out.println("distance - " + horse.getDistance() +"\n" + "speed - " + speed +"\n" + "random - "+ Horse.getRandomDouble(0.2, 0.9));
              assertEquals(8.5,distance);
            mockStatic.verify(()->Horse.getRandomDouble(0.2,0.9));

        }

    }
}