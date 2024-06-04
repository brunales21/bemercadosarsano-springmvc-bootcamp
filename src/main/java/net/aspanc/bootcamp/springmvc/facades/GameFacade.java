package net.aspanc.bootcamp.springmvc.facades;

import net.aspanc.bootcamp.springmvc.datas.GameData;
import net.aspanc.bootcamp.springmvc.datas.GameSteamData;

import java.util.List;
import java.util.Optional;

public interface GameFacade {

    List<GameData> findAll();

    Optional<GameData> findById(Long id);

    List<GameData> findByTitle(String title);

    void delete(Long id);

    GameData save(GameData gameData);

    GameSteamData getStoreAppDetails(Integer steamId, String countryCode, String language);
}
