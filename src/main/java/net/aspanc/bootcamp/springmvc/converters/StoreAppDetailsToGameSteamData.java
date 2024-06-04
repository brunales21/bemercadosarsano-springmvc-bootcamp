package net.aspanc.bootcamp.springmvc.converters;

import com.ibasco.agql.protocols.valve.steam.webapi.pojos.StoreAppDetails;
import net.aspanc.bootcamp.springmvc.datas.GameSteamData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class StoreAppDetailsToGameSteamData implements Converter<StoreAppDetails, GameSteamData> {

    @Override
    public GameSteamData convert(StoreAppDetails storeAppDetails) {
        return GameSteamData.builder()
                            .screenshotUrl(storeAppDetails.getScreenshots()
                                                          .get(new Random().nextInt(storeAppDetails.getScreenshots().size()))
                                                          .getFullPath())
                            .build();
    }
}