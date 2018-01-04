package com.ford.sa.campuspartytest;

import android.util.Log;

import com.ford.sa.interfacesdl.Config;
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

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by bgarci67 on 1/4/18.
 */

public class SdlApplication extends com.ford.sa.interfacesdl.SdlApplication {

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
                    HMIScreenManager.getInstance().setShowLayout(EnumDisplayLayout.NON_MEDIA);

                    HMIScreenManager.getInstance().setNewShow();

                    HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                    HMIScreenManager.getInstance().newShow.setMainField2("New Teste Bruno");
                    HMIScreenManager.getInstance().newShow.setMainField3("Teste Data Collection");


                    Vector<SoftButton> softButtons = new Vector<SoftButton>();

			        /* Button */
                    SoftButton newSB1 = new SoftButton();
                    newSB1.setSoftButtonID(1);
                    newSB1.setType(SoftButtonType.SBT_BOTH);

                    Image newImg1 = new Image();
                    newImg1.setValue("start.png");
                    newImg1.setImageType(ImageType.DYNAMIC);
                    newSB1.setImage(newImg1);
                    newSB1.setText("GetData");
                    softButtons.add(newSB1);

                    /* Button */
                    SoftButton newSB2 = new SoftButton();
                    newSB2.setSoftButtonID(2);
                    newSB2.setType(SoftButtonType.SBT_BOTH);

                    Image newImg2 = new Image();
                    newImg2.setValue("stop.png");
                    newImg2.setImageType(ImageType.DYNAMIC);
                    newSB2.setImage(newImg2);
                    newSB2.setText("Subscribe Data");
                    softButtons.add(newSB2);

                    /* Button */
                    /*SoftButton newSB3 = new SoftButton();
                    newSB3.setSoftButtonID(3);
                    newSB3.setType(SoftButtonType.SBT_BOTH);

                    Image newImg3 = new Image();
                    newImg3.setValue("diag.png");
                    newImg3.setImageType(ImageType.DYNAMIC);
                    newSB3.setImage(newImg3);
                    newSB3.setText("btn3");
                    softButtons.add(newSB3);


                    SoftButton newSB4 = new SoftButton();
                    newSB4.setSoftButtonID(4);
                    newSB4.setType(SoftButtonType.SBT_BOTH);

                    Image newImg4 = new Image();
                    newImg4.setValue("send.png");
                    newImg4.setImageType(ImageType.DYNAMIC);
                    newSB4.setImage(newImg4);
                    newSB4.setText("btn4");
                    softButtons.add(newSB4);


                    SoftButton newSB5 = new SoftButton();
                    newSB5.setSoftButtonID(6);
                    newSB5.setType(SoftButtonType.SBT_BOTH);

                    Image newImg5 = new Image();
                    newImg5.setValue("send.png");
                    newImg5.setImageType(ImageType.DYNAMIC);
                    newSB5.setImage(newImg4);
                    newSB5.setText("btn5");
                    softButtons.add(newSB5);
                    */
                    HMIScreenManager.getInstance().newShow.setSoftButtons(softButtons);

                    HMIScreenManager.getInstance().mostrarTela();
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

                }
            };



            ////------------------------------------------------------------------------------------------
            /**
             * Subscribe Data Listener
             */
            ServiceListeners.getInstance().listenerSubscribeData = new ServiceListeners.ListenerSubscribeData() {
                @Override
                public void onVehicleData(OnVehicleData notification) {
                    HMIScreenManager.getInstance().newShow.setMainField3("Data Collected!");
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

                    Vector<SoftButton> softButtons = new Vector<SoftButton>();

                    switch (notification.getCustomButtonName()){
                        case 1:
                            //HMIScreenManager.getInstance().newShow.setMainField3("Botão 1 pressionado");
                            TelematicsCollector.getInstance().setGetInit();

                            HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                            HMIScreenManager.getInstance().newShow.setMainField2("Getting Car Data");
                            HMIScreenManager.getInstance().newShow.setMainField3("Data colleting...");

                            HMIScreenManager.getInstance().mostrarTela();

                            break;
                        case 2:
                            //HMIScreenManager.getInstance().newShow.setMainField3("Botão 2 pressionado");


                            TelematicsCollector.getInstance().setSubscribeVehicleData();

                            HMIScreenManager.getInstance().setNewShow();
                            HMIScreenManager.getInstance().setShowLayout(EnumDisplayLayout.NON_MEDIA);


                            HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                            HMIScreenManager.getInstance().newShow.setMainField2("Subscribing Car Data");
                            HMIScreenManager.getInstance().newShow.setMainField3("Data colleting...");

                            softButtons = new Vector<SoftButton>();
                            /* Button */
                            SoftButton btnStop = new SoftButton();
                            btnStop.setSoftButtonID(5);
                            btnStop.setType(SoftButtonType.SBT_BOTH);

                            Image imgStop = new Image();
                            imgStop.setValue("stop.png");
                            imgStop.setImageType(ImageType.DYNAMIC);
                            btnStop.setImage(imgStop);
                            btnStop.setText("Stop");
                            softButtons.add(btnStop);

                            HMIScreenManager.getInstance().newShow.setSoftButtons(softButtons);
                            HMIScreenManager.getInstance().mostrarTela();



                            break;
                        case 3:
                            HMIScreenManager.getInstance().newShow.setMainField3("Botão 3 pressionado");
                            break;
                        case 4:
                            HMIScreenManager.getInstance().newShow.setMainField3("Botão 4 pressionado");
                            break;
                        case 5:

                            HMIScreenManager.getInstance().setNewShow();
                            HMIScreenManager.getInstance().setShowLayout(EnumDisplayLayout.NON_MEDIA);


                            HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                            HMIScreenManager.getInstance().newShow.setMainField2("Subscribing Car Data");
                            HMIScreenManager.getInstance().newShow.setMainField3("Subscribe Stoped");



			                /* Button */
                            SoftButton newSB1 = new SoftButton();
                            newSB1.setSoftButtonID(1);
                            newSB1.setType(SoftButtonType.SBT_BOTH);

                            Image newImg1 = new Image();
                            newImg1.setValue("start.png");
                            newImg1.setImageType(ImageType.DYNAMIC);
                            newSB1.setImage(newImg1);
                            newSB1.setText("GetData");
                            softButtons.add(newSB1);

                            /* Button */
                            SoftButton newSB2 = new SoftButton();
                            newSB2.setSoftButtonID(2);
                            newSB2.setType(SoftButtonType.SBT_BOTH);

                            Image newImg2 = new Image();
                            newImg2.setValue("stop.png");
                            newImg2.setImageType(ImageType.DYNAMIC);
                            newSB2.setImage(newImg2);
                            newSB2.setText("Subscribe Data");
                            softButtons.add(newSB2);

                            /* Button */
                            SoftButton newSB3 = new SoftButton();
                            newSB3.setSoftButtonID(3);
                            newSB3.setType(SoftButtonType.SBT_BOTH);

                            Image newImg3 = new Image();
                            newImg3.setValue("diag.png");
                            newImg3.setImageType(ImageType.DYNAMIC);
                            newSB3.setImage(newImg3);
                            newSB3.setText("btn3");
                            softButtons.add(newSB3);

                            /* Button */
                            SoftButton newSB4 = new SoftButton();
                            newSB4.setSoftButtonID(4);
                            newSB4.setType(SoftButtonType.SBT_BOTH);

                            Image newImg4 = new Image();
                            newImg4.setValue("send.png");
                            newImg4.setImageType(ImageType.DYNAMIC);
                            newSB4.setImage(newImg4);
                            newSB4.setText("btn4");
                            softButtons.add(newSB4);

                            HMIScreenManager.getInstance().newShow.setSoftButtons(softButtons);

                            HMIScreenManager.getInstance().mostrarTela();

                            break;
                    }


                    //HMIScreenManager.getInstance().newShow.setMainField3("Botão "+ notification.getCustomButtonName()  +" pressionado");
                    HMIScreenManager.getInstance().mostrarTela();
                    //Log.d("TESTE","TESTE BRUNO");


                }
            };

            initSdlService();



        } catch (Exception e) {
            e.getStackTrace();
        }

    }
}
