package Services;

import Dto.UsuarioDTO;
import dao.UsuarioImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;

public class UsuarioServiceImpl implements UsuarioService {

    UsuarioImpl dao = new UsuarioImpl();

    @Override
    public void crear(UsuarioDTO usuarioDTO) {
        Usuario usu = new Usuario();
        usu.setIDUSU(usuarioDTO.getIDUSU());
        usu.setNOMUSU(usuarioDTO.getNOMUSU());
        usu.setDNIUSU(usuarioDTO.getDNIUSU());
        usu.setSEXUSU(usuarioDTO.getSEXUSU());
        usu.setTELUSU(usuarioDTO.getTELUSU());
        usu.setESTUSU(usuarioDTO.getESTUSU());
        usu.setIDARE(usuarioDTO.getIDARE());
        try {
            dao.crear(usu);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar(UsuarioDTO usuarioDTO) {
        Usuario usu = new Usuario();
        usu.setIDUSU(usuarioDTO.getIDUSU());
        usu.setNOMUSU(usuarioDTO.getNOMUSU());
        usu.setDNIUSU(usuarioDTO.getDNIUSU());
        usu.setSEXUSU(usuarioDTO.getSEXUSU());
        usu.setTELUSU(usuarioDTO.getTELUSU());
        usu.setESTUSU(usuarioDTO.getESTUSU());
        usu.setIDARE(usuarioDTO.getIDARE());
        try {
            dao.modificar(usu);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarEstado(Integer IDUSU) {
        Usuario usu = new Usuario();
        usu.setIDUSU(IDUSU);
        usu.setESTUSU("I");
        try {
            dao.modificarEst(usu);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<UsuarioDTO> listar() throws Exception {
        return dao.listar();
    }

}
