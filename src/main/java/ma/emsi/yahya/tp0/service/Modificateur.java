package ma.emsi.yahya.tp0.service;

import jakarta.enterprise.context.Dependent;

import java.io.Serializable;
import java.util.Locale;

@Dependent
public class Modificateur implements Serializable {

    private static final long serialVersionUID = 1L;

    public String genererReponse(String question, String roleSysteme) {
        if (question == null || question.isBlank()) {
            return "Aucune question fournie. Veuillez formuler une interrogation sur Java, Git, Jakarta EE ou une erreur.";
        }

        String q = question.toLowerCase(Locale.FRENCH).trim();
        String roleNom = extraireNomRole(roleSysteme);

        String contenuReponse;

        if (estQuestionJava(q)) {
            contenuReponse = traiterQuestionJava(q);
        } else if (estQuestionGit(q)) {
            contenuReponse = traiterQuestionGit(q);
        } else if (estQuestionJakartaEE(q)) {
            contenuReponse = traiterQuestionJakartaEE(q);
        } else if (estQuestionDiagnostic(q)) {
            contenuReponse = traiterQuestionDiagnostic(q);
        } else {
            contenuReponse = genererReponseSecours(question);
        }

        return formaterSelonRole(contenuReponse, roleNom);
    }

    private boolean estQuestionJava(String q) {
        return q.contains("equals") || q.contains("==")
                || q.contains("primitif") || q.contains("wrapper") || q.contains("autoboxing")
                || q.contains("stream") || q.contains("record")
                || q.contains("polymorphisme") || q.contains("heritage") || q.contains("héritage")
                || q.contains("encapsulation") || q.contains("garbage collector") || q.contains(" gc ")
                || q.contains("interface") || q.contains("classe abstraite");
    }

    private boolean estQuestionGit(String q) {
        return q.contains("git") || q.contains("commit") || q.contains("rebase")
                || q.contains("merge") || q.contains("stash") || q.contains("conflit")
                || q.contains("branch") || q.contains("branche") || q.contains("push") || q.contains("pull");
    }

    private boolean estQuestionJakartaEE(String q) {
        return q.contains("jakarta") || q.contains("jsf") || q.contains("faces")
                || q.contains("cdi") || q.contains("scope") || q.contains("viewscoped")
                || q.contains("requestscoped") || q.contains("sessionscoped") || q.contains("inject")
                || q.contains("cycle de vie") || q.contains("primefaces") || q.contains("backing bean");
    }

    private boolean estQuestionDiagnostic(String q) {
        return q.contains("nullpointer") || q.contains("npe")
                || q.contains("classnotfound") || q.contains("noclassdef")
                || q.contains("classcast") || q.contains("stackoverflow")
                || q.contains("outofmemory") || q.contains("erreur") || q.contains("exception")
                || q.contains("list-components") || q.contains("port") || q.contains("4848") || q.contains("8080");
    }

    private String traiterQuestionJava(String q) {
        if (q.contains("equals") || q.contains("==")) {
            return """
                    [Notion : Comparaison equals() vs == en Java]
                    • '==' compare les références mémoires (l'adresse physique des objets dans la Heap).
                    • '.equals()' compare le contenu sémantique (l'égalité des valeurs portées par les objets).
                    
                    Exemple :
                      String s1 = new String("EMSI");
                      String s2 = new String("EMSI");
                      System.out.println(s1 == s2);      // false (2 objets distincts)
                      System.out.println(s1.equals(s2));  // true (même texte)
                    
                    Règle d'or : Quand vous redéfinissez equals(), redéfinissez TOUJOURS hashCode().""";
        }

        if (q.contains("primitif") || q.contains("wrapper") || q.contains("autoboxing")) {
            return """
                    [Notion : Types primitifs vs Classes Wrappers]
                    • Primitifs (int, double, boolean...) : stockés sur la Stack, très performants, ne peuvent pas valoir 'null'.
                    • Wrappers (Integer, Double, Boolean...) : objets stockés sur la Heap, utilisables dans les Collections génériques (List<Integer>).
                    • Autoboxing / Unboxing : conversion automatique entre primitif et wrapper.
                    
                    Attention : L'unboxing d'une référence wrapper nulle provoque une NullPointerException.""";
        }

        if (q.contains("record")) {
            return """
                    [Notion : Java Records (Java 16+)]
                    Un 'record' est une classe immuable conçue pour servir de conteneur de données pur.
                    
                    Syntaxe :
                      public record Etudiant(String nom, String filiere) {}
                    
                    Avantages :
                    • Le compilateur génère automatiquement : constructeur canonique, accesseurs (nom(), filiere()), equals(), hashCode() et toString().
                    • Tous les champs sont final et non modifiables.""";
        }

        if (q.contains("stream")) {
            return """
                    [Notion : Java Stream API (Java 8+)]
                    Un Stream permet de traiter des séquences d'éléments de façon fonctionnelle et déclarative.
                    
                    Deux types d'opérations :
                    1. Intermédiaires (lazy / paresseuses) : filter(), map(), sorted(), distinct()...
                    2. Terminales (déclenchent l'exécution) : collect(), forEach(), count(), reduce()...
                    
                    Exemple :
                      List<String> noms = etudiants.stream()
                          .filter(e -> e.getNote() >= 10)
                          .map(Etudiant::getNom)
                          .toList();""";
        }

        if (q.contains("polymorphisme") || q.contains("heritage") || q.contains("héritage") || q.contains("encapsulation")) {
            return """
                    [Notion : Piliers de la POO en Java]
                    • Encapsulation : Protection de l'état interne d'un objet (champs privés, accesseurs contrôlés).
                    • Héritage (extends) : Réutilisation de code et spécialisation d'une classe mère.
                    • Polymorphisme : Capacité d'un objet parent à adopter le comportement spécifique de ses sous-classes au runtime via la redéfinition (@Override).""";
        }

        return """
                [Notion : Fondamentaux Java]
                Java est un langage orienté objet fortement typé, compilé en bytecode exécuté sur la JVM.
                Points clés : gestion automatique de la mémoire (Garbage Collector), typage strict et écosystème standard robuste.""";
    }

    private String traiterQuestionGit(String q) {
        if (q.contains("merge") && q.contains("rebase")) {
            return """
                    [Notion : Git Merge vs Git Rebase]
                    • 'git merge' :
                      - Crée un commit de fusion (merge commit).
                      - Préserve l'historique chronologique réel avec ses branches.
                      - Idéal pour fusionner une branche de fonctionnalité sur 'main'.
                    
                    • 'git rebase' :
                      - Réapplique vos commits un par un par-dessus la branche cible.
                      - Produit un historique parfaitement linéaire et propre.
                      - Règle d'or : NE JAMAIS faire de rebase sur une branche publique/partagée.""";
        }

        if (q.contains("stash")) {
            return """
                    [Notion : Git Stash]
                    Permet de remiser temporairement vos modifications non commitées pour travailler sur autre chose sans perdre votre travail.
                    
                    Commandes essentielles :
                    • git stash            : met de côté vos modifications actuelles.
                    • git stash list       : affiche la liste des remises.
                    • git stash pop        : réapplique la dernière remise et la supprime de la pile.
                    • git stash drop       : supprime une remise sans l'appliquer.""";
        }

        if (q.contains("conflit")) {
            return """
                    [Notion : Résolution de Conflits Git]
                    Un conflit survient quand deux branches modifient les mêmes lignes d'un même fichier.
                    
                    Marqueurs de conflit :
                      <<<<<<< HEAD (votre version locale)
                      Votre code
                      =======
                      Code venant de la branche distante
                      >>>>>>> feature-branche
                    
                    Étapes de résolution :
                    1. Ouvrir le fichier et choisir le bon code en supprimant les marqueurs.
                    2. Marquer le conflit comme résolu : git add <fichier>
                    3. Finaliser l'opération : git commit ou git rebase --continue.""";
        }

        return """
                [Notion : Bonnes pratiques Git]
                • Faire des commits atomiques (une seule responsabilité par commit).
                • Rédiger des messages clairs (ex: 'feat: ajout du filtre par catégorie').
                • Toujours exécuter 'git status' avant 'git add' pour vérifier l'état de l'index.""";
    }

    private String traiterQuestionJakartaEE(String q) {
        if (q.contains("cycle de vie") || (q.contains("phase") && q.contains("jsf"))) {
            return """
                    [Notion : Cycle de vie d'une requête JSF (6 phases)]
                    1. Restore View        : Restauration ou création de l'arbre des composants (UIViewRoot).
                    2. Apply Request Values: Récupération des paramètres HTTP et affectation aux composants.
                    3. Process Validations : Validation des données saisies (ex: required, converters). Si erreur -> saut direct en phase 6 !
                    4. Update Model Values : Mise à jour des propriétés du Backing Bean avec les valeurs validées.
                    5. Invoke Application  : Exécution de la méthode d'action métier (ex: bb.envoyer()).
                    6. Render Response     : Rendu du HTML renvoyé au navigateur du client.""";
        }

        if (q.contains("scope") || q.contains("viewscoped") || q.contains("requestscoped") || q.contains("sessionscoped")) {
            return """
                    [Notion : Portées (Scopes) des Beans CDI / JSF]
                    • @RequestScoped     : Vit le temps d'une seule requête HTTP. Très éphémère.
                    • @ViewScoped        : Vit tant que l'utilisateur reste sur la même page JSF (idéal pour Ajax / PrimeFaces).
                    • @SessionScoped     : Vit pendant toute la session de l'utilisateur (authentification, panier).
                    • @ApplicationScoped : Instance unique partagée par tous les utilisateurs de l'application.
                    • @Dependent         : Portée par défaut, calquée sur le cycle de vie du bean qui l'injecte.""";
        }

        if (q.contains("inject") || q.contains("cdi")) {
            return """
                    [Notion : CDI (Contexts and Dependency Injection)]
                    CDI est le standard d'injection de dépendances de Jakarta EE.
                    
                    Principes :
                    • @Inject : injecte automatiquement l'instance d'un bean géré par le conteneur.
                    • @Named  : rend le bean accessible dans les vues EL (ex: #{bb.propriete}).
                    • Inversion de Contrôle : le développeur ne fait plus de 'new', le conteneur gère l'instanciation et le cycle de vie.""";
        }

        return """
                [Notion : Jakarta EE & JSF]
                Jakarta EE est la plateforme d'entreprise standard de Java.
                Jakarta Faces (JSF) sépare nettement la vue (Facelets XHTML) du contrôleur (Backing Bean CDI) avec gestion native des événements et requêtes AJAX.""";
    }

    private String traiterQuestionDiagnostic(String q) {
        if (q.contains("nullpointer") || q.contains("npe")) {
            return """
                    [Diagnostic : NullPointerException (NPE)]
                    • Cause : Tentative d'invoquer une méthode ou d'accéder à un champ sur une variable valant 'null'.
                    • Démarche de résolution :
                      1. Lire la trace d'erreur (Stack Trace) et repérer le premier numéro de ligne de votre code.
                      2. Identifier quelle variable sur cette ligne n'a pas été initialisée.
                      3. En CDI : vérifier que l'annotation @Inject n'a pas été oubliée et que la classe injectable porte bien un scope (ex: @Dependent).""";
        }

        if (q.contains("list-components")) {
            return """
                    [Diagnostic : Command list-components not found (Payara 7)]
                    • Cause : Dans Payara 7 (Jakarta EE 11), l'ancienne sous-commande d'administration 'list-components' a été définitivement supprimée au profit de 'list-applications'.
                    • Résolution :
                      - Mettre à jour l'agent GlassFish/Payara d'IntelliJ pour invoquer 'list-applications'.
                      - Ou déployer via 'asadmin deploy' ou par copie directe dans le répertoire autodeploy.""";
        }

        if (q.contains("classnotfound") || q.contains("noclassdef")) {
            return """
                    [Diagnostic : ClassNotFoundException vs NoClassDefFoundError]
                    • ClassNotFoundException : Levée au runtime quand un ClassLoader tente de charger dynamiquement une classe introuvable dans le classpath.
                    • NoClassDefFoundError : La classe était présente lors de la compilation mais est introuvable au moment de l'exécution (dépendance absente du packaging WAR).""";
        }

        if (q.contains("port") || q.contains("8080") || q.contains("4848")) {
            return """
                    [Diagnostic : Port 8080 ou 4848 déjà utilisé]
                    • Cause : Une instance précédente de Payara, GlassFish ou Tomcat est restée active en arrière-plan.
                    • Résolution :
                      1. Exécuter : asadmin stop-domain domain1
                      2. Ou sous Windows : repérer le processus avec 'Get-NetTCPConnection -LocalPort 8080' et fermer le processus correspondant.""";
        }

        return """
                [Diagnostic : Méthodologie générale de débogage]
                1. Toujours remonter à la 'Caused by' tout en bas de la pile d'exception.
                2. Vérifier les dépendances dans le pom.xml (scope provided vs compile).
                3. Consulter les logs du serveur d'application (server.log).""";
    }

    private String genererReponseSecours(String question) {
        return """
                [Assistant de Révision en Informatique — EMSI Casablanca]
                Je n'ai pas trouvé de règle exacte pour votre question : "%s"
                
                Voici les domaines sur lesquels je peux vous aider à réviser :
                1. Java :
                   - Différence entre 'equals()' et '=='
                   - Types primitifs vs Wrappers & Autoboxing
                   - Records (Java 16+) et Streams API
                   - Piliers de la POO (Héritage, Polymorphisme, Encapsulation)
                2. Git :
                   - Différence entre 'git merge' et 'git rebase'
                   - Utilisation de 'git stash'
                   - Résolution méthodique des conflits
                3. Jakarta EE & JSF :
                   - Les 6 phases du cycle de vie JSF
                   - Les portées CDI (@RequestScoped, @ViewScoped, @SessionScoped...)
                   - Le fonctionnement de l'injection de dépendances @Inject
                4. Diagnostic d'erreurs :
                   - Comprendre et corriger une NullPointerException
                   - Différence entre ClassNotFoundException et NoClassDefFoundError
                   - Conflits de ports (8080 / 4848) ou erreurs Payara 7 (list-components)
                
                Posez l'une de ces questions pour obtenir une explication détaillée !""".formatted(question);
    }

    private String extraireNomRole(String roleSysteme) {
        if (roleSysteme == null) {
            return "Assistant";
        }
        String r = roleSysteme.toLowerCase(Locale.FRENCH);
        if (r.contains("traducteur") || r.contains("translate")) {
            return "Traducteur";
        }
        if (r.contains("guide") || r.contains("travel")) {
            return "Guide";
        }
        if (r.contains("coach") || r.contains("java")) {
            return "Coach Java";
        }
        if (r.contains("mentor") || r.contains("git")) {
            return "Mentor Git";
        }
        if (r.contains("débogueur") || r.contains("debug") || r.contains("diagnostic")) {
            return "Diagnostiqueur";
        }
        return "Tuteur";
    }

    private String formaterSelonRole(String contenu, String roleNom) {
        String entete = switch (roleNom) {
            case "Diagnostiqueur" -> "🔍 [Mode Diagnostic & Résolution]";
            case "Coach Java" -> "☕ [Mode Coach Java & Jakarta EE]";
            case "Mentor Git" -> "🌿 [Mode Mentor Git & DevOps]";
            case "Traducteur" -> "🌐 [Mode Traducteur Technique]";
            case "Guide" -> "🧭 [Mode Guide Pédagogique]";
            default -> "🎓 [Assistant de Révision Informatique]";
        };

        return entete + "\n" + contenu;
    }
}
