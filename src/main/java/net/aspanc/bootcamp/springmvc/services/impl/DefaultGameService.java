package net.aspanc.bootcamp.springmvc.services.impl;

import lombok.RequiredArgsConstructor;
import net.aspanc.bootcamp.springmvc.daos.GameDao;
import net.aspanc.bootcamp.springmvc.models.GameModel;
import net.aspanc.bootcamp.springmvc.services.GameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultGameService implements GameService {

    private final GameDao gameDao;

    public List<GameModel> findAll() {
        return gameDao.findAll();
    }

    public Optional<GameModel> findById(Long id) {
        return gameDao.findById(id);
    }

    public List<GameModel> findByTitle(String title) {
        return gameDao.findByTitleContainingIgnoreCase(title);
    }

    public void delete(Long id) {
        gameDao.deleteById(id);
    }

    public GameModel save(GameModel game) {
        return gameDao.save(game);
    }
}