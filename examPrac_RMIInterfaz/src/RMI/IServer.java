/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author JET
 */
public interface IServer extends Remote{
    //notificar el porcentaje del servidor 
    public void notificarPorcentaje(int porcentaje, int IdCliente);
    
    // Inicia el registro del cliente en el Servidor, El servidor devuelve su Id de cliente para su posterior uso.
    public int registraCallBackCliente(ICliente cliente) throws RemoteException;
    
    // Quita el registro del cliente en el Servidor
    public void desregistraCallBackCliente(ICliente cliente) throws RemoteException;
}
