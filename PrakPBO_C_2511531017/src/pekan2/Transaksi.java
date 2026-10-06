package pekan2;

public class Transaksi {
	String idTransaksi;
	String jenis;
	double nominal;
	
	public Transaksi(String idTrx,String jenis,double nominal) {
		this.idTransaksi = idTrx;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	public void cetakDetail() {
		System.out.println("ID: " + idTransaksi + " | Jenis: " + jenis + " | Nominal: Rp" + nominal);
	}
	

}
