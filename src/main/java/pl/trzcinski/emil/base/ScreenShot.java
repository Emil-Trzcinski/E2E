package pl.trzcinski.emil.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static pl.trzcinski.emil.base.BasePage.getDriver;

@Slf4j
public class ScreenShot {

    public static void takeScreenShot(String name) {
        File sourceFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

        String path = Paths.get("target", "screenshots").toAbsolutePath().toString();
        File destinationFile = new File(path + prepareScreenShotName(name) + ".png");

        try {
            FileUtils.copyFile(sourceFile, destinationFile);

        } catch (IOException e) {
            log.error("Error while taking screenshot: " + e.getMessage());
        }
    }

    private static String prepareScreenShotName(String name) {
        String data = null;

        try {
            data = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        } catch (Exception e) {
            log.error("Error while preparing screenshot name: " + e.getMessage());
        }

        return data;
    }
}
