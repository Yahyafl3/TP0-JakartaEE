
package ma.emsi.yahya.tp0.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.io.Serializable;

@Dependent
public class GenerateurReponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Modificateur modificateur;

    public String genererReponse(String question, String roleSysteme) {
        return modificateur.genererReponse(question, roleSysteme);
    }
}
