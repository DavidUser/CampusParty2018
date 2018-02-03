package com.ford.sa.campuspartyexample;

import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.OnVehicleData;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CarData implements Serializable {

    public Map<String, String> data = new HashMap<String, String>();

    private CarData() {
      String defaultValue = " -- ";

      /* All Attributes */
      data.put("vin", defaultValue);
      data.put("fuelLevel_State", defaultValue);
      data.put("prndl", defaultValue);
      data.put("fuelLevel", defaultValue);
      data.put("speed", defaultValue);
      data.put("externalTemperature", defaultValue);
      data.put("rpm", defaultValue);
      data.put("engineTorque", defaultValue);
      data.put("odometer", defaultValue);
      data.put("driverBraking", defaultValue);
      data.put("headLampStatus", defaultValue);
      data.put("gps", defaultValue);
      data.put("tirePressure", defaultValue);

      /* headLampStatus */
      data.put("lowBeamsOn", defaultValue);
      data.put("ambientLightSensorStatus", defaultValue);
      data.put("highBeamsOn", defaultValue);

      /* GPS */
      data.put("latitudeDegrees", defaultValue);
      data.put("longitudeDegrees", defaultValue);
      data.put("altitude", defaultValue);
      data.put("heading", defaultValue);
      data.put("compassDirection", defaultValue);

      /* tirePressure */
      data.put("rightRear", defaultValue);
      data.put("pressureTelltale", defaultValue);
      data.put("innerLeftRear", defaultValue);
      data.put("rightFront", defaultValue);
      data.put("innerRightRear", defaultValue);
      data.put("leftRear", defaultValue);
      data.put("leftFront", defaultValue);

      /* bodyInformation */
      data.put("rearLeftDoorAjar", defaultValue);
      data.put("parkBrakeActive", defaultValue);
      data.put("driverDoorAjar", defaultValue);
      data.put("rearRightDoorAjar", defaultValue);
      data.put("ignitionStableStatus", defaultValue);
      data.put("passengerDoorAjar", defaultValue);
      data.put("ignitionStatus", defaultValue);
    }

    /* Singleton */
    private static CarData INSTANCE;
    public static CarData getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CarData();
        return INSTANCE;
    }

    private long timestamp;

    /* Getters & Setters */
    public String getLatitudeDegrees() {
        return latitudeDegrees;
    }

    public void setLatitudeDegrees(String latitudeDegrees) {
        this.latitudeDegrees = latitudeDegrees;
    }

    public String getLongitudeDegrees() {
        return longitudeDegrees;
    }

    public void setLongitudeDegrees(String longitudeDegrees) {
        this.longitudeDegrees = longitudeDegrees;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getCompassDirection() {
        return compassDirection;
    }

    public void setCompassDirection(String compassDirection) {
        this.compassDirection = compassDirection;
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

    public String getDriverBraking() {
        return driverBraking;
    }

    public void setDriverBraking(String driverBraking) {
        this.driverBraking = driverBraking;
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

    public String getParkBrakeActive() {
        return parkBrakeActive;
    }

    public void setParkBrakeActive(String parkBrakeActive) {
        this.parkBrakeActive = parkBrakeActive;
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

    public String getPassengerDoorAjar() {
        return passengerDoorAjar;
    }

    public void setPassengerDoorAjar(String passengerDoorAjar) {
        this.passengerDoorAjar = passengerDoorAjar;


    }

    public String getIgnitionStatus() {
        return ignitionStatus;
    }

    public void setIgnitionStatus(String ignitionStatus) {
        this.ignitionStatus = ignitionStatus;
    }

    /* GET DATA */
    public void processVehicleData(GetVehicleDataResponse notification) {

        for (HashMap.Entry<String, Object> obj : ((Hashtable<String, Object>)notification.getStore().get("response") ).entrySet() ) {
            if (obj.getKey().equals("parameters")) {
                for (HashMap.Entry<String, Object> item : ((Hashtable<String, Object>)obj.getValue()).entrySet() ) {
                    if (item.getKey().equals("tirePressure")) {
                        for ( HashMap.Entry<String, Object> objTirePressute : ((Hashtable<String,Object>)item.getValue()).entrySet() ) {
                            if (objTirePressute.getValue().getClass() == "".getClass()){
                                setItem(objTirePressute.getKey(), objTirePressute.getValue().toString());
                            }
                            else {
                                setItem(objTirePressute.getKey().toString(), ((Hashtable<String, Object>)objTirePressute.getValue()).get("status").toString() );
                            }
                        }
                    }
                    else if (item.getKey().equals("bodyInformation")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("headLampStatus")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("gps")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else {
                        setItem(item.getKey(), item.getValue().toString());
                    }
                }
            }
        }

        setTimestamp(System.currentTimeMillis());
    }

    /* SUBSCRIBE */
    public void processVehicleSubscribe(OnVehicleData responseSubs){
        for (HashMap.Entry<String, Object> obj : ((Hashtable<String, Object>)responseSubs.getStore().get("notification") ).entrySet() ) {
            if (obj.getKey().equals("parameters")) {
                for (HashMap.Entry<String, Object> item : ((Hashtable<String, Object>)obj.getValue()).entrySet() ) {
                    if (item.getKey().equals("tirePressure")) {
                        for ( HashMap.Entry<String, Object> objTirePressute : ((Hashtable<String,Object>)item.getValue()).entrySet() ) {
                            if (objTirePressute.getValue().getClass() == "".getClass()){
                                setItem(objTirePressute.getKey(), objTirePressute.getValue().toString());
                            }
                            else {
                                setItem(objTirePressute.getKey().toString(), ((Hashtable<String, Object>)objTirePressute.getValue()).get("status").toString() );
                            }
                        }
                    }
                    else if (item.getKey().equals("bodyInformation")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("headLampStatus")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("gps")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else {
                        setItem(item.getKey(), item.getValue().toString());
                    }
                }
            }
        }
    }

    /* process Hasmap to get sub-itens */
    public void setItemSubItem(Hashtable<String, Object> item) {
        for (HashMap.Entry<String, Object> obj : item.entrySet() ){
            setItem(obj.getKey(), obj.getValue().toString());
        }
    }

    /* set attributes by string name */
    private void setItem(String item, String value) {
        data.put(item, value);
    }

    /* get attributes by string name */
    public String getItem(String item) {
        return data.get(item);
    }

    /* set attributes from Static CarData Instance (used in Subscribe only) */
    public void newCarDataSubscribe(){
        setTimestamp(System.currentTimeMillis());
    }

}
