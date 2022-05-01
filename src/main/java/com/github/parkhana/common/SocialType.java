package com.github.parkhana.common;

public enum SocialType {
	GOOGLE("google");
	
	private final String ROLE_PREFIX = "ROLE_";
	private String name;
	
	SocialType(String name) {
		this.name = name;
	}
	
	public String getRoleType() { return ROLE_PREFIX + name.toUpperCase();}
	public String getValue() { return name; }
	public boolean isEquals(String autority) {
		return this.name.equals(autority);
	}
}
