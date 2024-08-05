package com.resource.resource.api.upgrade;

import org.springframework.web.client.RestTemplate;

public class BaseApiUpgradeWWW {
	public final String urlRestApi = "";
	public void ok(){
		RestTemplate rest = new  RestTemplate();
		rest.getForEntity(urlRestApi, null);
	}
}
