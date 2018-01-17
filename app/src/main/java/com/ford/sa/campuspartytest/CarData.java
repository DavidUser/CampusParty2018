package com.ford.sa.campuspartytest;

import android.util.Log;

import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.OnVehicleData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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

    public String[] VehicleParams = {"vin","fuelLevel_State", "tirePressure", "prndl", "fuelLevel", "speed","externalTemperature","rpm", "engineTorque", "odometer","driverBreaking","headLampStatus","gps","tirePressure"};

    private String vin;
    private String fuelLevel_State;
    private String prndl;
    private String fuelLevel;
    private String speed;
    private String externalTemperature;
    private String rpm;
    private String engineTorque;
    private String odometer;
    private String driverBreaking;

    /* headLampStatus */
    public String[] headLampParams = {"lowBeamsOn","ambientLightSensorStatus","highBeamsOn"};
    private String lowBeamsOn;
    private String ambientLightSensorStatus;
    private String highBeamsOn;

    /* GPS */
    //TODO Criar as variaveis do GPS


    /* tirePressure */
    public String[] tirePressureParams = {"rightRear","pressureTelltale","innerLeftRear","rightFront","innerRightRear","leftRear","leftFront"};
    private String rightRear;
    private String pressureTelltale;
    private String innerLeftRear;
    private String rightFront;
    private String innerRightRear;
    private String leftRear;
    private String leftFront;

    /* bodyInformation */
    public String[] bodyInformationParams = {"rearLeftDoorAjar","parkBreakeActive","driverDoorAjar","rearRightDoorAjar","ignitionStableStatus","passengreDoorAjar","ignitionStatus"};
    private String rearLeftDoorAjar;
    private String parkBreakeActive;
    private String driverDoorAjar;
    private String rearRightDoorAjar;
    private String ignitionStableStatus;
    private String passengreDoorAjar;
    private String ignitionStatus;






    private long timestamp;

    public CarData() {

    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getFuelLevel_State() {
        return fuelLevel_State;
    }

    public void setFuelLevel_State(String fuelLevel_State) {
        this.fuelLevel_State = fuelLevel_State;
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

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
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

    public String getEngineTorque() {
        return engineTorque;
    }

    public void setEngineTorque(String engineTorque) {
        this.engineTorque = engineTorque;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    public String getDriverBreaking() {
        return driverBreaking;
    }

    public void setDriverBreaking(String driverBreaking) {
        this.driverBreaking = driverBreaking;
    }

    public String getLowBeamsOn() {
        return lowBeamsOn;
    }

    public void setLowBeamsOn(String lowBeamsOn) {
        this.lowBeamsOn = lowBeamsOn;
    }

    public String getAmbientLightSensorStatus() {
        return ambientLightSensorStatus;
    }

    public void setAmbientLightSensorStatus(String ambientLightSensorStatus) {
        this.ambientLightSensorStatus = ambientLightSensorStatus;
    }

    public String getHighBeamsOn() {
        return highBeamsOn;
    }

    public void setHighBeamsOn(String highBeamsOn) {
        this.highBeamsOn = highBeamsOn;
    }

    public String getRightRear() {
        return rightRear;
    }

    public void setRightRear(String rightRear) {
        this.rightRear = rightRear;
    }

    public String getPressureTelltale() {
        return pressureTelltale;
    }

    public void setPressureTelltale(String pressureTelltale) {
        this.pressureTelltale = pressureTelltale;
    }

    public String getInnerLeftRear() {
        return innerLeftRear;
    }

    public void setInnerLeftRear(String innerLeftRear) {
        this.innerLeftRear = innerLeftRear;
    }

    public String getRightFront() {
        return rightFront;
    }

    public void setRightFront(String rightFront) {
        this.rightFront = rightFront;
    }

    public String getInnerRightRear() {
        return innerRightRear;
    }

    public void setInnerRightRear(String innerRightRear) {
        this.innerRightRear = innerRightRear;
    }

    public String getLeftRear() {
        return leftRear;
    }

    public void setLeftRear(String leftRear) {
        this.leftRear = leftRear;
    }

    public String getLeftFront() {
        return leftFront;
    }

    public void setLeftFront(String leftFront) {
        this.leftFront = leftFront;
    }

    public String getRearLeftDoorAjar() {
        return rearLeftDoorAjar;
    }

    public void setRearLeftDoorAjar(String rearLeftDoorAjar) {
        this.rearLeftDoorAjar = rearLeftDoorAjar;
    }

    public String getParkBreakeActive() {
        return parkBreakeActive;
    }

    public void setParkBreakeActive(String parkBreakeActive) {
        this.parkBreakeActive = parkBreakeActive;
    }

    public String getDriverDoorAjar() {
        return driverDoorAjar;
    }

    public void setDriverDoorAjar(String driverDoorAjar) {
        this.driverDoorAjar = driverDoorAjar;
    }

    public String getRearRightDoorAjar() {
        return rearRightDoorAjar;
    }

    public void setRearRightDoorAjar(String rearRightDoorAjar) {
        this.rearRightDoorAjar = rearRightDoorAjar;
    }

    public String getIgnitionStableStatus() {
        return ignitionStableStatus;
    }

    public void setIgnitionStableStatus(String ignitionStableStatus) {
        this.ignitionStableStatus = ignitionStableStatus;
    }

    public String getPassengreDoorAjar() {
        return passengreDoorAjar;
    }

    public void setPassengreDoorAjar(String passengreDoorAjar) {
        this.passengreDoorAjar = passengreDoorAjar;
    }

    public String getIgnitionStatus() {
        return ignitionStatus;
    }

    public void setIgnitionStatus(String ignitionStatus) {
        this.ignitionStatus = ignitionStatus;
    }





    public void processVehicleData(GetVehicleDataResponse notification) {

        for (HashMap.Entry<String, Object> obj : ((Hashtable<String, Object>)notification.getStore().get("response") ).entrySet() ) {
            if (obj.getKey().equals("parameters")) {
                for (HashMap.Entry<String, Object> item : ((Hashtable<String, Object>)obj.getValue()).entrySet() ) {
                    Log.d("BRUNO TESTE", "BRUNO TESTE - " + item.getKey() + " : " + item.getValue().toString());
                    if (item.getKey().equals("tirePressure")) {
                        for ( HashMap.Entry<String, Object> objTirePressute : ((Hashtable<String,Object>)item.getValue()).entrySet() ) {
                            setItem(item.getKey().toString(), ((Hashtable<String, Object>)objTirePressute.getValue()).get("status").toString() );
                        }
                    }
                    else if (item.getKey().equals("bodyInformation")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("headLampStatus")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("gps")) {
                        //TODO Verificar os dados do GPS
                    }
                    else {
                        setItem(item.getKey(), item.getValue().toString());
                    }
                }
            }
        }
        /*for (String obj : VehicleParams) {
            if (notification.getParameters(obj) != null) {
                setItem(obj, notification.getParameters(obj).toString());
            }
        }*/

        setTimestamp(System.currentTimeMillis());
    }

    public void setItemSubItem(Hashtable<String, Object> item) {
        for (HashMap.Entry<String, Object> obj : item.entrySet() ){
            setItem(obj.getKey(), obj.getValue().toString());
        }
    }


    public void setItem(String item, String valor) {
        switch (item){
            case "fuelLevel_State":
                setFuelLevel_State(valor);
                break;
            case "prndl":
                setPrndl(valor);
                break;
            case "fuelLevel":
                setFuelLevel(valor);
                break;
            case "speed":
                setSpeed(valor);
                break;
            case "externalTemperature":
                setExternalTemperature(valor);
                break;
            case "rpm":
                setRpm(valor);
                break;
            case "driverBraking":
                setDriverBreaking(valor);
                break;
            case "engineTorque":
                setEngineTorque(valor);
                break;
            case "odometer":
                setOdometer(valor);
                break;
            case "lowBeamsOn":
                setLowBeamsOn(valor);
                break;
            case "highBeamsOn":
                setHighBeamsOn(valor);
                break;
            case "ambientLightSensorStatus":
                setAmbientLightSensorStatus(valor);
                break;
            case "rearLeftDoorAjar":
                setRearLeftDoorAjar(valor);
                break;
            case "rearRightDoorAjar":
                setRearRightDoorAjar(valor);
                break;
            case "ignitionStatus":
                setIgnitionStatus(valor);
                break;
            case "driverDoorAjar":
                setDriverDoorAjar(valor);
                break;
            case "parkBrakeActive":
                setParkBreakeActive(valor);
                break;
            case "passengerDoorAjar":
                setPassengreDoorAjar(valor);
                break;
            case "ignitionStableStatus":
                setIgnitionStableStatus(valor);
                break;
            //TODO incluir o case para os TirePressure e GPS
        }
    }

    public void processVehicleSubscribe(OnVehicleData responseSubs){
        for (HashMap.Entry<String, Object> obj : ((Hashtable<String, Object>)responseSubs.getStore().get("notification") ).entrySet() ) {
            if (obj.getKey().equals("parameters")) {
                for (HashMap.Entry<String, Object> item : ((Hashtable<String, Object>)obj.getValue()).entrySet() ) {
                    Log.d("BRUNO TESTE", "BRUNO TESTE - " + item.getKey() + " : " + item.getValue().toString());
                    if (item.getKey().equals("tirePressure")) {

                    }
                    else if (item.getKey().equals("bodyInformation")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("headLampStatus")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("gps")) {

                    }
                    else {
                        setItem(item.getKey(), item.getValue().toString());
                    }
                }
            }
        }
    }

    public void newCarDataSubscribe(){
        for (String obj : VehicleParams) {
            switch (obj){
                case "fuelLevel_State":
                    setFuelLevel_State(CarData.getInstance().getFuelLevel_State());
                    break;
                case "prndl":
                    setPrndl(CarData.getInstance().getPrndl());
                    break;
                case "fuelLevel":
                    setFuelLevel(CarData.getInstance().getFuelLevel());
                    break;
                case "speed":
                    setSpeed(CarData.getInstance().getSpeed());
                    break;
                case "externalTemperature":
                    setExternalTemperature(CarData.getInstance().getExternalTemperature());
                    break;
                case "rpm":
                    setRpm(CarData.getInstance().getRpm());
                    break;
                case "driverBraking":
                    setDriverBreaking(CarData.getInstance().getDriverBreaking());
                    break;
                case "engineTorque":
                    setEngineTorque(CarData.getInstance().getEngineTorque());
                    break;
                case "odometer":
                    setOdometer(CarData.getInstance().getOdometer());
                    break;
            }
        }

        for (String obj : tirePressureParams) {
            switch (obj){
                case "rightRear":
                    setRightRear(CarData.getInstance().getRightRear());
                    break;
                case "pressureTelltale":
                    setPressureTelltale(CarData.getInstance().getPressureTelltale());
                    break;
                case "innerLeftRear":
                    setInnerLeftRear(CarData.getInstance().getInnerLeftRear());
                    break;
                case "rightFront":
                    setRightFront(CarData.getInstance().getRightFront());
                    break;
                case "innerRightRear":
                    setInnerRightRear(CarData.getInstance().getInnerRightRear());
                    break;
                case "leftRear":
                    setLeftRear(CarData.getInstance().getLeftRear());
                    break;
                case "leftFront":
                    setLeftFront(CarData.getInstance().getLeftFront());
                    break;
            }
        }

        for (String obj : bodyInformationParams) {
            switch (obj){
                case "rearLeftDoorAjar":
                    setRearLeftDoorAjar(CarData.getInstance().getRearLeftDoorAjar());
                    break;
                case "parkBreakeActive":
                    setParkBreakeActive(CarData.getInstance().getParkBreakeActive());
                    break;
                case "driverDoorAjar":
                    setDriverDoorAjar(CarData.getInstance().getDriverDoorAjar());
                    break;
                case "rearRightDoorAjar":
                    setRearRightDoorAjar(CarData.getInstance().getRearRightDoorAjar());
                    break;
                case "ignitionStableStatus":
                    setIgnitionStableStatus(CarData.getInstance().getIgnitionStableStatus());
                    break;
                case "passengreDoorAjar":
                    setPassengreDoorAjar(CarData.getInstance().getPassengreDoorAjar());
                    break;
                case "ignitionStatus":
                    setIgnitionStatus(CarData.getInstance().getIgnitionStatus());
                    break;

            }
        }

        for (String obj : headLampParams) {
            switch (obj){
                case "lowBeamsOn":
                    setLowBeamsOn(CarData.getInstance().getLowBeamsOn());
                    break;
                case "ambientLightSensorStatus":
                    setAmbientLightSensorStatus(CarData.getInstance().getAmbientLightSensorStatus());
                    break;
                case "highBeamsOn":
                    setHighBeamsOn(CarData.getInstance().getHighBeamsOn());
                    break;
            }
        }

        //TODO forEach do GPS

        setTimestamp(System.currentTimeMillis());
    }

}
