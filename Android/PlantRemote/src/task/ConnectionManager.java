/**
 * Questa clase contiene i metodi per stabilire le interazioni tra il dispositivo e la rete
 * @author giacomobellazzi
 */
package task;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Patterns;

public class ConnectionManager {
	
	private Context context;
	private static int TIME_HOST = 200;
	
	
	public ConnectionManager(Context context) {
		super();
		this.context = context;
	}
	/**
	 * Questo metodo permette di stabilire se il dispositivo è connesso ad Internet
	 * @return
	 */
	public boolean isConnectedToInternet(){
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) 
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) 
                for (int i = 0; i < info.length; i++) 
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
	}
	/**
	 * Questo metodo permette di stabilire se una {@link URL} è valida e quindi 
	 * è stata scritta in modo corretto
	 * @param url
	 * @return
	 */
	public boolean isCorrect(String url){
		return Patterns.WEB_URL.matcher(url).matches();
	}
	/**
	 * This method return true if the Host is alive
	 * @param IP
	 * @return
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public boolean isHostAlive(String host,int port) throws UnknownHostException, IOException{
		return InetAddress.getByName(host).isReachable(TIME_HOST);
	}
	/**
	 * This method give the network's state of the device
	 * @param url
	 * @param host
	 * @param port
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public int statusConnection(String url,String host,int port) throws UnknownHostException, IOException{
		if(isCorrect(url)&&isHostAlive(host, port)){
			return StatusNetwork.RECHARGEABLE;
		}
		if(!isCorrect(url)){
			return StatusNetwork.MALFORMED_URL;
		}
		if(!isHostAlive(host, port)){
			return StatusNetwork.HOST_NOT_ALIVE;
		}
		if(!isConnectedToInternet()){
			return StatusNetwork.NOT_CONNECTED_INTERNET;
		}else{
			return StatusNetwork.CONNECTED_INTERNET;
		}
	}
}
