package pl.trzcinski.emil.base;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static pl.trzcinski.emil.base.ScreenShot.takeScreenShot;

@Slf4j
public class Listeners implements ITestListener {

    public Listeners() {
    }

    public void onTestFailure(ITestResult result) {

        try {
            takeScreenShot(result.getName());
            log.info("Screenshot taken");
        } catch (Exception e) {
            log.error("Error while taking screenshot: " + e.getMessage());
        }
    }
}
