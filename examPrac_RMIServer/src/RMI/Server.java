/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author JET
 */
public class Server implements IServer{
    
    public Server() throws RemoteException{
        super();
    }
    
    public void init() {
         try {
            String direccion = (InetAddress.getLocalHost()).toString();
            int puerto = 3333;
            System.out.println("Iniciando servidor en " + direccion + ":" + puerto);
            
            Registry registro = LocateRegistry.createRegistry(puerto);
            registro.bind("MiServidor", (IServer) this);
            
        }
        catch(Exception ex) {
            System.out.println("Error en" + ex.getMessage());
        }
    }
    
    public static void main(String[] args) throws RemoteException{
        (new Server()).init();
    }
     
    @Override
    public void notificarPorcentaje(int porcentaje, int IdCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int registraCallBackCliente(ICliente cliente) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void desregistraCallBackCliente(ICliente cliente) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
