# Table des matières

- [Table des matières](#table-des-matières)
- [Liste des endpoints](#liste-des-endpoints)
    - [Authentification](#authentification)
      - [`POST /auth/register`](#post-authregister)
      - [`POST /auth/login`](#post-authlogin)
      - [`POST /auth/logout`](#post-authlogout)
    - [Mouvements](#mouvemements)
      - [`POST /movement/user/{idUser}/upload/level/{level}`](#post-movementuseriduseruploadlevellevel)
      - [`POST /movement/user/{idUser}/upload`](#post-movementuseriduserupload)
    - [Statistiques](#statistiques)
    - [Douleurs](#douleurs)
      - [`POST /pain/user/{idUser}/upload/`](#post-painuseriduserupload)
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
    "message": "The movement data and pain level was uploaded successfully."
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

# Erreurs
Si une erreur apparait, un message expliquant la raison de cette dernière est envoyé.
En voici un exemple :
```json
{
  "http-status": 404,
  "error": "The requested resource could not be found."
}
```
