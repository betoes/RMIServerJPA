/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author JET
 */
public interface ICliente extends Remote {
    // Inicia en el cliente la descarga paralelamente de las imágenes, las guarden disco duro y notifica el avance (porcentaje) del procesamiento al servidor.
    public void iniciaProcesamiento(List<Image> imagenes) throws RemoteException;
}
