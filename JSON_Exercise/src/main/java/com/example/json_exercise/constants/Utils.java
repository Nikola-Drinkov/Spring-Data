package com.example.json_exercise.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public enum Utils {
    ;
    public static final ModelMapper MODEL_MAPPER = new ModelMapper();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static <T> void writeIntoJsonFile(List<T> list, Path path) throws IOException {
        final FileWriter fileWriter = new FileWriter(path.toFile());
        GSON.toJson(list, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }
}
