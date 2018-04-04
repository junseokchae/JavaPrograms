
public class Foe extends Person {
	private int idNo;

	public int getIdNo() {
		return idNo;
	}

	public void setIdNo(int idNo) {
		this.idNo = idNo;
	}

	public Foe(String personName, String gender, int level, int idNo) {
		super(personName,gender,level);
		this.idNo = idNo;
	}

	

}
