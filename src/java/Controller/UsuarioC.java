package Controller;

import Dto.UsuarioDTO;
import Services.UsuarioServiceImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;

@Data
@Named(value = "usuarioC")
@SessionScoped
public class UsuarioC implements Serializable {

    UsuarioServiceImpl usuarioServiceImpl = new UsuarioServiceImpl();
    private List<UsuarioDTO> listUsuarioDTO = new ArrayList<>();
    private UsuarioDTO usuarioDTO = new UsuarioDTO();
    private UsuarioDTO seleccionado = new UsuarioDTO();

    public UsuarioC() {
    }

    @PostConstruct
    public void onInit() {
        try {
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() {
        try {
            usuarioServiceImpl.crear(usuarioDTO);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Registrado con exito"));
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Registrado con exito"));
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() {
        try {
            usuarioServiceImpl.modificar(seleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificacion", "Modificado con exito"));
            listar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void modificarEstado(Integer IDUSU) {
        try {
            usuarioServiceImpl.modificarEstado(IDUSU);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Inactivar", "Inactivado con exito"));
            listar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void listar() {
        try {
            listUsuarioDTO = new ArrayList<>();
            listUsuarioDTO = usuarioServiceImpl.listar();
        } catch (Exception e) {

        }
    }

}
