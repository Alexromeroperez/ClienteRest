package com.arp.clienterest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arp.clienterest.pojo.Actividad;

import java.util.ArrayList;

/**
 * Created by Alex on 02/03/2016.
 */
public class Adaptador extends ArrayAdapter<Actividad> {

    private Context contexto;
    private int recurso;
    private ArrayList<Actividad> lista;
    private LayoutInflater i;

    public Adaptador(Context context, int resource, ArrayList<Actividad> objects) {
        super(context, resource, objects);
        this.contexto=context;
        this.recurso=resource;
        this.lista=objects;
        i=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(vh==null){
            vh=new ViewHolder();
            convertView=i.inflate(recurso,null);
            vh.tvDescripcion=(TextView)convertView.findViewById(R.id.tvDescripcion);
            vh.tvTipo=(TextView)convertView.findViewById(R.id.tvTipo);
            vh.tvProfe=(TextView)convertView.findViewById(R.id.tvProfe);
            vh.tvLinicio=(TextView)convertView.findViewById(R.id.tvLinicio);
            vh.tvLfinal=(TextView)convertView.findViewById(R.id.tvLfinal);
            vh.tvHinicio=(TextView)convertView.findViewById(R.id.tvHinicio);
            vh.tvHfinal=(TextView)convertView.findViewById(R.id.tvHfinal);
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder)convertView.getTag();
        }
        Actividad a = lista.get(position);
        vh.tvDescripcion.setText(a.getDescripcion());
        vh.tvProfe.setText(a.getIdprofesor()+"");
        vh.tvTipo.setText(a.getTipo());
        vh.tvLinicio.setText(a.getLugari());
        vh.tvLfinal.setText(a.getLugarf());
        vh.tvHinicio.setText(a.getFechai());
        vh.tvHfinal.setText(a.getFechaf());
        return convertView;
    }

    static class ViewHolder{
        private TextView tvDescripcion,tvTipo,tvLinicio,tvLfinal,tvHinicio,tvHfinal,tvProfe;
    }

}
