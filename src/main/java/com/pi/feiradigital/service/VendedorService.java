package com.pi.feiradigital.service;

import com.pi.feiradigital.helper.DataHelper;
import com.pi.feiradigital.helper.FormatadorHelper;
import com.pi.feiradigital.model.Role;
import com.pi.feiradigital.model.Usuario;
import com.pi.feiradigital.model.Vendedor;
import com.pi.feiradigital.model.records.UserRecord;
import com.pi.feiradigital.model.records.VendedorRecord;
import com.pi.feiradigital.model.type.Status;
import com.pi.feiradigital.repository.RoleRepository;
import com.pi.feiradigital.repository.UsuarioRepository;
import com.pi.feiradigital.repository.VendedorRepository;
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
public class VendedorService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private DataHelper dataHelper;

    @Autowired
    private FormatadorHelper formatadorHelper;

    public ResponseEntity<?> adicionar(VendedorRecord vendedorRecord){

        Usuario user = criarUser(vendedorRecord.userRecord());

        Vendedor vendedor = criarVendedor(vendedorRecord, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Usuario criarUser(UserRecord userRecord){

        Optional<Role> userRole = roleRepository.findByName("VENDEDOR");
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

    private Vendedor criarVendedor(VendedorRecord vendedorRecord, Usuario user){

        Vendedor objVendedor = vendedorRepository.findByUsuario(user.getId());

        if (objVendedor != null){
            this.throwStatusException(HttpStatus.UNAUTHORIZED, "Não permitido");
        }

        Vendedor vendedor = new Vendedor();

        vendedor.setNome(vendedorRecord.nome());
        vendedor.setDocumento(formatadorHelper.formatarDocumentoBanco(vendedorRecord.documento()));
        vendedor.setTipoDocumento(vendedorRecord.tipoDocumento());
        vendedor.setCelular(formatadorHelper.formatarCelularBanco(vendedorRecord.celular()));
        vendedor.setEmail(vendedorRecord.email());
        vendedor.setTipoPessoa(vendedorRecord.tipoPessoa());
        vendedor.setUsuario(user.getId());
        vendedor.setDataIni(dataHelper.getDataHora());
        vendedor.setStatus(Status.ATIVO);

        Vendedor retorno = vendedorRepository.save(vendedor);

        return retorno;
    }

    private void throwStatusException(HttpStatus status, String message){

        ResponseStatusException exception = new ResponseStatusException(status, message);
        exception.setStackTrace(new StackTraceElement[]{});
        throw exception;
    }
}
