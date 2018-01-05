import java.io.Serializable;

public class FieldAuto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5147631025431178689L;
	private Path p;
	private float fieldWidth, fieldHeight;
	private int teamNum;
	
	public FieldAuto() {
		
	}
	
	public FieldAuto(Path p, float fieldWidth, float fieldHeight, int teamNum) {
		this.p = p;
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		this.teamNum = teamNum;
	}

	public Path getP() {
		return p;
	}

	public float getFieldWidth() {
		return fieldWidth;
	}

	public float getFieldHeight() {
		return fieldHeight;
	}

	public int getTeamNum() {
		return teamNum;
	}
	
	
	
	
	
}
