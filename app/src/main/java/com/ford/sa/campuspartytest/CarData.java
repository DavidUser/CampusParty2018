package com.ford.sa.campuspartytest;

import com.smartdevicelink.proxy.rpc.OnVehicleData;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by bgarci67 on 1/5/18.
 */

public class CarData {

    private static CarData INSTANCE;

    public static CarData getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CarData();
        return INSTANCE;
    }

    public String[] VehicleParams = {"fuelLevel_State", "tirePressure", "prndl", "fuelLevel", "speed","externalTemperature","rpm"};

    private String fuelLevel_State;
    private String tirePressure;
    private String prndl;
    private String fuelLevel;
    private ArrayList<Double> listSpeed = new ArrayList<>();
    private String externalTemperature;
    private String rpm;

    public CarData() {

    }



    public String getFuelLevel_State() {
        return fuelLevel_State;
    }

    public void setFuelLevel_State(String fuelLevel_State) {
        this.fuelLevel_State = fuelLevel_State;
    }

    public String getTirePressure() {
        return tirePressure;
    }

    public void setTirePressure(String tirePressure) {
        this.tirePressure = tirePressure;
    }

    public String getPrndl() {
        return prndl;
    }

    public void setPrndl(String prndl) {
        this.prndl = prndl;
    }

    public String getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(String fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public ArrayList<Double> getSpeed() {
        return listSpeed;
    }

    public void setSpeed(Double speed) {
        this.listSpeed.add(speed);
    }

    public String getExternalTemperature() {
        return externalTemperature;
    }

    public void setExternalTemperature(String externalTemperature) {
        this.externalTemperature = externalTemperature;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
    }


    public void processVehicleData(OnVehicleData notification) {
        for (String obj : VehicleParams) {

            if (notification.getParameters(obj) != null) {
                switch (obj){
                    case "fuelLevel_State":
                        setFuelLevel_State(notification.getParameters(obj).toString());
                        break;
                    case "tirePressure":
                        setTirePressure(notification.getParameters(obj).toString());
                        break;
                    case "prndl":
                        setPrndl(notification.getParameters(obj).toString());
                        break;
                    case "fuelLevel":
                        setFuelLevel(notification.getParameters(obj).toString());
                        break;
                    case "speed":
                        setSpeed((Double)notification.getParameters(obj));
                        break;
                    case "externalTemperature":
                        setExternalTemperature(notification.getParameters(obj).toString());
                        break;
                    case "rpm":
                        setRpm(notification.getParameters(obj).toString());
                        break;
                }
            }
        }
    }

    public void processVehicleSubscribe(OnVehicleData responseSubs){

        System.out.println(responseSubs);


        System.out.println(responseSubs);

    }
}
