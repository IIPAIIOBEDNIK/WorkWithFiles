package lesson;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class pdfDownloadTest {

    @Test
    void  junitPdfTest() throws IOException {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File pdfDownloadedFile = $(byText("PDF download")).download();
        PDF parsedPDF = new PDF(pdfDownloadedFile);
        assertThat(parsedPDF.author).contains("Marc Philipp");
    }
}
