package com.example.demo.service.cliente;

import com.example.demo.model.cliente.Cliente;
import com.example.demo.model.cliente.ClienteRepository;
import com.example.demo.model.endereco.Endereco;
import com.example.demo.model.endereco.EnderecoRepository;
import com.example.demo.service.viacep.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImp implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorID(Long id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public void inserir(Cliente cliente) {
        inserirClienteCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        if(clienteRepository.findById(id).isPresent()){
            inserirClienteCep(cliente);
        }
    }

    private void inserirClienteCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    Endereco novoEndereco = viaCepService.consultarCep(cep);
                    enderecoRepository.save(novoEndereco);
                    return novoEndereco;
                });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
