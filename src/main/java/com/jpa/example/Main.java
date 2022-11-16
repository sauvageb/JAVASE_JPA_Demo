package com.jpa.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World !");
        Book book = new Book(
                "Les quatre accords Toltèques.",
                "Découvrez ou redécouvrez Les quatre accords toltèques, et prenez comme des millions de lecteurs en France et à travers le monde, la voie de la liberté personnelle. Dans ce livre, Don Miguel révèle la source des croyances limitatrices qui nous privent de joie et créent des souffrances inutiles.",
                8.9f,
                3,
                LocalDate.now(),
                "https://m.media-amazon.com/images/I/71ZqQ7rSupL.jpg"
        );

        // TODO : create singleton for EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        // TODO : create DAO Layer for JPA (with entityManager methods)
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction et = entityManager.getTransaction();
        try {
            et.begin();
            // CREATE
            entityManager.persist(book);
            // READ
            //            entityManager.find()
            //            Query query = entityManager.createQuery("Requete en JPQL");
            // UPADTE
            //            entityManager.merge()
            // DELETE
            //             entityManager.remove();

            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
        } finally {
            emf.close();
        }
    }
}
