package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.GlobBuilder;
import config.PathBuilder;
import config.ProjectConfiguration;
import extractors.ExcelExtractor;
import extractors.WorkbookExtractor;
import model.PathBuilderMapValue;
import model.ReportCell;
import model.ReportTab;
import model.ReportTabSimpleBuilder;
import model.TabNamesMap;

public class SpreadsheetCreator {
	
	public static void main(String[] args) {
		
		final Path destinationPath = Paths.get("D:\\teste.xlsx");
		
		final PathBuilder pathBuilder = new PathBuilder();

		final Map<String, PathBuilderMapValue> idsMap = pathBuilder.buildFilePaths(
				GlobBuilder.getDefaultGlobPattern(), ProjectConfiguration.idsPath );
		final Map<String, PathBuilderMapValue> basesMap = pathBuilder.buildFilePaths(
				GlobBuilder.getDefaultGlobPattern(), ProjectConfiguration.basesPath );
		final Map<String, PathBuilderMapValue> resultMap = pathBuilder.buildFilePaths(
				GlobBuilder.getDefaultGlobPattern(), ProjectConfiguration.resultPath );
		
		for (String fileName : idsMap.keySet()) {
			try {
				final OPCPackage pkg = OPCPackage.open(idsMap.get(fileName).getPath().toFile());
				final XSSFReader reader= new XSSFReader(pkg);
				final WorkbookExtractor workbookExtractor = new WorkbookExtractor();
				final List<TabNamesMap> tabNamesMapList =  workbookExtractor.extractSheetNamesFrom(reader.getWorkbookData());
				
				ReportTab tab = tabNamesMapList.stream().map( tabMap ->  {
					final ReportTabSimpleBuilder simpleBuilder = new ReportTabSimpleBuilder();
					simpleBuilder.addDocumentName(fileName);
					final ExcelExtractor e = new ExcelExtractor(fileName, simpleBuilder);
					e.process(reader, tabMap);
					
					return e.getProcessedTab();
				}).findFirst().orElse(new ReportTab());
				
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	private static void create(List<ReportTab> tabs, Path path) {
		
//		try {
//			XSSFWorkbook newWorkBook = new XSSFWorkbook();
//	
//			tabs.forEach( t -> {
//				List<String> tableHeaders = t.getTableHeaders().values().stream().collect(Collectors.toList());
//				XSSFSheet sheet = newWorkBook.createSheet(t.getTabName());
//				List<Entry<ReportCellKey, ReportCell>> entries = t.getCells().entrySet().stream().collect(Collectors.toList());
//				for (int i = 0; i < t.getNumberOfRows(); i++) {
//					XSSFRow row = sheet.createRow(i);
//					for (int j = 0; j < tableHeaders.size(); j++) {
//						if (i == 0) {
//							row.createCell(j).setCellValue(tableHeaders.get(j));
//						} else {
//							
//						}
//					}
//				}
//			});
//			
//			FileOutputStream fileOut = new FileOutputStream("D:\\workbook.xlsx");
//			newWorkBook.write(fileOut);
//		    fileOut.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
}
