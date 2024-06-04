package net.aspanc.bootcamp.springmvc.facades.impl;

import com.ibasco.agql.protocols.valve.steam.webapi.interfaces.SteamStorefront;
import com.ibasco.agql.protocols.valve.steam.webapi.pojos.StoreAppDetails;
import net.aspanc.bootcamp.springmvc.datas.GameData;
import net.aspanc.bootcamp.springmvc.datas.GameSteamData;
import net.aspanc.bootcamp.springmvc.facades.GameFacade;
import net.aspanc.bootcamp.springmvc.models.GameModel;
import net.aspanc.bootcamp.springmvc.services.GameService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DefaultGameFacade implements GameFacade {

    @Resource
    private GameService gameService;
    @Resource
    private SteamStorefront storeFront;
    @Resource
    private Converter<GameModel, GameData> gameModelToGameDataConverter;
    @Resource
    private Converter<GameData, GameModel> gameDataToGameModelConverter;
    @Resource
    private Converter<StoreAppDetails, GameSteamData> storeAppDetailsGameSteamDataConverter;

    @Override
    public List<GameData> findAll() {
        return gameService.findAll()
                          .stream()
                          .map(gameModelToGameDataConverter::convert)
                          .collect(Collectors.toList());
    }

    @Override
    public Optional<GameData> findById(Long id) {
        Assert.notNull(id, "Id must not be null");
        return gameService.findById(id)
                          .map(gameModelToGameDataConverter::convert);
    }

    @Override
    public List<GameData> findByTitle(String title) {
        Assert.notNull(title, "Title must not be null");
        return gameService.findByTitle(title)
                          .stream()
                          .map(gameModelToGameDataConverter::convert)
                          .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id, "Id must not be null");
        gameService.delete(id);
    }

    @Override
    public GameData save(GameData gameData) {
        Assert.notNull(gameData, "GameData must not be null");
        return gameModelToGameDataConverter.convert(gameService.save(gameDataToGameModelConverter.convert(gameData)));
    }

    @Override
    public GameSteamData getStoreAppDetails(Integer steamId, String countryCode, String language) {
        return storeAppDetailsGameSteamDataConverter.convert(storeFront.getAppDetails(steamId, countryCode, language)
                                                                       .join());
    }
}