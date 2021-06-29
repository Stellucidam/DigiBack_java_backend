# Table des matières

- [Table des matières](#table-des-matières)
- [Liste des endpoints](#liste-des-endpoints)
    - [Authentification](#authentification)
      - [`POST /auth/register`](#post-authregister)
      - [`POST /auth/login`](#post-authlogin)
      - [`POST /auth/logout`](#post-authlogout)
      - [`POST /auth/verify/user/{userId}`](#post-authverifyuseruserid)
    - [Mouvements](#mouvemements)
      - [`POST /movement/user/{idUser}/upload/level/{level}`](#post-movementuseriduseruploadlevellevel)
      - [`POST /movement/user/{idUser}/upload`](#post-movementuseriduserupload)
    - [Statistiques](#statistiques)
    - [Douleurs](#douleurs)
      - [`POST /pain/user/{idUser}/upload/`](#post-painuseriduserupload)
    - [Activité](#activite)
        - [`POST /activity/user/{idUser}/upload`](#post-activityuseriduserupload)
        - [`GET /activity/user/{idUser}/date/{date}`](#get-activityuseriduserdatedate)
- [Erreurs](#erreurs)

# Liste des endpoints

## Authentification

### `POST /auth/register`
Permet d'enregistrer un nouvel utilisateur.

La représentation json d'un utilisateur doit être envoyée dans le corps de la requête.

Si la requête est un succès, le serveur envoi l'utilisateur ainsi crée en retour.


### `POST /auth/login`
Permet a un utilisateur de se "logger".

La représentation json d'un utilisateur doit être envoyée dans le corps de la requête.

Si la requête est un succès, le serveur envoi le token de l'utilisateur connecté en retour.


### `POST /auth/logout`
Permet à un utilisateur connecté de se "délogger".

La représentation json d'un utilisateur doit être envoyée dans le corps de la requête.

Si la requête est un succès, le serveur envoi un status indiquant le succès de l'action.


### `POST /auth/verify/user/{userId}`
Permet à un nouvel utilisateur de valider son adresse e-mail.

Le token de vérification de l'utilisateur doit être envoyée dans le corps de la requête.

Si la requête est un succès, le serveur envoi un status indiquant le succès de l'action.

## Mouvements

### `POST /movement/user/{idUser}/upload/level/{level}`
Permet d'"uploader" un nouveau mouvement avec un niveau de douleur associé pour un utilisateur donné.

L'identifiant de l'utilisateur doit remplacer le champ `{idUser}` dans la requête.
Le niveau de douleur de l'utilisateur pour le movement doit remplacer le champ `{level}` dans la requête.
Le token de l'utilisateur doit être entré dans les paramètres de la requête.
La représentation json d'un mouvement doit être envoyée dans le corps de la requête.

Si la requête est un succès, le serveur envoi un status indiquant le succès de l'action.
```json
{
    "status": "success",
    "message": "The movement data and activity level was uploaded successfully."
}
```

### `POST /movement/user/{idUser}/upload`
Permet d'"uploader" un nouveau mouvement pour un utilisateur donné.

L'identifiant de l'utilisateur doit remplacer le champ `{idUser}` dans la requête.
Le token de l'utilisateur doit être entré dans les paramètres de la requête.
La représentation json d'un mouvement doit être envoyée dans le corps de la requête.

Si la requête est un succès, le serveur envoi un status indiquant le succès de l'action.
```json
{
    "status": "success",
    "message": "The movement data was uploaded successfully."
}
```

## Statistiques
*Pas encore implémenté.*

## Douleurs
### `POST /pain/user/{idUser}/upload/`
Permet d'"uploader" un nouveau niveau de douleur pour un utilisateur donné.

L'identifiant de l'utilisateur doit remplacer le champ `{idUser}` dans la requête.
Le token de l'utilisateur doit être entré dans les paramètres de la requête.
La représentation json d'un niveau de douleur doit être envoyée dans le corps de la requête.

Si la requête est un succès, le serveur envoi un status indiquant le succès de l'action.
```json
{
    "status": "success",
    "message": "The pain data was uploaded successfully."
}
```

## Activité
### `POST /activity/user/{idUser}/upload`
Permet d'"uploader" une nouvelle activité pour un utilisateur donné.

L'identifiant de l'utilisateur doit remplacer le champ `{idUser}` dans la requête.
Le token de l'utilisateur doit être entré dans les paramètres de la requête.
La représentation json d'une activité doit être envoyée dans le corps de la requête.

Si la requête est un succès, le serveur envoi un status indiquant le succès de l'action.
```json
{
    "status": "success",
    "message": "The activity data was uploaded successfully."
}
```

### `GET /activity/user/{idUser}/date/{date}`
Permet de récupérer l'activité d'un utilisateur pour une date donnée.

L'identifiant de l'utilisateur doit remplacer le champ `{idUser}` dans la requête.
La date voulue doit remplacer le champ `{date}` dans la requête.
Le token de l'utilisateur doit être entré dans les paramètres de la requête.

Si la requête est un succès, le serveur envoi l'activité sous format json. 
Sinon, une erreur est envoyée.

# Erreurs
Si une erreur apparait, un message expliquant la raison de cette dernière est envoyé.
En voici un exemple :
```json
{
  "http-status": 404,
  "error": "The requested resource could not be found."
}
```
