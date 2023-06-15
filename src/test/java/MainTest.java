import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Disabled
    @Test
    @Timeout(value = 22,unit = TimeUnit.SECONDS)
    void main() throws Exception {
       /* List<Horse> horses =List.of(
                new Horse("Bucefal", 2.4),
                new Horse("Tuz pic", 2.5),
                new Horse("Ze fir", 2.6),
                new Horse("Fire", 2.7),
                new Horse("lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            try {
                Main.watch(hippodrome);
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        String winnerName = hippodrome.getWinner().getName();
        System.out.println("Победил " + winnerName + "!");
*/
        String[] args = null;
        Main.main(args);
    }
}