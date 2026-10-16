
async function copyToClipboard(idTextArea) {
    const textArea = document.getElementById("form:" + idTextArea);

    if (textArea) {
        try {
            await navigator.clipboard.writeText(textArea.value);
        } catch (err) {
            console.error("Erreur lors de la copie : ", err);
        }
    }
}

function toutEffacer() {
    const question = document.getElementById("form:question");
    const reponse = document.getElementById("form:reponse");

    if (question) {
        question.value = "";
    }

    if (reponse) {
        reponse.value = "";
    }
}
