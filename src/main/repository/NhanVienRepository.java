package main.repository;

import java.util.ArrayList;
import main.entity.NhanVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import main.config.DBConnect;

public class NhanVienRepository {

    public ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = """
                        SELECT [id_NhanVien]
                           ,[MaNhanVien]
                           ,[HoTen]
                           ,[NgaySinh]
                           ,[GioiTinh]
                           ,[SDT]
                           ,[Email]
                           ,[DiaChi]
                           ,[TrangThai]
                       FROM [dbo].[NhanVien] 
                     WHERE [TrangThai] = 1
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = NhanVien.builder()
                        .id(rs.getInt(1))
                        .ma(rs.getString(2))
                        .ten(rs.getString(3))
                        .ngaySinh(rs.getDate(4))
                        .gioiTinh(rs.getBoolean(5))
                        .sdt(rs.getString(6))
                        .email(rs.getString(7))
                        .diaChi(rs.getString(8))
                        .trangThai(rs.getBoolean(9))
                        .build();
                list.add(nv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean add(NhanVien nv) {
        String sql = """
                        INSERT INTO [dbo].[NhanVien]
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
            ps.setObject(1, nv.getTen());
            ps.setObject(2, nv.getNgaySinh());
            ps.setObject(3, nv.isGioiTinh());
            ps.setObject(4, nv.getSdt());
            ps.setObject(5, nv.getEmail());
            ps.setObject(6, nv.getDiaChi());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean delete(Integer id) {
        String sql = """
                     UPDATE [dbo].[NhanVien]
                        SET [TrangThai] = 0
                      WHERE id_NhanVien = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
        public Boolean update(NhanVien nv,Integer id) {
        String sql = """
                     UPDATE [dbo].[NhanVien]
                        SET [HoTen] = ?
                           ,[NgaySinh] = ?
                           ,[GioiTinh] = ?
                           ,[SDT] = ?
                           ,[Email] = ?
                           ,[DiaChi] = ?
                      WHERE id_NhanVien = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getTen());
            ps.setObject(2, nv.getNgaySinh());
            ps.setObject(3, nv.isGioiTinh());
            ps.setObject(4, nv.getSdt());
            ps.setObject(5, nv.getEmail());
            ps.setObject(6, nv.getDiaChi());
            ps.setObject(7, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
        
        public ArrayList<NhanVien> Tim(String hoten, String gioiTinh,String sdt, String ma ) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = """
                                          SELECT [id_NhanVien]
                                                ,[MaNhanVien]
                                                ,[HoTen]
                                                ,[NgaySinh]
                                                ,[GioiTinh]
                                                ,[SDT]
                                                ,[Email]
                                                ,[DiaChi]
                                                ,[TrangThai]
                                            FROM [dbo].[NhanVien]
                                          WHERE [TrangThai] = 1 
                                        and [HoTen] like '%'+?+'%'  
                                        and [GioiTinh] like '%'+?+'%' 
                                        and [SDT] like '%'+?+'%' 
                                        and [MaNhanVien] like '%'+?+'%'
                                        """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hoten);
            ps.setObject(2, gioiTinh);
            ps.setObject(3, sdt);
            ps.setObject(4, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = NhanVien.builder()
                        .id(rs.getInt(1))
                        .ma(rs.getString(2))
                        .ten(rs.getString(3))
                        .ngaySinh(rs.getDate(4))
                        .gioiTinh(rs.getBoolean(5))
                        .sdt(rs.getString(6))
                        .email(rs.getString(7))
                        .diaChi(rs.getString(8))
                        .trangThai(rs.getBoolean(9))
                        .build();
                list.add(nv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
