package pekan3;
import java.util.ArrayList;
public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	private String pin;
	
	ArrayList<Transaksi> riwayatTransaksi;
	
	public static boolean isValidPin(String pin) {
		if (pin == null || pin.length() != 6) return false;
		for (int i = 0; i < pin.length(); i++) {
			if (!Character.isDigit(pin.charAt(i))) return false;
		}

		for (int i = 0; i < pin.length(); i++) {
			for (int j = i + 1; j < pin.length(); j++) {
				if (pin.charAt(i) == pin.charAt(j)) {
					return false;
				}
			}
		}

		for (int i = 0; i < pin.length() - 1; i++) {
			int selisih = Math.abs(pin.charAt(i) - pin.charAt(i + 1));
			if (selisih == 1) {
				return false;
			}
		}

		return true;
	}
	
	public Rekening (String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		if (isValidPin(pinAwal)) {
			this.pin = pinAwal;
		}else {
			System.out.println("Peringatan: PIN tidak valid! Menggunakan PIN default 804927");
			this.pin = "804927";
		}
		
		this.riwayatTransaksi = new ArrayList<>();
	
		System.out.println("Rekening atas nama "+ namaPemilik + " berhasil dibuat."); 
	}
	
	public String getNomorRekening() { return nomorRekening;}
	public String getNamaPemilik() { return namaPemilik;}
	
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}
	
	public void setorTunai(double nominal) {
		if (nominal > 0 && nominal <= 500000 ) {
			saldo += nominal;
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi (idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
		}else {
			System.out.println("Gagal: nominal setor harus lebih dari 0 atau melebihi 500.000!");
		}
	}
	
public void tarikTunai (double nominalDebit) {
		
		if (nominalDebit > 10000) {
			if(saldo < nominalDebit) {
				System.out.println("saldo anda tidak cukup");
				
			}else {
			saldo -= nominalDebit;
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi (idTrx, "Debit", nominalDebit);
			riwayatTransaksi.add(trxBaru);
			System.out.println("tarik tunai Rp"+ nominalDebit + "berhasil. Saldo saat ini : Rp"+saldo);
			}
			
		}else {
			System.out.println("Gagal: Nominal tarik harus lebih dari Rp10000!");
		}
	}

public void cetakMutasi() {
	if (riwayatTransaksi.isEmpty()) {
		System.out.println(" Belum ada transaksi");
	}else {
		for (Transaksi trx : riwayatTransaksi) {
			trx.cetakDetail();
		}
		
	}
}

public void cekInformasi() {
	System.out.println("--- INFO REKENING ---");
	System.out.println("No. Rekening: "+ nomorRekening);
	System.out.println("Nama Pemilik: "+ namaPemilik);
	System.out.println("Saldo Akhir: Rp"+ saldo);
	System.out.println("-------------------");
	System.out.println("");
}



}