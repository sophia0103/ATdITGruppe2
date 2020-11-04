package controller;

import view.View;

/**
 * This Interface provides the method startProcess to start a logical Process that e.g. happens when a button on a view is pressed.
 * The views call method startProcess of controller classes in order to give the controller control of the process behind that button.
 * The controller then handles the process.
 */

public interface Controller {

	/**
	 * This method starts the logical process implemented in the controller.
	 * @param view The view the controller is called to do the process for.
	 */
	void startProcess(View view);
	

	
}
