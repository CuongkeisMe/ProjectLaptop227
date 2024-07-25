/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.util.ArrayList;
import main.entity.BanHang;
import main.config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BanHangSPRepositories {
    public ArrayList<BanHang> getAll(){
        ArrayList<BanHang> list = new ArrayList<>();
        String sql ="""
                    SELECT 
                        SanPham.MaSanPham AS 'MaSP',
                        SanPham.TenSanPham AS 'TenSP',
                        CPU.TenCPU AS 'CPU',
                        GPU.TenGPU AS 'GPU',
                        Ram.DungLuongRam AS 'RAM',
                        ManHinh.KichThuoc AS 'ManHinh',
                        OCung.LoaiOCung AS 'OCung',
                        Pin.DungLuongPin AS 'Pin',
                        SanPham.GiaBan AS 'Gia',
                        SanPham.SoLuong AS 'SoLuongSP'
                    FROM 
                        SanPham
                    JOIN 
                        CPU ON SanPham.id_CPU = CPU.id_CPU
                    JOIN 
                        GPU ON SanPham.id_GPU = GPU.id_GPU
                    JOIN 
                        Ram ON SanPham.id_Ram = Ram.id_Ram
                    JOIN 
                        ManHinh ON SanPham.id_ManHinh = ManHinh.id_ManHinh
                    JOIN 
                        OCung ON SanPham.id_OCung = OCung.id_OCung
                    JOIN 
                        Pin ON SanPham.id_Pin = Pin.id_Pin;
                    """;
        try (Connection con =DBConnect.getConnection();PreparedStatement ps = con.prepareStatement(sql)){
        ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                BanHang bh = BanHang.builder()
                        .maSP(rs.getString(1))
                        .tenSP(rs.getString(2))
                        .tenCPU(rs.getString(3))
                        .tenGPU(rs.getString(4))
                        .tenRam(rs.getString(5))
                        .tenMH(rs.getString(6))
                        .tenOCung(rs.getString(7))
                        .pin(rs.getString(8))
                        .giaSP(rs.getFloat(9))
                        .soLuongSP(rs.getInt(10))
                        .build();
                list.add(bh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
