package MApp;

public enum MClass {
	MCIRCLE("Окружность"),
	MSQUARE("Квадрат"),
	MRECTANGLE("Прямоугольник");
	
	private String title;
	
	MClass(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	/*
	 * Alternative names array
	 */
	
	private static String[] altNames = new String[MClass.values().length];
	
	static {
		int i = 0;
		for (MClass f: MClass.values()) {
			altNames[i] = f.getTitle();
			i++;
		}
	}
	
	public static MClass getEnumRepresentation(String name) {
		for (MClass f: MClass.values()) {
			if (f.getTitle().equals(name))
				return f;
		}
		return null;
	}
	
	public static String[] getAltNames() {
		return altNames;
	}
}
