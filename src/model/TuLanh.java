package model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class TuLanh{

	@Id // PRIMARY KEY
	@GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY (1,1)
	private Integer id;

	@Column(nullable = false)
	private String hang; // thông tin đặc trưng

	@Column(nullable = false)
	private String ten;

	@Column(nullable = false)
	private Boolean phuongPhapLamDong; // thông tin đặc trưng

	@Column(nullable = false)
	private Integer dungTich; // thông tin đặc trưng

	@Column(nullable = false)
	private Integer soBuong; // thông tin đặc trưng

	@Column(nullable = false)
	private String moTa;

	@Column(nullable = false)
	private BigDecimal gia;

	@Column(nullable = false)
	private Integer soLuong;

	@Column(nullable = false)
	private String nguoiTao;

	@Column(nullable = false)
	private Date thoiDiemTao;

	@Column(nullable = false)
	private String nguoiThayDoiCuoi;

	@Column(nullable = false)
	private Date thoiDiemThayDoiCuoi;

	@Column(nullable = false)
	private Boolean trangThai;

	public TuLanh() {
	}

	public TuLanh(Integer id, String hang, String ten, Boolean phuongPhapLamDong, Integer dungTich, Integer soBuong,
			String moTa, BigDecimal gia, Integer soLuong, String nguoiTao, Date thoiDiemTao, String nguoiThayDoiCuoi,
			Date thoiDiemThayDoiCuoi, Boolean trangThai) {
		super();
		this.id = id;
		this.hang = hang;
		this.ten = ten;
		this.phuongPhapLamDong = phuongPhapLamDong;
		this.dungTich = dungTich;
		this.soBuong = soBuong;
		this.moTa = moTa;
		this.gia = gia;
		this.soLuong = soLuong;
		this.nguoiTao = nguoiTao;
		this.thoiDiemTao = thoiDiemTao;
		this.nguoiThayDoiCuoi = nguoiThayDoiCuoi;
		this.thoiDiemThayDoiCuoi = thoiDiemThayDoiCuoi;
		this.trangThai = trangThai;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHang() {
		return hang;
	}

	public void setHang(String hang) {
		this.hang = hang;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Boolean getPhuongPhapLamDong() {
		return phuongPhapLamDong;
	}

	public void setPhuongPhapLamDong(Boolean phuongPhapLamDong) {
		this.phuongPhapLamDong = phuongPhapLamDong;
	}

	public Integer getDungTich() {
		return dungTich;
	}

	public void setDungTich(Integer dungTich) {
		this.dungTich = dungTich;
	}

	public Integer getSoBuong() {
		return soBuong;
	}

	public void setSoBuong(Integer soBuong) {
		this.soBuong = soBuong;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public BigDecimal getGia() {
		return gia;
	}

	public void setGia(BigDecimal gia) {
		this.gia = gia;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public String getNguoiTao() {
		return nguoiTao;
	}

	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}

	public Date getThoiDiemTao() {
		return thoiDiemTao;
	}

	public void setThoiDiemTao(Date thoiDiemTao) {
		this.thoiDiemTao = thoiDiemTao;
	}

	public String getNguoiThayDoiCuoi() {
		return nguoiThayDoiCuoi;
	}

	public void setNguoiThayDoiCuoi(String nguoiThayDoiCuoi) {
		this.nguoiThayDoiCuoi = nguoiThayDoiCuoi;
	}

	public Date getThoiDiemThayDoiCuoi() {
		return thoiDiemThayDoiCuoi;
	}

	public void setThoiDiemThayDoiCuoi(Date thoiDiemThayDoiCuoi) {
		this.thoiDiemThayDoiCuoi = thoiDiemThayDoiCuoi;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

}
