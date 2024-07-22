package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.Imei;

public class ImeiRepository {
    public ArrayList<Imei> getAll(){
        ArrayList<Imei> listImei = new ArrayList<>();
        String sql = """
                     SELECT [id_Imei]
                           ,[id_SanPham]
                           ,[Ma_Imei]
                           ,[TrangThai]
                       FROM [dbo].[Imei]
                     WHERE [TrangThai] = 1
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Imei imei = Imei.builder()
                        .IdImei(rs.getInt(1))
                        .IdSanPham(rs.getInt(2))
                        .MaImei(rs.getString(3))
                       .TrangThai(rs.getInt(4))
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
                                ([id_SanPham]
                                ,[Ma_Imei]
                                ,[TrangThai])
                          VALUES
                                (?,?,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ps);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public Boolean delete(Integer IdImei){
        String sql = """
                     UPDATE [dbo].[Imei]
                        SET [TrangThai] = 0
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
}
