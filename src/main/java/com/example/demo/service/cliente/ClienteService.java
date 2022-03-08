package com.example.demo.service.cliente;

import com.example.demo.model.cliente.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();
    Cliente buscarPorID(Long id);
    void inserir(Cliente cliente);
    void atualizar(Long id, Cliente cliente);
    void deletar(Long id);


}
