package model;

/**
 * @author Sophia
 * Represents the Credentials inserted by the user when changing the password.
 */
public class ChangePasswordCredentials {
	private String oldPassword;
	private String newPassword;
	private String newPasswordRepeat;

	public ChangePasswordCredentials(String oldPassword, String newPassword, String newPasswordRepeat) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.newPasswordRepeat = newPasswordRepeat;
	}

	/**
	 * @return Returns the old password.
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @return Returns the new password.
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @return Returns the repeated new password.
	 */
	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}
}
