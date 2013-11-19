package br.com.gtmf.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	// -- Diretorios da Aplicacao
	public static final String DIR_BASE = "/";
	public static final String DIR_FXML = DIR_BASE + "fxml/";
	public static final String DIR_IMAGES = DIR_BASE + "images/";
	public static final String DIR_FACES = DIR_IMAGES + "faces/";
	
	
	// -- Arquivos de Layout
	public static final String fxmlMainLayout = DIR_FXML + "MainLayout.fxml";
	

	// -- Logo
	public static final String LOGO = DIR_IMAGES + "jcac_client_logo.png";
	
	
	// -- Faces utlizadas no Jogo
	public static final String face01 = DIR_FACES + "man01.png";
	public static final String face02 = DIR_FACES + "man02.png";
	public static final String face03 = DIR_FACES + "man03.png";
	public static final String face04 = DIR_FACES + "man04.png";
	public static final String face05 = DIR_FACES + "man05.png";
	public static final String face06 = DIR_FACES + "man06.png";
	public static final String face07 = DIR_FACES + "man07.png";
	public static final String face08 = DIR_FACES + "man08.png";
	public static final String face09 = DIR_FACES + "man09.png";
	public static final String face10 = DIR_FACES + "man10.png";
	public static final String face11 = DIR_FACES + "man11.png";
	public static final String face12 = DIR_FACES + "man12.png";
	
	public static final String face13 = DIR_FACES + "woman01.png";
	public static final String face14 = DIR_FACES + "woman02.png";
	public static final String face15 = DIR_FACES + "woman03.png";
	public static final String face16 = DIR_FACES + "woman04.png";
	public static final String face17 = DIR_FACES + "woman05.png";
	public static final String face18 = DIR_FACES + "woman06.png";
	public static final String face19 = DIR_FACES + "woman07.png";
	public static final String face20 = DIR_FACES + "woman08.png";
	public static final String face21 = DIR_FACES + "woman09.png";
	public static final String face22 = DIR_FACES + "woman10.png";
	public static final String face23 = DIR_FACES + "woman11.png";
	public static final String face24 = DIR_FACES + "woman12.png";
	
	
	public static final String [] FACES = new String[] {
		Constants.face01, Constants.face02, Constants.face03, Constants.face04, 
		Constants.face05, Constants.face06, Constants.face07, Constants.face08,
		Constants.face09, Constants.face10, Constants.face11, Constants.face12,
		Constants.face13, Constants.face14, Constants.face15, Constants.face16,
		Constants.face17, Constants.face18, Constants.face19, Constants.face20, 
		Constants.face21, Constants.face22, Constants.face23, Constants.face24
	};
	
	public static Map<String, String> PERSONS = new HashMap<String, String>(); 
}
