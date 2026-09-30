package pekan4;

public class RekeningGiro extends Rekening {
	private double batasOverdraft;

	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
		// TODO Auto-generated constructor stub
	}
	public double getBatasOverdraft() {
		return batasOverdraft;
	}

}
