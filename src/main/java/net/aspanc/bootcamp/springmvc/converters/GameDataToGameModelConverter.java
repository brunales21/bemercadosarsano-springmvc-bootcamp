package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.datas.GameData;
import net.aspanc.bootcamp.springmvc.models.GameModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class GameDataToGameModelConverter implements Converter<GameData, GameModel> {

    @Override
    public GameModel convert(GameData gameData) {
        Assert.notNull(gameData, "The object gameData must not be null.");
        return GameModel.builder()
                        .id(gameData.getId())
                        .title(gameData.getTitle())
                        .description(gameData.getDescription())
                        .steamId(gameData.getSteamId())
                        .build();
    }
}
