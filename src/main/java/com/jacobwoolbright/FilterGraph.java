package com.jacobwoolbright;

import com.jacobwoolbright.db.DatabaseManager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FilterGraph {

    public static Map<Date, Integer> getGraphPointsDryer(){
        Map<Date, Integer> result = new HashMap<>();

        Map<Date, Integer> data = DatabaseManager.getInstance().getDryerAvailabilityRaw();


        for(Date apiDate : data.keySet()){
            boolean found = false;
            for(Date resultDate : result.keySet()){
                if(Math.abs(TimeUtils.getDateDiff(apiDate, resultDate, TimeUnit.SECONDS)) <= 120){
                    result.replace(resultDate, result.get(resultDate) + data.get(apiDate));
                    found = true;
                }
            }
            if(!found){
                result.put(apiDate, data.get(apiDate));
            }
        }

        System.out.println("-----------------------");
        for(Date date : result.keySet()){
            System.out.println(date + ": " + result.get(date));
        }

        return result;
    }

    public static Map<Date, Integer> getGraphPointsWasher(){
        Map<Date, Integer> result = new HashMap<>();

        Map<Date, Integer> data = DatabaseManager.getInstance().getWasherAvailabilityRaw();


        for(Date apiDate : data.keySet()){
            boolean found = false;
            for(Date resultDate : result.keySet()){
                if(Math.abs(TimeUtils.getDateDiff(apiDate, resultDate, TimeUnit.SECONDS)) <= 120){
                    result.replace(resultDate, result.get(resultDate) + data.get(apiDate));
                    found = true;
                }
            }
            if(!found){
                result.put(apiDate, data.get(apiDate));
            }
        }

        System.out.println("-----------------------");
        for(Date date : result.keySet()){
            System.out.println(date + ": " + result.get(date));
        }

        return result;
    }

}
