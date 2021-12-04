package ru.Litecart;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Countries {

    private WebDriver driver;
    private WebDriverWait wait;


    @Test
    public void countries() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("[href=\"http://localhost/litecart/admin/?app=countries&doc=countries\"]")).click();


        List<WebElement> rows = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        ArrayList<String> hrefs = new ArrayList<>();
        String prevCountry = "";
        for (WebElement row : rows) {
            WebElement countryLink = row.findElement(By.cssSelector("td:nth-child(5) a"));
            String country = countryLink.getText();
            int compare = country.compareToIgnoreCase(prevCountry);
            String numZonesStr = row.findElement(By.cssSelector("td:nth-child(6)")).getText();
            if (Integer.parseInt(numZonesStr) > 0) {
                hrefs.add(countryLink.getAttribute("href"));
            }
        }
        System.out.println(rows.size());


        for (String href : hrefs) {
            driver.navigate().to(href);
            String country = driver.findElement(By.cssSelector("input[name=name]")).getAttribute("value");
            String cellsSel = "table#table-zones.dataTable tr:not(.header) td:nth-child(3)";
            List<WebElement> cells = driver.findElements(By.cssSelector(cellsSel));
            int zoneCount = cells.size() - 1;
            String prevZoneName = "";
            for (int i = 0; i < zoneCount; i++) {
                String zoneName = cells.get(i).getText();
                int compare = zoneName.compareToIgnoreCase(prevZoneName);
            }
            System.out.println(zoneCount + country);
        }
        driver.quit();
        driver = null;
    }
}
//доделать
