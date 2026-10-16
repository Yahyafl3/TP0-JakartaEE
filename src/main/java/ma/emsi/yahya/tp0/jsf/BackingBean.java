
package ma.emsi.yahya.tp0.jsf;

import ma.emsi.yahya.tp0.service.GenerateurReponse;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("bb")
@ViewScoped
public class BackingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleSysteme;
    private boolean roleSystemeChangeable = true;
    private List<SelectItem> listeRolesSysteme;

    private String question = "";
    private String reponse = "";
    private StringBuilder conversation = new StringBuilder();

    @Inject
    private GenerateurReponse generateurReponse;

    @Inject
    private FacesContext facesContext;

    public BackingBean() {
    }

    @PostConstruct
    public void init() {
        if (!getRolesSysteme().isEmpty()) {
            this.roleSysteme = (String) getRolesSysteme().get(0).getValue();
        }
    }

    public String getRoleSysteme() {
        return roleSysteme;
    }

    public void setRoleSysteme(String roleSysteme) {
        this.roleSysteme = roleSysteme;
    }

    public boolean isRoleSystemeChangeable() {
        return roleSystemeChangeable;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getConversation() {
        return conversation.toString();
    }

    public List<SelectItem> getRolesSysteme() {
        if (listeRolesSysteme == null) {
            listeRolesSysteme = new ArrayList<>();

            String role = """
                    You are a computer science revision tutor at EMSI.
                    You explain Java, Git, Jakarta EE, and help diagnose programming errors pedagogically.
                    """;
            listeRolesSysteme.add(new SelectItem(role, "Tuteur Informatique (Généraliste)"));

            role = """
                    You are a senior Java & Jakarta EE software engineer and coach.
                    Focus on OOP principles, clean code, CDI, and Jakarta Faces.
                    """;
            listeRolesSysteme.add(new SelectItem(role, "Coach Java & Jakarta EE"));

            role = """
                    You are a Git and DevOps specialist.
                    Explain branching strategies, merge vs rebase, and conflict resolutions.
                    """;
            listeRolesSysteme.add(new SelectItem(role, "Mentor Git & DevOps"));

            role = """
                    You are an expert debugger and troubleshooter.
                    Analyze errors, identify root causes, and provide step-by-step fixes.
                    """;
            listeRolesSysteme.add(new SelectItem(role, "Débogueur & Diagnostic"));

            role = """
                    You are a helpful assistant.
                    You help the user to find the information they need.
                    If the user types a question, you answer it.
                    """;
            listeRolesSysteme.add(new SelectItem(role, "Assistant"));

            role = """
                    You are an interpreter.
                    You translate from English to French and from French to English.
                    """;
            listeRolesSysteme.add(new SelectItem(role, "Traducteur Anglais-Français"));

            role = """
                    You are a travel guide.
                    """;
            listeRolesSysteme.add(new SelectItem(role, "Guide touristique"));
        }

        return listeRolesSysteme;
    }

    public String envoyer() {
        if (question == null || question.isBlank()) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Erreur de validation : question vide",
                    "Veuillez formuler une question. Les entrées vides ou composées uniquement d'espaces ne sont pas autorisées."
            );

            facesContext.addMessage(null, message);
            return null;
        }

        if (conversation.isEmpty()) {
            roleSystemeChangeable = false;
        }

        reponse = generateurReponse.genererReponse(
                question,
                roleSysteme
        );

        afficherConversation();
        question = "";

        return null;
    }

    public String nouveauChat() {
        return "index?faces-redirect=true";
    }

    public void effacer() {
        this.question = "";
        this.reponse = "";
    }

    private void afficherConversation() {
        conversation.append("== User:\n")
                .append(question)
                .append("\n== Serveur:\n")
                .append(reponse)
                .append("\n\n");
    }
}
