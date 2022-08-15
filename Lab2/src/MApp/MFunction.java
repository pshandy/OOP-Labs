package MApp;

public enum MFunction {
	MMOVE("Переместить"),
	MDELETE("Удалить"),
	MCHANGESIDES("Изменить стороны");
	
	private String title;
	
	MFunction(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	/*
	 * Alternative names array
	 */
	
	private static String[] altNames = new String[MFunction.values().length];
	
	static {
		int i = 0;
		for (MFunction f: MFunction.values()) {
			altNames[i] = f.getTitle();
			i++;
		}
	}
	
	public static MFunction getEnumRepresentation(String name) {
		for (MFunction f: MFunction.values()) {
			if (f.getTitle().equals(name))
				return f;
		}
		return null;
	}
	
	public static String[] getAltNames() {
		return altNames;
	}
}
