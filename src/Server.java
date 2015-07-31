/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SyncComm;

import com.lts.ipc.IPCException;
import com.lts.ipc.fifo.FIFO;
import com.lts.ipc.fifo.FIFOImpl;
import com.lts.ipc.fifo.FIFOInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;

/**
 *
 * @author dude
 */
public class Server {

    public static void main(String[] args) throws IOException, IPCException {
        // TODO code application logic here
        FIFO f1 = new FIFO("pipe1");
        //f1.setBlockingMode(FIFO.BlockingMode.Blocking);
        //f1.getBlockingMode();
        //System.out.println("Mode: "+f1.getBlockingMode().name().);
        f1.openReader();
        byte[] buffer = new byte[48];
        int n = f1.read(buffer);
        // System.out.println("ch "+ch);
        //System.out.println(buffer.toString());
        System.out.println("Message received from Writer:");
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = "";
        }
        int[] intArr = new int[2];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if ((int) buffer[i] >= 48 && (int) buffer[i] <= 57) {
                arr[i] = arr[i] + (char) buffer[i];
                intArr[j] = Integer.parseInt(arr[i]);
                j++;
                System.out.printf("%c\n", (char) buffer[i]);
            }
        }

        //System.out.println(intArr[0]+","+intArr[1]);
        FIFO f2=new FIFO("pipe2");
        f2.create();
        f2.openWriter();
        char op = (char) buffer[n - 1];
        System.out.println("Operation: " + op);

        int result=PerformOp(intArr[0],intArr[1],op);
        System.out.println("result: "+result);
        String buffRes="";
        buffRes = buffRes+result;
        f2.write(buffRes.getBytes());
    }
    
    public static int PerformOp(int x,int y,char op)
    {
        
        if(op=='+')
            return x+y;
        if(op=='-')
            return x-y;
        
        if(op=='*')
            return x*y;
        
        if(op=='/')
            return x/y;
        
        return -1;
    }

}
