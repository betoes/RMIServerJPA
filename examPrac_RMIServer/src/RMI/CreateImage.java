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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("examPrac_RMIServerPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Images im1 = new Images(1,"https://as00.epimg.net/meristation/imagenes/2018/08/23/avances/1535007120_261319_1535013359_noticia_normal.jpg", new Date(), 0);
        em.persist(im1);
        Images im2 = new Images(2, "https://images-eds-ssl.xboxlive.com/image?url=8Oaj9Ryq1G1_p3lLnXlsaZgGzAie6Mnu24_PawYuDYIoH77pJ.X5Z.MqQPibUVTca8_Pe9ZuLWb5eJUbFiW9qAC3VyqYZW0Pr2AxBGozGqO3g5Tx9PXPmnqRGFUJQtmsQs.0286WTh6I_OVdZGwgRbH_Oi4mGLGRmyLMGhKvRBIRO0n3ao38yH5HZrSdiVbA8zL42BSC8LBHzCzutSqqVs686_SVI8wMq2xG_Bnaj7M-&h=1080&w=1920&format=jpg", new Date(), 0);
        em.persist(im2);
        Images im3 = new Images(3, "https://ep01.epimg.net/elpais/imagenes/2018/03/01/album/1519910473_492871_1519910821_noticia_normal.jpg", new Date(), 0);
        em.persist(im3);
        Images im4 = new Images(4, "https://mott.pe/noticias/wp-content/uploads/2017/10/Las-fotos-de-paisajes-naturales-m%C3%A1s-hermosos-del-mundo-por-Andi-Campbell_Jones.jpg", new Date(), 0);
        em.persist(im4);
        Images im5 = new Images(5, "https://mott.pe/noticias/wp-content/uploads/2017/09/Vista-panor%C3%A1mica-de-los-incre%C3%ADbles-paisajes-reflejados-en-im%C3%A1genes-de-Mikko-Leinonen-iloveimg-compressed.png", new Date(), 0);
        em.persist(im5);
        Images im6 = new Images(6, "https://culturafotografica.es/wp-content/uploads/2018/01/paisajes-marc-adamus-4-750x500.jpg", new Date(), 0);
        em.persist(im6);
        Images im7 = new Images(7, "https://www.viajejet.com/wp-content/viajes/Lago-Moraine-Parque-Nacional-Banff-Alberta-Canada-1440x810.jpg", new Date(), 0);
        em.persist(im7);
        Images im8 = new Images(8, "https://i.ytimg.com/vi/hW9PUzl7j9w/maxresdefault.jpg", new Date(), 0);
        em.persist(im8);
        Images im9 = new Images(9, "https://services.meteored.com/img/article/los-paisajes-del-agua---1.jpg", new Date(), 0);
        em.persist(im9);
        Images im10 = new Images(10, "https://mott.pe/noticias/wp-content/uploads/2016/11/Janette-Asche.jpg", new Date(), 0);
        em.persist(im10);
   
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
