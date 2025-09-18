package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne le nombre d'éléments présents dans la liste.
     * @return le nombre d'éléments dans la liste
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un nouvel élément au début de la liste.
     * @param element l'élément à ajouter en tête de liste
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie la première occurrence de l'élément recherché dans la liste.
     * Parcourt la liste depuis la tête jusqu'à trouver l'élément à modifier.
     * Si l'élément n'est pas trouvé, aucune modification n'est effectuée.
     * @param element l'élément à rechercher dans la liste
     * @param nouvelleValeur la nouvelle valeur à affecter à l'élément trouvé
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifie toutes les occurrences de l'élément recherché dans la liste.
     * Parcourt l'intégralité de la liste et remplace chaque occurrence trouvée.
     * @param element l'élément à rechercher dans la liste
     * @param nouvelleValeur la nouvelle valeur à affecter à toutes les occurrences trouvées
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Retourne une représentation textuelle de la liste.
     * @return une chaîne de caractères représentant la liste et ses éléments
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime la première occurrence de l'élément spécifié dans la liste.
     * @param element l'élément à supprimer de la liste
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime toutes les occurrences de l'élément spécifié dans la liste.
     * @param element l'élément dont toutes les occurrences doivent être supprimées
     */
    public void supprimeTous(int element) {
        tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Méthode récursive auxiliaire pour supprimer toutes les occurrences d'un élément.
     * @param element l'élément à supprimer
     * @param tete le nœud courant dans la récursion
     * @return la nouvelle tête de la sous-liste après suppression
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Retourne l'avant-dernier nœud de la liste.
     * @return l'avant-dernier nœud de la liste, ou null si la liste a moins de 2 éléments
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des éléments dans la liste.
     * Transforme la liste [A -> B -> C] en [C -> B -> A].
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Trouve et retourne le nœud précédent au nœud spécifié.
     * @param r le nœud dont on cherche le prédécesseur
     * @return le nœud qui précède r dans la liste
     * @throws NullPointerException si r n'appartient pas à la liste
     */
    public Noeud getPrecedent(Noeud r) {
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange la position de deux nœuds dans la liste.
     * Les nœuds eux-mêmes sont échangés, pas seulement leurs valeurs.
     * @param r1 le premier nœud à échanger
     * @param r2 le second nœud à échanger
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }
}
