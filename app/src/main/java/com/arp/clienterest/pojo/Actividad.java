package com.arp.clienterest.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 15/02/2016.
 */
public class Actividad  implements Parcelable{
    private long id,idprofesor;
    private String tipo,fechai,fechaf,lugari,lugarf,descripcion,alumno;

    public Actividad() {
        this(0, 0, "", "", "", "", "", "", "");
    }

    public Actividad(long id, long idprofesor, String tipo, String fechai, String fechaf, String lugari, String lugarf, String descripcion, String alumno) {
        this.id = id;
        this.idprofesor = idprofesor;
        this.tipo = tipo;
        this.fechai = fechai;
        this.fechaf = fechaf;
        this.lugari = lugari;
        this.lugarf = lugarf;
        this.descripcion = descripcion;
        this.alumno = alumno;
    }

    protected Actividad(Parcel in) {
        id = in.readLong();
        idprofesor = in.readLong();
        tipo = in.readString();
        fechai = in.readString();
        fechaf = in.readString();
        lugari = in.readString();
        lugarf = in.readString();
        descripcion = in.readString();
        alumno = in.readString();
    }

    public static final Creator<Actividad> CREATOR = new Creator<Actividad>() {
        @Override
        public Actividad createFromParcel(Parcel in) {
            return new Actividad(in);
        }

        @Override
        public Actividad[] newArray(int size) {
            return new Actividad[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(long idprofesor) {
        this.idprofesor = idprofesor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechai() {
        return fechai;
    }

    public void setFechai(String fechai) {
        this.fechai = fechai;
    }

    public String getFechaf() {
        return fechaf;
    }

    public void setFechaf(String fechaf) {
        this.fechaf = fechaf;
    }

    public String getLugari() {
        return lugari;
    }

    public void setLugari(String lugari) {
        this.lugari = lugari;
    }

    public String getLugarf() {
        return lugarf;
    }

    public void setLugarf(String lugarf) {
        this.lugarf = lugarf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", idprofesor=" + idprofesor +
                ", tipo='" + tipo + '\'' +
                ", fechai='" + fechai + '\'' +
                ", fechaf='" + fechaf + '\'' +
                ", lugari='" + lugari + '\'' +
                ", lugarf='" + lugarf + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", alumno='" + alumno + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(idprofesor);
        dest.writeString(tipo);
        dest.writeString(fechai);
        dest.writeString(fechaf);
        dest.writeString(lugari);
        dest.writeString(lugarf);
        dest.writeString(descripcion);
        dest.writeString(alumno);
    }
}
