package main.view.chucnang;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import main.entity.Cpu;
import main.entity.Gpu;
import main.entity.Imei;
import main.entity.ManHinh;
import main.entity.OCung;
import main.entity.Pin;
import main.entity.Ram;
import main.entity.SanPham;
import main.repository.CpuRepository;
import main.repository.GpuRepository;
import main.repository.ImeiRepository;
import main.repository.ManHinhRepository;
import main.repository.OCungRepository;
import main.repository.PinRepository;
import main.repository.RamRepository;
import main.repository.SanPhamRepository;
import main.request.FindSanPham;
import main.request.SanPhamRequest;
import main.response.SanPhamResponse;
import main.view.sanphamchitiet.CpuView;
import main.view.sanphamchitiet.GpuView;
import main.view.sanphamchitiet.ManHinhView;
import main.view.sanphamchitiet.OCungView;
import main.view.sanphamchitiet.PinView;
import main.view.sanphamchitiet.RamView;

public class SanPhamView extends javax.swing.JInternalFrame {

    private DefaultTableModel dtmSanPham;
    private DefaultTableModel dtmImei;
    private DefaultTableModel dtmImeiChiTiet;
    private DefaultTableModel dtmSanPhamDaXoa;
    private SanPhamRepository sanphamRepository;

    private DefaultComboBoxModel cpuDcbm;
    private DefaultComboBoxModel gpuDcbm;
    private DefaultComboBoxModel ramDcbm;
    private DefaultComboBoxModel manhinhDcbm;
    private DefaultComboBoxModel pinDcbm;
    private DefaultComboBoxModel ocungDcbm;
    private DefaultComboBoxModel sanphamDcbm;

    private CpuRepository cpuRepository;
    private GpuRepository gpuRepository;
    private RamRepository ramRepository;
    private ManHinhRepository manhinhRepository;
    private PinRepository pinRepository;
    private OCungRepository ocungRepository;
    private ImeiRepository imeiRepository;
    private SanPhamResponse sanphamResponse;

    public SanPhamView() {
        initComponents();
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.put("TabbedPane.tabsOverlapBorder", true);
        this.cauhinhForm();
        dtmSanPham = (DefaultTableModel) tblQuanLySP.getModel();
        dtmImei = (DefaultTableModel) tblQLyImei.getModel();
        dtmImeiChiTiet = (DefaultTableModel) tblImeiCT.getModel();
        dtmSanPhamDaXoa = (DefaultTableModel) tblQuanLySPDaXoa.getModel();
        sanphamRepository = new SanPhamRepository();
        this.cboDinhDang();
        this.repositoryDinhDang();
        this.showComboboxCPU();
        this.showComboboxGPU();
        this.showComboboxManHinh();
        this.showComboboxOCung();
        this.showComboboxPin();
        this.showComboboxRam();
        this.showComboboxSanPham();
        this.showDataTableSanPham(sanphamRepository.getAll(getFormSearch()));
        this.showDataTableImei(sanphamRepository.getAll(getFormSearch()));
//        this.showDataTableSanPhamDaXoa(sanphamRepository.getAllDelete());
    }

    public void cauhinhForm() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    private void cboDinhDang() {
        cpuDcbm = (DefaultComboBoxModel) cboCPU.getModel();
        gpuDcbm = (DefaultComboBoxModel) cboGPU.getModel();
        ramDcbm = (DefaultComboBoxModel) cboRam.getModel();
        manhinhDcbm = (DefaultComboBoxModel) cboKichThuoc.getModel();
        pinDcbm = (DefaultComboBoxModel) cboPin.getModel();
        ocungDcbm = (DefaultComboBoxModel) cboOCung.getModel();
        sanphamDcbm = (DefaultComboBoxModel) cboMaSP.getModel();
    }

    private void repositoryDinhDang() {
        cpuRepository = new CpuRepository();
        gpuRepository = new GpuRepository();
        ramRepository = new RamRepository();
        manhinhRepository = new ManHinhRepository();
        pinRepository = new PinRepository();
        ocungRepository = new OCungRepository();
        imeiRepository = new ImeiRepository();
    }

    public void showComboboxCPU() {
        cpuDcbm.removeAllElements();
        for (Cpu cpu : cpuRepository.getAll()) {
            cpuDcbm.addElement(cpu.getTenCPU());
        }
    }

    public void showComboboxGPU() {
        gpuDcbm.removeAllElements();
        for (Gpu gpu : gpuRepository.getAll()) {
            gpuDcbm.addElement(gpu.getTenGPU());
        }
    }

    public void showComboboxRam() {
        ramDcbm.removeAllElements();
        for (Ram ram : ramRepository.getAll()) {
            ramDcbm.addElement(ram.getDungLuongRam());
        }
    }

    public void showComboboxManHinh() {
        manhinhDcbm.removeAllElements();
        for (ManHinh mh : manhinhRepository.getAll()) {
            manhinhDcbm.addElement(mh.getKichThuoc());
        }
    }

    public void showComboboxPin() {
        pinDcbm.removeAllElements();
        for (Pin pin : pinRepository.getAll()) {
            pinDcbm.addElement(pin.getDungLuongPin());
        }
    }

    public void showComboboxOCung() {
        ocungDcbm.removeAllElements();
        for (OCung oc : ocungRepository.getAll()) {
            ocungDcbm.addElement(oc.getLoaiOCung());
        }
    }

    public void showComboboxSanPham() {
        sanphamDcbm.removeAllElements();
        for (SanPhamResponse sanPhamResponse : sanphamRepository.getAll(getFormSearch())) {
            sanphamDcbm.addElement(sanPhamResponse.getMaSanPham());
        }
    }

    public void showDataTableSanPham(ArrayList<SanPhamResponse> list) {
        dtmSanPham.setRowCount(0);
        list.forEach(x -> dtmSanPham.addRow(new Object[]{
            x.getMaSanPham(), x.getTenSanPham(), x.getHinhAnh(), x.getTenCPU(), x.getTenGPU(),
            x.getLoaiOCung(), x.getDungLuongRam(), x.getKichThuoc(), x.getDungLuongPin(),
            x.getGiaNhap(), x.getGiaBan()
        }));
    }

    public void showDataTableSanPhamDaXoa(ArrayList<SanPhamResponse> list) {
        dtmSanPham.setRowCount(0);
        list.forEach(x -> dtmSanPham.addRow(new Object[]{
            x.getMaSanPham(), x.getTenSanPham(), x.getHinhAnh(), x.getTenCPU(), x.getTenGPU(),
            x.getLoaiOCung(), x.getDungLuongRam(), x.getKichThuoc(), x.getDungLuongPin(),
            x.getGiaNhap(), x.getGiaBan()
        }));
    }

    public void showDataTableImei(ArrayList<SanPhamResponse> list) {
        dtmImei.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        list.forEach(x -> dtmImei.addRow(new Object[]{
            index.getAndIncrement(), x.getMaSanPham(), x.getTenSanPham(), x.getGiaNhap(), x.getGiaBan(), x.getSoLuong()
        }));
        for (int i = 0; i < sanphamRepository.getAll(getFormSearch()).size(); i++) {

        }
    }

    public void ShowDataTableImeiChiTiet(String MaSP) {
        dtmImeiChiTiet.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        sanphamRepository.getImeiByMaSP(MaSP).forEach(x -> dtmImeiChiTiet.addRow(new Object[]{
            index.getAndIncrement(), MaSP, x.getMaImei()
        }));
    }

    private void detail(int index) {
        SanPhamResponse spReponse = sanphamRepository.getAll(getFormSearch()).get(index);
        txtTenSP.setText(spReponse.getTenSanPham());
        cboCPU.setSelectedItem(spReponse.getTenCPU());
        cboGPU.setSelectedItem(spReponse.getTenGPU());
        cboOCung.setSelectedItem(spReponse.getLoaiOCung());
        cboRam.setSelectedItem(spReponse.getDungLuongRam());
        cboKichThuoc.setSelectedItem(spReponse.getKichThuoc());
        cboPin.setSelectedItem(spReponse.getDungLuongPin());
        txtGiaNhap.setText(String.valueOf(spReponse.getGiaNhap()));
        txtGiaBan.setText(String.valueOf(spReponse.getGiaBan()));
    }

    private SanPhamRequest getFormDataSanPham() {
        int indexCPU = cboCPU.getSelectedIndex();
        int indexGPU = cboGPU.getSelectedIndex();
        int indexRam = cboRam.getSelectedIndex();
        int indexManHinh = cboKichThuoc.getSelectedIndex();
        int indexOCung = cboOCung.getSelectedIndex();
        int indexPin = cboPin.getSelectedIndex();
        return SanPhamRequest.builder()
                .IdRam(ramRepository.getAll().get(indexRam).getIdRam())
                .IdCPU(cpuRepository.getAll().get(indexCPU).getIdCPU())
                .IdGPU(gpuRepository.getAll().get(indexGPU).getIdGPU())
                .IdManHinh(manhinhRepository.getAll().get(indexManHinh).getIdManHinh())
                .IdOCung(ocungRepository.getAll().get(indexOCung).getIdOCung())
                .IdPin(pinRepository.getAll().get(indexPin).getIdPin())
                .TenSanPham(txtTenSP.getText())
                .GiaNhap(Integer.parseInt(txtGiaNhap.getText()))
                .GiaBan(Integer.parseInt(txtGiaBan.getText()))
                .build();
    }

    private Imei getFormDataImei() {
        return Imei.builder()
                .MaImei(txtMaImei.getText())
                .build();
    }

    private FindSanPham getFormSearch() {
        FindSanPham fsp = new FindSanPham();
        fsp.setKeySearch(txtSearch.getText());
        return fsp;
    }

    private int getIdSpByMa() {
        for (SanPhamResponse spr : sanphamRepository.getAll(getFormSearch())) {
            if (cboMaSP.getSelectedItem().equals(spr.getMaSanPham())) {
                return spr.getIdSanPham();
            }
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        cboCPU = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btnAddCPU = new javax.swing.JButton();
        cboRam = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btnAddRam = new javax.swing.JButton();
        cboGPU = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btnAddGPU = new javax.swing.JButton();
        cboPin = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        btnAddPin = new javax.swing.JButton();
        cboOCung = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        btnAddOCung = new javax.swing.JButton();
        cboKichThuoc = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        btnAddKichThuoc = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuanLySP = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQLyImei = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboMaSP = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtMaImei = new javax.swing.JTextField();
        btnAddImei = new javax.swing.JButton();
        btnClearImei = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblImeiCT = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblQuanLySPDaXoa = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setBorder(null);
        setForeground(java.awt.Color.lightGray);
        setMaximizable(true);
        setFrameIcon(null);

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Chọn hình ảnh");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel16.setText("Tên SP");

        txtTenSP.setMinimumSize(new java.awt.Dimension(64, 30));
        txtTenSP.setPreferredSize(new java.awt.Dimension(64, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel19.setText("Giá nhập");

        txtGiaNhap.setMinimumSize(new java.awt.Dimension(64, 30));
        txtGiaNhap.setPreferredSize(new java.awt.Dimension(64, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel20.setText("Giá bán");

        txtGiaBan.setMinimumSize(new java.awt.Dimension(64, 30));
        txtGiaBan.setPreferredSize(new java.awt.Dimension(64, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel16)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuộc Tính", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.add(cboCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("CPU");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 40, -1));

        btnAddCPU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/32378_add_plus_icon.png"))); // NOI18N
        btnAddCPU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddCPUMouseClicked(evt);
            }
        });
        btnAddCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCPUActionPerformed(evt);
            }
        });
        jPanel3.add(btnAddCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 140, 30));

        cboRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRamActionPerformed(evt);
            }
        });
        jPanel3.add(cboRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 140, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("RAM");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, -1));

        btnAddRam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/32378_add_plus_icon.png"))); // NOI18N
        btnAddRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRamActionPerformed(evt);
            }
        });
        jPanel3.add(btnAddRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 140, 30));

        jPanel3.add(cboGPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 140, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("GPU");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        btnAddGPU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/32378_add_plus_icon.png"))); // NOI18N
        btnAddGPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddGPUActionPerformed(evt);
            }
        });
        jPanel3.add(btnAddGPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 140, 30));

        jPanel3.add(cboPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 140, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("PIN");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, -1, -1));

        btnAddPin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/32378_add_plus_icon.png"))); // NOI18N
        btnAddPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPinActionPerformed(evt);
            }
        });
        jPanel3.add(btnAddPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 140, 30));

        cboOCung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboOCungActionPerformed(evt);
            }
        });
        jPanel3.add(cboOCung, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 140, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Ổ CỨNG");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, -1, -1));

        btnAddOCung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/32378_add_plus_icon.png"))); // NOI18N
        btnAddOCung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddOCungActionPerformed(evt);
            }
        });
        jPanel3.add(btnAddOCung, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 140, 30));

        cboKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKichThuocActionPerformed(evt);
            }
        });
        jPanel3.add(cboKichThuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 140, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("KÍCH THƯỚC");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, -1, -1));

        btnAddKichThuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/32378_add_plus_icon.png"))); // NOI18N
        btnAddKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKichThuocActionPerformed(evt);
            }
        });
        jPanel3.add(btnAddKichThuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, 140, 30));

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/32378_add_plus_icon.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel3.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, 90, 50));

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/1582587_arrow_refresh_reload_rotate icon_icon.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel3.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 70, 90, 50));

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/11211468_eraser_clear_remove_tool_rubber_icon.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel3.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 150, 90, 50));

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/5049209_bin_delete_remove_trash_icon.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, 90, 50));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Lọc giá");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel4.setText("->");

        tblQuanLySP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Hình ảnh", "CPU", "GPU", "Loại ổ cứng", "Ram", "Kích thước", "Pin", "Giá nhập", "Giá xuất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuanLySP.getTableHeader().setReorderingAllowed(false);
        tblQuanLySP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLySPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQuanLySP);
        if (tblQuanLySP.getColumnModel().getColumnCount() > 0) {
            tblQuanLySP.getColumnModel().getColumn(0).setResizable(false);
            tblQuanLySP.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblQuanLySP.getColumnModel().getColumn(1).setMinWidth(130);
            tblQuanLySP.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblQuanLySP.getColumnModel().getColumn(9).setResizable(false);
            tblQuanLySP.getColumnModel().getColumn(10).setResizable(false);
        }

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thông Tin Sản Phẩm", jPanel4);

        tblQLyImei.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Giá Nhập", "Giá Bán", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQLyImei.getTableHeader().setReorderingAllowed(false);
        tblQLyImei.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLyImeiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQLyImei);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("THÊM IMEI CHO SẢN PHẨM");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Mã Sản Phẩm");

        cboMaSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Mã IMEI");

        btnAddImei.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/32378_add_plus_icon.png"))); // NOI18N
        btnAddImei.setText("Thêm Imei");
        btnAddImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddImeiActionPerformed(evt);
            }
        });

        btnClearImei.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/11211468_eraser_clear_remove_tool_rubber_icon.png"))); // NOI18N
        btnClearImei.setText("Làm mới");
        btnClearImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearImeiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(98, 98, 98))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaImei)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(btnAddImei)
                .addGap(56, 56, 56)
                .addComponent(btnClearImei)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaImei, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddImei, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearImei, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblImeiCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Imei"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblImeiCT.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblImeiCT);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Imei Sản Phẩm Chi Tiết");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Imei Sản Phẩm", jPanel5);

        tblQuanLySPDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Hình ảnh", "CPU", "GPU", "Loại ổ cứng", "Ram", "Kích thước", "Pin", "Số lượng", "Giá nhập", "Giá xuất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuanLySPDaXoa.getTableHeader().setReorderingAllowed(false);
        tblQuanLySPDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLySPDaXoaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblQuanLySPDaXoa);
        if (tblQuanLySPDaXoa.getColumnModel().getColumnCount() > 0) {
            tblQuanLySPDaXoa.getColumnModel().getColumn(0).setResizable(false);
            tblQuanLySPDaXoa.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblQuanLySPDaXoa.getColumnModel().getColumn(1).setMinWidth(130);
            tblQuanLySPDaXoa.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblQuanLySPDaXoa.getColumnModel().getColumn(9).setHeaderValue("Số lượng");
            tblQuanLySPDaXoa.getColumnModel().getColumn(10).setResizable(false);
            tblQuanLySPDaXoa.getColumnModel().getColumn(11).setResizable(false);
        }

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/1582587_arrow_refresh_reload_rotate icon_icon.png"))); // NOI18N
        jButton1.setText("Khôi phục");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1039, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(470, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm Đã Xóa", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboRamActionPerformed

    private void cboOCungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboOCungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboOCungActionPerformed

    private void cboKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKichThuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboKichThuocActionPerformed

    private void btnAddCPUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddCPUMouseClicked

    }//GEN-LAST:event_btnAddCPUMouseClicked

    private void btnAddCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCPUActionPerformed
        CpuView cpu = new CpuView(this);
        cpu.setVisible(true);
    }//GEN-LAST:event_btnAddCPUActionPerformed

    private void btnAddGPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddGPUActionPerformed
        GpuView gpu = new GpuView(this);
        gpu.setVisible(true);
    }//GEN-LAST:event_btnAddGPUActionPerformed

    private void btnAddRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRamActionPerformed
        RamView ram = new RamView(this);
        ram.setVisible(true);
    }//GEN-LAST:event_btnAddRamActionPerformed

    private void btnAddOCungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddOCungActionPerformed
        OCungView oc = new OCungView(this);
        oc.setVisible(true);
    }//GEN-LAST:event_btnAddOCungActionPerformed

    private void btnAddKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKichThuocActionPerformed
        ManHinhView mh = new ManHinhView(this);
        mh.setVisible(true);
    }//GEN-LAST:event_btnAddKichThuocActionPerformed

    private void btnAddPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPinActionPerformed
        PinView p = new PinView(this);
        p.setVisible(true);
    }//GEN-LAST:event_btnAddPinActionPerformed

    private void tblQuanLySPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLySPMouseClicked
        this.detail(tblQuanLySP.getSelectedRow());
    }//GEN-LAST:event_tblQuanLySPMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtTenSP.setText("");
        txtGiaBan.setText("");
        txtGiaNhap.setText("");
        cboCPU.setSelectedItem("");
        cboGPU.setSelectedItem("");
        cboKichThuoc.setSelectedItem("");
        cboPin.setSelectedItem("");
        cboOCung.setSelectedItem("");
        cboRam.setSelectedItem("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtTenSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên SP không dc để trống");
            txtTenSP.requestFocus();
            return;
        }
        if (txtGiaNhap.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá nhập không dc để trống");
            txtGiaNhap.requestFocus();
            return;
        }
        if (txtGiaBan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá bán không dc để trống");
            txtGiaBan.requestFocus();
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thêm sản phẩm này chưa ?");
        if (chon == 0) {
            if (sanphamRepository.add(this.getFormDataSanPham())) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                this.showDataTableSanPham(sanphamRepository.getAll(getFormSearch()));
                this.showDataTableImei(sanphamRepository.getAll(getFormSearch()));
                this.showComboboxSanPham();
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddImeiActionPerformed
        if (txtMaImei.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Imei không được để trống");
            txtMaImei.requestFocus();
            return;
        }
        for (Imei imei : imeiRepository.getAll()) {
            if (txtMaImei.getText().equals(imei.getMaImei())) {
                JOptionPane.showMessageDialog(this, "Mã Imei này đã tồn tại !");
                txtMaImei.requestFocus();
                return;
            }
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thêm Imei này chưa ?");
        if (chon == 0) {
            if (imeiRepository.add(this.getFormDataImei(), this.getIdSpByMa())) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                this.ShowDataTableImeiChiTiet(title);
            }
        }

    }//GEN-LAST:event_btnAddImeiActionPerformed

    private void btnClearImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearImeiActionPerformed
        txtMaImei.setText("");
    }//GEN-LAST:event_btnClearImeiActionPerformed

    private void tblQLyImeiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLyImeiMouseClicked
        int index = tblQLyImei.getSelectedRow();
        this.ShowDataTableImeiChiTiet(sanphamRepository.getAll(getFormSearch()).get(index).getMaSanPham());
    }//GEN-LAST:event_tblQLyImeiMouseClicked

    private void tblQuanLySPDaXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLySPDaXoaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblQuanLySPDaXoaMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int index = tblQuanLySP.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Laptop muốn xóa");
        } else {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa sản phẩm này chưa ?");
            if (chon == 0) {
                if (sanphamRepository.delete(sanphamRepository.getAll(getFormSearch()).get(index).getIdSanPham())) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công !");
                    txtTenSP.setText("");
                    txtGiaBan.setText("");
                    txtGiaNhap.setText("");
                    this.showDataTableSanPham(sanphamRepository.getAll(getFormSearch()));
                    this.showDataTableImei(sanphamRepository.getAll(getFormSearch()));
                    this.showComboboxSanPham();
                    //this.showDataTableSanPhamDaXoa(sanphamRepository.getAllDelete());
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int index = tblQuanLySP.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Laptop muốn sửa");
        } else {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn sửa sản phẩm này chưa ?");
            if (chon == 0) {
                if (sanphamRepository.update(this.getFormDataSanPham(), sanphamRepository.getAll(getFormSearch()).get(index).getIdSanPham())) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    this.showDataTableSanPham(sanphamRepository.getAll(getFormSearch()));
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.showDataTableSanPham(sanphamRepository.getAll(getFormSearch()));
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddCPU;
    private javax.swing.JButton btnAddGPU;
    private javax.swing.JButton btnAddImei;
    private javax.swing.JButton btnAddKichThuoc;
    private javax.swing.JButton btnAddOCung;
    private javax.swing.JButton btnAddPin;
    private javax.swing.JButton btnAddRam;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearImei;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboCPU;
    private javax.swing.JComboBox<String> cboGPU;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboMaSP;
    private javax.swing.JComboBox<String> cboOCung;
    private javax.swing.JComboBox<String> cboPin;
    private javax.swing.JComboBox<String> cboRam;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tblImeiCT;
    private javax.swing.JTable tblQLyImei;
    private javax.swing.JTable tblQuanLySP;
    private javax.swing.JTable tblQuanLySPDaXoa;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaImei;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
