package com.demo.example.main;

import com.demo.example.dao.Dao;
import com.demo.example.dao.PessoaDao;
import com.demo.example.model.Pessoa;

import java.util.List;

/**
 * Hello world JPA HIBERNATE !
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        System.out.println( "Hello World demo jpa-hibernate!" );

        PessoaDao pessoaDao = new PessoaDao();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Tony Stark");
        pessoa.setIdade(52);

        pessoaDao.save(pessoa);

        pessoa = new Pessoa();
        pessoa.setNome("Steve Rogers");
        pessoa.setIdade(97);

        pessoa = pessoaDao.save(pessoa);

        pessoa = pessoaDao.getById(pessoa.getId()-1);

        pessoa.setIdade(57);
        pessoa.setNome("Dr. Robert Bruce Banner");

        pessoaDao.update(pessoa);

        List<Pessoa> pessoas = pessoaDao.getAll();

        pessoaDao.delete(pessoas.get(0));

        pessoas = pessoaDao.getAll();

        for (Pessoa pessoa1 : pessoas) {
            System.out.println(pessoa1);
        }

        pessoaDao.disconnect();
    }
}
