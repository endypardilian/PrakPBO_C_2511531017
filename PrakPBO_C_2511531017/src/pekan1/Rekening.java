package pekan1;
import java.text.NumberFormat;
import java.util.*;

public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	public Rekening (String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
		
	}
	
	public void setorTunai(double nominal) {
		if (nominal >= 10000) {
			saldo += nominal;
			System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 10000!");
		}
	}
	
	public void tarikTunai(double nominal) {
		if (nominal < 10000) {
			System.out.println("Gagal: Minimal tarik 10000!");
		}else if(nominal > saldo){
			System.out.println(" Gagal : Saldo anda tidak cukup!");
		}else {
			saldo -= nominal;
			System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo anda saat ini: " + saldo);
		}	
	}

	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir  : Rp" + saldo);
		System.out.println("---------------------");
	}

}
