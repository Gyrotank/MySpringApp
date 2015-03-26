package ua.epam.rd;

public class Application {
	
	private Viewable view;
	
	public Application(Viewable view) {
		this.view = view;
	}
	
	public void show() {
		view.display("Hello world!");
	}
}
