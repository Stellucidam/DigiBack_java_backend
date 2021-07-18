# Table des matières

- [Table des matières](#table-des-matières)
- [Utilisateur](#utilisateur)
  - [Client](#client)
  - [Serveur](#serveur)
- [Mouvement](#mouvement)
  - [Client](#client-1)
- [Douleur](#douleur)
  - [Client](#client-2)
- [Activité](#activite)
  - [Client](#client-3)
    - [Pas](#pas)
    - [Quiz](#quiz)
    - [Exercices](#exercices)
  - [Serveur](#serveur-1)

# Utilisateur

## Client
Quand le client doit envoyer un utilisateur au serveur.
```json
{
  "username": "name",
  "password" : "password"
}
```
## Serveur
Quand le serveur doit envoyer un utilisateur au client.
```json
{
    "token": "7JOq_pV-WxJbkCYlLgoTAuOoPyYjBgwf9HxkkY-emeB62qRczACevQlhlLyd7D9hT56uA7CyJzDt9L-6H_VqZw==",
    "idUser": 1
}
```

# Mouvement
## Client
Quand le client doit envoyer un mouvement au serveur.
```json
{
  "type" : "FRONT_TILT",
  "date" : "2021-03-03",
  "angles" : [
    {
      "position" : 0,
      "angle" : 91.2
    },
    {
      "position" : 1,
      "angle" : 90.24
    },
    {
      "position" : 2,
      "angle" : 89.1
    }
  ]
}
```

# Douleur
## Client
Quand le client doit envoyer un niveau de douleur au serveur.
```json
{
    "movementType" : "FRONT_TILT",
    "date" : "2021-03-03",
    "level" : 4
}
```


# Activité
## Client
Quand le client doit envoyer une activité au serveur.
```json
{
  "date": "2020-01-01",
  "nbrSteps": 12,
  "nbrExercices": 2,
  "nbrQuiz": 22
}
```
### Pas
Quand le client doit envoyer un nombre de pas au serveur.
```json
{
  "date": "2020-01-01",
  "nbrSteps": 12
}
```
### Quiz
Quand le client doit envoyer un nombre de quiz au serveur.
```json
{
  "date": "2020-01-01",
  "nbrQuiz": 22
}
```
### Exercices
Quand le client doit envoyer un nombre d'exercices au serveur.
```json
{
  "date": "2020-01-01",
  "nbrExercices": 2
}
```

## Serveur
Quand le serveur doit envoyer une activité au client.
```json
{
  "date": "2020-01-02",
  "nbrSteps": 12,
  "nbrExercices": 2,
  "nbrQuiz": 22
}
```
