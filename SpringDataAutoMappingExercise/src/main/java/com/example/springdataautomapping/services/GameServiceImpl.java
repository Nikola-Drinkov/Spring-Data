package com.example.springdataautomapping.services;

import com.example.springdataautomapping.domain.entities.Game;
import com.example.springdataautomapping.domain.models.AddGameDTO;
import com.example.springdataautomapping.domain.models.DetailedGameDTO;
import com.example.springdataautomapping.domain.models.EditGameDTO;
import com.example.springdataautomapping.domain.models.GameTitleAndPriceDTO;
import com.example.springdataautomapping.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.springdataautomapping.constants.ErrorMessage.*;
import static com.example.springdataautomapping.constants.Messages.*;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        modelMapper = new ModelMapper();
    }

    @Override
    public String addGame(String[] args) {
        if(!this.userService.isLoggedAdmin()) return NON_ADMIN_GAME_ADD;

        if(this.gameRepository.findByTitle(args[1]).isPresent()) return "The game is already added!";

        String dateFormat = "dd-MM-yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

        String title = args[1];
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(args[2]));
        BigDecimal size = BigDecimal.valueOf(Double.parseDouble(args[3]));
        String trailer = args[4];
        String imageThumbnail = args[5];
        String description = args[6];
        LocalDate releaseDate = LocalDate.parse(args[7],dateFormatter);

        AddGameDTO addGameDTO;
        try{
            addGameDTO = new AddGameDTO(title,price,size,trailer,imageThumbnail,description,releaseDate);
        }catch (IllegalStateException e){
            return e.getMessage();
        }
        Game gameToAdd = modelMapper.map(addGameDTO,Game.class);
        this.gameRepository.saveAndFlush(gameToAdd);
        return String.format(SUCCESSFUL_GAME_ADD,gameToAdd.getTitle());
    }

    @Override
    public String editGame(String[] args) {
        if(!this.userService.isLoggedAdmin()) return NON_ADMIN_GAME_EDIT;

        if(this.gameRepository.findById(Long.parseLong(args[1])).isEmpty()) return "Nothing to edit. First add the game";

        final Optional<Game> gameToUpdate = this.gameRepository.findById(Long.parseLong(args[1]));
        if (gameToUpdate.isEmpty()) return NO_SUCH_GAME_EXISTING;

        Map<String, String> updatingArguments = new HashMap<>();
        for (int i = 2; i < args.length ; i++) {
            String[] argsForUpdate = args[i].split("=");
            updatingArguments.put(argsForUpdate[0],argsForUpdate[1]);
        }
        final EditGameDTO editGameDTO = modelMapper.map(gameToUpdate, EditGameDTO.class);
        editGameDTO.updateFields(updatingArguments);

        Game updatedGame = modelMapper.map(editGameDTO, Game.class);
        this.gameRepository.saveAndFlush(updatedGame);

        return String.format(SUCCESSFUL_GAME_EDIT,updatedGame.getTitle());
    }

    @Override
    public String deleteGame(String[] args) {
        if(!this.userService.isLoggedAdmin()) return NON_ADMIN_GAME_DELETE;

        final Optional<Game> gameToDelete = this.gameRepository.findById(Long.parseLong(args[1]));
        if (gameToDelete.isEmpty()) return NO_SUCH_GAME_EXISTING;

        this.gameRepository.delete(gameToDelete.get());
        return String.format(SUCCESSFUL_GAME_DELETE, gameToDelete.get().getTitle());
    }

    @Override
    public String viewAllGames() {
        if(this.gameRepository.count()==0) return NO_GAMES_ADDED;

        StringBuilder view = new StringBuilder();
        List<Game> games = this.gameRepository.findAll();
        games.forEach(game->{
            GameTitleAndPriceDTO gameTitleAndPriceDTO = modelMapper.map(game, GameTitleAndPriceDTO.class);
            view.append(gameTitleAndPriceDTO.toString()).append(System.lineSeparator());
        });
        return view.toString();
    }

    @Override
    public String viewDetailGame(String[] args) {
        if(this.gameRepository.count()==0) return NO_GAMES_ADDED;

        Optional<Game> game = this.gameRepository.findByTitle(args[1]);
        DetailedGameDTO detailedGameDTO = modelMapper.map(game, DetailedGameDTO.class);
        return detailedGameDTO.toString();
    }

    @Override
    public String viewOwnedGames() {
        //part of task 5
        return null;
    }
}
