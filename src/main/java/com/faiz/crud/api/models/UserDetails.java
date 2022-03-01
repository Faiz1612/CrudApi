package com.faiz.crud.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserDetailsTable")
public class UserDetails {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int userId;		
		@Column(name = "name", nullable = false)
		private String userName;
		@Column(name = "technology", nullable = false)
		private String userTechnology;
		
		public UserDetails() {
			super();
		}
		
		public UserDetails(int userId, String userName, String userTechnology) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.userTechnology = userTechnology;
		}

		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserTechnology() {
			return userTechnology;
		}
		public void setUserTechnology(String userTechnology) {
			this.userTechnology = userTechnology;
		}
		
		
}
