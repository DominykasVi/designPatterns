package com.visnevskis;

public class GetDistrictFactory {
    public District getDistrict(String districtName){
        if (districtName == null){
            return null;
        }

        if(districtName.equalsIgnoreCase("Verkiai")){
            return new Verkiai();
        }
        if(districtName.equalsIgnoreCase("Fabijoniskes")){
            return new Fabijoniskes();
        }
        if(districtName.equalsIgnoreCase("Zirmunai")){
            return new Zirmunai();
        }
        if(districtName.equalsIgnoreCase("Antakalnis")){
            return new Antakalnis();
        }
        return null;
    }

}
