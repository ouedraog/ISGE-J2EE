TP

# Développement d’une application bancaire pour JBank.

Un client peut ouvrir plusieurs comptes via le site de la banque. 
A l'ouverture il doit renseigner son nom, prénom, numéro de téléphone, adresse email, date de naissance, nationalité.

Une fois la demande effectuée le compte est créé et on fournit au client un numéro de compte en 6 chiffres et un mot de passe de 4 chiffres généré de façon aléatoire. A la première connexion le client est invité à changer le mot de passe en choisissant un autre mot de passe de 4 chiffres.
Les mots de passe simples sont proscrits (séquence, exemple : 1111, 0000, 1,2,3,4, et  la date de naissance du client). 

Une fois le mot de passe client changé il peut avoir accès à son compte et son solde et l'historique de ses entrées et sorties (virement émis et reçu ).

Chaque entrée sortie doit avoir une date et un libellé ( virement émis sur le compte xxx, virement reçu de xxx).
Le client a la possibilité de faire un virement vers un autre compte. Pour ce faire il doit indiquer le numéro de compte du destinataire et son nom et prénom et le montant du virement. Pour que le virement soit accepté il faut que le solde soit supérieur. Une fois le virement ok le solde est mis à jour et le compte destinataire crédité.

Pour que le virement soit accepté il faut que le solde après l'opération soit positif

Ajouter des logs pour afficher les informations pertinentes et les erreurs éventuelles.
Ajouter des tests unitaires permettant de vérifier qu'un mot de passe respcte la spécification indiquée.

# Notes
L'application doit être réalisée avec Struts2, maven, hibernate
Le choix de la base de donnée est libre (la base H2 peut aussi être utilisée pour faciliter le devéloppement)