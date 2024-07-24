
package main.repository;

import java.util.ArrayList;
import main.entity.KhachHang;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import main.config.DBConnect;

public class KhachHangRepository {
    public ArrayList<KhachHang> getAll(){
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = """
                     SELECT [id_KhachHang]
                           ,[MaKhachHang]
                           ,[HoTen]
                           ,[NgaySinh]
                           ,[GioiTinh]
                           ,[SDT]
                           ,[Email]
                           ,[DiaChi]
                           ,[TrangThai]
                       FROM [dbo].[KhachHang]
                     WHERE [TrangThai] = 1
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                KhachHang kh = KhachHang.builder()
                        .id(rs.getInt(1))
                        .ma(rs.getString(2))
                        .ten(rs.getString(3))
                        .ngaySinh(rs.getDate(4))
                        .gioiTinh(rs.getBoolean(5))
                        .sdt(rs.getString(6))
                        .email(rs.getString(7))
                        .diaChi(rs.getString(8))
                        .trangThai(rs.getString(9))
                        .build();
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Boolean add(KhachHang kh){
        String sql = """
                     INSERT INTO [dbo].[KhachHang]
                                ([HoTen]
                                ,[NgaySinh]
                                ,[GioiTinh]
                                ,[SDT]
                                ,[Email]
                                ,[DiaChi]
                                ,[TrangThai])
                          VALUES(?,?,?,?,?,?,1)
                     """;
        int check = 0; 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kh.getTen());
            ps.setObject(2, kh.getNgaySinh());
            ps.setObject(3, kh.isGioiTinh());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getEmail());
            ps.setObject(6, kh.getDiaChi());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    
    public Boolean delete(Integer id){
        String sql = """
                     UPDATE [dbo].[KhachHang]
                        SET [TrangThai] = 0
                      WHERE id_KhachHang = ?
                     """;
        int check = 0; 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check >0;
    }
    
    public Boolean update(KhachHang kh,Integer id){
        String sql = """
                     UPDATE [dbo].[KhachHang]
                        SET [HoTen] = ?
                           ,[NgaySinh] = ?
                           ,[GioiTinh] = ?
                           ,[SDT] = ?
                           ,[Email] = ?
                           ,[DiaChi] = ?
                      WHERE id_KhachHang = ?
                     """;
        int check = 0; 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kh.getTen());
            ps.setObject(2, kh.getNgaySinh());
            ps.setObject(3, kh.isGioiTinh());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getEmail());
            ps.setObject(6, kh.getDiaChi());
            ps.setObject(7, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
}
