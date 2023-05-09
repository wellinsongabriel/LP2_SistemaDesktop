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
			return true;
			
		}else {	
			return null;
		}
		
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		 if (columnIndex == 1) {
			 	System.out.println(aValue);
	            Usuario usuario = usuarios.get(rowIndex);
	            Object obj = aValue.toString().equalsIgnoreCase("false")?true:false;
	            usuario.setSelecionado((Boolean) obj);
	            fireTableCellUpdated(rowIndex, columnIndex);
	            
	        }
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }
	
	  @Override
	    public Class<?> getColumnClass(int columnIndex) {
	        if (columnIndex == 1) {
	            return Boolean.class;
	        } else {
	            return super.getColumnClass(columnIndex);
	        }
	    }
}
