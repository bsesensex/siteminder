package com.siteminder.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



public class JsonUsers 
{

	private Collection<JsonUser> JsonUser;

	public Collection<JsonUser> getUsers() {
		return JsonUser;
	}

	public void setUsers(Collection<JsonUser> users) {
		this.JsonUser = users;
	}
}
