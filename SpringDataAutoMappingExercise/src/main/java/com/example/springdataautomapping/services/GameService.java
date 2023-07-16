package com.example.springdataautomapping.services;

public interface GameService {
    String addGame(String[] args);
    String editGame(String[] args);
    String deleteGame(String[] args);
    String viewAllGames();
    String viewDetailGame(String[] args);
    String viewOwnedGames();
}
