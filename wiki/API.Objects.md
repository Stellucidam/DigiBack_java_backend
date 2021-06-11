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
  "idUser" : 1,
  "type" : "FRONT_TILT",
  "date" : "2021-03-03",
  "movementDataCredentials" : [
    {
      "time" : "6.747455300E-2",
      "xLinearAcc" : "-3.747564554E-2",
      "yLinearAcc" : "3.365421295E-2",
      "zLinearAcc" : "-4.718399048E-2",
      "absoluteLinearAcc" : "6.901709220E-2"
    }
  ]
}
```

# Douleur
## Client
Quand le client doit envoyer un niveau de douleur au serveur.
```json
{
    "idUser" : 1,
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
## Serveur
Quand le serveur doit envoyer une activité au client.
```json
{
  "idUser": 1,
  "date": "2020-01-02",
  "nbrSteps": 12,
  "nbrExercices": 2,
  "nbrQuiz": 22
}
```
