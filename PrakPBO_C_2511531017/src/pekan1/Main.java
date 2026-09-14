package pekan1;

import java.util.Scanner;
import java.util.*;

public class Main {
	public static void main(String[] endy) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while(isRunning) {
			System.out.println("\nMenu Utama: ");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Akun");
			System.out.println("0. Keluar");
			System.out.println("Pilih menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine();
			
			switch (pilihan) {
			case 1:
				System.out.println("Masukan No Rekening: ");
				String no = input.nextLine();
				boolean akun = false;
				for (Rekening r : daftarRekening) {
					if (r.nomorRekening.equalsIgnoreCase(no)) {
						akun = true;
						break;
					}
				}
				if(akun) {
					System.out.println("Error: Nomor rekening " + no + "sudah terdaftar!");
					break;
				}
				System.out.println("Masukan Nama Pemilik: ");
				String nama = input.nextLine();
				System.out.print("Masukan Saldo Awal: ");
				double saldo = input.nextDouble();
				input.nextLine();
				Rekening rekeningBaru = new Rekening(no, nama, saldo);
				daftarRekening.add(rekeningBaru);
				
				akunAktif = rekeningBaru;
				break;
				
			case 2:
				if (akunAktif == null) {
					System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
				}else {
					System.out.println("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor);
				}
				break;
				
			case 3:
				if (akunAktif == null) {
					System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
				}else {
					System.out.println("Masukkan nominal tarik: ");
					double tarik = input.nextDouble();
					akunAktif.tarikTunai(tarik);
				}
				break;
				
			case 4:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum buka rekening!");
				}else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 5:
				if (daftarRekening.isEmpty()) {
					System.out.println("Error: Belum ada rekening yang terdaftar!");
				} else {
					System.out.println("Masukkan nomor rekening yang ingin diaktifkan: ");
					String nomorDicari = input.nextLine();
 
					Rekening ditemukan = null;
					for (Rekening r : daftarRekening) {
						if (r.nomorRekening.equalsIgnoreCase(nomorDicari)) {
							ditemukan = r;
							break;
						}
					}
 
					if (ditemukan != null) {
						akunAktif = ditemukan;
						System.out.println("Berhasil berpindah ke rekening atas nama " + akunAktif.namaPemilik);
					} else {
						System.out.println("Error: Nomor rekening '" + nomorDicari + "' tidak ditemukan!");
					}
				}
				break;	
				
				
			case 0:
				isRunning = false;
				System.out.println("Sistem ditutup. Terimakasih!");
				break;
				
			default:
				System.out.println("Pilihan tidak valid!");
			}
			
		}
		input.close();
	}

}
