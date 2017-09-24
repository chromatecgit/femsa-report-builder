package model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import enums.IndentationEnum;
import interfaces.Indentable;

public class ReportTab implements Indentable {

	private Map<String, String> tableHeaders;
	private String fileName;
	private String tabName;
	private int numberOfRows;
	private int numberOfColumns;
	private Map<String, List<ReportCell>> rows;

	public ReportTab() {
		super();
		this.tabName = "";
		this.rows = new TreeMap<>();
	}

	public ReportTab(final String tabName) {
		super();
		this.tabName = tabName;
		this.rows = new TreeMap<>();
	}

	public ReportTab(String fileName, String tabName) {
		super();
		this.fileName = fileName;
		this.tabName = tabName;
		this.rows = new TreeMap<>();
	}

	public ReportTab(String fileName, String tabName, int numberOfRows, int numberOfColumns,
			Map<String, List<ReportCell>> rows) {
		super();
		this.fileName = fileName;
		this.tabName = tabName;
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		this.rows = rows;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	public IndentationEnum getHierarchy() {
		return IndentationEnum.LEVEL_1;
	}

	public Map<String, List<ReportCell>> getRows() {
		return rows;
	}

	public void setRows(Map<String, List<ReportCell>> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return this.getHierarchy().getIndentationEntity() + "ReportTab [fileName=" + fileName + ", tabName=" + tabName
				+ ", numberOfRows=" + numberOfRows + ", numberOfColumns=" + numberOfColumns + ", rows=" + rows + "]";
	}

	public Map<String, String> getTableHeaders() {
		return tableHeaders;
	}

	public void setTableHeaders(Map<String, String> tableHeaders) {
		this.tableHeaders = tableHeaders;
	}

}
