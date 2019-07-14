package com.demo.example.dao;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

    public static final int LOAD_FILE_PERSISTENCE = 1;
    public static final int LOAD_FILE_HIBERNATE_CFG = 2;
    public static int FILE_CONFIG = LOAD_FILE_PERSISTENCE;

    private static Conexao instance;

    private EntityManagerFactory factory = null;
    private EntityManager entityManager;

    public static Conexao getInstance(){
        if (instance == null){
            instance = new Conexao();
        }

        return instance;
    }

    private Conexao() {
        entityManager = getEntityManager();
    }

    public EntityManager getEntityManager() {

        if (entityManager == null) {

            System.out.println("OPEN : entitiyManager");

            if(FILE_CONFIG == LOAD_FILE_PERSISTENCE){
                factory = loadPersistenceUnit();
            }else{
                factory = loadHibernateCfg();
            }

            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    private EntityManagerFactory loadHibernateCfg(){

        System.out.println("Load file CFG");

        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            factory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }

        return factory;
    }

    private EntityManagerFactory loadPersistenceUnit(){
        System.out.println("Load file Persistence");

        factory = Persistence.createEntityManagerFactory( "dev-demo-H2" );

        return factory;
    }

    public void close(){
        if(entityManager != null && entityManager.isOpen()){
            System.out.println("Close entityManager");
            entityManager.close();
        }

        if(factory != null && factory.isOpen()) {
            System.out.println("Close factory");
            factory.close();
        }
    }
}
