package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.Imei;
import main.entity.SanPham;

public class ImeiRepository {
    public ArrayList<Imei> getAll(){
        ArrayList<Imei> listImei = new ArrayList<>();
        String sql = """
                     SELECT [id_Imei]
                           ,[id_SanPham]
                           ,[Ma_Imei]
                           ,[TrangThai]
                           ,[TinhTrang]
                       FROM [dbo].[Imei] 
                     order by [id_Imei] desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Imei imei = Imei.builder()
                        .IdImei(rs.getInt(1))
                        .IdSanPham(rs.getInt(2))
                        .MaImei(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .TinhTrang(rs.getInt(5))
                        .build();
                listImei.add(imei);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listImei;
    }
    
    public Boolean add(Imei imei){
        String sql = """
                     INSERT INTO [dbo].[Imei]
                                ([Ma_Imei]
                                ,[TrangThai]
                                ,[TinhTrang])
                          VALUES
                                (?,0,0)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, imei.getMaImei());
            check  = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
    public Boolean delete(Integer IdImei){
        String sql = """
                     DELETE FROM [dbo].[Imei]
                           WHERE id_Imei = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, IdImei);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public Boolean updateIdSP(SanPham sp, Integer IdImei){
        String sql = """
                     UPDATE [dbo].[Imei]
                        SET [id_SanPham] = ?
                      WHERE id_Imei = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sp.getIdSanPham());
            ps.setObject(2, IdImei);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
}
