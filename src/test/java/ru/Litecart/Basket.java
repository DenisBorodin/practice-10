package ru.Litecart;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Basket {
    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void basket() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("[href=\"http://localhost/litecart/en/\"]")).click();


        List<WebElement> ducks = driver.findElements(By.id("box-most-popular"));
        int x = 0;
        while (x < 3) {
            ducks.get(0).findElement(By.className("products")).click();
            driver.findElement(By.name("add_cart_product")).click();
            driver.findElement(By.id("logotype-wrapper")).click();
            x++;
        }
    }
}
//надо оперативно закончить
