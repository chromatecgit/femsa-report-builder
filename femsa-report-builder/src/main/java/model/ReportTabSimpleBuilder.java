package model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import interfaces.ReportTabBuilder;
import utils.MyLogPrinter;

public class ReportTabSimpleBuilder implements ReportTabBuilder {
	
	private ReportTab tab;
	private Map<String, String> tableHeaders;
	private String currentConcat;
	private Integer currentLineIndex;
	private List<ReportCell> cells;
	
	public ReportTabSimpleBuilder() {
		this.cells = new LinkedList<>();
		this.tab = new ReportTab();
		this.tableHeaders = new LinkedHashMap<>();
	}

	@Override
	public ReportTab build() {
		MyLogPrinter.printBuiltMessage("ReportTabSimpleBuilder_orphan_cells");
		this.tab.setTableHeaders(tableHeaders);
		return this.tab;
	}

	@Override
	public void addCell(final ReportCell cell) {
		this.currentLineIndex = cell.getLineNumber();
		if (this.currentLineIndex == 1) {
			this.tableHeaders.put(cell.getColumnLetter(), cell.getValue());
		} else if (this.tableHeaders.get(cell.getColumnLetter()).equalsIgnoreCase("CONCAT")) {
			this.currentConcat = cell.getValue();
		} else {
			this.addAndReset(cell, cell.getColumnLetter());
		}
	}

	@Override
	public void addAndReset(final ReportCell cell, String correspondingHeader) {
		String tableHeader = this.tableHeaders.get(correspondingHeader);
		cell.setColumnName(tableHeader);
		if (cell.getLineNumber() == this.currentLineIndex) {
			this.cells.add(cell);
		} else {
			this.tab.getRows().merge(this.currentConcat, this.cells, (x,y) -> {
				x.addAll(y);
				return x;
			});
		}
		
	}

	@Override
	public void addNumberOfRows(int rows) {
		this.tab.setNumberOfRows(rows);
	}

	@Override
	public void addNumberOfColumns(int columns) {
		this.tab.setNumberOfColumns(columns);
	}

	@Override
	public void addDocumentName(String fileName) {
		this.tab.setFileName(fileName);
	}

	@Override
	public void addTabName(String tabName) {
		this.tab.setTabName(tabName);
	}
}
