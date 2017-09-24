package model;

import enums.IndentationEnum;
import interfaces.Indentable;

public class ReportCell implements Indentable {
	private String columnName;
	private String value;
	private String address;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getLineNumber() {
		return Integer.parseInt(this.address.replaceAll("\\D+", ""));
	}
	
	public String getColumnLetter() {
		return this.address.replaceAll("\\d+", "");
	}

	@Override
	public String toString() {
		return this.getHierarchy().getIndentationEntity() + "ReportCell [columnName=" + columnName + ", value=" + value
				+ ", address=" + address + "]";
	}

	@Override
	public IndentationEnum getHierarchy() {
		return IndentationEnum.LEVEL_3;
	}

}
