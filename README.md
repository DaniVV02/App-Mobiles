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

👉 **Méthode utilisée** :

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



## TP3

L'enregistrement d'écran de ce TP est également présent dans ce dépôt.

### Partie 1

### Exercice 1

Pour cet exercice, nous avons créé dans un premier temps deux fragments : **FragmentInscription.kt** et **FragmentAffichage.kt**. Ces derniers sont gérés et affichés par **MainActivity.kt** ainsi que son layout **activity_main.xml** où on inclut la balise FrameLayout pour afficher ces différents fragments. 

Il faut remarquer que pour cet exercice j'ai suivi la maquette suivante du site Canva avec quelques changements : https://www.canva.com/design/DAGi2L6iLb8/vT5X5IE4TpZj28QaZMHe4Q/edit

Ensuite, pour que le bouton "Soumettre" sauvegarde les données de FragmentInscription dans une base de données, nous avons dû créer quelques fichiers suplémentaires. Tout d'abord, nous avons utilisé l'architecture Room + MVVM dans notre application Android pour gérer cette persistance de données.

Nous avons donc créé ainsi un fichier **AppDB.kt** qui crée et fournit une instance unique de la base de données Room (singleton). 
@Database: Définit les entités utilisées (Utilisateur, Planning) et la version. Nous avons défini également deux méthodes abstraites pour accéder aux DAOs (utilisateurDao() et planningDao()). Le companion object assure qu’il n’y ait qu’une seule instance de la base à tout moment (pattern Singleton).Et finalement fallbackToDestructiveMigration() supprime les anciennes données si le schéma change (version 1, 2, 3, etc.).

Ensuite, il nous fallait créer notre "table SQL" appelée **Utilisateur.kt**, il s'agit en réalité d'une classe de données (@Entity) représentant un utilisateur dans la base de données Room.
Chaque objet Utilisateur contient :

- id: clé primaire auto-générée.

- nom, nomUtilisateur, dateNaissance, telephone, email, motDePasse, centresInteret : toutes les infos nécessaires à l’inscription.

Après, nous avons créé un fichier **UtilisateurDao.kt**, une interface qui définit les méthodes d’accès à la base de données Room pour la table utilisateur. Dans ce fichier nous avons les opérations CRUD : @Insert, @Update, @Delete. Voici une petite explication des méthodes (requêtes SQL) : 

- supprimerParId(id) : supprime un utilisateur spécifique par son identifiant.

- supprimerTous() : supprime tous les utilisateurs.

- getAllUtilisateurs() : renvoie tous les utilisateurs sous forme de LiveData, ce qui permet de mettre à jour automatiquement l’interface dès qu’il y a un changement.

- getUtilisateurById(id) ou getUtilisateurByEmail(email) : pour récupérer un utilisateur en particulier.

- verifierLogin(identifiant, motDePasse) : vérifie si l’identifiant (email ou nom d’utilisateur) et mot de passe sont corrects.

- existeEmail(email) / existeNomUtilisateur(...) : vérifient si un email ou nom d’utilisateur est déjà utilisé.

- getPremierUtilisateur() : récupère le dernier utilisateur enregistré (le plus récent).


Et finalement nous avons créé le fichier **UtilisateurViewModel.kt** qui sert de pont entre l’interface utilisateur (UI) et la base de données pour les utilisateurs. En fait, il récupère le DAO avec AppDB.getInstance(...), et contient LiveData<List<Utilisateur>> pour observer tous les utilisateurs (tousLesUtilisateurs). Les méthodes inserer, modifier, supprimer, supprimerTous lancent des coroutines dans le viewModelScope pour faire les opérations en tâche de fond.

Ce fichier avec ses méthodes sera utilisé avec **ListeUtilisateursActivity.ky** où on pourra gérer les différents utilisateurs s'il s'agit de l'administrateur. Celui-ci est pré défini dans la base de données, si on se connecte en tant qu'administrateur, on pourra visualiser tous les utilisateurs inscrits ainsi que modifier leurs nom d'utilisateurs ou même les supprimer.


### Exercice 2

Pour cet exercice nous avons rajouté un fragment **FragmentLogin.kt**, et nous avons rendu cliquables le texte : "Connectez-vous ici". Lorsqu'on clique cet élèment, le formulaire du Login apparaît. C'est pareil avec ce fragment avec le texte "Inscrivez-vous ici" qui nous emmène vers **FragmentInscription.kt**.

Les vérifications indiquées dans l'exercice concernant certaines règles de champs de saisie ou de l'existence d'un utilisateur dans la base de données sont indiqués sous forme de "hint" dans les champs de saisie ou sous forme de **Toast** lorsqu'on clique sur le bouton en ne respectant pas ces règles.

Ensuite, nous avons rajouté une fonctionnalité qui n'a pas été demandé mais qui est très utile lorsque nous avons l'existence d'un administrateur. Celui-ci peut avoir accès à certaines fonctionnalités additionnelles que des utilisateurs "normaux" n'ont pas. Pour ce TP, nous avons choisi la possibilité de se connecter en tant qu'administrateur. Celui-ci pourra visualiser ainsi que modifier ou éliminer des utilisateurs comme nous l'avons dit dans l'exercice précédent. Les utilisateurs "normaux" n'auront pas accès à ce bouton qui permet de visualiser tous les utilisateurs.

Finalement, pour l'acitvité Planning, nous avons suivi la même méthodologie et architecture qu'avec Utilisateur. Nous avons donc créé **Planning.kt**, **PlanningViewModel**.kt et **PlanningDao.kt**.

Voici une petite explication des méthodes (requêtes SQL) dans **PlanningDao.kt** : 

- getDernierPlanning() → récupère le dernier planning ajouté pour un utilisateur.

- getPlanningsParEmailOuUsername() → récupère tous les plannings pour un utilisateur selon son email ou son pseudo.

- getAllPlannings() → expose une liste observable (LiveData) pour afficher automatiquement tous les plannings.

- getById() → utile pour retrouver un planning spécifique.


### Partie 2

En cours...


