package ru.job4j.io.chat;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * chat bot with random lines from text file
 * @author mbardakov
 * @since 01.05.2020
 */
public class Bot {
    private String path;
    private List<String> lines;
    private boolean active;

    public Bot(String path) {
        this.path = path;
        active = true;
        initLines();
    }

    /**
     * initialize list of lines from text file     *
     */
    private void initLines() {
        try (Scanner in = new Scanner(new FileInputStream(path))) {
            lines = new ArrayList<>();
            while (in.hasNext()) {
                lines.add(in.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * gets random line from list of lines
     * @return random line
     */
    public String getRandomLine() {
        return lines.get(new Random().nextInt(lines.size()));
    }
}
