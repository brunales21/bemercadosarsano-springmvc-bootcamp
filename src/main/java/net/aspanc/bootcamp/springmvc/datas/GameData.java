package net.aspanc.bootcamp.springmvc.datas;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameData {

    private Long id;

    @NotBlank(message = "Title can not be blank.")
    private String title;

    private String description;

    @Digits(integer = 10, fraction = 0, message = "SteamId must contain a maximum of 10 digits.")
    private Integer steamId;
}
