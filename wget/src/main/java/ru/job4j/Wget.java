package ru.job4j;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class Wget implements Callable<Boolean> {

    private String fileAddress;
    private String speed;

    public Wget(String fileAddress, String speed) {
        this.fileAddress = fileAddress;
        this.speed = speed;
    }

    @Override
    public Boolean call() throws Exception {
        String fileName = fileAddress.substring(fileAddress.lastIndexOf('/') + 1);
        URL url = new URL(fileAddress);
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(
                     System.getProperty("user.dir") + "\\" + fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long startTime = Calendar.getInstance().getTimeInMillis();
            var count = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                count++;
                if (count == Integer.parseInt(speed)) {
                    count = 0;
                    var currentTime = Calendar.getInstance().getTimeInMillis();
                    var deltaTime = currentTime - startTime;
                    if (deltaTime < 1000) {
                        Thread.sleep(deltaTime);
                    }
                    startTime = currentTime;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new Exception("Wrong arguments" + System.lineSeparator()
                        + "correct usage: java -jar jarFile.jar URL speed");
            }
            var executor = Executors.newSingleThreadExecutor();
            executor.submit(new Wget(args[0], args[1]));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
