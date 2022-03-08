package com.example.demo.controller.cliente;

import com.example.demo.model.cliente.Cliente;
import com.example.demo.service.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos(){
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> buscarPorID(@PathVariable("id") Long id){
        return ResponseEntity.ok(clienteService.buscarPorID(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente){
        clienteService.inserir(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable("id") Long id ,@RequestBody Cliente cliente){
        clienteService.atualizar(id, cliente);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }






}
