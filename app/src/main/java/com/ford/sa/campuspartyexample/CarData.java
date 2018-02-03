package com.ford.sa.campuspartyexample;

import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.OnVehicleData;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CarData implements Serializable {

    public Map<String, String> data = new HashMap<String, String>();
    protected static String defaultValue = " -- ";

    private CarData() {

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
        return getItem("timestamp");
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

    private void process(Hashtable<String, Object> data) {

        for (HashMap.Entry<String, Object> obj : data.entrySet() ) {
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

    /* GET DATA */
    public void processVehicleData(GetVehicleDataResponse notification) {
      process((Hashtable<String, Object>) notification.getStore().get("response"));
    }

    /* SUBSCRIBE */
    public void processVehicleSubscribe(OnVehicleData responseSubs){
      process((Hashtable<String, Object>) responseSubs.getStore().get("notification"));
    }

    /* process Hasmap to get sub-itens */
    public void setItemSubItem(Hashtable<String, Object> item) {
        for (HashMap.Entry<String, Object> obj : item.entrySet() ){
            setItem(obj.getKey(), obj.getValue().toString());
        }
    }

    /* set attributes by string name */
    private void setItem(String item, String value) {
        data.put(item, value? value : defaultValue);
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
