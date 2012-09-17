/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.data;

import javax.microedition.rms.*;
import java.util.Vector;

/**
 *
 * @author agung
 */
public class GameDataHelper {
    
//    public static final String MINI_GAME_PANJAT_PINANG = "PanjatPinang";
//    public static final String MINI_GAME_MANCING = "Mancing";
    public static final int MINI_GAME_PANJAT_PINANG = 0;
    public static final int MINI_GAME_MANCING = 1;
    public static final int BALAP_KARUNG_RUMAH = 2;
    public static final int BALAP_KARUNG_RUMAH_1 = 7;
    public static final int BALAP_KARUNG_RUMAH_2 = 8;
    public static final int BALAP_KARUNG_KUBURAN = 3;
    public static final int BALAP_KARUNG_KUBURAN_1 = 9;
    public static final int BALAP_KARUNG_KUBURAN_2 = 10;
    public static final int BALAP_KARUNG_LOMBA = 5;
    public static final int BALAP_KARUNG_LOMBA_1 = 11;
    public static final int BALAP_KARUNG_LOMBA_2 = 12;
    public static final int ENDLESS = 20;
    public static final int TOTAL_COIN = 6;
    public static final int PILIHAN_PLAYER = 4;
    
    public static final String RECORD_STORE_NAME = "flipboxdolanan";
    private static final int MAX_RECORD = 30;
    
    public static boolean writeHighScore(int key, long value){
        if(key >= MAX_RECORD){
            System.out.println("%%%%%%%%%%%%%%%%%%%%% CANT KEY >= MAX_RECORD");
            return false;
        }
        try{
            Vector existingData = loadData(RECORD_STORE_NAME);
            if(existingData.size() <= 0){
                System.out.println("Existing Data belom ada ^&^*%%&^$^#%$#^$%#%");
                for(int i = 0;i<MAX_RECORD;++i)
                    existingData.addElement((""+0).getBytes());
            }else if(existingData.size() < MAX_RECORD){
                Vector temp = new Vector(MAX_RECORD);
                for(int i = 0;i<MAX_RECORD;++i)
                    temp.addElement((""+0).getBytes());
                for(int i = 0;i < existingData.size(); ++i){
                    System.out.println("i : "+i+" dari "+existingData.size());
                    temp.setElementAt(existingData.elementAt(i), i);
                }
                existingData = temp;
            }
            System.out.println("value: "+value+" di key:"+key);
            existingData.setElementAt((""+value).getBytes(), key);
            saveData(RECORD_STORE_NAME, existingData);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public static long getHighScore(int key){
        if(key >= MAX_RECORD){
            System.out.println("%%%%%%%%%%%%%%%%%%%%% CANT KEY >= MAX_RECORD");
            return -1;
        }
        try{
            Vector existingData = loadData(RECORD_STORE_NAME);
            if((existingData.size()-1) < key)
                return -1;
            String data = new String((byte[])existingData.elementAt(key));
            
            return Long.parseLong(data);
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    private static void saveData(String recordStoreName, Vector data) throws RecordStoreException {
        try {
            RecordStore.deleteRecordStore(recordStoreName);
        } catch (RecordStoreNotFoundException rsnfe) {
            // ignore this exception only
        }
        RecordStore rs = RecordStore.openRecordStore(recordStoreName, true);
        try {
            int size = data.size();
            for (int i = 0; i < size; i++) {
                byte[] record = (byte[]) data.elementAt(i);
                rs.addRecord(record, 0, record.length);
            }
        } finally {
            rs.closeRecordStore();
        }
    }
 
    private static Vector loadData(String recordStoreName) throws RecordStoreException {
        Vector data = new Vector();
        RecordStore rs = RecordStore.openRecordStore(recordStoreName, true);
        try {
            int size = rs.getNumRecords();
            for (int i = 1; i <= size; i++) {
                data.addElement(rs.getRecord(i));
            }
        } finally {
            rs.closeRecordStore();
        }
        return data;
    }
    
//    public static long getHighScore(String keyName){
//        try{
//           String value = GameDataHelper.getValue(keyName);
//           if(value!=null)
//               return Long.parseLong(value);
//           else
//               return -1;
//        }catch(Exception e){
//            e.printStackTrace();
//            return -1;
//        }
//    }
//    
//    public static boolean writeHighScore(String keyName, long score){
//        try{
//            String value = GameDataHelper.getValue(keyName);
//            if(value == null){
//                
//            }else{
//                
//            }
//                
//        }catch(Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//    
//    private static String getValue(String keyname) throws Exception{
//        InputStreamReader input = GameMidlet.gameCanvas.getClass().getResourceAsStream("/highscore/highscore.txt");
//        char temp = (char)-1;
//        StringBuffer buffer = new StringBuffer();
//
//        boolean keyTurn = true;
//        boolean valueTurn  = false;
//        boolean keyFound = false;
//        String result = "";
//
//        while((temp = input.read())!=-1){
//            if((temp >= 'a' && temp <= 'z') || 
//                    (temp >= 'A' && temp <= 'Z') ||
//                    (temp >= '0' && temp <= '9')){
//                buffer.append(temp);
//            }else{
//                String res = buffer.toString();
//                if(keyTurn){
//                    if(res.equalsIgnoreCase(keyName)){
//                        keyFound = true;
//                    }
//                    keyTurn = false;
//                    valueTurn = true;
//                }else if(valueTurn ){
//                    keyTurn = true;
//                    valueTurn = false;
//                    if(keyFound){
//                        result = buffer.toString();
//                        break;
//                    }
//                }
//                buffer = new StringBuffer();
//            }
//        }
//        input.close();
//        if(keyFound){
//            return result;
//        }else if(temp == -1){
//            if(buffer!=null && buffer.length() > 0)
//                return buffer.toString();
//        }
//        return null;
//    }
//    
//    /**
//     * Doesn't provide unique key mechanism
//     * @param keyname
//     * @param value 
//     */
//    private void addKeyValue(String keyname, String value){
//        
//    }
//    
//    /**
//     * Doesn't provide 
//     * @param keyname
//     * @param value 
//     */
//    private void updateKeyValue(String keyname, String value){
//        
//    }
    
    
    
//    public static long getHighScore(String keyName){
//        try{
//            if(rs == null){
//                rs = RecordStore.openRecordStore(keyName, true);
//            }
//            if(rs.getNextRecordID() == 1){
//                rs.closeRecordStore();
//                return -1;
//            }
//            
//            int length = rs.getRecordSize(1);
//            
//            byte[] data = new byte[length];
//            rs.getRecord(1, data, 0);
//            rs.closeRecordStore();
//            if(data!=null){
//                long result = Long.parseLong(new String(data));
//                System.out.println(keyName+" : "+ result+ " ######################");
//                return result;
//            }
//            else
//                return -1;
//          
//        }catch(Exception e){
//            e.printStackTrace();
//            return -1;
//        }
//    }
//    
//    public static boolean writeHighScore(String keyName, String name, long score){
//        try{
//            byte[] data = (""+score).getBytes();
//            if(rs == null){
//                rs = RecordStore.openRecordStore(keyName, true);
//            }
//            System.out.println(keyName+" : "+ score + " ######################");
//            if(rs.getNextRecordID() > 1){
//                rs.setRecord(1, data, 0, data.length);
//            }else{
//                rs.addRecord(data, 0, data.length);
//            }
//            rs.closeRecordStore();
//            rs = null;
//            return true;
//        }catch(Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
}
