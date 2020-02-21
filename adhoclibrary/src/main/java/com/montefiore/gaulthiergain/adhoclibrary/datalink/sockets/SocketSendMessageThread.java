package com.montefiore.gaulthiergain.adhoclibrary.datalink.sockets;

import android.os.Handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.montefiore.gaulthiergain.adhoclibrary.datalink.util.MessageAdHoc;
import com.montefiore.gaulthiergain.adhoclibrary.datalink.util.Utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SocketSendMessageThread extends Thread {

    private final DataOutputStream oos;
    private final MessageAdHoc msg;
    private final boolean json;
    private final ObjectMapper mapper;

    SocketSendMessageThread(MessageAdHoc msg,DataOutputStream oos,boolean json,ObjectMapper mapper) {
        this.msg=msg;
        this.oos=oos;
        this.json=json;
        this.mapper=mapper;
    }

    @Override
    public void run() {
        if (json) {
            PrintWriter pw = new PrintWriter(oos);
            try {
                pw.println(mapper.writeValueAsString(msg));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            pw.flush();
        } else {
            try{
                byte[] byteArray = Utils.serialize(msg);
                if (byteArray != null) {
                    oos.writeInt(byteArray.length);
                    oos.write(byteArray);
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
