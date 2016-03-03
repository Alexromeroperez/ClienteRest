package com.arp.clienterest.interfaces;

import com.arp.clienterest.pojo.Actividad;
import com.arp.clienterest.pojo.Profesor;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Alex on 16/02/2016.
 */
public interface ApiActividad {
    @GET("restful/api/actividad/alex")
    Call<List<Actividad>>getActividades();

    @GET("restful/api/profesor")
    Call<List<Profesor>> getProfesores();

    @POST("restful/api/actividad")
    Call<Actividad>setActividad(@Body Actividad act);

    @PUT("restful/api/actividad")
    Call<Actividad>updateActividad(@Body Actividad act);

    @DELETE("restful/api/actividad/{id}")
    Call<Actividad>deleteActividad(@Path("id") String id);
}
