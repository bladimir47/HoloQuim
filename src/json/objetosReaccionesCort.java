package json;

import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class objetosReaccionesCort {
	
	private static String urlimga = "http://chocolatescript.tk/holoquim/imagenesnoti/";
	public String titulo= "";
	public String texto = "";
	public int id = 0;
	public Bitmap imagen = null;
	
	
	public objetosReaccionesCort(int id,String titulo,String texto,Bitmap imagen){
		this.id = id;
		this.titulo = titulo;
		this.texto = texto;
		this.imagen = imagen;
	}
	
	
	private Bitmap descargarImagen (String imageHttpAddress){
        URL imageUrl = null;
        Bitmap imagen = null;
        try{
            imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            imagen = BitmapFactory.decodeStream(conn.getInputStream());
        }catch(Exception ex){
            ex.printStackTrace();
        }
         
        return imagen;
	}
	
	

}
