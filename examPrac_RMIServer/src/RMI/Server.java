/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author JET
 */
public class Server extends UnicastRemoteObject implements IServer {

    public static JFrame frame;

    private final List<Image> imagenes;
    private List<Image> imagenes2;
    private final List<ICliente> clientes;
    private int contadorClientes;
    private JDesktopPane pane;
    private JLabel cc;
    private JLabel limite;

    public Server() throws RemoteException {
        super();
        gui();
        imagenes = new ArrayList<>();
        clientes = new ArrayList<>();
        contadorClientes = clientes.size();

        imagenes.add(new Image("Imagen1", "https://as00.epimg.net/meristation/imagenes/2018/08/23/avances/1535007120_261319_1535013359_noticia_normal.jpg"));
        imagenes.add(new Image("Imagen2", "https://images-eds-ssl.xboxlive.com/image?url=8Oaj9Ryq1G1_p3lLnXlsaZgGzAie6Mnu24_PawYuDYIoH77pJ.X5Z.MqQPibUVTca8_Pe9ZuLWb5eJUbFiW9qAC3VyqYZW0Pr2AxBGozGqO3g5Tx9PXPmnqRGFUJQtmsQs.0286WTh6I_OVdZGwgRbH_Oi4mGLGRmyLMGhKvRBIRO0n3ao38yH5HZrSdiVbA8zL42BSC8LBHzCzutSqqVs686_SVI8wMq2xG_Bnaj7M-&h=1080&w=1920&format=jpg"));
        imagenes.add(new Image("Imagen3", "https://ep01.epimg.net/elpais/imagenes/2018/03/01/album/1519910473_492871_1519910821_noticia_normal.jpg"));
        imagenes.add(new Image("Imagen4", "https://mott.pe/noticias/wp-content/uploads/2017/10/Las-fotos-de-paisajes-naturales-m%C3%A1s-hermosos-del-mundo-por-Andi-Campbell_Jones.jpg"));
        imagenes.add(new Image("Imagen5", "https://mott.pe/noticias/wp-content/uploads/2017/09/Vista-panor%C3%A1mica-de-los-incre%C3%ADbles-paisajes-reflejados-en-im%C3%A1genes-de-Mikko-Leinonen-iloveimg-compressed.png"));
        imagenes.add(new Image("Imagen6", "https://culturafotografica.es/wp-content/uploads/2018/01/paisajes-marc-adamus-4-750x500.jpg"));
        imagenes.add(new Image("Imagen7", "https://www.viajejet.com/wp-content/viajes/Lago-Moraine-Parque-Nacional-Banff-Alberta-Canada-1440x810.jpg"));
        imagenes.add(new Image("Imagen8", "https://i.ytimg.com/vi/hW9PUzl7j9w/maxresdefault.jpg"));
        imagenes.add(new Image("Imagen9", "https://services.meteored.com/img/article/los-paisajes-del-agua---1.jpg"));
        imagenes.add(new Image("Imagen10", "https://mott.pe/noticias/wp-content/uploads/2016/11/Janette-Asche.jpg"));

    }

    private void gui() {
        frame = new JFrame("Server");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pane = new JDesktopPane();
        frame.setContentPane(pane);

        JInternalFrame frameIF = new JInternalFrame();
        frameIF.setLayout(new GridLayout(3, 1));

        cc = new JLabel("Clientes conectados: " + contadorClientes);
        limite = new JLabel();
        JButton botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientes.size() > 0) {
                    if (clientes.size() == 1) {
                        try {
                            notificarPorcentaje(0, 1);
                        } catch (RemoteException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        for (int x = 0; x < clientes.size(); x++) {
                            try {
                                notificarPorcentaje(0, x);
                                System.out.println("-------");
                            } catch (RemoteException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        });

        frameIF.add(cc);
        frameIF.add(botonIniciar);
        frameIF.add(limite);
        frameIF.setSize(400, 100);
        frameIF.setVisible(true);
        pane.add(frameIF);
        frame.setVisible(true);

    }

    public void init() throws RemoteException {
        try {
            String direccion = (InetAddress.getLocalHost()).toString();
            int puerto = 3333;
            System.out.println("Iniciando servidor en " + direccion + ":" + puerto);

            Registry registro = LocateRegistry.createRegistry(puerto);
            registro.bind("MiServidor", (IServer) this);

        } catch (Exception ex) {
            System.out.println("Error en" + ex.getMessage());
        }
    }

    @Override
    public void notificarPorcentaje(int porcentaje, int IdCliente) throws RemoteException {
        imagenes2 = new ArrayList<>();
        if (clientes.size() > 1) {
            int ixc = imagenes.size() / clientes.size();
           
            for (int i = ((ixc * (IdCliente + 1)) - ixc); i < (ixc * (IdCliente + 1))+1; i++) {
                if (i < (ixc*(IdCliente+1))) {
                    System.out.println(i);
                    imagenes2.add(imagenes.get(i));
                  
                } else {
                    ICliente cliente = clientes.get(IdCliente);
                    cliente.iniciaProcesamiento(imagenes2);
                   
                }

            }

        } else {
            for (ICliente cliente : clientes) {
                cliente.iniciaProcesamiento(imagenes);
            }
        }
    }

    @Override
    public int registraCallBackCliente(ICliente cliente) throws RemoteException {
        int i = 1;
        if (!clientes.contains(cliente)) {
            if (clientes.size() < 10) {
                clientes.add(cliente);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cc.setText("Clientes conectados: " + clientes.size());
                    }
                });

            } else {
                limite.setText("Ya no se pueden agregar mas clientes");
            }

        }
        return clientes.size();
    }

    @Override
    public void desregistraCallBackCliente(ICliente cliente) throws RemoteException {
        if (clientes.contains(cliente)) {
            clientes.remove(cliente);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    cc.setText("Clientes conectados: " + clientes.size());
                }
            });
        }
    }

    public static void main(String[] args) throws RemoteException {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    (new Server()).init();
                } catch (RemoteException ex) {
                    System.out.println("Error en " + ex.getMessage());
                }
            }

        });
    }
}
