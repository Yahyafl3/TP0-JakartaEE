package ma.emsi.yahya.tp0.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModificateurTest {

    private Modificateur modificateur;

    @BeforeEach
    void setUp() {
        modificateur = new Modificateur();
    }

    @Test
    @DisplayName("Question Java : equals vs == doit expliquer les références et le contenu")
    void testQuestionJavaEqualsVsDoubleEqual() {
        String reponse = modificateur.genererReponse("Quelle est la différence entre equals et == ?", "Coach Java");

        assertNotNull(reponse);
        assertTrue(reponse.contains("equals() vs =="));
        assertTrue(reponse.contains("références mémoires"));
        assertTrue(reponse.contains("contenu sémantique"));
        assertTrue(reponse.contains("Coach Java"));
    }

    @Test
    @DisplayName("Question Java : Records doit expliquer l'immuabilité et la génération des accesseurs")
    void testQuestionJavaRecord() {
        String reponse = modificateur.genererReponse("Comment fonctionne un record en Java moderne ?", "Tuteur");

        assertNotNull(reponse);
        assertTrue(reponse.contains("record"));
        assertTrue(reponse.contains("immuable"));
    }

    @Test
    @DisplayName("Question Java : Streams doit expliquer les opérations intermédiaires et terminales")
    void testQuestionJavaStream() {
        String reponse = modificateur.genererReponse("Explique-moi l'API Stream", "Tuteur");

        assertNotNull(reponse);
        assertTrue(reponse.contains("Stream API"));
        assertTrue(reponse.contains("Intermédiaires"));
        assertTrue(reponse.contains("Terminales"));
    }

    @Test
    @DisplayName("Question Git : merge vs rebase doit expliquer l'historique et la règle d'or")
    void testQuestionGitMergeVsRebase() {
        String reponse = modificateur.genererReponse("git merge ou git rebase ?", "Mentor Git");

        assertNotNull(reponse);
        assertTrue(reponse.contains("Merge vs Git Rebase"));
        assertTrue(reponse.contains("linéaire"));
        assertTrue(reponse.contains("Mentor Git"));
    }

    @Test
    @DisplayName("Question Git : stash doit expliquer la mise de côté temporaire")
    void testQuestionGitStash() {
        String reponse = modificateur.genererReponse("À quoi sert git stash ?", "Mentor Git");

        assertNotNull(reponse);
        assertTrue(reponse.contains("Git Stash"));
        assertTrue(reponse.contains("git stash pop"));
    }

    @Test
    @DisplayName("Question Git : GitHub vs GitLab doit comparer les plateformes")
    void testQuestionGitHubVsGitLab() {
        String reponse = modificateur.genererReponse("quelle est la différence entre github et gitlab?", "Mentor Git");

        assertNotNull(reponse);
        assertTrue(reponse.contains("GitHub vs GitLab"));
        assertTrue(reponse.contains("Microsoft"));
        assertTrue(reponse.contains("CI/CD"));
    }

    @Test
    @DisplayName("Question Jakarta EE : Cycle de vie JSF doit énumérer les 6 phases")
    void testQuestionCycleDeVieJSF() {
        String reponse = modificateur.genererReponse("Quelles sont les phases du cycle de vie JSF ?", "Coach Java");

        assertNotNull(reponse);
        assertTrue(reponse.contains("Restore View"));
        assertTrue(reponse.contains("Apply Request Values"));
        assertTrue(reponse.contains("Process Validations"));
        assertTrue(reponse.contains("Update Model Values"));
        assertTrue(reponse.contains("Invoke Application"));
        assertTrue(reponse.contains("Render Response"));
    }

    @Test
    @DisplayName("Question Jakarta EE : Scopes CDI doit détailler ViewScoped, RequestScoped, etc.")
    void testQuestionScopesCDI() {
        String reponse = modificateur.genererReponse("Quels sont les scopes CDI disponibles ?", "Coach Java");

        assertNotNull(reponse);
        assertTrue(reponse.contains("@RequestScoped"));
        assertTrue(reponse.contains("@ViewScoped"));
        assertTrue(reponse.contains("@SessionScoped"));
    }

    @Test
    @DisplayName("Diagnostic : NullPointerException doit guider l'étudiant dans l'analyse de stack trace")
    void testDiagnosticNullPointerException() {
        String reponse = modificateur.genererReponse("J'ai une NullPointerException dans mon application", "Débogueur");

        assertNotNull(reponse);
        assertTrue(reponse.contains("NullPointerException"));
        assertTrue(reponse.contains("Stack Trace"));
        assertTrue(reponse.contains("Diagnostic"));
    }

    @Test
    @DisplayName("Diagnostic : Erreur Payara list-components doit expliquer la dépréciation Payara 7")
    void testDiagnosticListComponentsPayara7() {
        String reponse = modificateur.genererReponse("Pourquoi l'erreur Command list-components not found ?", "Débogueur");

        assertNotNull(reponse);
        assertTrue(reponse.contains("Payara 7"));
        assertTrue(reponse.contains("list-applications"));
    }

    @Test
    @DisplayName("Question inconnue : doit déclencher la réponse de secours avec les suggestions")
    void testReponseSecours() {
        String reponse = modificateur.genererReponse("Quelle est la météo à Casablanca ?", "Assistant");

        assertNotNull(reponse);
        assertTrue(reponse.contains("Assistant de Révision en Informatique"));
        assertTrue(reponse.contains("Java :"));
        assertTrue(reponse.contains("Git :"));
        assertTrue(reponse.contains("Jakarta EE"));
    }

    @Test
    @DisplayName("Entrée nulle ou composée d'espaces : doit renvoyer un message d'invitation sans planter")
    void testEntreeNulleOuEspaces() {
        String repNull = modificateur.genererReponse(null, "Assistant");
        String repVide = modificateur.genererReponse("   ", "Assistant");

        assertNotNull(repNull);
        assertNotNull(repVide);
        assertTrue(repNull.contains("Aucune question fournie"));
        assertTrue(repVide.contains("Aucune question fournie"));
    }
}
