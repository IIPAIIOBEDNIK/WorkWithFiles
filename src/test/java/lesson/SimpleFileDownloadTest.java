package lesson;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.Selenide.*;

public class SimpleFileDownloadTest {

    static final String PATH_TO_DWD = "download";

    @AfterAll
    static void releaseFiles() throws IOException {
        FileUtils.cleanDirectory(new File(PATH_TO_DWD));
    }

    @Test
    void simpleDownload() throws Exception {
        /*
        Использовать только в крайнем случае, из-за этого может возникнуть множество ошибок
        Перехватываем загружаемый с бэка файл на уровне прокси
        Configuration.proxyEnabled = true;
        Configuration.fileDownload = PROXY;
         */

        Configuration.downloadsFolder = PATH_TO_DWD;

        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download();
        //File - работает только с путями, не выполняет чтение и запись
        //System.out.println(downloadedFile.toString()); //- выводит путь до файла

        /*
        Чтение из файла без использования библиотек
        String s;
        try (InputStream is = new FileInputStream(downloadedFile)){
            s = new String(is.readAllBytes());
        }
        System.out.println(s);
         */

        String s1 = FileUtils.readFileToString(downloadedFile, "UTF-8");
        //Чтение с использованием библиотек
        Assertions.assertTrue(s1.contains("Official CI build server for JUnit 5."));
    }
}