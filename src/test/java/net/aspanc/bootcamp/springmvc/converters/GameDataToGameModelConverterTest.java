package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.datas.GameData;
import net.aspanc.bootcamp.springmvc.models.GameModel;
import org.junit.Assert;
import org.junit.Test;


public class GameDataToGameModelConverterTest {

    private static final Long ID = 1L;
    private static final String TITLE = "Minecraft";
    private static final String DESCRIPTION = "Just buy it.";
    private static final Integer STEAM_ID = 1;

    @Test
    public void convert() {
        GameModel gameModel = new GameDataToGameModelConverter().convert(GameData.builder()
                                                                                 .id(ID)
                                                                                 .title(TITLE)
                                                                                 .description(DESCRIPTION)
                                                                                 .steamId(STEAM_ID)
                                                                                 .build());

        Assert.assertNotNull("The gameModel object should not be null", gameModel);
        Assert.assertEquals("IDs do not match", ID, gameModel.getId());
        Assert.assertEquals("Titles do not match", TITLE, gameModel.getTitle());
        Assert.assertEquals("Descriptions do not match", DESCRIPTION, gameModel.getDescription());
        Assert.assertEquals("Steam IDs do not match", STEAM_ID, gameModel.getSteamId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNull() {
        GameModel gameModel = new GameDataToGameModelConverter().convert(null);
    }
}