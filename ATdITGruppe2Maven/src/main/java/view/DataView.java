package view;

/**
 * @author Luis
 * Extends the view interface with the method getData. DataViews have user input and pass the input on to a DataController.
 */
public interface DataView extends View{

	/**
	 * @return String[] Returns raw user input without transforming it or validating it.
	 */
	public String[] getData();
	
}
