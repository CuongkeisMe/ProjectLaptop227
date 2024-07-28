
package main.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HoaDonTro {
    private String maHoaDon;
    private Date ngayTao;
    private String maNhanVien;
    private Boolean tinhTrang;
}
