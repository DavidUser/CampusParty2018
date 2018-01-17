package com.ford.sa.campuspartytest;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ford.sa.interfacesdl.Config;
import com.ford.sa.interfacesdl.LockScreenActivity;
import com.ford.sa.interfacesdl.hmi.CurrentHMIState;
import com.ford.sa.interfacesdl.hmi.EnumDisplayLayout;
import com.ford.sa.interfacesdl.hmi.HMIScreenManager;
import com.ford.sa.interfacesdl.listeners.ServiceListeners;
import com.ford.sa.interfacesdl.telematics.TelematicsCollector;
import com.smartdevicelink.exception.SdlException;
import com.smartdevicelink.proxy.SdlProxyALM;
import com.smartdevicelink.proxy.rpc.AddCommandResponse;
import com.smartdevicelink.proxy.rpc.AddSubMenuResponse;
import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.Image;
import com.smartdevicelink.proxy.rpc.OnButtonPress;
import com.smartdevicelink.proxy.rpc.OnCommand;
import com.smartdevicelink.proxy.rpc.OnHMIStatus;
import com.smartdevicelink.proxy.rpc.OnLockScreenStatus;
import com.smartdevicelink.proxy.rpc.OnVehicleData;
import com.smartdevicelink.proxy.rpc.SoftButton;
import com.smartdevicelink.proxy.rpc.enums.ImageType;
import com.smartdevicelink.proxy.rpc.enums.LockScreenStatus;
import com.smartdevicelink.proxy.rpc.enums.SoftButtonType;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by bgarci67 on 1/4/18.
 */

public class SdlApplication extends com.ford.sa.interfacesdl.SdlApplication {


    int cont = 0;
    Vector<SoftButton> softButtons = new Vector<SoftButton>();
    SoftButton btnGetData = new SoftButton();
    SoftButton btnSubsData = new SoftButton();
    SoftButton btnStopSub = new SoftButton();

    //static TextView txtConteudo;

    private SdlApplication INSTANCE;

    //LockScreenAct lockScreen;

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
                public void onGetVehicleDataResponse(Context ctx, GetVehicleDataResponse response) {
                    HMIScreenManager.getInstance().newShow.setMainField3("Data Collected!");
                    HMIScreenManager.getInstance().mostrarTela();
                    //CurrentHMIState.dataCollectionActive = false;

                    String texto = " - Vin: " + response.getVin();

                    cont = cont+1;

                    try {
                        //txtConteudo.setText(cont + texto);
                       // LockScreenActivity.recreateAct();
                    } catch(Exception e) {
                        e.getStackTrace();
                    }

                    CarData obj = new CarData();
                    obj.processVehicleData(response);

                    Shared.getInstance().ListCarDataSort("asc");
                    Shared.getInstance().setListCarData(obj);
                    Shared.getInstance().ListCarDataSort("desc");


                    CarData.getInstance().processVehicleData(response);

                    //sendLockScreenMsg(cont + texto);



                    //lockScreen.setTxtView(cont + " - Data Collected!!\n\n\n" + texto );

                }
            };



            ////------------------------------------------------------------------------------------------
            /**
             * Subscribe Data Listener
             */
            ServiceListeners.getInstance().listenerSubscribeData = new ServiceListeners.ListenerSubscribeData() {
                @Override
                public void onVehicleData(OnVehicleData notification) {

                    for (HashMap.Entry<String, Object> obj : ((Hashtable<String, Object>)notification.getStore().get("notification") ).entrySet() ) {
                        if (obj.getKey().equals("parameters")) {
                            for (HashMap.Entry<String, Object> item : ((Hashtable<String, Object>)obj.getValue()).entrySet() ) {
                                HMIScreenManager.getInstance().newShow.setMainField3(item.getKey() + " : " + item.getValue() );
                            }
                        }
                    }

                    CarData.getInstance().processVehicleSubscribe(notification);
                    HMIScreenManager.getInstance().mostrarTela();

                    CarData obj = new CarData();
                    obj.newCarDataSubscribe();

                    Shared.getInstance().ListCarDataSort("asc");
                    Shared.getInstance().setListCarData(obj);
                    Shared.getInstance().ListCarDataSort("desc");


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

                                //txtConteudo.setText("Get Data init...");

                            }

                            break;
                        case 2:

                            CurrentHMIState.dataCollectionActive = true;
                            TelematicsCollector.getInstance().setGetInit();

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





            ServiceListeners.getInstance().listenerLockScreenEvents = new ServiceListeners.ListenerLockScreenEvents() {
                @Override
                public void onDisposeSyncProxy() {


                    LockScreenActivity.updateLockScreenStatus(LockScreenStatus.OFF);


                }

                @Override
                public void onLockScreenNotification(OnLockScreenStatus notification) {
                    LockScreenActivity.updateLockScreenStatus(notification.getShowLockScreen());

                    sendLockScreenMsg("Bruno Garcia Testando o Broadcast Receiver - TESTE");

                    /*if(notification.getHMILevel().equals("HMI_FULL")  && notification.getShowLockScreen() == LockScreenStatus.REQUIRED) {
                        Intent showLockScreenIntent = new Intent(INSTANCE, LockScreenAct.class);
                        showLockScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //if(lockScreenManager.getLockScreenIcon() != null){
                        //    showLockScreenIntent.putExtra(LockScreenActivity.LOCKSCREEN_BITMAP_EXTRA, lockScreenManager.getLockScreenIcon());
                        //}
                        startActivity(showLockScreenIntent);
                    }else if(notification.getShowLockScreen() == LockScreenStatus.OFF){
                        sendBroadcast(new Intent("CLOSE_LOCK_SCREEN"));
                    }*/

                }

                @Override
                public void onCreate(Activity activity, Bundle bundle) {

                    try {
                        //activity.setContentView(R.layout.activity_lock_screen_new);
                        //txtConteudo =  (TextView) activity.findViewById(R.id.txtConteudo);


                        //txtConteudo.setText("Bruno testando....");

                    } catch (Exception e) {
                        e.getStackTrace();
                    }



                }

                @Override
                public void onBroadcastReceive(Context context, Intent intent) {

                }
            };



            //LockScreenAct.registerActivityLifecycle(INSTANCE);

            //lockScreen = new LockScreenAct();


            Config.ConnectionType = ServiceListeners.ProxyConnection.SYNC;

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

    private void sendLockScreenMsg(String text) {
        Intent intent = new Intent("MSG");
        intent.putExtra("extra", text );
        sendBroadcast(intent);
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
