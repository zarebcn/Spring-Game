package com.zarebcn.springgames.controllers;

import com.zarebcn.springgames.model.Game;
import com.zarebcn.webapputils.util.MustacheUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/games", produces = MediaType.TEXT_HTML_VALUE)
public class GameController {

    private List<Game> games;

    public GameController() {

        games = new ArrayList<>();

        games.add(new Game("Bioshock", "2K Games", "FPS", 1));
        games.add(new Game("Alien Isolation", "CA Games", "Survival Horror", 2));
        games.add(new Game("PES 2018", "Konami", "Sport", 3));
        games.add(new Game("Skyrim", "Bethesda", "RPG", 4));
        games.add(new Game("The Witcher 3", "CD Projekt RED", "RPG", 5));
        games.add(new Game("Half Life 2", "Valve", "FPS", 6));
        games.add(new Game("Resident Evil 7", "Capcom", "Survival Horror", 7));
        games.add(new Game("NBA 2K18", "2K Games", "Sport", 8));
        games.add(new Game("Thimbleweed Park", "Terrible Toybox", "Adventure", 9));
        games.add(new Game("Fallout 4", "Bethesda", "RPG", 10));
        games.add(new Game("Call of Duty 4 Modern Warfare Remastered", "Raven Software", "FPS", 11));
        games.add(new Game("Wolfenstein 2 The New Colossus", "MachineGames", "FPS", 12));

    }

    @RequestMapping
    public String viewGames() throws IOException {

        return MustacheUtil.processTemplate("games.html", games);
    }

    @RequestMapping("/{id}")
    public String viewGame(@PathVariable("id") int gameId) throws IOException {

        if (gameId > 0 && gameId <= games.size()) {

            Game game = games.get(gameId - 1);

            Map<String, Object> map = new HashMap<>();
            map.put("title", game.getTitle());
            map.put("developer", game.getDeveloper());
            map.put("genre", game.getGenre());

            return MustacheUtil.processTemplate("game.html", map);

        } else {
            return "Game not found";
        }
    }

    @RequestMapping("/search")
    public String search() {
        return "<h1>Enter a game genre filter value</h1>";
    }

    @RequestMapping("/search/{genre}")
    public String filterByGenre(@PathVariable("genre") String genre) throws IOException {

        List<Game> gamesFiltered = new ArrayList<>();


        for (int i = 0; i < games.size(); i++) {

            Game game = games.get(i);

            if (genre != null && game.getGenre().toLowerCase().equals(genre.toLowerCase())) {

                gamesFiltered.add(game);
            }
        }

        if (gamesFiltered.size() > 0) {

            return MustacheUtil.processTemplate("filteredgames.html", gamesFiltered);

        } else {

            return "<h1>No games found by " + "'" + genre + "'" + " filter</h1>";
        }
    }
}
