package com.example.springdataautomapping.services;

import com.example.springdataautomapping.domain.entities.Game;
import com.example.springdataautomapping.domain.models.AddGameDTO;
import com.example.springdataautomapping.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.springdataautomapping.constants.Messages.SUCCESSFUL_GAME_ADD;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public String addGame(String[] args) {
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
        return null;
    }
}
