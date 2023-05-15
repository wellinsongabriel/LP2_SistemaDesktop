package br.com.desktop.controller;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.zip.ZipEntry;

import java.util.zip.ZipInputStream;

import java.util.zip.ZipOutputStream;

import javax.swing.JOptionPane;

public class Backup {

	private static final SimpleDateFormat formatoData = new SimpleDateFormat("ddMMyyyy_HHmmss");
	private static final SistemaOperacional so = SistemaOperacional.getIstancia();

	private String pathAbsolutoParcial() {

		File file = new File("Backup.java");

		String pathAbsolutoAtual = file.getAbsolutePath();

		String pathAbsolutoParcial = null;

		// verifica se está no Windows para definir o sentido das barras que o path
		// atual tem
		if (so.sistemaWindows()) {
			pathAbsolutoParcial = pathAbsolutoAtual.substring(0, pathAbsolutoAtual.lastIndexOf('\\'));
		} else {
			pathAbsolutoParcial = pathAbsolutoAtual.substring(0, pathAbsolutoAtual.lastIndexOf('/'));
		}
		return pathAbsolutoParcial;
	}

	public void descompactarArquivo(String caminhoArquivoZip) throws IOException {
		StringBuilder pastaDestino = new StringBuilder(pathAbsolutoParcial());
		byte[] buffer = new byte[1024];

		if (so.sistemaWindows()) {
			pastaDestino.append("\\resources");
//			System.out.println(pastaDestino);
		} else {

			pastaDestino.append("/");
			pastaDestino.append("resources");
//			System.out.println(pastaDestino);
		}
		// Cria um novo diretório para extrair os arquivos
		File pasta = new File(pastaDestino.toString());

		if (!pasta.exists()) {
			pasta.mkdirs();
		}

		try (ZipInputStream zis = new ZipInputStream(new FileInputStream(caminhoArquivoZip))) {

			// Percorre cada arquivo dentro do ZIP
			ZipEntry entry;

			while ((entry = zis.getNextEntry()) != null) {

				String nomeArquivo = entry.getName();
				File arquivo = new File(pastaDestino + File.separator + nomeArquivo);

				// Cria um novo diretório caso o arquivo seja uma pasta
				if (entry.isDirectory()) {
					arquivo.mkdirs();
					continue;
				}

				// Cria os diretórios caso não existam

				File parent = arquivo.getParentFile();

				if (!parent.exists()) {
					parent.mkdirs();
				}

				// Escreve o arquivo em disco
				try (FileOutputStream fos = new FileOutputStream(arquivo)) {
					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
				}

			}
			JOptionPane.showMessageDialog(null, "Backup restaurado com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falha ao restaurar backup do arquivo " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("Erro ao descompactar arquivo: " + e.getMessage());
			throw e;
		}

	}

	public void compactarDiretorio() {

		StringBuilder dirPath = null;

		StringBuilder zipPath = new StringBuilder();

		try {
			dirPath = new StringBuilder(pathAbsolutoParcial());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (so.sistemaWindows()) {

			dirPath.append("\\");
			zipPath.append(dirPath.toString());
			dirPath.append("\\resources");
			zipPath.append("backup");
			zipPath.append(formatoData.format(new Date()));
			zipPath.append(".zip");
//			System.out.println(dirPath);
//			System.out.println(zipPath);
		} else {

			dirPath.append("/");
			zipPath.append(dirPath.toString());
			dirPath.append("resources");
			zipPath.append("backup");
			zipPath.append(formatoData.format(new Date()));
			zipPath.append(".zip");
//			System.out.println(dirPath);
//			System.out.println(zipPath);
		}
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipPath.toString());

			zos = new ZipOutputStream(fos);

			adicionarDiretorioAoZip("", dirPath.toString(), zos);
			JOptionPane.showMessageDialog(null, "Backup realizado com sucesso");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falha ao realizar o backup", "", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			try {
				zos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private void adicionarDiretorioAoZip(String caminho, String dirPath, ZipOutputStream zos) throws IOException {

		File dir = new File(dirPath);

		for (String nomeArquivo : dir.list()) {
			String caminhoCompleto = dirPath + "/" + nomeArquivo;
			if (new File(caminhoCompleto).isDirectory()) {
				adicionarDiretorioAoZip(caminho + nomeArquivo + "/", caminhoCompleto, zos);
				continue;
			}

			ZipEntry ze = new ZipEntry(caminho + nomeArquivo);

			zos.putNextEntry(ze);

			FileInputStream fis = new FileInputStream(caminhoCompleto);

			byte[] buffer = new byte[1024];

			int len;

			while ((len = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}

			fis.close();

		}

	}

	public ArrayList<String> listarArquivos() {
		String dirPath = pathAbsolutoParcial();
		File diretorio = new File(dirPath);
		ArrayList<String> caminhosBackups = new ArrayList<>();

		
		if (diretorio.exists()) {
			
			File[] arquivos = diretorio.listFiles();

			// verifica se existem arquivos no diretório
			if (arquivos != null && arquivos.length > 0) {
				
				// listar todos os arquivos que contenham backup no nome
				for (File arquivo : arquivos) {
					if (arquivo.isFile()) {
						if (arquivo.getName().contains("backup")) {
							caminhosBackups.add(arquivo.getAbsolutePath());
//							System.out.println(arquivo.getName());
						}
					}
				}
			} else {
				System.out.println("Não há arquivos no diretório");
			}
		} else {
			System.out.println("O diretório não existe");
		}
		return caminhosBackups;
	}

}
