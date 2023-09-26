package com.jacobwoolbright;

import com.jacobwoolbright.db.DatabaseManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FilterGraph {

    public static Map<Date, Integer> getGraphPointsDryer(){
        return getGraphPointsDryer("24h", "2m");
    }

    public static Map<Date, Integer> getGraphPointsDryer(String timespan){

        return getGraphPointsDryer(timespan, "2m");
    }

    public static Map<Date, Integer> getGraphPointsDryer(String timespan, String groupTime){

        Map<Date, Integer> result = new HashMap<>();

        ArrayList<Availability> data = DatabaseManager.getInstance().getDryerAvailabilityRaw(timespan);

        int groupTimeInt = Integer.valueOf(groupTime.substring(0, groupTime.length()-1));

        for(Availability availability : data){
            boolean found = false;
            for(Date resultDate : result.keySet()){
                if(groupTime.endsWith("m")){
                    if(Math.abs(TimeUtils.getDateDiff(availability.getTime(), resultDate, TimeUnit.MINUTES)) <= groupTimeInt){
                        result.replace(resultDate, result.get(resultDate) + availability.getAvailable());
                        found = true;
                    }
                } else if (groupTime.endsWith("h")) {
                    if(Math.abs(TimeUtils.getDateDiff(availability.getTime(), resultDate, TimeUnit.HOURS)) <= groupTimeInt){
                        result.replace(resultDate, result.get(resultDate) + availability.getAvailable());
                        found = true;
                    }
                }
            }
            if(!found){
                result.put(availability.getTime(), availability.getAvailable());
            }
        }

        return result;
    }

    public static Map<Date, Integer> getGraphPointsWasher(){

        return getGraphPointsWasher("24h", "2m");
    }

    public static Map<Date, Integer> getGraphPointsWasher(String timespan){

        return getGraphPointsWasher(timespan, "2m");
    }

    public static Map<Date, Integer> getGraphPointsWasher(String timespan, String groupTime){

        Map<Date, Integer> result = new HashMap<>();

        ArrayList<Availability> data = DatabaseManager.getInstance().getWasherAvailabilityRaw(timespan);

        int groupTimeInt = Integer.valueOf(groupTime.substring(0, groupTime.length()-1));

        for(Availability availability : data){
            boolean found = false;
            for(Date resultDate : result.keySet()){
                if(groupTime.endsWith("m")){
                    if(Math.abs(TimeUtils.getDateDiff(availability.getTime(), resultDate, TimeUnit.MINUTES)) <= groupTimeInt){
                        result.replace(resultDate, result.get(resultDate) + availability.getAvailable());
                        found = true;
                    }
                } else if (groupTime.endsWith("h")) {
                    if(Math.abs(TimeUtils.getDateDiff(availability.getTime(), resultDate, TimeUnit.HOURS)) <= groupTimeInt){
                        result.replace(resultDate, result.get(resultDate) + availability.getAvailable());
                        found = true;
                    }
                }
            }
            if(!found){
                result.put(availability.getTime(), availability.getAvailable());
            }
        }

        return result;
    }
}
