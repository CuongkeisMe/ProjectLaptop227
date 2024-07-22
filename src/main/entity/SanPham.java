package main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SanPham {
    private Integer IdSanPham;
    private Integer IdRam;
    private Integer IdCPU;
    private Integer IdGPU;
    private Integer IdManHinh;
    private Integer IdOCung;
    private Integer IdPin;
    private String MaSanPham;
    private String TenSanPham;
    private String HinhAnh;
    private Integer SoLuong;
    private Integer GiaNhap;
    private Integer GIaBan;
    private Integer TrangThai;
}