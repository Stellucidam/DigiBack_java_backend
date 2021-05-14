# Table des matières

- [Table des matières](#table-des-matières)
- [List des endpoints](#list-des-endpoints)
    - [Authentification](#authentification)
    - [Mouvements](#mouvemements)
    - [Statistiques](#statistiques)
- [Erreurs](#erreurs)

# List des endpoints

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

# Erreurs
Si une erreur apparait, un message expliquant la raison de cette dernière est envoyé.
En voici un exemple :
```json
{
  "error": "The requested resource could not be found."
}
```
