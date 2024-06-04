package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.datas.GameData;
import net.aspanc.bootcamp.springmvc.models.GameModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class GameModelToGameDataConverter implements Converter<GameModel, GameData> {

    @Override
    public GameData convert(GameModel gameModel) {
        Assert.notNull(gameModel, "The object gameModel must not be null.");
        return GameData.builder()
                       .id(gameModel.getId())
                       .title(gameModel.getTitle())
                       .description(gameModel.getDescription())
                       .steamId(gameModel.getSteamId())
                       .build();
    }
}