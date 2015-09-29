package com.chocolatescript.holoquim;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.ProgressDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import json.objetosReaccionesCort;
import json.parser;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	private ViewGroup Vg; 
	private List<RelativeLayout> rowItem;
	public static List<objetosReaccionesCort>reaccionesObj;
	private ListView listViewdos;
	private int val = 0;
	private ProgressDialog progredial;
	private RelativeLayout relativeLayout;
	private JSONArray reacciones = null;
	private static String url = "http://chocolatescript.tk/holoquim/android/reacciones.php";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Vg = (ViewGroup)findViewById(R.id.content);
		new Carga().execute();
	}
	
	public void onClic(View botton){
		val++;	
	}
	
	
	
	
	private class Carga extends AsyncTask<String, String, JSONObject>{
        
        ProgressDialog pDialog;
 
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
             
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Cargando Reacciones");
            pDialog.setCancelable(true);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();
             
        }
 
        @Override
        protected JSONObject doInBackground(String... params) {
            
            Log.i("doInBackground" , "Entra en doInBackground");
            MainActivity.reaccionesObj = new ArrayList<objetosReaccionesCort>();
            parser jParser = new parser();
            JSONObject json = jParser.getJSONFromUrl(url);
            
            try {
				reacciones = json.getJSONArray("reacciones");
				for(int i = 0;i< reacciones.length();i++){
					
					JSONObject c = reacciones.getJSONObject(i);
					Bitmap bp = descargarImagen("http://chocolatescript.tk/holoquim/imagenesnoti/"+c.getString("Imagen") );
					objetosReaccionesCort oRC = new objetosReaccionesCort(Integer.parseInt(c.getString("IDreaccion")),c.getString("Titulo"),c.getString("Texto"),bp );
					reaccionesObj.add(oRC);
							        
					
				}
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}            
            
            
            return json;
        }
         
        @Override
        protected void onPostExecute(JSONObject json) {
        	
        	try {
				
				for(int i = 0;i< reaccionesObj.size();i++){
					
					JSONObject c = reacciones.getJSONObject(i);
					
					LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
			        int id = R.layout.comentariopequenio;
			                 
			        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);
			 
			        //llamada de los objetos
			        TextView textView = (TextView) relativeLayout.findViewById(R.id.textViewTexto);      
			        TextView textodos =  (TextView)relativeLayout.findViewById(R.id.textViewTitulo);
			        ImageView imagenView = (ImageView)relativeLayout.findViewById(R.id.imageViewIcono);
			        
			        //agregacion de los textos que mostrar
			        textView.setText(""+reaccionesObj.get(i).texto);
			        textodos.setText(""+reaccionesObj.get(i).titulo);
			        imagenView.setImageBitmap(reaccionesObj.get(i).imagen);
			        
			        //agregamos accion y evento a cada boton
			        ImageButton bt = (ImageButton)relativeLayout.findViewById(R.id.boton11); 
			        bt.setId( reaccionesObj.get(i).id );
			        bt.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							Toast.makeText(getApplicationContext(),"Position :" + arg0.getId() , Toast.LENGTH_SHORT).show();
							
						}
					});
			        
			        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			        
			        params.topMargin = 15;
			        params.leftMargin = 10;
			        relativeLayout.setPadding(5, 3, 5, 3);  
			        relativeLayout.setLayoutParams(params);
			        Vg.addView(relativeLayout);
					
				}
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
             
            
            pDialog.dismiss();
        }
         
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
