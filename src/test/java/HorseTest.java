import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void shouldMessageWhenNameIsNull() {
        try {
            new Horse(null, 1, 1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\t\t", "\n\n\n\n"})
    public void shouldThrowExceptionWhenNameIsBlank(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void shouldReturnNameWhenGetName() {
        String name = "horse";
        Horse horse = new Horse(name, 1, 1);

        assertEquals(name, horse.getName());
    }

    @Test
    public void shouldReturnSpeedWhenGetSpeed() {
        String name = "horse";
        double speed = 100;
        Horse horse = new Horse(name, speed, 1);

        assertEquals(speed, horse.getSpeed());
    }

    @Test
    public void shouldReturnDistanceWhenGetDistance() {
        String name = "horse";
        double distance = 100;
        Horse horse = new Horse(name, 1, distance);

        assertEquals(distance, horse.getDistance());
    }

    @Test
    public void shouldReturnDistanceZeroWhenDistanceByDefault() {
        String name = "horse";
        Horse horse = new Horse(name, 1);

        assertEquals(0, horse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.4, 0.9, 1.0, 99.99, 0.0})
    public void shouldUseGetRandomDoubleWhenUsedMove(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("horse", 100, 500);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(500 + 100 * random, horse.getDistance());
        }
    }
}
