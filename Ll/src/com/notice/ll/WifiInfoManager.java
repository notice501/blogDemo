package com.notice.ll;



import java.util.ArrayList;  
import android.content.Context;  
import android.net.wifi.WifiManager;  
public class WifiInfoManager {  
      
    WifiManager wm;  
      
    public WifiInfoManager(){}  
      
    public ArrayList getWifiInfo(Context context){  
        ArrayList<WifiInfo> wifi = new ArrayList();  
        wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
        WifiInfo info = new WifiInfo();  
        info.mac = wm.getConnectionInfo().getBSSID();  
        wifi.add(info);  
        return wifi;  
    }  
}  