package com.demo.example.dao;

import com.demo.example.model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class PessoaDao implements Dao<Pessoa> {

    private EntityManagerFactory factory = null;

    private Conexao conexao = Conexao.getInstance();

    @Override
    public Pessoa getById(final long id) {
        Pessoa pessoa = null;

        EntityManager manager = conexao.getEntityManager();
        pessoa = manager.find(Pessoa.class, id);

        return pessoa;
    }

    @Override
    public List<Pessoa> getAll() {
        List<Pessoa> pessoas = null;

        EntityManager manager = conexao.getEntityManager();
        pessoas = manager.createQuery(" FROM " + Pessoa.class.getName()).getResultList();

        return pessoas;
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        System.out.println("save " + Pessoa.class.getSimpleName());
        EntityManager manager = conexao.getEntityManager();
        try{
            manager.getTransaction().begin();
            manager.persist(pessoa);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }

        return pessoa;
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        EntityManager manager = conexao.getEntityManager();
        try{
            manager.getTransaction().begin();
            manager.merge(pessoa);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }

        return pessoa;
    }

    @Override
    public void delete(Pessoa pessoa) {
        EntityManager manager = conexao.getEntityManager();
        try{
            manager.getTransaction().begin();
            manager.remove(pessoa);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void disconnect(){
        conexao.close();
    }
}
