package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {


    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGames() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Портал 2", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.play(game1, 3);
        player.play(game2, 7);

        int expected = 10;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfOneGameFromTwo() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Портал 2", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.play(game1, 6);

        int expected = 6;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    //
    @Test
    public void shouldPlayGameNotInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        assertThrows(RuntimeException.class, () -> {
            player.play(game, 3);
        });
    }

    @Test
    public void getName() {
        Player player = new Player("Petya");

        String expected = "Petya";
        String actual = player.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void installGame() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Герои Меча и Магии 3", "Стратегия");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);

        player.play(game1, 3);

        player.installGame(game1);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenre() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Герои Меча и Магии 3", "Стратегия");
        Game game3 = store.publishGame("Портал 2", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 2);
        player.play(game3, 4);

        String expected = String.valueOf(game3);
        String actual = String.valueOf(player.mostPlayerByGenre(game1.getGenre()));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreWithGamesNotPlay() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Портал 2", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.play(game1, 0);
        player.play(game2, 0);

        Game actual = player.mostPlayerByGenre(game1.getGenre());
        assertEquals(null, actual);
    }

    @Test
    public void shouldSumGenreIfPlayedFewGamesOfSameGenre() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Герои Меча и Магии 3", "Стратегия");
        Game game3 = store.publishGame("Портал 2", "Аркады");
        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 6);
        player.play(game3, 2);
        player.play(game2, 5);

        int expected = 5;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void negativeTimeTest() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);

        assertThrows(RuntimeException.class, () -> {
            player.play(game, -3);
        });
    }

}
