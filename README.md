# App-Mobiles

## TP1

Les vidéos de chaque exercice sont également présents dans ce dépôt.

### HelloWorld

Application de base affichant Hello World avec un font et une couleur spécifique de texte et un fond d'écran gris obscur.
Il s'agit juste d'une application pour découvrir Android Studio.

### Exo3

Ce dossier correspondant aux exercices 3 à 7, il s'agit donc de la création d'un formulaire qui demande des informations à l'utilisateur. Ensuite l'application ouvre l'application Téléphone avec le numéro pré rempli que l'utilisateur a saisi dans le formulaire.

### AppTrains

Il s'agit d'une application simple (sans utilisations d'API) de recherches d'itinéraires de Train. Les données ont été codées en dûr dans ResultActivity.kt simulant des itinéraires réels avec un écart de temp entre elles. Après avoir choisi un itinéraire, l'application demandera à l'utilisateur de saisir des informations afin de compléter la réservation.

### AppAgenda

Cette application joue le rôle d'un agenda. La persistance de données a été gérée ici. Donc on pourra bien utiliser cette application comme une vrai application mobile. On peut créer autant d'évènements qu'on veut et y accéder avec leurs détails spécifiés tels que le titre, la description, l'heure de début et l'heure de fin de l'évènement, la localisation et le marquer associé. Nous pouvons aussi les modifier ainsi que les supprimer à volonté dans la section où on a tous nos évènements afffichés.

## TP2

Ce TP2 explore l'utilisation des Capteurs, ListView, RecyclerView et Fragments pour développer des applications interactives sous Android.
Chaque exercice est accompagné d’une démonstration vidéo disponible dans ce dépôt.

### Partie 1

Cette première partie du TP explore l’utilisation des capteurs d’un smartphone sous Android Studio avec Kotlin et XML. Nous avons implémenté différentes fonctionnalités basées sur les capteurs intégrés et la géolocalisation.

#### Objectif
Développer une application qui :
- Affiche la liste des capteurs disponibles sur l’appareil en utilisant ListView.
- Indique si des capteurs spécifiques (accéléromètre, proximité...) sont présents ou non.
- Permet d'afficher des détails sur un capteur en cliquant dessus.


#### Exercice 1 : Liste de capteurs disponibles
Nous avons développé une application qui récupère et affiche la liste des capteurs disponibles sur le smartphone.
👉 Utilisation du SensorManager pour récupérer les capteurs et les afficher dans un ListView, et ArrayAdapter pour lier les données à la ListView.

✔ Résultat attendu : L’application affiche une liste des capteurs (ex. : Accéléromètre, Gyroscope, etc.).

#### Exercice 2 : Détection de présence/absence de capteurs
Cet exercice vérifie la disponibilité de certains capteurs (accéléromètre, gyroscope, etc.) et informe l'utilisateur si un capteur est absent.

**Méthode utilisée** :

- Vérification avec getDefaultSensor(Sensor.TYPE_xxx).
- Affichage dynamique des capteurs disponibles ✅ et indisponibles ❌.
- TextView pour afficher les résultats des vérifications des capteurs.
  
✔ Résultat attendu : Une liste de capteurs indiquant s’ils sont présents ou non sur l’appareil.

#### Exercice 3 : Changement de couleur selon l’accéléromètre
L’application change la couleur du fond d’écran en fonction de l’intensité du mouvement détecté par l’accéléromètre.

👉 **Méthode utilisée** :

- Lecture des valeurs de l’accéléromètre (x, y, z).
- Calcul de l’accélération totale.
- Changement de couleur :
- Vert 🍃 → Valeurs faibles.
- Noir ⚫ → Valeurs moyennes.
- Rouge 🔴 → Valeurs élevées.
- 
✔ Résultat attendu : Lorsque l'utilisateur bouge son téléphone, l’écran change de couleur.

#### Exercice 4 : Détection de direction
Nous avons implémenté une application qui détecte la direction du mouvement (gauche, droite, haut, bas) à l’aide de l’accéléromètre.

👉 **Méthode utilisée** :

- Lecture des valeurs x et y du capteur.
- Définition des seuils :
- - - Gauche ⬅️ → x > 2
    - Droite ➡️ → x < -2
    - Haut ⬆️ → y > 2
    - Bas ⬇️ → y < -2
- Affichage dynamique de la direction en TextView.
- 
✔ Résultat attendu : L’application affiche "⬅️ Gauche" si on incline l’appareil vers la gauche, "➡️ Droite" si on l’incline à droite, etc.

#### Exercice 5 : Allumer/Éteindre le flash en secouant le téléphone
Cet exercice permet d’allumer et d’éteindre le flash en secouant le téléphone.

👉 **Méthode utilisée** :

- Détection d’un secouement via l’accéléromètre (seuil > 12 m/s²).
- Utilisation de l’API Camera pour activer/désactiver le flash.
- Alternance entre les états allumé et éteint à chaque secousse.

✔ Résultat attendu : Lorsque l’utilisateur secoue son téléphone, le flash s’allume 💡 et s’éteint 🔄 à la secousse suivante, tout en affichant un GIF.

#### Exercice 6 : Détection de proximité
L’application affiche une image de chaton correspondant à la proximité d’un objet devant le téléphone grâce au capteur de proximité.

👉 **Méthode utilisée**:

- Lecture des valeurs du capteur de proximité (distance en cm).
- Affichage d’une image différente selon la distance :
- Loin 🟢 → distance > 3
- Proche 🟠 → 1 ≤ distance ≤ 3
- Très proche 🔴 → distance < 1

✔ Résultat attendu : L’image change dynamiquement en fonction de la distance d’un objet devant l’écran.

#### Exercice 7 : Géolocalisation et ouverture de Google Maps
Cet exercice permet de récupérer et afficher la position géographique de l’utilisateur et d’ouvrir Google Maps pour afficher son emplacement.

👉 **Méthode utilisée** :

- Utilisation du LocationManager pour récupérer latitude et longitude.
- Conversion des coordonnées en adresse complète avec Geocoder.
- Ajout d’un bouton permettant d’ouvrir Google Maps :
- Recherche par coordonnées GPS (geo:latitude,longitude).
- Recherche par adresse (geo:0,0?q=adresse).
- Gestion des erreurs : Si le GPS est désactivé, un Toast informe l’utilisateur.

✔ Résultat attendu :

L’application affiche :
📍 Latitude : -12.0464
📍 Longitude : -77.0428
📌 Adresse : Lima, Pérou
En appuyant sur le bouton, Google Maps s’ouvre et affiche l’emplacement.


### Partie 2

#### Exercice 8

#### Objectif
Créer une application avec deux activités :
✔ Une première activité (MainActivity) affichant une liste cliquable de pays avec RecyclerView.
✔ Une deuxième activité (DetailActivity) affichant les détails du pays sélectionné (nom, capitale, population).

#### Technologies utilisées
- RecyclerView pour afficher une liste performante et optimisée.
- Intent pour passer les données du pays sélectionné entre les activités.

#### Implémentation
1️⃣ Création d’un RecyclerView dans MainActivity avec une liste de pays statique.
2️⃣ Création de Pays.kt pour stocker les informations (nom, capitale, population).
3️⃣ Création d’un RecyclerView.Adapter (PaysAdapter.kt) pour gérer l’affichage de la liste. Donc chaque pays a son propre design d'item
4️⃣ Ajout d’un OnClickListener dans l’adaptateur pour détecter le clic sur un pays.
5️⃣ Passage des données via un Intent vers DetailActivity.
6️⃣ Affichage des informations du pays sélectionné dans DetailActivity.


#### Exercice 9 

#### Objectif
Créer une application utilisant des Fragments qui :
✔ Affiche une liste de pays (France, Allemagne, Espagne, Italie, etc.).
✔ Affiche les détails d'un pays (nom, capitale, population) lorsqu'on le sélectionne.
✔ Utilise des Fragments pour afficher la liste, les détails et l'image de manière dynamique.

#### Technologies utilisées
ListView pour l’affichage de la liste des pays.
Fragments pour la séparation de la liste et des détails.
FrameLayout pour gérer l’affichage dynamique des fragments.
Bundle pour passer les informations du pays sélectionné.

 #### Implémentation
1️⃣ Création de Pays.kt, une classe data class pour représenter un pays.
2️⃣ Création de ListePaysFragment.kt, un fragment affichant la liste des pays avec RecyclerView.
3️⃣ Création de DetailsPaysFragment.kt, un fragment affichant les détails d’un pays.
4️⃣ Création de ImgPaysFragment.kt, un fragment affichant l'image associé au pays séléctionné.
5️⃣ Gestion de la navigation dans Exo9Activity.kt, qui charge dynamiquement les fragments selon l’écran.


