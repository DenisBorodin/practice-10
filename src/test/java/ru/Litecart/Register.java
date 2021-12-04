package ru.Litecart;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class Register {

    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void register() throws InterruptedException {
        enter();
        driver.get("http://localhost/litecart/en/");

        String email = randomEmail();
        String password = "123456";

        create(email, password);
        Thread.sleep(1000);

        logout();
        Thread.sleep(1000);

        login(email, password);
        Thread.sleep(1000);

        logout();
        Thread.sleep(1000);

        driver.quit();
        driver = null;
    }


    public void enter(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
    }

    public String randomEmail() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        System.out.println(generatedString + "@mail.ru");
        return generatedString + "@mail.ru";
    }

    private void create(String email, String password) {
        driver.findElement(By.cssSelector("form[name='login_form'] table tr:last-child")).click();
        driver.findElement(By.name("firstname")).sendKeys("Denis");
        driver.findElement(By.name("lastname")).sendKeys("Borodin");
        driver.findElement(By.name("address1")).sendKeys("618 Gopher Road");
        driver.findElement(By.name("postcode")).sendKeys("97603");
        driver.findElement(By.name("city")).sendKeys("Washington");
        driver.findElement(By.cssSelector(".select2-selection")).click();
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("United States" + Keys.ENTER);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+15036789872");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.name("create_account")).click();
    }

    private void login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    private void logout() {
        driver.findElement(By.cssSelector("div#box-account div.content li:last-child a")).click();
    }
}

