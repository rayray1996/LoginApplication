/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author rayta
 */
@Named(value = "userLocaleManagedBean")
@SessionScoped
public class UserLocaleManagedBean implements Serializable {

    private String translatedLoginString;
    private String translatedSelectARoleString;
    private String translatedBackToHomeString;
    private String translatedBackToLoginString;
    private String translatedRegisterString;
    private String translatedCreateAccountString;
    private String translatedSecretPageString;
    private String translatedLogoutString;

    private String selectedLocale = "en";
    private Map<String, String> fr_mapping = new HashMap<>();
    private Map<String, String> en_mapping = new HashMap<>();

    public UserLocaleManagedBean() {
    }

    @PostConstruct
    public void init() {
        en_mapping.put("Username", "Username");
        en_mapping.put("Password", "Password");
        en_mapping.put("Not a user? Click the button below to register", "Not a user? Click the button below to register");
        en_mapping.put("Login", "Login");
        en_mapping.put("Register", "Register");
        en_mapping.put("Welcome", "Welcome");
        en_mapping.put("Manager Access Only", "Manager Access Only");
        en_mapping.put("Have you finished what you wanted to do?", "Have you finished what you wanted to do?");
        en_mapping.put("Secret Page", "Secret Page");
        en_mapping.put("Logout", "Logout");
        en_mapping.put("Role", "Role");
        en_mapping.put("Hello Manager", "Hello Manager");
        en_mapping.put("This is a secret page. This page should only be for your eyes", "This is a secret page. This page should only be for your eyes");
        en_mapping.put("Back to Home", "Back to Home");
        en_mapping.put("Name", "Name");
        en_mapping.put("Select a Role", "Select a Role");
        en_mapping.put("Select a Locale", "Select a Locale");
        en_mapping.put("Create Account", "Create Account");
        en_mapping.put("Your account has been successfully created", "Your account has been successfully created");
        en_mapping.put("Back to Login Page", "Back to Login Page");
        en_mapping.put("Do not understand the language? Change your language", "Do not understand the language? Change your language");
        en_mapping.put("Create An User Account", "Create An User Account");

        fr_mapping.put("Username", "Nom d'utilisateur");
        fr_mapping.put("Password", "Mot de passe");
        fr_mapping.put("Not a user? Click the button below to register", "Pas un utilisateur? Cliquez sur le bouton ci-dessous pour vous inscrire");
        fr_mapping.put("Login", "Connexion");
        fr_mapping.put("Register", "S'inscrire");
        fr_mapping.put("Welcome", "Accueillir");
        fr_mapping.put("Manager Access Only", "Acc??s du gestionnaire uniquement");
        fr_mapping.put("Have you finished what you wanted to do?", "Avez-vous termin?? ce que vous vouliez faire?");
        fr_mapping.put("Secret Page", "Page secr??te");
        fr_mapping.put("Logout", "Se d??connecter");
        fr_mapping.put("Role", "R??le");
        fr_mapping.put("Hello Manager", "Hello Manager");
        fr_mapping.put("This is a secret page. This page should only be for your eyes", "Ceci est une page secr??te. Cette page ne devrait ??tre que pour vos yeux");
        fr_mapping.put("Back to Home", "De retour ?? la maison");
        fr_mapping.put("Name", "Nom");
        fr_mapping.put("Select a Role", "S??lectionnez un r??le");
        fr_mapping.put("Select a Locale", "S??lectionnez un lieu");
        fr_mapping.put("Create Account", "Cr??er un compte");
        fr_mapping.put("Your account has been successfully created", "Votre compte ?? ??t?? cr???? avec succ??s");
        fr_mapping.put("Back to Login Page", "Retour ?? la page de connexion");
        fr_mapping.put("Do not understand the language? Change your language", "Vous ne comprenez pas la langue? Changez votre langue");
        fr_mapping.put("Create An User Account", "Cr??er un compte d'utilisateur");

        initialiseButtonsLanguage();
    }

    private void initialiseButtonsLanguage() {
        if (selectedLocale.equals("en")) {
            translatedLoginString = en_mapping.get("Login");
            translatedBackToHomeString = en_mapping.get("Back to Home");
            translatedBackToLoginString = en_mapping.get("Back to Login Page");
            translatedRegisterString = en_mapping.get("Register");
            translatedSelectARoleString = en_mapping.get("Select a Role");
            translatedCreateAccountString = en_mapping.get("Create Account");
            translatedSecretPageString = en_mapping.get("Secret Page");
            translatedLogoutString = en_mapping.get("Logout");

        } else {
            translatedLoginString = fr_mapping.get("Login");
            translatedBackToHomeString = fr_mapping.get("Back to Home");
            translatedBackToLoginString = fr_mapping.get("Back to Login Page");
            translatedRegisterString = fr_mapping.get("Register");
            translatedSelectARoleString = fr_mapping.get("Select a Role");
            translatedCreateAccountString = fr_mapping.get("Create Account");
            translatedSecretPageString = fr_mapping.get("Secret Page");
            translatedLogoutString = fr_mapping.get("Logout");

        }
    }

    public String getSelectedLocaleLanguage(String key) {
        if (selectedLocale.equals("en")) {
            return en_mapping.get(key);
        } else {
            return fr_mapping.get(key);
        }
    }

    public String getSelectedLocale() {
        return selectedLocale;
    }

    public void setSelectedLocale(String selectedLocale) {
        this.selectedLocale = selectedLocale;
    }

    public void localeChanged(ValueChangeEvent e) {
        selectedLocale = e.getNewValue().toString();
        initialiseButtonsLanguage();
    }

    public String getTranslatedCreateAccountString() {
        return translatedCreateAccountString;
    }

    public void setTranslatedCreateAccountString(String translatedCreateAccountString) {
        this.translatedCreateAccountString = translatedCreateAccountString;
    }

    public String getTranslatedLoginString() {
        return translatedLoginString;
    }

    public void setTranslatedLoginString(String translatedLoginString) {
        this.translatedLoginString = translatedLoginString;
    }

    public String getTranslatedSelectARoleString() {
        return translatedSelectARoleString;
    }

    public void setTranslatedSelectARoleString(String translatedSelectARoleString) {
        this.translatedSelectARoleString = translatedSelectARoleString;
    }

    public String getTranslatedBackToHomeString() {
        return translatedBackToHomeString;
    }

    public void setTranslatedBackToHomeString(String translatedBackToHomeString) {
        this.translatedBackToHomeString = translatedBackToHomeString;
    }

    public String getTranslatedBackToLoginString() {
        return translatedBackToLoginString;
    }

    public void setTranslatedBackToLoginString(String translatedBackToLoginString) {
        this.translatedBackToLoginString = translatedBackToLoginString;
    }

    public String getTranslatedRegisterString() {
        return translatedRegisterString;
    }

    public void setTranslatedRegisterString(String translatedRegisterString) {
        this.translatedRegisterString = translatedRegisterString;
    }

    public String getTranslatedSecretPageString() {
        return translatedSecretPageString;
    }

    public void setTranslatedSecretPageString(String translatedSecretPageString) {
        this.translatedSecretPageString = translatedSecretPageString;
    }

    public String getTranslatedLogoutString() {
        return translatedLogoutString;
    }

    public void setTranslatedLogoutString(String translatedLogoutString) {
        this.translatedLogoutString = translatedLogoutString;
    }

}
