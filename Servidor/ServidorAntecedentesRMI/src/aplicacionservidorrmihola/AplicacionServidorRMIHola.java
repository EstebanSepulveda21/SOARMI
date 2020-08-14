/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionservidorrmihola;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import model.ServicioAntecedentes;

/**
 *
 * @author Estudiantes
 */
public class AplicacionServidorRMIHola {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ServicioAntecedentes model = new ServicioAntecedentes();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//192.168.16.9/ServidorAntecedentes", model);
            System.out.println("Servidor Antecedentes funcionando");
        }catch(Exception e){
            System.out.println("Error! : " +e);
        }
    }
    
}
