import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void shouldThrowExceptionWhenParameterListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void shouldMessageWhenParameterListIsEmpty() {
        try {
            new Hippodrome(new ArrayList<>());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenParameterListIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void shouldMessageWhenParameterListIsNull() {
        try {
            new Hippodrome(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void shouldReturnListWhenParameterGetList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("horse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void shouldAllObjectListWhenMoveMethodCalled() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for(Horse horse: horses) {
            verify(horse).move();
        }
    }

    @Test
    public void shouldReturnMaxDistanceWhenParameterGetList() {
        Horse horse1 = new Horse("horse1",1,2.9999);
        Horse horse2 = new Horse("horse2",1,3);
        Horse horse3 = new Horse("horse3",1,2);
        Horse horse4 = new Horse("horse4",1,1);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));

        assertSame(horse2,hippodrome.getWinner());
    }


}
