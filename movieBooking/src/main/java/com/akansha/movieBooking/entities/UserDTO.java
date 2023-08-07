package com.akansha.movieBooking.entities;

public class UserDTO {

	// it is just to take input when the api is called(username, password)
		private String userName;
		private String password;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
}
