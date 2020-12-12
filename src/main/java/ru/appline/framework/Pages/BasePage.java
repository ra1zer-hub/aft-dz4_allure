package ru.appline.framework.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.PagesManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.appline.framework.managers.DriverManager.getDriver;

public class BasePage {

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 15, 1000);
    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();
    protected PagesManager app = PagesManager.getManagerPages();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    protected WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void updateInfo(WebElement newPayment, double currentPayment) {
        while (getNumberFromWebElement(newPayment) != currentPayment) {
            sleep(1000);
            currentPayment = getNumberFromWebElement(newPayment);
        }
    }

    protected double getNumberFromWebElement(WebElement element) {
        return Double.parseDouble(element.getText().replaceAll("[\\D.]", ""));
    }

    protected void fillInputField(WebElement field, String value) {
        field = field.findElement(By.xpath(".//input"));
        elementToBeClickable(field).click();
        while (!field.getAttribute("value").isEmpty()) {
            field.sendKeys(Keys.BACK_SPACE);
        }
        field.sendKeys(value);
        assertEquals(value, field.getAttribute("value"),
                "Поле заполнено не правильно.");
    }

    protected void returnDefaultFrame() {
        getDriver().switchTo().defaultContent();
    }

    protected void checkAdditionalOptions(WebElement element, boolean value) {
        String expectedValue = String.valueOf(value);
        WebElement additionalServices = element.findElement(By.xpath(".//input"));
        if (!additionalServices.getAttribute("ariaChecked").equals(expectedValue)) {
            additionalServices.click();
            assertEquals(expectedValue, additionalServices.getAttribute("ariaChecked"),
                    "Ошибка при выборе доп. услуг");
        }
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
