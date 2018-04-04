
public abstract class UserSetting {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static boolean validateName(String name) {
		boolean result = false;
		boolean ifNameIncludeNumbers=false;//validate if the name includes any number
		String numbers = "1234567890";
		char testChar;
		String testName = name.toLowerCase();
		for(int i=0;i<name.length();i++)
		{
			testChar = testName.charAt(i);
			if(numbers.indexOf(testChar)>=0)
				ifNameIncludeNumbers = true;
		}
		if (ifNameIncludeNumbers)
			result = true;
		
		return result;
	} 

}
