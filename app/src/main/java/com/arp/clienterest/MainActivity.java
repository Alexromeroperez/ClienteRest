package com.arp.clienterest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arp.clienterest.interfaces.ApiActividad;
import com.arp.clienterest.pojo.Actividad;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Actividad> actividades;
    private ListView lv;
    private Adaptador ad;
    private Retrofit retrofit;
    private ApiActividad api;
    private Call<Actividad> call;
    private final int AÑADIR=1,MODIFICA=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listView);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        actividades=new ArrayList<>();
        ini();

    }

    /*genera el adaptador*/
    private void genera(){
        ad=new Adaptador(this,R.layout.lista_detalle,actividades);
        lv.setAdapter(ad);
        registerForContextMenu(lv);
        ad.notifyDataSetChanged();
    }

    private void ini(){
        api = retrofit.create(ApiActividad.class);
        Call<List<Actividad>> call = api.getActividades();
        call.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Response<List<Actividad>> response, Retrofit retrofit) {
                for(Actividad a: response.body()) {
                    actividades.add(a);
                }
                genera();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }
    /********************Los menus de la lista******************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.añadir){
            Intent i=new Intent(this,Gestiona.class);
            startActivityForResult(i,AÑADIR);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Actividad a=actividades.get(info.position);
        switch (item.getItemId()) {
            case R.id.modificar:
                Intent i=new Intent(this,Gestiona.class);
                i.putExtra("actividad",a);
                startActivityForResult(i, MODIFICA);
                return true;
            case R.id.eliminar:
                call=api.deleteActividad(a.getId()+"");
                call.enqueue(new Callback<Actividad>() {
                    @Override
                    public void onResponse(Response<Actividad> response, Retrofit retrofit) {
                        Toast.makeText(getApplicationContext(),"Actividad Eliminada",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
                actividades.remove(info.position);
                ad.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

/********************Procesa las respuestas de crear y modificar las actividades******************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==AÑADIR){
            if(resultCode==RESULT_OK){
                Actividad a=data.getParcelableExtra("actividad");
                actividades.add(a);
                call=api.setActividad(a);
                call.enqueue(new Callback<Actividad>() {
                    @Override
                    public void onResponse(Response<Actividad> response, Retrofit retrofit) {
                        Toast.makeText(getApplicationContext(),"Actividad añadida",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }else {
                Toast.makeText(this,"No añadida",Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode==MODIFICA)
            if(resultCode==RESULT_OK){
                Actividad a=data.getParcelableExtra("actividad");
                call=api.updateActividad(a);
                call.enqueue(new Callback<Actividad>() {
                    @Override
                    public void onResponse(Response<Actividad> response, Retrofit retrofit) {
                        Toast.makeText(getApplicationContext(),"Actividad modificada",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
                ad.notifyDataSetChanged();
            }else {
                Toast.makeText(this,"No modificada",Toast.LENGTH_SHORT).show();
            }
    }
}
