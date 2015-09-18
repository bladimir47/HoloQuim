package com.chocolatescript.holoquim;


import java.util.List;


import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ViewGroup Vg; 
	private List<RelativeLayout> rowItemsdos;
	private ListView listViewdos;
	private int val = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Vg = (ViewGroup)findViewById(R.id.content);
	}
	
	public void onClic(View botton){
		val++;
		this.addComentario();
				
	}
	
	
	
	private void addComentario()
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.comentariopequenio;
                 
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);
 
        
        //llamada de los objetos
        TextView textView = (TextView) relativeLayout.findViewById(R.id.textViewTexto);      
        TextView textodos =  (TextView)relativeLayout.findViewById(R.id.textViewTitulo);
        
        
        //agregacion de los textos que mostrar
        textView.setText("Es todo proceso termodinámico en el cual una o más sustancias (llamadas reactantes), por efecto de un factor energético, se transforman, cambiando su estructura molecular y sus enlaces, en otras sustancias llamadas productos. Los reactantes pueden ser elementos o compuestos.");
        textodos.setText("Reacción química");
        
        //agregamos accion y evento a cada boton
        ImageButton bt = (ImageButton)relativeLayout.findViewById(R.id.boton11); 
        bt.setId(val);
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
	
	
	

	
}
