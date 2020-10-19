package model;

public class ChangePasswordCredentials {
	private String oldPassword;
	private String newPassword;
	private String newPasswordRepeat;

	public ChangePasswordCredentials(String oldPassword, String newPassword, String newPasswordRepeat) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.newPasswordRepeat = newPasswordRepeat;
	}

	public String getOldPassword() {
		return oldPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}

	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}
}
