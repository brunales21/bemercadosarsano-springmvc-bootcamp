package net.aspanc.bootcamp.springmvc.controllers;

import net.aspanc.bootcamp.springmvc.datas.GameData;
import net.aspanc.bootcamp.springmvc.datas.GameSteamData;
import net.aspanc.bootcamp.springmvc.facades.GameFacade;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class GameController {

    private static final String REDIRECT_GAME = "redirect:/game/";
    private static final String ERROR_404 = "error404";
    private static final String UPDATE_GAME = "updateGame";
    private static final String CREATE_GAME = "createGame";
    private static final String INDEX = "index";
    private static final String REDIRECT = "redirect:/";
    private static final String GAME_DETAILS = "gameDetails";
    private static final String GAMES = "games";
    private static final String GAME = "game";
    private static final String VALIDATION_MESSAGE = "valid";
    private static final String STEAMID_VALIDATION_MESSAGE = "notValid";
    private static final String TYPE_MISMATCH = "typeMismatch";
    private static final String SUCCESSFUL_DELETE = "successfulDelete";
    private static final String SUCCESSFUL_CREATE = "successfulCreate";
    private static final String SUCCESSFUL_UPDATE = "successfulUpdate";
    private static final String SUCCESSFUL_ACTION = "ok";

    @Resource
    private GameFacade gameFacade;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(GAMES, gameFacade.findAll());
        return INDEX;
    }

    @GetMapping("/game/{id}")
    public String gameDataDetails(Model model, @PathVariable Long id) {
        return gameFacade.findById(id)
                         .map(gameData -> {
                             model.addAttribute(GAME, gameData);
                             return GAME_DETAILS;
                         })
                         .orElse(ERROR_404);
    }

    @PostMapping("/game/delete/{id}")
    public String deleteGame(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        return gameFacade.findById(id)
                         .map(game -> {
                             gameFacade.delete(id);
                             redirectAttributes.addFlashAttribute(SUCCESSFUL_DELETE, SUCCESSFUL_ACTION);
                             return REDIRECT;
                         })
                         .orElse(ERROR_404);
    }

    @GetMapping("/game")
    public String findByTitle(Model model, @RequestParam String title) {
        model.addAttribute(GAMES, gameFacade.findByTitle(title));
        return INDEX;
    }

    @GetMapping("/game/new")
    public String showCreateGameForm(Model model) {
        model.addAttribute(GAME, new GameData());
        return CREATE_GAME;
    }

    @PostMapping("/game/new")
    public String createGame(Model model, @Valid @ModelAttribute(GAME) GameData game, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            if (result.getAllErrors()
                      .stream()
                      .anyMatch(e -> Objects.equals(e.getCode(), TYPE_MISMATCH))) {
                model.addAttribute(VALIDATION_MESSAGE, STEAMID_VALIDATION_MESSAGE);
            }
            model.addAttribute(GAME, game);
            return CREATE_GAME;
        }
        gameFacade.save(game);
        redirectAttributes.addFlashAttribute(SUCCESSFUL_CREATE, SUCCESSFUL_ACTION);
        return REDIRECT;
    }

    @GetMapping("/game/edit/{id}")
    public String showUpdateGameForm(Model model, @PathVariable Long id) {
        return gameFacade.findById(id)
                         .map(game -> {
                             model.addAttribute(GAME, game);
                             return UPDATE_GAME;
                         })
                         .orElse(ERROR_404);
    }

    @PostMapping("/game/edit/{id}")
    public String updateGameForm(Model model, @Valid @ModelAttribute("game") GameData game, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            if (result.getAllErrors()
                      .stream()
                      .anyMatch(e -> Objects.equals(e.getCode(), TYPE_MISMATCH))) {
                model.addAttribute(VALIDATION_MESSAGE, STEAMID_VALIDATION_MESSAGE);
            }
            model.addAttribute(GAME, game);
            return UPDATE_GAME;
        }
        gameFacade.save(game);
        redirectAttributes.addFlashAttribute(SUCCESSFUL_UPDATE, SUCCESSFUL_ACTION);
        return REDIRECT_GAME + game.getId();
    }

    @GetMapping("/steam/details/{steamId}")
    @ResponseBody
    public GameSteamData getSteamGameDetails(@PathVariable Integer steamId) {
        return gameFacade.getStoreAppDetails(steamId,
                LocaleContextHolder.getLocale().getCountry(),
                LocaleContextHolder.getLocale().getLanguage());

    }
}