package com.carlita.ui.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class LoginFormFactory {

	@Autowired
	private DaoAuthenticationProvider daoAuthenticationProvider;
	
	private class LoginForm {
		
		private VerticalLayout root;
		private Panel panel;
		private TextField username;
		private PasswordField passwordField;
		private Button loginButton;
		private Button signupButton;
		
		public LoginForm init() {
			
			root = new VerticalLayout();
			root.setMargin(true);
			root.setHeight("100%");
			
			panel = new Panel("Login");
			panel.setSizeUndefined();
			
			loginButton = new Button("Login");
			loginButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			signupButton = new Button("Sign up");
			signupButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
			
			username = new TextField("Username");
			passwordField = new PasswordField("Password");
			
			return this;
		}
		
		public Component layout() {
			
			root.addComponent(panel);
			root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
			
			FormLayout loginLayout = new FormLayout();
			loginLayout.addComponent(username);
			loginLayout.addComponent(passwordField);
			
			loginLayout.addComponent(new HorizontalLayout(loginButton, signupButton));
			loginLayout.setSizeUndefined();
			loginLayout.setMargin(true);
			
			loginButton.addClickListener(new ClickListener() {
				public void buttonClick(ClickEvent event) {
					try {
						Authentication auth = new UsernamePasswordAuthenticationToken(username.getValue(), passwordField.getValue());
						Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
						SecurityContextHolder.getContext().setAuthentication(authenticated);
						
						UI.getCurrent().getPage().setLocation("/univers-web/ui");
					} catch (AuthenticationException e) {
						Notification.show("Error!", "Login fail! Try again", Type.ERROR_MESSAGE);
						System.out.println(e.getMessage());
					}
					
					username.clear();
					passwordField.clear();
				}
			});
			
			signupButton.addClickListener(new ClickListener() {
				public void buttonClick(ClickEvent event) {
					UI.getCurrent().getPage().setLocation("/univers-web/signup");
				}
			});
			
			panel.setContent(loginLayout);
			
			return root;
		}
	}
	
	public Component createComponent() {
		return new LoginForm().init().layout();
	}
}
