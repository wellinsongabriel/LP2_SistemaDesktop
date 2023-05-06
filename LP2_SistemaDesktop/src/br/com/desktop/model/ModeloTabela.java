package br.com.desktop.model;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class ModeloTabela extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] colunas = {
			"NOME", ""
		};
	
	ArrayList<Usuario> usuarios;
	
	public ModeloTabela(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

	@Override
	public int getRowCount() {
		 return usuarios.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Usuario usuario =usuarios.get(rowIndex);
		if(columnIndex==0) {
			return usuario.getUsuario();
		}else
			if(columnIndex==1) {
			return false;
			
		}else {	
			return null;
		}
		
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	
}
