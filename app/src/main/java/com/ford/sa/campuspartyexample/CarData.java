package com.ford.sa.campuspartyexample;

import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.OnVehicleData;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CarData implements Serializable {

    public Map<String, String> data = new HashMap<String, String>();
    protected static String DEFAULT_VALUE = " -- ";

    /* Singleton */
    private static CarData INSTANCE;
    public static CarData getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CarData();
        return INSTANCE;
    }

    private long timestamp;

    /* Getters */
    public String getLatitudeDegrees() {
        return getItem("latitudeDegrees");
    }

    public String getLongitudeDegrees() {
        return getItem("longitudeDegrees");
    }

    public String getAltitude() {
        return getItem("altitude");
    }

    public String getHeading() {
        return getItem("heading");
    }

    public String getCompassDirection() {
        return getItem("compassDirection");
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getVin() {
        return getItem("vin");
    }

    public String getFuelLevel_State() {
        return getItem("fuelLevel_State");
    }

    public String getPrndl() {
        return getItem("prndl");
    }

    public String getFuelLevel() {
        return getItem("fuelLevel");
    }

    public String getSpeed() {
        return getItem("speed");
    }

    public String getExternalTemperature() {
        return getItem("externalTemperature");
    }

    public String getRpm() {
        return getItem("rpm");
    }

    public String getEngineTorque() {
        return getItem("engineTorque");
    }

    public String getOdometer() {
        return getItem("odometer");
    }

    public String getDriverBraking() {
        return getItem("driverBraking");
    }

    public String getLowBeamsOn() {
        return getItem("lowBeamsOn");
    }

    public String getAmbientLightSensorStatus() {
        return getItem("ambientLightSensorStatus");
    }

    public String getHighBeamsOn() {
        return getItem("highBeamsOn");
    }

    public String getRightRear() {
        return getItem("rightRear");
    }

    public String getPressureTelltale() {
        return getItem("pressureTelltale");
    }

    public String getInnerLeftRear() {
        return getItem("innerLeftRear");
    }

    public String getRightFront() {
        return getItem("rightFront");
    }

    public String getInnerRightRear() {
        return getItem("innerRightRear");
    }

    public String getLeftRear() {
        return getItem("leftRear");
    }

    public String getLeftFront() {
        return getItem("leftFront");
    }

    public String getRearLeftDoorAjar() {
        return getItem("rearLeftDoorAjar");
    }

    public String getParkBrakeActive() {
        return getItem("parkBrakeActive");
    }

    public String getDriverDoorAjar() {
        return getItem("driverDoorAjar");
    }

    public String getRearRightDoorAjar() {
        return getItem("rearRightDoorAjar");
    }

    public String getIgnitionStableStatus() {
        return getItem("ignitionStableStatus");
    }

    public String getPassengerDoorAjar() {
        return getItem("passengerDoorAjar");
    }

    public String getIgnitionStatus() {
        return getItem("ignitionStatus");
    }

    private void updateTimestamp() {
        this.timestamp = System.currentTimeMillis();
    }

    private void process(Hashtable<String, Object> data) {
        updateTimestamp();

        Hashtable<String, Object> parameters = (Hashtable<String, Object>) data.get("parameters");
        if (parameters == null) 
            return;

        for (HashMap.Entry<String, Object> item : parameters.entrySet() ) {
            switch (item.getKey()) {
                case "tirePressure":
                    for ( HashMap.Entry<String, Object> objTirePressute : ((Hashtable<String,Object>)item.getValue()).entrySet() ) {
                        setItem(
                            objTirePressute.getKey(), 
                            objTirePressute.getValue() instanceof String? 
                            objTirePressute.getValue().toString() : ((Hashtable<String, Object>)objTirePressute.getValue()).get("status").toString()
                        );
                    }
                    break;
                case "bodyInformation":
                case "headLampStatus":
                case "gps" :
                    for (HashMap.Entry<String, Object> obj : ((Hashtable<String, Object>) item.getValue()).entrySet() ) {
                        setItem(obj.getKey(), obj.getValue().toString());
                    }
                    break;
                default:
                    setItem(item.getKey(), item.getValue().toString());
            }
        }
    }

    /* GET DATA */
    public void processVehicleData(GetVehicleDataResponse notification) {
      process((Hashtable<String, Object>) notification.getStore().get("response"));
    }

    /* SUBSCRIBE */
    public void processVehicleSubscribe(OnVehicleData responseSubs){
      process((Hashtable<String, Object>) responseSubs.getStore().get("notification"));
    }

    /* set attributes by string name */
    private void setItem(String item, String value) {
        data.put(item, value == null? DEFAULT_VALUE : value);
    }

    /* get attributes by string name */
    public String getItem(String item) {
        String value = data.get(item);
        return value == null? DEFAULT_VALUE : value;
    }

    /* set attributes from Static CarData Instance (used in Subscribe only) */
    public void newCarDataSubscribe(){
        updateTimestamp();
    }

}
