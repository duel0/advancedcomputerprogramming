package printer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IPrinter;

public class PrinterThread extends Thread{
    private Socket s;
    private IPrinter p;

    public PrinterThread(Socket s, IPrinter p){
        this.s=s;
        this.p=p;
    }

    public void run(){
        try{
            DataInputStream istream = new DataInputStream(s.getInputStream());
            DataOutputStream ostream = new DataOutputStream(s.getOutputStream());
            
            String docName = istream.readUTF();

            boolean condition = p.print(docName);
            if(condition){
                ostream.writeUTF("true");
            } else {
                ostream.writeUTF("false");
            }

            ostream.flush();
            istream.close();
            ostream.close();
            s.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
