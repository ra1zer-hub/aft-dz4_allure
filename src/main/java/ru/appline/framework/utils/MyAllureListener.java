package ru.appline.framework.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.junit5.AllureJunit5;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.framework.managers.DriverManager;

import java.util.Map;

public class MyAllureListener extends AllureJunit5 {

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] addScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public Map<String, String> buildFailureEvent(String type, String uuid, Throwable throwable) {
        addScreenshot();
        return super.buildFailureEvent(type, uuid, throwable);
    }
}
