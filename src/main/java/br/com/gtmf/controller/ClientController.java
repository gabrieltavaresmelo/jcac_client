package br.com.gtmf.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.drafts.Draft_75;
import org.java_websocket.drafts.Draft_76;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.gtmf.model.Bundle;
import br.com.gtmf.window.ListenerWindow;

/**
 * Classe que implementa a logica do Cliente (WebSocketClient)
 * 
 * Envia/recebe mensagens para/do servidor.
 * 
 * @author Gabriel Tavares
 *
 */
public class ClientController {
	
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);
    
	public static final Draft[] drafts = { new Draft_17(), new Draft_10(), new Draft_76(), new Draft_75() };
	
	private boolean isConnected = false;

	private WebSocketClient cc = null;
	private ListenerWindow listener = null;

	private String username;
	private String ipserver;
	
	// Singleton
	private static ClientController instance = null;

	
	public static ClientController getInstance() {
		if (instance == null) {
			instance = new ClientController();
		}
		return instance;
	}

	public void setListener(ListenerWindow listener) {
		this.listener = listener;
	}
	
	/**
	 * Faz a conexao com o Servidor
	 * 
	 * @param ipServer
	 * @param port
	 * @param username
	 * @throws URISyntaxException
	 */
	public void connect(String ipServer, int port, String username) throws URISyntaxException {
		this.username = username;
		this.ipserver = ipServer + ":" + port;
		
		String url = "ws://" + this.ipserver;

		// cc = new ChatClient(new URI(uriField.getText()), area, ( Draft ) draft.getSelectedItem() );
		cc = new WebSocketClient(new URI(url), drafts[0]) {

			@Override
			public void onMessage( String message ) {
//				log.debug(message);

				try {
					Bundle bundle = new Bundle(message);
					
					switch (bundle.getHead()) {
					case Bundle.LIST_USERS_ON:
						if(listener != null)
							listener.lvUsers(bundle.getUsersOn());
						
						break;
						
					default:
						if(listener != null)
							listener.receive(bundle);
						
						break;
					}
					
				} catch (Exception e) {
					log.warn("Formato de mensagem nao identificado!");
				}
			}

			@Override
			public void onOpen( ServerHandshake handshake ) {
				isConnected = true;
				log.debug("Usuario conectado: " + getURI());

				if(listener != null){
					listener.setConnected(isConnected);
					
					try {
						Bundle bundle = new Bundle(Bundle.USER_IN,
								ClientController.getInstance().getUsername());
		    			send(bundle.toJson());
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					}
				}
			}

			@Override
			public void onClose( int code, String reason, boolean remote ) {
				isConnected = false;

				log.debug("Usuario desconectado: " + getURI() + "; Codigo: " + code + " " + reason);

				if(listener != null){
					listener.setLockToConnectUI(false);
					listener.setConnected(isConnected);
				}
			}

			@Override
			public void onError( Exception ex ) {
				if(listener != null){
					listener.error(ex);
				}
			}
		};

		if(listener != null){
			listener.setLockToConnectUI(true);
		}

		cc.connect();
	}
	
	public void send(String message) {
		if(cc != null && isConnected){
			cc.send(message);
		}
	}

	public void stop() {
		if(cc != null && isConnected){
			try {
				Bundle bundle = new Bundle(Bundle.USER_OUT,
						ClientController.getInstance().getUsername());
    			send(bundle.toJson());
    			
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}	
		}
	}

	public void destroy() {
		if(cc != null && isConnected){    			
			cc.close();    			
		}
		instance = null;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getIpServer() {
		return ipserver;
	}
}
