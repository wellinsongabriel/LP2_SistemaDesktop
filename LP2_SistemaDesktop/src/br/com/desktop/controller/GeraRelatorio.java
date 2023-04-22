package br.com.desktop.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
	private static String JDBC = "jdbc:sqlite:";
	private static String LISTAR_TAREFAS = " SELECT * FROM TAREFA WHERE 1=1 ";
	private static String AND_ID = " AND ID = $P{ID} ";
	
	 public GeraRelatorio() {
		super();
		try{
			File file = new File("GeraRelatorio.java");
			String pathAbsolutoAtual = file.getAbsolutePath();
			String pathAbsolutoParcial = null;
			
			//verifica se está no Windows para definir o sentido das barras que o path atual tem
			if(System.getProperty("os.name").contains("Windows")) {
				pathAbsolutoParcial = pathAbsolutoAtual.substring(0,pathAbsolutoAtual.lastIndexOf('\\')).replace("\\","/");
			}else {
				pathAbsolutoParcial = pathAbsolutoAtual.substring(0,pathAbsolutoAtual.lastIndexOf('/'));
			}
			
			connection =  DriverManager.getConnection(JDBC+pathAbsolutoParcial+"/resources/bdtarefas.db");
	        
			Map<String, Object> parametros = new HashMap<String, Object>();	   
			parametros.put("QUERY", LISTAR_TAREFAS+AND_ID);
	        parametros.put("ID", "5");
			
	        JasperReport jasperReport = JasperCompileManager.compileReport(pathAbsolutoParcial+"/relatorios/RelatorioTarefa.jrxml");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, connection);
			JasperViewer jasperViewew = new JasperViewer(jasperPrint, false);//false para não fechar o sistema ao fechar o relatório
			
			jasperViewew.setVisible(true);
		} catch (JRException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	

}
