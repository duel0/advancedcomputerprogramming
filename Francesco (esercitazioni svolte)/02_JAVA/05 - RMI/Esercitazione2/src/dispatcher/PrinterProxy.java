package dispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import service.IPrinter;

public class PrinterProxy implements IPrinter{
    private String host;
    private int port;

    public PrinterProxy(String host, int port){
        this.host=host;
        this.port=port;
    }

    @Override
    public boolean print(String docName) {
        try {
            Socket s = new Socket(host,port);
            DataInputStream istream = new DataInputStream(s.getInputStream());
            DataOutputStream ostream = new DataOutputStream(s.getOutputStream());

            ostream.writeUTF(docName);
            ostream.flush();

            String esito = istream.readUTF();
            istream.close();
            ostream.close();
            s.close();
            if(esito.equals("true")){
                return true;
            } else {
                return false;
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    
}
