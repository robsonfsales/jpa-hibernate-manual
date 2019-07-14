package com.demo.example.main;

import com.demo.example.model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppTeste {

    public static void main( String[] args )
    {
        System.out.println( "Hello World demo jpa-hibernate!" );

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Steve Rogers");
        pessoa.setIdade(97);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "dev-demo-H2" );
        EntityManager manager = factory.createEntityManager();

        //Iniciar transação no BD
        manager.getTransaction().begin();

        manager.persist(pessoa);

        manager.getTransaction().commit();

        System.out.println("Id pessoa : " + pessoa.getId());
        System.out.println("Nome pessoa : " + pessoa.getNome());
        System.out.println("Idade pessoa : " + pessoa.getIdade());

        if(manager != null && manager.isOpen()){
            manager.close();
        }

        if(factory != null && factory.isOpen()){
            factory.close();
        }
    }
}
