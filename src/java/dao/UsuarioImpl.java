package dao;

import Dto.UsuarioDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioImpl extends Conexion {

    public void crear(Usuario usu) throws Exception {
        String sql = "Insert into Usuario"
                + "(IDARE, NOMUSU, DNIUSU, SEXUSU, TELUSU, ESTUSU)"
                + " values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, usu.getIDARE());
            ps.setString(2, usu.getNOMUSU());
            ps.setString(3, usu.getDNIUSU());
            ps.setString(4, usu.getSEXUSU());
            ps.setString(5, usu.getTELUSU());
            ps.setString(6, "A");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en insert UsuDAO " + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public void modificar(Usuario usu) throws Exception {
        String sql = "update Usuario set "
                + " IDARE=?, NOMUSU=?, DNIUSU=?, SEXUSU=?, TELUSU=?, ESTUSU=? where IDUSU=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, usu.getIDARE());
            ps.setString(2, usu.getNOMUSU());
            ps.setString(3, usu.getDNIUSU());
            ps.setString(4, usu.getSEXUSU());
            ps.setString(5, usu.getTELUSU());
            ps.setString(6, "A");
            ps.setInt(7, usu.getIDUSU());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en modificar UsuDAO" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public void modificarEst(Usuario usu) throws Exception {
        String sql = "update Usuario set"
                + " ESTUSU = 'I' where IDUSU=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, usu.getIDUSU());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar Usuario" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public List<UsuarioDTO> listar() throws Exception {
        List<UsuarioDTO> listado = new ArrayList<>();
        UsuarioDTO pro;
        String sql = " select USUARIO.IDUSU, USUARIO.NOMUSU, USUARIO.DNIUSU, USUARIO.SEXUSU, USUARIO.TELUSU, USUARIO.ESTUSU, AREA.IDARE, AREA.NOMARE from USUARIO\n"
                + " inner join AREA ON AREA.IDARE = USUARIO.IDARE WHERE USUARIO.ESTUSU = 'A' ORDER BY DNIUSU";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pro = new UsuarioDTO();
                pro.setIDUSU(rs.getInt("IDUSU"));
                pro.setDNIUSU(rs.getString("DNIUSU"));
                pro.setSEXUSU(rs.getString("SEXUSU"));
                pro.setTELUSU(rs.getString("TELUSU"));
                pro.setESTUSU(rs.getString("ESTUSU"));
                pro.setNOMUSU(rs.getString("NOMUSU"));
                pro.setIDARE(rs.getInt("IDARE"));
                pro.setArea(rs.getString("NOMARE"));
                listado.add(pro);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en la lista Usuario" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }
}
