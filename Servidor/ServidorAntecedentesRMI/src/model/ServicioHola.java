/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import logica.estuctural.Antecedente;

/**
 *
 * @author Estudiantes
 */
public class ServicioHola extends UnicastRemoteObject implements IServicioHola{

    public ServicioHola( )throws RemoteException  {
    }

    @Override
    public String decirHola(String nombre) throws RemoteException {
        return "Hola - " + nombre;
    }

    public String mostrarAntecedente(Antecedente antecedente) throws RemoteException {
        return antecedente.getTipoDeCrimen() + " " + antecedente.getDetalles();
    }
}
