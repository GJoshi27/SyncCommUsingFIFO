package SyncComm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.lts.ipc.IPCException;
import com.lts.ipc.fifo.FIFO;
import com.lts.ipc.fifo.FIFOImpl;
import com.lts.ipc.fifo.FIFOInputStream;
import com.lts.ipc.fifo.FIFOOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.ByteBuffer;

/**
 *
 * @author dude
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, IPCException {
        // TODO code application logic here
        FIFO f1 = new FIFO("pipe1");
        f1.create();
        System.out.println("Writing String Pipe");
        f1.openWriter();
        String buff = "4,5,*";
        int n = f1.write(buff.getBytes());
        //String num="4";
        //f1.write(num.getBytes());
        System.out.println("written bytes are " + n);
        System.out.println("Reading results from Writer");

        FIFO f2 = new FIFO("pipe2");
        f2.openReader();
        byte[] buffer = new byte[48];
        n = f2.read(buffer);
        for (int i = 0; i < n; i++) {
            System.out.printf("%c", (char) buffer[i]);
        }

        System.out.println("");

    }

}
