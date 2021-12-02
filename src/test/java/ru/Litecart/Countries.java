package ru.Litecart;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Countries {

    private WebDriver driver;
    private WebDriverWait wait;
    List<WebElement> countries;
    List<WebElement> countriesZonesQuantity;
    List<WebElement> countriesZones;
    List<WebElement> tableOfCountries;
    List<WebElement> countyZones;



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
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        if (Collections.sort(countries) == false) {
            AssertionError error = new AssertionError();
            System.out.println("Countries are not sorted" + error.getMessage());
            Assert.fail();

        } else {
            System.out.println("Countries are sorted correctly");
        }
        for (int i = 0; i < countriesZonesQuantity.size(); i++) {
            countriesZones = new ArrayList<WebElement>();

            if (!(countriesZonesQuantity.get(i).getText().equals("0"))) {
                countries.get(i).findElement(By.cssSelector("a")).click();
                List<WebElement> list = driver.findElements(By.cssSelector("#table-zones tr td"));

                for (WebElement element : list) {
                    if (element.getAttribute("cellIndex").equals("2")
                            && (element.findElement(By.tagName("input")).getAttribute("type").equals("hidden"))) {
                        countriesZones.add(element);
                    }
                }

                if (Collections.sort(countriesZones) == false || countriesZones.size() == 0) {
                    System.out.println(driver.getCurrentUrl() + " Country Zone are not sorted or not present");
                    Assert.fail();
                }
                System.out.println("Country's zones " + driver.getCurrentUrl() + " is sorted correctly");

                findCountiesAndCountryZonesQuantity();
            }
        }
    }

    public void findCountiesAndCountryZonesQuantity() {

        countries = new ArrayList<WebElement>();
        countriesZonesQuantity = new ArrayList<WebElement>();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> countriesAllMenu = driver.findElements(By.cssSelector(".row td"));

        for (int k = 0; k < countriesAllMenu.size(); k++) {

            if (countriesAllMenu.get(k).getAttribute("cellIndex").equals("4")) {
                countries.add(countriesAllMenu.get(k));
            }
            if (countriesAllMenu.get(k).getAttribute("cellIndex").equals("5")) {
                countriesZonesQuantity.add(countriesAllMenu.get(k));
            }
        }
    }

    @Test
    public void checkGeoZonesSorting() {

        for (int i=0; i<tableOfCountries.size(); i++) {
            WebElement link = tableOfCountries.get(i);
            link.click();
            countyZones = driver.findElements(By.cssSelector("#table-zones td select[name *= zone_code]"));

            if (Collections.sort(countyZones)==false) {
                AssertionError error = new AssertionError();
                System.out.println(error.getMessage() + "Not sorted GeoZones on the page" + driver.getCurrentUrl());
                Assert.fail();
            }
            System.out.println("Zones for " + driver.getCurrentUrl() + " is sorted");

            findTableOfCountries();
        }
    }

    public void findTableOfCountries() {
        tableOfCountries = new ArrayList<WebElement>();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        tableOfCountries = driver.findElements(By.cssSelector(".dataTable .row a:not([title])"));
    }
}
