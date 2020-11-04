package controller;


/**
 * This interface extends the Controller interface with the method createModel, because all DataController have to handle input data
 * and form it into a model. DataController are called by DataViews.
 */

public interface DataController extends Controller {

	/**
	 * This method converts validated user input into a model instance of a generic sort
	 * @param inputArray The raw user input without any validation
	 * @return <T>T
	 */
	public <T>T createModel(String[] inputArray);
}
