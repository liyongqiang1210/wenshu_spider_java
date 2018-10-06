package com.spider.entity;

/**
 * <p>
 * Title: IP
 * </p>
 * <p>
 * Description: ip实体类
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年10月6日 下午2:52:16
 */
public class IP {

	private int id;
	private String ipAddress;
	private String ipPort;
	private String ipType;
	private String ipLocation;
	private String ipVerifyTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpPort() {
		return ipPort;
	}

	public void setIpPort(String ipPort) {
		this.ipPort = ipPort;
	}

	public String getIpType() {
		return ipType;
	}

	public void setIpType(String ipType) {
		this.ipType = ipType;
	}

	public String getIpLocation() {
		return ipLocation;
	}

	public void setIpLocation(String ipLocation) {
		this.ipLocation = ipLocation;
	}

	public String getIpVerifyTime() {
		return ipVerifyTime;
	}

	public void setIpVerifyTime(String ipVerifyTime) {
		this.ipVerifyTime = ipVerifyTime;
	}

	@Override
	public String toString() {
		return "IP [id=" + id + ", ipAddress=" + ipAddress + ", ipPort=" + ipPort + ", ipType=" + ipType
				+ ", ipLocation=" + ipLocation + ", ipVerifyTime=" + ipVerifyTime + "]";
	}

}
