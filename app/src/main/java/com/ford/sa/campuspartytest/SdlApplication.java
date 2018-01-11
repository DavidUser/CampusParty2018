package com.ford.sa.campuspartytest;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.ford.sa.interfacesdl.Config;
import com.ford.sa.interfacesdl.hmi.CurrentHMIState;
import com.ford.sa.interfacesdl.hmi.EnumDisplayLayout;
import com.ford.sa.interfacesdl.hmi.HMIScreenManager;
import com.ford.sa.interfacesdl.listeners.ServiceListeners;
import com.ford.sa.interfacesdl.telematics.TelematicsCollector;
import com.smartdevicelink.proxy.rpc.AddCommandResponse;
import com.smartdevicelink.proxy.rpc.AddSubMenuResponse;
import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.Image;
import com.smartdevicelink.proxy.rpc.OnButtonPress;
import com.smartdevicelink.proxy.rpc.OnCommand;
import com.smartdevicelink.proxy.rpc.OnHMIStatus;
import com.smartdevicelink.proxy.rpc.OnVehicleData;
import com.smartdevicelink.proxy.rpc.SoftButton;
import com.smartdevicelink.proxy.rpc.enums.ImageType;
import com.smartdevicelink.proxy.rpc.enums.SoftButtonType;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by bgarci67 on 1/4/18.
 */

public class SdlApplication extends com.ford.sa.interfacesdl.SdlApplication {


    Vector<SoftButton> softButtons = new Vector<SoftButton>();
    SoftButton btnGetData = new SoftButton();
    SoftButton btnSubsData = new SoftButton();
    SoftButton btnStopSub = new SoftButton();

    private SdlApplication INSTANCE;

    public SdlApplication() {
        this.INSTANCE = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {






            ////------------------------------------------------------------------------------------------
            /**
             * Screen Manager Listener
             */
            ServiceListeners.getInstance().listenerScreen = new ServiceListeners.ListenerScreen() {
                @Override
                public void showWelcomeScreen() {
                    HMIScreenManager.getInstance().setShowLayout(EnumDisplayLayout.TEXT_AND_SOFTBUTTONS_WITH_GRAPHIC );

                    HMIScreenManager.getInstance().setNewShow();

                    HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                    HMIScreenManager.getInstance().newShow.setMainField2("New Teste Bruno");
                    HMIScreenManager.getInstance().newShow.setMainField3("Teste Data Collection");

                    btnGetData.setSoftButtonID(1);
                    btnGetData.setType(SoftButtonType.SBT_BOTH);
                    Image imgGetData = new Image();
                    imgGetData.setValue("start.png");
                    imgGetData.setImageType(ImageType.DYNAMIC);
                    btnGetData.setImage(imgGetData);
                    btnGetData.setText("Get");
                    btnGetData.setIsHighlighted(false);
                    softButtons.add(btnGetData);



                    btnSubsData.setSoftButtonID(2);
                    btnSubsData.setType(SoftButtonType.SBT_BOTH);
                    Image imgSubsData = new Image();
                    imgSubsData.setValue("diag.png");
                    imgSubsData.setImageType(ImageType.DYNAMIC);
                    btnSubsData.setImage(imgSubsData);
                    btnSubsData.setText("Subs");
                    softButtons.add(btnSubsData);


                    btnStopSub.setSoftButtonID(3);
                    btnStopSub.setType(SoftButtonType.SBT_BOTH);
                    Image newImg3 = new Image();
                    newImg3.setValue("stop.png");
                    newImg3.setImageType(ImageType.DYNAMIC);
                    btnStopSub.setImage(newImg3);
                    btnStopSub.setText("Stop");
                    //softButtons.add(btnStopSub);

                    HMIScreenManager.getInstance().newShow.setSoftButtons(softButtons);

                    HMIScreenManager.getInstance().mostrarTela();

                    //ClearCache threadCache = new ClearCache(INSTANCE);
                    //threadCache.run();
                }
            };

            ////------------------------------------------------------------------------------------------
            /**
             * HMI Status Listener
             */
            ServiceListeners.getInstance().listenerHMIStatus = new ServiceListeners.ListenerHMIStatus() {
                @Override
                public void onHMIFull(OnHMIStatus notification) {


                    /**
                     * Upload images
                     */
                    ArrayList<ServiceListeners.SdlImage> listaImagens = new ArrayList<>();
                    listaImagens.add(new ServiceListeners.SdlImage("send.png",R.drawable.send));
                    listaImagens.add(new ServiceListeners.SdlImage("start.png",R.drawable.start));
                    listaImagens.add(new ServiceListeners.SdlImage("stop.png",R.drawable.stop));
                    listaImagens.add(new ServiceListeners.SdlImage("diag.png",R.drawable.diag));
                    Config.instance.uploadListImages(listaImagens);


                    ServiceListeners.getInstance().listenerScreen.showWelcomeScreen();
                }

                @Override
                public void onHMIBackground(OnHMIStatus notification) {

                }

                @Override
                public void onHMINone(OnHMIStatus notification) {

                }

                @Override
                public void onHMILimited(OnHMIStatus notification) {

                }
            };



            ////------------------------------------------------------------------------------------------
            /**
             * Get Data Listener
             */
            ServiceListeners.getInstance().listenerGetData = new ServiceListeners.ListenerGetData() {
                @Override
                public void onGetVehicleDataResponse(GetVehicleDataResponse response) {
                    HMIScreenManager.getInstance().newShow.setMainField3("Data Collected!");
                    HMIScreenManager.getInstance().mostrarTela();
                    CurrentHMIState.dataCollectionActive = false;
                }
            };



            ////------------------------------------------------------------------------------------------
            /**
             * Subscribe Data Listener
             */
            ServiceListeners.getInstance().listenerSubscribeData = new ServiceListeners.ListenerSubscribeData() {
                @Override
                public void onVehicleData(OnVehicleData notification) {




                    //for (String obj : VehicleParams) {
                    //    if (notification.getParameters(obj) != null) {
                    //        HMIScreenManager.getInstance().newShow.setMainField3(obj + ": " + notification.getParameters(obj).toString() );
                    //        HMIScreenManager.getInstance().mostrarTela();
                    //    }
                    //}

                    CarData.getInstance().processVehicleSubscribe(notification);


                    if (notification.getParameters("fuelLevel") != null) {
                        HMIScreenManager.getInstance().newShow.setMainField3("FuelLevel: " + notification.getParameters("fuelLevel").toString() );
                    }

                    if (notification.getParameters("speed") != null) {
                        HMIScreenManager.getInstance().newShow.setMainField3("speed: " + notification.getParameters("speed").toString() );
                    }

                    if (notification.getParameters("prndl") != null) {
                        HMIScreenManager.getInstance().newShow.setMainField3("PRNDL: " + notification.getParameters("prndl").toString() );
                    }

                    HMIScreenManager.getInstance().mostrarTela();




                }
            };


            ////------------------------------------------------------------------------------------------
            /**
             * On Commands Listener
             */
            ServiceListeners.getInstance().listenerOnCommand = new ServiceListeners.ListenerOnCommand() {
                @Override
                public void onCommand(OnCommand notification) {

                }

                @Override
                public void onAddCommandResponse(AddCommandResponse response) {

                }

                @Override
                public void onAddSubMenuResponse(AddSubMenuResponse addSubMenuResponse) {

                }

                @Override
                public void onOnButtonPress(OnButtonPress notification) {



                    switch (notification.getCustomButtonName()){
                        case 1:

                            if (!btnGetData.getIsHighlighted()) {
                                CurrentHMIState.dataCollectionActive = true;
                                TelematicsCollector.getInstance().setGetInit();

                                HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                                HMIScreenManager.getInstance().newShow.setMainField2("Getting Car Data");
                                HMIScreenManager.getInstance().newShow.setMainField3("Data colleting...");

                                HMIScreenManager.getInstance().mostrarTela();
                            }

                            break;
                        case 2:

                            CurrentHMIState.dataCollectionActive = true;
                            TelematicsCollector.getInstance().setSubscribeVehicleData();

                            HMIScreenManager.getInstance().setNewShow();


                            HMIScreenManager.getInstance().setShowLayout(EnumDisplayLayout.TEXT_AND_SOFTBUTTONS_WITH_GRAPHIC );


                            HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                            HMIScreenManager.getInstance().newShow.setMainField2("Subscribing Car Data");
                            HMIScreenManager.getInstance().newShow.setMainField3("Data colleting...");

                            softButtons.clear();
                            btnGetData.setIsHighlighted(true);
                            softButtons.add(btnGetData);
                            softButtons.add(btnStopSub);

                            HMIScreenManager.getInstance().newShow.setSoftButtons(softButtons);
                            HMIScreenManager.getInstance().mostrarTela();

                            break;
                        case 3:


                            TelematicsCollector.getInstance().setUnsubscribeVehicleData();
                            HMIScreenManager.getInstance().setNewShow();
                            HMIScreenManager.getInstance().setShowLayout(EnumDisplayLayout.TEXT_AND_SOFTBUTTONS_WITH_GRAPHIC);


                            HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                            HMIScreenManager.getInstance().newShow.setMainField2("Subscribing Car Data");
                            HMIScreenManager.getInstance().newShow.setMainField3("Subscribe Stoped");


                            softButtons.clear();
                            btnGetData.setIsHighlighted(false);
                            softButtons.add(btnGetData);
                            softButtons.add(btnSubsData);

                            HMIScreenManager.getInstance().newShow.setSoftButtons(softButtons);

                            HMIScreenManager.getInstance().mostrarTela();

                            CurrentHMIState.dataCollectionActive = false;


                            break;

                    }


                    //HMIScreenManager.getInstance().newShow.setMainField3("Botão "+ notification.getCustomButtonName()  +" pressionado");
                    //HMIScreenManager.getInstance().mostrarTela();
                    //Log.d("TESTE","TESTE BRUNO");


                }
            };

            initSdlService();


            new Thread() {
                @Override
                public void run() {
                    do {
                        try {
                            Thread.sleep(30000);
                            //deleteCache(ctx);
                            System.gc();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (true);
                }
            }.start();


        } catch (Exception e) {
            e.getStackTrace();
        }




    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onTrimMemory(int level) {
        Log.d("CAMPUSPARTY", "onTrimMemory: Level-"+level);
        super.onTrimMemory(level);
    }





    public class ClearCache implements Runnable {


        private Context ctx;

        public ClearCache(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {

            do {
                try {
                    Thread.sleep(30000);
                    //deleteCache(ctx);
                    System.gc();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (isRunning(ctx));


        }

        public void deleteCache(Context context) {
            try {
                File dir = context.getCacheDir();
                deleteDir(dir);
            } catch (Exception e) {}
        }

        public boolean deleteDir(File dir) {
            if (dir != null && dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
                return dir.delete();
            } else if(dir!= null && dir.isFile()) {
                return dir.delete();
            } else {
                return false;
            }
        }

        public boolean isRunning(Context ctx) {
            ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);

            List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

            for (ActivityManager.RunningTaskInfo task : tasks) {
                if (ctx.getPackageName().equalsIgnoreCase(task.baseActivity.getPackageName()))
                    return true;
            }
            return false;
        }

    }


}
