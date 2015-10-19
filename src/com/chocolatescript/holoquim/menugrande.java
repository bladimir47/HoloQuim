package com.chocolatescript.holoquim;



import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import estaticas.valoresgenerales;
import json.objetosReaccionesCort;
import json.objetosReaccionesLargo;
import json.parser;

public class menugrande extends Activity{

	
	private ViewGroup Vg; 
	private List<RelativeLayout> rowItem;
	public static List<objetosReaccionesLargo>reaccionesObj;
	private ListView listViewdos;
	private int val = 0;
	private ProgressDialog progredial;
	private RelativeLayout relativeLayout;
	private JSONArray reacciones = null;
	private static String url = "http://chocolatescript.tk/holoquim/android/reaccionejemplo.php?reacc=";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comgrande_main);
		Vg = (ViewGroup)findViewById(R.id.content);
		url = "http://chocolatescript.tk/holoquim/android/reaccionejemplo.php?reacc="+valoresgenerales.valorReaccion;
		new Carga().execute();
	}
	
	public void onClic(View botton){
		//val++;
		//this.agregarmenu();
	}
	
	
	
	public void agregarmenu(){
		
		LayoutInflater inflater = LayoutInflater.from(menugrande.this);
        int id = R.layout.comentariogrande;
                 
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);
 
        //llamada de los objetos
        TextView textView = (TextView) relativeLayout.findViewById(R.id.textViewTexto);      
        TextView textodos =  (TextView)relativeLayout.findViewById(R.id.textViewTitulo);
        
        //agregacion de los textos que mostrar
        textView.setText(""+valoresgenerales.valorReaccion);
        textodos.setText("Esto es una prueba general jajajajaj");
        
        //agregamos accion y evento a cada boton
        ImageButton bt = (ImageButton)relativeLayout.findViewById(R.id.imageButton1); 
        bt.setId( val );
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
	
	
private class Carga extends AsyncTask<String, String, JSONObject>{
        
        ProgressDialog pDialog;
 
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
             
            pDialog = new ProgressDialog(menugrande.this);
            pDialog.setMessage("Cargando Reacciones");
            pDialog.setCancelable(true);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();
             
        }
 
        @Override
        protected JSONObject doInBackground(String... params) {
            
            Log.i("doInBackground" , "Entra en doInBackground");
            menugrande.reaccionesObj = new ArrayList<objetosReaccionesLargo>();
            parser jParser = new parser();
            JSONObject json = jParser.getJSONFromUrl(url);
            
            try {
				reacciones = json.getJSONArray("reacciones");
				for(int i = 0;i< reacciones.length();i++){
					
					JSONObject c = reacciones.getJSONObject(i);
					objetosReaccionesLargo oRC = new objetosReaccionesLargo(c.getString("Titulo"),c.getString("Texto"),c.getString("video") );
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
					
					LayoutInflater inflater = LayoutInflater.from(menugrande.this);
			        int id = R.layout.comentariogrande;
			                 
			        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);
			 
			        //llamada de los objetos
			        TextView textView = (TextView) relativeLayout.findViewById(R.id.textViewTexto);      
			        TextView textodos =  (TextView)relativeLayout.findViewById(R.id.textViewTitulo);
			        
			        //agregacion de los textos que mostrar
			        textView.setText(""+reaccionesObj.get(i).texto);
			        textodos.setText(""+reaccionesObj.get(i).titulo);
			        val = i;
			        
			        //agregamos accion y evento a cada boton
			        ImageButton bt = (ImageButton)relativeLayout.findViewById(R.id.imageButton1); 
			        bt.setId(i);
			        bt.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							//Toast.makeText(getApplicationContext(),"Position :" + arg0.getId()+"-" + menugrande.reaccionesObj.get(arg0.getId()).direccion , Toast.LENGTH_SHORT).show();
							startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse( menugrande.reaccionesObj.get(arg0.getId()).direccion ) ));
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
