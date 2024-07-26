package main.response;

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

public class SanPhamResponse {
    private Integer IdSanPham;
    private String MaSanPham;
    private String TenSanPham;
    private String MaImei;
    private String HinhAnh;
    private String TenCPU;
    private String TenGPU;
    private String LoaiOCung;
    private String DungLuongRam;
    private String KichThuoc;
    private String DungLuongPin;
    private Integer SoLuong;
    private Integer GiaNhap;
    private Integer GiaBan;
}

