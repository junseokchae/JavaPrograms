import java.util.regex.*;
public class GPS {
	private String[] coordinates;

	public String[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String[] coordinates) {
		this.coordinates = coordinates;
	}

	public GPS(String[] coordinates) {
		super();
		this.coordinates = coordinates;
	}
	
	public static boolean validateGPS(String coordinate) {
		boolean result = false;
		Pattern pattern = Pattern.compile("([-]?\\d?\\d\\d\\.\\d\\d\\d\\d\\d\\d\\d\\s[-]?\\d?\\d\\d\\.\\d\\d\\d\\d\\d\\d\\d)");
		//?: optional(should be following the optional expression) d: digit s:whitespace
		Matcher matcher = pattern.matcher(coordinate);
		result = matcher.matches();
		return result;
	}	
}
