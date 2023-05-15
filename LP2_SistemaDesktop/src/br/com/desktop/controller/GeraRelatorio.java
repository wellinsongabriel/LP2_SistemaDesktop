package br.com.desktop.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class GeraRelatorio {
	Connection connection = null;
	private static SistemaOperacional so = SistemaOperacional.getIstancia();
	private static String JDBC = "jdbc:sqlite:";
	private static String LISTAR_TAREFAS = " SELECT T.*, P.NOME AS NOME_PROJETO, " + " CASE " + " WHEN T.STATUS = 0 THEN 'A fazer' "
			+ " WHEN T.STATUS = 1 THEN 'Em andamento' " + " ELSE 'Concluída'" + " END AS STATUS_TEXTO"
			+ " FROM TAREFA T "
			+ " INNER JOIN PROJETO P ON T.ID_PROJETO = P.ID "
			+ " WHERE 1=1 ";

	private static String AND_ID_PROJETO = " AND T.ID_PROJETO = $P{ID_PROJETO} ";

	private static String AND_STATUS = " AND T.STATUS = $P{STATUS} ";

	private static String AND_DATA_INICIO_AND_DATA_FIM = " AND T.DATA_CRIACAO >= $P{DATA_INICIAL} AND T.DATA_CRIACAO <= $P{DATA_FINAL} ";
	
	private static String ORDER_BY = " ORDER BY T.DATA_CRIACAO ";
	
	

//	private static String  

	public GeraRelatorio(int idProjeto, int status, Date dataInicial, Date dataFinal) {
		super();
		try {
			File file = new File("GeraRelatorio.java");
			String pathAbsolutoAtual = file.getAbsolutePath();
			String pathAbsolutoParcial = null; 

			// verifica se está no Windows para definir o sentido das barras que o path
			// atual tem
			if (so.sistemaWindows()) {
				pathAbsolutoParcial = pathAbsolutoAtual.substring(0, pathAbsolutoAtual.lastIndexOf('\\')).replace("\\",
						"/");
			} else {
				pathAbsolutoParcial = pathAbsolutoAtual.substring(0, pathAbsolutoAtual.lastIndexOf('/'));
			}

			connection = DriverManager.getConnection(JDBC + pathAbsolutoParcial + "/resources/bdtarefas.db");

			Map<String, Object> parametros = new HashMap<String, Object>();

			String query = LISTAR_TAREFAS + AND_ID_PROJETO + AND_DATA_INICIO_AND_DATA_FIM;

			
			if(status>=0) {
				 query += AND_STATUS;
				 parametros.put("STATUS", status);
			}
			
			query +=  ORDER_BY;
			
			parametros.put("QUERY", query);
			parametros.put("ID_PROJETO", idProjeto);
			
			Calendar calendaDataInicial = Calendar.getInstance();
			calendaDataInicial.setTime(dataInicial);
			calendaDataInicial.set(Calendar.HOUR_OF_DAY, 0);
			calendaDataInicial.set(Calendar.MINUTE, 0);
			calendaDataInicial.set(Calendar.SECOND,1);
			
			Calendar calendaDataFinal = Calendar.getInstance();
			calendaDataFinal.setTime(dataFinal);
			calendaDataFinal.set(Calendar.HOUR_OF_DAY, 23);
			calendaDataFinal.set(Calendar.MINUTE, 59);
			calendaDataFinal.set(Calendar.SECOND, 59);
			
			parametros.put("DATA_INICIAL", calendaDataInicial.getTime());
			parametros.put("DATA_FINAL", calendaDataFinal.getTime());
			

			JasperReport jasperReport = JasperCompileManager
					.compileReport(pathAbsolutoParcial + "/relatorios/RelatorioTarefa.jrxml");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, connection);
			JasperViewer jasperViewew = new JasperViewer(jasperPrint, false);// false para não fechar o sistema ao
																				// fechar o relatório

			jasperViewew.setVisible(true);
		} catch (JRException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
