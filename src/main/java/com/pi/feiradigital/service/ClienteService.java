package com.pi.feiradigital.service;

import com.pi.feiradigital.helper.DataHelper;
import com.pi.feiradigital.helper.FormatadorHelper;
import com.pi.feiradigital.model.Cliente;
import com.pi.feiradigital.model.Role;
import com.pi.feiradigital.model.Usuario;
import com.pi.feiradigital.model.records.ClienteRecord;
import com.pi.feiradigital.model.records.UserRecord;
import com.pi.feiradigital.model.type.Status;
import com.pi.feiradigital.repository.ClienteRepository;
import com.pi.feiradigital.repository.RoleRepository;
import com.pi.feiradigital.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private DataHelper dataHelper;

    @Autowired
    private FormatadorHelper formatadorHelper;

    public ResponseEntity<?> adicionar(ClienteRecord clienteRecord){

        Usuario user = criarUser(clienteRecord.userRecord());

        Cliente cliente = criarCliente(clienteRecord, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Usuario criarUser(UserRecord userRecord){

        Optional<Role> userRole = roleRepository.findByName("CLIENTE");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole.get());

        Usuario usuario = new Usuario();

        usuario.setRoles(roles);
        usuario.setLogin(userRecord.login());
        usuario.setPassword(encoder.encode(userRecord.password()));
        usuario.setStatus(Status.ATIVO);
        usuario.setDataIni(dataHelper.getDataHora());

        Usuario retorno = usuarioRepository.save(usuario);

        return retorno;
    }

    private Cliente criarCliente(ClienteRecord clienteRecord, Usuario user){

        Cliente objCliente = clienteRepository.findByUsuario(user.getId());

        if (objCliente != null){
            this.throwStatusException(HttpStatus.UNAUTHORIZED, "Não permitido");
        }

        Cliente cliente = new Cliente();

        cliente.setNome(clienteRecord.nome());
        cliente.setDocumento(formatadorHelper.formatarDocumentoBanco(clienteRecord.documento()));
        cliente.setTipoDocumento(clienteRecord.tipoDocumento());
        cliente.setCelular(formatadorHelper.formatarCelularBanco(clienteRecord.celular()));
        cliente.setEmail(clienteRecord.email());
        cliente.setTipoPessoa(clienteRecord.tipoPessoa());
        cliente.setUsuario(user.getId());
        cliente.setDataIni(dataHelper.getDataHora());
        cliente.setStatus(Status.ATIVO);

        Cliente retorno = clienteRepository.save(cliente);

        return retorno;
    }

    private void throwStatusException(HttpStatus status, String message){

        ResponseStatusException exception = new ResponseStatusException(status, message);
        exception.setStackTrace(new StackTraceElement[]{});
        throw exception;
    }
}
