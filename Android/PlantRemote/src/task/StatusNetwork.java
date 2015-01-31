/**
 * Questa classe ha la reponsabilità di contenere le informazioni riguardo alla stato delle
 * connettività di Android
 */
package task;

import java.util.HashMap;

import com.ismanettone.iplant.R;




public class StatusNetwork {
	
	public static int RECHARGEABLE = 0;
	public static int MALFORMED_URL = 1;
	public static int HOST_NOT_ALIVE = 2;
	public static int NOT_CONNECTED_INTERNET = 3;
	public static int CONNECTED_INTERNET  = 4;
	public static StatusNetwork network = new StatusNetwork();
	private HashMap<Integer, Integer> status = new HashMap<>();
	
	private StatusNetwork(){
		createMapStatus();
	}
	
	private void createMapStatus(){
		status.put(MALFORMED_URL, R.string.dialog_url_text);
		status.put(NOT_CONNECTED_INTERNET, R.string.no_internet_detail);
		status.put(HOST_NOT_ALIVE, R.string.host_not_alive);
	}

	public HashMap<Integer, Integer> getStatus() {
		return status;
	}
}
