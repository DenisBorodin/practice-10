package ru.Litecart;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Basket {
    private WebDriver driver;
    private WebDriverWait wait;
    int timeout = 1;

    @Test
    public void basket() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("[href=\"http://localhost/litecart/en/\"]")).click();

        for (int i = 1; i <= 3; i++) {
            add();
        }

        driver.findElement(By.cssSelector("div#cart a.link")).click();
        delete();
        driver.quit();
        driver = null;

    }


    public void add() throws InterruptedException {
        List<WebElement> ducks = driver.findElements(By.id("box-most-popular"));
        ducks.get(0).findElement(By.className("products")).click();
        driver.findElement(By.name("add_cart_product")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[href=\"/litecart/\"]")).click();
    }

    public void delete(){
        int item = driver.findElements(By.cssSelector(".shortcut")).size();
        for (int x = item; x>0; x--) {
            if (item != 0) {
                driver.findElement(By.name("remove_cart_item")).click();
                timeOf();
                wait.until(ExpectedConditions.stalenessOf(
                        driver.findElement(By.cssSelector("table.dataTable"))));
                timeOn();
            }
        }
    }

    public void  timeOf() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public void timeOn() {
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
    }
}
