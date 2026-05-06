package utils;

import io.qameta.allure.Step;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DownloadFolderManager {
    File downloadDir;

    public DownloadFolderManager(String dirPath) {
        this.downloadDir = new File(dirPath);
    }

    @Step("Создаю папку download, если её нет")
    public  void createFolderForDownload() {
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }
    }

    @Step("Получаю количество файлов в папке download")
    public int getSizeDownloadDir() {
        long size = 0L;
        if (downloadDir.exists()) {
            try {
                size = Files.list(downloadDir.toPath())
                        .filter(Files::isRegularFile)
                        .count();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (int) size;
    }

    @Step("Очищаю папку download, если она есть и не пустая")
    public void cleanDownloadDir() {
        if (downloadDir.exists() && (getSizeDownloadDir() != 0)) {
            try {
                Files.list(downloadDir.toPath())
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Step("Проверяю что файл ManualTrades.xlsx в папке download")
    public boolean ListDealsDownloaded(String listDealsName) {
        if (!downloadDir.exists() || getSizeDownloadDir() == 0) {
            return false;
        }
        try {
            List<Path> files = Files.list(downloadDir.toPath())
                    .filter(Files::isRegularFile)
                    .toList();
            return files.stream()
                    .anyMatch(path -> path.getFileName().toString().equals(listDealsName));

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Step("Жду загрузки файла")
    public static void waitingForFileUpload() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
