package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddGames() {

        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Что? Где? Когда?", "Викторины");
        Game game3 = store.publishGame("Шахматы", "Стратегии");

        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));
        assertTrue(store.containsGame(game3));
    }

    @Test
    public void shouldCheckGameAbsence() {

        GameStore store = new GameStore();
        GameStore anotherStore = new GameStore();
        Game game = anotherStore.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertFalse(store.containsGame(game));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "\"Gleb\", 0, 0, \"Gleb\"",
            "\"Gleb\", 0, 1, \"Gleb\"",
            "\"Gleb\", 1, 1, \"Gleb\""
    })
    public void shouldGetMostPlayerForSingle(String playerName, int hours1, int hours2, String expected) {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.addPlayTime(playerName, hours1);
        store.addPlayTime(playerName, hours2);

        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "\"Gleb\", 3, \"Egor\", 2, \"Gleb\"",
            "\"Gleb\", 2, \"Egor\", 2, \"Egor\"",
            "\"Gleb\", 2, \"Egor\", 3, \"Egor\""
    })
    public void shouldGetMostPlayerForSeveral(String playerName1, int hours1, String playerName2, int hours2, String expected) {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.addPlayTime(playerName1, hours1);
        store.addPlayTime(playerName2, hours2);

        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetMostPlayerForNobody() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        String actual = store.getMostPlayer();

        assertEquals(null, actual);
    }

    @Test
    public void shouldTrowRunTimeExceptionForNegativeHours() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertThrows(RuntimeException.class, () -> {
            store.addPlayTime("Egor", -2);
        });
    }

    @Test
    public void shouldGetSumPlayedTime() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.addPlayTime("Gleb", 1);
        store.addPlayTime("Egor", 1);
        store.addPlayTime("Gleb", 0);
        store.addPlayTime("Egor", 4);

        int actual = store.getSumPlayedTime();

        int expected = 1 + 1 + 0 + 4;
        assertEquals(expected, actual);
    }
}