/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Beto Lafarc
 */
public class CreateImage {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("examPrac_RMIInterfazPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Image im1 = new Image(1,"https://as00.epimg.net/meristation/imagenes/2018/08/23/avances/1535007120_261319_1535013359_noticia_normal.jpg");
        em.persist(im1);
        Image im2 = new Image(2, "https://images-eds-ssl.xboxlive.com/image?url=8Oaj9Ryq1G1_p3lLnXlsaZgGzAie6Mnu24_PawYuDYIoH77pJ.X5Z.MqQPibUVTca8_Pe9ZuLWb5eJUbFiW9qAC3VyqYZW0Pr2AxBGozGqO3g5Tx9PXPmnqRGFUJQtmsQs.0286WTh6I_OVdZGwgRbH_Oi4mGLGRmyLMGhKvRBIRO0n3ao38yH5HZrSdiVbA8zL42BSC8LBHzCzutSqqVs686_SVI8wMq2xG_Bnaj7M-&h=1080&w=1920&format=jpg");
        em.persist(im2);
        Image im3 = new Image(3, "https://ep01.epimg.net/elpais/imagenes/2018/03/01/album/1519910473_492871_1519910821_noticia_normal.jpg");
        em.persist(im3);
        Image im4 = new Image(4, "https://mott.pe/noticias/wp-content/uploads/2017/10/Las-fotos-de-paisajes-naturales-m%C3%A1s-hermosos-del-mundo-por-Andi-Campbell_Jones.jpg");
        em.persist(im4);
        Image im5 = new Image(5, "https://mott.pe/noticias/wp-content/uploads/2017/09/Vista-panor%C3%A1mica-de-los-incre%C3%ADbles-paisajes-reflejados-en-im%C3%A1genes-de-Mikko-Leinonen-iloveimg-compressed.png");
        em.persist(im5);
        Image im6 = new Image(6, "https://culturafotografica.es/wp-content/uploads/2018/01/paisajes-marc-adamus-4-750x500.jpg");
        em.persist(im6);
        Image im7 = new Image(7, "https://www.viajejet.com/wp-content/viajes/Lago-Moraine-Parque-Nacional-Banff-Alberta-Canada-1440x810.jpg");
        em.persist(im7);
        Image im8 = new Image(8, "https://i.ytimg.com/vi/hW9PUzl7j9w/maxresdefault.jpg");
        em.persist(im8);
        Image im9 = new Image(9, "https://services.meteored.com/img/article/los-paisajes-del-agua---1.jpg");
        em.persist(im9);
        Image im10 = new Image(10, "https://mott.pe/noticias/wp-content/uploads/2016/11/Janette-Asche.jpg");
        em.persist(im10);
   
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
