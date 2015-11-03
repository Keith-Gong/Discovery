package keith.com.discovery.ipc.socket;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by Keith on 10/27/15.
 */
public class TCPServerService extends Service {
    private boolean mIsServiceDestroyed = false;
    private String[] mDefineMsg = new String[] {
            "Hi",
            "Hello",
            "Nice to meet you"

    };

    private class TcpServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                System.out.println("failed : 8688");
                e.printStackTrace();
            }
            while (mIsServiceDestroyed) {
                try {
                    final Socket client = serverSocket.accept();
                    System.out.println("accept");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("Welcome");
        while(!mIsServiceDestroyed) {
            String str = in.readLine();
            System.out.println("msg from client:" + str);
            if (str == null)
                break;
            int i = new Random().nextInt(mDefineMsg.length);
            String msg = mDefineMsg[i];
            System.out.println("send: " + msg);
            System.out.println("client quit");
            out.close();
            in.close();
            client.close();
        }
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
