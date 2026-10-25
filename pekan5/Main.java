package pekan5;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
	public static void main (String[]args) {
		Scanner input = new Scanner (System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama: ");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Akun Aktif");
			System.out.println("6. Cetak Mutasi (Riwayat)");
			System.out.println("7. Simulasi akhir Bulan (Khusus Tabungan");
			System.out.println("8. Transfer ke Rekening Lain");
			System.out.println("0. Keluar");
			System.out.println("Pilih Menu: ");
			  
	              
	            
			int pilihan = input.nextInt();
			input.nextLine();
			switch (pilihan) {
			
			case 1 :
			System.out.println("Masukkan No Rekening: ");
			String no = input.nextLine();
			System.out.println("Masukkan Nama Pemilik: ");
			String nama = input.nextLine();
			System.out.println("Masukkan Saldo Awal: ");
			double saldo = input.nextDouble();
			input.nextLine();
            System.out.println("Masukkan PIN (6 digit angka): ");
            String pin = input.nextLine();
            
			if(saldo < 50000) {
				System.out.println("saldo awal minimal Rp50.000");
				
			}else {	
				String pin1 = "";
				boolean pinValid = false;
				
				while(!pinValid) {
					System.out.print("Masukkan PIN (6 digit, tanpa angka berulang/berurutan): ");
					pin1 = input.nextLine();
					
					if(Rekening.isValidPin(pin1)) {
						pinValid = true;
					} else {
						System.out.println("PIN tidak valid! Syarat: 6 digit angka, tidak ada angka berulang, dan tidak ada dua angka yang berurutan (misal: 12, 54). Silakan coba lagi.\n");
					}
				}
				
			System.out.println("Pilih Produk: 1. Tabungan Umum \n 2. Giro Bisnis");
			System.out.println("Pilihan: ");
		    int produk = input.nextInt();
		    input.nextLine();

		    Rekening rekeningBaru = null;

		    if (produk == 1) {
		        System.out.print("Masukkan suku bunga (%): ");
		        double sukuBunga = input.nextDouble();
		        input.nextLine();

		        rekeningBaru = new RekeningTabungan(
		            no, nama, saldo, pin1, sukuBunga
		        );

		        System.out.println("Rekening Tabungan berhasil dibuat");

		    } else if (produk == 2) {
		        System.out.print("Masukkan batas overdraft (limit pinjaman): ");
		        double batasOverdraft = input.nextDouble();
		        input.nextLine();

		        rekeningBaru = new RekeningGiro(
		            no, nama, saldo, pin1, batasOverdraft
		        );

		        System.out.println("Rekening Giro berhasil dibuat");

		    } else {
		        System.out.println("Pilihan produk tidak valid! Pembukaan rekening dibatalkan.");
		        break;
		    }

		    daftarRekening.add(rekeningBaru);
		    akunAktif = rekeningBaru;
			}
			break;
			
			case 2: 
				if (akunAktif == null) {
					System.out.println("Error : Mohon Maaf, Anda belum Memiliki nomor rekening!");
				}else {
					System.out.println("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					
					if(setor < 10000) {
						System.out.println("saldo setor minimal Rp10.000");
						
					}else {
						
					
					akunAktif.setorTunai(setor);
					}
				}
				break;
				
			case 3:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				}else {
                    System.out.print("Masukkan PIN untuk otentikasi: ");
                    String pinInput = input.nextLine();
                    
                    if (akunAktif.otentikasi(pinInput)) {
                        System.out.println("Masukkan nominal tarik tunai: ");
                        double tarik = input.nextDouble();
                        input.nextLine();
                        akunAktif.tarikTunai(tarik);
                    } else {
                        System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                    }
                }
                break;
				
			case 4: 
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				}else { 
					akunAktif.cekInformasi();
				}
				break;
				
			case 5:
				if (daftarRekening.isEmpty()) {
					System.out.println("Belum ada rekening yang terdaftar!");
				} else {
					System.out.println("DAFTAR REKENING TERDAFTAR ");
					for (int i = 0; i < daftarRekening.size(); i++) {
						System.out.println("Pilihan Ke-" + (i + 1) + ":");
					
						daftarRekening.get(i).cekInformasi();
						System.out.println("=============");
					}
					
					System.out.print("Pilih nomor indeks rekening yang ingin diaktifkan (1 - " + daftarRekening.size() + "): ");
					int indeksPilihan = input.nextInt();
					
					if (indeksPilihan >= 1 && indeksPilihan <= daftarRekening.size()) {
						
						akunAktif = daftarRekening.get(indeksPilihan - 1);
						System.out.println("Berhasil berganti ke akun pilihan Anda!");
					} else {
						System.out.println("Nomor pilihan tidak valid!");
					}
				}
				break;
				
			case 6:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				}else {
                    System.out.print("Masukkan PIN untuk otentikasi: ");
                    String pinInput = input.nextLine();
                    
                    if (akunAktif.otentikasi(pinInput)) {
                    	akunAktif.cetakMutasi();
                    } else {
                        System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                    }
                }
                break;
                
			case 7:
                if (akunAktif == null) {
                    System.out.println("Belum ada rekening yang dibuat!");
                    break;
                }

                if (akunAktif instanceof RekeningTabungan) {
                    RekeningTabungan tabungan = (RekeningTabungan) akunAktif;
                    tabungan.tambahBungaAkhirBulan();
                } else {
                    System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
                }
                break;   
					
			case 8:
			    if (akunAktif == null) {
			        System.out.println("Error: Anda belum membuka rekening!");
			        break;
			    }

			    System.out.print("Masukkan Nomor Rekening Tujuan: ");
			    String noTujuan = input.nextLine();

			    Rekening akunTujuan = null;
			    for (Rekening r : daftarRekening) {
			        if (r.getNomorRekening().equals(noTujuan)) {
			            akunTujuan = r;
			            break;
			        }
			    }

			    if (akunTujuan == null) {
			        System.out.println("Error: Rekening tujuan tidak ditemukan!");
			    } else {
			        System.out.print("Masukkan nominal transfer: ");
			        double nominalTransfer = input.nextDouble();
			        input.nextLine();

			        System.out.print("Masukkan PIN Anda: ");
			        String pinTransfer = input.nextLine();

			        akunAktif.transfer(akunTujuan, nominalTransfer, pinTransfer);
			    }
			    break;
				
			case 0: 
				isRunning = false;
				System.out.println("Sistem ditutup. Terima kasih!");
				break;
				
				default:
					System.out.println("Pilihan tidak valid!");
			}
	}
		input.close();
	}}