/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Set;
import logica.estuctural.Antecedente;
import logica.estuctural.Ciudadano;
import persistencia.PersistenciaFake;

/**
 *
 * @author richa
 */
public class ServicioAntecedentes extends UnicastRemoteObject implements IServicioAntecedentes{

    @Override
    public void datosPrueba() throws RemoteException {
        PersistenciaFake.datosPrueba();
    }

    @Override
    public void eliminarCiudadano(String cedula, Ciudadano.TipoDocumento tipoDocumento) throws RemoteException {
        Ciudadano persona = PersistenciaFake.getCiudadanoPorCedula(cedula, tipoDocumento);
        PersistenciaFake.deleteCiudadano(persona);
    }

    @Override
    public void agregarCiudadano(String nombre, String apellido, String direccion, Date fecha, String cedula, Ciudadano.TipoDocumento tipoDocumento) throws RemoteException {
        Ciudadano ciudadano = new Ciudadano(nombre, apellido, direccion, fecha, cedula, tipoDocumento);
        PersistenciaFake.addCiudadano(ciudadano);
    }

    @Override
    public Ciudadano darCiudadanoPorCedula(String cedula, Ciudadano.TipoDocumento tipoDocumento) throws RemoteException {
        return PersistenciaFake.getCiudadanoPorCedula(cedula, tipoDocumento);
    }

    @Override
    public Set<Ciudadano> darCiudadanos() throws RemoteException {
         return PersistenciaFake.getCiudadanos();
    }

    @Override
    public void actualizarCiudadano(String nombre, String apellido, String direccion, Date fecha, String cedula, Ciudadano.TipoDocumento tipoDocumento) throws RemoteException {
        Ciudadano antiguo = darCiudadanoPorCedula(cedula, tipoDocumento);
        if(antiguo != null)
        {
            Ciudadano nuevo = new Ciudadano(nombre, apellido, direccion, fecha, cedula, tipoDocumento);
            PersistenciaFake.updateCiudadano(antiguo, nuevo);
        }
        else
        {
            throw new RemoteException("El ciudadano a actualizar no existe.");
        }
    }

    @Override
    public void aniadirAntecedenteCiudadano(Antecedente ant, String cedula, Ciudadano.TipoDocumento tipoDocumento) throws RemoteException {
        Ciudadano ciudadano = darCiudadanoPorCedula(cedula, tipoDocumento);
        ciudadano.addAntecedente(ant);
    }

    @Override
    public Antecedente darPrimerAntecedente(String cedula, Ciudadano.TipoDocumento tipoDocumento) throws RemoteException {
        Ciudadano ciudadano = darCiudadanoPorCedula(cedula, tipoDocumento);
        if(ciudadano != null){
            if(!ciudadano.getAntecedentes().isEmpty())
            for(Antecedente ant : ciudadano.getAntecedentes()){
                return ant;
            }
        }
        return null;
    }
    
}
