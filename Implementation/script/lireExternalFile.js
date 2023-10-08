function afficherContenuFichier(idElement, cheminFichier) {
  const contenuFichier = document.getElementById(idElement);

    fetch(cheminFichier)
      .then(response => response.text())
      .then(data => {
        contenuFichier.innerHTML = data;
      })
      .catch(error => {
        console.error('Une erreur s\'est produite :', error);
      });
  }



