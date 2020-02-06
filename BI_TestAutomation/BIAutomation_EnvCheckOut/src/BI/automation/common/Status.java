package BI.automation.common;

public class Status {
	private String key;
	private String defaultkey;
	
	private String entrydatetime;
	private String lastupdtdatetime;
	
	private String result="FAILED";
	private String comments;
	private String exception;
	private String screenshotpath;
	private String filterkey;
	private long elapsedtimeinseconds;
	private String removewordsfromexception="For documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html";
	
	
	
	public long getElapsedTimeInSeconds() {
		return elapsedtimeinseconds;
	}
	public void setElapsedTimeInSeconds(long elapsedtimeinseconds) {
		this.elapsedtimeinseconds = elapsedtimeinseconds;
	}
	public String getFilterkey() {
		return filterkey;
	}
	public void setFilterkey(String filterkey) {
		this.filterkey = filterkey;
	}
	public String getKey() {
		return this.key;
	}
	public void setKey(String key) {
		this.key = key;
	}


	public String getEntryDateTime() {
		return this.entrydatetime;
	}
	public void setEntryDateTime(String entrydatetime) {
		this.entrydatetime = entrydatetime;
	}
	
	public String getLastUpdatedDateTime() {
		return this.lastupdtdatetime;
	}
	public void setLastUpdatedDateTime(String lastupdtdatetime) {
		this.lastupdtdatetime = lastupdtdatetime;
	}
	public String getResult() {
		return this.result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getComments() {
		return this.comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getException() {
		return this.exception;
	}
	public void setException(String exception) {
		
		this.exception = exception.replace(removewordsfromexception, " ");
	}
	public String getScreenshotPath() {
		return this.screenshotpath;
	}
	public void setScreenshotPath(String screenshotpath) {
		this.screenshotpath = screenshotpath;
	}
	public String getDefaultkey() {
		return defaultkey;
	}
	public void setDefaultkey(String defaultkey) {
		this.defaultkey = defaultkey;
	}
		
	
}


