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
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JET
 */
public class Server extends UnicastRemoteObject implements IServer{
    
    private final List <Image> imagenes;
    
    public Server() throws RemoteException{
        super();
        imagenes = new ArrayList<>();
        
        imagenes.add(new Image("Imagen1", "https://www.dzoom.org.es/wp-content/uploads/2017/07/seebensee-2384369-810x540.jpg"));
        imagenes.add(new Image("Imagen2", "https://www.nationalgeographic.com.es/medio/2018/02/27/playa-de-isuntza-lekeitio__1280x720.jpg"));
        imagenes.add(new Image("Imagen3", "https://ep01.epimg.net/elpais/imagenes/2018/03/01/album/1519910473_492871_1519910821_noticia_normal.jpg"));
        imagenes.add(new Image("Imagen4", "https://mott.pe/noticias/wp-content/uploads/2017/10/Las-fotos-de-paisajes-naturales-m%C3%A1s-hermosos-del-mundo-por-Andi-Campbell_Jones.jpg"));
        imagenes.add(new Image("Imagen5", "https://mott.pe/noticias/wp-content/uploads/2017/09/Vista-panor%C3%A1mica-de-los-incre%C3%ADbles-paisajes-reflejados-en-im%C3%A1genes-de-Mikko-Leinonen-iloveimg-compressed.png"));
        
        imagenes.add(new Image("Imagen6", "https://culturafotografica.es/wp-content/uploads/2018/01/paisajes-marc-adamus-4-750x500.jpg"));
        imagenes.add(new Image("Imagen7", "https://www.viajejet.com/wp-content/viajes/Lago-Moraine-Parque-Nacional-Banff-Alberta-Canada-1440x810.jpg"));
        imagenes.add(new Image("Imagen8", "https://i.ytimg.com/vi/hW9PUzl7j9w/maxresdefault.jpg"));
        imagenes.add(new Image("Imagen9", "https://services.meteored.com/img/article/los-paisajes-del-agua---1.jpg"));
        imagenes.add(new Image("Imagen10", "https://mott.pe/noticias/wp-content/uploads/2016/11/Janette-Asche.jpg"));
    }
    
    public void init() throws RemoteException {
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
     
    @Override
    public void notificarPorcentaje(int porcentaje, int IdCliente) throws RemoteException{
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
    
    public static void main(String[] args) throws RemoteException{
        (new Server()).init();
    }
}
