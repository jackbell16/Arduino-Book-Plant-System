/**
 * Questa classe permette di inviare una richiesta HTTP ad un server e ricevere la risposta
 * @author giacomobellazzi
 */
package task;

import java.io.IOException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ismanettone.iplant.R;

public class ConnectionTask extends AsyncTask<String, String, String> {
	
	private ProgressDialog pDialog;
	private Context context;
	private String text;
	private String result;
	private ConnectionManager manager;
	private int status;
	
	public ConnectionTask(Context context,int idText) {
		super();
		this.context = context;
		this.text = context.getString(idText);
		manager = new ConnectionManager(context);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(context);
		pDialog.setMessage(context.getString(R.string.working));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
	
	@Override
	protected String doInBackground(String... params) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String host =params[0];
			String command = params[1];
			String url = "http://"+host+"/?"+command;
			try {
				status = manager.statusConnection(url, host, 80);
				if(status == 0){
					result = ReceiverHTTP.getResponse(url);
					result = this.text;
				}else{
					
					result = context.getString(StatusNetwork.network.getStatus().get(status));
				}
			}catch (IOException e1) {
			}
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		pDialog.dismiss();
		Toast.makeText(context, this.result, Toast.LENGTH_LONG).show();
		super.onPostExecute(result);
	}
}
