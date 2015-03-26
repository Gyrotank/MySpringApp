package ua.epam.rd;

public class DesktopView implements Viewable {

	int defaultId;
	
	public DesktopView(int defaultId) {
		this.defaultId = defaultId;
	}
	
	public void display(String s) {
		System.out.println("Desktop view: " + s + " - " + defaultId);

	}

}
