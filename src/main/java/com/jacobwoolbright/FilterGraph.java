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

        return result;
    }

    public static Map<Date, Integer> getGraphPointsWasher(String timespan){

        Map<Date, Integer> result = new HashMap<>();

        Map<Date, Integer> data = DatabaseManager.getInstance().getWasherAvailabilityRaw(timespan);

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

        return result;
    }

    public static Map<Date, Integer> getGraphPointsWasher(String timespan, String groupTime){

        Map<Date, Integer> result = new HashMap<>();

        Map<Date, Integer> data = DatabaseManager.getInstance().getWasherAvailabilityRaw(timespan);

        int groupTimeInt = Integer.valueOf(groupTime.substring(0, groupTime.length()-1));

        for(Date apiDate : data.keySet()){
            boolean found = false;
            for(Date resultDate : result.keySet()){
                if(groupTime.endsWith("m")){
                    if(Math.abs(TimeUtils.getDateDiff(apiDate, resultDate, TimeUnit.MINUTES)) <= groupTimeInt){
                        result.replace(resultDate, result.get(resultDate) + data.get(apiDate));
                        found = true;
                    }
                } else if (groupTime.endsWith("h")) {
                    if(Math.abs(TimeUtils.getDateDiff(apiDate, resultDate, TimeUnit.HOURS)) <= groupTimeInt){
                        result.replace(resultDate, result.get(resultDate) + data.get(apiDate));
                        found = true;
                    }
                }
            }
            if(!found){
                result.put(apiDate, data.get(apiDate));
            }
        }

        return result;
    }

}
