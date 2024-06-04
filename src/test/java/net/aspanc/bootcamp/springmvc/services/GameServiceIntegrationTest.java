package net.aspanc.bootcamp.springmvc.services;

import net.aspanc.bootcamp.springmvc.models.GameModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceIntegrationTest {

    private static final String TITLE = "Minecraft";
    private static final String NEW_TITLE = "Minecraft 2";
    private static final String DESCRIPTION = "just buy it.";
    private static final Integer STEAM_ID = 1;

    @Resource
    private GameService gameService;

    private GameModel gameModel;

    @Before
    public void setUp() {
        gameModel = gameService.save(GameModel.builder()
                                              .title(TITLE)
                                              .description(DESCRIPTION)
                                              .steamId(STEAM_ID)
                                              .build());
    }

    @After
    public void tearDown() {
        gameService.findById(gameModel.getId())
                   .ifPresent(game -> gameService.delete(game.getId()));
    }

    @Test
    public void findAllGames() {
        Assert.assertTrue(gameService.findAll()
                                     .contains(gameModel));
    }

    @Test
    public void findGameById() {
        gameService.findById(gameModel.getId())
                   .ifPresentOrElse(expectedGame -> Assert.assertEquals(expectedGame, gameModel), Assert::fail);
    }

    @Test
    public void findGameByTitle() {
        Assert.assertTrue(gameService.findByTitle(gameModel.getTitle())
                                     .contains(gameModel));
    }

    @Test
    public void deleteGame() {
        gameService.delete(gameModel.getId());
        Assert.assertTrue(gameService.findById(gameModel.getId())
                                     .isEmpty());
    }

    @Test
    public void saveGame() {
        Assert.assertTrue(gameService.findById(gameModel.getId())
                                     .isPresent());
    }

    @Test
    public void updateGame() {
        gameModel.setTitle(NEW_TITLE);
        Assert.assertEquals(gameService.save(gameModel)
                                       .getTitle(), gameModel.getTitle());
    }
}