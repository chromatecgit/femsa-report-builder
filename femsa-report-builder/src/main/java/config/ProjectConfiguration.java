package config;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe base de configura�ao para aplicacoes
 * 
 * @author Cesar Sportore
 */

public class ProjectConfiguration {
	/**
	 * Diret�rio raiz padr�o da aplica��o. Daqui ramificam-se os outros.
	 **/
	private static final String root =
			System.getProperty("user.dir")
					.concat(File.separator);
//			"D:".concat(File.separator);
	
	/** 
	 * Constante contendo o endereco dos arquivos de log.
	 *	OBS.: � feita uma concatena��o a mais em cada Modulo do Auditor para separar os arquivos em pastas
	 */
	public static final String logFolder = root.concat("logs").concat(File.separator);
	
	public static final Path idsPath = Paths.get(
			root.concat("spreadsheets").concat(File.separator).concat("ids_ref").concat(File.separator));
	
	public static final Path basesPath = Paths.get(
			root.concat("spreadsheets").concat(File.separator).concat("bases").concat(File.separator));
	
	public static final Path resultPath = Paths.get(
			root.concat("spreadsheets").concat(File.separator).concat("result").concat(File.separator));
	
	public static final String glob = "**.{xlsx}";
	
	/**
	 * Array com o nome das pastas de projetos permitidos.
	 * - Com o array vazio, todas as pastas serao incluidas
	 * ATENCAO: Procure nao usar em conjunto com o blacklist
	 * 
	 * EX.: "127_PetshopClinicasVeterinarias","124_Consultorios"
	 */
	public static final String[] whitelistFolders = {
	};
	
	/** 
	 * Array com o nome das pastas de projetos probidos.
	 * - Com o array vazio, nenhuma pasta sera excluida
	 * 	ATENCAO: Procure nao usar em conjunto com o whitelist
	 * 
	 * EX.: "127_PetshopClinicasVeterinarias","124_Consultorios"
	 */
	public static final String[] blacklistFolders = {
	};
	
	/** Array com as extensoes de arquivo a serem incluidas na busca. */
	public static final String[] fileTypes = {
			"xlsx"
	};
	
}
