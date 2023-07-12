package com.example.springdataautomapping;

import com.example.springdataautomapping.services.GameService;
import com.example.springdataautomapping.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.example.springdataautomapping.constants.Commands.*;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;
    private final Scanner sc;

    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.sc = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        String input = sc.nextLine();
        while (!input.equals(CLOSE)){
            final String[] inputParts = input.split("\\|");
            final String command = inputParts[0];

            final String output = switch (command){
                case REGISTER_USER -> this.userService.registerUser(inputParts);
                case LOGIN_USER -> this.userService.loginUser(inputParts);
                case LOGOUT_USER -> this.userService.logout();
                case ADD_GAME -> this.gameService.addGame(inputParts);
                default -> throw new Exception();
            };

            System.out.println(output);
            input = sc.nextLine();
        }

    }
}
