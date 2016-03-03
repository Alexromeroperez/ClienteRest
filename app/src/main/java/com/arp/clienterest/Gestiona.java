package com.arp.clienterest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.arp.clienterest.pojo.Actividad;
import java.util.Calendar;

public class Gestiona extends AppCompatActivity {
    private EditText etLi,etLf,etDescripcion;
    private TextView tvFi,tvFf;
    private Spinner spProfe,spTipo;
    private Actividad a;
    private ArrayAdapter<CharSequence> adapter;
    private String fecha="",hora="";
    private boolean estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestiona);
        ini();
        a=getIntent().getParcelableExtra("actividad");
        if(a!=null){
            loadDatos(a);
        }else{
            a=new Actividad();
        }
    }

    private void ini(){
        spProfe=(Spinner)findViewById(R.id.spProfesores);
        spTipo=(Spinner)findViewById(R.id.spTipo);
        etDescripcion=(EditText)findViewById(R.id.etDescripcion);
        tvFi=(TextView)findViewById(R.id.tvFinicio);
        tvFf=(TextView)findViewById(R.id.tvFfinal);
        etLi=(EditText)findViewById(R.id.etLinicio);
        etLf=(EditText)findViewById(R.id.etLfinal);
        adaptadorSpinner(R.array.tipo);
        spTipo.setAdapter(adapter);
        adaptadorSpinner(R.array.profesores);
        spProfe.setAdapter(adapter);
    }

    private void loadDatos(Actividad a){
        spProfe.setSelection((int) (a.getIdprofesor()-1));
        if(a.getTipo().equals("extraescolar")) {
            spTipo.setSelection(0);
        }else if(a.getTipo().equals("complementaria")){
            spTipo.setSelection(1);
        }else {
            spTipo.setSelection(2);
        }
        etLi.setText(a.getLugari());
        etLf.setText(a.getLugarf());
        tvFi.setText(a.getFechai());
        tvFf.setText(a.getFechaf());
        etDescripcion.setText(a.getDescripcion());
    }

    /**********************Acciones de los botones*********************************/
    public void fechai(View v){
        estado=true;
        DialogFragment newFragment = new DatePicker();
        newFragment.show(getFragmentManager(), "Elegir Fecha");

    }

    public void fechaf(View v){
        estado=false;
        DialogFragment newFragment = new DatePicker();
        newFragment.show(getFragmentManager(), "Elegir Fecha");
    }

    public void horai(View v){
        estado=true;
        DialogFragment newFragment = new TimePicker();
        newFragment.show(getFragmentManager(), "Elige Hora");
    }

    public void horaf(View v){
        estado=false;
        DialogFragment newFragment = new TimePicker();
        newFragment.show(getFragmentManager(), "Elige Hora");
    }

    public void guardar(View v){
        a.setAlumno("alex");
        a.setDescripcion(etDescripcion.getText().toString());
        a.setIdprofesor(spProfe.getSelectedItemId()+1);
        a.setTipo(spTipo.getSelectedItem().toString());
        a.setFechai(tvFi.getText().toString());
        a.setFechaf(tvFf.getText().toString());
        a.setLugari(etLi.getText().toString());
        a.setLugarf(etLf.getText().toString());
        Intent i=new Intent();
        i.putExtra("actividad",a);
        setResult(MainActivity.RESULT_OK, i);
        finish();
    }

    public void cancelar(View v){
        setResult(MainActivity.RESULT_CANCELED);
        finish();
    }

    /**********************Adaptador del spinner*********************************/
    private void adaptadorSpinner(int array){
        adapter = ArrayAdapter.createFromResource(this,
                array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    /***************Clases DatePicker y TimePicker***************/

    public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            fecha=year+"-"+monthOfYear+"-"+dayOfMonth;
            if(estado) {
                tvFi.setText(fecha+" "+hora);
            }else {
                tvFf.setText(fecha+" "+hora);
            }
        }
    }

    public class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            hora=hourOfDay+":"+minute;
            if(estado){
            tvFi.setText(fecha+" "+hora);
            }else {
                tvFf.setText(fecha+" "+hora);
            }
        }
    }

}
