package com.jacobwoolbright;

import com.jacobwoolbright.db.DatabaseManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class CheckStatus {

    public static Status CheckStatusOfMachine(String machineID){
        WebDriver driver = WebDriverManager.getInstance().getDriver();
        driver.get(MachineIdToLink.GetLinkFromMachineID(machineID));

        Status status = new Status(machineID);

//        FREE SELECTOR: //*[@id="root"]/div/main/div[3]/div/div[2]

        long startTime = System.currentTimeMillis();
        while (true){

            // try to get time element
            try{
                WebElement timeElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div/div/div/span[1]"));
                String timeString = timeElement.getText();
                if(timeString.matches("-?\\d+")){
                    status.setRunning(true);
                    status.setAvailable(false);
                    status.setTimeLeft(Integer.valueOf(timeString));
                    status.setStatusMessage("running");
                    return status;
                }
            }
            catch (Exception ignored){
                if((System.currentTimeMillis() - startTime) >= 2000){
                    status.setAvailable(false);
                    status.setStatusMessage("not responding...");
                    status.setRunning(false);
                    return status;
                }
            }

            // try to get "Press Start"
            try{
                WebElement pressStartElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div/div/h1"));
                String pressStartString = pressStartElement.getText();
                if(pressStartString.equalsIgnoreCase("press start")){
                    status.setRunning(false);
                    status.setAvailable(true);
                    status.setStatusMessage("press start");
                    return status;
                }
            }
            catch (Exception ignored){
                if((System.currentTimeMillis() - startTime) >= 2000){
                    status.setAvailable(false);
                    status.setStatusMessage("not responding...");
                    status.setRunning(false);
                    return status;
                }
            }
            // try to get "Free" element
            try{
                WebElement freeElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[3]/div/div[2]"));
                String freeText = freeElement.getText();
                if(freeText.equalsIgnoreCase("free")){
                    status.setRunning(false);
                    status.setAvailable(true);
                    status.setStatusMessage("free");
                    return status;
                }
            }
            catch (Exception ignored){
                if((System.currentTimeMillis() - startTime) >= 2000){
                    status.setAvailable(false);
                    status.setStatusMessage("not responding...");
                    status.setRunning(false);
                    return status;
                }
            }
            // try to get "offline" element
            try{
                WebElement offlineElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[3]/div/div[1]/div/h3"));
                String offlineText = offlineElement.getText();
                if(offlineText.equalsIgnoreCase("machine offline")){
                    status.setRunning(false);
                    status.setAvailable(false);
                    status.setStatusMessage("offline");
                    return status;
                }
            }
            catch (Exception ignored){
                if((System.currentTimeMillis() - startTime) >= 2000){
                    status.setAvailable(false);
                    status.setStatusMessage("not responding...");
                    status.setRunning(false);
                    return status;
                }
            }
            // try to get "done" element
            try{
                WebElement doneElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div/div/h1"));
                String doneText = doneElement.getText();
                if(doneText.equalsIgnoreCase("done")){
                    status.setRunning(false);
                    status.setAvailable(true);
                    status.setStatusMessage("done");
                    return status;
                }
            }
            catch (Exception ignored){
                if((System.currentTimeMillis() - startTime) >= 2000){
                    status.setAvailable(false);
                    status.setStatusMessage("not responding...");
                    status.setRunning(false);
                    return status;
                }
            }
        }
    }


    public static ArrayList<Status> checkAll(){
        ArrayList<Status> statusList = new ArrayList<>();

        for(String key : MachineIdToLink.getIdToLinkMap().keySet()){

            Status status = CheckStatusOfMachine(key);
            NotificationManager.checkNotification(status);
            statusList.add(status);
        }
        return statusList;
    }

}
