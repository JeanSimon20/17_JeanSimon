package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Area;

public class AreaImpl extends Conexion implements ICRUD<Area> {

    @Override
    public void registrar(Area are) throws Exception {
        String sql = "insert into Area"
                + "(NOMARE, ESTARE) values (?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, are.getNOMARE());
            ps.setString(2, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en registrar Area" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificar(Area are) throws Exception {
        String sql = "update Area set"
                + " NOMARE=?, ESTARE=? where IDARE=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, are.getNOMARE());
            ps.setString(2, "A");
            ps.setInt(3, are.getIDARE());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar AreDAO" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificarEst(Area are) throws Exception {
        String sql = "update Area set"
                + " ESTARE = 'I' where IDARE=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, are.getIDARE());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar AreDAO" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void eliminar(Area gen) throws Exception {

    }

    @Override
    public List<Area> listar() throws Exception {
        List<Area> listado = new ArrayList<>();
        Area are;
        String sql = "SELECT * FROM AREA WHERE AREA.ESTARE = 'A' ORDER BY NOMARE";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                are = new Area();
                are.setIDARE(rs.getInt("IDARE"));
                are.setNOMARE(rs.getString("NOMARE"));
                are.setESTARE(rs.getString("ESTARE"));
                listado.add(are);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en la lisTa AreDAO" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }

}
