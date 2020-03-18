package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CsvReader {
    private String CSV_file = "login.csv";
    private CSVReader reader;
    private String userMail;
    private String password;

    public CsvReader() throws IOException, InterruptedException, CsvValidationException {
        reader = new CSVReader(new FileReader(CSV_file));
        verifySearch();
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void verifySearch() throws InterruptedException, IOException, CsvValidationException {
        String[] cell;

        while ((cell = reader.readNext()) != null) {
            for (int i = 0; i < 1; i++) {
                userMail = cell[i];
                password = cell[i + 1];
            }
        }
    }


}
