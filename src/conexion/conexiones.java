package conexion;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class conexiones {

	
	public String envioservidor(String entrada){
		String texto = "";
		try{
			HttpClient hhtp = new DefaultHttpClient();
			HttpPost hhtpos = new HttpPost(entrada);
			HttpResponse httreq = hhtp.execute(hhtpos);
			HttpEntity ent = httreq.getEntity();
			texto = EntityUtils.toString(ent);
		}catch(Exception e){
			Log.e("xxxxx", ""+e);
		}
		
		return texto;
	}
	
	
}
