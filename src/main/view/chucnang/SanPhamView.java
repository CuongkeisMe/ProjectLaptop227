package main.view.chucnang;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import main.entity.Cpu;
import main.entity.Gpu;
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
import main.response.SanPhamResponse;
import main.view.sanphamchitiet.CpuView;
import main.view.sanphamchitiet.GpuView;
import main.view.sanphamchitiet.ImeiView;
import main.view.sanphamchitiet.ManHinhView;
import main.view.sanphamchitiet.OCungView;
import main.view.sanphamchitiet.PinView;
import main.view.sanphamchitiet.RamView;

public class SanPhamView extends javax.swing.JInternalFrame {
    private DefaultTableModel dtm;
    
    private SanPhamRepository sanphamRepo;
    
    private DefaultComboBoxModel cpuDcbm;
    private DefaultComboBoxModel gpuDcbm;
    private DefaultComboBoxModel ramDcbm;
    private DefaultComboBoxModel manhinhDcbm;
    private DefaultComboBoxModel pinDcbm;
    private DefaultComboBoxModel ocungDcbm;
    private DefaultComboBoxModel imeiDcbm;
    
    private CpuRepository cpuRepository;
    private GpuRepository gpuRepository;
    private RamRepository ramRepository;
    private ManHinhRepository manhinhRepository;
    private PinRepository pinRepository;
    private OCungRepository ocungRepository;
    private ImeiRepository imeiRepository;

    public SanPhamView() {
        initComponents();
        this.cauhinhForm();
        this.cboDinhDang();
        this.repositoryDinhDang();
        this.showComboboxImei();
        this.showComboboxCPU();
        this.showComboboxGPU();
        this.showComboboxManHinh();
        this.showComboboxOCung();
        this.showComboboxPin();
        this.showComboboxRam();
        dtm = (DefaultTableModel)tblQuanLySP.getModel();
        sanphamRepo = new SanPhamRepository();
        this.showDataTable(sanphamRepo.getAll());
    }
    
    public void cauhinhForm(){
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }
    
    private void cboDinhDang(){
        cpuDcbm = (DefaultComboBoxModel) cboCPU.getModel();
        gpuDcbm = (DefaultComboBoxModel) cboGPU.getModel();
        ramDcbm = (DefaultComboBoxModel) cboRam.getModel();
        manhinhDcbm = (DefaultComboBoxModel) cboKichThuoc.getModel();
        pinDcbm = (DefaultComboBoxModel) cboPin.getModel();
        ocungDcbm = (DefaultComboBoxModel) cboOCung.getModel();
        imeiDcbm = (DefaultComboBoxModel) cboImei.getModel();
    }
    
    private void repositoryDinhDang(){
        cpuRepository = new CpuRepository();
        gpuRepository = new GpuRepository();
        ramRepository = new RamRepository();
        manhinhRepository = new ManHinhRepository();
        pinRepository = new PinRepository();
        ocungRepository = new OCungRepository();
        imeiRepository = new ImeiRepository();
    }
    
    public void showComboboxImei(){
        imeiDcbm.removeAllElements();
        imeiDcbm.addElement("");
        for(main.entity.Imei imei : imeiRepository.getAll()){
            imeiDcbm.addElement(imei.getMaImei());
        }
    }
    
    public void showComboboxCPU(){
        cpuDcbm.removeAllElements();
        for(Cpu cpu : cpuRepository.getAll()){
            cpuDcbm.addElement(cpu.getTenCPU());
        }
    }
    
    public void showComboboxGPU(){
        gpuDcbm.removeAllElements();
        for(Gpu gpu : gpuRepository.getAll()){
            gpuDcbm.addElement(gpu.getTenGPU());
        }
    }
    
    public void showComboboxRam(){
        ramDcbm.removeAllElements();
        for(Ram ram : ramRepository.getAll()){
            ramDcbm.addElement(ram.getDungLuongRam());
        }
    }
    
    public void showComboboxManHinh(){
        manhinhDcbm.removeAllElements();
        for(ManHinh mh : manhinhRepository.getAll()){
            manhinhDcbm.addElement(mh.getKichThuoc());
        }
    }
    
    public void showComboboxPin(){
        pinDcbm.removeAllElements();
        for(Pin pin : pinRepository.getAll()){
            pinDcbm.addElement(pin.getDungLuongPin());
        }
    }
    
    public void showComboboxOCung(){
        ocungDcbm.removeAllElements();
        for(OCung oc : ocungRepository.getAll()){
            ocungDcbm.addElement(oc.getLoaiOCung());
        }
    }
    
    public void showDataTable(ArrayList<SanPhamResponse> list){
        dtm.setRowCount(0);
        list.forEach(x -> dtm.addRow(new Object[]{
            x.getMaSanPham(), x.getTenSanPham(), x.getHinhAnh(), x.getTenCPU(), x.getTenGPU(), 
            x.getLoaiOCung(), x.getDungLuongRam(), x.getKichThuoc(), x.getDungLuongPin(), x.getSoLuong(), 
            x.getGiaNhap(), x.getGiaBan()
        }));
    }   

    private void detail(int index){
        SanPhamResponse spReponse = sanphamRepo.getAll().get(index);
        txtTenSP.setText(spReponse.getTenCPU());
        cboImei.setSelectedItem(spReponse.getMaImei());
        cboCPU.setSelectedItem(spReponse.getTenCPU());
        cboGPU.setSelectedItem(spReponse.getTenGPU());
        cboOCung.setSelectedItem(spReponse.getLoaiOCung());
        cboRam.setSelectedItem(spReponse.getDungLuongRam());
        cboKichThuoc.setSelectedItem(spReponse.getKichThuoc());
        cboPin.setSelectedItem(spReponse.getDungLuongPin());
        txtSoLuong.setText(String.valueOf(spReponse.getSoLuong()));
        txtGiaNhap.setText(String.valueOf(spReponse.getGiaNhap()));
        txtGiaBan.setText(String.valueOf(spReponse.getGiaBan()));
   }
    
    private SanPhamResponse getFormData(){
        return SanPhamResponse.builder()
                .TenSanPham(txtTenSP.getText())
                .TenCPU(cboCPU.getSelectedItem().toString())
                .TenGPU(cboGPU.getSelectedItem().toString())
                .LoaiOCung(cboOCung.getSelectedItem().toString())
                .DungLuongRam(cboRam.getSelectedItem().toString())
                .KichThuoc(cboKichThuoc.getSelectedItem().toString())
                .DungLuongPin(cboPin.getSelectedItem().toString())
                .SoLuong(Integer.valueOf(txtSoLuong.getText()))
                .GiaNhap(Integer.valueOf(txtGiaBan.getText()))
                .GiaBan(Integer.valueOf(txtGiaBan.getText()))
                .build();
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuanLySP = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cboImei = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
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

        setMaximizable(true);
        setFrameIcon(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

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
                "Mã SP", "Tên SP", "Hình ảnh", "CPU", "GPU", "Loại ổ cứng", "Ram", "Kích thước", "Pin", "Số lượng", "Giá nhập", "Giá xuất", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblQuanLySP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLySPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQuanLySP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 50, Short.MAX_VALUE)))
                .addGap(2, 2, 2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Chọn hình ảnh");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel16.setText("Tên SP");

        txtTenSP.setMinimumSize(new java.awt.Dimension(64, 30));
        txtTenSP.setPreferredSize(new java.awt.Dimension(64, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel17.setText("Imei");

        cboImei.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboImei.setMinimumSize(new java.awt.Dimension(72, 30));
        cboImei.setPreferredSize(new java.awt.Dimension(72, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/32378_add_plus_icon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel18.setText("Số lượng");

        txtSoLuong.setEditable(false);
        txtSoLuong.setActionCommand("<Not Set>");
        txtSoLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtSoLuong.setEnabled(false);
        txtSoLuong.setMinimumSize(new java.awt.Dimension(64, 30));
        txtSoLuong.setPreferredSize(new java.awt.Dimension(64, 30));

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(10, 10, 10)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboImei, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(11, 11, 11)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(19, 19, 19)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(cboImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jPanel3.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 70, 90, 50));

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/1582587_arrow_refresh_reload_rotate icon_icon.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        jPanel3.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 90, 50));

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/11211468_eraser_clear_remove_tool_rubber_icon.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel3.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 150, 90, 50));

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/5049209_bin_delete_remove_trash_icon.png"))); // NOI18N
        btnDelete.setText("Xóa");
        jPanel3.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, 90, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ImeiView imei = new ImeiView();
        imei.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblQuanLySPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLySPMouseClicked
        this.detail(tblQuanLySP.getSelectedRow());
        txtSoLuong.setEnabled(false);
    }//GEN-LAST:event_tblQuanLySPMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtTenSP.setText("");
        txtSoLuong.setText("");
        txtGiaBan.setText("");
        txtGiaNhap.setText("");
        cboImei.setSelectedItem("");
        cboCPU.setSelectedItem("");
        cboGPU.setSelectedItem("");
        cboKichThuoc.setSelectedItem("");
        cboPin.setSelectedItem("");
        cboOCung.setSelectedItem("");
        cboRam.setSelectedItem("");
    }//GEN-LAST:event_btnClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddCPU;
    private javax.swing.JButton btnAddGPU;
    private javax.swing.JButton btnAddKichThuoc;
    private javax.swing.JButton btnAddOCung;
    private javax.swing.JButton btnAddPin;
    private javax.swing.JButton btnAddRam;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboCPU;
    private javax.swing.JComboBox<String> cboGPU;
    private javax.swing.JComboBox<String> cboImei;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboOCung;
    private javax.swing.JComboBox<String> cboPin;
    private javax.swing.JComboBox<String> cboRam;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tblQuanLySP;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
