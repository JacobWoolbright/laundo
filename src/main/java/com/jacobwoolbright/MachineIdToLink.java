package com.jacobwoolbright;

import java.util.HashMap;
import java.util.Map;

public class MachineIdToLink {

    private static Map<String, String> IdToLink;

    public static String GetLinkFromMachineID(String machineID){
        initMap();
        if (IdToLink.containsKey(machineID)){
            return IdToLink.get(machineID);
        }
        return null;
    }

    public static void initMap(){

        if(IdToLink != null){
            return;
        }

        IdToLink = new HashMap<>();

        IdToLink.put("81","https://mycscgo.com/qr/2Tx39");
        IdToLink.put("82","https://mycscgo.com/qr/wtDNm");
        IdToLink.put("83","https://mycscgo.com/qr/bcRAl");
        IdToLink.put("84","https://mycscgo.com/qr/rKzIF");
        IdToLink.put("85","https://mycscgo.com/qr/ONvog");
        IdToLink.put("86","https://mycscgo.com/qr/hT2qI");
        IdToLink.put("87","https://mycscgo.com/qr/fS5Uk");
        IdToLink.put("88","https://mycscgo.com/qr/aKVPC");
        IdToLink.put("89","https://mycscgo.com/qr/w1G0k");
        IdToLink.put("90","https://mycscgo.com/qr/QvZMC");
        IdToLink.put("91","https://mycscgo.com/qr/dcOnL");
        IdToLink.put("92","https://mycscgo.com/qr/Q9Au8");
        IdToLink.put("93","https://mycscgo.com/qr/9SPUc");
        IdToLink.put("94","https://mycscgo.com/qr/RCkj5");
        IdToLink.put("95","https://mycscgo.com/qr/v2VCH");
        IdToLink.put("96","https://mycscgo.com/qr/XQ3y9");
        IdToLink.put("97","https://mycscgo.com/qr/1XC4n");
        IdToLink.put("98","https://mycscgo.com/qr/LztOh");
        IdToLink.put("99","https://mycscgo.com/qr/WkC6u");
        IdToLink.put("100","https://mycscgo.com/qr/dPxai");
        IdToLink.put("101","https://mycscgo.com/qr/BLtQL");
        IdToLink.put("102","https://mycscgo.com/qr/MYsYh");
        IdToLink.put("103","https://mycscgo.com/qr/uf3Rr");
        IdToLink.put("104","https://mycscgo.com/qr/4jeI1");
        IdToLink.put("105","https://mycscgo.com/qr/nlpLl");
        IdToLink.put("106","https://mycscgo.com/qr/ftcto");
        IdToLink.put("107","https://mycscgo.com/qr/WehYu");
        IdToLink.put("108","https://mycscgo.com/qr/yNVAx");
        IdToLink.put("109","https://mycscgo.com/qr/xSfDG");
        IdToLink.put("110","https://mycscgo.com/qr/urexc");
        IdToLink.put("111","https://mycscgo.com/qr/Gxhhg");
        IdToLink.put("112","https://mycscgo.com/qr/tXVO3");
        IdToLink.put("113","https://mycscgo.com/qr/p0i9X");
        IdToLink.put("114","https://mycscgo.com/qr/hpLfZ");
        IdToLink.put("115","https://mycscgo.com/qr/Wy2aj");
        IdToLink.put("116","https://mycscgo.com/qr/tUODf");
        IdToLink.put("117","https://mycscgo.com/qr/uUAyj");
        IdToLink.put("118","https://mycscgo.com/qr/wZY1O");
        IdToLink.put("119","https://mycscgo.com/qr/dnIKo");
        IdToLink.put("120","https://mycscgo.com/qr/ZOQKc");
    }

    public static Map<String, String> getIdToLinkMap() {
        initMap();
        return IdToLink;
    }
}
