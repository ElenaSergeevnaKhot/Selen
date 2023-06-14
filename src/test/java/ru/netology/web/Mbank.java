package ru.netology.web;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Mbank {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("driver.chromedriver", "./drivers/win/chromedriver");
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options =new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
    @Test
    void shouldTestV1() {
        driver.get("http://localhost:9999/");
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        inputs.get(0).sendKeys("Иванов Василий");
        inputs.get(1).sendKeys("+79856239080");
        driver.findElement(By.tagName("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText().trim();
        assertEquals(expected, actual);

    }
}
