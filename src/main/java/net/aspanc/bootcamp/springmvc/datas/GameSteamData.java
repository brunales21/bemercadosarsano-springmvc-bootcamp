package net.aspanc.bootcamp.springmvc.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GameSteamData {

    private String screenshotUrl;
}
